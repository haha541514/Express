package kyle.leis.es.price.currency.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class CurrencyPriceDate extends ARuleDate  {

	@Override
	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		// TODO Auto-generated method stub
		CurrencyColumns objCurrencyColumns = (CurrencyColumns)objSavingColumns;
		CurrencyCondition objCurrencyCondition = new CurrencyCondition();
		
		objCurrencyCondition.setCocode(objCurrencyColumns.getCococode());
		objCurrencyCondition.setPgcode(objCurrencyColumns.getPgpgcode());
		objCurrencyCondition.setPkcode(objCurrencyColumns.getPkpkcode());
		
		objCurrencyCondition.setPscode(objCurrencyColumns.getPspscode());
		objCurrencyCondition.setEecode(objCurrencyColumns.getEeeecode());
		
		return objCurrencyCondition;
	}

	@Override
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		CurrencyColumns objCurrencyColumns = (CurrencyColumns)objQueryColumns;
		return objCurrencyColumns.getEpependdate();
	}

	@Override
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		CurrencyColumns objCurrencyColumns = (CurrencyColumns)objQueryColumns;
		return objCurrencyColumns.getEpepstartdate();
	}

	@Override
	protected String getRulecode(GeneralColumns objQueryColumns) {
		CurrencyColumns objCurrencyColumns = (CurrencyColumns)objQueryColumns;
		return objCurrencyColumns.getCuepcode();
	}

	@Override
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		if (objCondition instanceof CurrencyCondition) {
			return CurrencyPriceDemand.query((CurrencyCondition)objCondition);
		}
		return null;
	}

}
