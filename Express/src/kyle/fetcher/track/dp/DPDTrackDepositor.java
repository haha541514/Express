package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class DPDTrackDepositor extends SingleTrackDepositor {
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
			String s= fTrack.getFieldValue("����");
			s = s.replaceAll("DPD","");
			String xq = s.substring(s.indexOf("\"statusInfos\":"));
			xq = "{"+ xq ;
			JSONObject result =  JSONObject.fromObject(xq);
			JSONArray jaryStatusInfos = JSONArray.fromObject(result.get("statusInfos"));
			for(int i=jaryStatusInfos.size()-1;i>=0;i--){
				JSONObject statusInfos =  jaryStatusInfos.getJSONObject(i);
				String date =statusInfos.getString("date"); 
				String time =statusInfos.getString("time"); 
				String city =statusInfos.getString("city");
				JSONArray jaryContentss = JSONArray.fromObject(statusInfos.get("contents"));
				String label = "";
				for(int j=0;j<jaryContentss.size();j++){
					JSONObject contents = jaryContentss.getJSONObject(j);
					 label += contents.getString("label");
				}
				String[] strDate = date.split("\\.");
				String dateTime = "";
				for(int j = strDate.length-1;j>=0;j--){
					dateTime =dateTime+strDate[j];
				}
				String[] strTime = time.split(":");
				String sTime = "";
				for(int j = 0;j<strTime.length ;j++){
					sTime += strTime[j];
				}
				date = dateTime.trim()+sTime.trim();
				String dt ="to_date('"+ date+"', 'yyyyMMdd,hh24:mi', 'NLS_DATE_LANGUAGE=American')";
				FRecord frecord = new FRecord();
				frecord.putField("ʱ��", dt);
				frecord.putField("�ص�",city);
				frecord.putField("����", label);
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);			
			}
			}
		listFBlockTrack.set(0, currentformatBlock);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}
