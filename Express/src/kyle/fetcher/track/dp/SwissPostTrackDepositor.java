package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.common.util.textformat.bl.TextFormatRule;
import kyle.common.webpageaccess.bl.APageFetcher;
import kyle.common.webpageaccess.bl.IFTextDepositor;
import kyle.common.webpageaccess.rule.bl.WebPageAccessRule;
import kyle.fetcher.track.dax.ITrackBasic;

public class SwissPostTrackDepositor extends SingleTrackDepositor{
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
			
			//循环检查每一个轨迹
			String[] tracks=fTrack.getFieldValue("明细").split("<td class=\"event_date\">");
			for (int i=0;i<tracks.length-1;i++){
				String date=tracks[i+1].substring(tracks[i+1].indexOf("</span>")+7,tracks[i+1].indexOf("</td>")).trim();
				date=date+tracks[i+1].substring(tracks[i+1].indexOf("<td class=\"event_time\">")+23,tracks[i+1].indexOf("<td class=\"event_event\">")).replace("</td>", "").trim();
				String des=tracks[i+1].substring(tracks[i+1].indexOf("<td class=\"event_event\">")+24,tracks[i+1].indexOf("<td class=\"event_city\">"));
				des=des.substring(des.indexOf("</span>")+7,des.indexOf("</td>")).trim();
				String loaction =tracks[i+1].substring(tracks[i+1].indexOf("<td class=\"event_city\">")+23);
				loaction=loaction.substring(0,loaction.indexOf("</td>")).trim();
				date ="to_date('"+ date+"', 'dd.mm.yyyyhh24:mi', 'NLS_DATE_LANGUAGE=American')";
		        FRecord frecord = new FRecord();
				frecord.putField("时间", date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("地点",loaction.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("详情", des.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
			}
			
				
		}
		listFBlockTrack.set(0, currentformatBlock);
	
	} catch (Exception e) {
		e.printStackTrace();
	}
 }

}
