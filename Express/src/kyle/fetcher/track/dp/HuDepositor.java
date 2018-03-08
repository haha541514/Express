package kyle.fetcher.track.dp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;

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

public class HuDepositor extends SingleTrackDepositor {
	// �ڱ���POD������TRACK����ǰ��Ԥ�ȴ������ݡ�
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {
		System.out.println("����Ԥ�������");
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock currentformatBlock = new FBlock();
		listTrackFormatRecord = listFBlockTrack.get(0).getFRecordList(
				ITrackBasic.FTR_TRACK);
		for (int i = 0; i < listTrackFormatRecord.size(); i++) {
			FRecord frecord = new FRecord();
			frecord.putField("ʱ��", DateFormat(listTrackFormatRecord.get(i).getFieldValue(
					"����").substring(
					listTrackFormatRecord.get(i).getFieldValue("����").indexOf(
							'>') + 1,
					listTrackFormatRecord.get(i).getFieldValue("����").length())
					+ " "
					+ listTrackFormatRecord.get(i).getFieldValue("ʱ��")
							.substring(
									listTrackFormatRecord.get(i).getFieldValue(
											"ʱ��").indexOf('>') + 1,
									listTrackFormatRecord.get(i).getFieldValue(
											"ʱ��").length())));
			if (-1!=(listTrackFormatRecord.get(i)
					.getFieldValue("����").indexOf("/"))){
				frecord.putField("����", listTrackFormatRecord.get(i)
						.getFieldValue("����").split("/")[1].substring(1,listTrackFormatRecord.get(i)
								.getFieldValue("����").split("/")[1].length()));
			}
			else{
				frecord.putField("����", "this filed has no details");
			}
			currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
			
		}
		listFBlockTrack.remove(0);
		listFBlockTrack.add(0, currentformatBlock);
	}
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
	            //savePOD(listFBlockPOD, htParameter);
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
   
   private int saveAllTrack(List<FBlock> listTrackFBlock,
			Hashtable<String, String> htParameter) {
		String strCocode, strChncode, strCwcode,strHuCode;
		int intInsertedTrack = 0;
		FRecord objFRecord;
		FBlock objFBlock;
		List<FRecord> listFRecord;
		strCocode = htParameter.get(ITrackBasic.DEPOSITOR_COMPANYID);
		strCwcode = htParameter.get(ITrackBasic.DEPOSITOR_BUSINESSCODE);
		strChncode = htParameter.get(ITrackBasic.DEPOSITOR_CHANNELCODE);
		strHuCode = htParameter.get("wbtFromCode");

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
				objFRecord = listFRecord.get(listFRecord.size()-j-1);
				WaybilltrackColumns objWBTColumns = new WaybilltrackColumns();
				objWBTColumns.setCococode(strCocode);
				objWBTColumns.setWbbtcwcode(Long.parseLong(strCwcode));				
				objWBTColumns.setWbtwbtorigindescription(objFRecord.getFieldValue(ITrackBasic.FTF_ORIGINDETAIL));
				objWBTColumns.setWbtwbtdescription(objFRecord.getFieldValue(ITrackBasic.FTF_DETAIL));
				objWBTColumns.setWbtwbtlocation(objFRecord.getFieldValue(ITrackBasic.FTF_SPOT));
				String strOccurDate = objFRecord.getFieldValue(ITrackBasic.FTF_DATETIME);
	         	//objWBTColumns.setWbtwbtoccurdate(DateFormatUtility.getDBStandardDate(strOccurDate));
				//objWBTColumns.setWbtwbtoccurdate(DateFormatUtility.getDBStandardDate("to_char(sysdate,'yy-mm-dd hh24:mi:ss')"));
				objWBTColumns.setWbtwbtoccurdate(DateFormatUtility.getStringDate(strOccurDate, DateFormatUtility.STANDARD));				// �ڱ��浥��TRACK����ǰ��Ԥ�ȴ�����TRACK�����TRACK֮���Ĺ�ϵ��
				objWBTColumns.setWbtwbtfrom(strHuCode);
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


	// �ڱ��浥��TRACK����ǰ��Ԥ�ȴ�����TRACK�����TRACK֮���Ĺ�ϵ��
	public void preDealSingleTrack(List<FBlock> vctTrackFBlock,
			WaybilltrackColumns objWBTrackColumns) {
	}
	
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		return strOriginLocation;
	}
	
	public String DateFormat(String strdate){
		String time=null;
		SimpleDateFormat formatter1=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    SimpleDateFormat formatter2=new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		try {
			time=formatter1.format(formatter2.parse(strdate.replace(".", "-")));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			s_objLogger.warning("����ת���쳣");
		}  
		return time;
   }
}
