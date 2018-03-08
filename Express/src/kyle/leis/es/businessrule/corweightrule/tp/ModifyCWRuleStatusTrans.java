package kyle.leis.es.businessrule.corweightrule.tp;

import java.util.ArrayList;

import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDate;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDemand;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCWRuleStatusTrans extends AModifyBusinessStatusTrans {
	
	public ArrayList<RuleCheckReturn> checkBRDateConflict(String strBrid, 
			String strSscode) throws Exception {
		ARuleDate objRuleDate = new CorWeightRuleDate();
		
		CorweightruleColumns objCorWRColumns = CorWeightRuleDemand.load(strBrid);
		objCorWRColumns.setSssscode(strSscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objCorWRColumns);
		
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyCWRuleDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
