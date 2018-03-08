package kyle.leis.es.systemcertification.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TesSystemcertification;
import net.sf.hibernate.Session;

public class SaveSFOwnEnterpriseTrans extends AbstractTransaction {

	private String m_strOperId;
	private String m_strScId;
	private String m_strOwnEnterpriseSign;
	
	public void setParam(String strOperId,
			String strScId,
			String strOwnEnterpriseSign) {
		m_strOperId = strOperId;
		m_strScId = strScId;
		m_strOwnEnterpriseSign = strOwnEnterpriseSign;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strOperId) || 
				StringUtility.isNull(m_strScId) || 
				StringUtility.isNull(m_strOwnEnterpriseSign)) 
			return;
		
		TesSystemcertification objTesSystemcertification = (TesSystemcertification)objSession.load(TesSystemcertification.class, 
				Long.valueOf(m_strScId));
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
				Long.valueOf(m_strOperId));
		
		objTesSystemcertification.setTdiOperatorByScOpIdModify(objTdiOperator);
		objTesSystemcertification.setScOwnenterprisesign(m_strOwnEnterpriseSign);
		objTesSystemcertification.setScModifydate(DateFormatUtility.getSysdate());
		objSession.update(objTesSystemcertification);
	}

}
