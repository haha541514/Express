package kyle.leis.es.systemproperty.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.es.systemproperty.bl.Systemproperty;
import kyle.leis.es.systemproperty.da.SystempropertyColumns;
import kyle.leis.es.systemproperty.da.SystempropertyCondition;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;

public class SystempropertyService extends AService {

	/**
	 * …Ë÷√ Ù–‘
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String add(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		List listSystemproperty = objPD.getParameterList(0, SystempropertyColumns.class);
		Systemproperty objSystemproperty = new Systemproperty();
		List objList = objSystemproperty.add(listSystemproperty);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	/**
	 * ≤È—Ø
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		SystempropertyCondition objSPConciditno = (SystempropertyCondition) objPD.getParameter(0, SystempropertyCondition.class);
		List listSPColumns = SystempropertyDemand.query(objSPConciditno);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listSPColumns);
		return objEncode.toString();
	}
	
	
	public String getSysdate(Decoder objPD) throws Exception
	{
		String strSysdate = DateFormatUtility.getStandardSysdate();
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strSysdate);
		return objEncode.toString();
	}	
	
}
