package kyle.leis.eo.billing.calculate.freight.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.da.FreightpricevalueColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.price.zone.dax.ZoneDemand;

public class Freight {
	private FreightpriceColumns m_objFPriceColumns;
	private List m_listFreightvalues;
	private int m_iZnvid;
	
	
	/**
	 * 计算运费值
	 * @param objFPCondition
	 * @param objCalcFeeParameter
	 * @return
	 * @throws Exception
	 */
	public FeeCalculateResult calculate(FreightpriceCondition objFPCondition, 
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		// 查找符合条件的价格值
		m_listFreightvalues = getFreightvalues(objFPCondition, objCalcFeeParameter);
		if (m_listFreightvalues == null || m_listFreightvalues.size() < 1)
			return null;
		// 查找重量等级
		String strUtcode = m_objFPriceColumns.getUtutcode();
		String strChargeweight = objCalcFeeParameter.getChargeWeight();
		if (strUtcode.equals("G"))
			strChargeweight = new BigDecimal(strChargeweight).multiply(new BigDecimal("1000")).toString();
		int iLevel = getWeightLevel(strChargeweight);
		if (iLevel < 0) return null;
		// 计费
		String strFreightvalue = calculate(iLevel, strChargeweight);
		// 返回值
		FeeCalculateResult objClacFeeResult = new FeeCalculateResult(); 
		objClacFeeResult.setCkcode(m_objFPriceColumns.getCkckcode());
		objClacFeeResult.setPdcode(m_objFPriceColumns.getPdpdcode());
		objClacFeeResult.setFkcode(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT);
		objClacFeeResult.setMinimumvalue("0");
		objClacFeeResult.setPricevalue(strFreightvalue);
		objClacFeeResult.setRemark("系统自动计费");
		objClacFeeResult.setReversesign("N");
		objClacFeeResult.setUtcode(m_objFPriceColumns.getUtutcode());
		objClacFeeResult.setUnitnumber(objCalcFeeParameter.getChargeWeight());
		objClacFeeResult.setCommissionirate(m_objFPriceColumns.getFpfpcommissionrate());
		objClacFeeResult.setEpcode(m_objFPriceColumns.getFpepcode());
		objClacFeeResult.setEpvid(String.valueOf(iLevel));
		objClacFeeResult.setFreightpriceRemark(m_objFPriceColumns.getEpepremark());
		objClacFeeResult.setFreightpriceColumns(m_objFPriceColumns);
		// 汇率
		Currency objCurrency = new Currency();
		String strCurrencyrate = objCurrency.getCurrencyrate(objOldFPCondition.getCocode(), 
				objOldFPCondition.getPkcode(), 
				objOldFPCondition.getEpstartdate(), 
				objOldFPCondition.getEecode(), 
				m_objFPriceColumns.getCkckcode());
		objClacFeeResult.setCurrencyrate(strCurrencyrate);
		// 换成本位币
		return objClacFeeResult;
	}
	
	/**
	 * 渠道选择，查找重量段的费用值
	 * @param objFPCondition
	 * @param objCalcFeeParameter
	 * @return
	 * @throws Exception
	 */
	public List<FeeCalculateResult> calcForchannelsearch(FreightpriceCondition objFPCondition, 
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// 查找符合条件的价格值
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		
		m_listFreightvalues = getFreightvalues(objFPCondition, objCalcFeeParameter);
		if (m_listFreightvalues == null || m_listFreightvalues.size() < 1)
			return null;
		// 查找重量等级
		String strUtcode = m_objFPriceColumns.getUtutcode();
		String strChargeweight = objCalcFeeParameter.getChargeWeight();
		if (strUtcode.equals("G"))
			strChargeweight = new BigDecimal(strChargeweight).multiply(new BigDecimal("1000")).toString();
		int iLevel = getWeightLevel(strChargeweight);
		if (iLevel < 0) return null;
		// 返回值
		List<FeeCalculateResult> listFCResults = new ArrayList<FeeCalculateResult>();
		// 汇率
		Currency objCurrency = new Currency();
		String strCurrencyrate = objCurrency.getCurrencyrate(objOldFPCondition.getCocode(), 
				"A", 
				objOldFPCondition.getEpstartdate(), 
				objOldFPCondition.getEecode(), 
				m_objFPriceColumns.getCkckcode());		
		// 获得波动重量
		Freightwaveweight objFreightwaveweight = new Freightwaveweight();
		HashMap<String, Integer> hmWaveweight = objFreightwaveweight.getWaveweight(m_listFreightvalues, 
				iLevel);
		hmWaveweight.put(strChargeweight, iLevel);
		
		Iterator<String> objWaveweight = hmWaveweight.keySet().iterator();
		while(objWaveweight.hasNext()) {
            strChargeweight = objWaveweight.next();
			// 计费
			String strFreightvalue = calculate(hmWaveweight.get(strChargeweight), strChargeweight);
			if (new BigDecimal(strFreightvalue).compareTo(new BigDecimal("0")) == 0)
				continue;
			// 返回值
			FeeCalculateResult objClacFeeResult = new FeeCalculateResult();
			objClacFeeResult.setCkcode(m_objFPriceColumns.getCkckcode());
			objClacFeeResult.setFkcode(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT);
			objClacFeeResult.setMinimumvalue("0");
			objClacFeeResult.setPricevalue(strFreightvalue);
			objClacFeeResult.setRemark("系统自动计费");
			objClacFeeResult.setReversesign("N");
			objClacFeeResult.setUtcode(m_objFPriceColumns.getUtutcode());
			
			if (strUtcode.equals("G")) {
				String strKGWeight = new BigDecimal(strChargeweight).divide(new BigDecimal("1000"), 3, 4).toString();
				objClacFeeResult.setUnitnumber(strKGWeight);
				objClacFeeResult.setChargeweight(strKGWeight);
			}
			else {
				objClacFeeResult.setUnitnumber(strChargeweight);
				objClacFeeResult.setChargeweight(strChargeweight);
			}
			
			objClacFeeResult.setCommissionirate(m_objFPriceColumns.getFpfpcommissionrate());
			objClacFeeResult.setFreightpriceRemark(m_objFPriceColumns.getEpepremark());
			objClacFeeResult.setCurrencyrate(strCurrencyrate);
			objClacFeeResult.setFreightpriceColumns(m_objFPriceColumns);
			listFCResults.add(objClacFeeResult);
		}
		// 换成本位币
		return listFCResults;		
	}
	
