package kyle.leis.es.businessrule.productrule.tp;

import java.util.ArrayList;

import kyle.leis.es.businessrule.businessrules.tp.AModifyBusinessStatusTrans;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDate;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyPRStatusTrans extends AModifyBusinessStatusTrans  {
	
	public ArrayList<RuleCheckReturn> checkBRDateConflict(String strBrid, 
			String strSscode) throws Exception {
		ARuleDate objRuleDate = new ProductruleDate();
		
		ProductruleColumns objProductruleColumns = ProductruleDemand.load(strBrid);
		objProductruleColumns.setSssscode(strSscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objProductruleColumns);
		
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyProductruleDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
