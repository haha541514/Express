package kyle.leis.es.price.expressprice.bl;

import java.util.ArrayList;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public abstract class AExpressPrice {
	public SavedResult modifyStatus(String strEpcode, 
			String strPscode,			
			String strOperId, 
			boolean isIgnoreNotice,
			boolean isCheckDateConflic) throws Exception {
		AModifyPriceStatusTrans objMPSTrans = getModifyPriceStatusTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objMPSTrans.setParam(strEpcode, 
				strPscode, 
				strOperId, 
				isCheckDateConflic);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice) {
			objMPSTrans.execute();
			
			// Ë¢ÐÂ»º³å
			QueryCache objQueryCache = new QueryCache();
			objQueryCache.reset();			
		}
		// ·µ»ØÖµ
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(strEpcode);
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;
	}
	
	protected abstract AModifyPriceStatusTrans getModifyPriceStatusTrans();
}
