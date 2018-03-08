package kyle.leis.es.company.companys.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.leis.es.company.companys.bl.Companys;

public class CompanysService extends AService {
	
	public String modifyStatus(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strCocode = (String)objPD.getParameter(0, String.class);
		String strCscode = (String)objPD.getParameter(1, String.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		
		Companys objCompanys = new Companys();
		objCompanys.modifyStatus(strCocode, strCscode, strOperId);
		
		return "";
	}
}
