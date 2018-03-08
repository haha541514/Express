package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ModifyServerWaybillcodeColumns;

public class ModifyServerWaybillcodeTrans extends AbstractTransaction {

	private List m_listMSWCColumns;
	private String m_strOperId;
	
	public void setParam(List listMSWCColumns, String strOperId) {
		m_listMSWCColumns = listMSWCColumns;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if (m_listMSWCColumns == null || m_listMSWCColumns.size() < 1)
			return;
		for (int i = 0; i < m_listMSWCColumns.size(); i++) {
			ModifyServerWaybillcodeColumns objMSWCColumns = (ModifyServerWaybillcodeColumns)m_listMSWCColumns.get(i);
			if (StringUtility.isNull(objMSWCColumns.getServerwaybillcode()) ||
					StringUtility.isNull(objMSWCColumns.getOldServerwaybillcode()))
				continue;			
			
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.cw_serverewbcode = '" +
			objMSWCColumns.getServerwaybillcode() + 
			"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_serverewbcode = '" + objMSWCColumns.getOldServerwaybillcode() + "'";
			execute(objSession, strUpdateSql);			
		}
	}
}
