package kyle.leis.es.businessrule.corweightrule.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.sv.ABusinessruleService;
import kyle.leis.es.businessrule.corweightrule.bl.CorWeightRule;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleCondition;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDemand;
import kyle.leis.es.price.expressprice.dax.SavedResult;

public class CorWeightRuleService extends ABusinessruleService {
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strBrId = (String)objPD.getParameter(0, String.class);
		CorweightruleColumns objCorweightruleColumns = CorWeightRuleDemand.load(strBrId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCorweightruleColumns);
		return objEncode.toString();
	}
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CorweightruleCondition objCWRCondition = (CorweightruleCondition)objPD.getParameter(0, 
				CorweightruleCondition.class);
		List objList = CorWeightRuleDemand.query(objCWRCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		CorweightruleColumns objCorweightruleColumns = (CorweightruleColumns)objPD.getParameter(0, 
				CorweightruleColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		CorWeightRule objCorWeightRule = new CorWeightRule();
		SavedResult objSavedResult = objCorWeightRule.save(objCorweightruleColumns, 
				strOperId, 
				isIgnoreNotice);
		
		return objSavedResult.toString();
	}	
	
	
	protected ABusinessrule getBusinessrule() {
		return new CorWeightRule();
	}

}
