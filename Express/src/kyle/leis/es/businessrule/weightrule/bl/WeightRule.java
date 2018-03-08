package kyle.leis.es.businessrule.weightrule.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.tp.ModifyWRStatusTrans;
import kyle.leis.es.businessrule.weightrule.tp.SaveWeightRuleTrans;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class WeightRule extends ABusinessrule {

	public SavedResult save(WeightruleColumns objWeightruleColumns,
			List listWRVColumns,
			List listVWRVColumns,
			List listCWRVColumns,
			String strOperId,
			boolean isIgnoreNotice) throws Exception {
		SaveWeightRuleTrans objSaveWeightRuleTrans = new SaveWeightRuleTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveWeightRuleTrans.setParam(objWeightruleColumns, 
				listWRVColumns, 
				listVWRVColumns,
				listCWRVColumns,
				strOperId, 
				true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice)
			objSaveWeightRuleTrans.execute();
		// ·µ»ØÖµ
		Long lBrid = objSaveWeightRuleTrans.getNewBrid();
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(String.valueOf(lBrid));
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;			
	}
	
	protected AModifyBusinessStatusTrans getModifyBusinessStatusTrans() {
		return new ModifyWRStatusTrans();
	}

}
