package kyle.leis.es.company.shipperconsignee.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.company.shipperconsignee.bl.Shipperconsignee;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeCondition;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;

public class ShipperconsigneeService extends AService {
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		// ����
		ShipperconsigneeColumns objSCColumns = (ShipperconsigneeColumns)objPD.getParameter(0, 
				ShipperconsigneeColumns.class);
		// ����
		Shipperconsignee objShipperconsignee = new Shipperconsignee();
		ShipperconsigneeColumns objSavedSCColumns = objShipperconsignee.save(objSCColumns);
		// ����ֵ
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSavedSCColumns);
		return objEncode.toString();
	}
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		// ����
		ShipperconsigneeCondition objSCCondition = (ShipperconsigneeCondition)objPD.getParameter(0, 
				ShipperconsigneeCondition.class);
		// ��ѯ
		List objList = ShipperconsigneeDemand.query(objSCCondition);
		// ����ֵ
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		// ����
		String strSCCode = (String)objPD.getParameter(0, String.class);
		Shipperconsignee objShipperconsignee = new Shipperconsignee();
		objShipperconsignee.delete(strSCCode);
		return "";
	}	
}
