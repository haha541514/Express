package kyle.leis.es.businessrule.corweightrule.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class CorWeightRuleDate extends ARuleDate {
	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		CorweightruleColumns objCorweightruleColumns = (CorweightruleColumns)objSavingColumns;
		CorweightruleCondition objCorWRCondition = new CorweightruleCondition();
		
		objCorWRCondition.setSscode(objCorweightruleColumns.getSssscode());
		objCorWRCondition.setChncode(objCorweightruleColumns.getChnchncode());
		objCorWRCondition.setCocode(objCorweightruleColumns.getCococode());
		objCorWRCondition.setWrkname(objCorweightruleColumns.getWrkwrkname());
		
		return objCorWRCondition;
	}
	
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		CorweightruleColumns objCorweightruleColumns = (CorweightruleColumns)objQueryColumns;
		return objCorweightruleColumns.getBrbrenddate();
	}
	
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		CorweightruleColumns objCorweightruleColumns = (CorweightruleColumns)objQueryColumns;
		return objCorweightruleColumns.getBrbrstartdate();
	}
	
	protected String getRulecode(GeneralColumns objQueryColumns) {
		CorweightruleColumns objCorweightruleColumns = (CorweightruleColumns)objQueryColumns;
		return objCorweightruleColumns.getCwrbrid();
	}
	
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		CorweightruleCondition objCorWRCondition = (CorweightruleCondition)objCondition;
		return CorWeightRuleDemand.query(objCorWRCondition);
	}

}
