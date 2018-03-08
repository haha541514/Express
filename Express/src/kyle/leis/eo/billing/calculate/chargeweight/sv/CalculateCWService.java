package kyle.leis.eo.billing.calculate.chargeweight.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.billing.calculate.chargeweight.bl.Chargeweight;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;

public class CalculateCWService extends AService {
	
	public String calculate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		// 获得参数
		ChargeweightParameter objCWeightParameter = (ChargeweightParameter)objPD.getParameter(0, 
				ChargeweightParameter.class);
		List listWaybillpieces = objPD.getParameterList(1, CorewaybillpiecesColumns.class);
		objCWeightParameter.setWaybillpiecesCollection(listWaybillpieces);
		
		Chargeweight objChargeweight = new Chargeweight(); 
		ChargeweightResult objChargeweightResult = objChargeweight.calculate(objCWeightParameter);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objChargeweightResult.toStringArray());
		return objEncode.toString();
	}
	
	public String calculateByCwcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		// 获得参数
		String strCwcode = (String)objPD.getParameter(0, String.class);
		
		Chargeweight objChargeweight = new Chargeweight(); 
		ChargeweightResult objChargeweightResult = objChargeweight.calculate(strCwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objChargeweightResult.toStringArray());
		return objEncode.toString();
	}
}