	/**
	 * 获得分区值id
	 * @return
	 */
	public int getZonevalueId() {
		return m_iZnvid;
	}
	
	/**
	 * 获得运费价格表
	 * @return
	 */
	public FreightpriceColumns getFreightpriceColumns() {
		return m_objFPriceColumns;
	}
	
	private List getFreightvalues(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// 查找价格表
		FreightSearch objFreightSearch = new FreightSearch();
		m_objFPriceColumns = objFreightSearch.search(objFPCondition);
		
		if (m_objFPriceColumns == null) return null;
		// 查找运费价格值
		if (StringUtility.isNull(m_objFPriceColumns.getFpepcode()))
			return null;
		List listFreightvalue = FreightPriceDemand.loadFreightValue(m_objFPriceColumns.getFpepcode());
		if (listFreightvalue == null || listFreightvalue.size() < 1)
			return null;
		// 返回值
		List<FreightpricevalueColumns> objList = new ArrayList<FreightpricevalueColumns>();
		// 查找分区
		m_iZnvid = ZoneDemand.getZnvidByDistrict(objCalcFeeParameter.getDtcode(), 
				objCalcFeeParameter.getPostcode(), 
				m_objFPriceColumns.getZnzncode());
		if (m_iZnvid < 0) return null;
		for (int i = 0; i < listFreightvalue.size(); i++) {
			FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)listFreightvalue.get(i);
			if (objFPValueColumns.getFpvznvid().equals(String.valueOf(m_iZnvid)))
				objList.add(objFPValueColumns);
		}
		return objList;
	}
	
	private int getWeightLevel(String strChargeweight) {
		FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(0);
		BigDecimal objChargeweight = new BigDecimal(strChargeweight);
		BigDecimal objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
		
		if (objChargeweight.compareTo(objWeightgrade) <= 0) {
			return 0;
		}
		objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(m_listFreightvalues.size() - 1);
		objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
		if (objChargeweight.compareTo(objWeightgrade) >= 0) {
			return m_listFreightvalues.size() - 1;
		}
		BigDecimal objNextWeightgrade = new BigDecimal("0");
		FreightpricevalueColumns objNextFPValueColumns;
		for (int i = 0; i < m_listFreightvalues.size() - 1; i++) {
			objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(i);
			objNextFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(i + 1);
			
			objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
			objNextWeightgrade = new BigDecimal(objNextFPValueColumns.getFpvfpvweightgrade());
			if (objChargeweight.compareTo(objWeightgrade) == 0)
				return i;
			
			if (objChargeweight.compareTo(objNextWeightgrade) == 0)
				return i + 1;		
			
			if (objChargeweight.compareTo(objWeightgrade) > 0 &&
					objChargeweight.compareTo(objNextWeightgrade) < 0)
				return i;
		}
		return -1;
	}
	
	private String calculate(int iLevel, 
			String strChargeweight) throws Exception {
		FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(iLevel);
		// 重量类别
		String strFvtcode = objFPValueColumns.getFvtfvtcode();
		if (strFvtcode.equals(IFeeCalculateBasicData.FVT_FIRSTWEIGHT))
			return calcFirstweightfee(iLevel, strChargeweight);
		else if (strFvtcode.equals(IFeeCalculateBasicData.FVT_UNITWEIGHT))
			return calcUnitweightfee(iLevel, strChargeweight);
		else if (strFvtcode.equals(IFeeCalculateBasicData.FVT_CONTINUEWEIGHT))
			return calcContinueweightfee(iLevel, strChargeweight);
		else if (strFvtcode.equals(IFeeCalculateBasicData.FVT_INCREASED_CONTINUEWEIGHT))
			return calcIncreasedContinuefee(iLevel, strChargeweight);
		return "";
	}
	
	private String calcFirstweightfee(int iLevel, 
			String strChargeweight) {
		FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(iLevel);
		BigDecimal objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
		if (objWeightgrade.compareTo(new BigDecimal(strChargeweight)) <= 0 )
			return objFPValueColumns.getFpvfpvpricevalue();
		return "0";
	}
	
	private String calcUnitweightfee(int iLevel, 
			String strChargeweight) {
		FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(iLevel);
		BigDecimal objWeightunit = new BigDecimal(objFPValueColumns.getFpvfpvweightunit());
		BigDecimal objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
		if (objWeightgrade.compareTo(new BigDecimal("0")) > 0) {
			BigDecimal objPricevalue = new BigDecimal(objFPValueColumns.getFpvfpvpricevalue());
			objPricevalue = new BigDecimal(strChargeweight).divide(objWeightunit, 0, 2).multiply(objPricevalue);
			return objPricevalue.toString();
		}
		return "0";
	}
	
	private String calcContinueweightfee(int iLevel, 
			String strChargeweight) {
		BigDecimal objFirstweightprice = new BigDecimal("0");
		// 查找最近的首重价格
		for (int i = iLevel - 1; i >= 0; i--) {
			FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(i);
			if (objFPValueColumns.getFvtfvtcode().equals(IFeeCalculateBasicData.FVT_FIRSTWEIGHT)) {
				objFirstweightprice = new BigDecimal(objFPValueColumns.getFpvfpvpricevalue());
				break;
			}
		}
		if (objFirstweightprice.compareTo(new BigDecimal("0")) <= 0)
			return "0";
		// 计算续重价格值
		FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(iLevel);
		BigDecimal objPricevalue = new BigDecimal(objFPValueColumns.getFpvfpvpricevalue());
		BigDecimal objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
		BigDecimal objWeightunit = new BigDecimal(objFPValueColumns.getFpvfpvweightunit());
		BigDecimal objChargeweight = new BigDecimal(strChargeweight).add(objWeightgrade.multiply(new BigDecimal("-1")));
		
		objChargeweight = objChargeweight.add(objWeightunit).divide(objWeightunit, 0, 2);
		objPricevalue = objChargeweight.multiply(objPricevalue).add(objFirstweightprice);
		
		return objPricevalue.toString();
	}
	
	private String calcIncreasedContinuefee(int iLevel, 
			String strChargeweight) {
		BigDecimal objChargeweight = new BigDecimal(strChargeweight);
		BigDecimal objFirstweightprice = new BigDecimal("0");
		BigDecimal objSumPricevalue = new BigDecimal("0");
		for (int i = iLevel; i >= 0; i--) {
			FreightpricevalueColumns objFPValueColumns = (FreightpricevalueColumns)m_listFreightvalues.get(i);
			String strFvtcode = objFPValueColumns.getFvtfvtcode();
			// 递增续重
			if (strFvtcode.equals(IFeeCalculateBasicData.FVT_INCREASED_CONTINUEWEIGHT)) {
				BigDecimal objWeightunit = new BigDecimal(objFPValueColumns.getFpvfpvweightunit());
				BigDecimal objWeightgrade = new BigDecimal(objFPValueColumns.getFpvfpvweightgrade());
				BigDecimal objPricevalue = new BigDecimal(objFPValueColumns.getFpvfpvpricevalue());
				if (i == iLevel) {
					objChargeweight = objChargeweight.add(objWeightgrade.multiply(new BigDecimal("-1")));
					objChargeweight = objChargeweight.add(objWeightunit);
				} else {
					objChargeweight = objChargeweight.add(objWeightgrade.multiply(new BigDecimal("-1")));
				}
				objSumPricevalue = objSumPricevalue.add(objChargeweight.divide(objWeightunit, 0, 2).multiply(objPricevalue));
				objChargeweight = objWeightgrade;
			}
			if (strFvtcode.equals(IFeeCalculateBasicData.FVT_FIRSTWEIGHT)) {
				objFirstweightprice = new BigDecimal(objFPValueColumns.getFpvfpvpricevalue());
				break;
			}
		}
		objSumPricevalue = objSumPricevalue.add(objFirstweightprice);
		return objSumPricevalue.toString();
	}
}
