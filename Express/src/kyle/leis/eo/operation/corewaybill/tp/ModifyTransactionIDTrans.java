package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybill.dax.ModifyServerWaybillcodeColumns;
import net.sf.hibernate.Session;

public class ModifyTransactionIDTrans extends AbstractTransaction {
	private List m_listMSWCColumns;
	
	public void setParam(List listMSWCColumns) {
		m_listMSWCColumns = listMSWCColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listMSWCColumns == null || m_listMSWCColumns.size() < 1)
			return;
		for (int i = 0; i < m_listMSWCColumns.size(); i++) {
			ModifyServerWaybillcodeColumns objMSWCColumns = (ModifyServerWaybillcodeColumns)m_listMSWCColumns.get(i);
			String strUpdateSql = "update T_OP_HOUSEWAYBILL hw SET hw.HW_TransactionID = '" +
			objMSWCColumns.getServerwaybillcode() + "'" + 
			"WHERE hw.cw_code = (select cw_code from T_OP_COREWAYBILL where cw_serverewbcode = '" + 
			objMSWCColumns.getOldServerwaybillcode() + "' and rownum < 2)";
			execute(objSession, strUpdateSql);			
		}
	}
}
