package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ModifyCustomerWaybillcodeColumns;

public class ModifyCustomerWaybillcodeTrans extends AbstractTransaction {

	private List m_listMCWCColumns;
	private String m_strOperId;
	
	public void setParam(List listMCWCColumns, String strOperId) {
		m_listMCWCColumns = listMCWCColumns;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if (m_listMCWCColumns == null || m_listMCWCColumns.size() < 1)
			return;
		for (int i = 0; i < m_listMCWCColumns.size(); i++) {
			ModifyCustomerWaybillcodeColumns objMCWCColumns = (ModifyCustomerWaybillcodeColumns)m_listMCWCColumns.get(i);
			if (StringUtility.isNull(objMCWCColumns.getOldCustomerwaybillcode()) ||
					StringUtility.isNull(objMCWCColumns.getCustomerwaybillcode()))
				continue;
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.cw_customerewbcode = '" +
			objMCWCColumns.getCustomerwaybillcode() + 
			"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_customerewbcode = '" + objMCWCColumns.getOldCustomerwaybillcode() + "'";
			execute(objSession, strUpdateSql);			
		}
	}
}
