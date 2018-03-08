package kyle.fetcher.track.dp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.common.util.textformat.bl.FText;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.fetcher.track.dax.TrackWayBillDemand;
import kyle.fetcher.track.tp.SaveTrackTransaction;
import kyle.fetcher.track.tp.SaveTrackWebSiteTransaction;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.eo.customerservice.track.tp.SaveBatchTrackTransaction;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;

public class SiacargoDepositor extends SingleTrackDepositor {
	public int process(FText objFText, TextFormatRule objTFR,
			Hashtable<String, String> htParameter) {
		List<FBlock> listFBlockPOD;
		List<FBlock> listFBlockTrack;
		List<FBlock> listFBlockPage;
		String strCwCode, strWPARCode, strChncode,strtwbCode;
		int intInsertedTrack = 0;
		if (objFText == null) {
			return 0;
		}
		strChncode = htParameter.get(ITrackBasic.DEPOSITOR_CHANNELCODE);
		strCwCode = ((String) htParameter
				.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
		strtwbCode  = ((String) htParameter
				.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
		// 更新航空主单下面的所有运单
		List<TransportcorewaybillColumns> list = null;
		try {
			list = TransportWaybillDemand.loadCorewaybill(strCwCode);
		} catch (Exception e) {
			s_objLogger.warning("获取批次运单异常!!!!");
		}
		strWPARCode = ((String) htParameter.get(ITrackBasic.FETCHER_WPARCODE))
				.trim();
		if (list == null || list.size() < 1)
			return 0;
		for (int z = 0; z < list.size(); z++) {
			strCwCode = list.get(z).getCwcw_code();
			htParameter.put("BusinessCode", strCwCode);
			htParameter.put("TwbCode", strtwbCode);
			try {
				htParameter.put("CompanyID", CorewaybillDemand
						.loadSimpleCorewaybill(strCwCode)
						.getCwco_code_supplier());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				s_objLogger.warning("获取服务商轨渠道失败 ");
			}
			if (!TrackWayBillDemand.existDifferTrackWebSite(strCwCode,
					strWPARCode)) {
				listFBlockPOD = objFText.getFBlockList(ITrackBasic.FTB_POD);
				listFBlockTrack = objFText
						.getFBlockList(ITrackBasic.FTB_TRACK_REPORT);
				listFBlockPage = objFText.getFBlockList(ITrackBasic.FTB_PAGE);
				String page = null;
				if (listFBlockPage != null && listFBlockPage.size() > 0) {
					FRecord recordPage = listFBlockPage.get(0).getFRecordList()
							.get(0);
					page = recordPage.getFieldValue(ITrackBasic.FTF_PAGE);
				}
				intInsertedTrack = saveAllTrack(listFBlockTrack, htParameter);
				System.out.println("Cwcode:" + strCwCode + "<--轨迹数量："
						+ intInsertedTrack + " 渠道:" + strWPARCode + "-->");
				if (intInsertedTrack > 0) {
					saveTrackWebSite(strCwCode, strWPARCode);
				}
			}
		}
		return 0;
	}

	private int saveAllTrack(List<FBlock> listTrackFBlock,
			Hashtable<String, String> htParameter) {
		String strCocode, strChncode, strCwcode,strwbtcode;
		int intInsertedTrack = 0;
		FRecord objFRecord;
		FBlock objFBlock;
		List<FRecord> listFRecord;
		strCocode = htParameter.get(ITrackBasic.DEPOSITOR_COMPANYID);
		strCwcode = htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE);
		strChncode = htParameter.get(ITrackBasic.DEPOSITOR_CHANNELCODE);
		strwbtcode = htParameter.get("TwbCode");

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
			for (int j = 0; j < listFRecord.size(); j++) {
				objFRecord = listFRecord.get(j);
				WaybilltrackColumns objWBTColumns = new WaybilltrackColumns();
				objWBTColumns.setCococode(strCocode);
				objWBTColumns.setWbbtcwcode(Long.parseLong(strCwcode));
				objWBTColumns.setWbtwbtorigindescription(objFRecord
						.getFieldValue(ITrackBasic.FTF_ORIGINDETAIL));
				objWBTColumns.setWbtwbtdescription(objFRecord
						.getFieldValue(ITrackBasic.FTF_DETAIL));
				objWBTColumns.setWbtwbtlocation(objFRecord
						.getFieldValue(ITrackBasic.FTF_SPOT));
				Date strOccurDate = dateFormate(objFRecord
						.getFieldValue(ITrackBasic.FTF_DATE)
						+ " " + objFRecord.getFieldValue(ITrackBasic.FTF_TIME));
				objWBTColumns.setWbtwbtoccurdate(strOccurDate);
				objWBTColumns.setWbtwbtfrom(strwbtcode);
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
					objWBTColumns.getCococode(), objWBTColumns
							.getWbtwbtdescription(), objWBTColumns
							.getWbtwbtlocation(), objWBTColumns
							.getWbtwbtoccurdate())) {
				listSavedWBTColumns.add(objWBTColumns);
			}
		}
		try {
			if (listSavedWBTColumns == null || listSavedWBTColumns.size() < 1) {
				// 更新批次轨迹表为最新状态
				if (objWBTColumns != null) {
					saveBatchTrack(objWBTColumns, strChncode);
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
		WaybillbatchtrackColumns objWBBTColumns = TrackDemand
				.loadBatchTrack(objWBTColumns.getWbbtcwcode());
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
		if (!StringUtility.isNull(objWBBTColumns.getWbtswbtscode())
				&& objWBBTColumns.getWbtswbtscode().equals(strWbtscode))
			return;

		objWBBTColumns.setWbbtcwcode(Long.parseLong(objWBTColumns
				.getWbbtcwcode()));
		objWBBTColumns.setWbbtwbbtlatesttrackdesc(objWBTColumns
				.getWbtwbtdescription());
		objWBBTColumns.setWbtswbtscode(strWbtscode);

		if (!StringUtility.isNull(objWBTColumns.getWbtwbtoccurdate()))
			objWBBTColumns.setWbbtwbbtlatesttrackdate(DateFormatUtility
					.getStandardDate(objWBTColumns.getWbtwbtoccurdate()));

		SaveBatchTrackTransaction objSBTTrans = new SaveBatchTrackTransaction();
		objSBTTrans.setParam(objWBBTColumns, isInsetBatchTrack);
		objSBTTrans.execute();
	}

	private int saveTrackWebSite(String strBSCode, String strTFRCode) {
		SaveTrackWebSiteTransaction objSaveTrackWebSiteTransaction;
		objSaveTrackWebSiteTransaction = new SaveTrackWebSiteTransaction();
		try {
			objSaveTrackWebSiteTransaction.setTrackWebSite(strBSCode,
					strTFRCode);
			objSaveTrackWebSiteTransaction.execute();
		} catch (Exception ex) {
			ex.printStackTrace();
			s_objLogger.warning("Save TrackWebSite failed! " + ex);
		}
		return 1;
	}

	// 在保存POD及所有TRACK数据前，预先处理数据。
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {
	}

	// 在保存单行TRACK数据前，预先处理单行TRACK与多行TRACK之单的关系。
	public void preDealSingleTrack(List<FBlock> vctTrackFBlock,
			WaybilltrackColumns objWBTrackColumns) {
	}

	public String rebuildLocation(String strOriginLocation) throws Exception {
		return strOriginLocation;
	}

	private Date dateFormate(String date) {
		SimpleDateFormat f1 = new SimpleDateFormat("dd MMM yyyy HH:mm",
				Locale.US);
		SimpleDateFormat f2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		java.util.Date d1 = null;
		try {
			d1 = f1.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			s_objLogger.warning("时间转化异常 ");
		}
		return d1;
	}

}
