package kyle.leis.es.company.customer.tp;

import java.math.BigDecimal;
import java.util.Date;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.hi.TcoCustomer;

public class SaveTempAndcreditlimitTrans extends AbstractTransaction {
	private String m_strCocode;
	private String m_strCreditLimit;
	private String m_strOldCreditLimit;
	private String m_strTemporarycreditlimit;
	private String m_strOldTemporarycreditlimit;
	private Date m_strTclstartdate;
	private Date m_strTclenddate;
	private String m_strHoldHWRate;
	private String m_strOldHoldHWRate;
	
	public void setParam(String strCocode,String strCreditLimit,
			String strTemporarycreditlimit,
			Date strTclstartdate,
			Date strTclenddate,
			String strHoldHWRate) {
		m_strCocode = strCocode;
		m_strCreditLimit = strCreditLimit;
		m_strTemporarycreditlimit = strTemporarycreditlimit;
		m_strTclstartdate = strTclstartdate;
		m_strTclenddate = strTclenddate;
		m_strHoldHWRate = strHoldHWRate;
	}
	
	public String getOldCreditlimit() {
		return m_strOldCreditLimit;
	}
	
	public String getOldHoldHWRate() {
		return m_strOldHoldHWRate;
	}	
	
	public String getM_strOldTemporarycreditlimit() {
		return m_strOldTemporarycreditlimit;
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
		// 旧扣货比列
		BigDecimal objOldHoldHWRate = objTcoCustomer.getCmHoldhwrate();
		if (objOldHoldHWRate != null)
			m_strOldHoldHWRate = objOldHoldHWRate.toString();
		else
			m_strOldHoldHWRate = "0";				
		
		//旧临时信用额度
		if(objTcoCustomer.getCmTemporarycreditlimit() != null)
			m_strOldTemporarycreditlimit = objTcoCustomer.getCmTemporarycreditlimit().toString();
		// 扣货比例
		if (StringUtility.isNull(m_strHoldHWRate))
			m_strHoldHWRate = "0";
		objTcoCustomer.setCmHoldhwrate(new BigDecimal(m_strHoldHWRate));		
		
		// 修改额度
		objTcoCustomer.setCmCreditlimit(new BigDecimal(m_strCreditLimit));
		objTcoCustomer.setCmTemporarycreditlimit(new BigDecimal(m_strTemporarycreditlimit));
		objTcoCustomer.setCmTclstartdate(m_strTclstartdate);
		objTcoCustomer.setCmTclenddate(m_strTclenddate);
		objSession.save(objTcoCustomer);
	}
}
