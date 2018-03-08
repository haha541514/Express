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

public class DHLGBTrackDepositor extends SingleTrackDepositor {
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
			//System.out.println(listTrackFormatRecord.get(0).getFieldValue("明细")+"++++++++++++++++++++++++++++");
			//循环检查每一个轨迹
			String[] days=fTrack.getFieldValue("明细").replaceAll("DHL", "").split("<li class=\"timeline-date\">");
			for(int i = 0; i< days.length; i++){
				String field = days[i];
				String date=field.substring(0,field.indexOf("</li>"));
				String []records=days[i].split("<div class=\"timeline-time\">");
				for(int j=0;j<records.length-1;j++){
					String timeString=records[j+1].substring(4,records[j+1].indexOf("</div>"));
					timeString=timeString.replace("</em>", " ");
					//String booleanTime=time.substring(time.length()-2);
					//System.out.println(booleanTime+"___________________________________");
					//time=time.substring(0,time.length()-3);
					timeString=date+" "+timeString;
					SimpleDateFormat sim=new SimpleDateFormat("MMM dd, yyyy hh:mm a",Locale.ENGLISH);
					
					Date time=sim.parse(timeString);
					SimpleDateFormat s=new SimpleDateFormat("MMM dd, yyyy HH:mm",Locale.ENGLISH);
					timeString=s.format(time);
					System.out.println(s.format(time));
				
					String location=records[j+1].substring(records[j+1].indexOf("<div class=\"timeline-location\">"),records[j+1].indexOf("<div class=\"timeline-description\">"));
					location=location.substring(location.indexOf("</i>")).trim().replaceAll("\n", "").replaceAll("\t", "").replaceAll("\r", "")
					.replace("</i>", "").replace("</div>", "");
					String des=records[j+1].substring(records[j+1].indexOf("<div class=\"timeline-description\">"));
				    des=des.substring(0,des.indexOf("</div>")).replace("<div class=\"timeline-description\">", "");
				    System.out.println(s.format(time)+" "+location+" "+des);
				
				
				//时间中加上日期， 再转换成ORACLE的格式。
				if (!StringUtility.isNull(timeString)) {
					//Monday, January 05, 2009 11:13
					timeString = "to_date('"+ timeString +"', 'mon dd, yyyy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
					//System.out.println(time+"__---------------____");
				} else {
					if (htParameter.containsKey(ITrackBasic.DEPOSITOR_CHECKINDATE)) {
						timeString = htParameter.get(ITrackBasic.DEPOSITOR_CHECKINDATE);
					} else {
						timeString = DateFormatUtility.getStandardSysdate();
					}
					timeString= timeString.substring(0, 12);
					
					timeString = "to_date('"+ timeString +"', 'yyyy-mm-dd')";
			}
				FRecord frecord = new FRecord();
				frecord.putField("时间", timeString);
				frecord.putField("地点", location);
				frecord.putField("详情", des);
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
			}
		}
		}
		listFBlockTrack.set(0, currentformatBlock);
	
	} catch (ParseException e) {
		e.printStackTrace();
	}
 }
}
