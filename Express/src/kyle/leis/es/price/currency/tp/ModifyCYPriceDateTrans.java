package kyle.leis.es.price.currency.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;
import kyle.leis.es.price.currency.dax.LoadCurrencyResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCYPriceDateTrans extends AModifyPriceDateTrans {
	// 创建新规则
	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strEpcode = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strEpcode)) return;
		
		LoadCurrencyResult objLoadCurrencyResult = CurrencyPriceDemand.load(strEpcode);
		SaveCurrencyTransaction objSCTrans = new SaveCurrencyTransaction();
		objSCTrans.setConflictParam(objLoadCurrencyResult, 
				"0", 
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSCTrans.transaction(objSession);		
	}

}
