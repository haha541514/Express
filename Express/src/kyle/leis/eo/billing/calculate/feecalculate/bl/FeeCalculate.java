package kyle.leis.eo.billing.calculate.feecalculate.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.freight.bl.Freight;
import kyle.leis.eo.billing.calculate.freight.bl.Surcharge;
import kyle.leis.eo.billing.calculate.incidental.bl.IncidentalSearch;
import kyle.leis.eo.billing.calculate.incidental.dax.IncidentalDemand;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.hi.TdiFeekind;

public class FeeCalculate {
	static Logger s_objLogger = Logger.getLogger(FeeCalculate.class.getName());
	private HashMap<String, String> m_hmCurrencyrate = new HashMap<String, String>();
	private String m_strZncode;
	private int m_iZnvid;
	
	public String getZncode() {
		return m_strZncode;
	}
	
	public int getZnvId() {
		return m_iZnvid;
	}
	
	/**
	 * �������
	 * @throws Exception
	 */
	public HashMap<String,FeeCalculateResult> calculate(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// ԭ�ƷѲ�ѯ����
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		// �����˷Ѽ۸�
		Freight objFreight = new Freight();
		FeeCalculateResult objFreightFeeResult = objFreight.calculate(objFPCondition, objCalcFeeParameter);
		if (objFreightFeeResult == null ||
				StringUtility.isNull(objFreightFeeResult.getPricevalue()))
			return null;
		int iZnvid = objFreight.getZonevalueId();
		FreightpriceColumns objFPriceColumns = objFreight.getFreightpriceColumns();
		if (iZnvid < 0 || objFPriceColumns == null ||
				StringUtility.isNull(objFPriceColumns.getFpepcode()))
			return null;
		// �����ͷ���ID
		m_strZncode = objFPriceColumns.getZnzncode();
		m_iZnvid = iZnvid;
		// ���Ҹ��ӷ�
		Surcharge objSurcharge = new Surcharge();
		objSurcharge.addSurchargevalue(objFPriceColumns, iZnvid);
		HashMap<String,FeeCalculateResult> hmSurchargevalueResult = objSurcharge.getSurchargevalueResult();
		// �����ٵ��˷�
		hmSurchargevalueResult.put(objFreightFeeResult.getFkcode(), objFreightFeeResult);
		// �����ӷ�
		// ת����ѯ����
		IncidentalpriceCondition objIPCondition = IncidentalDemand.transferToCondition(objOldFPCondition);
		IncidentalSearch objIncidentalSearch = new IncidentalSearch();
		objIncidentalSearch.addIncidentalvalue(objIPCondition, 
				objFPriceColumns.getCtctcode(),
				objFPriceColumns.getPmpmcode(),
				hmSurchargevalueResult,
				objCalcFeeParameter.getSpecialtypeRecords(),
				false);
		HashMap<String,FeeCalculateResult> hmFeeResult = objIncidentalSearch.getIncidentalvalueResult();
		return process(hmFeeResult, objCalcFeeParameter, objOldFPCondition);
	}
	
	/**
	 * ��������ӷ�
	 * @throws Exception
	 */
	public HashMap<String,FeeCalculateResult> process(HashMap<String,FeeCalculateResult> hmFeeResult, 
			FeeCalculateParameter objCalcFeeParameter,
			FreightpriceCondition objFPCondition) throws Exception {
		if (hmFeeResult == null && hmFeeResult.size() < 1)
			return null;
		// �����ӷѼ۸�ֵ
		HashMap<String,FeeCalculateResult> hmFeeResultProcessed = processPricevalue(hmFeeResult, 
				objCalcFeeParameter, 
				objFPCondition);
		// �����������õ������ٷֱȷ���
		processPCTPricevalue(objCalcFeeParameter, 
				hmFeeResultProcessed, 
				objFPCondition,
				1);
		// ����2�������õ������ٷֱȷ���
		processPCTPricevalue(objCalcFeeParameter, 
				hmFeeResultProcessed, 
				objFPCondition,
				2);	
		processPCTPricevalue(objCalcFeeParameter, 
				hmFeeResultProcessed, 
				objFPCondition,
				3);			
		// ������С���á��෴���õ�
		processOthers(hmFeeResultProcessed);
		
		return hmFeeResultProcessed;
	}
	
