package kyle.leis.fs.dictionary.waybillcodekind.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.waybillcodekind.bl.Waybillcodekind;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindCondition;
import kyle.leis.fs.dictionary.waybillcodekind.dax.WaybillCKDemand;
@SuppressWarnings("unchecked")
public class WaybillCKService extends AService {

	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		WaybillcodekindColumns objColumns = (WaybillcodekindColumns) objPD.getParameter(0, WaybillcodekindColumns.class);
		Waybillcodekind objWaybillCK = new Waybillcodekind();
		List<WaybillcodekindColumns> objList = objWaybillCK.save(objColumns);
		
		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		WaybillcodekindCondition objWBCKCondition = (WaybillcodekindCondition) objPD.getParameter(0, WaybillcodekindCondition.class);
		List<WaybillcodekindColumns> objList = WaybillCKDemand.query(objWBCKCondition);

		Encoder objEncoder = new Encoder();
		objEncoder.addParameter(objList);
		return objEncoder.toString();
	}
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strBckcode = (String) objPD.getParameter(0, String.class);
		Waybillcodekind objWaybillCK = new Waybillcodekind();
		WaybillcodekindColumns objWaybillcodekindColumns = objWaybillCK.delete(strBckcode);
	
		
		if(objWaybillcodekindColumns != null){
			Encoder objEncoder = new Encoder();
			objEncoder.addParameter(objWaybillcodekindColumns);
			return objEncoder.toString();
		}else{
			return "";
		}
	}

}
