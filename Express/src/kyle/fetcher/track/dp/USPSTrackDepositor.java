package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class USPSTrackDepositor extends SingleTrackDepositor {

	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {

		if (listFBlockPOD != null && listFBlockPOD.size() > 0) {
			List<FRecord> listPODRecord = listFBlockPOD.get(0).getFRecordList();
			if (listPODRecord != null && listPODRecord.size() > 0) {
				FRecord recordPOD = listPODRecord.get(0);
				String number = recordPOD.getFieldValue("单号");
				String service = recordPOD.getFieldValue("Service");
				
				FRecord fecordPOD = new FRecord();
				fecordPOD.putField("单号", number);
				fecordPOD.putField("service", service);
				/*
				if (service.indexOf("delivered") > 0) {
					fecordPOD.putField(ITrackBasic.FTF_SIGN_STATUS, "SF");
					if (service.indexOf(" in ") > 0)
						fecordPOD.putField(ITrackBasic.FTF_SIGNFOR_PERSON, service.substring(service.indexOf(" in ") + 4));
					fecordPOD.putField(ITrackBasic.FTF_SIGNFOR_DATE, number);
				}
				*/
				FBlock fblockPOD = new FBlock();
				fblockPOD.addFRecord(ITrackBasic.FTR_SIGNFOR, fecordPOD);
				
				listFBlockPOD.set(0, fblockPOD);
			}
		}
		
		if (listFBlockTrack != null && listFBlockTrack.size() > 0) {
			List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
			FBlock fblockTrack = new FBlock();
			FRecord recordTrack = null;
			for (int i = 0; i < listTrackRecord.size(); i++) {
				recordTrack = listTrackRecord.get(i);
				String date = replaceString(recordTrack.getFieldValue("日期"));			
				String location = replaceString(recordTrack.getFieldValue("地点"));
				String status = replaceUSPSBlank(replaceString(recordTrack.getFieldValue("状态")));		
				//String features = replaceString(recordTrack.getFieldValue("features"));	
				
				date = dateString(date);
				date  = "to_date('"+date+"','month dd, yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
				
				try {
					location = rebuildLocation(location);
				} catch (Exception ex) {
					
				}
				if (StringUtility.isNull(location))
					continue;
				
				FRecord frecordTrack = new FRecord();
				frecordTrack.putField("详情", status);
				frecordTrack.putField("地点", location);
				frecordTrack.putField("时间", date);
				//frecordTrack.putField("features", features);
				fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
				//System.out.println("date:" + date);
				//System.out.println("location:" + location);
				//System.out.println("description:" + status);
				//System.out.println("features:" + features);
				//System.out.println("===============");
			}
			listFBlockTrack.set(0, fblockTrack);
		}
	}

	public String replaceString(String str) {
		if (StringUtility.isNull(str))
			return "";
		str = str.replaceAll("<[^>]*>", ""); 		
		str = str.replaceAll("\t|\r|\n","");
		str = str.trim();
		str = str.replace("&nbsp;", " ");
		str = str.replace("&#153;", "");
		if (str.indexOf(">") >= 0) {
			str = str.substring(str.indexOf(">") + 1).trim();
		}
		return str;
	}
    
	public String replaceUSPSBlank(String str) {
		if (!StringUtility.isNull(str)) {
			str = str.replace("USPS ", "");
			str = str.replace("usps ", "");
		}
		return str;
	}	
	
	
	public static String dateString(String strDate){		
		String date[] = strDate.split(":");
		if(date.length == 1){
			return strDate;
		}else{			
			String str = strDate.substring(strDate.length() -2,strDate.length());			
			if(str.equals("pm")){
				int iLastIndex = date[0].lastIndexOf(",");
				int h = Integer.parseInt(date[0].substring(iLastIndex + 1, date[0].length()).trim());
				h = h + 12;
				if (h < 24) {
					date[0] = date[0].substring(0,date[0].length()-2);
					if (!date[0].substring(date[0].length()-1).equals(" "))
						date[0] = date[0] + " ";
					strDate = date[0].concat(String.valueOf(h).concat(":").concat(date[1]));
				}
			}
			strDate = strDate.substring(0,strDate.length() -3);
			return strDate;
		}
	}
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		if (StringUtility.isNull(strOriginLocation))
			return "";
		if (strOriginLocation.indexOf(" - ") >= 0)
			return strOriginLocation;
		
		if (strOriginLocation.substring(strOriginLocation.length() - 1).equals(" "))
			strOriginLocation = strOriginLocation.substring(0, strOriginLocation.length() - 1);
		
		if (strOriginLocation.indexOf(",") > 0)
			strOriginLocation = strOriginLocation.replaceAll(", ", " - ");
		
		int iLastBlankIndex = strOriginLocation.lastIndexOf(" ");
		String str = strOriginLocation.substring(0, iLastBlankIndex);
		if (str.indexOf(" ") >= 0) {
			strOriginLocation = str;
			return strOriginLocation + " - " + "UNITED STATES OF AMERICA";
		}
		return strOriginLocation;
	}	
	
	public static void main(String[] args) {
		try {
			USPSTrackDepositor objFTD = new USPSTrackDepositor();
			System.out.println(objFTD.rebuildLocation("ELK GROVE"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	
}
