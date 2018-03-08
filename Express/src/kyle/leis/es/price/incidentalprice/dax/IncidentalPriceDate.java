package kyle.leis.es.price.incidentalprice.dax;

import java.util.List;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.dbaccess.query.ICondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.ruledate.ARuleDate;

public class IncidentalPriceDate extends ARuleDate {

	protected ICondition buildCondition(GeneralColumns objSavingColumns) {
		IncidentalpriceColumns objIPColumns = (IncidentalpriceColumns)objSavingColumns;
		
		IncidentalpriceCondition objIPCondition = new IncidentalpriceCondition();
		objIPCondition.setChncode(objIPColumns.getChnchncode());
		objIPCondition.setCocode(objIPColumns.getCococode());
		objIPCondition.setDtcode(objIPColumns.getDtdtcode());
		objIPCondition.setEecode(objIPColumns.getEeeecode());
		objIPCondition.setEpkcode(objIPColumns.getEpkepkcode());
		objIPCondition.setPdcode(objIPColumns.getPdpdcode());
		objIPCondition.setPgcode(objIPColumns.getPgpgcode());
		objIPCondition.setPkcode(objIPColumns.getPkpkcode());
		objIPCondition.setPscode(objIPColumns.getPspscode());
		return objIPCondition;
	}
	
	protected String getRuleEndDate(GeneralColumns objQueryColumns) {
		return ((IncidentalpriceColumns)objQueryColumns).getEpependdate();
	}
	
	protected String getRuleStartDate(GeneralColumns objQueryColumns) {
		return ((IncidentalpriceColumns)objQueryColumns).getEpepstartdate();
	}
	
	protected String getRulecode(GeneralColumns objQueryColumns) {
		return ((IncidentalpriceColumns)objQueryColumns).getEpepcode();
	}
	
	protected List queryRulesRange(ICondition objCondition) throws Exception {
		IncidentalpriceCondition objIPCondition = (IncidentalpriceCondition)objCondition;
		return IncidentalPriceDemand.query(objIPCondition);
	}
}
