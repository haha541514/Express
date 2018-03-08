package kyle.leis.eo.billing.incidentalfee.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.incidentalfee.bl.Incidentalfee;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeCondition;
import kyle.leis.eo.billing.incidentalfee.dax.IncidentalfeeDemand;

public class IncidentalfeeService extends AService {

	/*
	 * 查询杂项费用
	 */
	public String query(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,1,this);
		IncidentalfeeCondition objIncidentalfeeCon = (IncidentalfeeCondition) objPD.getParameter(0, IncidentalfeeCondition.class);
		List objList =  IncidentalfeeDemand.query(objIncidentalfeeCon);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	
	/*
	 * 保存杂项费用
	 */
	public String addIncidentalfee(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,2,this);
		
		IncidentalfeeColumns objIncidentalfeeCol = (IncidentalfeeColumns) objPD.getParameter(0, IncidentalfeeColumns.class);
		String strOperId = (String) objPD.getParameter(1,String.class);
		
		Incidentalfee objIncidentalfee = new Incidentalfee();
		IncidentalfeeColumns objReturn = objIncidentalfee.addIncidentalfee(objIncidentalfeeCol, strOperId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objReturn);
		return objEncode.toString();
	}
	
	/*
	 * 确定
	 */
	public String confrim(Decoder objPD) throws Exception
	{
		checkParameterCount(objPD,3,this);
		
		String strIfId =  (String) objPD.getParameter(0, String.class);
		String strFsCode =  (String) objPD.getParameter(1, String.class);
		String strOperId =  (String) objPD.getParameter(2, String.class);
		
		Incidentalfee objIncidentalfee = new Incidentalfee();
		objIncidentalfee.modifyStatus(strIfId, strFsCode, strOperId);
		
		return "";
	}
	
}
