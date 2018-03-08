package kyle.leis.es.businessrule.operationprompt.sv;


import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.sv.ABusinessruleService;
import kyle.leis.es.businessrule.operationprompt.bl.OperationPrompt;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptColumns;
import kyle.leis.es.businessrule.operationprompt.da.OperationpromptCondition;
import kyle.leis.es.businessrule.operationprompt.dax.OperationPromptQueryReturn;
import kyle.leis.es.businessrule.operationprompt.dax.OperationpromptDemand;

public class OperationpromptService extends ABusinessruleService {
	
	/**
	 * 查询操作规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		OperationpromptCondition objOPTCondition = (OperationpromptCondition) objPD.getParameter(0, OperationpromptCondition.class);
		List objList = OperationpromptDemand.query(objOPTCondition);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * 加载操作规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryByBrId(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strBrId = (String) objPD.getParameter(0, String.class); 
		OperationPromptQueryReturn objReturn = OperationpromptDemand.loadByBrId(strBrId);
		
		return objReturn.toString();
	}
	
	/**
	 * 添加操作规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String add(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,10,this);
		
		OperationpromptColumns objOPTColumns = (OperationpromptColumns) objPD.getParameter(0, OperationpromptColumns.class);
		String[] astrCustomer = objPD.getParameterArray(1, String.class);
		String[] astrSupplier = objPD.getParameterArray(2, String.class);
		String[] astrChannel = objPD.getParameterArray(3, String.class);
		String strOptn_CsSign = (String) objPD.getParameter(4, String.class);
		String[] astrSpecialType = objPD.getParameterArray(5, String.class);
		String[] astrOperationTache = objPD.getParameterArray(6, String.class);
		String[] astrOdtCode = objPD.getParameterArray(7, String.class);
		String[] astrDdtCode = objPD.getParameterArray(8, String.class);
		String strOperator = (String)objPD.getParameter(9, String.class);
		
		OperationPrompt objOperationPrompt = new OperationPrompt();
		OperationpromptColumns objOPTReturnColumns = objOperationPrompt.add(objOPTColumns, astrCustomer, astrSupplier, astrChannel, strOptn_CsSign, astrSpecialType, astrOperationTache, astrOdtCode, astrDdtCode, strOperator);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objOPTReturnColumns);
		return objEncode.toString();
	}
	
	/**
	 * 制单等操作规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String verifyOpt(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strCwcode = (String) objPD.getParameter(0, String.class);
		String strOtCode = (String) objPD.getParameter(1, String.class);
		
		OperationPrompt objOperationPrompt = new OperationPrompt();
		String[] astrContent = objOperationPrompt.verifyOpt(strCwcode,strOtCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(astrContent);
		return objEncode.toString();
	}
	
	/**
	 * 出货操作规则
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String verifySignOutopt(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		String strServerewbcode = (String)objPD.getParameter(0, String.class);
		String strOtCode = (String) objPD.getParameter(1, String.class);
		
		OperationPrompt objOperationPrompt = new OperationPrompt();
		String[] astrContent = objOperationPrompt.verifySignOutopt(strServerewbcode,strOtCode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(astrContent);
		return objEncode.toString();
	}
	
	
	@Override
	protected ABusinessrule getBusinessrule() {
		return new OperationPrompt();
	}
	
}
