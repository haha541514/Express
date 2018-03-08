package kyle.leis.eo.operation.predictwaybill.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.predictwaybill.bl.Predictwaybill;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillCondition;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillforprintCondition;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillDemand;

public class PredictwaybillService extends AService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		PredictwaybillCondition objPWCondition = (PredictwaybillCondition)objPD.getParameter(0, 
				PredictwaybillCondition.class);
		List listResults = PredictwaybillDemand.query(objPWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}
	
	public String queryForPrint(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		PredictwaybillforprintCondition condition = (PredictwaybillforprintCondition)objPD.getParameter(0, 
				PredictwaybillforprintCondition.class);
		List listResults = PredictwaybillDemand.queryForPrint(condition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}	
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		PredictwaybillColumns objPredictwaybillColumns = (PredictwaybillColumns)objPD.getParameter(0, 
				PredictwaybillColumns.class);
		List listCargoinfo = objPD.getParameterList(1, PredictcargoinfoColumns.class);
		String strOperID = (String)objPD.getParameter(2, String.class);
		
		Predictwaybill objPredictwaybill = new Predictwaybill();
		SavedResultUtility objSavedResultUtility = objPredictwaybill.save(objPredictwaybillColumns, 
				listCargoinfo,
				strOperID);
		
		return objSavedResultUtility.toString();
	}
	
	public String upload(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strPwbcode = (String)objPD.getParameter(0, String.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Predictwaybill objPredictwaybill = new Predictwaybill();
		SavedResultUtility objSavedResultUtility = objPredictwaybill.upload(strPwbcode, strOperID);
		
		return objSavedResultUtility.toString();
	}	
	
	public String uploadFinance(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strPwbcode = (String)objPD.getParameter(0, String.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Predictwaybill objPredictwaybill = new Predictwaybill();
		SavedResultUtility objSavedResultUtility = objPredictwaybill.uploadFinance(strPwbcode, strOperID);
		
		return objSavedResultUtility.toString();
	}		
	
	public String withdraw(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strPwbcode = (String)objPD.getParameter(0, String.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Predictwaybill objPredictwaybill = new Predictwaybill();
		SavedResultUtility objSavedResultUtility = objPredictwaybill.withdraw(strPwbcode, strOperID, false);
		
		return objSavedResultUtility.toString();
	}		
	
	public String print(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strPwbcode = (String)objPD.getParameter(0, String.class);
		String strOperID = (String)objPD.getParameter(1, String.class);
		
		Predictwaybill objPredictwaybill = new Predictwaybill();
		SavedResultUtility objSavedResultUtility = objPredictwaybill.print(strPwbcode, strOperID);
		
		return objSavedResultUtility.toString();
	}		
	
}
