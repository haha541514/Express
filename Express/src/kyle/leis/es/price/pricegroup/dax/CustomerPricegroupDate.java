package kyle.leis.es.price.pricegroup.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class CustomerPricegroupDate extends ARuleDate {

	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objSavingColumns;
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		
		objCPGCondition.setCocode(objCPGColumns.getCococode());
		objCPGCondition.setEecode(objCPGColumns.getEeeecode());
		objCPGCondition.setEpkcode(objCPGColumns.getEpkepkcode());
		objCPGCondition.setPgkcode(objCPGColumns.getPgkpgkcode());
		objCPGCondition.setPkcode(objCPGColumns.getPkpkcode());
		objCPGCondition.setPscode(objCPGColumns.getPspscode());
		
		return objCPGCondition;
	}

	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objQueryColumns;
		return objCPGColumns.getEpependdate();
	}

	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objQueryColumns;
		return objCPGColumns.getEpepstartdate();
	}

	protected String getRulecode(GeneralColumns objQueryColumns) {
		CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objQueryColumns;
		return objCPGColumns.getCpgepcode();
	}
	
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		CustomerpricegroupCondition objCPGCondition = (CustomerpricegroupCondition)objCondition;
		return CustomerPricegroupDemand.query(objCPGCondition);
	}

}
