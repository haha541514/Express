package kyle.leis.es.price.pricegroup.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.price.expressprice.bl.AExpressPrice;
import kyle.leis.es.price.expressprice.dax.SavedResult;
import kyle.leis.es.price.expressprice.sv.AExpressPriceService;
import kyle.leis.es.price.pricegroup.bl.CustomerPricegroup;
import kyle.leis.es.price.pricegroup.da.CustomergroupandvalueCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueColumns;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.es.price.pricegroup.dax.LoadCustomergroupResult;

public class CustomerPricegroupService extends AExpressPriceService {
    /**
     * 普通查询
     * @param objPD
     * @return
     * @throws Exception
     */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomerpricegroupCondition objCPGCondition = (CustomerpricegroupCondition)objPD.getParameter(0, 
				CustomerpricegroupCondition.class);
		List objList = CustomerPricegroupDemand.query(objCPGCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 根据价格类型、价格组查询
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryPGAndValue(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CustomergroupandvalueCondition objCPGAVCondition = (CustomergroupandvalueCondition)objPD.getParameter(0, 
				CustomergroupandvalueCondition.class);
		List objList = CustomerPricegroupDemand.queryPGAndValue(objCPGAVCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();		
	}
	
	/**
	 * 装载记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strEpcode = (String)objPD.getParameter(0, String.class);
		LoadCustomergroupResult objLCPGResult = CustomerPricegroupDemand.load(strEpcode);
		
		return objLCPGResult.toString();		
	}
	
	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);

		CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objPD.getParameter(0, CustomerpricegroupColumns.class);
		List listCPGValueColumns = objPD.getParameterList(1, CustomerpricegroupvalueColumns.class);
		String strOperId = (String)objPD.getParameter(2, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(3, String.class));
		
		CustomerPricegroup objCustomerPricegroup = new CustomerPricegroup();
		SavedResult objSavedResult = objCustomerPricegroup.save(objCPGColumns, 
				listCPGValueColumns, 
				strOperId, 
				isIgnoreNotice);
		
		return objSavedResult.toString();		
	}
	
	protected AExpressPrice getExpressPrice() {
		return new CustomerPricegroup();
	}

}
