package kyle.leis.es.price.freightprice.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.da.PriceenterpriseColumns;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.sv.AExpressPriceService;
import kyle.leis.es.price.freightprice.bl.FreightPrice;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.da.FreightpricevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevalueColumns;
import kyle.leis.es.price.freightprice.da.SurchargevaluebaseColumns;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.price.freightprice.dax.LoadResult;

public class FreightPriceService extends AExpressPriceService {
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		FreightpriceCondition objFPC = (FreightpriceCondition)objPD.getParameter(0, 
				FreightpriceCondition.class);
		List objList = FreightPriceDemand.queryFreightPrice(objFPC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strEpcode = (String)objPD.getParameter(0, String.class);
		LoadResult objLoadResult = FreightPriceDemand.load(strEpcode);
		
		return objLoadResult.toString();
	}
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 7, this);

		FreightpriceColumns objFPColumns = (FreightpriceColumns)objPD.getParameter(0, 
				FreightpriceColumns.class);
		List listFreightpricevalue = objPD.getParameterList(1, FreightpricevalueColumns.class);
		List listSurchargevalue = objPD.getParameterList(2, SurchargevalueColumns.class); 
		List listSurchargevaluebase = objPD.getParameterList(3, SurchargevaluebaseColumns.class); 
		String strOperId = (String)objPD.getParameter(4, String.class); 
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(5, String.class));
		List listEnterprise = objPD.getParameterList(6, PriceenterpriseColumns.class); 
		
		FreightPrice objFreightPrice = new FreightPrice();
		SavedResult objSavedResult = objFreightPrice.save(objFPColumns, 
				listFreightpricevalue, 
				listSurchargevalue, 
				listSurchargevaluebase, 
				strOperId, 
				isIgnoreNotice,
				listEnterprise);
		return objSavedResult.toString();
	}
	
	protected AExpressPrice getExpressPrice() {
		return new FreightPrice();
	}
}
