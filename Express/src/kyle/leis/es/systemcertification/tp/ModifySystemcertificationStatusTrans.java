package kyle.leis.es.systemcertification.tp;

import java.util.Date;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TesSystemcertification;
import net.sf.hibernate.Session;

public class ModifySystemcertificationStatusTrans extends AbstractTransaction {
	private String m_strOperId;
	private String m_strScId;
	private String m_strScSscode;
	private String m_strNewScId;
	
	public String getNewScId()
	{
		return this.m_strNewScId;
	}
	public void setParam(String strOperId,String strScId,String strScssCode)
	{
		this.m_strOperId = strOperId;
		this.m_strScId = strScId;
		this.m_strScSscode = strScssCode;
	}
	public void transaction(Session objSession) throws Exception {
		if(StringUtility.isNull(m_strOperId) || StringUtility.isNull(m_strScId) || StringUtility.isNull(m_strScSscode)) return;
		
		TesSystemcertification objTesSystemcertification = (TesSystemcertification)objSession.load(TesSystemcertification.class, Long.valueOf(m_strScId));
		TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, Long.valueOf(m_strOperId));
		
		Date objToday = DateFormatUtility.getSysdate();
		if(objTesSystemcertification.getTdiOperatorByScOpIdConfirm() == null)
			objTesSystemcertification.setTdiOperatorByScOpIdConfirm(objTdiOperator);
		if(objTesSystemcertification.getScConfirmdate() == null)
			objTesSystemcertification.setScConfirmdate(objToday);
		objTesSystemcertification.setTdiOperatorByScOpIdModify(objTdiOperator);
		objTesSystemcertification.setScModifydate(objToday);
		objTesSystemcertification.setTdiSimplestatus((TdiSimplestatus)objSession.load(TdiSimplestatus.class, m_strScSscode));
		objSession.update(objTesSystemcertification);
		this.m_strNewScId = String.valueOf(objTesSystemcertification.getScId());
	}

}
