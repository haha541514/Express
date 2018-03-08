package kyle.leis.eo.finance.cashrecord.dax;

import java.math.BigDecimal;

import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;

public class SumCashTotalResult {
	private BigDecimal m_objSumCrTotal;
	private CashrecordColumns m_objCashrecordColumns;
	
	public BigDecimal getSumCrTotal() {
		return m_objSumCrTotal;
	}
	
	public void setSumCrTotal(BigDecimal objSumCrTotal) {
		m_objSumCrTotal = objSumCrTotal;
	}
	
	public CashrecordColumns getCashrecordColumns() {
		return m_objCashrecordColumns;
	}
	
	public void setCashrecordColumns(CashrecordColumns objCashrecordColumns) {
		m_objCashrecordColumns = objCashrecordColumns;
	}
}
