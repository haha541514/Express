package kyle.leis.es.businessrule.productrule.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.tp.ModifyPRStatusTrans;
import kyle.leis.es.businessrule.productrule.tp.SaveProductruleTrans;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class Productrule extends ABusinessrule {
	
	public SavedResult save(ProductruleColumns objProductruleColumns,
			List listChnPRColumns,
			List listDistrictPRColumns,
			List listCorporationPRColumns,
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SaveProductruleTrans objSaveProductRuleTrans = new SaveProductruleTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveProductRuleTrans.setParam(objProductruleColumns, 
				listChnPRColumns, 
				listDistrictPRColumns,
				listCorporationPRColumns,
				strOperId, 
				true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice) {
			objSaveProductRuleTrans.execute();
			// Ë¢ÐÂ»º³å
			QueryCache objQueryCache = new QueryCache();
			objQueryCache.reset();			
		}
		// ·µ»ØÖµ
		Long lBrid = objSaveProductRuleTrans.getNewBrid();
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(String.valueOf(lBrid));
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;		
	}
	
	
	protected AModifyBusinessStatusTrans getModifyBusinessStatusTrans() {
		return new ModifyPRStatusTrans();
	}

}
