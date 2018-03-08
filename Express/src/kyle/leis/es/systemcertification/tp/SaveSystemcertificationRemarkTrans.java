package kyle.leis.es.systemcertification.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TesSystemcertification;

public class SaveSystemcertificationRemarkTrans extends AbstractTransaction {

	private String m_strOperId;
	private String m_strScId;
	private String m_strRemark;
	
	public void setParam(String strOperId,String strScId,String strRemark)
	{
		this.m_strOperId = strOperId;
		this.m_strScId = strScId;
		this.m_strRemark = strRemark;
	}
	
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strOperId) || StringUtility.isNull(m_strScId) || StringUtility.isNull(m_strRemark)) return;
		
		TesSystemcertification objTesSystemcertification = (TesSystemcertification)objSession.load(TesSystemcertification.class, Long.valueOf(m_strScId));
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.valueOf(m_strOperId));
		objTesSystemcertification.setTdiOperatorByScOpIdModify(objTdiOperator);
		objTesSystemcertification.setScRemark(m_strRemark);
		objTesSystemcertification.setScModifydate(DateFormatUtility.getSysdate());
		objSession.update(objTesSystemcertification);
	}

}
