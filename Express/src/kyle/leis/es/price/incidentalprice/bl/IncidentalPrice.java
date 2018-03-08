package kyle.leis.es.price.incidentalprice.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.tp.ModifyIncidentalPriceStatusTrans;
import kyle.leis.es.price.incidentalprice.tp.SaveIncidentalTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class IncidentalPrice extends AExpressPrice {
	
	public SavedResult save(IncidentalpriceColumns objIPColumns,
			List listIPValueColumns,
			List listIPVBaseColumns, 
			List listIPVChannelColumns,
			List listEnterprise,
			String strOperId,  
			boolean isIgnoreNotice) throws Exception {
		SaveIncidentalTrans objSaveIncidentalTrans = new SaveIncidentalTrans();
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objSaveIncidentalTrans.setParam(objIPColumns, 
				listIPValueColumns, 
				listIPVBaseColumns,
				listIPVChannelColumns,
				listEnterprise,
				strOperId,
				true);
		if (alRuleCheckReturn == null || alRuleCheckReturn.size() < 1 || isIgnoreNotice) {
			objSaveIncidentalTrans.execute();			
		}
		// ·µ»ØÖµ
		String strEpcode = objSaveIncidentalTrans.getEpcode();
		SavedResult objSavedResult = new SavedResult();	
		objSavedResult.setRulecode(strEpcode);	
		objSavedResult.setRuleCheckList(alRuleCheckReturn);
		return objSavedResult;
	}
	
	
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyIncidentalPriceStatusTrans();
	}
}
