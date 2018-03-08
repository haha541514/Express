package kyle.leis.es.businessrule.corweightrule.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.businessrules.tp.AModifyBRDateTrans;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDemand;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCWRuleDateTrans extends AModifyBRDateTrans {
	
	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strBrid = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strBrid)) return;
		
		CorweightruleColumns objCWRColumns = CorWeightRuleDemand.load(strBrid);
		SaveCorWeightRuleTrans objSCWRTrans = new SaveCorWeightRuleTrans();
		objSCWRTrans.setConflictParam(objCWRColumns, 
				"0", 
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSCWRTrans.transaction(objSession);			
	}

}
