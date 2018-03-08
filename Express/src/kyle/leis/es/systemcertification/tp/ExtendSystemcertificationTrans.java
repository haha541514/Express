package kyle.leis.es.systemcertification.tp;


import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TesSystemcertification;
import net.sf.hibernate.Session;

public class ExtendSystemcertificationTrans extends AbstractTransaction {

	private String m_strOperId;
	private String m_strScId;
	private String m_strExtendDate;
	private String m_strNewScId;
	
	public String getNewScId()
	{
		return this.m_strNewScId;
	}
	
	public void setParam(String strOperId, String strScId,String strExtendDate)
	{
		this.m_strOperId = strOperId;
		this.m_strScId = strScId;
		this.m_strExtendDate = strExtendDate;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strOperId) || StringUtility.isNull(m_strScId) || StringUtility.isNull(m_strExtendDate)) return;
		
		TesSystemcertification objTdiSystemcertification = (TesSystemcertification)objSession.load(TesSystemcertification.class, Long.valueOf(m_strScId));
		if(!objTdiSystemcertification.getTdiSimplestatus().getSsCode().equals("ON")) return;//状态不为已认证则返回
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.valueOf(m_strOperId)); 
		
		objTdiSystemcertification.setScStartdate(DateFormatUtility.getStandardDate(DateFormatUtility.getStandardSysdate().substring(0,10)+" 00:00:00"));
		objTdiSystemcertification.setScEnddate(DateFormatUtility.getStandardDate(m_strExtendDate+" 23:59:59"));
		objTdiSystemcertification.setScModifydate(DateFormatUtility.getSysdate());
		objTdiSystemcertification.setTdiOperatorByScOpIdModify(objTdiOperator);
		objSession.update(objTdiSystemcertification);
		this.m_strNewScId = String.valueOf(objTdiSystemcertification.getScId());
	}

}