	/**
	 * ����۸�ֵ
	 * @param hmFeeResult
	 * @param objCalcFeeParameter
	 * @return
	 * @throws Exception
	 */
	protected HashMap<String,FeeCalculateResult> processPricevalue(HashMap<String,FeeCalculateResult> hmFeeResult, 
			FeeCalculateParameter objCalcFeeParameter, 
			FreightpriceCondition objFPCondition) throws Exception {
		if (hmFeeResult == null && hmFeeResult.size() < 1)
			return null;
		Iterator<FeeCalculateResult> objFeeResults = hmFeeResult.values().iterator();
		FeeCalculateResult objFreightFeeResult = hmFeeResult.get(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT);
		HashMap<String,FeeCalculateResult> hmFeeResultProcessed = new HashMap<String,FeeCalculateResult>();
		// �����˷ѵĻ���
		m_hmCurrencyrate.put(objFreightFeeResult.getCkcode(), objFreightFeeResult.getCurrencyrate());
		// ���ȴ���λ���Ͳ�Ϊ�ٷֱȻ����Ϊ�ٵ��˷ѵİٷֱȷ���
		while(objFeeResults.hasNext()) {
			FeeCalculateResult objClacFeeResult = objFeeResults.next();
			FeeCalculateResult objCopyClacFeeResult = objClacFeeResult.copy();
			
			String strUtcode = objClacFeeResult.getUtcode();
			String strFkcode = objClacFeeResult.getFkcode();
			String strCkcode = objClacFeeResult.getCkcode();
			String strEstcode = objClacFeeResult.getEstcode();
			String strCarryweight = objClacFeeResult.getCarryweigh();
			// �۸�����������������ͣ�ҵ��δ���������������򲻼Ʒ�
			if (!StringUtility.isNull(strEstcode) && 
					!SpecialtypeDemand.isExistsSpecialtype(objCalcFeeParameter.getSpecialtypeRecords(),
							strEstcode))
				continue;
			// ��û���
			String strCurrencyrate = getCurrencyRate(objFPCondition, strCkcode);
			objCopyClacFeeResult.setCurrencyrate(strCurrencyrate);
			if (new BigDecimal(strCurrencyrate).compareTo(new BigDecimal("0")) == 0) {
				s_objLogger.warning("����Ϊ" + strCkcode + "���ʲ���Ϊ0���۸��Ϊ:" + objClacFeeResult.getEpcode());				
				strCurrencyrate = "0";
			}
			BigDecimal objPricevalue = new BigDecimal(objClacFeeResult.getPricevalue());
			// ���ٴ����ٵ��˷�
			if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) { 
				hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
				continue;
			}
			if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_KG) ||
					strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_G)) {
				// ������
				String strChargeweight = objCalcFeeParameter.getChargeWeight();
				if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_G))
					strChargeweight = new BigDecimal(strChargeweight).multiply(new BigDecimal("1000")).toString();
				// ��λ����
				if (!StringUtility.isNull(strCarryweight) && 
						new BigDecimal(strCarryweight).compareTo(new BigDecimal("0")) > 0) {
					BigDecimal objChargeweight = new BigDecimal(strChargeweight);
					BigDecimal objCarriedWeight = objChargeweight.divide(new BigDecimal(strCarryweight), 0, 2).multiply(new BigDecimal(strCarryweight));
					strChargeweight = objCarriedWeight.toString();
				}
				
				objPricevalue = new BigDecimal(strChargeweight).multiply(objPricevalue);
				objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
				objCopyClacFeeResult.setUnitnumber(strChargeweight);
				// �и��˷Ѱ�ʵ�ؼ���
				/*
				if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_HKFREIGHT)) {
					objPricevalue = new BigDecimal(objClacFeeResult.getPricevalue());
					String strGrossweight = objCalcFeeParameter.getGrossWeight();
					objPricevalue = new BigDecimal(strGrossweight).multiply(objPricevalue);
					objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
					objCopyClacFeeResult.setUnitnumber(strGrossweight);					
				}*/
				hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
				
			} else if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_GW)) {
				// ��ʵ�ؼ�������и��˷�
				objPricevalue = new BigDecimal(objClacFeeResult.getPricevalue());
				String strGrossweight = objCalcFeeParameter.getGrossWeight();
				objPricevalue = new BigDecimal(strGrossweight).multiply(objPricevalue);
				objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
				objCopyClacFeeResult.setUnitnumber(strGrossweight);					
				
				hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
			}else if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_BILL)) {
			    // ��Ʊ�Ʒ�
				String strBillcounts = objCalcFeeParameter.getBillcounts();
				if (StringUtility.isNull(strBillcounts))
					strBillcounts = "1";
				objPricevalue = new BigDecimal(strBillcounts).multiply(objPricevalue);
				objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
				objCopyClacFeeResult.setUnitnumber(strBillcounts);				
				hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
			} else if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_PIECE)) {
			    // �����Ʒ�
				String strPieces = objCalcFeeParameter.getPieces();
				objPricevalue = new BigDecimal(strPieces).multiply(objPricevalue);
				objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
				objCopyClacFeeResult.setUnitnumber(strPieces);
				hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
			} else if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_PERCENTAGE)) {
				//����ٷֱ��һ���Ϊ�˷ѵ��ӷ���Ŀ
				ArrayList<String> alBaseFkcode = objClacFeeResult.getBaseFkcode();
				if (alBaseFkcode != null && alBaseFkcode.size() == 1 &&
						alBaseFkcode.get(0).equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) {
					String strFreightvalue = objFreightFeeResult.getPricevalue();
					objPricevalue = objPricevalue.multiply(new BigDecimal(strFreightvalue)).divide(new BigDecimal(1),
							2, 4);
					// �˷Ѻ��ӷѵĻ���
					/*
					if (StringUtility.isNull(strCurrencyrate) || 
							new BigDecimal(strCurrencyrate).compareTo(new BigDecimal("0")) == 0) {
						s_objLogger.warning("���ʲ���Ϊ0���۸��Ϊ:" + objFreightFeeResult.getEpcode());
					}
					*/
					BigDecimal objCurrencyRelative = new BigDecimal(objFreightFeeResult.getCurrencyrate()).divide(new BigDecimal(strCurrencyrate), 2, 4);
					objPricevalue = objPricevalue.multiply(objCurrencyRelative);
					
					objCopyClacFeeResult.setUnitnumber(strFreightvalue);
					objCopyClacFeeResult.setPricevalue(objPricevalue.toString());
					hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
				}
				else
					hmFeeResultProcessed.put(objClacFeeResult.getFkcode(), objCopyClacFeeResult);
			}
		}
		return hmFeeResultProcessed;
	}
	
	/**
	 * ������Ѳ�Ϊ�ٵ��˷ѵİٷֱȷ���
	 * @param hmFeeResult
	 * @param objCalcFeeParameter
	 * @return
	 * @throws Exception
	 */
	protected void processPCTPricevalue(FeeCalculateParameter objCalcFeeParameter,
			HashMap<String,FeeCalculateResult> hmProcessFeeResult,
			FreightpriceCondition objFPCondition,
			int iProcessMoreBaseFee) throws Exception {
		if (hmProcessFeeResult == null && hmProcessFeeResult.size() < 1)
			return;
		Iterator<FeeCalculateResult> objFeeResults = hmProcessFeeResult.values().iterator();
		// ����ٷֱȷ���
		while(objFeeResults.hasNext()) {
			FeeCalculateResult objClacFeeResult = objFeeResults.next();
			
			String strUtcode = objClacFeeResult.getUtcode();
			BigDecimal objPricevalue = new BigDecimal(objClacFeeResult.getPricevalue());
			if (strUtcode.equals(IFeeCalculateBasicData.UNITTYPE_PERCENTAGE)) {
				// �ٷֱ��һ���Ϊ�˷ѵ��ӷ���Ŀ�Ѿ�����
				String strCkcode = objClacFeeResult.getCkcode();
				// ��û���
				String strCurrencyrate = getCurrencyRate(objFPCondition, strCkcode);
				ArrayList<String> alBaseFkcode = objClacFeeResult.getBaseFkcode();
				if (alBaseFkcode != null && alBaseFkcode.size() == 1 &&
						alBaseFkcode.get(0).equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT))
					continue;
				// ����������2������ʱ������3������(����3��)�ķ���
				if (alBaseFkcode != null && iProcessMoreBaseFee < 3 && alBaseFkcode.size() != iProcessMoreBaseFee)
					continue;
				// 3�����ϵķ��ò��ٴ�������������
				if (alBaseFkcode != null && iProcessMoreBaseFee >= 3 && alBaseFkcode.size() < iProcessMoreBaseFee)
					continue;				
				BigDecimal objBasepricevalue = new BigDecimal("0");
				for (int i = 0; i < alBaseFkcode.size(); i++) {
					// �걨��ֵ
					TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(alBaseFkcode.get(i));
					if (!hmProcessFeeResult.containsKey(alBaseFkcode.get(i)) &&
							!"Y".equals(objTdiFeekind.getFkDeclarevaluesign())) {
						continue;
					}
					FeeCalculateResult objBaseFeeResult = null;
					if (hmProcessFeeResult.containsKey(alBaseFkcode.get(i))) {
						objBaseFeeResult = hmProcessFeeResult.get(alBaseFkcode.get(i));
					} else if ("Y".equals(objTdiFeekind.getFkDeclarevaluesign())) {
						objBaseFeeResult = new FeeCalculateResult();
						objBaseFeeResult.setMinimumvalue("0");
						objBaseFeeResult.setMaxvalue("0");
						// ����걨��ֵ�ܶ��Լ�����
						objBaseFeeResult.setCurrencyrate(objCalcFeeParameter.getDeclareCurrencyRate());
						objBaseFeeResult.setPricevalue(objCalcFeeParameter.getTotalDeclarevalue());
					}
					if (objBaseFeeResult == null) continue;
					
					BigDecimal objBaseMinimumvalue = new BigDecimal(objBaseFeeResult.getMinimumvalue());
					BigDecimal objBasePricevalue = new BigDecimal(objBaseFeeResult.getPricevalue());
					// ��Сֵ
					if (objBasePricevalue.compareTo(objBaseMinimumvalue) < 0)
						objBasePricevalue = objBaseMinimumvalue;
					// ���ֵ
					if (!StringUtility.isNull(objBaseFeeResult.getMaxvalue())) {
						BigDecimal objBaseMaxvalue = new BigDecimal(objBaseFeeResult.getMaxvalue());
						if (objBaseMaxvalue.compareTo(new BigDecimal("0")) > 0 && 
								objBasePricevalue.compareTo(objBaseMaxvalue) > 0)
						objBasePricevalue = objBaseMaxvalue;
					}
					// ���ʱ�
					BigDecimal objCurrencyRelative = new BigDecimal(objBaseFeeResult.getCurrencyrate()).divide(new BigDecimal(strCurrencyrate), 2, 4);
					BigDecimal objTPricevalue = objBasePricevalue.multiply(objCurrencyRelative);
					objBasepricevalue = objBasepricevalue.add(objTPricevalue);
				}
				objClacFeeResult.setCurrencyrate(strCurrencyrate);
				objClacFeeResult.setUnitnumber(objBasepricevalue.toString());
				objPricevalue = objPricevalue.multiply(objBasepricevalue).divide(new BigDecimal("1"), 2, 4);
				objClacFeeResult.setPricevalue(objPricevalue.toString());
				hmProcessFeeResult.put(objClacFeeResult.getFkcode(), objClacFeeResult);
			}
		}
	}
	
	private String getCurrencyRate(FreightpriceCondition objFPCondition, 
			String strCkcode) throws Exception {
		String strCurrencyrate = "0";
		if (m_hmCurrencyrate.containsKey(strCkcode))
			strCurrencyrate = m_hmCurrencyrate.get(strCkcode);
		else {
			Currency objCurrency = new Currency();
			String strPkcode = objFPCondition.getPkcode();
			if (StringUtility.isNull(strPkcode))
				strPkcode = "A";
			strCurrencyrate = objCurrency.getCurrencyrate(objFPCondition.getCocode(), 
					strPkcode,
					objFPCondition.getEpstartdate(), 
					objFPCondition.getEecode(),
					strCkcode);
			m_hmCurrencyrate.put(strCkcode, strCurrencyrate);
		}
		return strCurrencyrate;
	}
	
	protected void processOthers(HashMap<String,FeeCalculateResult> hmFeeResultProcessed) {
		if (hmFeeResultProcessed == null && hmFeeResultProcessed.size() < 1)
			return;
		Iterator<FeeCalculateResult> objFeeResults = hmFeeResultProcessed.values().iterator();
		// ����ٷֱȷ���
		while(objFeeResults.hasNext()) {
			FeeCalculateResult objClacFeeResult = objFeeResults.next();
			
			//String strCkcode = objClacFeeResult.getCkcode();
			String strFkcode = objClacFeeResult.getFkcode();
			String strReversesign = objClacFeeResult.getReversesign();
			String strBasevalue = objClacFeeResult.getBasevalue();
			BigDecimal objMinimumvalue = new BigDecimal(objClacFeeResult.getMinimumvalue());
			BigDecimal objPricevalue = new BigDecimal(objClacFeeResult.getPricevalue());
			// ���ٴ����ٵ��˷�
			if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) continue;
			// ��Сֵ
			if (objPricevalue.compareTo(new BigDecimal("0")) > 0 && 
					objPricevalue.compareTo(objMinimumvalue) < 0) {
				objPricevalue = objMinimumvalue;
				if (StringUtility.isNull(objClacFeeResult.getUnitnumber()) || 
						new BigDecimal(objClacFeeResult.getUnitnumber()).compareTo(new BigDecimal("0")) == 0)
					objClacFeeResult.setUnitnumber("1");
			}
			// ���ֵ
			if (!StringUtility.isNull(objClacFeeResult.getMaxvalue())) {
				BigDecimal objMaxvalue = new BigDecimal(objClacFeeResult.getMaxvalue());
				if (objMaxvalue.compareTo(new BigDecimal("0")) > 0 && 
						objPricevalue.compareTo(objMaxvalue) > 0)
					objPricevalue = objMaxvalue;
			}			
			// ����
			if (!StringUtility.isNull(strBasevalue)) {
				objPricevalue = new BigDecimal(strBasevalue).add(objPricevalue);
			}
			// �������
			if (strReversesign.equals("Y")) {
				objPricevalue = objPricevalue.multiply(new BigDecimal("-1"));
			}
			objClacFeeResult.setPricevalue(objPricevalue.toString());
		}
	}
}
