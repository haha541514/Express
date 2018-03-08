package kyle.leis.eo.billing.calculate.feecalculate.dax;

import java.math.BigDecimal;
import java.util.logging.Logger;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.IReceivableBasicData;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiFeekind;

public class FeeCalculateDemand {
	static Logger s_objLogger = Logger.getLogger(FeeCalculateDemand.class.getName());
	public static FreightpriceCondition transferToCondition(HousewaybillColumns objHwbColumns,
			String strBkcode) throws Exception {
		FreightpriceCondition objFPriceCondition = new FreightpriceCondition();
		
		objFPriceCondition.setCtcode(objHwbColumns.getCtctcode());
		objFPriceCondition.setDtcode(objHwbColumns.getOdtdtcode());
		objFPriceCondition.setEecode(EnterpriseelementDemand.getUpBranchEecode(objHwbColumns.getEeeecode()));
		objFPriceCondition.setPmcode(objHwbColumns.getPmpmcode());
		objFPriceCondition.setPscode(IFeeCalculateBasicData.PRICESTATUS_RELEASE);
		// 应收
		if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE)) {
			objFPriceCondition.setChncode(objHwbColumns.getCchnchncode());
			objFPriceCondition.setCocode(CustomerDemand.getTopParentCustomer(objHwbColumns.getCcococode()));
			objFPriceCondition.setEpstartdate(objHwbColumns.getAbwadddate().substring(0, 10));
			objFPriceCondition.setEpstartdate2(objHwbColumns.getAbwadddate().substring(0, 10));
			// objFPriceCondition.setEpstartdate(objHwbColumns.getAbwadddate());
			// objFPriceCondition.setEpstartdate2(objHwbColumns.getAbwadddate());			
			objFPriceCondition.setPkcode(objHwbColumns.getPkpkcode());
			objFPriceCondition.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		} else if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_PAYABLE)) {
			objFPriceCondition.setChncode(objHwbColumns.getSchnchncode());
			objFPriceCondition.setCocode(objHwbColumns.getScococode());
			objFPriceCondition.setEpstartdate(objHwbColumns.getDbwadddate().substring(0, 10));
			objFPriceCondition.setEpstartdate2(objHwbColumns.getDbwadddate().substring(0, 10));
			// objFPriceCondition.setEpstartdate(objHwbColumns.getDbwadddate());
			// objFPriceCondition.setEpstartdate2(objHwbColumns.getDbwadddate());			
			
			objFPriceCondition.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_COSTS);			
		}
		if (strBkcode.equals(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_CW))
			objFPriceCondition.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_COSTS);
		return objFPriceCondition;
	}
	
	public static FeeCalculateParameter transferToParameter(HousewaybillColumns objHwbColumns,
			String strBkcode) throws Exception {
		FeeCalculateParameter objFCParameter = new FeeCalculateParameter();
		// 目的国家
		if (!StringUtility.isNull(objHwbColumns.getHwhwconsigneepostcode()))
			objFCParameter.setPostcode(objHwbColumns.getHwhwconsigneepostcode());
		else if (!StringUtility.isNull(objHwbColumns.getCwcwpostcodedestination()))
			objFCParameter.setPostcode(objHwbColumns.getCwcwpostcodedestination());
		
		if (StringUtility.isNull(objHwbColumns.getDdtdtcode()))
			objFCParameter.setDtcode(objHwbColumns.getSdtdtcode());
		else
			objFCParameter.setDtcode(objHwbColumns.getDdtdtcode());
		if (StringUtility.isNull(objFCParameter.getDtcode())) {
			s_objLogger.warning("目的国家为空，无法自动计费！");
			return null;
		}
		
		objFCParameter.setBkcode(strBkcode);
		
		if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE) ||
				strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_CW)) {
			objFCParameter.setChargeWeight(objHwbColumns.getCwcwchargeweight());
			objFCParameter.setGrossWeight(objHwbColumns.getCwcwgrossweight());
			objFCParameter.setPieces(objHwbColumns.getCwcwpieces());
			// 体积重
			objFCParameter.setVolumeWeight("");
		} else if (strBkcode.startsWith(IFeeCalculateBasicData.BILLINGKIND_PAYABLE_SW)) {
			objFCParameter.setChargeWeight(objHwbColumns.getCwcwserverchargeweight());
			objFCParameter.setGrossWeight(objHwbColumns.getCwcwtransfergrossweight());
			objFCParameter.setPieces(objHwbColumns.getCwcwtransferpieces());
			// 体积重
			objFCParameter.setVolumeWeight("");
		}
		// 票数
		objFCParameter.setBillcounts(objHwbColumns.getCwcwbillcounts());
		
		return objFCParameter;
	}
	
	public static ReceivableColumns transferToRecColumns(FeeCalculateResult objFeeCalculateResult,
			HousewaybillColumns objHwbColumns,
			String strBkcode) throws Exception {
		ReceivableColumns objReceivableColumns = new ReceivableColumns();
		
		BigDecimal objPricevalue = new BigDecimal(objFeeCalculateResult.getPricevalue());
		BigDecimal objUnitnumber = new BigDecimal(objFeeCalculateResult.getUnitnumber());
		
		objReceivableColumns.setBkbkcode(strBkcode);
		if (strBkcode.startsWith("A01")) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objFeeCalculateResult.getFkcode());
			String strFkAccountingonlysign = objTdiFeekind.getFkAccountingonlysign();
			if (!StringUtility.isNull(strFkAccountingonlysign) && strFkAccountingonlysign.equals("Y"))
				objReceivableColumns.setBkbkcode("A0102");
		}
		
		objReceivableColumns.setCkckcode(objFeeCalculateResult.getCkcode());
		objReceivableColumns.setChnchncode(objHwbColumns.getCchnchncode());
		// 记到最上层公司上
		objReceivableColumns.setCococode(CustomerDemand.getTopParentCustomer(objHwbColumns.getCcococode()));
		objReceivableColumns.setCwcwcode(Long.parseLong(objHwbColumns.getHwcwcode()));
		objReceivableColumns.setFsfscode(IReceivableBasicData.FEE_STATUS_CONFIRM);
		objReceivableColumns.setRvrvoccurdate(DateFormatUtility.getStandardDate(objHwbColumns.getAbwadddate()));
		objReceivableColumns.setMopopid(0L);
		
		objReceivableColumns.setFkfkcode(objFeeCalculateResult.getFkcode());
		if (!StringUtility.isNull(objFeeCalculateResult.getEpcode()))
			objReceivableColumns.setRvepcode(Long.parseLong(objFeeCalculateResult.getEpcode()));
		if (!StringUtility.isNull(objFeeCalculateResult.getEpvid()))
			objReceivableColumns.setRvepvid(Integer.parseInt(objFeeCalculateResult.getEpvid()));
		
		objReceivableColumns.setRvrvtotal(objPricevalue);
		objReceivableColumns.setRvrvactualtotal(objPricevalue);
		objReceivableColumns.setRvrvunitnumber(objUnitnumber);
		objReceivableColumns.setRvrvcurrencyrate(new BigDecimal(objFeeCalculateResult.getCurrencyrate()));
		objReceivableColumns.setRvrvremark(objFeeCalculateResult.getRemark());
		
		BigDecimal objUnitprice = objPricevalue.divide(objUnitnumber, 3, 4);
		objReceivableColumns.setRvrvunitprice(objUnitprice);
		
		String strCommissionrate = objFeeCalculateResult.getCommissionrate();
		if (StringUtility.isNull(strCommissionrate))
			strCommissionrate = "0";
		objReceivableColumns.setRvrvcommissionrate(new BigDecimal(strCommissionrate));
		
		return objReceivableColumns;
	}
	
	public static PayableColumns transferToPayableColumns(FeeCalculateResult objFeeCalculateResult,
			HousewaybillColumns objHwbColumns,
			String strBkcode) throws Exception {
		PayableColumns objPayableColumns = new PayableColumns();
		
		BigDecimal objPricevalue = new BigDecimal(objFeeCalculateResult.getPricevalue());
		BigDecimal objUnitnumber = new BigDecimal(objFeeCalculateResult.getUnitnumber());		
		
		objPayableColumns.setBkbkcode(strBkcode);
		objPayableColumns.setCkckcode(objFeeCalculateResult.getCkcode());
		
		objPayableColumns.setChnchncode(objHwbColumns.getSchnchncode());
		objPayableColumns.setCococode(objHwbColumns.getScococode());
		// 如果存储渠道不为空则设置为存储渠道
		String strChncode = objFeeCalculateResult.getStorechannelcode();
		if (!StringUtility.isNull(strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			objPayableColumns.setChnchncode(strChncode);
			objPayableColumns.setCococode(objTchnChannel.getTcoCorporation().getCoCode());			
		}
		objPayableColumns.setCwcwcode(Long.parseLong(objHwbColumns.getHwcwcode()));
		objPayableColumns.setFsfscode(IReceivableBasicData.FEE_STATUS_CONFIRM);
		objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(objHwbColumns.getDbwadddate()));
		objPayableColumns.setMopopid(0L);
		
		objPayableColumns.setFkfkcode(objFeeCalculateResult.getFkcode());
		objPayableColumns.setPyepcode(Long.parseLong(objFeeCalculateResult.getEpcode()));
		objPayableColumns.setPyepvid(Integer.parseInt(objFeeCalculateResult.getEpvid()));
		
		objPayableColumns.setPypytotal(objPricevalue);
		objPayableColumns.setPypyactualtotal(objPricevalue);
		objPayableColumns.setPypyunitnumber(objUnitnumber);
		objPayableColumns.setPypycurrencyrate(new BigDecimal(objFeeCalculateResult.getCurrencyrate()));
		objPayableColumns.setPypyremark(objFeeCalculateResult.getRemark());		
		
		BigDecimal objUnitprice = new BigDecimal("0");
		if (objUnitnumber.compareTo(new BigDecimal("0")) != 0) {
			objUnitprice = objPricevalue.divide(objUnitnumber, 3, 4);
		}
		
		objPayableColumns.setPypyunitprice(objUnitprice);		
		if (StringUtility.isNull(objFeeCalculateResult.getCommissionrate()))
			objPayableColumns.setPypycommissionrate(new BigDecimal("0"));
		else
			objPayableColumns.setPypycommissionrate(new BigDecimal(objFeeCalculateResult.getCommissionrate()));
		
		return objPayableColumns;
	}
	
}
