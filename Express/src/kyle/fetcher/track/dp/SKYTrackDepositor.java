package kyle.fetcher.track.dp;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import kyle.common.util.textformat.bl.FBlock;
import kyle.common.util.textformat.bl.FRecord;
import kyle.fetcher.track.dax.ITrackBasic;

public class SKYTrackDepositor extends SingleTrackDepositor{
	public void preDealPODTrack(List<FBlock> listFBlockPOD,
			List<FBlock> listFBlockTrack, 
			Hashtable<String, String> htParameter) {
		try {
		FBlock trackBlock;  
		// ��ʽ���飬  ��POD��͹켣����֡�
		// ��ʽ����¼����POD��¼�͹켣��¼���֡� ��¼�����ֶ�
		// �����POD��¼�͹켣��¼��
		List<FRecord> listPODRecord = null;
		if (listFBlockPOD != null && listFBlockPOD.size() > 0)
			listPODRecord = listFBlockPOD.get(0).getFRecordList();
		if (listPODRecord != null && listPODRecord.size() > 0) {
			String del = listPODRecord.get(0).getFieldValue("����");
			System.out.println(del);
			if(del.indexOf("<img id='img5' src='https://ws01.ffdx.net/v4/files/images_effect_popup/icn_save.jpg' height='28px' style='text-align:left;'/></td><td class='Size9Bold' style='color:green;text-align:left;padding-left:5px;'>")!=-1){
				
			String date=del.substring(del.indexOf("<strong>")+8,del.indexOf("</strong>"));
			String user=del.substring(del.lastIndexOf("<strong>")+8,del.lastIndexOf("</strong>"));
				FRecord frecordPod = new FRecord();
				frecordPod.putField("ǩ��״̬", "SF");
				frecordPod.putField("ǩ����", user.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecordPod.putField("ǩ������",date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				FBlock fblockPod = new FBlock();
				fblockPod.addFRecord(ITrackBasic.FTR_SIGNFOR, frecordPod);
				listFBlockPOD.set(0, fblockPod);
			}
		}
		
	    List<FRecord> listTrackFormatRecord = new ArrayList<FRecord>();
		FBlock currentformatBlock = new FBlock();
		if (listFBlockTrack == null || listFBlockTrack.size() < 1) return;
		
		trackBlock = listFBlockTrack.get(0);
		listTrackFormatRecord = trackBlock.getFRecordList(ITrackBasic.FTR_TRACK);
		for (FRecord fTrack : listTrackFormatRecord){
			//ѭ�����ÿһ���켣
			String strContent=fTrack.getFieldValue("��ϸ").substring(fTrack.getFieldValue("��ϸ").indexOf("<th>Activity</th>")+26,fTrack.getFieldValue("��ϸ").indexOf("</table>"));
			if(!"<td colspan=\'4\'>No tracking information exists for this consignment. Please contact our customer service department for further details.</td></tr>".equals(strContent)){
				
			
			String[] recodes=strContent.split("<tr>");
			for(int i=0;i<recodes.length;i++){
				String date=recodes[i].substring(0,recodes[i].indexOf("<td class=")).replaceAll("<td>", "").replaceAll("</td>", " ");
				date=date.substring(0,date.length()-1);
				String location=recodes[i].substring(recodes[i].indexOf("<td class="));
				location=location.substring(location.indexOf(">")+1,location.indexOf("</td>"));
				String des=recodes[i].substring(recodes[i].lastIndexOf("<td>")+4,recodes[i].lastIndexOf("</td>"));
				if(des.indexOf(">")!=-1)
				  des=des.substring(des.lastIndexOf(">")+1);
				date ="to_date('"+ date+"', 'dd mon yy hh24:mi', 'NLS_DATE_LANGUAGE=American')";
				FRecord frecord = new FRecord();
				frecord.putField("ʱ��", date.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("�ص�",location.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				frecord.putField("����", des.replaceAll("\\\\r", "").replaceAll("\\\\n", ""));
				currentformatBlock.addFRecord(ITrackBasic.FTR_TRACK, frecord);
			}
			}
			
		}
			listFBlockTrack.set(0, currentformatBlock);
	} catch (Exception e) {
		e.printStackTrace();
	}
 }
}
