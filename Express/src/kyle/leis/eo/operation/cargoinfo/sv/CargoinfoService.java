package kyle.leis.eo.operation.cargoinfo.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.operation.cargoinfo.bl.Cargoinfo;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;

public class CargoinfoService extends AService {
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, 3, this);

		List listCargoInfo = objPD.getParameterList(0, CargoinfoColumns.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		Cargoinfo objCargoinfo = new Cargoinfo();
		
		if (objPD.getParameterCount() == 3) {
			String strGenericGoods = (String)objPD.getParameter(2, String.class);
			objCargoinfo.save(listCargoInfo, strGenericGoods, strCwcode);
			return "";
		} else {
			objCargoinfo.save(listCargoInfo, strCwcode);
			return "";
		}
	}
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		
		List listResult = CargoInfoDemand.queryByCwCode(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResult);
		return objEncode.toString();
	}	
	
}
