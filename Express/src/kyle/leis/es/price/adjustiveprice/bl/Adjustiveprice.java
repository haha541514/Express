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
	 * �����۸�����
	 */
	public SavedResult saveAdjustiveprice(AdjustivepriceColumns objAdjustivepriceCol, List listAdjustivepricevalueCol,String strOperId,boolean isIgnoreNotice) throws Exception
	{
		SaveAdjustivepriceTrans objSaveAdjustivepriceTrans = new SaveAdjustivepriceTrans();
		ArrayList<RuleCheckReturn> listRuleCheckReturn = objSaveAdjustivepriceTrans.setParam(objAdjustivepriceCol, listAdjustivepricevalueCol,strOperId,true);
		if (listRuleCheckReturn == null || listRuleCheckReturn.size() < 1 || isIgnoreNotice)
			objSaveAdjustivepriceTrans.execute();
		
		// ˢ�»���
		QueryCache objQueryCache = new QueryCache();
		objQueryCache.reset();		
		
		//����ֵ
		SavedResult objSavedResult = new SavedResult();
		objSavedResult.setRulecode(objSaveAdjustivepriceTrans.getEpcode());
		objSavedResult.setRuleCheckList(listRuleCheckReturn);
		return objSavedResult;
	}

	
	/*
	 * ���״̬�޸ĵ����������
	 */
	@Override
	protected AModifyPriceStatusTrans getModifyPriceStatusTrans() {
		return new ModifyAdjustivePriceStatusTrans();
	}
}
