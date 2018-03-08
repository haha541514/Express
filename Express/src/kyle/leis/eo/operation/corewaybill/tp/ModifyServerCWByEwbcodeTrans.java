package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybill.dax.ModifyServerChargeweightColumns;
import net.sf.hibernate.Session;

public class ModifyServerCWByEwbcodeTrans extends AbstractTransaction {
	private String m_strOperId;
	private List m_listServerchargeweightColumns;
	
	public void setParam(List listServerchargeweightColumns,
			String strOperID) {
		m_listServerchargeweightColumns = listServerchargeweightColumns;
		m_strOperId = strOperID;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listServerchargeweightColumns == null || m_listServerchargeweightColumns.size() < 1)
			return;
		for (int i = 0; i < m_listServerchargeweightColumns.size(); i++) {
			ModifyServerChargeweightColumns  objMSCWColumns = (ModifyServerChargeweightColumns)m_listServerchargeweightColumns.get(i);	
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.CW_SERVERCHARGEWEIGHT = " + objMSCWColumns.getServerchargeweight() + 
			", cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_serverewbcode = '" + objMSCWColumns.getServerwaybillcode() + "'";
			execute(objSession, strUpdateSql);
		}
	}
}
