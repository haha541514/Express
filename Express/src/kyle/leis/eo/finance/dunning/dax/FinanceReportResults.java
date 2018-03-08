package kyle.leis.eo.finance.dunning.dax;

import java.util.List;

import kyle.common.connectors.util.Encoder;

public class FinanceReportResults {
	private String m_strFinanceKey;
	private String m_strCocode;
	private String m_strOccurDate;
	private String m_strRemark;
	private String m_strBillTotal;
	private String m_strCashTotal;
	private String m_strBalanceTotal;
	
	public void setFinanceKey(String strFinanceKey) {
		m_strFinanceKey = strFinanceKey;
	}
	
	public String getFinanceKey() {
		return m_strFinanceKey;
	}
	
	public void setCocode(String strCocode) {
		m_strCocode = strCocode;
	}
	
	public String getCocode() {
		return m_strCocode;
	}
	
	public void setOccurDate(String strOccurDate) {
		m_strOccurDate = strOccurDate;
	}
	
	public String getOccurDate() {
		return m_strOccurDate;
	}	
	
	public void setRemark(String strRemark) {
		m_strRemark = strRemark;
	}
	
	public String getRemark() {
		return m_strRemark;
	}		

	public void setBillTotal(String strBillTotal) {
		m_strBillTotal = strBillTotal;
	}
	
	public String getBillTotal() {
		return m_strBillTotal;
	}		
	
	public void setCashTotal(String strCashTotal) {
		m_strCashTotal = strCashTotal;
	}
	
	public String getCashTotal() {
		return m_strCashTotal;
	}		
	
	public void setBalanceTotal(String strBalanceTotal) {
		m_strBalanceTotal = strBalanceTotal;
	}
	
	public String getBalanceTotal() {
		return m_strBalanceTotal;
	}		
	
	public String[] toStringArray() {
		String[] astrResults = new String[7];
		
		astrResults[0] = this.getFinanceKey();
		astrResults[1] = this.getCocode();
		astrResults[2] = this.getOccurDate();
		astrResults[3] = this.getRemark();
		astrResults[4] = this.getBillTotal();
		astrResults[5] = this.getCashTotal();
		astrResults[6] = this.getBalanceTotal();
		
		return astrResults;
	}
	
	public static String toString(List<FinanceReportResults> listFinanceReportResults) {
		if (listFinanceReportResults == null || 
				listFinanceReportResults.size() < 1)
			return "";
		String[][] aastr = new String[listFinanceReportResults.size()][];
		for (int i = 0; i < listFinanceReportResults.size(); i++) {
			FinanceReportResults objFRResults = listFinanceReportResults.get(i);
			aastr[i] =  objFRResults.toStringArray();
		}
		Encoder objEncode = new Encoder();
		objEncode.addParameter(aastr);
		return objEncode.toString();		
	}
}
