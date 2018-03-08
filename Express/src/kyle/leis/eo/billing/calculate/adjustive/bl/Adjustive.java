package kyle.leis.eo.billing.calculate.adjustive.bl;

import java.math.BigDecimal;
import java.util.List;

import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateParameter;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;

public class Adjustive {
	
	public FeeCalculateResult calculate(FreightpriceCondition objFPCondition,
			FeeCalculateParameter objCalcFeeParameter) throws Exception {
		// ���Ҽ۸�
		AdjustiveSearch objAdjustiveSearch = new AdjustiveSearch();
		List<AdjustivepricevalueColumns> listResults = objAdjustiveSearch.search(objFPCondition, 
				objCalcFeeParameter);
		if (listResults == null || listResults.size() < 1) return null;
		BigDecimal objPricevalue = new BigDecimal("0");
		String strUtcode = "KG";
		for (int i = 0; i < listResults.size(); i++) {
			AdjustivepricevalueColumns objAPVColumns = listResults.get(i);
			strUtcode = objAPVColumns.getUtutcode();
			objPricevalue = objPricevalue.add(calculate(strUtcode, objAPVColumns.getApvapvpricevalue(), objCalcFeeParameter)); 
		}
		FeeCalculateResult objClacFeeResult = new FeeCalculateResult();
		
		objClacFeeResult.setBasevalue("0");
		objClacFeeResult.setCkcode("RMB");
		objClacFeeResult.setFkcode("A30");
		objClacFeeResult.setMinimumvalue(objPricevalue.toString());
		objClacFeeResult.setPricevalue(objPricevalue.toString());
		objClacFeeResult.setRemark("��������");
		objClacFeeResult.setReversesign("N");
		objClacFeeResult.setUtcode(strUtcode);	
		objClacFeeResult.setCommissionirate("0");
		// ����
		objClacFeeResult.setCurrencyrate("1");		
		
		return objClacFeeResult;
	}
	
	private BigDecimal calculate(String strUtcode, 
			String strPricevalue,
			FeeCalculateParameter objCalcFeeParameter) {
		BigDecimal objPricevalue = new BigDecimal("0");
		if (strUtcode.equals("KG"))
			objPricevalue = new BigDecimal(strPricevalue).multiply(new BigDecimal(objCalcFeeParameter.getChargeWeight()));
		// Ʊ
		else if (strUtcode.equals("BL"))
			objPricevalue = new BigDecimal(strPricevalue);
		// ��
		else if (strUtcode.equals("PCE"))
			objPricevalue = new BigDecimal(strPricevalue).multiply(new BigDecimal(objCalcFeeParameter.getPieces()));
		// ��������
		objPricevalue = objPricevalue.divide(new BigDecimal("1"), 2, 4);
		
		return objPricevalue;
	}
	
}
