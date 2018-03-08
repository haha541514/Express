package kyle.leis.es.price.pricegroup.tp;

import java.util.ArrayList;

import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDate;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyCustomerPriceStatusTrans extends AModifyPriceStatusTrans {

	public ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode, 
			String strPscode) throws Exception {
		
		ARuleDate objRuleDate = new CustomerPricegroupDate();
		CustomerpricegroupColumns objCPGColumns = CustomerPricegroupDemand.loadCPriceGroup(strEpcode);
		objCPGColumns.setPspscode(strPscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objCPGColumns);
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyCustomerPriceDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}
}
