package kyle.leis.eo.operation.channelsearch.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.bl.ChannelSearchFeeCalculate;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchDemand;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ChanneltransferchargeColumns;
import kyle.leis.eo.operation.housewaybill.da.ChanneltransferchargeQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.specialtype.da.SpecialtypeColumns;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.businessrule.productrule.da.ProductruleCondition;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpressspecialtypeDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiExpressspecialtype;

public class ChannelSearch {
	
	private Map<String, String> m_hmMaxChargeChannel = new HashMap<String, String>();
	
	public List<ChannelSearchResult> searchChannels(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		if (SystempropertyDemand.getEnterprise().startsWith("HST")) {
			String strEecode = EnterpriseelementDemand.getUpBranchEecode(objFPCondition.getEecode());
			objFPCondition.setEecode(strEecode);
		} else {
			objFPCondition.setEecode("");
		}
		
		if (!StringUtility.isNull(objFPCondition.getEpstartdate()) &&
				objFPCondition.getEpstartdate().length() > 10) {
			objFPCondition.setEpstartdate(objFPCondition.getEpstartdate().substring(0, 10));
		}
		if (!StringUtility.isNull(objFPCondition.getEpstartdate2()) &&
				objFPCondition.getEpstartdate2().length() > 10) {
			objFPCondition.setEpstartdate2(objFPCondition.getEpstartdate2().substring(0, 10));
		}		
		// 获得体积件数
		List listWaybillpieces = new ArrayList();
		HousewaybillColumns objHwcolumns = null;
		if (!StringUtility.isNull(objCalcFeeParameter.getCwcode())) {
			listWaybillpieces = CorewaybillpiecesDemand.load(objCalcFeeParameter.getCwcode());
			objHwcolumns = HousewaybillDemand.loadByCwcode(objCalcFeeParameter.getCwcode());
			// 申报价值
			List listCargoInfo = CargoInfoDemand.queryByCwCode(objCalcFeeParameter.getCwcode());
			if (listCargoInfo != null && listCargoInfo.size() > 0) {
				objCalcFeeParameter.setTotalDeclarevalue(CargoInfoDemand.sumCargovalue(listCargoInfo));
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
				objCalcFeeParameter.setDeclareCurrencyRate(CargoInfoDemand.getCargoCurrency(listCargoInfo));
			}			
		}
		// 获得特殊类型
		List listSpecialtype = SpecialtypeDemand.load(objCalcFeeParameter.getCwcode());
		// 获得可选渠道
		HashSet<String> hsChannels = getChanneScope(objFPCondition,
				objCalcFeeParameter.getDtcode(),
				objCalcFeeParameter.getPostcode());
		if (hsChannels == null || hsChannels.size() < 1) 
			return null;
		// 如果特殊类型指定了渠道，则只出现该渠道
		hsChannels = filterBySpecialtype(hsChannels,
				objCalcFeeParameter.getSpecialtypeRecords());
		if (hsChannels == null || hsChannels.size() < 1) 
			return null;		
		Iterator<String> itChannels = hsChannels.iterator();
		List<ChannelSearchResult> listReturn = new ArrayList<ChannelSearchResult>();
		while(itChannels.hasNext()) {
			// 获得计费重量
			String strChannel = itChannels.next();
			/*
			if (strChannel.equals("19080"))
				strChannel = "19080";
			*/
			// 货物重量限制
			if (!ChannelSearchDemand.checkServerChargeweight(strChannel))
				continue;
			ChargeweightParameter objCWParameter = ChannelSearchDemand.transferToCWParameter(objCalcFeeParameter, 
					strChannel, 
					objFPCondition.getEpstartdate(), 
					listWaybillpieces);
			if (objHwcolumns != null)
				objCWParameter.setHawbGrossweight(objHwcolumns.getCwcwgrossweight());
			
			Chargeweight objChargeweight = new Chargeweight();
			ChargeweightResult objChargeweightResult = objChargeweight.calculate(objCWParameter);
			String strChargeweight = objChargeweightResult.getChargeweight();
			// 计算每个渠道的成本
			String strResultchargeweight = objChargeweightResult.getResultChargeweight();
			/*
			String strTwrk_id = objChargeweightResult.getTransferWRK();
			if (!StringUtility.isNull(strTwrk_id) && 
					strTwrk_id.equals("A01") && 
					objHwcolumns != null) {
				strSichargeweight = objHwcolumns.getCwcwchargeweight();
				objCalcFeeParameter.setChargeWeight(strSichargeweight);
			} else {
				objCalcFeeParameter.setChargeWeight(strChargeweight);
			}
			*/
			objCalcFeeParameter.setChargeWeight(strChargeweight);
			objCalcFeeParameter.setSpecialtypeRecords(listSpecialtype);
			ChannelSearchFeeCalculate objCSFCalculate = new ChannelSearchFeeCalculate();
			objFPCondition.setChncode(strChannel);
			objFPCondition.setPkcode("");
			List<ChannelSearchResult> listCSearchResults = objCSFCalculate.calculate(objFPCondition, 
					objCalcFeeParameter);
			if (listCSearchResults == null || listCSearchResults.size() < 1)
				continue;
			// 处理返回值
			processChannelResults(listCSearchResults,
					objCalcFeeParameter.getGrossWeight(),
					objChargeweightResult.getVolumeweight(),
					strChargeweight,
					objChargeweightResult.getExpression(),
					objChargeweightResult.getVolumeRate(),
					objChargeweightResult.getServerweightrulekind(),
					objChargeweightResult.getCarryweight(),
					strResultchargeweight);
			listReturn.addAll(listCSearchResults);
		}
		// 设置最佳渠道和相对值
		setOptimalChannels(listReturn);
		return listReturn;
	}
	
