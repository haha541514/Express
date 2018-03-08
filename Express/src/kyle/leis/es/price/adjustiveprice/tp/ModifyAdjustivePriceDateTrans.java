package kyle.leis.es.price.adjustiveprice.tp;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDemand;
import kyle.leis.es.price.adjustiveprice.dax.LoadAdjustivePriceResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;
import net.sf.hibernate.Session;

public class ModifyAdjustivePriceDateTrans extends AModifyPriceDateTrans {

	@Override
	protected void createNewRule(RuleCheckReturn objRuleCheckReturn,
			Session objSession) throws Exception {
		String strEpCode = objRuleCheckReturn.getRulecode();
		if(StringUtility.isNull(strEpCode)) return;
		
		LoadAdjustivePriceResult objLoadAdjustivePriceResult = AdjustivePriceDemand.loadByEpCode(strEpCode); 
		SaveAdjustivepriceTrans objSaveAdjustivepriceTrans = new SaveAdjustivepriceTrans();
		
		objSaveAdjustivepriceTrans.setConflictParam(objLoadAdjustivePriceResult, objRuleCheckReturn.getNewStartDate(), objRuleCheckReturn.getNewEndDate(),"0",false);
		objSaveAdjustivepriceTrans.transaction(objSession);
	}

}
