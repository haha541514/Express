package kyle.leis.eo.operation.specialtype.tp;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;

public class SaveSingleSpecialtypeTrans extends AbstractTransaction {
	private String m_strOperId;
	private String m_strCwcode;
	private String m_strEstcode;
	private String m_strRemark;
	
	public void setParam(String strCwcode, 
			String strEstcode, 
			String strOperId, 
			String strRemark) {
		m_strCwcode = strCwcode;
		m_strEstcode = strEstcode;
		m_strOperId = strOperId;
		m_strRemark = strRemark; 
	}

	public void transaction(Session objSession) throws Exception {
		// 先删除
		DeleteSpecialtypeTrans objDSTrans = new DeleteSpecialtypeTrans();
		objDSTrans.setParam(m_strCwcode, m_strEstcode);
		objDSTrans.transaction(objSession);
		// 再新增
		SaveSpecialtypeTrans objSSTrans = new SaveSpecialtypeTrans();
		objSSTrans.setParam(m_strCwcode, m_strEstcode, m_strOperId, m_strRemark);
		objSSTrans.transaction(objSession);
	}
}
