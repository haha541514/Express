package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;
import java.util.logging.Logger;
import java.util.*;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.fetcher.track.dax.TrackWayBillDemand;
import kyle.fetcher.track.tp.SavePODTransaction;
import kyle.fetcher.track.tp.SaveTrackTransaction;
import kyle.fetcher.track.tp.SaveTrackWebSiteTransaction;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.eo.customerservice.track.tp.SaveBatchTrackTransaction;

/**
 * User: Kyle 
 * Date: 2010-2-9 
 * Time: 16:37:56
 */
public class SingleTrackDepositor implements IFTextDepositor {
	static Logger s_objLogger = Logger.getLogger(SingleTrackDepositor.class.getName());

	public int process(FText objFText, TextFormatRule objTFR,
			Hashtable<String, String> htParameter) {
		List<FBlock> listFBlockPOD;
		List<FBlock> listFBlockTrack;
		List<FBlock> listFBlockPage;
		String strCwCode, strWPARCode;
		int intInsertedTrack = 0;

		if (objFText == null) {
			return 0;
		}

		// 如果其它规则未取到过轨迹或根本未取过轨迹，则保存本规则取到的轨迹。
		// 主要用于防止本次取轨迹周期内，其它规则已经取到轨迹。
		strCwCode = ((String) htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
		strWPARCode = ((String) htParameter.get(ITrackBasic.FETCHER_WPARCODE)).trim();
		if (!TrackWayBillDemand.existDifferTrackWebSite(strCwCode, strWPARCode)) {			
			listFBlockPOD = objFText.getFBlockList(ITrackBasic.FTB_POD);
			listFBlockTrack = objFText.getFBlockList(ITrackBasic.FTB_TRACK_REPORT);		
			listFBlockPage = objFText.getFBlockList(ITrackBasic.FTB_PAGE);
			String page = null;
			if(listFBlockPage != null && listFBlockPage.size()>0){
				FRecord recordPage = listFBlockPage.get(0).getFRecordList().get(0);
				page = recordPage.getFieldValue(ITrackBasic.FTF_PAGE);
			}
			//如果翻页标记为空，则保存当前页轨迹信息
			if(page == null || "".equals(page)){	
			// 在保存POD及所有TRACK数据前，预先处理数据。
	            preDealPODTrack(listFBlockPOD, listFBlockTrack, htParameter);
				// 保存POD信息
	            savePOD(listFBlockPOD, htParameter);
				// 保存轨迹明细，并返回本次插入的轨迹明细数量
	            intInsertedTrack = saveAllTrack(listFBlockTrack, htParameter);	            
	            // 打印信息
	            System.out.println("Cwcode:" + strCwCode + "<--轨迹数量："
						+ intInsertedTrack + " 渠道:" + strWPARCode + "-->");
				// 如果插入了轨迹明细，则更新本运单的取轨迹规则
				if (intInsertedTrack > 0) {
					saveTrackWebSite(strCwCode, strWPARCode);
				}
			}
			
		}
		return 0;
	}

	private int savePOD(List<FBlock> vctPODFBlock,Hashtable<String, String> htParameter) {
		FBlock objFBlockSignFor;
		FRecord objFRecordSignFor;
		List<FRecord> vctFRecordSignFor;
		SavePODTransaction objSavePodTransaction;
		String strCwcode = "";

		objSavePodTransaction = new SavePODTransaction();
		if (vctPODFBlock != null && vctPODFBlock.size() >= 1) {
			// 认为只有一个POD块
			objFBlockSignFor = vctPODFBlock.get(0);
			if (objFBlockSignFor == null) {
				return 0;
			}			
			// 获取POD块中的所有签收记录
			vctFRecordSignFor = objFBlockSignFor.getFRecordList(ITrackBasic.FTR_SIGNFOR);
			if (vctFRecordSignFor != null) {
				if (vctFRecordSignFor.size() == 1) {
					try {
						// 认为只有一个签收记录，故只取第一条。
						objFRecordSignFor = vctFRecordSignFor.get(0);
						// 如果签收记录为空，则不再保存。
						if (objFRecordSignFor == null) {
							return 0;
						}
						// Set sPOD;
						// sPOD = objFRecordSignFor.getFFieldNames();
						if (StringUtility.isNull(objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGN_STATUS)) || 
								StringUtility.isNull(objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGNFOR_PERSON)) || 
								StringUtility.isNull(objFRecordSignFor.getFieldValue(ITrackBasic.FTF_SIGNFOR_DATE))) {
							return 0;
						}

						// 如果运单已经处于完成状态，则不再更新。
						strCwcode = ((String) htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
						if (TrackWayBillDemand.existWayBillFinishStatus(strCwcode)) {
							return 0;
						}
						// 保存POD。
						objSavePodTransaction.setPOD(objFRecordSignFor,htParameter);
						objSavePodTransaction.execute();
					} catch (Exception ex) {
						ex.printStackTrace();
						s_objLogger.warning("Save pod failed! " + ex);
					}
				} else if (vctFRecordSignFor.size() > 1) {
					s_objLogger.warning("Single hawb track depositor can't process multi sign for info!");
				}
			}
		}
		return 1;
	}

