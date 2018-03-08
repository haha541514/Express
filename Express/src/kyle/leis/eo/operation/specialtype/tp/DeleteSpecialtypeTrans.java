package kyle.leis.eo.operation.specialtype.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;

public class DeleteSpecialtypeTrans extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strEstcode;
	
	public void setParam(String strCwcode, 
			String strEstcode) {
		m_strCwcode = strCwcode;
		m_strEstcode = strEstcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strEstcode)) {
			execute(objSession, "delete from t_op_waybillspecialtype wst " + 
					"where wst.cw_code = " + m_strCwcode);			
		} else {
			execute(objSession, "delete from t_op_waybillspecialtype wst " + 
					"where wst.cw_code = " + m_strCwcode + 
					" and wst.est_code = '" + m_strEstcode + "'");
		}
	}
}
