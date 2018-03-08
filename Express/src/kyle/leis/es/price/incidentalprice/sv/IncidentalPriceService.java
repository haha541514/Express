package kyle.leis.es.price.incidentalprice.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.da.PriceenterpriseColumns;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.sv.AExpressPriceService;
import kyle.leis.es.price.incidentalprice.bl.IncidentalPrice;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseColumns;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.es.price.incidentalprice.dax.LoadIncidentalResult;

public class IncidentalPriceService extends AExpressPriceService {
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		IncidentalpriceCondition objIPC = (IncidentalpriceCondition)objPD.getParameter(0, 
				IncidentalpriceCondition.class);
		List objList = IncidentalPriceDemand.query(objIPC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strEpcode = (String)objPD.getParameter(0, String.class);
		LoadIncidentalResult objLIR = IncidentalPriceDemand.load(strEpcode);
		
		return objLIR.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 7, this);

		IncidentalpriceColumns objIPColumns = (IncidentalpriceColumns)objPD.getParameter(0, 
				IncidentalpriceColumns.class);
		List listIPValueColumns = objPD.getParameterList(1, IncidentalpricevalueColumns.class);
		List listIPVBaseColumns = objPD.getParameterList(2, IncidentalvaluebaseColumns.class); 
		String strOperId = (String)objPD.getParameter(3, String.class);  
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(4, String.class));
		List listIPVChannelColumns = objPD.getParameterList(5, IncidentalstoragechannelColumns.class); 
		List listEnterprise = objPD.getParameterList(6, PriceenterpriseColumns.class); 
		
		IncidentalPrice objIncidentalPrice = new IncidentalPrice();
		SavedResult objSavedResult = objIncidentalPrice.save(objIPColumns, 
				listIPValueColumns, 
				listIPVBaseColumns,
				listIPVChannelColumns,
				listEnterprise,
				strOperId, 
				isIgnoreNotice);
		return objSavedResult.toString();
	}
	
	protected AExpressPrice getExpressPrice() {
		return new IncidentalPrice();
	}
}
