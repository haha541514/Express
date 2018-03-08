package kyle.leis.es.company.customer.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TcoCustomer;

public class SaveCreditlimitTrans extends AbstractTransaction {
	private String m_strCocode;
	private String m_strCreditLimit;
	private String m_strOldCreditLimit;
	private String m_strHoldHWRate;
	private String m_strOldHoldHWRate;
	
	public void setParam(String strCocode,			
			String strCreditLimit,
			String strHoldHWRate) {
		m_strCocode = strCocode;
		m_strCreditLimit = strCreditLimit;
		m_strHoldHWRate = strHoldHWRate;
	}
	
	public String getOldHoldHWRate() {
		return m_strOldHoldHWRate;
	}	
	
	public String getOldCreditlimit() {
		return m_strOldCreditLimit;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCocode))
			return;
		TcoCustomer objTcoCustomer = (TcoCustomer)objSession.load(TcoCustomer.class, 
				m_strCocode);
		// 旧信用额度
		BigDecimal objOldCreditlimit = objTcoCustomer.getCmCreditlimit();
		if (objOldCreditlimit != null)
			m_strOldCreditLimit = objOldCreditlimit.toString();
		else
			m_strOldCreditLimit = "0";
		
		BigDecimal objOldHoldHWRate = objTcoCustomer.getCmHoldhwrate();
		if (objOldHoldHWRate != null)
			m_strOldHoldHWRate = objOldHoldHWRate.toString();
		else
			m_strOldHoldHWRate = "0";
		
		// 修改额度
		objTcoCustomer.setCmCreditlimit(new BigDecimal(m_strCreditLimit));
		if (StringUtility.isNull(m_strHoldHWRate))
			m_strHoldHWRate = "0";
		objTcoCustomer.setCmHoldhwrate(new BigDecimal(m_strHoldHWRate));
		
		objTcoCustomer.setCmTemporarycreditlimit(new BigDecimal("0"));
		objSession.save(objTcoCustomer);
	}
}
