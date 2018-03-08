package kyle.leis.eo.billing.calculate.adjustive.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.adjustive.dax.AdjustiveDemand;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceCondition;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueCondition;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDemand;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceValueDemand;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;

public class AdjustiveSearch {
	
	public List<AdjustivepricevalueColumns> search(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// 查询价格表
		AdjustivepriceCondition objAPCondition = AdjustiveDemand.transferToCondition(objFPCondition);
		objAPCondition.setUseCacheSign(true);
		List listResults = AdjustivePriceDemand.query(objAPCondition);
		if (listResults == null || listResults.size() < 1)
			return null;
		// 获得价格值
		AdjustivepriceColumns objAPColumns = (AdjustivepriceColumns)listResults.get(0);
		AdjustivepricevalueCondition objAPVCondition = new AdjustivepricevalueCondition();
		objAPVCondition.setEpcode(objAPColumns.getApepcode());
		objAPVCondition.setUseCacheSign(true);
		List listValueResults = AdjustivePriceValueDemand.query(objAPVCondition);
		if (listValueResults == null || listValueResults.size() < 1)
			return null;
		List<AdjustivepricevalueColumns> listPricevalueColumns = new ArrayList<AdjustivepricevalueColumns>();
		for (int i = 0; i < listValueResults.size(); i++) {
			AdjustivepricevalueColumns objAPVColumns = (AdjustivepricevalueColumns)listValueResults.get(i);
			// 是否满足计费的条件
			if (isExistsCalculate(objAPVColumns, objFPCondition, objCalcFeeParameter))
				listPricevalueColumns.add(objAPVColumns);
		}
		return listPricevalueColumns;
	}
	
	private boolean isExistsCalculate(AdjustivepricevalueColumns objAPVColumns,
			FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) {
		String strCtcode = objAPVColumns.getCtctcode();
		String strOriginDtcode = objAPVColumns.getDtdtcode();
		String strWeekDay = objAPVColumns.getApvapvweekday();
		
		if (!strCtcode.equals("A") && !strCtcode.equals(objFPCondition.getCtcode()))
			return false;
		if (!strOriginDtcode.equals(objFPCondition.getDtcode()))
			return false;
		String strCurrentWeekDay = DateUtility.getChineseWeekDayName(DateFormatUtility.getStandardSysdate());
		if (!StringUtility.isNull(strWeekDay) && !strWeekDay.equals("零"))
			if (!strCurrentWeekDay.equals(strWeekDay))
				return false;
		
		return true;
	}
	
	
}
