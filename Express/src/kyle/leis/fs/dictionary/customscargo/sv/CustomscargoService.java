package kyle.leis.fs.dictionary.customscargo.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.fs.dictionary.customscargo.bl.Customscargo;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoColumns;
import kyle.leis.fs.dictionary.customscargo.da.CustomscargoCondition;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameColumns;
import kyle.leis.fs.dictionary.customscargo.da.MemorydeclarenameCondition;
import kyle.leis.fs.dictionary.customscargo.dax.CustomscargoDemand;


public class CustomscargoService extends AService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomscargoCondition objCCCondition = (CustomscargoCondition)objPD.getParameter(0, 
				CustomscargoCondition.class);
		List objList = CustomscargoDemand.query(objCCCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		List objCustomscargoColumns = (List)objPD.getParameterList(0, 
				CustomscargoColumns.class);
		
		Customscargo objCustomscargo = new Customscargo();
		CustomscargoColumns  objCC= objCustomscargo.saveCustomscargo(objCustomscargoColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objCC);
		return objEncode.toString();
	}
	
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String ccCode= (String) objPD.getParameter(0, String.class);
		
		Customscargo objCustomscargo = new Customscargo();
		objCustomscargo.deleteCustomscargo(ccCode);
		
		return "";
	}
	
	public String queryMemoryDeclare(Decoder objPD) throws Exception {
		MemorydeclarenameCondition objMDC = new MemorydeclarenameCondition();
		List objList = CustomscargoDemand.queryMemorydeclarename(objMDC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		
		return objEncode.toString();
	}
	
	public String saveMemoryDeclare(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		MemorydeclarenameColumns objMDNColumns = (MemorydeclarenameColumns)objPD.getParameter(0, 
				MemorydeclarenameColumns.class);
		
		Customscargo objCustomscargo = new Customscargo();
		MemorydeclarenameColumns objMDC = objCustomscargo.saveMemoryDeclare(objMDNColumns);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objMDC);
		
		return objEncode.toString();
	}
	
	public String deleteMemoryDeclare(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strMdncode = (String) objPD.getParameter(0, String.class);
		
		Customscargo objCustomscargo = new Customscargo();
		objCustomscargo.deleteMemoryDeclare(strMdncode);
		
		return "";
	}	
	
}
