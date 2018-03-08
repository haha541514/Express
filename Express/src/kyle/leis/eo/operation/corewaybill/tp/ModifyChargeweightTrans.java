package kyle.leis.eo.operation.corewaybill.tp;

import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.dax.ModifyChargeweightColumns;
import net.sf.hibernate.Session;

public class ModifyChargeweightTrans extends AbstractTransaction {
	private List m_listMWCColumns;
	private String m_strCwcode;
	private String m_strOperId;
	private String m_strChargeweight;
	
	public void setParam(List listMWCColumns, 
			String strOperId) {
		m_listMWCColumns = listMWCColumns;
		m_strOperId = strOperId;
	}
	
	public void setParam(String strCwcode, 
			String strChargeweight,
			String strOperId) throws Exception {
		m_strCwcode = strCwcode;
		m_strOperId = strOperId;
		m_strChargeweight = strChargeweight;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (!StringUtility.isNull(m_strCwcode)) {
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET " + 
			"cw.cw_chargeweight = " + m_strChargeweight + 
			", cw.cw_grossweight = " + m_strChargeweight + 
			", cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.CW_CODE = " + m_strCwcode;
			execute(objSession, strUpdateSql);
		} else {
			if (m_listMWCColumns != null && m_listMWCColumns.size() > 0)
				for (int i = 0; i < m_listMWCColumns.size(); i++) {
					ModifyChargeweightColumns objMCWColumns = (ModifyChargeweightColumns)m_listMWCColumns.get(i);
					String strUpdateSql = "update T_OP_COREWAYBILL cw SET " + 
					"cw.cw_chargeweight = " + objMCWColumns.getChargeweight() + 
					", cw.cw_grossweight = " + objMCWColumns.getChargeweight() + 
					", cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
					", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_customerewbcode = '" + objMCWColumns.getCustomerwaybillcode() + "'";
					execute(objSession, strUpdateSql);
				}
		}
	}
}