	/**
	 * 当网站显示轨迹的顺序是先发生先显示时，调用此方法保存轨迹。 
	 * 算法： 
	 * 1.从网站最后的一条轨迹开始，如果此条轨迹在系统中不存在则插入此条轨迹，如果此条存在了则所有的都不再插入。 
	 * 2.插入前判断对应业务是否已经签收，如果没有签收再插入。 <
	 * p/> 探讨：
	 * 以前的想法是网页上最新的轨迹是否比系统中最新的轨迹的时间要新，但是遇到DHL和GLS可能最新轨迹的时间不对。
	 * @param vctTrackFBlock
	 * @param htParameter
	 * @return
	 */
	private int saveAllTrack(List<FBlock> listTrackFBlock,
			Hashtable<String, String> htParameter) {
		String strCocode, strChncode, strCwcode;
		int intInsertedTrack = 0;
		FRecord objFRecord;
		FBlock objFBlock;
		List<FRecord> listFRecord;
		strCocode = htParameter.get(ITrackBasic.DEPOSITOR_COMPANYID);
		strCwcode = htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE);
		strChncode = htParameter.get(ITrackBasic.DEPOSITOR_CHANNELCODE);

		if (listTrackFBlock == null) {
			return 0;
		}
		
		List<WaybilltrackColumns> listWBTColumns = new ArrayList<WaybilltrackColumns>();
		for (int i = 0; i < listTrackFBlock.size(); i++) {
			objFBlock = listTrackFBlock.get(i);
			// 获取轨迹记录
			listFRecord = objFBlock.getFRecordList(ITrackBasic.FTR_TRACK);
			if (listFRecord == null || listFRecord.size() < 1)
				return 0;
			for (int j = listFRecord.size() - 1; j >= 0; j--) {
				objFRecord = listFRecord.get(j);
				WaybilltrackColumns objWBTColumns = new WaybilltrackColumns();
				objWBTColumns.setCococode(strCocode);
				objWBTColumns.setWbbtcwcode(Long.parseLong(strCwcode));				
				objWBTColumns.setWbtwbtorigindescription(objFRecord.getFieldValue(ITrackBasic.FTF_ORIGINDETAIL));
				objWBTColumns.setWbtwbtdescription(objFRecord.getFieldValue(ITrackBasic.FTF_DETAIL));
				objWBTColumns.setWbtwbtlocation(objFRecord.getFieldValue(ITrackBasic.FTF_SPOT));
				String strOccurDate = objFRecord.getFieldValue(ITrackBasic.FTF_DATETIME);
				objWBTColumns.setWbtwbtoccurdate(DateFormatUtility.getDBStandardDate(strOccurDate));
				// 在保存单行TRACK数据前，预先处理单行TRACK与多行TRACK之单的关系。
				preDealSingleTrack(listTrackFBlock, objWBTColumns);
				listWBTColumns.add(objWBTColumns);
				// 当要保存的已经存在时返回false，则不再保存
				intInsertedTrack = intInsertedTrack + 1;
			}
		}
		if (!saveTracks(listWBTColumns, strChncode))
			intInsertedTrack = 0;
		return intInsertedTrack;
	}

	private boolean saveTracks(List<WaybilltrackColumns> listWBTColumns,
			String strChncode) {
		List<WaybilltrackColumns> listSavedWBTColumns = new ArrayList<WaybilltrackColumns>();
		if (listWBTColumns == null || listWBTColumns.size() < 1)
			return false;
		WaybilltrackColumns objWBTColumns = null;
		for (int i = 0; i < listWBTColumns.size(); i++) {
			objWBTColumns = listWBTColumns.get(i);
			if (!TrackWayBillDemand.existTrack(objWBTColumns.getWbbtcwcode(),
					objWBTColumns.getCococode(), 
					objWBTColumns.getWbtwbtdescription(), 
					objWBTColumns.getWbtwbtlocation(),
					objWBTColumns.getWbtwbtoccurdate())) {
				listSavedWBTColumns.add(objWBTColumns);
			}
		}
		try {
			if (listSavedWBTColumns == null || listSavedWBTColumns.size() < 1) {
				// 更新批次轨迹表为最新状态
				if (objWBTColumns != null) {
					saveBatchTrack(objWBTColumns,
							strChncode);
				}
				return false;
			}
			SaveTrackTransaction objSTT = new SaveTrackTransaction();
			objSTT.setTracks(listSavedWBTColumns, strChncode);
			objSTT.execute();
			return true;
		} catch (Exception ex) {
			ex.printStackTrace();
			s_objLogger.warning("Save track failed!" + ex);
			return false;
		}
	}
	
	private void saveBatchTrack(WaybilltrackColumns objWBTColumns,
			String strChncode) throws Exception {
		WaybillbatchtrackColumns objWBBTColumns = TrackDemand.loadBatchTrack(objWBTColumns.getWbbtcwcode());
		boolean isInsetBatchTrack = false;
		if (objWBBTColumns == null) {
			objWBBTColumns = new WaybillbatchtrackColumns();
			isInsetBatchTrack = true;
		}
		
		String strWbtscode = objWBTColumns.getWbtswbtscode();
		if (StringUtility.isNull(strWbtscode)) {
			strWbtscode = TrackWayBillDemand.mapTrackStatus(strChncode, 
					objWBTColumns.getWbtwbtdescription());
			objWBTColumns.setWbtswbtscode(strWbtscode);
		}
		// 为空则不需要更新
		if (StringUtility.isNull(strWbtscode))
			return;
		// 状态一致则不需要更新
		if (!StringUtility.isNull(objWBBTColumns.getWbtswbtscode()) &&
				objWBBTColumns.getWbtswbtscode().equals(strWbtscode))
			return;
		
		objWBBTColumns.setWbbtcwcode(Long.parseLong(objWBTColumns.getWbbtcwcode()));
		objWBBTColumns.setWbbtwbbtlatesttrackdesc(objWBTColumns.getWbtwbtdescription());
		objWBBTColumns.setWbtswbtscode(strWbtscode);
		
		if (!StringUtility.isNull(objWBTColumns.getWbtwbtoccurdate()))
			objWBBTColumns.setWbbtwbbtlatesttrackdate(DateFormatUtility.getStandardDate(objWBTColumns.getWbtwbtoccurdate()));
		
		SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
		objSBTTrans.setParam(objWBBTColumns, isInsetBatchTrack);			
		objSBTTrans.execute();
	}
		
		
	private int saveTrackWebSite(String strBSCode, 
			String strTFRCode) {
		SaveTrackWebSiteTransaction objSaveTrackWebSiteTransaction;
		objSaveTrackWebSiteTransaction = new SaveTrackWebSiteTransaction();
		try {
			objSaveTrackWebSiteTransaction.setTrackWebSite(strBSCode,strTFRCode);
			objSaveTrackWebSiteTransaction.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			s_objLogger.warning("Save TrackWebSite failed! " + ex);
		}
		return 1;
	}

	// 在保存POD及所有TRACK数据前，预先处理数据。
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
	}

	// 在保存单行TRACK数据前，预先处理单行TRACK与多行TRACK之单的关系。
	public void preDealSingleTrack(List<FBlock> vctTrackFBlock,
			WaybilltrackColumns objWBTrackColumns) {
	}
	
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		return strOriginLocation;
	}
}
