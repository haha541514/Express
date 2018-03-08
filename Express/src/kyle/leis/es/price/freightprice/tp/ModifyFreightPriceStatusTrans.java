package kyle.leis.es.price.freightprice.tp;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.es.price.expressprice.tp.AModifyPriceStatusTrans;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDate;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.ruledate.ARuleDate;
import kyle.leis.es.ruledate.RuleCheckReturn;

public class ModifyFreightPriceStatusTrans extends AModifyPriceStatusTrans {

	public ArrayList<RuleCheckReturn> checkPriceDateConflict(String strEpcode, String strPscode)
	throws Exception {
		ARuleDate objRuleDate = new FreightPriceDate();
		List listFreightPrice = FreightPriceDemand.loadFreightPrice(strEpcode);
		FreightpriceColumns objFreightpriceColumns = (FreightpriceColumns)listFreightPrice.get(0);
		
		objFreightpriceColumns.setPspscode(strPscode);
		ArrayList<RuleCheckReturn> alRuleCheckReturn = objRuleDate.checkRuleDate((FreightpriceColumns)listFreightPrice.get(0));
		if (alRuleCheckReturn != null && alRuleCheckReturn.size() > 0) {
			m_objMPDateTrans = new ModifyFreightPriceDateTrans();
			m_objMPDateTrans.setParam(alRuleCheckReturn);
		}
		return alRuleCheckReturn;
	}

}
