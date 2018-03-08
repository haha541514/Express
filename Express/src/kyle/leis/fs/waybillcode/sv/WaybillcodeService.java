package kyle.leis.fs.waybillcode.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.waybillcode.bl.AWaybillcode;
import kyle.leis.fs.waybillcode.bl.WaybillcodeFactory;
import kyle.leis.fs.waybillcode.da.WaybillcodeColumns;
import kyle.leis.fs.waybillcode.da.WaybillcodeCondition;
import kyle.leis.fs.waybillcode.dax.WaybillcodeDemand;
import kyle.leis.fs.waybillcode.dax.WaybillcodeRegisterReturn;

public class WaybillcodeService extends AService{

	/*
	 * ×¢²á
	 */
	public String register(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		WaybillcodeColumns objWaybillcodeCol = (WaybillcodeColumns) objPD.getParameter(0, WaybillcodeColumns.class);
		String strOperId = (String) objPD.getParameter(1, String.class);
		String isIgnoreNotice = (String) objPD.getParameter(2,String.class);
		
		AWaybillcode objAWaybillcode = WaybillcodeFactory.getWaybillcode(objWaybillcodeCol.getBckbckcode());
		WaybillcodeRegisterReturn objWaybillcodeRegisterReturn = objAWaybillcode.register(objWaybillcodeCol, strOperId, Boolean.parseBoolean(isIgnoreNotice));
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWaybillcodeRegisterReturn.getPromptUtilityCollection().toStringArray());
		objEncode.addParameter(objWaybillcodeRegisterReturn.getWaybillcodeColumns());
		return objEncode.toString();
		
	}
	
	
	
	/*
	 * ²éÑ¯µ¥ºÅ
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		WaybillcodeCondition objWaybillcodeCon = (WaybillcodeCondition) objPD.getParameter(0, WaybillcodeCondition.class);
		List objList = WaybillcodeDemand.query(objWaybillcodeCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
}
