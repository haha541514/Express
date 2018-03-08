package kyle.leis.es.businessrule.businessrules.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;

public class ModifyBusinessruleTrans extends AbstractTransaction {
	private String m_strBrid;
	private String m_strSscode;
	private String m_strStartDate;
	private String m_strEndDate;
	private String m_strOperId;
	/**
	 * �޸ļ۸��״̬
	 * @param strEpcode
	 * @param strPscode
	 */
	public void setModifyStatusParam(String strBrid, 
			String strSscode, 
			String strOperId) {
		m_strBrid = strBrid;
		m_strSscode = strSscode;
		m_strOperId = strOperId;
	}
	
	/**
	 * �޸ļ۸������
	 * @param strEpcode
	 * @param strSavingStartDate
	 * @param strSavingEndDate
	 */
	public void setModifyDateParam(String strBrid, 
			String strStartDate, 
			String strEndDate, 
			String strOperId) {
		m_strBrid =  strBrid;
		m_strStartDate = strStartDate;
		m_strEndDate = strEndDate;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBrid)) return;
		// ��ѯ�۸��
		TbrBusinessrule objTbrBusinessrule = (TbrBusinessrule)objSession.load(TbrBusinessrule.class, 
				Long.parseLong(m_strBrid));
		// �޸ļ۸��״̬
		if (!StringUtility.isNull(m_strSscode)) {
			TdiSimplestatus objTdiSimplestatus = (TdiSimplestatus)objSession.load(TdiSimplestatus.class, 
					m_strSscode);
			objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
		}
		// �޸�����
		if (!StringUtility.isNull(m_strStartDate))
			objTbrBusinessrule.setBrStartdate(DateFormatUtility.getStandardDate(m_strStartDate));
		if (!StringUtility.isNull(m_strEndDate))
			objTbrBusinessrule.setBrEnddate(DateFormatUtility.getStandardDate(m_strEndDate));
		// ����Ա
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(objTdiOperator);
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
		}
		objSession.save(objTbrBusinessrule);		
	}
}
