package kyle.leis.eo.billing.calculate.feecalculate.bl;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateDemand;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.feecalculate.tp.FeeRecordTransaction;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.eo.billing.payable.tp.DeleltePayableTrans;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.eo.billing.receivable.tp.DelelteReceivableTrans;
import kyle.leis.eo.finance.financialstatistics.bl.Financialstatistics;
import kyle.leis.eo.finance.financialstatistics.blx.FinancialstatisticsThread;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.SimplewaybillforpackageColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.tp.SaveHousewaybillTrans;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TdiProductkind;

public class AutoFeeCalculate {
	private static Logger s_objLogger = Logger.getLogger(AutoFeeCalculate.class.getName());
	private String m_strBrkcode;
	private HousewaybillColumns m_objHwbColumns;
	private FeeCalculate m_objFeeCalculate;
	
	public AutoFeeCalculate() {
		m_objFeeCalculate = new FeeCalculate();
	}
	
	/**
	 * 计算应收费用
	 * @param strCwcode
	 * @throws Exception
	 */
	public void calcReceivable(String strCwcode, String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW, 
				strRemark);
	}
	
	/**
	 * 计算服务商应付费用
	 * @param strCwcode
	 * @throws Exception
	 */
	public void calcServerpayable(String strCwcode, String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW, 
				strRemark);
	}
	
	/**
	 * 计算中转应付费用
	 * @param strCwcode
	 * @throws Exception
	 */
	public void calcTransferpayable(String strCwcode, 
			String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_TW, 
				strRemark);
	}
	
	
	public void calcChargeweightpayable(String strCwcode, 
			String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_CW, 
				strRemark);
	}	
	
	/**
	 * 重新计费
	 * @param strCwcode
	 * @throws Exception
	 */
	public void recalculate(String strCwcode) throws Exception {
		try {
			calcReceivable(strCwcode, "");
		} catch (Exception ex) {
			s_objLogger.warning(ex.getMessage());
		}
		calcServerpayable(strCwcode, "");
		// 同时计算收货重量应付
		calcChargeweightpayable(strCwcode, "");
	}
	
	private void calculate(String strCwcode, 
			String strBkcode, 
			String strRemark) throws Exception {
		// 查询运单情况
		m_strBrkcode = strBkcode;
		HousewaybillColumns objHwbColumns = (HousewaybillColumns)HousewaybillDemand.loadByCwcode(strCwcode);
		if (objHwbColumns == null) return;
		
		String strPkcode = objHwbColumns.getPkpkcode();
		if (StringUtility.isNull(strPkcode)) return;
		String strEnterprise = SystempropertyDemand.getEnterprise();
		// SLY不再重算2013.06.01之前运单的费用
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLY")) {
			if (!StringUtility.isNull(objHwbColumns.getHwhwsignindate()) && 
					objHwbColumns.getHwhwsignindate().substring(0, 19).compareTo("2013-06-10 00:00:00") < 0)
				return;
		}
		// 客户操作状态、预报状态都不计费
		if (StringUtility.isNull(objHwbColumns.getCwscwscode()) ||
				objHwbColumns.getCwscwscode().equals("PR") ||
				objHwbColumns.getCwscwscode().startsWith("C")) {
			// 进口系统申报时需要计费
			boolean isUndoAutocalcFee = true;
			if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLYIM") &&
					objHwbColumns.getCwscwscode().startsWith("CHD")) {
				isUndoAutocalcFee = false;
			}
			if (isUndoAutocalcFee)
				return;
		}
		TdiProductkind objTPK = TdiProductkindDC.loadByKey(strPkcode);
		// 如果按总单计费而且在总单值中没有值则表示按运单收货，单票不需要计费
		if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE)) {
			if (!StringUtility.isNull(objTPK.getPkBillingbybatchwaysign()) && 
					objTPK.getPkBillingbybatchwaysign().equals("Y")) {
				SimplewaybillforpackageColumns objSWFPC = HousewaybillDemand.querySimpleForPackage(strCwcode);
				if (objSWFPC == null)
					return;
			}
		}
		if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_PAYABLE)) {
			if (!StringUtility.isNull(objTPK.getPkPaybillingbybatchwaysign()) && 
					objTPK.getPkPaybillingbybatchwaysign().equals("Y")) {
				SimplewaybillforpackageColumns objSWFPC = HousewaybillDemand.querySimpleForPackage(strCwcode);
				if (objSWFPC == null)
					return;
			}
		}		
		// 签出之后才计算成本
		if (strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW) ||
				strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_TW)) {
			// SBD不需要出货计算成本，用到货时间代替出货时间
			if (!strEnterprise.startsWith("SBD")) {
				String strBwcodeDeparture = objHwbColumns.getDbwbwcode();
				if (StringUtility.isNull(strBwcodeDeparture))
					return;
			} else {
				String strBwcodeDeparture = objHwbColumns.getDbwbwcode();
				if (StringUtility.isNull(strBwcodeDeparture))
					objHwbColumns.setDbwadddate(DateFormatUtility.getStandardDate(objHwbColumns.getAbwadddate()));
				if (StringUtility.isNull(objHwbColumns.getSchnchncode()))
					return;
			}
		} else {
			// wc的直客不自动计算应收费用，都手工录入费用
			if (strEnterprise.startsWith("WC")) {
				TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(objHwbColumns.getCcococode());
				if (objTcoCustomer.getTdiCustomertype().getCtCode().equals("DC"))
					return;
			}
		}
		m_objHwbColumns = objHwbColumns;
		// 处理免费件
		processFreeWayBill(objHwbColumns);
		// 特殊类型
		List listSpecialtype = SpecialtypeDemand.load(strCwcode);		
		// 转换为计费的条件
		FreightpriceCondition objFPCondition = FeeCalculateDemand.transferToCondition(objHwbColumns, 
				strBkcode);
		// 转换为计费的参数
		FeeCalculateParameter objFCParameter = FeeCalculateDemand.transferToParameter(objHwbColumns, 
				strBkcode);
		objFCParameter.setSpecialtypeRecords(listSpecialtype);
		// 申报价值
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		if (listCargoInfo != null && listCargoInfo.size() > 0) {
			objFCParameter.setTotalDeclarevalue(CargoInfoDemand.sumCargovalue(listCargoInfo));
			// 汇率
			String strCurrency = CargoInfoDemand.getCargoCurrency(listCargoInfo);
			String strCurrencyrate = "0";
			if (!StringUtility.isNull(strCurrency)) {
				Currency objCurrency = new Currency();
				strCurrencyrate = objCurrency.getCurrencyrate(objFPCondition.getCocode(), 
						"A", 
						objFPCondition.getEpstartdate(), 
						"", 
						strCurrency);
			}
			objFCParameter.setDeclareCurrencyRate(strCurrencyrate);
		}
		// 计费条件和计费参数
		if (objFPCondition == null || 
				objFCParameter == null || 
				objHwbColumns.getCwscwscode().indexOf("EL") >= 0)
			return;
		// 计费
		HashMap<String,FeeCalculateResult> hmResults = m_objFeeCalculate.calculate(objFPCondition, 
				objFCParameter);
		record(hmResults, strRemark);
		// 计算应收则更新分区
		if (strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) {
			SaveHousewaybillTrans objSHWTrans = new SaveHousewaybillTrans();
			objSHWTrans.setModifyZnvIdParam(objHwbColumns.getHwcwcode(), 
					m_objFeeCalculate.getZncode(), 
					m_objFeeCalculate.getZnvId());
			objSHWTrans.execute();
		}
	}
	
	/**
	 * 记录费用至应收应付中
	 */
	protected void record(HashMap<String, FeeCalculateResult> hmFeeResult, 
			String strRemark) 
	throws Exception {
		// 记录费用
		FeeRecordTransaction objFRTransaction = new FeeRecordTransaction();
		objFRTransaction.setParam(hmFeeResult, 
				m_objHwbColumns, 
				m_strBrkcode,
				strRemark);
		objFRTransaction.execute();
		// 中间价计费不需要更新余额
		if (m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_CW) ||
				m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_TW))
			return;
		// 更新余额
		List listOriginRvColumns = objFRTransaction.getOriginBalanceRvColumns();
		List listOriginPayColumns = objFRTransaction.getOriginPayColumns();
		// 计算应付费用且原应收费用为空
		// 记录费用时已经查询出原费用，不需要此判断
		/*
		if (!(m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) && 
				(listOriginRvColumns == null || listOriginRvColumns.size() < 1))
			listOriginRvColumns = ReceivableDemand.load(m_objHwbColumns.getHwcwcode());
		// 计算应收费用且原应付费用为空
		if ((m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) && 
				(listOriginPayColumns == null || listOriginPayColumns.size() < 1))
			listOriginPayColumns = PayableDemand.load(m_objHwbColumns.getHwcwcode());
		*/		
		// 费用统计
		boolean isOriginCkcode = false;
		if (m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW))
			isOriginCkcode = true;
		Financialstatistics.reStatistical(listOriginRvColumns, 	
				listOriginPayColumns, 	
				m_objHwbColumns.getHwcwcode(),
				m_strBrkcode,
				isOriginCkcode);
	}
	
	/**
	 * 处理免费件
	 * @param objHwbColumns
	 */
	protected void processFreeWayBill(HousewaybillColumns objHwbColumns) throws Exception {
		// 免费件则删除原费用
		String strPmcode = objHwbColumns.getPmpmcode();
		if (strPmcode.equals(IFeeCalculateBasicData.PAYMENTMODE_FREE)) {
			List listOriginRvColumns = ReceivableDemand.load(objHwbColumns.getHwcwcode());
			// 删除应收
			DelelteReceivableTrans objDeleteRvTrans = new DelelteReceivableTrans();
			objDeleteRvTrans.setParam(objHwbColumns.getHwcwcode(), "0");
			objDeleteRvTrans.execute();
			// 更新余额
			FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginRvColumns,
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					objHwbColumns.getHwcwcode());
			objFSThread.start();				
			
			List listOriginPyColumns = PayableDemand.load(objHwbColumns.getHwcwcode(), "A0201");
			// 删除应付
			DeleltePayableTrans objDeletePayTrans = new DeleltePayableTrans();
			objDeletePayTrans.setParam(objHwbColumns.getHwcwcode(), "0");
			objDeletePayTrans.execute();	
			// 更新余额
			FinancialstatisticsThread objPFSThread = new FinancialstatisticsThread(listOriginPyColumns,
					IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
					objHwbColumns.getHwcwcode());
			objPFSThread.start();
		}
	}
}
