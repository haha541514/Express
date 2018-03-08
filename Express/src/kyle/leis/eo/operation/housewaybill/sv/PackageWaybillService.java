package kyle.leis.eo.operation.housewaybill.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.housewaybill.bl.PackageWaybill;

public class PackageWaybillService extends AService {
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);	
		
		String strCwserverewbcode = (String)objPD.getParameter(0, String.class);
		String strAdtcode = (String)objPD.getParameter(1, String.class);
		String strBwvid = (String)objPD.getParameter(2, String.class);
		String strOperID = (String)objPD.getParameter(3, String.class);
		
		PackageWaybill objPackageWaybill = new PackageWaybill();
		SavedResultUtility objSRUtility = objPackageWaybill.save(strCwserverewbcode,
				strAdtcode,
				strBwvid,
				strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSRUtility.getPromptUtilityCollection().toStringArray());
		objEncode.addParameter(objSRUtility.getColumns());
		return objEncode.toString();
	}
}
