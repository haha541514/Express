package kyle.leis.eo.billing.calculate.feecalculate.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.SaleTrialCalculateResult;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.es.price.expressprice.bl.ExpressPrice;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.hi.TdiFeekind;

public class SaleTrialCalculate {
	private FeeCalculate m_objFeeCalculate;
	private static Log LOG = LogFactory.getLog(SaleTrialCalculate.class);
	
	public SaleTrialCalculate() {
		m_objFeeCalculate = new FeeCalculate();
	}
	
	public List<SaleTrialCalculateResult> calculate(SaleTrialCalculateParameter objSCParameter) 
	throws Exception {
		FreightpriceCondition objFPCondition = transferToCondition(objSCParameter);
		FeeCalculateParameter objFCParameter = transferToParameter(objSCParameter);
		List<SaleTrialCalculateResult> listSTCResults = new ArrayList<SaleTrialCalculateResult>();
		
		// 获得所有试算的产品
		//ProductkindQuery objProductkindQuery = new ProductkindQuery();
		//objProductkindQuery.setPksaletrialsign("Y");
		//objProductkindQuery.setUseCachesign(true);
		//List listTrialProductkind = objProductkindQuery.getResults();
		
		ExpressPrice objExpressPrice = new ExpressPrice();
		HashSet<String> hsPkcode = objExpressPrice.searchProductKind(objSCParameter.getCocode(), 
				objSCParameter.getOrginDtcode(), 
				"1",
				"");
		
		if (hsPkcode == null || hsPkcode.size() < 1)
			return listSTCResults;
		String strOriginPkcode = objSCParameter.getPkcode();
		
		for (Iterator i = hsPkcode.iterator(); i.hasNext(); ) {
			try {
				String strPkcode = (String)i.next();
				SaleTrialCalculateResult objSTCResult = new SaleTrialCalculateResult();
				// 已经设置产品则只计算该产品
				if (!StringUtility.isNull(strOriginPkcode) &&
						!strOriginPkcode.equals(strPkcode))
					continue;
				objSCParameter.setPkcode(strPkcode);
				objFPCondition.setPkcode(strPkcode);
			
				// 获得计费重量
				ChargeweightParameter objCWParameter = transferToCWParameter(objSCParameter);
				Chargeweight objChargeweight = new Chargeweight();
				ChargeweightResult objCWResult = objChargeweight.calculate(objCWParameter);
				if (StringUtility.isNull(objCWResult.getChargeweight()))
					continue;
				// 设置参数
				objFCParameter.setChargeWeight(objCWResult.getChargeweight());
				objFCParameter.setVolumeWeight(objCWResult.getVolumeRate());
				// 试算
				HashMap<String,FeeCalculateResult> hmFeeCalculateResult = m_objFeeCalculate.calculate(objFPCondition, 
						objFCParameter);
				if (hmFeeCalculateResult == null || hmFeeCalculateResult.size() < 1)
					continue;
				// 转换为结果
				setSaleTrialCalculateResult(hmFeeCalculateResult, objSTCResult);
				// 设置重量等
				objSTCResult.setGrossweight(objSCParameter.getGrossweight());
				objSTCResult.setPkcode(strPkcode);
				objSTCResult.setChargeweight(objCWResult.getChargeweight());
				objSTCResult.setVolumerate(objCWResult.getVolumeRate());
				objSTCResult.setVolumeweight(CorewaybillpiecesDemand.calcVolumeweight(objSCParameter.getPiecesList(), 
						objCWResult.getVolumeRate()));
				listSTCResults.add(objSTCResult);
			} catch (Exception e) {
				e.printStackTrace();
				LOG.error(e.toString());
			}
		}
		return listSTCResults;
	}
	
	private FreightpriceCondition transferToCondition(SaleTrialCalculateParameter objSCParameter) {
		FreightpriceCondition objFPCondition = new FreightpriceCondition();
		
		objFPCondition.setCocode(objSCParameter.getCocode());
		objFPCondition.setCtcode(objSCParameter.getCtcode());
		objFPCondition.setDtcode(objSCParameter.getOrginDtcode());
		objFPCondition.setEecode(IFeeCalculateBasicData.DEFAULT_ENTERPRISE);
		objFPCondition.setEpstartdate(DateFormatUtility.getStandardSysdate());
		objFPCondition.setEpstartdate2(DateFormatUtility.getStandardSysdate());
		objFPCondition.setPdcode(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE);
		objFPCondition.setPmcode(objSCParameter.getPmcode());
		objFPCondition.setPscode(IFeeCalculateBasicData.PRICESTATUS_RELEASE);
		
		return objFPCondition;
	}
	
