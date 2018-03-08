package kyle.leis.eo.finance.billrecord.bl;

import java.math.BigDecimal;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.incidentalfee.dax.IncidentalfeeDemand;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.finance.billrecord.tp.MAccountingonlyTotalTrans;

public class BillRecordAmountTH extends Thread {
	private String m_strCocode;
	private String m_strBrid;
	private String m_strBkcode;
	private String m_strAccountingonlyAmount;
	private String m_strAccountAmount;
	
	public String getAccountingonlyAmount() {
		return m_strAccountingonlyAmount;
	}
	
	public String getAccountAmount() {
		return m_strAccountAmount;
	}	
	
	public void setParam(String strCocode,
			String strBrid,
			String strBkcode) {
		m_strCocode = strCocode;
		m_strBrid = strBrid;
		m_strBkcode = strBkcode;
	}
	
	
	public void run() {
		try {
			// 查询账单对应的记账费用
			BigDecimal objAccountingonlyAmount = new BigDecimal("0");
			String strReceivableAmount = "";
			String strPayableAmount = "";
			String strIncidentalAmount = "";
			
			if (m_strBkcode.startsWith("A01")) {
				objAccountingonlyAmount = ReceivableDemand.sumAccountingonly(m_strCocode, 
						m_strBrid);
				strReceivableAmount = ReceivableDemand.sumHasAccountingAmount(m_strCocode, m_strBrid);
				strPayableAmount = PayableDemand.sumHasAccountingAmount(m_strCocode, m_strBrid);
				strIncidentalAmount = IncidentalfeeDemand.sumHasAccountingAmount(m_strCocode, m_strBrid);
			} else if (m_strBkcode.startsWith("A02")) {
				strReceivableAmount = ReceivableDemand.sumHasAccountingOriginAmount(m_strCocode, m_strBrid);
				strPayableAmount = PayableDemand.sumHasAccountingOriginAmount(m_strCocode, m_strBrid);
				strIncidentalAmount = IncidentalfeeDemand.sumHasAccountingOriginAmount(m_strCocode, m_strBrid);				
			}
			BigDecimal objAccountAmount = new BigDecimal(StringUtility.isNull(strReceivableAmount) ? "0" : strReceivableAmount);
			objAccountAmount = objAccountAmount.add(new BigDecimal(StringUtility.isNull(strIncidentalAmount) ? "0" : strIncidentalAmount)); 
			objAccountAmount = objAccountAmount.add(new BigDecimal(StringUtility.isNull(strPayableAmount) ? "0" : strPayableAmount).multiply(new BigDecimal("-1")));
			// 更新账单的记账费用以及账单总额
			m_strAccountingonlyAmount = objAccountingonlyAmount.toString();
			m_strAccountAmount = objAccountAmount.toString();
			
			MAccountingonlyTotalTrans objMAOTTrans = new MAccountingonlyTotalTrans();
			objMAOTTrans.setParam(m_strBrid, objAccountingonlyAmount, objAccountAmount);
			objMAOTTrans.execute();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
