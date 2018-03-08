package kyle.leis.es.price.pricegroup.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.tp.ModifyCustomerPriceStatusTrans;
import kyle.leis.es.price.pricegroup.tp.SaveCustomerPricegroupTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class CustomerPricegroup extends AExpressPrice {

	public SavedResult save(CustomerpricegroupColumns objCPGColumns,
			List listCPGValueColumns,
			String strOperId, 
			boolean isIgnoreNotice) throws Exception {
		SaveCustomerPricegroupTrans objSaveCPGTrans = new SaveCustomerPricegroupTrans();
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objSaveCPGTrans.setParam(objCPGColumns,
				listCPGValueColumns,
				strOperId, 
				true);
		if (alRuleCheckReturn == null || alRuleCheckReturn.size() < 1 || isIgnoreNotice) {
			objSaveCPGTrans.execute();			
		}
		// ·µ»ØÖµ
		String strEpcode = objSaveCPGTrans.getEpcode();
		SavedResult objSavedResult = new SavedResult();	
		objSavedResult.setRulecode(strEpcode);	
		objSavedResult.setRuleCheckList(alRuleCheckReturn);
		
		return objSavedResult;
	}
	
	
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyCustomerPriceStatusTrans();
	}
	
}
