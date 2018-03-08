package kyle.leis.eo.billing.calculate.feecalculate.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.adjustive.bl.Adjustive;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.freight.bl.Freight;
import kyle.leis.eo.billing.calculate.freight.bl.Surcharge;
import kyle.leis.eo.billing.calculate.incidental.bl.IncidentalSearch;
import kyle.leis.eo.billing.calculate.incidental.dax.IncidentalDemand;
import kyle.leis.eo.operation.channelsearch.dax.ChannelSearchResult;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.hi.TdiFeekind;

public class ChannelSearchFeeCalculate {
	private FeeCalculate m_objFeeCalculate;
	
	public ChannelSearchFeeCalculate() {
		m_objFeeCalculate = new FeeCalculate();
	}
	
	public List<ChannelSearchResult> calculate(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// ԭ�ƷѲ�ѯ����
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		// �����˷Ѽ۸�
		Freight objFreight = new Freight();
		List<FeeCalculateResult> listFCResults = objFreight.calcForchannelsearch(objFPCondition, 
				objCalcFeeParameter);
		if (listFCResults == null || listFCResults.size() < 0)
			return null;
		int iZnvid = objFreight.getZonevalueId();
		FreightpriceColumns objFPriceColumns = objFreight.getFreightpriceColumns();
		if (iZnvid < 0 || objFPriceColumns == null ||
				StringUtility.isNull(objFPriceColumns.getFpepcode()))
			return null;
		// ���Ҹ��ӷ�
		Surcharge objSurcharge = new Surcharge();
		objSurcharge.addSurchargevalue(objFPriceColumns, iZnvid);
		HashMap<String,FeeCalculateResult> hmSurchargevalueResult = objSurcharge.getSurchargevalueResult();
		// �����ӷ�
		// ת����ѯ����
		// ����һЩά���ĳɱ�����������ѡ��ѡ������ʱֻ�����������ĳɱ���
		boolean isFilterDiffChannel = false;
		String strEename = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strEename) && 
				strEename.startsWith("WC"))
			isFilterDiffChannel = true;
		IncidentalpriceCondition objIPCondition = IncidentalDemand.transferToCondition(objOldFPCondition);
		IncidentalSearch objIncidentalSearch = new IncidentalSearch();
		objIncidentalSearch.addIncidentalvalue(objIPCondition, 
				objFPriceColumns.getCtctcode(),
				objFPriceColumns.getPmpmcode(),
				hmSurchargevalueResult,
				objCalcFeeParameter.getSpecialtypeRecords(),
				isFilterDiffChannel);
		HashMap<String,FeeCalculateResult> hmFeeResult = objIncidentalSearch.getIncidentalvalueResult();
		// ����ֵ
		List<ChannelSearchResult> alResults = new ArrayList<ChannelSearchResult>();
        // �����ӷ�
		for (int i = 0; i < listFCResults.size(); i++) {
			FeeCalculateResult objFreightFeeResult = listFCResults.get(i);
			// �����ٵ��˷�
			hmFeeResult.put(objFreightFeeResult.getFkcode(), objFreightFeeResult);
			objCalcFeeParameter.setChargeWeight(objFreightFeeResult.getChargeweight());
			// ���������ϸ
			HashMap<String, FeeCalculateResult> hmFeeResults = m_objFeeCalculate.process(hmFeeResult, 
					objCalcFeeParameter, 
					objOldFPCondition);
			ChannelSearchResult objChannelSearchResult = transferToSearchResult(objFPriceColumns.getChnchncode(),
					objFreightFeeResult.getChargeweight(),
					hmFeeResults);
			// �����������
			Adjustive objAdjustive = new Adjustive();
			FeeCalculateResult objAFCResult = objAdjustive.calculate(objFPCondition, objCalcFeeParameter);
			if (objAFCResult != null)
				objChannelSearchResult.setAdjustivevalue(objAFCResult.getPricevalue());
			
			alResults.add(objChannelSearchResult);
		}
		return alResults;
	}
	
	private ChannelSearchResult transferToSearchResult(String strChncode,
			String strChargeweight,
			HashMap<String,FeeCalculateResult> hmFeeResults) throws Exception {
		ChannelSearchResult objChannelSearchResult = new ChannelSearchResult();
		objChannelSearchResult.setChncode(strChncode);
		objChannelSearchResult.setLevelweight(strChargeweight);
		
		BigDecimal objFreightvalue = new BigDecimal("0");
		BigDecimal objSurchargevalue = new BigDecimal("0");
		BigDecimal objIncidentalvalue = new BigDecimal("0");
		Iterator<String> itFeekind = hmFeeResults.keySet().iterator();

		while (itFeekind.hasNext()) {
			String strFkcode = itFeekind.next();
			FeeCalculateResult objFeeCalculateResult = hmFeeResults.get(strFkcode);
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
				objChannelSearchResult.setFreightpriceRemark(objFeeCalculateResult.getFreightpriceRemark());
				
				objChannelSearchResult.setExpressenddate(objFeeCalculateResult.getFreightpriceColumns().getEpependdate());
				objChannelSearchResult.setExpressstartdate(objFeeCalculateResult.getFreightpriceColumns().getEpepstartdate());
				objChannelSearchResult.setFreightprice(objFeeCalculateResult.getFreightpriceColumns().getFpepcode());
				objChannelSearchResult.setZonename(objFeeCalculateResult.getFreightpriceColumns().getZnznname());				
				
			}
			else if (strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL) &&
					!isAccountingonly)
				objIncidentalvalue = objIncidentalvalue.add(objPricevalue);
			else if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL))
				objSurchargevalue = objSurchargevalue.add(objPricevalue);
		}
		objChannelSearchResult.setFreightvalue(objFreightvalue.toString());
		objChannelSearchResult.setIncidentalvalue(objIncidentalvalue.toString());
		objChannelSearchResult.setSurchargevalue(objSurchargevalue.toString());
		
		return objChannelSearchResult;
	}
	
}
