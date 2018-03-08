package kyle.leis.eo.operation.manifest.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillformanifestCondition;
import kyle.leis.eo.operation.manifest.dax.ManifestExportFormatDemand;

public class ManifestExportFormatService extends AService {

	public String queryForMEF(Decoder objPD) throws Exception{
		checkParameterCount(objPD, 2, this);
		String mefcode = (String)objPD.getParameter(0, String.class);
		HousewaybillformanifestCondition objHWBFMCondition = (HousewaybillformanifestCondition)objPD.getParameter(1, 
				HousewaybillformanifestCondition.class);
		if (StringUtility.isNull(objHWBFMCondition.getCwbatchwaybillsign()))
			objHWBFMCondition.setCwbatchwaybillsign("N");
		if (!StringUtility.isNull(objHWBFMCondition.getCwcode()))
			objHWBFMCondition.setCwbatchwaybillsign("");
		ManifestExportFormatDemand mefDemand = new ManifestExportFormatDemand();
		List listResults = mefDemand.queryForMEF(mefcode, objHWBFMCondition);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(listResults);
		return objEncoder.toString();
	}
}
