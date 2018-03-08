package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ModifyHawbWaybillcodeColumns;
import net.sf.hibernate.Session;

public class ModifyHawbWaybillcodeTrans extends AbstractTransaction {

	private List m_listMHWCColumns;
	private String m_strOperId;
	
	public void setParam(List listMHWCColumns, String strOperId) {
		m_listMHWCColumns = listMHWCColumns;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if (m_listMHWCColumns == null || m_listMHWCColumns.size() < 1)
			return;
		for (int i = 0; i < m_listMHWCColumns.size(); i++) {
			ModifyHawbWaybillcodeColumns objMHWCColumns = (ModifyHawbWaybillcodeColumns)m_listMHWCColumns.get(i);
			if (StringUtility.isNull(objMHWCColumns.getOldHawbwaybillcode()) ||
					StringUtility.isNull(objMHWCColumns.getHawbwaybillcode()))
				continue;
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.cw_ewbcode = '" +
			objMHWCColumns.getHawbwaybillcode() + 
			"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_ewbcode = '" + objMHWCColumns.getOldHawbwaybillcode() + "'";
			execute(objSession, strUpdateSql);			
		}
	}

}
