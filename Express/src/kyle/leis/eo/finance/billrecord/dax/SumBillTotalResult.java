package kyle.leis.eo.finance.billrecord.dax;

import java.math.BigDecimal;

import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;

public class SumBillTotalResult {
	private BigDecimal m_objSumBrTotal;
	private BillrecordColumns m_objBillrecordColumns;
	
	public BigDecimal getSumBrTotal() {
		return m_objSumBrTotal;
	}
	
	public void setSumBrTotal(BigDecimal objSumBrTotal) {
		m_objSumBrTotal = objSumBrTotal;
	}
	
	public BillrecordColumns getBillrecordColumns() {
		return m_objBillrecordColumns;
	}
	
	public void setBillrecordColumns(BillrecordColumns objBillrecordColumns) {
		m_objBillrecordColumns = objBillrecordColumns;
	}
}
