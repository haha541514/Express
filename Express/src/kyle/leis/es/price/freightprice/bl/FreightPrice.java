package kyle.leis.es.price.freightprice.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.tp.ModifyFreightPriceStatusTrans;
import kyle.leis.es.price.freightprice.tp.SaveTransaction;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class FreightPrice extends AExpressPrice {
	public SavedResult save(FreightpriceColumns objFPColumns,
			List listFreightpricevalue, 
			List listSurchargevalue, 
			List listSurchargevaluebase, 
			String strOperId, 
			boolean isIgnoreNotice,
			List listEnterprise) throws Exception {
		SaveTransaction objSaveTransaction = new SaveTransaction();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveTransaction.setParam(objFPColumns, 
				listFreightpricevalue, 
				listSurchargevalue, 
				listSurchargevaluebase, 
				listEnterprise,
				strOperId, 
				true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice) {
			objSaveTransaction.execute();		
			// 刷新缓冲
			QueryCache objQueryCache = new QueryCache();
			objQueryCache.reset();	
		}
		// 返回值
		String strEpcode = objSaveTransaction.getEpcode();
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(strEpcode);
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;
	}
	
	/**
	 * 获得状态修改的事务处理对象
	 */
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyFreightPriceStatusTrans();
	}
}
