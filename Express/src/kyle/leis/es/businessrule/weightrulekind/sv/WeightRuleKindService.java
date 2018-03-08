package kyle.leis.es.businessrule.weightrulekind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.weightrulekind.bl.WeightRuleKind;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindColumns;
import kyle.leis.es.businessrule.weightrulekind.da.WeightrulekindCondition;
import kyle.leis.es.businessrule.weightrulekind.dax.WeightRuleKindDemand;

public class WeightRuleKindService extends AService {
	/**
	 * 查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		WeightrulekindCondition objWRKCondition = (WeightrulekindCondition)objPD.getParameter(0, 
				WeightrulekindCondition.class);
		List objList = WeightRuleKindDemand.query(objWRKCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 装载记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strWrkid = (String)objPD.getParameter(0, String.class);
		WeightrulekindColumns objWRKColumns = WeightRuleKindDemand.load(strWrkid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWRKColumns);
		return objEncode.toString();
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		WeightrulekindColumns objWRKColumns = (WeightrulekindColumns)objPD.getParameter(0, WeightrulekindColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		WeightRuleKind objWeightRuleKind = new WeightRuleKind(); 
		objWRKColumns = objWeightRuleKind.save(objWRKColumns, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWRKColumns);
		return objEncode.toString();
	}
	
	// 作废
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String strWrkid = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		WeightRuleKind objWeightRuleKind = new WeightRuleKind(); 
		WeightrulekindColumns objWRKColumns = objWeightRuleKind.eliminate(strWrkid, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWRKColumns);
		return objEncode.toString();
	}
	
}
