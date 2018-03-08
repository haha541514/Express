package kyle.leis.es.price.adjustiveprice.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.cache.QueryCache;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.tp.ModifyAdjustivePriceStatusTrans;
import kyle.leis.es.price.adjustiveprice.tp.SaveAdjustivepriceTrans;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class Adjustiveprice extends AExpressPrice {
	/*
	 * 调整价格设置
	 */
	public SavedResult saveAdjustiveprice(AdjustivepriceColumns objAdjustivepriceCol, List listAdjustivepricevalueCol,String strOperId,boolean isIgnoreNotice) throws Exception
	{
		SaveAdjustivepriceTrans objSaveAdjustivepriceTrans = new SaveAdjustivepriceTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveAdjustivepriceTrans.setParam(objAdjustivepriceCol, listAdjustivepricevalueCol,strOperId,true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice)
			objSaveAdjustivepriceTrans.execute();
		
		// 刷新缓冲
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();		
		
		//返回值
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(objSaveAdjustivepriceTrans.getEpcode());
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;
	}

	
	/*
	 * 获得状态修改的事务处理对象
	 */
	@Override
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyAdjustivePriceStatusTrans();
	}
}
