package kyle.leis.es.price.adjustiveprice.tp;

import java.util.ArrayList;

import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDate;
import kyle.leis.es.price.adjustiveprice.dax.AdjustivePriceDemand;
import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyAdjustivePriceStatusTrans extends AModifyPriceStatusTrans {

	@Override
	public ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode,
			String strPscode) throws Exception {
		
		ARuleDate objRuleDate = new AdjustivePriceDate();
		AdjustivepriceColumns objAdjustivepriceCol = AdjustivePriceDemand.queryByEpCode(strEpcode);
		objAdjustivepriceCol.setPspscode(strPscode);
		
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate(objAdjustivepriceCol);
		if(alRuleCheckReturn != null && alRuleCheckReturn.size()>0)
		{
			m_objMPDateTrans = new ModifyAdjustivePriceDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
