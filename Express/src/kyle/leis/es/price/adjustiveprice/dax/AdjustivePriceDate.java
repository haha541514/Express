package kyle.leis.es.price.adjustiveprice.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class AdjustivePriceDate extends ARuleDate {

	@Override
	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		AdjustivepriceColumns objAdjustivepriceCol = (AdjustivepriceColumns) objSavingColumns;
		AdjustivepriceCondition objAdjustivepriceCon = new AdjustivepriceCondition();
		
		objAdjustivepriceCon.setEecode(objAdjustivepriceCol.getEeeecode());
//		objAdjustivepriceCon.setEpcode(objAdjustivepriceCol.getApepcode());
		objAdjustivepriceCon.setEpkcode(objAdjustivepriceCol.getEpkepkcode());
		objAdjustivepriceCon.setPscode(objAdjustivepriceCol.getPspscode());
		
		return objAdjustivepriceCon;
	}

	@Override
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		return ((AdjustivepriceColumns)(objQueryColumns)).getEpependdate();
	}

	@Override
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		return ((AdjustivepriceColumns)(objQueryColumns)).getEpepstartdate();
	}

	@Override
	protected String getRulecode(GeneralColumns objQueryColumns) {
		return ((AdjustivepriceColumns)(objQueryColumns)).getApepcode();
	}

	@Override
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		return AdjustivePriceDemand.query((AdjustivepriceCondition)objCondition);
	}

}
