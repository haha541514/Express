package kyle.leis.eo.customerservice.track.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.customerservice.track.bl.Track;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackCondition;
import kyle.leis.eo.customerservice.track.da.WaybillfortrackclientCondition;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;

public class TrackService extends AService {
	/**
	 * 查询运单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryWaybillfortrack(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		WaybillfortrackCondition objWBFTCondition = (WaybillfortrackCondition)objPD.getParameter(0, 
				WaybillfortrackCondition.class);
		List objList = TrackDemand.queryWaybillfortrack(objWBFTCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryWaybillfortrackclient(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		WaybillfortrackclientCondition objWBFTCondition = (WaybillfortrackclientCondition)objPD.getParameter(0, 
				WaybillfortrackclientCondition.class);
		List objList = TrackDemand.queryWaybillfortrackclient(objWBFTCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	
	/**
	 * 装载轨迹信息
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String loadTrack(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		List objList = TrackDemand.loadTracks(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 删除
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String[] astrWbtid = objPD.getParameterArray(0, String.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		Track objTrack = new Track();
		objTrack.delete(astrWbtid, strCwcode);
		
		return "";
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		WaybilltrackColumns objWBTColumns = (WaybilltrackColumns)objPD.getParameter(0, 
				WaybilltrackColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		Track objTrack = new Track();
		List objList = objTrack.Save(objWBTColumns, strOperId, false);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String addSingleTrack(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 6, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class); 
		String strDtcode = (String)objPD.getParameter(1, String.class); 
		String strWbtscode = (String)objPD.getParameter(2, String.class);
		String strWbtdescription = (String)objPD.getParameter(3, String.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		String strOccurDate = (String)objPD.getParameter(5, String.class);		
		
		Track objTrack = new Track();
		objTrack.addSingleTrack(strCwcode, strDtcode, 
				strWbtscode, strWbtdescription,
				strOperId, strOccurDate);
		
		return "";
	}	
	
	public String checkPoint(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 5, this);
		
		String strScanNo = (String)objPD.getParameter(0, String.class); 
		String strType = (String)objPD.getParameter(1, String.class); 
		String strOperId = (String)objPD.getParameter(2, String.class);
		String strWbtscode = (String)objPD.getParameter(3, String.class);	
		String strDtcode = (String)objPD.getParameter(4, String.class);	
		
		Track objTrack = new Track();
		objTrack.checkPoint(strScanNo, strType, 
				strOperId, strWbtscode, strDtcode);
		
		return "";
	}
}
