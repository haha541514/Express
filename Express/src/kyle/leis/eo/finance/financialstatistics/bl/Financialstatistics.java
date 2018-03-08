package kyle.leis.eo.finance.financialstatistics.bl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.finance.financialstatistics.dax.FinancialstatisticsDemand;
import kyle.leis.eo.finance.financialstatistics.tp.SaveFSCollectionTransaction;
import kyle.leis.eo.finance.financialstatistics.tp.SaveWhenCashModifyTrans;

public class Financialstatistics {
	/**
	 * 收付款调整时余额变化
	 * @param strCocode
	 * @param strOriginCrtotal
	 * @param strCurrentCrtotal
	 * @throws Exception
	 */
	public static synchronized void modifyCash(String strCocode, 
			String strOriginCrtotal, 
			String strCurrentCrtotal) throws Exception {
		SaveWhenCashModifyTrans objSWCMTrans = new SaveWhenCashModifyTrans();
		objSWCMTrans.setParam(strCocode, strOriginCrtotal, strCurrentCrtotal);
		objSWCMTrans.execute();
	}
	
	/**
	 * 因为应收变化重新统计余额
	 * @param listOriginRvColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public static synchronized void reStatisticalByRvChange(List listOriginRvColumns,
			String strCwcode) throws Exception {
		HashMap<String, String> hmOriginReceivable = FinancialstatisticsDemand.statisticalByRev(listOriginRvColumns, false);
		// 获得费用更改后的应收
		List listRvColumns = ReceivableDemand.load(strCwcode);
		HashMap<String, String> hmReceivable = FinancialstatisticsDemand.statisticalByRev(listRvColumns, false);
		// 统计新旧费用表中公司的应收费用差额
		HashMap<String, String> hmRevDifference = getDifferenceCollection(hmOriginReceivable,
				hmReceivable);
		SaveFSCollectionTransaction objSaveCollectionTrans = new SaveFSCollectionTransaction();
		objSaveCollectionTrans.setParam(hmRevDifference, null, "", "", IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW);
		objSaveCollectionTrans.execute();
	}
	
	/**
	 * 因为应付变化重新统计余额
	 * @param listOriginPyColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public static synchronized void reStatisticalByPyChange(List listOriginPyColumns,
			String strCwcode) throws Exception {
		HashMap<String, String> hmOriginPayable = FinancialstatisticsDemand.statisticalByPay(listOriginPyColumns, true);
		// 获得费用更改后的应收
		List listPayColumns = PayableDemand.load(strCwcode, "A0201");
		HashMap<String, String> hmPayable = FinancialstatisticsDemand.statisticalByPay(listPayColumns, true);
		
		String strCkcode = "";
		if (listOriginPyColumns != null && listOriginPyColumns.size() > 0) {
			PayableColumns objPayableColumns = (PayableColumns)listOriginPyColumns.get(0);
			strCkcode = objPayableColumns.getCkckcode();
		}
		// 统计新旧费用表中公司的应收费用差额
		HashMap<String, String> hmPayDifference = getDifferenceCollection(hmOriginPayable,
				hmPayable);
		SaveFSCollectionTransaction objSaveCollectionTrans = new SaveFSCollectionTransaction();
		objSaveCollectionTrans.setParam(null, hmPayDifference, strCkcode, "", IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW);
		objSaveCollectionTrans.execute();
	}
	
	/**
	 * 根据运单重新统计
	 * @param listOriginRvColumns
	 * @param listOriginPyColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public static synchronized void reStatistical(List listOriginRvColumns,
			List listOriginPyColumns,
			String strCwcode,
			String strBkcode,
			boolean isOriginCkcode) throws Exception {
		HashMap<String, String> hmOriginReceivable = FinancialstatisticsDemand.statisticalByRev(listOriginRvColumns, isOriginCkcode);
		HashMap<String, String> hmOriginPayable = FinancialstatisticsDemand.statisticalByPay(listOriginPyColumns, isOriginCkcode);
		// 获得费用更改后的应收、应付
		List listRvColumns = ReceivableDemand.load(strCwcode);
		List listPayColumns = PayableDemand.load(strCwcode, "A0201");
		HashMap<String, String> hmReceivable = FinancialstatisticsDemand.statisticalByRev(listRvColumns, isOriginCkcode);
		HashMap<String, String> hmPayable = FinancialstatisticsDemand.statisticalByPay(listPayColumns, isOriginCkcode);
		// 统计新旧费用表中公司的应收费用差额
		HashMap<String, String> hmRevDifference = getDifferenceCollection(hmOriginReceivable,
				hmReceivable);
		HashMap<String, String> hmPayDifference = getDifferenceCollection(hmOriginPayable,
				hmPayable);
		
		String strCkcode = "";
		if (isOriginCkcode && 
				listPayColumns != null && 
				listPayColumns.size() > 0) {
			PayableColumns objPayableColumns = (PayableColumns)listPayColumns.get(0);
			strCkcode = objPayableColumns.getCkckcode();
		}		
		
		SaveFSCollectionTransaction objSaveCollectionTrans = new SaveFSCollectionTransaction();
		objSaveCollectionTrans.setParam(hmRevDifference, hmPayDifference, strCkcode, strCwcode, strBkcode);
		objSaveCollectionTrans.execute();
	}
	
	private static HashMap<String, String> getDifferenceCollection(HashMap<String, String> hmOriginReceivable,
			HashMap<String, String> hmReceivable) {
		HashMap<String, String> hmDifference = new HashMap<String, String>();
		if (hmOriginReceivable != null && hmOriginReceivable.size() > 0) {
			Iterator<String> objCocode = hmOriginReceivable.keySet().iterator();
			while (objCocode.hasNext()) {
				String strCocode = objCocode.next();
				BigDecimal objOriginAmount = new BigDecimal(hmOriginReceivable.get(strCocode));
				BigDecimal objNewAmount = new BigDecimal("0");
				// 新应收表中存在
				if (hmReceivable.containsKey(strCocode))
					objNewAmount = new BigDecimal(hmReceivable.get(strCocode));
				// 差额 = 新应收-原应收
				hmDifference.put(strCocode, objNewAmount.add(objOriginAmount.multiply(new BigDecimal("-1"))).toString());
			}
		}
		// 只有在新应收表中存在的费用总和
		Iterator<String> objCocode = hmReceivable.keySet().iterator();
		while (objCocode.hasNext()) {
			String strCocode = objCocode.next();
			if (!hmOriginReceivable.containsKey(strCocode))
				hmDifference.put(strCocode, hmReceivable.get(strCocode));
		}		
		return hmDifference;
	}
}
