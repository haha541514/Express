package kyle.leis.eo.customerservice.track.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.customerservice.track.da.WaybillbatchtrackCondition;
import kyle.leis.eo.customerservice.track.dax.TrackDemand;

public class ModifyLatestFetchDataTrans extends AbstractTransaction {

	private String m_strCwcode;
	private String m_strRemark;
	
	public void setParam(String strCwcode,
			String strRemark) {
		m_strCwcode = strCwcode;
		if (strRemark.length() <= 250)
			m_strRemark = strRemark;
		else
			m_strRemark = strRemark.substring(0, 250);
	}
	
	
	public void transaction(Session objSession) throws Exception {
		WaybillbatchtrackCondition objWBTCondition = new WaybillbatchtrackCondition();
		objWBTCondition.setCwcode(m_strCwcode);
		List listBatchTrack = TrackDemand.queryBatchTrack(objWBTCondition);
		String strSQL = "";
		if (listBatchTrack == null || listBatchTrack.size() < 1) {
			// ÐÂÔö
			strSQL = "insert into t_cs_waybillbatchtrack(CW_CODE, WBBT_LATESTTRACKFETCHDATE, WBBT_LATESTTRACKFETCHTHREAD)" +
			                "       values(" + m_strCwcode + ", sysdate, '" + m_strRemark + "')";
		} else {
			// ÐÞ¸Ä
			strSQL = "update t_cs_waybillbatchtrack set WBBT_LATESTTRACKFETCHDATE = sysdate, " +
			         "WBBT_LATESTTRACKFETCHTHREAD = '" + m_strRemark + "'" +
            "          where cw_code = " + m_strCwcode;		
		}
		execute(objSession, strSQL);
	}

}
