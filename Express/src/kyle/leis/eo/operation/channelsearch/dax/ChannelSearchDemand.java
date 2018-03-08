package kyle.leis.eo.operation.channelsearch.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.housewaybill.da.ChanneltransferweightColumns;
import kyle.leis.eo.operation.housewaybill.da.ChanneltransferweightQuery;
import kyle.leis.es.businessrule.businessrules.dax.IBusinessruleBasicData;
import kyle.leis.es.businessrule.productrule.da.ProductruleCondition;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;

public class ChannelSearchDemand {
	public static ProductruleCondition transferToPRCondition(FreightpriceCondition objFPCondition) {
		ProductruleCondition objPRCondition = new ProductruleCondition();
		objPRCondition.setPkcode(objFPCondition.getPkcode());
		objPRCondition.setSscode(IBusinessruleBasicData.SIMPLESTATUS_ON);
		objPRCondition.setValiddate1(objFPCondition.getEpstartdate());
		objPRCondition.setValiddate2(objFPCondition.getEpstartdate());
		return objPRCondition;
	}
	
	public static ChargeweightParameter transferToCWParameter(FeeCalculateParameter objCalcFeeParameter,
			String strChncode,
			String strSearchDate,
			List listWaybillpieces) {
		ChargeweightParameter objCWParameter = new ChargeweightParameter();
		
		objCWParameter.setCocode(strChncode);
		objCWParameter.setDtcode(objCalcFeeParameter.getDtcode());
		objCWParameter.setGrossWeight(objCalcFeeParameter.getGrossWeight());
		objCWParameter.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_COSTS);
		objCWParameter.setPostcode(objCalcFeeParameter.getPostcode());
		objCWParameter.setSearchDate(strSearchDate);
		objCWParameter.setWaybillpiecesCollection(listWaybillpieces);
		objCWParameter.setOriginVolumerate(objCalcFeeParameter.getOriginVolumerate());
		objCWParameter.setVolumeweight(objCalcFeeParameter.getVolumeWeight());
		
		return objCWParameter;
	}
	
	public static boolean checkServerChargeweight(String strChncode) throws Exception {
		// 检查重量限制
		ChannelColumns objChannelColumns = ChannelDemand.load(strChncode, true);
		String strMaxTCW = objChannelColumns.getChnchnmaxdtransferweight();
		if (StringUtility.isNull(strMaxTCW))
			strMaxTCW = "0";
		/*
		if (strChncode.equals("13") || strChncode.equals("14") ||
				strChncode.equals("15") || strChncode.equals("16")) {*/
		if (new BigDecimal(strMaxTCW).compareTo(new BigDecimal("0")) > 0) {
			// 查询总重量
			String strSysdate = DateFormatUtility.getStandardSysdate().substring(0, 10);
			ChanneltransferweightQuery objCTWQ = new ChanneltransferweightQuery();
			objCTWQ.setChncodesupplier(strChncode);
			objCTWQ.setCreatedate(strSysdate);
			List objList = objCTWQ.getResults();
			if (objList == null || objList.size() < 1) 
				return true;
			ChanneltransferweightColumns objCTWColumns = (ChanneltransferweightColumns)objList.get(0);
			BigDecimal objServerchargeweight = new BigDecimal(objCTWColumns.getSumserverchargeweight());
			if (objServerchargeweight.compareTo(new BigDecimal(strMaxTCW)) >= 0)
				return false;
			return true;
		}
		return true;
	}
}
