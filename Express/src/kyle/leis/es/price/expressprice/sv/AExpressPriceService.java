package kyle.leis.es.price.expressprice.sv;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;

import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;

public abstract class AExpressPriceService extends AService {
	
	public String confirm(Decoder objPD) throws Exception
	{
		return modifyStatus(objPD, "C");
	}
	
	public String audit(Decoder objPD) throws Exception
	{
		return modifyStatus(objPD, "A");
	}
	
	public String approve(Decoder objPD) throws Exception
	{
		return modifyStatus(objPD, "R");
	}	
	
	public String eliminate(Decoder objPD) throws Exception
	{
		return modifyStatus(objPD, "E");
	}	
	
	public String withdraw(Decoder objPD) throws Exception
	{
		return modifyStatus(objPD, "N");
	}	
	
	private String modifyStatus(Decoder objPD, String strPscode) 
	throws Exception {
		checkParameterCount(objPD, 3, this);

		String strEpcode = (String)objPD.getParameter(0, String.class);		
		String strOperId = (String)objPD.getParameter(1, String.class); 
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		boolean isCheckDateConflic = true;
		if (strPscode.equals("E"))
			isCheckDateConflic = false;
		
		AExpressPrice objExpressPrice = getExpressPrice(); 
		SavedResult objSavedResult = objExpressPrice.modifyStatus(strEpcode, 
				strPscode, 
				strOperId, 
				isIgnoreNotice, 
				isCheckDateConflic);
		return objSavedResult.toString();
	}
	
	protected abstract AExpressPrice getExpressPrice();
}
