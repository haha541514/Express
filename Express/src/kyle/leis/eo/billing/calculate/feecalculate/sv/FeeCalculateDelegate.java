package kyle.leis.eo.billing.calculate.feecalculate.sv;

import kyle.common.connectors.servlet.RemoteServlet;
import kyle.common.connectors.util.Encoder;

public class FeeCalculateDelegate extends RemoteServlet {
	public FeeCalculateDelegate() {
		super();
		setServiceName("FeeCalculateService");
	}
	
	public void recalculate(String strCwcode) throws Exception {
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strCwcode);
		
		execute("recalculate", objEncode.toString());
	}
}
