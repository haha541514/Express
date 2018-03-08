package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class UPSTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, Hashtable<String, String> htParameter) {
		String arriveDate = null;
		if (listFBlockTrack == null || listFBlockTrack.size() < 1)
			return;
		List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
		FBlock fblockTrack = new FBlock();
		for (int i = 1; i < listTrackRecord.size(); i++) {
			FRecord recordTrack = listTrackRecord.get(i);
			String location = replaceBlank(recordTrack.getFieldValue("地点"));
			String date = recordTrack.getFieldValue("日期");
			String time = recordTrack.getFieldValue("时间");
			String description = recordTrack.getFieldValue("详情");
			String datetime = date.concat(" ").concat(time);
			
			description = cutUPSChar(description);
			datetime = dateString(datetime);
			datetime = "to_date('" + datetime + "','mm/dd/yyyy hh24:mi')";
			if (i == 1) {
				arriveDate = datetime;
			}
			try {
				location = rebuildLocation(location);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			
			FRecord frecordTrack = new FRecord();
			frecordTrack.putField("地点", location);
			frecordTrack.putField("时间", datetime);
			frecordTrack.putField("详情", description);
			fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
		}
		listFBlockTrack.set(0, fblockTrack);

		List<FRecord> listPODRecord = listFBlockPOD.get(0).getFRecordList();
		if (listPODRecord != null && listPODRecord.size() > 0) {
			FRecord recordPOD = listPODRecord.get(0);
			String status = null;
			String name = cutString(recordPOD.getFieldValue("签收人"));
			if (name != null && !"".equals(name)) {
				status = "SF";
			}
			FRecord frecordPOD = new FRecord();
			if (!StringUtility.isNull(name)) {
				frecordPOD.putField("签收人", name);
				frecordPOD.putField("签收日期", arriveDate);
				frecordPOD.putField("签收状态", status);
				FBlock fblockPOD = new FBlock();
				fblockPOD.addFRecord(ITrackBasic.FTR_SIGNFOR, frecordPOD);
				listFBlockPOD.set(0, fblockPOD);
			}
		}
	}

	public String cutString(String str) {
		if (StringUtility.isNull(str))
			return "";
		int strLength = str.length();
		str = str.substring(4, strLength);
		return str;
	}
	
	private String cutUPSChar(String detail) {
		if(detail.indexOf(": Ready for UPS") > -1) {
			detail = detail.substring(0, detail.indexOf(": Ready for UPS"));
		}
		if (detail.indexOf("'") > -1)
			detail = detail.replaceAll("'", "''");
		return detail;
	}	
	
	public static String dateString(String strDate){		
		String date[] = strDate.split(":");
		if(date.length == 1){
			return strDate;
		}else{			
			String str = strDate.substring(strDate.length() - 4,strDate.length());			
			if(str.equals("P.M.")){
				int h = Integer.parseInt(date[0].substring(date[0].length()-2,date[0].length()).trim());
				h = h + 12;
				if (h < 24) {
					date[0] = date[0].substring(0,date[0].length()-2);
					if (!date[0].substring(date[0].length()-1).equals(" "))
						date[0] = date[0] + " ";
					strDate = date[0].concat(String.valueOf(h).concat(":").concat(date[1]));
				}
			}
			strDate = strDate.substring(0,strDate.length() - 4);
			return strDate;
		}
		
	}	
	
	public String replaceBlank(String str) {
		Pattern p = Pattern.compile("\\s*|\t|\r|\n");
		Matcher m = p.matcher(str);
		String after = m.replaceAll("");
		return after;
	}
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		if (StringUtility.isNull(strOriginLocation))
			return "";
		
		if (strOriginLocation.indexOf(" - ") >= 0)
			return strOriginLocation;		
		
		if (strOriginLocation.indexOf(",") > 0)
			return strOriginLocation.replaceAll(",", " - ");
		
		return strOriginLocation;
	}	
	
	
	public static void main(String[] args) {
		String str = "Botany,Australia";
		try {
			UPSTrackDepositor objUPSTD = new UPSTrackDepositor();
			System.out.println(objUPSTD.rebuildLocation(str));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
