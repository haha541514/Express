package kyle.leis.es.businessrule.weightrule.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class WeightruleDate extends ARuleDate {

	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		WeightruleColumns objWeightruleColumns = (WeightruleColumns)objSavingColumns;
		
		WeightruleCondition objWeightruleCondition = new WeightruleCondition();
	    objWeightruleCondition.setSscode(objWeightruleColumns.getSssscode());
	    // need changed
	    objWeightruleCondition.setWrkid(objWeightruleColumns.getWrkwrkid());
	    
		return objWeightruleCondition;
	}

	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		WeightruleColumns objWeightruleColumns = (WeightruleColumns)objQueryColumns;
		return objWeightruleColumns.getBrbrenddate();
	}
	
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		WeightruleColumns objWeightruleColumns = (WeightruleColumns)objQueryColumns;
		return objWeightruleColumns.getBrbrstartdate();
	}
	
	protected String getRulecode(GeneralColumns objQueryColumns) {
		WeightruleColumns objWeightruleColumns = (WeightruleColumns)objQueryColumns;
		return objWeightruleColumns.getWrbrid();
	}
	
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		WeightruleCondition objWeightruleCondition = (WeightruleCondition)objCondition;
		return WeightRuleDemand.query(objWeightruleCondition);
	}
}
