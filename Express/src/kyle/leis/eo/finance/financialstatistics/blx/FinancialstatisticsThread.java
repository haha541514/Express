package kyle.leis.eo.finance.financialstatistics.blx;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.finance.financialstatistics.bl.Financialstatistics;

public class FinancialstatisticsThread extends Thread {
	private String m_strBrkcode;
	private String m_strCwcode;
	private List m_listOriginFeeColumns;
	private Logger m_objLogger = Logger.getLogger(FinancialstatisticsThread.class.getName());
	
	public FinancialstatisticsThread(List listOriginFeeColumns,
			String strBrkcode,
			String strCwcode) {
		m_strBrkcode = strBrkcode;
		m_strCwcode = strCwcode;
		m_listOriginFeeColumns = listOriginFeeColumns;
	}
	
	public void run() {
		try {
			// ��ΪӦ������ͳ�����
			if (!StringUtility.isNull(m_strBrkcode) &&
					m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) {
				Financialstatistics.reStatisticalByRvChange(m_listOriginFeeColumns, m_strCwcode);
			} else {
				// ��ΪӦ���仯����ͳ�����
				Financialstatistics.reStatisticalByPyChange(m_listOriginFeeColumns, m_strCwcode);			
			}
		} catch (Exception ex) {
			m_objLogger.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
