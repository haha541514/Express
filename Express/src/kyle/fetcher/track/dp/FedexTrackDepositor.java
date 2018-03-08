package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class FedexTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		//����켣
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock trackBlock;
		trackBlock = listFBlockTrack.get(0);
		String arrivetime="";
		if(trackBlock!=null){
			listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		}
		FBlock currentformatBlock = new FBlock();
		for (int i = 0; i < listTrackFormatRecord.size(); i++) {
			FRecord fTrack = listTrackFormatRecord.get(i);
			String ft = fTrack.getFieldValue("����");
			String time="";
			String location = "";
			String detail="";
			int beginIndex = ft.indexOf("date\":\"")+"date\":\"".length();
			int endIndex = ft.indexOf("\",\"gmtOffset\"");
			if (beginIndex >= 0 && endIndex >= 0){
				time = ft.substring(beginIndex, endIndex);
			}
			beginIndex = ft.indexOf("\"status\":\"")+"\"status\":\"".length();
			endIndex = ft.indexOf("\",\"statusCD\"");
			if (beginIndex >= 0 && endIndex >= 0){
				detail = ft.substring(beginIndex, endIndex);
			}
			beginIndex = ft.indexOf("\"scanLocation\":\"")+"\"scanLocation\":\"".length();
			endIndex = ft.indexOf("\",\"scanDetails\"");
			if (beginIndex >= 0 && endIndex >= 0){
				location = ft.substring(beginIndex, endIndex);
			}
			
			if (StringUtility.isNull(location) && StringUtility.isNull(detail) && time.length()==10)
				continue;
			
			if (!StringUtility.isNull(time)) {
				time = time.replace("\",\"time\":\""," ").replace("\\u003a",":" ).replace("\\u002d", "-");

				time = "to_date('"
					+ time
					+ "', 'yyyy-mm-dd hh24:mi:ss', 'NLS_DATE_LANGUAGE=American')";
				
				if(detail.equals("Delivered")){
					arrivetime=time;
				}				

			} else {
				if (htParameter.containsKey(ITrackBasic.DEPOSITOR_CHECKINDATE)) {
					time = htParameter.get(ITrackBasic.DEPOSITOR_CHECKINDATE);
				} else {
					time = DateFormatUtility.getStandardSysdate();
				}
				time = time.substring(0, 10);
				time = "to_date('" + time + "', 'yyyy-mm-dd')";
			}


			FRecord frecord = new FRecord();
			frecord.putField("ʱ��", time);
			frecord.putField("�ص�", location);
			frecord.putField("����", detail);
			currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		listFBlockTrack.set(0, currentformatBlock);

		if(listFBlockPOD!=null){
			FBlock PODBlock = listFBlockPOD.get(0);

			if (PODBlock != null) {
				FRecord fPOD = PODBlock.getFRecordList("ǩ��").get(0);
				if (fPOD != null) {
					String content = fPOD.getFieldValue("�˵���Ϣ").replace("\\u003a",":" ).replace("\\u002f", "-").replace("\\u003b", ":");
					String[] info = content.split(":");
					String status="";
					if (info[0].equals("Delivered")) {
						status = "SF";
					}
					if (info.length < 4) return;

					String name = info[3];
					FRecord frecordPod = new FRecord();
					frecordPod.putField("ǩ��״̬", status);
					frecordPod.putField("ǩ����", name);
					frecordPod.putField("ǩ������", arrivetime);
					FBlock fblockPod = new FBlock();
					fblockPod.addFRecord(ITrackBasic.FTR_SIGNFOR, frecordPod);
					listFBlockPOD.set(0, fblockPod);
				}
			}
		}

	}

	public String cutString(String body, String startString, int end) {
		int startStringLength = startString.length() + 3;
		int start = body.indexOf(startString) + startStringLength;
		return body.substring(start, end);
	}

	public String toTime(String time) {
		int length = time.length();
		String m = time.substring(length - 2, length);
		String t[] = time.split(":");
		int h = Integer.parseInt(t[0]) + 12;
		if (h >= 24)
			h = Integer.parseInt(t[0]);
		
		String mi = t[1].substring(0, 2);
		String newTime = null;
		if (m.equals("AM")) {
			newTime = t[0].concat(":").concat(mi);
		}
		if (m.equals("PM")) {
			String hh = String.valueOf(h);
			newTime = hh.concat(":").concat(mi);
		}
		return newTime;
	}
	
	public String rebuildLocation(String strOriginLocation) throws Exception {
		if (StringUtility.isNull(strOriginLocation))
			return "";
		if (strOriginLocation.indexOf(" - ") >= 0)
			return strOriginLocation;
		
		int iLastBlankIndex = strOriginLocation.lastIndexOf(" ");
		String strCoutryHubcode = strOriginLocation.substring(iLastBlankIndex + 1);
		String strDtcode = DistrictDemand.getDtcodeByHubcode(strCoutryHubcode);
		if (StringUtility.isNull(strDtcode))
			return strOriginLocation;
		
		String strDtename = DistrictDemand.getDtenameByDtcode(strDtcode);
		if (StringUtility.isNull(strDtename))
			return strOriginLocation;
		
		return strOriginLocation.substring(0, iLastBlankIndex) + " - " + strDtename;
	}
	
	public static void main(String[] args) {
		try {
			FedexTrackDepositor objFTD = new FedexTrackDepositor();
			System.out.println(objFTD.rebuildLocation("GUANGZHOU CN"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
