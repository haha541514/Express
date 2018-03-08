package kyle.leis.eo.billing.calculate.feecalculate.blx;

import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.bl.AutoFeeCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.customerservice.issue.bl.Issue;

public class AutoFeeCalculateThread extends Thread {
	private String m_strCwcode;
	private String m_strBrkcode;
	private String m_strRemark;
	private boolean m_isRecordArrearageIssue;
	private boolean m_isSyncCustomerBalance;
	private String m_strSyncCocode;
	private static Logger s_objLogger = Logger.getLogger(AutoFeeCalculateThread.class.getName());
	
	public AutoFeeCalculateThread(String strCwcode, 
			String strBrkcode,
			boolean isRecordArrearageIssue) {
		m_strCwcode = strCwcode;
		m_strBrkcode = strBrkcode;
		m_isRecordArrearageIssue = isRecordArrearageIssue;
	}
	
	public AutoFeeCalculateThread(String strCwcode, 
			String strBrkcode,
			String strRemark,
			boolean isRecordArrearageIssue) {
		m_strCwcode = strCwcode;
		m_strBrkcode = strBrkcode;
		m_strRemark = strRemark;
		m_isRecordArrearageIssue = isRecordArrearageIssue;
	}	
	
	public void setSyncCustomerBalance(String strCocode) {
		m_isSyncCustomerBalance = true;
		m_strSyncCocode = strCocode;
	}
	
	public void run() {
		try {
			AutoFeeCalculate objAutoFeeCalculate = new AutoFeeCalculate();
			// 计算应收费用
			if (!StringUtility.isNull(m_strBrkcode) && 
					m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) {
				objAutoFeeCalculate.calcReceivable(m_strCwcode, m_strRemark);
				/*
				if (m_isSyncCustomerBalance && !StringUtility.isNull(m_strSyncCocode)) {
					if (!"82402".equals(m_strSyncCocode)) {
						String strSqlText = "select FUN_SYNC_CORBALANCE(" + m_strSyncCocode + ") from dual"; 
						CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
						objCalcWVQuery.getResults(strSqlText);	
					}
				}
				*/
				// 记录欠费问题
				if (m_isRecordArrearageIssue) {
					Issue objIssue = new Issue();
					objIssue.addArrearageIssue(m_strCwcode);
				}
			}
			// 计算应付费用
			if (!StringUtility.isNull(m_strBrkcode) &&
					m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW)) {
				objAutoFeeCalculate.calcServerpayable(m_strCwcode, m_strRemark);
				// 同时计算收货重量应付
				objAutoFeeCalculate.calcChargeweightpayable(m_strCwcode, m_strRemark);
			}
			// 计算应收应付费用
			if (StringUtility.isNull(m_strBrkcode)) {
				objAutoFeeCalculate.recalculate(m_strCwcode);
			}
		} catch(Exception ex) {
			s_objLogger.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
