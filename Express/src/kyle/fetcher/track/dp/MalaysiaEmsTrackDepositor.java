package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class MalaysiaEmsTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		try {
		FBlock trackBlock;  
		// ��ʽ���飬  ��POD��͹켣����֡�
		// ��ʽ����¼����POD��¼�͹켣��¼���֡� ��¼�����ֶ�
		// �����POD��¼�͹켣��¼��
		
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock currentformatBlock = new FBlock();
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		for (FRecord fTrack : listTrackFormatRecord){
			//ѭ�����ÿһ���켣
			String[] tracks=fTrack.getFieldValue("��ϸ").split("<tr bgcolor=\"white\">");
			for(int i = 0; i< tracks.length-1; i++){
				String []track=tracks[i+1].split("<td><font color=\"black\">");
				String date=track[1].substring(0,track[1].indexOf("</font></td>")).trim();
				date=date+","+track[2].substring(0,track[2].indexOf("</font></td>")).trim();
				String des=track[3].substring(0,track[3].indexOf("</font></td>")).trim();
				String loaction=track[4].substring(0,track[4].indexOf("</font></td>")).trim();
				date ="to_date('"+ date+"', 'dd-mon-yyyy,hh24mi', 'NLS_DATE_LANGUAGE=American')";
		        FRecord frecord = new FRecord();
				frecord.putField("ʱ��", date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("�ص�",loaction.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("����", des.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		}
		listFBlockTrack.set(0, currentformatBlock);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}
