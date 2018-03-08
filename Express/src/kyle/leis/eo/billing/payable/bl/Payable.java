package kyle.leis.eo.billing.payable.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableColumnsForImport;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.tp.DeleltePayableTrans;
import kyle.leis.eo.billing.payable.tp.ModifyPayableStatusTrans;
import kyle.leis.eo.billing.payable.tp.SavePayableTrans;
import kyle.leis.eo.finance.financialstatistics.blx.FinancialstatisticsThread;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class Payable {
	public void save(List listPayableColumns, 
			String strCwcode, 
			String strOperId) throws Exception {
		// 查询出原始费用
		List listOriginPyColumns = new ArrayList();
		if (!StringUtility.isNull(strCwcode))
			listOriginPyColumns = PayableDemand.load(strCwcode, "A0201");		
		SavePayableTrans objSavePayableTrans = new SavePayableTrans();
		objSavePayableTrans.setParam(listPayableColumns, 
				strCwcode, 
				strOperId,
				false,
				listOriginPyColumns);
		objSavePayableTrans.execute();
		// 更新余额
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginPyColumns,
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
				strCwcode);
		objFSThread.start();		
	}
	
	/**
	 * 加收费用
	 * @param strFkcode
	 * @param strUtcode
	 * @param strFkBasecode
	 * @param strFeeAmount
	 * @param strCwcode
	 * @param strCkcode
	 * @param strOperID
	 * @param isFixedFeeAmount
	 * @return
	 * @throws Exception
	 */
	public String add(String strFkcode,
			String strUtcode,
			String strFkBasecode,
			String strFeeAmount,
			String strCwcode,
			String strCkcode,
			String strChncode,
			String strOperID,
			boolean isFixedFeeAmount,
			int iChargeweightSign) throws Exception {
		HousewaybillColumns objHwcolumns = HousewaybillDemand.loadByCwcode(strCwcode);
		BigDecimal objFeeAmount = new BigDecimal(strFeeAmount);
		BigDecimal objUnitnumber = new BigDecimal("1");
		if (!isFixedFeeAmount) {
			if (!StringUtility.isNull(strUtcode) && strUtcode.equals("KG")) {
				objUnitnumber = new BigDecimal(objHwcolumns.getCwcwserverchargeweight());
				if (iChargeweightSign == 1)
					objUnitnumber = new BigDecimal(objHwcolumns.getCwcwchargeweight());
				else if (iChargeweightSign == 2)
					objUnitnumber = new BigDecimal(objHwcolumns.getCwcwtransferchargeweight());
				objFeeAmount = objFeeAmount.multiply(objUnitnumber);
			}
			if (!StringUtility.isNull(strUtcode) && strUtcode.equals("G")) {
				objUnitnumber = new BigDecimal(objHwcolumns.getCwcwserverchargeweight()).multiply(new BigDecimal("1000"));
				if (iChargeweightSign == 1)
					objUnitnumber = new BigDecimal(objHwcolumns.getCwcwchargeweight()).multiply(new BigDecimal("1000"));
				else if (iChargeweightSign == 2)
					objUnitnumber = new BigDecimal(objHwcolumns.getCwcwtransferchargeweight()).multiply(new BigDecimal("1000"));
				objFeeAmount = objFeeAmount.multiply(objUnitnumber);
			}
			if (!StringUtility.isNull(strUtcode) && strUtcode.equals("PCE")) {
				objUnitnumber = new BigDecimal(objHwcolumns.getCwcwpieces());
				objFeeAmount = objFeeAmount.multiply(objUnitnumber);
			}
			// 百分比
			if (!StringUtility.isNull(strUtcode) && strUtcode.equals("PCT")) {
				// 获得对应基费的价值
				List listPayable = PayableDemand.load(strCwcode, "A0201");
				objUnitnumber = PayableDemand.sumActualTotal(listPayable, 
						strFkBasecode, true);
				if (objUnitnumber.compareTo(new BigDecimal("0")) == 0)
					return "不存在对应的基费";
				objFeeAmount = objFeeAmount.multiply(objUnitnumber);
			}
		}
		
		Currency objCurrency = new Currency();
		String strCurrencyrate = objCurrency.getCurrencyrate(objHwcolumns.getScococode(), 
				"A", 
				objHwcolumns.getDbwadddate(), 
				"1", 
				strCkcode);
		if (StringUtility.isNull(strCurrencyrate) || 
				new BigDecimal(strCurrencyrate).compareTo(new BigDecimal("0")) == 0)
			return "未获得对应币种的汇率，对应的币种为" + strCkcode;
		
		PayableColumns objPayableColumns = PayableDemand.buildPayable(objHwcolumns, 
				objUnitnumber, 
				objFeeAmount, 
				strFkcode, 
				strCkcode, 
				"加收费用", 
				strCurrencyrate, 
				strOperID,
				strChncode);
		List<PayableColumns> listPayable = new ArrayList<PayableColumns>();
		listPayable.add(objPayableColumns);
		save(listPayable, strCwcode, strOperID);
		
		return "";
	}
	
	public void modifyStatus(String[] astrPyid, 
			String strOperId, 
			String strFscode) throws Exception {
		ModifyPayableStatusTrans objMPSTrans = new ModifyPayableStatusTrans();
		objMPSTrans.setParam(astrPyid, strOperId, strFscode);
		objMPSTrans.execute();
	}
	
	/**
	 * 删除某项费用
	 * @param astrPyid
	 * @param strOperId
	 * @throws Exception
	 */
	public void delete(String[] astrPyid, 
			String strOperId) throws Exception {
		DeleltePayableTrans objDPTrans = new DeleltePayableTrans();
		objDPTrans.setParam(astrPyid, strOperId);
		
		String strCwcode = objDPTrans.getOriginDeleteCwcode();
		List listOriginPyColumns = PayableDemand.load(strCwcode, "A0201");
		
		List<PayableColumns> listPayColumns = objDPTrans.getOriginPayable();
		if (listPayColumns == null || listPayColumns.size() < 1)
			return;
		// 执行事务
		objDPTrans.execute();
		// 更新余额
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginPyColumns,
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
				strCwcode);
		objFSThread.start();			
	}
	
	/**
	 * 删除所有费用
	 * @param strCwcode
	 * @param strOperId
	 * @throws Exception
	 */
	public void deleteAll(String strCwcode, 
			String strOperId) throws Exception {
		List listOriginPyColumns = PayableDemand.load(strCwcode, "A0201");
		
		DeleltePayableTrans objDPTrans = new DeleltePayableTrans();
		objDPTrans.setDelAllParam(strCwcode, strOperId);
		objDPTrans.execute();
		// 更新余额
		List<PayableColumns> listPayColumns = objDPTrans.getOriginPayable();
		if (listPayColumns == null || listPayColumns.size() < 1)
			return;
		FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginPyColumns,
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
				strCwcode);
		objFSThread.start();		
	}
	
	public PromptUtilityCollection importPayable(List listPyColumnsforimport,
			String strOperID) throws Exception {
		PromptUtilityCollection objPUCollection = new PromptUtilityCollection();
		if (listPyColumnsforimport == null || listPyColumnsforimport.size() < 1)
			return objPUCollection;
		PayableColumnsForImport objPCI = (PayableColumnsForImport)listPyColumnsforimport.get(0);
		String strCwserverewbcode = objPCI.getCwserverewbcode();
		HousewaybillColumns objHWColumns = HousewaybillDemand.load(strCwserverewbcode, 
				ICorewaybillBasicData.EWBCODE_TYPE_SERVER);
		if (objHWColumns == null) {
			objPUCollection.add("E_001", "不存在的运单号", "importReceivable");
			return objPUCollection;
		}
			
		try {
			List listPycolumns = PayableDemand.transferImportPyToColumns(objHWColumns, 
					listPyColumnsforimport, 
					strOperID);
			// 保存
			save(listPycolumns, objHWColumns.getHwcwcode(), strOperID);
		} catch (Exception ex) {
			objPUCollection.add("E_001", ex.toString(), "importReceivable");
			return objPUCollection;
		}
		return objPUCollection;
	}	
	
	
}
