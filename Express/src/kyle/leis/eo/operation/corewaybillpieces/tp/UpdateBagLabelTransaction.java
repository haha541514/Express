package kyle.leis.eo.operation.corewaybillpieces.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class UpdateBagLabelTransaction extends AbstractTransaction {

	private String m_strOriginBgLabelcode;
	private String m_strNewBgLabelcode;
	
	public void setParam(String strOriginBgLabelcode, 
			String strNewBgLabelcode) {
		m_strOriginBgLabelcode = strOriginBgLabelcode;
		m_strNewBgLabelcode = strNewBgLabelcode;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		String strUpdateSql = "UPDATE t_op_corewaybillpieces cp " + 
		" SET cp.cp_Baglabelcode = '" +	m_strNewBgLabelcode +
		"' WHERE cp.cp_Baglabelcode = '" + m_strOriginBgLabelcode + "'";	
		execute(objSession, strUpdateSql);		
	}

}
