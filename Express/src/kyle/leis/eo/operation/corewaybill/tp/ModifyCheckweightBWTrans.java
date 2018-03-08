package kyle.leis.eo.operation.corewaybill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import net.sf.hibernate.Session;

public class ModifyCheckweightBWTrans extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strOperId;
	private String m_strBwcodeArrival;
	private String m_strCheckweightBWCode;
	
	public void setParam(String strCwcode, 
			String strCheckweightBWCode,
			String strBwcodeArrival,
			String strOperId) throws Exception {
		m_strCwcode = strCwcode;
		m_strOperId = strOperId;
		m_strCheckweightBWCode = strCheckweightBWCode;
		m_strBwcodeArrival = strBwcodeArrival;
	}
	
	public void transaction(Session objSession) throws Exception {
		String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.bw_code_weightcheck = '" +
		m_strCheckweightBWCode + 
		"', cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
		", cw.bw_code_arrival = " + m_strBwcodeArrival + 
		", cw.CW_MODIFYDATE = SYSDATE WHERE cw.CW_CODE = " + m_strCwcode;
		execute(objSession, strUpdateSql);
		// 记录轨迹点也要修改核查时间
		strUpdateSql = "update t_op_housewaybill hw SET hw.hw_op_id_weightcheck = " + m_strOperId + 
		", hw.hw_weightcheckdate = SYSDATE WHERE hw.CW_CODE = " + m_strCwcode;
		execute(objSession, strUpdateSql);
	}

}
