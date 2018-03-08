package kyle.fetcher.track.dp;


import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class TNTTrackDepositor extends SingleTrackDepositor {

	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {

		List<FRecord> listPODRecord = listFBlockPOD.get(0).getFRecordList();
		List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
		FRecord recordPOD = listPODRecord.get(0);
		String delivery = cutString(recordPOD.getFieldValue("ǩ������"));
		String name = cutString(recordPOD.getFieldValue("ǩ����"));
		delivery = "to_date('"+delivery+"','hh24:mi,dd month yyyy','NLS_DATE_LANGUAGE=American')";
		String status = null;
		if (name != null && !"".equals(name)) 
		{
			status = "SF";
		}
		FRecord fecordPOD = new FRecord();
		fecordPOD.putField("ǩ��״̬", status);
		fecordPOD.putField("ǩ����", name);
		fecordPOD.putField("ǩ������", delivery);
		FBlock fblockPOD = new FBlock();
		fblockPOD.addFRecord(ITrackBasic.FTR_SIGNFOR, fecordPOD);
		listFBlockPOD.set(0, fblockPOD);

		FBlock fblockTrack = new FBlock();
		FRecord recordTrack = null;
		for (int i = 0; i < listTrackRecord.size(); i++) {
			recordTrack = listTrackRecord.get(i);
			String date = cutString(recordTrack.getFieldValue("����"));
			String time = cutString(recordTrack.getFieldValue("ʱ��"));
			String location = cutString(recordTrack.getFieldValue("�ص�"));
			String description = cutString(recordTrack.getFieldValue("����"));
			String datetime = date.concat(" ").concat(time);
			datetime = "to_date('"+datetime+"','dd month yyyy hh24:mi:ss','NLS_DATE_LANGUAGE=American')";		
			FRecord frecordTrack = new FRecord();
			frecordTrack.putField("����", description);
			frecordTrack.putField("�ص�", location);
			frecordTrack.putField("ʱ��", datetime);
			fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
		}
		listFBlockTrack.set(0, fblockTrack);
	}

	public String cutString(String str) {
		int strLength = str.length();
		str = str.substring(0, strLength - 2);
		return str;
	}

}
