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
	 * �ո������ʱ���仯
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
	 * ��ΪӦ�ձ仯����ͳ�����
	 * @param listOriginRvColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public static synchronized void reStatisticalByRvChange(List listOriginRvColumns,
			String strCwcode) throws Exception {
		HashMap<String, String> hmOriginReceivable = FinancialstatisticsDemand.statisticalByRev(listOriginRvColumns, false);
		// ��÷��ø��ĺ��Ӧ��
		List listRvColumns = ReceivableDemand.load(strCwcode);
		HashMap<String, String> hmReceivable = FinancialstatisticsDemand.statisticalByRev(listRvColumns, false);
		// ͳ���¾ɷ��ñ��й�˾��Ӧ�շ��ò��
		HashMap<String, String> hmRevDifference = getDifferenceCollection(hmOriginReceivable,
				hmReceivable);
		SaveFSCollectionTransaction objSaveCollectionTrans = new SaveFSCollectionTransaction();
		objSaveCollectionTrans.setParam(hmRevDifference, null, "", "", IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW);
		objSaveCollectionTrans.execute();
	}
	
	/**
	 * ��ΪӦ���仯����ͳ�����
	 * @param listOriginPyColumns
	 * @param strCwcode
	 * @throws Exception
	 */
	public static synchronized void reStatisticalByPyChange(List listOriginPyColumns,
			String strCwcode) throws Exception {
		HashMap<String, String> hmOriginPayable = FinancialstatisticsDemand.statisticalByPay(listOriginPyColumns, true);
		// ��÷��ø��ĺ��Ӧ��
		List listPayColumns = PayableDemand.load(strCwcode, "A0201");
		HashMap<String, String> hmPayable = FinancialstatisticsDemand.statisticalByPay(listPayColumns, true);
		
		String strCkcode = "";
		if (listOriginPyColumns != null && listOriginPyColumns.size() > 0) {
			PayableColumns objPayableColumns = (PayableColumns)listOriginPyColumns.get(0);
			strCkcode = objPayableColumns.getCkckcode();
		}
		// ͳ���¾ɷ��ñ��й�˾��Ӧ�շ��ò��
		HashMap<String, String> hmPayDifference = getDifferenceCollection(hmOriginPayable,
				hmPayable);
		SaveFSCollectionTransaction objSaveCollectionTrans = new SaveFSCollectionTransaction();
		objSaveCollectionTrans.setParam(null, hmPayDifference, strCkcode, "", IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW);
		objSaveCollectionTrans.execute();
	}
	
	/**
	 * �����˵�����ͳ��
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
		// ��÷��ø��ĺ��Ӧ�ա�Ӧ��
		List listRvColumns = ReceivableDemand.load(strCwcode);
		List listPayColumns = PayableDemand.load(strCwcode, "A0201");
		HashMap<String, String> hmReceivable = FinancialstatisticsDemand.statisticalByRev(listRvColumns, isOriginCkcode);
		HashMap<String, String> hmPayable = FinancialstatisticsDemand.statisticalByPay(listPayColumns, isOriginCkcode);
		// ͳ���¾ɷ��ñ��й�˾��Ӧ�շ��ò��
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
				// ��Ӧ�ձ��д���
				if (hmReceivable.containsKey(strCocode))
					objNewAmount = new BigDecimal(hmReceivable.get(strCocode));
				// ��� = ��Ӧ��-ԭӦ��
				hmDifference.put(strCocode, objNewAmount.add(objOriginAmount.multiply(new BigDecimal("-1"))).toString());
			}
		}
		// ֻ������Ӧ�ձ��д��ڵķ����ܺ�
		Iterator<String> objCocode = hmReceivable.keySet().iterator();
		while (objCocode.hasNext()) {
			String strCocode = objCocode.next();
			if (!hmOriginReceivable.containsKey(strCocode))
				hmDifference.put(strCocode, hmReceivable.get(strCocode));
		}		
		return hmDifference;
	}
}