	private FeeCalculateParameter transferToParameter(SaleTrialCalculateParameter objSCParameter) {
		FeeCalculateParameter objFCParameter = new FeeCalculateParameter();	
		
		int iPieces = 1;
		if (objSCParameter.getPiecesList() != null && objSCParameter.getPiecesList().size() > 0)
			iPieces = objSCParameter.getPiecesList().size();
		
		objFCParameter.setBkcode(IFeeCalculateBasicData.BILLINGKIND_RECEIVABLE);
		objFCParameter.setCtcode(objSCParameter.getCtcode());
		objFCParameter.setDtcode(objSCParameter.getDestDtcode());
		objFCParameter.setGrossWeight(objSCParameter.getGrossweight());
		objFCParameter.setPieces(String.valueOf(iPieces));
		objFCParameter.setPmcode(objSCParameter.getPmcode());
		
		return objFCParameter;
	}
	
	private ChargeweightParameter transferToCWParameter(SaleTrialCalculateParameter objSCParameter) {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		objCWParameter.setDtcode(objSCParameter.getDestDtcode());
		objCWParameter.setCocode(objSCParameter.getCocode());
		objCWParameter.setGrossWeight(objSCParameter.getGrossweight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		objCWParameter.setPkcode(objSCParameter.getPkcode());
		objCWParameter.setPostcode(objSCParameter.getPostcode());
		objCWParameter.setSearchDate(DateFormatUtility.getStandardSysdate());
		// 获得件数详细信息
		objCWParameter.setWaybillpiecesCollection(objSCParameter.getPiecesList());
		
		return objCWParameter;
	}
	
	
	private void setSaleTrialCalculateResult(HashMap<String,FeeCalculateResult> hmFeeCalculateResult,
			SaleTrialCalculateResult objSTCResult) throws Exception {
		
		BigDecimal objFreightvalue = new BigDecimal("0");
		BigDecimal objSurchargevalue = new BigDecimal("0");
		BigDecimal objIncidentalvalue = new BigDecimal("0");
		Iterator<String> itFeekind = hmFeeCalculateResult.keySet().iterator();

		while (itFeekind.hasNext()) {
			String strFkcode = itFeekind.next();
			FeeCalculateResult objFeeCalculateResult = hmFeeCalculateResult.get(strFkcode);
			BigDecimal objPricevalue = new BigDecimal(objFeeCalculateResult.getPricevalue());
			objPricevalue = objPricevalue.multiply(new BigDecimal(objFeeCalculateResult.getCurrencyrate()));
			objPricevalue = objPricevalue.divide(new BigDecimal("1"), 2, 4);
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(strFkcode);
			boolean isAccountingonly = false;
			if (!StringUtility.isNull(objTdiFeekind.getFkAccountingonlysign()) &&
					objTdiFeekind.getFkAccountingonlysign().equals("Y"))
					isAccountingonly = true;
			
			if (strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) {
				objFreightvalue = objFreightvalue.add(objPricevalue);
				objSTCResult.setFreightpriceRemark(objFeeCalculateResult.getFreightpriceRemark());
				
				objSTCResult.setExpressenddate(objFeeCalculateResult.getFreightpriceColumns().getEpependdate());
				objSTCResult.setExpressstartdate(objFeeCalculateResult.getFreightpriceColumns().getEpepstartdate());
				objSTCResult.setFreightprice(objFeeCalculateResult.getFreightpriceColumns().getFpepcode());
				objSTCResult.setZonename(objFeeCalculateResult.getFreightpriceColumns().getZnznname());
			}
			else if (strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL) && 
					!isAccountingonly)
				objIncidentalvalue = objIncidentalvalue.add(objPricevalue);
			else if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL))
				objSurchargevalue = objSurchargevalue.add(objPricevalue);
		}
		objSTCResult.setFreightvalue(objFreightvalue.toString());
		objSTCResult.setIncidentalvalue(objIncidentalvalue.toString());
		objSTCResult.setSurchargevalue(objSurchargevalue.toString());
	}
}
