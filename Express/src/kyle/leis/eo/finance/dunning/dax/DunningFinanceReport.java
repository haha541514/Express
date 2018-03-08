package kyle.leis.eo.finance.dunning.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.da.BillrecordfordunColumns;
import kyle.leis.eo.finance.cashrecord.da.CashrecordfordunColumns;

public class DunningFinanceReport {
	public List<FinanceReportResults> transferFinanceReport(List listBillRecords,
			List listCashRecords,
			String strStartDate,
			String strEndDate) {
		List<FinanceReportResults> listResults = merge(listBillRecords, listCashRecords);
		calcBalanceAmount(listResults);
		listResults = filterByDate(listResults, strStartDate, strEndDate);
		return listResults;
	}
	
	/**
	 * 合并收付款和账单
	 * @param listBillRecords
	 * @param listCashRecords
	 * @return
	 */
	private List<FinanceReportResults> merge(List listBillRecords,
			List listCashRecords) {
		List<FinanceReportResults> listResults = new ArrayList<FinanceReportResults>();
		BillrecordfordunColumns objBRFDColumns = null;
		int iStartIndex = 0;
		if (listBillRecords != null && listBillRecords.size() > 0)
			for (int i = 0; i < listBillRecords.size(); i++) {
				FinanceReportResults objFRResults = new FinanceReportResults();
				objBRFDColumns = (BillrecordfordunColumns)listBillRecords.get(i);
				// 如果收付款日期小于账单日期则增加收付款
				iStartIndex = AddPrecedeCashCollection(listResults, 
						objBRFDColumns, 
						listCashRecords, 
						iStartIndex);
				objFRResults.setBalanceTotal("0");
				objFRResults.setBillTotal(objBRFDColumns.getBrtotal());
				objFRResults.setCashTotal("0");
				objFRResults.setCocode(objBRFDColumns.getBrco_code());
				objFRResults.setFinanceKey(objBRFDColumns.getBrbr_id());
				objFRResults.setOccurDate(objBRFDColumns.getBrbr_occurdate());
				objFRResults.setRemark(objBRFDColumns.getBrbr_remark());
				listResults.add(objFRResults);
			}
		// 增加收付款日期大于账单日期的收付款
		AddPastCashCollection(listResults, 
				objBRFDColumns, 
				listCashRecords, 
				iStartIndex);
		return listResults;		
	}
	
	private int AddPrecedeCashCollection(List<FinanceReportResults> listResults,
			BillrecordfordunColumns objBRFDColumns,
			List listCashRecords,
			int iStartCashIndex) {
		if (listCashRecords == null || listCashRecords.size() < 1) 
			return 0;
		int iEndIndex = iStartCashIndex;
		for (int i = iStartCashIndex; i < listCashRecords.size(); i++) {
			CashrecordfordunColumns objCRFDColumns = (CashrecordfordunColumns)listCashRecords.get(i);
			if (objCRFDColumns.getCrcr_occurdate().compareTo(objBRFDColumns.getBrbr_occurdate()) <= 0) {
				AddCashCollection(listResults, objCRFDColumns);
				iEndIndex = i + 1;
			}
		}
		return iEndIndex;
	}
	
	private int AddPastCashCollection(List<FinanceReportResults> listResults,
			BillrecordfordunColumns objBRFDColumns,
			List listCashRecords,
			int iStartCashIndex) {
		if (listCashRecords == null || listCashRecords.size() < 1) 
			return 0;
		int iEndIndex = iStartCashIndex;
		for (int i = iStartCashIndex; i < listCashRecords.size(); i++) {
			CashrecordfordunColumns objCRFDColumns = (CashrecordfordunColumns)listCashRecords.get(i);
			if (objBRFDColumns == null || 
					StringUtility.isNull(objBRFDColumns.getBrbr_occurdate()) || 
					objCRFDColumns.getCrcr_occurdate().compareTo(objBRFDColumns.getBrbr_occurdate()) > 0) {
				AddCashCollection(listResults, objCRFDColumns);
				iEndIndex = i + 1;
			}
		}
		return iEndIndex;		
	}
	
	private void AddCashCollection(List<FinanceReportResults> listResults,
			CashrecordfordunColumns objCRFDColumns) {
		if (objCRFDColumns == null) return;

		FinanceReportResults objFRResults = new FinanceReportResults();
		objFRResults.setBalanceTotal("0");
		objFRResults.setBillTotal("0");
		objFRResults.setCashTotal(objCRFDColumns.getCrtotal());
		objFRResults.setCocode(objCRFDColumns.getCrco_code());
		objFRResults.setFinanceKey(objCRFDColumns.getCrcr_id());
		objFRResults.setOccurDate(objCRFDColumns.getCrcr_occurdate());
		objFRResults.setRemark(objCRFDColumns.getCrcr_remark());
		
		listResults.add(objFRResults);		
	}
	
	
	/**
	 * 计算结余
	 * @param listResults
	 */
	private void calcBalanceAmount(List<FinanceReportResults> listResults) {
		if (listResults == null || listResults.size() < 1)
			return;
		for (int i = 0; i < listResults.size(); i++) {
			FinanceReportResults objFRResults = listResults.get(i);
			String strBillTotal = objFRResults.getBillTotal();
			String strCashTotal = objFRResults.getCashTotal();
			String strAboveGradeBalaceTotal = "0";
			if (i > 0)
				strAboveGradeBalaceTotal = listResults.get(i - 1).getBalanceTotal();
			// 余额相加
			if (StringUtility.isNull(strBillTotal))
				strBillTotal = "0";
			if (StringUtility.isNull(strCashTotal))
				strCashTotal = "0";			
			if (StringUtility.isNull(strAboveGradeBalaceTotal))
				strAboveGradeBalaceTotal = "0";
			// 计算余额
			BigDecimal objAboveBalanceTotal = new BigDecimal(strAboveGradeBalaceTotal);
			BigDecimal objBalaceTotal = objAboveBalanceTotal.add(new BigDecimal(strCashTotal));
			objBalaceTotal = objBalaceTotal.add(new BigDecimal(strBillTotal).multiply(new BigDecimal("-1")));
			objFRResults.setBalanceTotal(objBalaceTotal.toString());
		}
		
	}
	
	/**
	 * 过滤数据
	 * @param listResults
	 * @param strStartDate
	 * @param strEndDate
	 * @return
	 */
	private List<FinanceReportResults> filterByDate(List<FinanceReportResults> listResults,
			String strStartDate,
			String strEndDate) {
		List<FinanceReportResults> listFilterResults = new ArrayList<FinanceReportResults>();
		if (listResults == null || listResults.size() < 1) 
			return listFilterResults;
		for (int i = 0; i < listResults.size(); i++) {
			FinanceReportResults objFRResults = listResults.get(i);
			if (objFRResults.getOccurDate().compareTo(strStartDate) >= 0 &&
					objFRResults.getOccurDate().compareTo(strEndDate) <= 0)
				listFilterResults.add(objFRResults);
		}
		return listFilterResults;
	}
}

