package kyle.leis.es.businessrule.weightrule.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.sv.ABusinessruleService;
import kyle.leis.es.businessrule.weightrule.bl.WeightRule;
import kyle.leis.es.businessrule.weightrule.da.CarryweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.VolumeweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleCondition;
import kyle.leis.es.businessrule.weightrule.da.WeightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.dax.LoadWeighruleResult;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.price.expressprice.dax.SavedResult;

public class WeightRuleService extends ABusinessruleService {

	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		WeightruleCondition objWeightruleCondition = (WeightruleCondition)objPD.getParameter(0, 
				WeightruleCondition.class);
		List objList = WeightRuleDemand.query(objWeightruleCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strBrId = (String)objPD.getParameter(0, String.class);
		LoadWeighruleResult objLoadResult = WeightRuleDemand.loadResults(strBrId);
		
		return objLoadResult.toString();
	}	
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 6, this);

		WeightruleColumns objWeightruleColumns = (WeightruleColumns)objPD.getParameter(0, 
				WeightruleColumns.class);
		List listWRVColumns = objPD.getParameterList(1, WeightrulevalueColumns.class);
		List listVWRVColumns = objPD.getParameterList(2, VolumeweightrulevalueColumns.class);
		List listCWRVColumns = objPD.getParameterList(3, CarryweightrulevalueColumns.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(5, String.class));
		
		WeightRule objWeightRule = new WeightRule();
		SavedResult objSavedResult = objWeightRule.save(objWeightruleColumns, 
				listWRVColumns, 
				listVWRVColumns,
				listCWRVColumns,
				strOperId, 
				isIgnoreNotice);
		
		return objSavedResult.toString();
	}	
	
	
	protected ABusinessrule getBusinessrule() {
		return new WeightRule();
	}

}
