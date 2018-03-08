package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class DHLTrackDepositor extends SingleTrackDepositor {
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		try {
		FBlock trackBlock;  
		// 格式化块，  有POD块和轨迹块二种。
		// 格式化记录，有POD记录和轨迹记录二种。 记录中有字段
		// 具体的POD记录和轨迹记录。
		
		List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock currentformatBlock = new FBlock();
		FBlock PODBlock = listFBlockPOD.get(0);
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		for (FRecord fTrack : listTrackFormatRecord){
			//System.out.println(listTrackFormatRecord.get(0).getFieldValue("明细")+"++++++++++++++++++++++++++++");
			//循环检查每一个轨迹
			String tracks=fTrack.getFieldValue("明细").substring(fTrack.getFieldValue("明细").indexOf("checkpoints")).replaceAll("\"", "");
			String[]track=tracks.split("counter");
			for(int i=0;i<track.length-1;i++){
				String des=track[i+1].substring(track[i+1].indexOf("description")+11,track[i+1].indexOf("time")).replaceAll(":", "").replaceAll(",", "").trim();
				String date=track[i+1].substring(track[i+1].indexOf("time")+4,track[i+1].indexOf("location")).replaceFirst(":","").replaceAll(",", "").trim();
				String time=date.substring(0,5);
				date=date.substring(date.indexOf("day")+3).trim()+","+time;
				String loaction=track[i+1].substring(track[i+1].indexOf("location")+8,track[i+1].indexOf("}")).replaceFirst(":", "").replaceAll("  ", "").trim();
				if(loaction.indexOf("totalPieces")!=-1){
					loaction=loaction.substring(0,loaction.lastIndexOf("totalPieces")).trim();
					loaction=loaction.substring(0,loaction.lastIndexOf(",")).trim();
				}
				if(des.indexOf(loaction)!=-1){
					des=des.replaceAll(loaction, "");
				}
				//System.out.println(loaction);
				date ="to_date('"+ date+"', 'month dd yyyy,hh24:mi', 'NLS_DATE_LANGUAGE=American')";
				if(des.indexOf("Delivered")!=-1){
					String person=des.substring(des.indexOf("Signed for by")+13).trim();
					des="Delivered";
					FRecord frecord1 = new FRecord();
					frecord1.putField("签收人", person);
					frecord1.putField("签收日期", date);
					frecord1.putField("签收状态", "SF");
					FBlock fblockPod = new FBlock();
					fblockPod.addFRecord(ITrackBasic.FTR_SIGNFOR, frecord1);
					listFBlockPOD.set(0,fblockPod);
				}
				FRecord frecord = new FRecord();
				frecord.putField("时间", date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("地点",loaction.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("详情", des.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
			}
		}
		
		listFBlockTrack.set(0, currentformatBlock);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}
