package kyle.leis.eo.customerservice.track.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackColumns;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcsWaybillbatchtrack;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TopCorewaybill;

public class SaveBatchTrackTransaction extends AbstractTransaction {
	private WaybillbatchtrackColumns m_objWBBTC;
	private boolean m_isInsertRow = false;
	private TcsWaybillbatchtrack m_objWBBatchTrack;
	
	public void setSignforParam(String strCwCode,
			String strSignForUser,
			String strSignForDate,
			String strWbtscode) throws Exception {
		WaybillbatchtrackColumns objWBBTC = TrackDemand.loadBatchTrack(strCwCode);
		if (objWBBTC == null) {
			objWBBTC = new WaybillbatchtrackColumns();
			m_isInsertRow = true;
		}
		objWBBTC.setWbbtwbbtsignfordate(DateFormatUtility.getDBStandardDate(strSignForDate));
		objWBBTC.setWbbtwbbtsignforuser(strSignForUser);
		objWBBTC.setWbtswbtscode(strWbtscode);
		objWBBTC.setWbbtwbbtlatesttrackdesc("Sign for");
		objWBBTC.setWbbtcwcode(Long.parseLong(strCwCode));
		m_objWBBTC = objWBBTC;		
	}
	
	public void setParam(String strCwCode, 
			String strWpacode) throws Exception {
		WaybillbatchtrackColumns objWBBTC = TrackDemand.loadBatchTrack(strCwCode);
		if (objWBBTC == null) {
			objWBBTC = new WaybillbatchtrackColumns();
			m_isInsertRow = true;
		}
		objWBBTC.setWpawpacode(strWpacode);
		objWBBTC.setWbbtcwcode(Long.parseLong(strCwCode));
		m_objWBBTC = objWBBTC;
	}
	
	public void setLatestBusinessLog(String strCwCode,
			String strOperId,
			String strBusinesslog) throws Exception {
		// 客服员记录的备注需要更新最新轨迹表
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
		if (!objTdiOperator.getTdiDepartment().getDpCode().equals("CS"))
			return;
		WaybillbatchtrackColumns objWBBTC = TrackDemand.loadBatchTrack(strCwCode);
		if (objWBBTC == null) {
			objWBBTC = new WaybillbatchtrackColumns();
			m_isInsertRow = true;
		}
		objWBBTC.setWbbtwbbtlatestcslogdesc(strBusinesslog);
		objWBBTC.setWbbtwbbtcslogcreatedate(DateFormatUtility.getSysdate());
		objWBBTC.setWbbtcwcode(Long.parseLong(strCwCode));
		m_objWBBTC = objWBBTC;		
	}
	
	public void setParam(WaybillbatchtrackColumns objWBBTC, 
			boolean isInsertRow) throws Exception {
		m_isInsertRow = isInsertRow;
		m_objWBBTC = objWBBTC;
	}
	
	public TcsWaybillbatchtrack getSavedBatchTrack() {
		return m_objWBBatchTrack;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objWBBTC == null) return;
		String strCwcode = m_objWBBTC.getWbbtcwcode();
		TcsWaybillbatchtrack objWBBatchTrack;
		// 新增和修改
		if (m_isInsertRow)
			objWBBatchTrack = new TcsWaybillbatchtrack();
		else
			objWBBatchTrack = (TcsWaybillbatchtrack)objSession.load(TcsWaybillbatchtrack.class, 
					Long.parseLong(strCwcode));
		TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
				Long.parseLong(strCwcode));
		objWBBatchTrack.setTopCorewaybill(objTopCorewaybill);
		TrackDemand.setBatchtrackByColumns(objWBBatchTrack, m_objWBBTC);
		objSession.save(objWBBatchTrack);
		m_objWBBatchTrack = objWBBatchTrack;
	}
}
