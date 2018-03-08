package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class GLSTrackDepositor extends SingleTrackDepositor{
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		String PODDate="";
		//轨迹处理
		
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock trackBlock;
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock
				.getFRecordList(ITrackBasic.FTR_TRACK);
		FBlock currentformatBlock = new FBlock();

		for (int i = 0; i < listTrackFormatRecord.size(); i++) {
			FRecord fTrack = listTrackFormatRecord.get(i);
			String time = fTrack.getFieldValue("时间");
			String location = fTrack.getFieldValue("地点");
			String detail = fTrack.getFieldValue("详情");
			if (!StringUtility.isNull(time)) {
				time = time.replace("\",\"time\":\"", "");
				time = "to_date('" + time + "','yyyy-mm-dd hh24:mi:ss')";
				if (i == 0 & detail.contains("Delivered")) {
					PODDate = time;
				}
			}

			String city = location.substring(location.indexOf("\"city\":\"")
					+ "\"city\":\"".length(), location.indexOf("\",\""));
			String country = location.substring(location
					.indexOf("\"countryName\":\"")
					+ "\"countryName\":\"".length(), location.length() - 1);
			location = country + "  " + city;

			FRecord frecord = new FRecord();
			frecord.putField("时间", time);
			frecord.putField("地点", location);
			frecord.putField("详情", detail);
			currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		listFBlockTrack.set(0, currentformatBlock);
		
		//POD处理
		if (listFBlockPOD != null) {
			FBlock PODBlock = listFBlockPOD.get(0);
			if (PODBlock != null) {
				if (PODBlock.getFRecordList("状态") == null || 
						PODBlock.getFRecordList("状态").size() < 1 || 
						PODBlock.getFRecordList("签收人") == null || 
						PODBlock.getFRecordList("签收人").size() < 1)
					return;
				FRecord fStatus = PODBlock.getFRecordList("状态").get(0);
				FRecord fUser = PODBlock.getFRecordList("签收人").get(0);

				String status = fStatus
						.getFieldValue(ITrackBasic.FTF_SIGN_STATUS);
				String person = fUser
						.getFieldValue(ITrackBasic.FTF_SIGNFOR_PERSON);

				if (status.contains("Delivered")) {
					status = "SF";
					FBlock fblockPod = new FBlock();
					FRecord frecord = new FRecord();
					frecord.putField("签收状态", status);
					frecord.putField("签收人", person);
					frecord.putField("签收日期", PODDate);
					fblockPod.addFRecord("签收", frecord);
					listFBlockPOD.set(0, fblockPod);
				}
			}
		}
	}
}