	private HashSet<String> getChanneScope(FreightpriceCondition objFPCondition,
			String strDdtcode, 
			String strPostcode) throws Exception {
		// 所有产品则返回所有渠道
		if (!StringUtility.isNull(objFPCondition.getPkcode()) &&
				objFPCondition.getPkcode().equals("A")) {
			ChannelCondition objChannelCondition = new ChannelCondition();
			objChannelCondition.setCscode("AP");
			objChannelCondition.setCstcode("S");
			List listResults = ChannelDemand.query(objChannelCondition);
			HashSet<String> hsChannel = new HashSet<String>();
			if (listResults != null && listResults.size() > 0) {
				for (int i = 0; i < listResults.size(); i++) {
					ChannelColumns objChannelColumns = (ChannelColumns)listResults.get(i);
					hsChannel.add(objChannelColumns.getChnchncode());
				}
			}
			return hsChannel;
		}
		ProductruleDemand objProductruleDemand = new ProductruleDemand();
		ProductruleCondition objPRCondition = ChannelSearchDemand.transferToPRCondition(objFPCondition);
		objPRCondition.setUseCacheSign(true);
		// 查找产品规则
		return objProductruleDemand.getChannels(objPRCondition, 
				objFPCondition.getDtcode(),
				strDdtcode, strPostcode);
	}
	
