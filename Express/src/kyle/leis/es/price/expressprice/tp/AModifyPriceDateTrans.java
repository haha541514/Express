package kyle.leis.es.price.expressprice.tp;

import net.sf.hibernate.Session;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.ruledate.AModifyRuleDateTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public abstract class AModifyPriceDateTrans extends AModifyRuleDateTrans {
	protected void eliminate(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		ModifyPriceTrans objModifyEPTrans = new ModifyPriceTrans();
		objModifyEPTrans.setModifyStatusParam(objRuleCheckReturn.getRulecode(), 
				IExpressPriceBasicData.PRICE_STATUS_ELIMINATE, 
				"0");
		objModifyEPTrans.transaction(objSession);
	}

	protected void modifyDate(RuleCheckReturn objRuleCheckReturn,
			Session objSession) throws Exception {
		ModifyPriceTrans objModifyEPTrans = new ModifyPriceTrans();
		objModifyEPTrans.setModifyDateParam(objRuleCheckReturn.getRulecode(), 
				objRuleCheckReturn.getNewStartDate(), 
				objRuleCheckReturn.getNewEndDate(), 
				"0");
		objModifyEPTrans.transaction(objSession);		
	}
}
