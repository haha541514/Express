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
	 * ����Ӧ�շ���
	 * @param strCwcode
	 * @throws Exception
	 */
	public void calcReceivable(String strCwcode, String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW, 
				strRemark);
	}
	
	/**
	 * ���������Ӧ������
	 * @param strCwcode
	 * @throws Exception
	 */
	public void calcServerpayable(String strCwcode, String strRemark) throws Exception {
		calculate(strCwcode, 
				IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW, 
				strRemark);
	}
	
	/**
	 * ������תӦ������
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
	 * ���¼Ʒ�
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
		// ͬʱ�����ջ�����Ӧ��
		calcChargeweightpayable(strCwcode, "");
	}
	
	private void calculate(String strCwcode, 
			String strBkcode, 
			String strRemark) throws Exception {
		// ��ѯ�˵����
		m_strBrkcode = strBkcode;
		HousewaybillColumns objHwbColumns = (HousewaybillColumns)HousewaybillDemand.loadByCwcode(strCwcode);
		if (objHwbColumns == null) return;
		
		String strPkcode = objHwbColumns.getPkpkcode();
		if (StringUtility.isNull(strPkcode)) return;
		String strEnterprise = SystempropertyDemand.getEnterprise();
		// SLY��������2013.06.01֮ǰ�˵��ķ���
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLY")) {
			if (!StringUtility.isNull(objHwbColumns.getHwhwsignindate()) && 
					objHwbColumns.getHwhwsignindate().substring(0, 19).compareTo("2013-06-10 00:00:00") < 0)
				return;
		}
		// �ͻ�����״̬��Ԥ��״̬�����Ʒ�
		if (StringUtility.isNull(objHwbColumns.getCwscwscode()) ||
				objHwbColumns.getCwscwscode().equals("PR") ||
				objHwbColumns.getCwscwscode().startsWith("C")) {
			// ����ϵͳ�걨ʱ��Ҫ�Ʒ�
			boolean isUndoAutocalcFee = true;
			if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLYIM") &&
					objHwbColumns.getCwscwscode().startsWith("CHD")) {
				isUndoAutocalcFee = false;
			}
			if (isUndoAutocalcFee)
				return;
		}
		TdiProductkind objTPK = TdiProductkindDC.loadByKey(strPkcode);
		// ������ܵ��ƷѶ������ܵ�ֵ��û��ֵ���ʾ���˵��ջ�����Ʊ����Ҫ�Ʒ�
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
		// ǩ��֮��ż���ɱ�
		if (strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW) ||
				strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_TW)) {
			// SBD����Ҫ��������ɱ����õ���ʱ��������ʱ��
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
			// wc��ֱ�Ͳ��Զ�����Ӧ�շ��ã����ֹ�¼�����
			if (strEnterprise.startsWith("WC")) {
				TcoCustomer objTcoCustomer = TcoCustomerDC.loadByKey(objHwbColumns.getCcococode());
				if (objTcoCustomer.getTdiCustomertype().getCtCode().equals("DC"))
					return;
			}
		}
		m_objHwbColumns = objHwbColumns;
		// ������Ѽ�
		processFreeWayBill(objHwbColumns);
		// ��������
		List listSpecialtype = SpecialtypeDemand.load(strCwcode);		
		// ת��Ϊ�Ʒѵ�����
		FreightpriceCondition objFPCondition = FeeCalculateDemand.transferToCondition(objHwbColumns, 
				strBkcode);
		// ת��Ϊ�ƷѵĲ���
		FeeCalculateParameter objFCParameter = FeeCalculateDemand.transferToParameter(objHwbColumns, 
				strBkcode);
		objFCParameter.setSpecialtypeRecords(listSpecialtype);
		// �걨��ֵ
		List listCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
		if (listCargoInfo != null && listCargoInfo.size() > 0) {
			objFCParameter.setTotalDeclarevalue(CargoInfoDemand.sumCargovalue(listCargoInfo));
			// ����
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
		// �Ʒ������ͼƷѲ���
		if (objFPCondition == null || 
				objFCParameter == null || 
				objHwbColumns.getCwscwscode().indexOf("EL") >= 0)
			return;
		// �Ʒ�
		HashMap<String,FeeCalculateResult> hmResults = m_objFeeCalculate.calculate(objFPCondition, 
				objFCParameter);
		record(hmResults, strRemark);
		// ����Ӧ������·���
		if (strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) {
			SaveHousewaybillTrans objSHWTrans = new SaveHousewaybillTrans();
			objSHWTrans.setModifyZnvIdParam(objHwbColumns.getHwcwcode(), 
					m_objFeeCalculate.getZncode(), 
					m_objFeeCalculate.getZnvId());
			objSHWTrans.execute();
		}
	}
	
	/**
	 * ��¼������Ӧ��Ӧ����
	 */
	protected void record(HashMap<String, FeeCalculateResult> hmFeeResult, 
			String strRemark) 
	throws Exception {
		// ��¼����
		FeeRecordTransaction objFRTransaction = new FeeRecordTransaction();
		objFRTransaction.setParam(hmFeeResult, 
				m_objHwbColumns, 
				m_strBrkcode,
				strRemark);
		objFRTransaction.execute();
		// �м�ۼƷѲ���Ҫ�������
		if (m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_CW) ||
				m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_TW))
			return;
		// �������
		List listOriginRvColumns = objFRTransaction.getOriginBalanceRvColumns();
		List listOriginPayColumns = objFRTransaction.getOriginPayColumns();
		// ����Ӧ��������ԭӦ�շ���Ϊ��
		// ��¼����ʱ�Ѿ���ѯ��ԭ���ã�����Ҫ���ж�
		/*
		if (!(m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) && 
				(listOriginRvColumns == null || listOriginRvColumns.size() < 1))
			listOriginRvColumns = ReceivableDemand.load(m_objHwbColumns.getHwcwcode());
		// ����Ӧ�շ�����ԭӦ������Ϊ��
		if ((m_strBrkcode.equals(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW)) && 
				(listOriginPayColumns == null || listOriginPayColumns.size() < 1))
			listOriginPayColumns = PayableDemand.load(m_objHwbColumns.getHwcwcode());
		*/		
		// ����ͳ��
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
	 * ������Ѽ�
	 * @param objHwbColumns
	 */
	protected void processFreeWayBill(HousewaybillColumns objHwbColumns) throws Exception {
		// ��Ѽ���ɾ��ԭ����
		String strPmcode = objHwbColumns.getPmpmcode();
		if (strPmcode.equals(IFeeCalculateBasicData.PAYMENTMODE_FREE)) {
			List listOriginRvColumns = ReceivableDemand.load(objHwbColumns.getHwcwcode());
			// ɾ��Ӧ��
			DelelteReceivableTrans objDeleteRvTrans = new DelelteReceivableTrans();
			objDeleteRvTrans.setParam(objHwbColumns.getHwcwcode(), "0");
			objDeleteRvTrans.execute();
			// �������
			FinancialstatisticsThread objFSThread = new FinancialstatisticsThread(listOriginRvColumns,
					IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE_CW,
					objHwbColumns.getHwcwcode());
			objFSThread.start();				
			
			List listOriginPyColumns = PayableDemand.load(objHwbColumns.getHwcwcode(), "A0201");
			// ɾ��Ӧ��
			DeleltePayableTrans objDeletePayTrans = new DeleltePayableTrans();
			objDeletePayTrans.setParam(objHwbColumns.getHwcwcode(), "0");
			objDeletePayTrans.execute();	
			// �������
			FinancialstatisticsThread objPFSThread = new FinancialstatisticsThread(listOriginPyColumns,
					IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW,
					objHwbColumns.getHwcwcode());
			objPFSThread.start();
		}
	}
}
