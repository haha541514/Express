package kyle.leis.es.company.customer.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiOperator;

public class BatchModifyOperatorTrans extends AbstractTransaction {
	private String[] m_astrCocode;
	private String m_strMkcode;
	private String m_strNewOperId;
	private String m_strActionOperId;
	
	public void setParam(String[] astrCocode,
			String strNewOperId,
			String strMkcode, 
			String strActionOperId) {
		m_astrCocode = astrCocode;
		m_strMkcode = strMkcode;
		m_strNewOperId = strNewOperId;
		m_strActionOperId = strActionOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrCocode == null || m_astrCocode.length < 1 || 
				StringUtility.isNull(m_strMkcode))
			return;
		// �޸�����Ա��ͷ�Ա
		if (!m_strMkcode.equals("SS") && 
				!m_strMkcode.equals("CS") && 
				!m_strMkcode.equals("DN"))
			return;
		TdiOperator objTOPAction = TdiOperatorDC.loadByKey(m_strActionOperId);
		TdiOperator objTOPNew = TdiOperatorDC.loadByKey(m_strNewOperId);
		for (int i = 0; i < m_astrCocode.length; i++) {
			String strCocode = m_astrCocode[i];
			if (StringUtility.isNull(strCocode)) continue;
			// ԭ��˾�ʹ�����
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					strCocode);
			TcoCustomer objTcoCustomer = (TcoCustomer)objSession.load(TcoCustomer.class, 
					strCocode);
			// �޸��˺��޸�ʱ��
			objTcoCorporation.setTdiOperatorByCoOpIdModify(objTOPAction);
			objTcoCorporation.setCoModifydate(DateFormatUtility.getSysdate());
			// �޸Ŀͷ�Ա������Ա
			if (m_strMkcode.equals("SS"))
				objTcoCustomer.setTdiOperatorByCmOpIdSale(objTOPNew);
			else if (m_strMkcode.equals("CS"))
				objTcoCustomer.setTdiOperatorByCmOpIdCservice(objTOPNew);
			else if (m_strMkcode.equals("DN"))
				objTcoCustomer.setTdiOperatorByCmOpIdDun(objTOPNew);			
			// ����
			objSession.save(objTcoCorporation);
			objSession.save(objTcoCustomer);
		}
	}
}
