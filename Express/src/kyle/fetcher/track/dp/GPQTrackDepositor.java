package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class GPQTrackDepositor extends SingleTrackDepositor {
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
			System.out.println(fTrack.getFieldValue("��ϸ"));
			String[] days=fTrack.getFieldValue("��ϸ").substring(fTrack.getFieldValue("��ϸ")
					.indexOf("\"x\":[{")).substring(10).replaceAll("\"","")
					.replaceAll("\\{","").replaceAll("\\},","").replace("y:[", "").replace("],", "")
					.replace("z:", "").replaceAll("\'"," ").split("a:");
			for(int i = 0; i< days.length; i++){
				String datetime=days[i].substring(0,days[i].indexOf(","));
				//System.out.println(datetime);
				datetime = "to_date('" + datetime + "','yyyy-MM-dd hh24:mi:ss')";
				String des=days[i].substring(days[i].indexOf("b:")+2);
				//System.out.println(des);
				FRecord frecord = new FRecord();
				frecord.putField("ʱ��", datetime);
				//frecord.putField("�ص�","1");
				frecord.putField("����", des);
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		}
		listFBlockTrack.set(0, currentformatBlock);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}


