package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class USDHLGlobeTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {	
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		FBlock fblockTrack = new FBlock();
		
		if (listTrackFormatRecord == null || listTrackFormatRecord.size() < 1)
			return;
		
		for (FRecord fTrack : listTrackFormatRecord){
			//ѭ�����ÿһ���켣
			String dateString = fTrack.getFieldValue("����");
			if (StringUtility.isNull(dateString))
				return;
			dateString = dateString + " 00:00:00";
			String date  = "to_date('"+dateString+"','dd.mm.yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
			
			String strLocation = fTrack.getFieldValue("�ص�");
			String status = fTrack.getFieldValue("״̬");
			if (StringUtility.isNull(status))
				return;
			status = cutSpecialString(status);
			
			FRecord frecordTrack = new FRecord();
			frecordTrack.putField("����", status);
			frecordTrack.putField("�ص�", strLocation);
			frecordTrack.putField("ʱ��", date);
			fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
		}
		listFBlockTrack.set(0, fblockTrack);
	}
	
	private String cutSpecialString(String strSource) {
		if (StringUtility.isNull(strSource))
			return strSource;
		if (strSource.indexOf("&amp;") > 0) {
			strSource = strSource.replaceAll("&amp;", ""); 
		}
		return strSource;
	}

}
