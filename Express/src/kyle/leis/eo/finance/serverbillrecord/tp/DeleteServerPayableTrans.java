package kyle.leis.eo.finance.serverbillrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteServerPayableTrans extends AbstractTransaction {
	private String m_strSbrId;
	
	public void setParam(String strSbrId) {
		m_strSbrId = strSbrId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strSbrId)) return;
		// 删除应付费用的关系
		String strUpdatePaySql = "update t_bl_payable py set py.spy_id = null " +
		"where exists (select spy.spy_id from t_fi_serverpayable spy where spy.spy_id = py.spy_id and spy.sbr_id = " +
		m_strSbrId + ")";
		execute(objSession, strUpdatePaySql);
		
		strUpdatePaySql = "update t_bl_payable py set py.br_id = null, py.fs_code = 'C' " +
		"where exists (select br.br_id from t_Fi_Billrecord br where br.br_id = py.br_id and br.sbr_id = " +
		m_strSbrId + ")";
		execute(objSession, strUpdatePaySql);
		// 删除对应的应付账单
		String strDelBRSql = "delete from t_Fi_Billrecord BR WHERE br.sbr_id = " + m_strSbrId;
		execute(objSession, strDelBRSql);
		// 删除服务商应付
		String strDeleteSql = "delete from T_FI_SERVERPAYABLE spy where spy.SBR_ID = " + m_strSbrId;
		execute(objSession, strDeleteSql);		
		
		String strUpdateServerbillSql = "update t_fi_serverbillrecord sbr set sbr.ss_code = 'OFF' where sbr.SBR_ID = " + m_strSbrId;
		execute(objSession, strUpdateServerbillSql);	
	}

}
