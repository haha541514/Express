package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class SGEMSTrackDepositor extends SingleTrackDepositor {

	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
		FBlock fblockTrack = new FBlock();
		FRecord recordTrack = null;
		String location = "";
		
		if (listTrackRecord == null || listTrackRecord.size() < 1) {
			listFBlockTrack.set(0, fblockTrack);
			return;
		}
		
		for (int i = 0; i < listTrackRecord.size(); i++) {
			recordTrack = listTrackRecord.get(i);
			String date = replaceString(recordTrack.getFieldValue("日期"));			
			String description = replaceString(recordTrack.getFieldValue("详情"));
			
			date  = "to_date('"+date+"','dd-mm-yyyy')";
			
			try {
				location = rebuildLocation(description);
			} catch (Exception ex) {
				
			}
			
			if (description.indexOf("Country code") > 0) {
				description = description.substring(0, description.indexOf("Country code") - 2);
			}
			
			FRecord frecordTrack = new FRecord();
			frecordTrack.putField("详情", description);
			frecordTrack.putField("地点", location);
			frecordTrack.putField("时间", date);
			fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
			//System.out.println("date:" + date);
			//System.out.println("location:" + location);
			//System.out.println("description:" + status);
			//System.out.println("features:" + features);
			//System.out.println("===============");
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
	
	public String rebuildLocation(String description) throws Exception {
		if (StringUtility.isNull(description))
			return "";
		if (description.indexOf(" - ") >= 0)
			return description;
		
		String strLocation = "";
		if (description.indexOf("Country code:") > 0) {
			strLocation = description.substring(description.indexOf("Country code:") + "Country code:".length());
			strLocation = strLocation.substring(0, strLocation.length() - 1);
			if (!StringUtility.isNull(strLocation)) {
				strLocation = strLocation.trim();
				strLocation = DistrictDemand.getDtenameByHubcode(strLocation);
				return " - " + strLocation;
			}
		}
		return strLocation;
	}	
	
	public static void main(String[] args) {
		try {
			SGEMSTrackDepositor objFTD = new SGEMSTrackDepositor();
			System.out.println(objFTD.rebuildLocation("Despatched  to overseas (Country code: AU)"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	
	

}
