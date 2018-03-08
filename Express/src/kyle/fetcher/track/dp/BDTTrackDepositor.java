package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class BDTTrackDepositor extends SingleTrackDepositor {
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
			String tracks=fTrack.getFieldValue("详情").replaceAll("<tr","").
			replaceAll("bgcolor=\"#A1DCF2\">","").replaceAll("<font color=\"#005E8A\" size=\"3\">","").
			replaceAll("bgcolor=\"White\">","").replaceAll("</font>","").replaceAll("<td>","").
			replaceAll("\n","").replaceAll("\r","").replaceAll("\t","").replaceAll("</th></tr>","");
			String[] tr = tracks.split("</tr>");
			for(int i=0;i<tr.length;i++){
				String[] td = tr[i].split("</td>");
//				for(int j=0;j<td.length;i++){
					if(td.length != 5){
						continue;
					}
					String date = td[3].replaceAll("/","");
					String dt ="to_date('"+ date+"', 'ddMMyyyy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
					FRecord frecord = new FRecord();
					frecord.putField("时间", dt);
					frecord.putField("地点",td[2]);
					frecord.putField("详情", td[4]);
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