	// 特殊类型是否走指定渠道
	private HashSet<String> filterBySpecialtype(HashSet<String> hsChannelofscope,
			List listSpecialtype) throws Exception {
		HashSet<String> hsChannel = new HashSet<String>();
		if (listSpecialtype == null || listSpecialtype.size() < 1)
			return hsChannelofscope;
		if (hsChannelofscope == null || hsChannelofscope.size() < 1)
			return hsChannelofscope;
		
		String strEstPeculiarly = "";
		for (int i = 0; i < listSpecialtype.size(); i++) {
			SpecialtypeColumns objSpecialtypeColumns = (SpecialtypeColumns)listSpecialtype.get(i);
			String strEstcode = objSpecialtypeColumns.getWbstcomp_idestcode();
			TdiExpressspecialtype objTEST = TdiExpressspecialtypeDC.loadByKey(strEstcode);
			if (!StringUtility.isNull(objTEST.getEstPeculiarlychannelsign()) &&
					objTEST.getEstPeculiarlychannelsign().equals("Y")) {
				strEstPeculiarly = strEstcode;
				continue;
			}
				
		}
		if (StringUtility.isNull(strEstPeculiarly))
			return hsChannelofscope;
		for (String strChannel : hsChannelofscope) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChannel);
			TdiExpressspecialtype objTES = objTchnChannel.getTdiExpressspecialtype();
			if (objTES != null && 
					!StringUtility.isNull(objTES.getEstCode()))
				hsChannel.add(strChannel);
		}
		return hsChannel;
	}
	
	
	
	private void processChannelResults(List<ChannelSearchResult> listChannelResults,
			String strGrossweight,
			String strVolumeweight,
			String strChargeweight,
			String strCalcCWExpression,
			String strVolumerate,
			String strServerweightrulekind,
			String strCarriedweight,
			String strResultchargeweight) {
		if (listChannelResults == null || listChannelResults.size() < 1)
			return;
		for (int i = 0; i < listChannelResults.size(); i++) {
			ChannelSearchResult objChannelSearchResult = listChannelResults.get(i);
			objChannelSearchResult.setChargeweight(strChargeweight);
			objChannelSearchResult.setCalcCWExpression(strCalcCWExpression);
			objChannelSearchResult.setGrossweight(strGrossweight);
			objChannelSearchResult.setVolumeweight(strVolumeweight);
			objChannelSearchResult.setVolumerate(strVolumerate);
			objChannelSearchResult.setServerweightrulekind(strServerweightrulekind);
			objChannelSearchResult.setCarriedweight(strCarriedweight);
			objChannelSearchResult.setResultchargeweight(strResultchargeweight);
		}
	}
	
	private void setOptimalChannels(List<ChannelSearchResult> listChannelResults) throws Exception {
		List<ChannelSearchResult> listMinumChannels = new ArrayList<ChannelSearchResult>();
		for (int i = 0; i < listChannelResults.size(); i++) {
			ChannelSearchResult objChannelSearchResult = listChannelResults.get(i);
			String strChargeweight = StringUtility.replaceWhenNull(objChannelSearchResult.getChargeweight(),
					"0");
			String strLevelweight = StringUtility.replaceWhenNull(objChannelSearchResult.getLevelweight(),
					"0");
			// 最大金额
			boolean isMaxTransfercharge = checkMaxTransfercharge(objChannelSearchResult);
			String strResultchargeweight = objChannelSearchResult.getResultchargeweight();
			
			if (!isMaxTransfercharge && new BigDecimal(strChargeweight).compareTo(new BigDecimal(strLevelweight)) == 0) {
				listMinumChannels.add(objChannelSearchResult);	
				if (!StringUtility.isNull(strResultchargeweight)) {
					objChannelSearchResult.setChargeweight(strResultchargeweight);
					objChannelSearchResult.setLevelweight(strResultchargeweight);					
				}
			}
			if (!StringUtility.isNull(strResultchargeweight) &&
					!isMaxTransfercharge && 
					new BigDecimal(strChargeweight).compareTo(new BigDecimal(strLevelweight)) != 0) {
				objChannelSearchResult.setChargeweight(strResultchargeweight);
			}
			/*
			if (StringUtility.isNull(strSichargeweight)) {
				if (!isMaxTransfercharge && new BigDecimal(strChargeweight).compareTo(new BigDecimal(strLevelweight)) == 0)
					listMinumChannels.add(objChannelSearchResult);
			} else {
				// 按收货重量选择渠道
				if (!isMaxTransfercharge && new BigDecimal(strSichargeweight).compareTo(new BigDecimal(strLevelweight)) == 0) {
					objChannelSearchResult.setLevelweight(strChargeweight);
					listMinumChannels.add(objChannelSearchResult);			
				}
				if (!isMaxTransfercharge && new BigDecimal(strSichargeweight).compareTo(new BigDecimal(strLevelweight)) != 0) {
					objChannelSearchResult.setChargeweight(strSichargeweight);
				}
			}
			*/
		}
		// 设置最优渠道
		if (listMinumChannels == null || listMinumChannels.size() < 1)
			return;
		BigDecimal objMinumPricevalue = new BigDecimal(listMinumChannels.get(0).getTotalpricevalue());
		int iIndex = 0;
		for (int i = 0; i < listMinumChannels.size(); i++) {
			ChannelSearchResult objChannelSearchResult = listMinumChannels.get(i);
			BigDecimal objPricevalue = new BigDecimal(objChannelSearchResult.getTotalpricevalue());
			if (objMinumPricevalue.compareTo(objPricevalue) > 0) {
				objMinumPricevalue = objPricevalue;
				iIndex = i;
			}
		}
		listMinumChannels.get(iIndex).setOptimalsign("Y");
		// 设置相对值
		objMinumPricevalue = objMinumPricevalue.multiply(new BigDecimal("-1"));
		for (int i = 0; i < listChannelResults.size(); i++) {
			ChannelSearchResult objChannelSearchResult = listChannelResults.get(i);
			BigDecimal objPricevalue = new BigDecimal(objChannelSearchResult.getTotalpricevalue());
			// 如果已经设置了相对值则不再设置
			if (StringUtility.isNull(objChannelSearchResult.getRelativevalue()) ||
					!objChannelSearchResult.getRelativevalue().equals("9999999.99"))
				objChannelSearchResult.setRelativevalue(objPricevalue.add(objMinumPricevalue).toString());
		}
	}
	
	private boolean checkMaxTransfercharge(ChannelSearchResult objChannelSearchResult) throws Exception {
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objChannelSearchResult.getChncode());
		TdiCurrencykind objTdiCurrencykind = objTchnChannel.getTdiCurrencykind();
		if (objTdiCurrencykind == null || StringUtility.isNull(objTdiCurrencykind.getCkCode()))
			return false;
		// 最大金额
		String strCkcode = objTdiCurrencykind.getCkCode();
		BigDecimal objMaxdaycharge = objTchnChannel.getChnMaxdtransfercharge();
		BigDecimal objMaxmonthcharge = objTchnChannel.getChnMaxmtransfercharge();
		BigDecimal objMaxcharge = new BigDecimal("0");
		BigDecimal objSumservercharge = new BigDecimal("0");
		// 获取汇率
		Currency objCurrency = new Currency();
		String strCurrencyrate = objCurrency.getCurrencyrate(objTchnChannel.getTcoCorporation().getCoCode(), 
				"A", 
				DateFormatUtility.getStandardSysdate(), 
				"1", 
				strCkcode);
		if (objMaxdaycharge != null && objMaxdaycharge.compareTo(new BigDecimal("0")) > 0) {
			objMaxcharge = objMaxdaycharge.multiply(new BigDecimal(strCurrencyrate)).divide(new BigDecimal("1"), 2, 4);
		} else if (objMaxmonthcharge != null && objMaxmonthcharge.compareTo(new BigDecimal("0")) > 0) {
			objMaxcharge = objMaxmonthcharge.multiply(new BigDecimal(strCurrencyrate)).divide(new BigDecimal("1"), 2, 4);
		}
		if (m_hmMaxChargeChannel.containsKey(objChannelSearchResult.getChncode())) {
			// 已出货的费用+本票出货费用>最大费用则表示此渠道不能出货
			objSumservercharge = new BigDecimal(m_hmMaxChargeChannel.get(objChannelSearchResult.getChncode()));
			if (objSumservercharge.add(new BigDecimal(objChannelSearchResult.getTotalpricevalue())).compareTo(objMaxcharge) > 0) {
				objChannelSearchResult.setRelativevalue("9999999.99");
				return true;
			}
			return false;
		}
		ChanneltransferchargeQuery objCTCQuery = new ChanneltransferchargeQuery();
		objCTCQuery.setChncodesupplier(objChannelSearchResult.getChncode());
		
		if (objMaxdaycharge != null && objMaxdaycharge.compareTo(new BigDecimal("0")) > 0) {
			objCTCQuery.setDayoccurdate(DateFormatUtility.getStandardSysdate().substring(0, 10));
			objMaxcharge = objMaxdaycharge.multiply(new BigDecimal(strCurrencyrate)).divide(new BigDecimal("1"), 2, 4);
		} else if (objMaxmonthcharge != null && objMaxmonthcharge.compareTo(new BigDecimal("0")) > 0) {
			objCTCQuery.setMonthoccurdate(DateFormatUtility.getStandardSysdate().substring(0, 7));
			objMaxcharge = objMaxmonthcharge.multiply(new BigDecimal(strCurrencyrate)).divide(new BigDecimal("1"), 2, 4);
		}
		if (objMaxcharge.compareTo(new BigDecimal("0")) > 0) {
			List listResults = objCTCQuery.getResults();
			ChanneltransferchargeColumns objCTCC = (ChanneltransferchargeColumns)listResults.get(0);
			objSumservercharge = new BigDecimal(objCTCC.getSumservercharge());
			m_hmMaxChargeChannel.put(objChannelSearchResult.getChncode(), objSumservercharge.toString());
			// 已出货的费用+本票出货费用>最大费用则表示此渠道不能出货
			if (objSumservercharge.add(new BigDecimal(objChannelSearchResult.getTotalpricevalue())).compareTo(objMaxcharge) > 0) {
				objChannelSearchResult.setRelativevalue("9999999.99");
				return true;
			}
		}
		return false;
	}
	
}
