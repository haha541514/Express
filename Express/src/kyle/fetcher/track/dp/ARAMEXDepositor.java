package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class ARAMEXDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {
		// ITrackBasic.FTF_SIGN_STATUS)
		List<FRecord> listPODRecord = null;
		if (listFBlockPOD != null && listFBlockPOD.size() > 0)
			listPODRecord = listFBlockPOD.get(0).getFRecordList();
		if (listPODRecord != null && listPODRecord.size() > 0) {
			String del = listPODRecord.get(0).getFieldValue("详情");
		    del=del.replaceAll("\\\\\"", "");
		    String status=del.substring(del.indexOf("CurrentStatus:"),del.indexOf("SRNNo"));
		    if(status.indexOf("Delivered")>=0){
		      String user=del.substring(del.indexOf("DeliveredTo:")+"DeliveredTo:".length(),del.indexOf(",PickupDate"));
		      String date=del.substring(del.indexOf("DeliveredDate:")+"DeliveredDate:".length(),del.indexOf(",DeliveredTo:"));
		      if (!StringUtility.isNull(date)) {
					int hh = date.indexOf("PM");
					date = date.substring(0, date.length() - 2).trim();
					if (hh > -1) {
						String hour = (date.substring(date.indexOf(" "), date
								.indexOf(":"))).trim();
						int ReadyHour = Integer.parseInt(hour) + 12;
						date.replace(" " + hour, " "
								+ String.valueOf(ReadyHour));
						// time.re
					}
		    }
		      date = "to_date('"+ date+ "', 'mm/dd/yyyy hh24:mi:ss', 'NLS_DATE_LANGUAGE=American')";
		        FRecord frecordPod = new FRecord();
				frecordPod.putField("签收状态", "SF");
				frecordPod.putField("签收人", user.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecordPod.putField("签收日期",date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				FBlock fblockPod = new FBlock();
				fblockPod.addFRecord(ITrackBasic.FTR_SIGNFOR, frecordPod);
				listFBlockPOD.set(0, fblockPod); 
		    }
		}

		 List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
			FBlock currentformatBlock = new FBlock();
			FBlock trackBlock;
			if (listFBlockTrack == null || listFBlockTrack.size() < 1) return;
			
			trackBlock = listFBlockTrack.get(0);
			listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
			for (FRecord fTrack : listTrackFormatRecord){
				//循环检查每一个轨迹
				String strContent=fTrack.getFieldValue("明细").replaceAll("\\\\\"", "").replaceFirst("ActionDate:","");
				String[] recodes=strContent.split("ActionDate:");
				for(int i=0;i<recodes.length;i++){
					String time=recodes[i].substring(0,recodes[i].indexOf(","));
					String location=recodes[i].substring(recodes[i].indexOf("UpdateLocationFormatted:")+"UpdateLocationFormatted:".length(),recodes[i].indexOf(",Comments1:"));
					String des=recodes[i].substring(recodes[i].indexOf("CustomerDescription:")+"CustomerDescription:".length(),recodes[i].indexOf(",TrackingConditionDescription:"));
					if (!StringUtility.isNull(time)) {
						int hh = time.indexOf("PM");
						time = time.substring(0, time.length() - 2).trim();
						if (hh > -1) {
							String hour = (time.substring(time.indexOf(" "), time
									.indexOf(":"))).trim();
							int ReadyHour = Integer.parseInt(hour) + 12;
							time.replace(" " + hour, " "
									+ String.valueOf(ReadyHour));
							// time.re
						}
			    }
					time = "to_date('"+ time+ "', 'mm/dd/yyyy hh24:mi:ss', 'NLS_DATE_LANGUAGE=American')";
				FRecord frecord = new FRecord();
				frecord.putField("时间", time.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("地点",location.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("详情", des.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
				}
				
			}
		listFBlockTrack.set(0, currentformatBlock);
	}

}
