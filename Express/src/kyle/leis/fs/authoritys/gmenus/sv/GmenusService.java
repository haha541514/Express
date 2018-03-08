package kyle.leis.fs.authoritys.gmenus.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.authoritys.gmenus.bl.Gmenus;
import kyle.leis.fs.authoritys.gmenus.da.GmenusColumns;
import kyle.leis.fs.authoritys.gmenus.da.GmenusCondition;
import kyle.leis.fs.authoritys.gmenus.dax.GmenusDemand;

public class GmenusService extends AService {

	public String queryGmenus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strGmcode = (String)objPD.getParameter(0, String.class);
		GmenusCondition objGmenusCondition = new GmenusCondition();
		objGmenusCondition.setGmcode(strGmcode);
		List objList = GmenusDemand.query(objGmenusCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String saveGmenus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		GmenusColumns objGmenusColumns = (GmenusColumns)objPD.getParameter(0, GmenusColumns.class);
		Gmenus objGmenus = new Gmenus();
		GmenusColumns objGmenusColumnsReturn = objGmenus.save(objGmenusColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objGmenusColumnsReturn);
		return objEncode.toString();
	}
	
	public String DeleteGmenus(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		
		String strGmcode = (String)objPD.getParameter(0, String.class);
		Gmenus objGmenus = new Gmenus();
		objGmenus.delete(strGmcode);
		return "";
	}
}
