package kyle.leis.eo.operation.transportwaybill.tp;

import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.customerservice.track.da.WaybilltrackColumns;
import kyle.leis.eo.customerservice.track.tp.SaveWBTrackTransaction;
import kyle.leis.eo.operation.transportwaybill.da.TransportcorewaybillColumns;
import kyle.leis.eo.operation.transportwaybill.da.TransportwaybilltraceColumns;
import kyle.leis.eo.operation.transportwaybill.dax.TransportWaybillDemand;

public class AddWaybillTrackTrans extends AbstractTransaction {
	private List<TransportwaybilltraceColumns> m_listTWBTColumns;
	private List m_listCorewaybill;
	private String m_strOperId;
	private String m_strTwbid;
	private boolean m_isDelAllTWBTrack;
	
	public void setParam(String strTwbid, 
			TransportwaybilltraceColumns objTWBTColumns,
			String strOperId) throws Exception {
		if (objTWBTColumns == null) return;
		List<TransportwaybilltraceColumns> listTWBTColumns = new ArrayList<TransportwaybilltraceColumns>();
		this.setParam(strTwbid, 
				listTWBTColumns, 
				strOperId,
				false);
	}
	
	public void setParam(String strTwbid, 
			List<TransportwaybilltraceColumns> listTWBTColumns,
			String strOperId,
			boolean isDelAllTWBTrack) throws Exception {
		m_strTwbid = strTwbid;
		m_listTWBTColumns = listTWBTColumns;
		m_strOperId = strOperId;
		m_isDelAllTWBTrack = isDelAllTWBTrack;
		// 查询运单
		m_listCorewaybill = TransportWaybillDemand.loadCorewaybill(strTwbid);
	}
	
	public void transaction(Session objSession) throws Exception {
		// 删除所有轨迹
		if (m_isDelAllTWBTrack) {
			execute(objSession, "delete from t_cs_waybilltrack wbt " +
					"where wbt.wbt_from = 'TWB' and wbt.cw_code in (select cw.cw_code " + 
					"from t_op_corewaybill cw," + 
					"t_op_transportwaybillvalue twbv " +
					"where cw.bw_code_departure = twbv.bw_code" +
					"  and twbv.twb_id = " + m_strTwbid + ")");
		}
		// 新增轨迹
		if (m_listCorewaybill == null || m_listCorewaybill.size() < 1)
			return;
		for (int i = 0; i < m_listCorewaybill.size(); i++) {
			TransportcorewaybillColumns objTCWBColumns = (TransportcorewaybillColumns)m_listCorewaybill.get(i);
			List<WaybilltrackColumns> listWBTColumns = TransportWaybillDemand.buildWaybillTrack(m_listTWBTColumns, 
					objTCWBColumns.getCwcw_code());
			SaveWBTrackTransaction objSWBTTrans = new SaveWBTrackTransaction();
			objSWBTTrans.setParam(listWBTColumns, 
					m_strOperId, 
					false);
			objSWBTTrans.transaction(objSession);
		}
	}
}
