package kyle.leis.eo.operation.corewaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class ModifyServerchargeweightTrans extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strOperId;
	private String m_strServerChargeweight;
	
	public void setParam(String strCwcode, 
			String strServerChargeweight,
			String strOperId) throws Exception {
		m_strCwcode = strCwcode;
		m_strOperId = strOperId;
		m_strServerChargeweight = strServerChargeweight;
	}
	
	public void transaction(Session objSession) throws Exception {
		String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.CW_SERVERCHARGEWEIGHT = " +
		m_strServerChargeweight + 
		", cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
		", cw.CW_MODIFYDATE = SYSDATE WHERE cw.CW_CODE = " + m_strCwcode;
		execute(objSession, strUpdateSql);
	}

}
