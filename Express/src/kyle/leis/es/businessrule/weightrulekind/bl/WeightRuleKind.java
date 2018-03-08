package kyle.leis.es.businessrule.weightrulekind.bl;

import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns;
import kyle.leis.es.businessrule.weightrulekind.dax.WeightRuleKindDemand;
import kyle.leis.es.businessrule.weightrulekind.tp.ModifyWeightRuleKindTrans;
import kyle.leis.es.businessrule.weightrulekind.tp.SaveWeightRuleKindTrans;

public class WeightRuleKind {
	
	public WeightrulekindColumns save(WeightrulekindColumns objWRKColumns,
			String strOperId) throws Exception {
		SaveWeightRuleKindTrans objSaveWRKTrans = new SaveWeightRuleKindTrans();
		objSaveWRKTrans.setParam(objWRKColumns, strOperId);
		objSaveWRKTrans.execute();
		// 刷新数据
		Long lNewWrkid = objSaveWRKTrans.getNewWrkid();
		if (lNewWrkid == null) return null;
		return WeightRuleKindDemand.load(String.valueOf(lNewWrkid));
	}
	
	public WeightrulekindColumns eliminate(String strWrkid, 
			String strOperId) throws Exception {
		ModifyWeightRuleKindTrans objModifyWRKTrans = new ModifyWeightRuleKindTrans();
		objModifyWRKTrans.setParam(strWrkid, "OFF");
		objModifyWRKTrans.execute();
		// 刷新数据
		return WeightRuleKindDemand.load(strWrkid);
	}
}
