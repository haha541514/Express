package kyle.leis.fs.dictionary.transporttool.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.transporttool.bl.Transporttool;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolColumns;
import kyle.leis.fs.dictionary.transporttool.da.TransporttoolCondition;
import kyle.leis.fs.dictionary.transporttool.dax.TransporttoolDemand;

public class TransporttoolService extends AService {
	/*
	 * 查询运输工具
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		TransporttoolCondition objTransporttoolCon = (TransporttoolCondition) objPD.getParameter(0, TransporttoolCondition.class);
		List objList = TransporttoolDemand.query(objTransporttoolCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/*
	 * 添加(修改)运输工具
	 */
	public String addTransporttool(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		TransporttoolColumns objTransporttoolCol = (TransporttoolColumns) objPD.getParameter(0, TransporttoolColumns.class);
		Transporttool objTransporttool = new Transporttool();
		TransporttoolColumns objReturn = objTransporttool.addTransporttool(objTransporttoolCol);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
}
