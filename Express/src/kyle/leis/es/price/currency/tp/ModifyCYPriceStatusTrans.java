package kyle.leis.es.price.currency.tp;

import java.util.ArrayList;

import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.dax.CurrencyPriceDate;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCYPriceStatusTrans extends AModifyPriceStatusTrans {

	@Override
	public ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode, 
			String strPscode) throws Exception {
		ARuleDate objRuleDate = new CurrencyPriceDate();
		CurrencyColumns objCurrencyColumns = CurrencyPriceDemand.loadCurrency(strEpcode);
		
		objCurrencyColumns.setPspscode(strPscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objCurrencyColumns);
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyCYPriceDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
