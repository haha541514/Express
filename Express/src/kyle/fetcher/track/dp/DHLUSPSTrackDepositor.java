package kyle.fetcher.track.dp;

import java.util.ArrayList;

import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class DHLUSPSTrackDepositor extends SingleTrackDepositor {

	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {

		FBlock trackBlock;
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock currentformatBlock = new FBlock();
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock
		.getFRecordList(ITrackBasic.FTR_TRACK);
		for (FRecord fTrack : listTrackFormatRecord) {
			String[] detailed = fTrack.getFieldValue("明细").split(
			"<li class=\"timeline-date\">");
			System.out.println("detailed:" + detailed.length);
			for (int i = 1; i < detailed.length; i++) {
				String field = detailed[i];
				String date = field.substring(0, field.indexOf("</li>"));
				date = replaceString(date);
				String[] records = field
				.split("<div class=\"timeline-time\"><em>");
				for (int j = 1; j < records.length; j++) {
					String timeString = records[j].substring(0, records[j]
					                                                    .indexOf("</div>"));
					timeString = replaceString(timeString);
					timeString = dateString(timeString);
					timeString = date + ", " + timeString;
					timeString = "to_date('"
						+ timeString
						+ "','mm/dd/yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
					String location;
					try {
						location = records[j]
						                   .substring(
						                		   records[j]
						                		           .indexOf("<div class=\"timeline-location\">"),
						                		           records[j]
						                		                   .indexOf("<div class=\"timeline-description\">"));
						location = replaceString(location);
						location = rebuildLocation(location);

					} catch (Exception ex) {
						// continue;
						location = "";
					}
					String des = records[j].substring(records[j]
					                                          .indexOf("<div class=\"timeline-description\">"));
					des = des.substring(0, des.indexOf("</div>")).replace(
							"<div class=\"timeline-description\">", "");
					
					if(!(des.trim().equals("Received Dispatched")||des.trim().equals("Processing at Destination")||
							des.trim().equals("Leaving Origin")||des.trim().equals("Customer Printed Label")||
							des.trim().equals("Left Transit Hub")||des.trim().equals("Arrived at Destination")||
							des.trim().equals("En route to DHL eCommerce Distrbution Center")||des.trim().equals("En Route")||
							des.trim().equals("Delivery delay-Recipients request to hold for future de"))){
					FRecord frecord = new FRecord();
//					if(des.equals("En route to DHL eCommerce Distribution Center")){
//						String depositor=htParameter.get(ITrackBasic.DEPOSITOR_CHECKINDATE);
//						if(depositor!=null||depositor!=""){
//							if(depositor.trim().length()<=10){
//								depositor =depositor+" 00:00:00";
//							}
//							GregorianCalendar gcCheckIn = new GregorianCalendar();
//							Date objDate = DateFormatUtility.getStandardDate(depositor);
//							gcCheckIn.setTime(objDate);
//							gcCheckIn.add(GregorianCalendar.DATE,1);
//							String CheckInDate = DateFormatUtility.getStandardDate(gcCheckIn);
//							frecord.putField("时间", CheckInDate);
//						}
//					}else{
//						frecord.putField("时间", timeString);
//					}
					frecord.putField("时间", timeString);
					frecord.putField("地点", location);
					frecord.putField("详情", des);
					currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK,
							frecord);
					// System.out.println("时间:" + timeString + ";地点:" + location
					// + ";详情:" + des);
					}
				}
			}

		}
		listFBlockTrack.set(0, currentformatBlock);

	}

	public String replaceString(String str) {
		str = str.replaceAll("class=\"bold\"", "");
		str = str.replaceAll("</div>", "");
		str = str.replaceAll("<div class=\"timeline-location\">", "");
		str = str.replaceAll("</em>", " ");
		str = str.replaceAll(">", "");
		str = str.replaceAll("<[^>]*>", "");
		str = str.replaceAll("\t|\r|\n", "");
		str = str.replaceAll("class=\"bold\"", "");
		str = str.trim();
		str = str.replace("&nbsp;", " ");
		str = str.replace("&#153;", "");
		return str;
	}

	public static String dateString(String strDate) {
		/*
		 * strDate = strDate.substring(0, strDate.length() - 3); String date[] =
		 * strDate.split(":"); if (date.length == 1) { return strDate; } else {
		 * String str = strDate.substring(strDate.length() - 2,
		 * strDate.length()).toLowerCase(); if (str.equals("PM")) { int h =
		 * Integer.parseInt(date[0].trim()); h = h + 12; if (h < 24) { strDate =
		 * String.valueOf(h).concat(":").concat(date[1]); } } strDate =
		 * strDate.substring(0, strDate.length() - 2); return strDate; }
		 */

		String date[];
		if (strDate.indexOf("PM") != -1) {
			strDate = strDate.substring(0, strDate.indexOf("PM") - 1);
			date = strDate.split(":");
			int h = Integer.parseInt(date[0].trim());
			h = h + 12;
			if (h < 24) {
				strDate = String.valueOf(h).concat(":").concat(date[1]);
			}
		} else {
			strDate = strDate.substring(0, strDate.indexOf("AM") - 1);
		}
		return strDate;

	}

	public String rebuildLocation(String strOriginLocation) throws Exception {
		if (StringUtility.isNull(strOriginLocation))
			return "";
		if (strOriginLocation.indexOf(" - ") >= 0)
			return strOriginLocation;

		if (strOriginLocation.substring(strOriginLocation.length() - 1).equals(
		" "))
			strOriginLocation = strOriginLocation.substring(0,
					strOriginLocation.length() - 1);

		if (strOriginLocation.indexOf(",") > 0)
			strOriginLocation = strOriginLocation.replaceAll(", ", " - ");

		return strOriginLocation.toUpperCase();

	}

	public static void main(String[] args) {
		try {
			String str = "09:45 AM ET";
			System.out.println(dateString(str));
			str = "11:45 PM ET";
			System.out.println(dateString(str));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
