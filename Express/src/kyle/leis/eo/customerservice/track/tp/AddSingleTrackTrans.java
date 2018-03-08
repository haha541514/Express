package kyle.leis.eo.customerservice.track.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.da.WaybilltrackCondition;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;

public class AddSingleTrackTrans extends AbstractTransaction {
	private WaybilltrackColumns m_objWBTColumns;
	private DeleteWBTrackTrans m_objDeleteWBTrackTrans;
	private String m_strOperId;
	
	public void setParam(String strCwcode, 
			String strDtcode, 
			String strWbtscode,
			String strOperId,
			String strOccurDate) throws Exception {
		setParam(strCwcode, strDtcode, 
				strWbtscode, strOperId, 
				"", strOccurDate);
	}
	
	
	public void setParam(String strCwcode, 
			String strDtcode, 
			String strWbtscode,
			String strOperId,
			String strWbtdescription,
			String strOccurDate) throws Exception {
		m_objWBTColumns = new WaybilltrackColumns();
		m_objWBTColumns.setDtdtcode(strDtcode);
		m_objWBTColumns.setWbbtcwcode(Long.parseLong(strCwcode));
		m_objWBTColumns.setWbtswbtscode(strWbtscode);
		m_objWBTColumns.setWbtwbtoccurdate(DateFormatUtility.getStandardDate(strOccurDate));
		m_objWBTColumns.setWbtwbtdescription(strWbtdescription);
		m_objWBTColumns.setWbtwbtopensign("Y");
		m_strOperId = strOperId;
		// 删除原轨迹
		WaybilltrackCondition objWBTCondition = new WaybilltrackCondition();
		objWBTCondition.setCwcode(strCwcode);
		objWBTCondition.setDtcode(strDtcode);
		if (!StringUtility.isNull(strWbtscode))
			objWBTCondition.setWbtscode(strWbtscode);
		else
			objWBTCondition.setWbtdescription(strWbtdescription);
		
		List objList = TrackDemand.queryTrack(objWBTCondition);
		if (objList != null && objList.size() > 0) {
			m_objDeleteWBTrackTrans = new DeleteWBTrackTrans();
			String[] astrWbtid = new String[objList.size()];
			for (int i = 0; i < objList.size(); i++) {
				WaybilltrackColumns objWBTColumns = (WaybilltrackColumns)objList.get(i);
				astrWbtid[i] = objWBTColumns.getWbtwbtid();
			}
			m_objDeleteWBTrackTrans.setParam(astrWbtid, strCwcode);
		}
	}
	
	public void transaction(Session objSession) throws Exception {
		// 先删除
		if (m_objDeleteWBTrackTrans != null)
			m_objDeleteWBTrackTrans.transaction(objSession);
		// 新增
		SaveWBTrackTransaction objSWBTrackTrans = new SaveWBTrackTransaction();
		objSWBTrackTrans.setParam(m_objWBTColumns, m_strOperId, false);
		objSWBTrackTrans.transaction(objSession);
	}

}
