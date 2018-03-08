package kyle.leis.es.price.pricegroup.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.es.price.pricegroup.dax.LoadCustomergroupResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCustomerPriceDateTrans extends AModifyPriceDateTrans {

	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strEpcode = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strEpcode)) return;
		
		LoadCustomergroupResult objLCPGResult = CustomerPricegroupDemand.load(strEpcode);
		SaveCustomerPricegroupTrans objSCPGTrans = new SaveCustomerPricegroupTrans();
		objSCPGTrans.setConflictParam(objLCPGResult, 
				"0",
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSCPGTrans.transaction(objSession);			
	}
}
