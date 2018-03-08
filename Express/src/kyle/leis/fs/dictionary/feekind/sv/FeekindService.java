package kyle.leis.fs.dictionary.feekind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.feekind.bl.Feekind;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.da.FeekindCondition;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;

public class FeekindService extends AService {
	/*
	 * 查询费用种类
	 */
	public String queryFeekind(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		FeekindCondition objFeekindCondition = (FeekindCondition)objPD.getParameter(0, FeekindCondition.class);
		List objList = FeekindDemand.query(objFeekindCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	
	/*
	 * 新加费用种类
	 */
	public String addFeekind(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		FeekindColumns objFeekindColumns = (FeekindColumns)objPD.getParameter(0, FeekindColumns.class);
		Feekind objFeekind = new Feekind();
		FeekindColumns objReturn = objFeekind.addFeekind(objFeekindColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/*
	 * 作废
	 */
	public String eliminate(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strFkcode = (String) objPD.getParameter(0, String.class);
		Feekind objFeekind = new Feekind();
		objFeekind.modifyStatus(strFkcode, "OFF");
		return "";
	}
	
}
