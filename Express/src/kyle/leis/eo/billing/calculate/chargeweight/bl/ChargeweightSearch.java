package kyle.leis.eo.billing.calculate.chargeweight.bl;

import java.util.List;

import kyle.leis.es.businessrule.corweightrule.da.CorweightruleColumns;
import kyle.leis.es.businessrule.corweightrule.da.CorweightruleCondition;
import kyle.leis.es.businessrule.corweightrule.dax.CorWeightRuleDemand;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleCondition;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindCondition;
import kyle.leis.es.businessrule.weightrulekind.dax.WeightRuleKindDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;

public class ChargeweightSearch {
	public WeightruleColumns searchWeightrule(String strWrkid, 
			String strSearchDate) throws Exception {
		WeightruleCondition objWRuleCondition = new WeightruleCondition();
		objWRuleCondition.setUseCacheSign(true);
		objWRuleCondition.setWrkid(strWrkid);
		objWRuleCondition.setValiddate1(strSearchDate);
		objWRuleCondition.setValiddate2(strSearchDate);
		objWRuleCondition.setSscode("ON");
		List objList = WeightRuleDemand.query(objWRuleCondition);
		if (objList == null || objList.size() != 1) 
			return null;
		return (WeightruleColumns)objList.get(0);
	}
	
	public String searchWeightrulekind(String strPkcode, 
			String strCocode,
			String strPdcode,
			String strSearchDate) throws Exception {
		// 设置查询条件
		CorweightruleCondition objCorWRCondition = new CorweightruleCondition();
		objCorWRCondition.setUseCacheSign(true);
		objCorWRCondition.setValiddate1(strSearchDate);
		objCorWRCondition.setValiddate2(strSearchDate);
		objCorWRCondition.setSscode("ON");
		if (strPdcode.equals(IExpressPriceBasicData.PRICEDOMAIN_SALES))
			objCorWRCondition.setCocode(strCocode);
		else
			objCorWRCondition.setChncode(strCocode);
		// 查找规则种类
		List listCorweightrule = CorWeightRuleDemand.query(objCorWRCondition);
        // 查找默认的规则种类
		if (listCorweightrule == null || listCorweightrule.size() < 1) {
			return searchDefaultWeightrulekind(strPkcode, strPdcode);
		} else if (listCorweightrule.size() == 1) {
			CorweightruleColumns objCorWRColumns = (CorweightruleColumns)listCorweightrule.get(0);
			return objCorWRColumns.getWrkwrkid();
		}
		return "";
	}
	
	public String searchDefaultWeightrulekind(String strPkcode, 
			String strPdcode) throws Exception {
		WeightrulekindCondition objWRKindCondition = new WeightrulekindCondition();
		objWRKindCondition.setUseCacheSign(true);
		
		objWRKindCondition.setSscode("ON");
		objWRKindCondition.setWrkdefaultsign("Y");
		objWRKindCondition.setPdcode(strPdcode);
		if (strPdcode.equals(IExpressPriceBasicData.PRICEDOMAIN_SALES))
			objWRKindCondition.setPkcode(strPkcode);
		List objList = WeightRuleKindDemand.query(objWRKindCondition);
		
		if (objList == null || objList.size() < 1 || objList.size() > 1) 
			return null;
		WeightrulekindColumns objWRKColumns = (WeightrulekindColumns)objList.get(0);
		return objWRKColumns.getWrkwrkid();
	}	
}
