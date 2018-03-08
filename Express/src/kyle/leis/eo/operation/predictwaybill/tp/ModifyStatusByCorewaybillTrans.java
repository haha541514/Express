package kyle.leis.eo.operation.predictwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class ModifyStatusByCorewaybillTrans extends AbstractTransaction {

	private String m_strCwcode;
	private String m_strCwscode;
	private String m_strOperID;
	
	public void setParam(String strCwcode,
			String strCwscode,
			String strOperID) {
		m_strCwcode = strCwcode;
		m_strCwscode = strCwscode;
		m_strOperID = strOperID;
	}
	
	public void transaction(Session objSession) throws Exception {
		String strPwbscode = "";
		String strSQL = "";
		String strCargoInfoSQL = "";
		if (m_strCwscode.equals("SI") ||
				m_strCwscode.equals("IP") ||
				m_strCwscode.equals("SO")) {
			strSQL = "delete from t_op_predictwaybill pwb where pwb.cw_code = " + m_strCwcode;
			strCargoInfoSQL = "delete from T_OP_PREDICTCARGOINFO pwc " +
					           "where pwc.pwb_code in " +
					           "(select pwb_code from t_op_predictwaybill pwb where pwb.cw_code = " + m_strCwcode + ")";
		} else {
			if (m_strCwscode.equals("CHD")) {
				strPwbscode = "CHU";
			} else if (m_strCwscode.equals("CHP")) {
				strPwbscode = "CHP";
			} else if (m_strCwscode.equals("CEL")) {
				strPwbscode = "CEL";
			}
			if (!StringUtility.isNull(strPwbscode)) {
				strSQL = "update t_op_predictwaybill pwb " + 
						    "set pwb.pwbs_code = '" + strPwbscode + "'" +
				            ",   pwb.op_id_modifier = " + m_strOperID +
				            ",   pwb.pwb_modifydate = sysdate " +
						  "where pwb.cw_code = " + m_strCwcode;
			}
		}
		if (!StringUtility.isNull(strCargoInfoSQL)) {
			execute(objSession, strCargoInfoSQL);
		}		
		if (!StringUtility.isNull(strSQL)) {
			execute(objSession, strSQL);
		}
	}

}
