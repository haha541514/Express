package kyle.fetcher.track.dp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class FranchEMSTrackDepositor extends SingleTrackDepositor{
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
			String[] days=fTrack.getFieldValue("明细").split("<div class=\"trace_column tc_date\">");
			for(int i = 0; i< days.length-1; i++){
				String date=days[i+1].substring(0,days[i+1].indexOf("</div>"));
				String loaction=days[i+1].substring(days[i+1].indexOf("<div class=\"trace_column tc_location\">")+"<div class=\"trace_column tc_location\">".length(),days[i+1].indexOf("<div class=\"trace_column tc_comment\">"));
				loaction=loaction.substring(0,loaction.indexOf("&nbsp;"));
				String des=days[i+1].substring(days[i+1].indexOf("<div class=\"trace_column tc_comment\">")+"<div class=\"trace_column tc_comment\">".length(),days[i+1].lastIndexOf("</div>"));
				des=des.substring(0,des.indexOf("</div>")).trim();
				date ="to_date('"+ date+"', 'mon dd, yyyy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
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