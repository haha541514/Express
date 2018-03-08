package kyle.leis.es.businessrule.productrule.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.da.ProductruleCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class ProductruleDate extends ARuleDate {
	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		ProductruleColumns objProductruleColumns = (ProductruleColumns)objSavingColumns;
		
		ProductruleCondition objProductruleCondition = new ProductruleCondition();
		objProductruleCondition.setPkcode(objProductruleColumns.getPkpkcode());
		objProductruleCondition.setSscode(objProductruleColumns.getSssscode());
		
		return objProductruleCondition;
	}
	
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		ProductruleColumns objProductruleColumns = (ProductruleColumns)objQueryColumns;
		return objProductruleColumns.getBrbrenddate();
	}
	
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		ProductruleColumns objProductruleColumns = (ProductruleColumns)objQueryColumns;
		return objProductruleColumns.getBrbrstartdate();
	}
	
	protected String getRulecode(GeneralColumns objQueryColumns) {
		ProductruleColumns objProductruleColumns = (ProductruleColumns)objQueryColumns;
		return objProductruleColumns.getPrbrid();
	}
	
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		ProductruleCondition objProductruleCondition = (ProductruleCondition)objCondition;
		return ProductruleDemand.query(objProductruleCondition);
	}

}
