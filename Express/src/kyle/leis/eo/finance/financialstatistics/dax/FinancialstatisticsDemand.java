package kyle.leis.eo.finance.financialstatistics.dax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;

public class FinancialstatisticsDemand {
	public static HashMap<String, String> statisticalByRev(List listRvColumns,
			boolean isOriginCurrency) {
		HashMap<String, String> hmStatistics = new HashMap<String, String>();
		if (listRvColumns == null || listRvColumns.size() < 0)
			return hmStatistics;
		
		for (int i = 0; i < listRvColumns.size(); i++) {
			ReceivableColumns objRvColumns = (ReceivableColumns)listRvColumns.get(i);
			String strCocode = objRvColumns.getCococode();
			String strFscode = objRvColumns.getFsfscode();
			String strActualtotal = objRvColumns.getRvrvactualtotal();
			String strCurrencyrate = objRvColumns.getRvrvcurrencyrate();
			BigDecimal objAmount = new BigDecimal(strActualtotal).multiply(new BigDecimal(strCurrencyrate));
			if (isOriginCurrency)
				objAmount = new BigDecimal(strActualtotal);			
			
			objAmount = objAmount.divide(new BigDecimal("1"), 2, 4);
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT))
				continue;
			if (hmStatistics.containsKey(strCocode)) {
				// 已经包含公司的费用
				BigDecimal objSumAmount = new BigDecimal(hmStatistics.get(strCocode));
				objSumAmount = objSumAmount.add(objAmount);
				hmStatistics.put(strCocode, objSumAmount.toString());
			} else {
				// 未包含则直接新增到集合中
				hmStatistics.put(strCocode, objAmount.toString());
			}
		}
		return hmStatistics;
	}
	
	public static HashMap<String, String> statisticalByPay(List listPyColumns, boolean isOriginCkcode) {
		HashMap<String, String> hmStatistics = new HashMap<String, String>();
		if (listPyColumns == null || listPyColumns.size() < 0)
			return hmStatistics;
		
		for (int i = 0; i < listPyColumns.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPyColumns.get(i);
			String strCocode = objPayableColumns.getCococode();
			String strFscode = objPayableColumns.getFsfscode();
			String strActualtotal = objPayableColumns.getPypyactualtotal();
			String strCurrencyrate = objPayableColumns.getPypycurrencyrate();
			BigDecimal objAmount = new BigDecimal(strActualtotal).multiply(new BigDecimal(strCurrencyrate));
			if (isOriginCkcode)
				objAmount = new BigDecimal(strActualtotal);
			
			objAmount = objAmount.divide(new BigDecimal("1"), 2, 4);
			if (strFscode.equals(IReceivableBasicData.FEE_STATUS_WRITEOFF) ||
					strFscode.equals(IReceivableBasicData.FEE_STATUS_PRELERT))
				continue;
			if (hmStatistics.containsKey(strCocode)) {
				// 已经包含公司的费用
				BigDecimal objSumAmount = new BigDecimal(hmStatistics.get(strCocode));
				objSumAmount = objSumAmount.add(objAmount);
				hmStatistics.put(strCocode, objSumAmount.toString());
			} else {
				// 未包含则直接新增到集合中
				hmStatistics.put(strCocode, objAmount.toString());
			}
		}
		return hmStatistics;
	}
}
