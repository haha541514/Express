package kyle.leis.es.businessrule.businessrules.bl;

import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public abstract class ABusinessrule {
	public SavedResult modifyStatus(String strBrid, 
			String strSscode,			
			String strOperId, 
			boolean isIgnoreNotice,
			boolean isCheckDateConflic) throws Exception {
		AModifyBusinessStatusTrans objMBSTrans = getModifyBusinessStatusTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objMBSTrans.setParam(strBrid, 
				strSscode, 
				strOperId, 
				isCheckDateConflic);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || 
				isIgnoreNotice) {
			objMBSTrans.execute();
			
			// Ë¢ÐÂ»º³å
			QueryCache objQueryCache = new QueryCache();
			objQueryCache.reset();				
		}
		// ·µ»ØÖµ
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(strBrid);
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;
	}
	
	protected abstract AModifyBusinessStatusTrans getModifyBusinessStatusTrans();
	
}
