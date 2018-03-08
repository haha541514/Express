package kyle.leis.eo.finance.audit.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.finance.audit.bl.Audit;

public class AuditService extends AService {
	public String audit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isReceiveAuditSign = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		Audit objAudit = new Audit();
		PromptUtilityCollection objPUC = objAudit.audit(strCwcode, strOperId, isReceiveAuditSign);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPUC.toStringArray());
		return objEncode.toString();
	}
	
	/**
	 * ≥∑œ˙…Û∫À
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String revokeAudit(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		String strCwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		Audit objAudit = new Audit();
		objAudit.revokeAudit(strCwcode, strOperId);
		
		return "";
		
	}
}
