package kyle.leis.es.price.currency.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.currency.bl.CurrencyPrice;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.price.currency.da.CurrencyvalueColumns;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;
import kyle.leis.es.price.currency.dax.LoadCurrencyResult;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.sv.AExpressPriceService;

public class CurrencyService extends AExpressPriceService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CurrencyCondition objCurrencyCondition = (CurrencyCondition)objPD.getParameter(0, 
				CurrencyCondition.class);
		List objList = CurrencyPriceDemand.query(objCurrencyCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strEpcode = (String)objPD.getParameter(0, String.class);
		LoadCurrencyResult objLoadCurrencyResult = CurrencyPriceDemand.load(strEpcode);
		
		return objLoadCurrencyResult.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);

		CurrencyColumns objCurrencyColumns = (CurrencyColumns)objPD.getParameter(0, CurrencyColumns.class);
		List listCurrencyValue = objPD.getParameterList(1, CurrencyvalueColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(3, String.class));
		
		CurrencyPrice objCurrencyPrice = new CurrencyPrice();
		SavedResult objSavedResult = objCurrencyPrice.save(objCurrencyColumns, 
				listCurrencyValue, 
				strOperId, 
				isIgnoreNotice);
		return objSavedResult.toString();
	}
	
	protected AExpressPrice getExpressPrice() {
		return new CurrencyPrice();
	}
}
