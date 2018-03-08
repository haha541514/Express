package kyle.leis.es.businessrule.businessrules.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.price.expressprice.dax.SavedResult;

public abstract class ABusinessruleService extends AService {
	
	public String confirm(Decoder objPD) throws Exception {
		return modifyStatus(objPD, "ON");
	}	
	
	public String eliminate(Decoder objPD) throws Exception {
		return modifyStatus(objPD, "OFF");
	}
	
	private String modifyStatus(Decoder objPD, String strSscode) 
	throws Exception {
		checkParameterCount(objPD, 3, this);

		String strBrid = (String)objPD.getParameter(0, String.class);		
		String strOperId = (String)objPD.getParameter(1, String.class); 
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		boolean isCheckDateConflic = true;
		if (strSscode.equals("OFF"))
			isCheckDateConflic = false;
		
		ABusinessrule objBusinessrule = getBusinessrule(); 
		SavedResult objSavedResult = objBusinessrule.modifyStatus(strBrid, 
				strSscode, 
				strOperId, 
				isIgnoreNotice, 
				isCheckDateConflic);
		return objSavedResult.toString();
	}
	
	protected abstract ABusinessrule getBusinessrule();	
}
