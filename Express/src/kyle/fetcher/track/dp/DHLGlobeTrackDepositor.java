package kyle.fetcher.track.dp;

import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class DHLGlobeTrackDepositor extends SingleTrackDepositor {

	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		
		if (listFBlockTrack != null && listFBlockTrack.size() > 0) {
			List<FRecord> listTrackRecord = listFBlockTrack.get(0).getFRecordList();
			FBlock fblockTrack = new FBlock();
			FRecord recordTrack = null;
			for (int i = 0; i < listTrackRecord.size(); i++) {
				recordTrack = listTrackRecord.get(i);
				String status = replaceString(recordTrack.getFieldValue("详情"));
				String date = "";
				if (status.indexOf(" am ") > 0)
					date = status.substring(status.indexOf(" am ") + 4, status.indexOf(" am ") + 14);
				if (status.indexOf(" pm ") > 0)
					date = status.substring(status.indexOf(" pm ") + 4, status.indexOf(" pm ") + 14);
				
				if (StringUtility.isNull(date))
					continue;
				
				date = date + " 00:00:00";
				date  = "to_date('"+date+"','dd.mm.yyyy, hh24:mi:ss','NLS_DATE_LANGUAGE=American')";
				
				FRecord frecordTrack = new FRecord();
				frecordTrack.putField("详情", status);
				frecordTrack.putField("时间", date);
				fblockTrack.addFRecord(ITrackBasic.FTR_TRACK, frecordTrack);
			}
			listFBlockTrack.set(0, fblockTrack);
		}
	}

	public String replaceString(String str) {
		str = str.replaceAll("class=\"bold\"", "");
		str = str.replaceAll(">", "");
		str = str.replaceAll("<[^>]*>", ""); 		
		str = str.replaceAll("\t|\r|\n","");
		str = str.trim();
		str = str.replace("&nbsp;", " ");
		str = str.replace("&#153;", "");
		return str;
	}
	
	public static void main(String[] args) {
		try {
			String status = "Die Sendung wurde pm 05.10.2013 zugestellt";
			String date = "";
			if (status.indexOf(" am ") > 0)
				date = status.substring(status.indexOf(" am ") + 4, status.indexOf(" am ") + 14);
			if (status.indexOf(" pm ") > 0)
				date = status.substring(status.indexOf(" pm ") + 4, status.indexOf(" pm ") + 14);
			
			System.out.println(date);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}	

}
