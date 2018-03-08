package kyle.leis.fs.corewaybillauditlog.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.corewaybillauditlog.bl.Auditlog;
import kyle.leis.fs.corewaybillauditlog.dax.AuditlogDemand;

public class AuditlogService extends AService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class);
		
		List listResults = AuditlogDemand.query(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String add(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class); 
		String strFaltcode = (String)objPD.getParameter(1, String.class); 
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Auditlog objAuditlog = new Auditlog();
		objAuditlog.add(strCwcode, strFaltcode, strOperId);
		
		return "";
	}
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strCwcode = (String)objPD.getParameter(0, String.class); 
		String strFaltcode = (String)objPD.getParameter(1, String.class); 
		
		Auditlog objAuditlog = new Auditlog();
		objAuditlog.delete(strCwcode, strFaltcode);
		
		return "";
	}	
}
