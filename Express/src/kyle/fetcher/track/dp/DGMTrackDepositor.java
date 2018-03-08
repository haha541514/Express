package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class DGMTrackDepositor extends SingleTrackDepositor {
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
				String tracks=fTrack.getFieldValue("����"); 
				String[] track=tracks.split("<div class=\"flowdgrm-top\">");
				for(int i=1;i<track.length;i++){
					String[] lbAll=track[i].split("<label");  
					String lb1 = lbAll[1].substring(lbAll[1].indexOf("flowdgrm-top-font\">")+19,lbAll[1].indexOf("</label>"));
					String lb2 = lbAll[2].substring(lbAll[2].indexOf("flowdgrm-time-font\">")+20,lbAll[2].indexOf("</label>"));
					String lb3 = lbAll[3].substring(lbAll[3].indexOf("important;\">")+12,lbAll[3].indexOf("</label>"));
					String lb4 = lbAll[4].substring(lbAll[4].indexOf("flowdgrm-status-font \">")+23,lbAll[4].indexOf("</label>"));

					String Time =lb1+","+lb2;
					String date = "to_date('"+Time+"' ,'dd month,hh24:mi', 'NLS_DATE_LANGUAGE=American')";
					FRecord frecord = new FRecord();
					frecord.putField("ʱ��", date);
					frecord.putField("�ص�",lb3);
					frecord.putField("����", lb4);
					currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
				}
			}
			listFBlockTrack.set(0, currentformatBlock);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
