package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class GPQTrackDepositor extends SingleTrackDepositor {
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
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		for (FRecord fTrack : listTrackFormatRecord){
			//循环检查每一个轨迹
			System.out.println(fTrack.getFieldValue("明细"));
			String[] days=fTrack.getFieldValue("明细").substring(fTrack.getFieldValue("明细")
					.indexOf("\"x\":[{")).substring(10).replaceAll("\"","")
					.replaceAll("\\{","").replaceAll("\\},","").replace("y:[", "").replace("],", "")
					.replace("z:", "").replaceAll("\'"," ").split("a:");
			for(int i = 0; i< days.length; i++){
				String datetime=days[i].substring(0,days[i].indexOf(","));
				//System.out.println(datetime);
				datetime = "to_date('" + datetime + "','yyyy-MM-dd hh24:mi:ss')";
				String des=days[i].substring(days[i].indexOf("b:")+2);
				//System.out.println(des);
				FRecord frecord = new FRecord();
				frecord.putField("时间", datetime);
				//frecord.putField("地点","1");
				frecord.putField("详情", des);
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
		}
		}
		listFBlockTrack.set(0, currentformatBlock);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}


