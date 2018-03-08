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

		// �����������δȡ�����켣�����δȡ���켣���򱣴汾����ȡ���Ĺ켣��
		// ��Ҫ���ڷ�ֹ����ȡ�켣�����ڣ����������Ѿ�ȡ���켣��
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
			//�����ҳ���Ϊ�գ��򱣴浱ǰҳ�켣��Ϣ
			if(page == null || "".equals(page)){	
			// �ڱ���POD������TRACK����ǰ��Ԥ�ȴ������ݡ�
	            preDealPODTrack(listFBlockPOD, listFBlockTrack, htParameter);
				// ����POD��Ϣ
	            savePOD(listFBlockPOD, htParameter);
				// ����켣��ϸ�������ر��β���Ĺ켣��ϸ����
	            intInsertedTrack = saveAllTrack(listFBlockTrack, htParameter);	            
	            // ��ӡ��Ϣ
	            System.out.println("Cwcode:" + strCwCode + "<--�켣������"
						+ intInsertedTrack + " ����:" + strWPARCode + "-->");
				// ��������˹켣��ϸ������±��˵���ȡ�켣����
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
			// ��Ϊֻ��һ��POD��
			objFBlockSignFor = vctPODFBlock.get(0);
			if (objFBlockSignFor == null) {
				return 0;
			}			
			// ��ȡPOD���е�����ǩ�ռ�¼
			vctFRecordSignFor = objFBlockSignFor.getFRecordList(ITrackBasic.FTR_SIGNFOR);
			if (vctFRecordSignFor != null) {
				if (vctFRecordSignFor.size() == 1) {
					try {
						// ��Ϊֻ��һ��ǩ�ռ�¼����ֻȡ��һ����
						objFRecordSignFor = vctFRecordSignFor.get(0);
						// ���ǩ�ռ�¼Ϊ�գ����ٱ��档
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

						// ����˵��Ѿ��������״̬�����ٸ��¡�
						strCwcode = ((String) htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE)).trim();
						if (TrackWayBillDemand.existWayBillFinishStatus(strCwcode)) {
							return 0;
						}
						// ����POD��
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
	 * ����վ��ʾ�켣��˳�����ȷ�������ʾʱ�����ô˷�������켣�� 
	 * �㷨�� 
	 * 1.����վ����һ���켣��ʼ����������켣��ϵͳ�в��������������켣��������������������еĶ����ٲ��롣 
	 * 2.����ǰ�ж϶�Ӧҵ���Ƿ��Ѿ�ǩ�գ����û��ǩ���ٲ��롣 <
	 * p/> ̽�֣�
	 * ��ǰ���뷨����ҳ�����µĹ켣�Ƿ��ϵͳ�����µĹ켣��ʱ��Ҫ�£���������DHL��GLS�������¹켣��ʱ�䲻�ԡ�
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
			// ��ȡ�켣��¼
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
				// �ڱ��浥��TRACK����ǰ��Ԥ�ȴ�����TRACK�����TRACK֮���Ĺ�ϵ��
				preDealSingleTrack(listTrackFBlock, objWBTColumns);
				listWBTColumns.add(objWBTColumns);
				// ��Ҫ������Ѿ�����ʱ����false�����ٱ���
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
				// �������ι켣��Ϊ����״̬
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
		// Ϊ������Ҫ����
		if (StringUtility.isNull(strWbtscode))
			return;
		// ״̬һ������Ҫ����
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

	// �ڱ���POD������TRACK����ǰ��Ԥ�ȴ������ݡ�
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
	}

	// �ڱ��浥��TRACK����ǰ��Ԥ�ȴ�����TRACK�����TRACK֮���Ĺ�ϵ��
	public void preDealSingleTrack(List<FBlock> vctTrackFBlock,
			WaybilltrackColumns objWBTrackColumns) {
	}
	
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		return strOriginLocation;
	}
}
