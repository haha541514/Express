package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class SEEMSTrackDepositorSE extends SingleTrackDepositor {

	private Map<String, String> m_htParameter;
	
	private static Map<String, String> s_htSEMonth;
	private static Map<String, String> s_htStandardMonth;
	private static List<String> s_listOriginLocation;
	private static List<String> s_listDestLocation;
	
	static {
		s_htSEMonth = new HashMap<String, String>();
		s_htSEMonth.put("jan", "1");
		s_htSEMonth.put("feb", "2");
		s_htSEMonth.put("mar", "3");
		s_htSEMonth.put("apr", "4");
		s_htSEMonth.put("maj", "5");
		s_htSEMonth.put("jun", "6");
		s_htSEMonth.put("jul", "7");
		s_htSEMonth.put("aug", "8");
		s_htSEMonth.put("sept", "9");
		s_htSEMonth.put("okt", "10");
		s_htSEMonth.put("nov", "11");
		s_htSEMonth.put("dec", "12");
		
		s_htStandardMonth = new HashMap<String, String>();
		s_htStandardMonth.put("1", "January");
		s_htStandardMonth.put("2", "February");
		s_htStandardMonth.put("3", "March");
		s_htStandardMonth.put("4", "April");
		s_htStandardMonth.put("5", "May");
		s_htStandardMonth.put("6", "June");
		s_htStandardMonth.put("7", "July");
		s_htStandardMonth.put("8", "August");
		s_htStandardMonth.put("9", "September");
		s_htStandardMonth.put("10", "October");
		s_htStandardMonth.put("11", "November");
		s_htStandardMonth.put("12", "December");
		
		s_listOriginLocation = new ArrayList<String>();
		s_listDestLocation = new ArrayList<String>();
		
		s_listOriginLocation.add("terminal for onward transport abroad");
		s_listOriginLocation.add("international terminal for sorting");
		s_listOriginLocation.add("terminal for sorting and transport abroad");
		s_listOriginLocation.add("EORI number coplete - Item released");
		
		s_listDestLocation.add("delivery to recipient");
		s_listDestLocation.add("over to Customs");
		s_listDestLocation.add("at inward office of exchange");
		s_listDestLocation.add("to the recipient");
		s_listDestLocation.add("at the recipient");
		s_listDestLocation.add("at customs abroad for determination");
		s_listDestLocation.add("at delivery office");
		s_listDestLocation.add("in the destination country");
	}
	
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
		FBlock fblockTrack = new FBlock();
		FRecord recordTrack = null;
		String location = "";
		m_htParameter = htParameter;
		// 签收状态
		List<FRecord> listPODRecord = null;
		if (listFBlockPOD != null && listFBlockPOD.size() > 0)
			listPODRecord = listFBlockPOD.get(0).getFRecordList();		
		if (listPODRecord != null && listPODRecord.size() > 0) {
			String user = listPODRecord.get(0).getFieldValue("签收人");
			String arrivetime = listPODRecord.get(0).getFieldValue("签收日期");
			
			if (StringUtility.isNull(user) && !user.trim().equals("-")) {
				FRecord frecordPod = new FRecord();
				frecordPod.putField("签收状态", "SF");
				frecordPod.putField("签收人", user);
				frecordPod.putField("签收日期", arrivetime);
				
				FBlock fblockPod = new FBlock();
				fblockPod.addFRecord(ITrackBasic.FTR_SIGNFOR, frecordPod);
				listFBlockPOD.set(0, fblockPod);
			}
		}		
		// 轨迹信息
		for (int i = 0; i < listTrackRecord.size(); i++) {
			recordTrack = listTrackRecord.get(i);
			String date = replaceString(recordTrack.getFieldValue("日期"));			
			String description = replaceString(recordTrack.getFieldValue("详情"));
			
			if (StringUtility.isNull(date) || date.indexOf(">") < 0)
				continue;
			date = date.substring(date.indexOf(">") + 1);
			
			date = dateString(date);
			if (StringUtility.isNull(date))
				continue;
			
			if (StringUtility.isNull(description) || description.indexOf(">") < 0)
				continue;
			description = description.substring(description.indexOf(">") + 1);
			
			date  = "to_date('"+date+"','month dd, yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
			try {
				location = rebuildLocation(description);
			} catch (Exception ex) {
				continue;
			}
			if (StringUtility.isNull(location))
				continue;			
			
			description = description.replaceAll("'", "''");
			
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
    
	public static String dateString(String strDate) {
		if (StringUtility.isNull(strDate))
			return "";
		String[] astr = strDate.split(" ");
		if (astr == null || astr.length < 2)
			return "";
		if (!s_htSEMonth.containsKey(astr[1].trim()))
			return "";
		String strMonth = s_htStandardMonth.get(s_htSEMonth.get(astr[1].trim()));
		return strMonth + " " + 
		astr[0].trim() + ", " + 
		DateFormatUtility.getStandardSysdate().substring(0, 4);
		
	}
	
	public String rebuildLocation(String description) throws Exception {
		if (StringUtility.isNull(description))
			return "";
		if (description.indexOf(" - ") >= 0)
			return description;
		
		for (String str : s_listOriginLocation) {
			if (description.indexOf(str) > 0) {
				return " - SINGAPORE";
			}
		}
		String strDest = "";
		if (m_htParameter != null && m_htParameter.size() > 0 &&
				m_htParameter.containsKey(ITrackBasic.DEPOSITOR_DESTINATION))
			strDest = m_htParameter.get(ITrackBasic.DEPOSITOR_DESTINATION);
		if (StringUtility.isNull(strDest))
			return "";
		
		for (String str : s_listDestLocation) {
			if (description.indexOf(str) > 0) {
				if (m_htParameter != null && m_htParameter.size() > 0 &&
						m_htParameter.containsKey(ITrackBasic.DEPOSITOR_DESTINATION)) {
					return " - " + strDest;
				}
			}
		}
		return "";
	}
	
	public static void main(String[] args) {
		try {
			String str = dateString("11 maj");
			System.out.println("to_date('" + str + "','month dd, yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')");
			str = "HWDC Langley,Storbritannien, Arrival at inward office of exchange";
			
			SEEMSTrackDepositorSE objSSE = new SEEMSTrackDepositorSE();
			System.out.println(objSSE.rebuildLocation(str));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

}
