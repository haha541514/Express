package kyle.leis.es.price.freightprice.tp;

import net.sf.hibernate.Session;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.tp.AModifyPriceDateTrans;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.price.freightprice.dax.LoadResult;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyFreightPriceDateTrans extends AModifyPriceDateTrans {
	// 创建新规则
	protected void createNewRule(RuleCheckReturn objRuleCheckReturn, 
			Session objSession) throws Exception {
		String strEpcode = objRuleCheckReturn.getRulecode();
		if (StringUtility.isNull(strEpcode)) return;
		
		LoadResult objLoadResult = FreightPriceDemand.load(strEpcode);
		SaveTransaction objSaveTransaction = new SaveTransaction();
		objSaveTransaction.setConflictParam(objLoadResult, 
				"0", 
				objRuleCheckReturn.getNewStartDate(),
				objRuleCheckReturn.getNewEndDate(),
				false);
		objSaveTransaction.transaction(objSession);
	}
}
