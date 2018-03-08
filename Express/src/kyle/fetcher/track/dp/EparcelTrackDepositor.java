package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class EparcelTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		try {
			FBlock trackBlock;  
			// 格式化块，  有POD块和轨迹块二种。
			// 格式化记录，有POD记录和轨迹记录二种。 记录中有字段
			// 具体的POD记录和轨迹记录。
			
			List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
			FBlock currentformatBlock = new FBlock();
			trackBlock = listFBlockTrack.get(0);
			listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
			for (FRecord fTrack : listTrackFormatRecord){
			String tracks= fTrack.getFieldValue("详情").replaceAll("<tr>","").
			replaceAll("<tr bgcolor=\"#F5F7FA\">","").replaceAll("<td align=\"left\"  >","").
			replaceAll(System.getProperty("line.separator"),"").replaceAll("\\s+"," ");
			String[] tr = tracks.split("</tr>");
			for(int i=1;i<tr.length;i++){
				String[] td = tr[i].split("</td>");
//				for(int j=0;j<td.length;i++){
					if(td.length != 5){
						continue;
					}
					String date = td[0].replaceAll("/","")+" "+td[1];
					String dt ="to_date('"+ date+"', 'ddMMyyyy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
					FRecord frecord = new FRecord();
					frecord.putField("时间", dt);
					frecord.putField("地点",td[3]);
					frecord.putField("详情", td[2]);
					currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);	
//				}
			}
		}
		listFBlockTrack.set(0, currentformatBlock); 
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}
