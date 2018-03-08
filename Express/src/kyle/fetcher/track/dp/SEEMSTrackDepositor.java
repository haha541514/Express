package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class SEEMSTrackDepositor extends SingleTrackDepositor {

	private Hashtable<String, String> m_htParameter;
	
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
		FBlock fblockTrack = new FBlock();
		FRecord recordTrack = null;
		String location = "";
		m_htParameter = htParameter;
		
		String strOriginDate = "";
		String strDestinDate = "";
		
		if (listTrackRecord == null || listTrackRecord.size() < 1) {
			listFBlockTrack.set(0, fblockTrack);
			return;
		}		
		
		for (int i = 0; i < listTrackRecord.size(); i++) {
			recordTrack = listTrackRecord.get(i);
			String date = replaceString(recordTrack.getFieldValue("日期"));
			String description = replaceString(recordTrack.getFieldValue("详情"));
			if (description.indexOf("origin") > 0)
				strOriginDate = date;
			if (description.indexOf("destination") > 0)
				strDestinDate = date;			
		}
		
		
		for (int i = 0; i < listTrackRecord.size(); i++) {
			recordTrack = listTrackRecord.get(i);
			String date = replaceString(recordTrack.getFieldValue("日期"));			
			String description = replaceString(recordTrack.getFieldValue("详情"));
			
			String strWebDisplaydate = dateString(date);
			date  = "to_date('" + strWebDisplaydate + "','mm/dd/yy hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
			
			try {
				location = rebuildLocation(description);
			} catch (Exception ex) {
				
			}
			if (StringUtility.isNull(location) &&
					!StringUtility.isNull(strWebDisplaydate)) {
				if (strWebDisplaydate.compareTo(strDestinDate) <= 0 || 
						strWebDisplaydate.compareTo(strOriginDate) <= 0)
					location = " - SINGAPORE";
				else {
					if (m_htParameter != null && m_htParameter.size() > 0 &&
							m_htParameter.containsKey(ITrackBasic.DEPOSITOR_DESTINATION)) {
						location = " - " + m_htParameter.get(ITrackBasic.DEPOSITOR_DESTINATION);
					}
				}
			}
			
			FRecord frecordTrack = new FRecord();
			frecordTrack.putField("详情", description);
			frecordTrack.putField("地点", location);
			frecordTrack.putField("时间", date);
			fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
		}
		listFBlockTrack.set(0, fblockTrack);
	}

	public String replaceString(String str) {
		str = str.replaceAll("<[^>]*>", ""); 		
		str = str.replaceAll("\t|\r|\n","");
		str = str.trim();
		str = str.replace("&nbsp;", " ");
		str = str.replace("&#153;", "");
		return str;
	}
    
	public static String dateString(String strDate){		
		String date[] = strDate.split(":");
		if(date.length == 1){
			return strDate;
		}else{			
			String str = strDate.substring(strDate.length() -2,strDate.length());			
			if(str.equals("PM")){
				int h = Integer.parseInt(date[0].substring(date[0].length()-2,date[0].length()).trim());
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
	
	public String rebuildLocation(String description) throws Exception {
		if (StringUtility.isNull(description))
			return "";
		if (description.indexOf(" - ") >= 0)
			return description;
		
		String strLocation = "";
		if (description.indexOf("origin") > 0) {
			return " - SINGAPORE";
		}
		if (description.indexOf("destination") > 0) {
			if (m_htParameter != null && m_htParameter.size() > 0 &&
					m_htParameter.containsKey(ITrackBasic.DEPOSITOR_DESTINATION)) {
				return " - " + m_htParameter.get(ITrackBasic.DEPOSITOR_DESTINATION);
			}
		}
		return strLocation;
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
