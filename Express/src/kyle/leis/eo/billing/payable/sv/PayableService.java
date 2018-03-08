package kyle.leis.eo.billing.payable.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.billing.payable.bl.Payable;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.da.PayableCondition;
import kyle.leis.eo.billing.payable.dax.PayableColumnsForImport;
import kyle.leis.eo.billing.payable.dax.PayableDemand;

public class PayableService extends AService  {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		PayableCondition objPayableCondition = (PayableCondition)objPD.getParameter(0, 
				PayableCondition.class);
		List objList = PayableDemand.query(objPayableCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strCwcode = (String)objPD.getParameter(0, String.class);
		List objList = PayableDemand.load(strCwcode, "A0201");
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);

		List listPyColumns = objPD.getParameterList(0, PayableColumns.class);
		String strCwcode = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Payable objPayable = new Payable();
		objPayable.save(listPyColumns, strCwcode, strOperId);
		return "";
	}
	
	public String add(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 10, this);
		
		String strFkcode = (String)objPD.getParameter(0, String.class);
		String strUtcode = (String)objPD.getParameter(1, String.class);
		String strFkBasecode = (String)objPD.getParameter(2, String.class);
		String strFeeAmount = (String)objPD.getParameter(3, String.class);
		String strCwcode = (String)objPD.getParameter(4, String.class);
		String strCkcode = (String)objPD.getParameter(5, String.class);
		String strOperID = (String)objPD.getParameter(6, String.class);
		boolean isFixedFeeAmount = Boolean.parseBoolean((String)objPD.getParameter(7, String.class));
		String strChncode = (String)objPD.getParameter(8, String.class);
		int iChargeweightSign = Integer.parseInt((String)objPD.getParameter(9, String.class));
		
		String strResult = "";
		try {
			Payable objPayable = new Payable();
			strResult = objPayable.add(strFkcode, 
					strUtcode, 
					strFkBasecode, 
					strFeeAmount, 
					strCwcode, 
					strCkcode, 
					strChncode,
					strOperID, 
					isFixedFeeAmount,
					iChargeweightSign);
		} catch (Exception ex) {
			strResult = ex.getMessage();
		}
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResult);
		return objEncode.toString();
	}	
	
	
	public String confirm(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String[] astrPyid = objPD.getParameterArray(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Payable objPayable = new Payable();
		objPayable.modifyStatus(astrPyid, strOperId, "C");
		return "";
	}

	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);

		String[] astrPyid = objPD.getParameterArray(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		
		Payable objPayable = new Payable();
		objPayable.delete(astrPyid, strOperId);
		return "";
	}
	
	public String importPayable(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		List listPyColumnsforimport = objPD.getParameterList(0, PayableColumnsForImport.class);
		String strOperID = (String)objPD.getParameter(1, String.class);		
		
		Payable objPayable = new Payable();
		PromptUtilityCollection objPU = objPayable.importPayable(listPyColumnsforimport, strOperID);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objPU.toStringArray());
		return objEncode.toString();
	}		
	
}
