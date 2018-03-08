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
		//�켣����
		
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock trackBlock;
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock
				.getFRecordList(ITrackBasic.FTR_TRACK);
		FBlock currentformatBlock = new FBlock();

		for (int i = 0; i < listTrackFormatRecord.size(); i++) {
			FRecord fTrack = listTrackFormatRecord.get(i);
			String time = fTrack.getFieldValue("ʱ��");
			String location = fTrack.getFieldValue("�ص�");
			String detail = fTrack.getFieldValue("����");
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
			frecord.putField("ʱ��", time);
			frecord.putField("�ص�", location);
			frecord.putField("����", detail);
			currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		listFBlockTrack.set(0, currentformatBlock);
		
		//POD����
		if (listFBlockPOD != null) {
			FBlock PODBlock = listFBlockPOD.get(0);
			if (PODBlock != null) {
				if (PODBlock.getFRecordList("״̬") == null || 
						PODBlock.getFRecordList("״̬").size() < 1 || 
						PODBlock.getFRecordList("ǩ����") == null || 
						PODBlock.getFRecordList("ǩ����").size() < 1)
					return;
				FRecord fStatus = PODBlock.getFRecordList("״̬").get(0);
				FRecord fUser = PODBlock.getFRecordList("ǩ����").get(0);

				String status = fStatus
						.getFieldValue(ITrackBasic.FTF_SIGN_STATUS);
				String person = fUser
						.getFieldValue(ITrackBasic.FTF_SIGNFOR_PERSON);

				if (status.contains("Delivered")) {
					status = "SF";
					FBlock fblockPod = new FBlock();
					FRecord frecord = new FRecord();
					frecord.putField("ǩ��״̬", status);
					frecord.putField("ǩ����", person);
					frecord.putField("ǩ������", PODDate);
					fblockPod.addFRecord("ǩ��", frecord);
					listFBlockPOD.set(0, fblockPod);
				}
			}
		}
	}
}
