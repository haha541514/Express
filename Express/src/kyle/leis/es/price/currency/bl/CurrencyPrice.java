package kyle.leis.es.price.currency.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.tp.ModifyCYPriceStatusTrans;
import kyle.leis.es.price.currency.tp.SaveCurrencyTransaction;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class CurrencyPrice extends AExpressPrice  {
	public SavedResult save(CurrencyColumns objCurrencyColumns,
			List listCurrencyValue,
			String strOperId,
			boolean isIgnoreNotice) throws Exception {
		SaveCurrencyTransaction objSCUTrans = new SaveCurrencyTransaction();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSCUTrans.setParam(objCurrencyColumns, 
				listCurrencyValue,
				strOperId, 
				true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice)
			objSCUTrans.execute();
		// ·µ»ØÖµ
		String strEpcode = objSCUTrans.getEpcode();
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(strEpcode);
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;		
	}
	
	
	@Override
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyCYPriceStatusTrans();
	}

}
