package kyle.leis.es.price.freightprice.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class FreightPriceDate extends ARuleDate {
	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		FreightpriceColumns objFPColumns = (FreightpriceColumns)objSavingColumns;
		FreightpriceCondition objFPCondition = new FreightpriceCondition();
		
		objFPCondition.setChncode(objFPColumns.getChnchncode());
		objFPCondition.setCocode(objFPColumns.getCococode());
		objFPCondition.setDtcode(objFPColumns.getDtdtcode());
		objFPCondition.setPdcode(objFPColumns.getPdpdcode());
		objFPCondition.setPgcode(objFPColumns.getPgpgcode());
		objFPCondition.setPkcode(objFPColumns.getPkpkcode());
		objFPCondition.setPmcode(objFPColumns.getPmpmcode());
		objFPCondition.setCtcode(objFPColumns.getCtctcode());
		
		objFPCondition.setPscode(objFPColumns.getPspscode());
		objFPCondition.setEecode(objFPColumns.getEeeecode());
		objFPCondition.setEpkcode(IExpressPriceBasicData.PRICEKIND_FREIGHT);
		return objFPCondition;
	}
	
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		FreightpriceColumns objFreightPriceC = (FreightpriceColumns)objQueryColumns;
		return objFreightPriceC.getEpependdate();
	}
	
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		FreightpriceColumns objFreightPriceC = (FreightpriceColumns)objQueryColumns;
		return objFreightPriceC.getEpepstartdate();
	}
	
	protected String getRulecode(GeneralColumns objQueryColumns) {
		FreightpriceColumns objFreightPriceC = (FreightpriceColumns)objQueryColumns;
		return objFreightPriceC.getFpepcode();
	}
	
	protected List queryRulesRange(ICondition objCondition) 
	throws Exception {
		if (objCondition instanceof FreightpriceCondition) {
			return FreightPriceDemand.queryFreightPrice((FreightpriceCondition)objCondition);
		}
		return null;
	}
}
