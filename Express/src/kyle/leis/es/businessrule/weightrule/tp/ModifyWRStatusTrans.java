package kyle.leis.es.businessrule.weightrule.tp;

import java.util.ArrayList;

import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.businessrule.weightrule.dax.WeightruleDate;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyWRStatusTrans extends AModifyBusinessStatusTrans {
	
	public ArrayList<RuleCheckReturn> checkBRDateConflict(String strBrid, 
			String strSscode) throws Exception {
		ARuleDate objRuleDate = new WeightruleDate();
		
		WeightruleColumns objWeightruleColumns = WeightRuleDemand.loadWeightRule(strBrid);
		objWeightruleColumns.setSssscode(strSscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objWeightruleColumns);
		
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyWeightruleDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
