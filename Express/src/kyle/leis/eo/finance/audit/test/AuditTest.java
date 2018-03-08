package kyle.leis.eo.finance.audit.test;

import kyle.common.connectors.util.Decoder;
import kyle.leis.eo.finance.audit.sv.AuditService;

public class AuditTest {
	public static void main(String[] args) {
		try {
			String strCwcode = "7559";
		    String strOpid = "0";
		    String str = "7559~`~@~#0~`~@~#";
			Decoder objPD = new Decoder(str);
			AuditService audit = new AuditService();
			audit.revokeAudit(objPD);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
	}

}
