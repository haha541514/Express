package kyle.leis.es.businessrule.productrule.sv;

import java.util.List;

import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.businessrule.businessrules.bl.ABusinessrule;
import kyle.leis.es.businessrule.businessrules.sv.ABusinessruleService;
import kyle.leis.es.businessrule.productrule.bl.Productrule;
import kyle.leis.es.businessrule.productrule.da.ChannelforproductCondition;
import kyle.leis.es.businessrule.productrule.da.ChannelproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.CorporationproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.DistrictproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.da.ProductruleCondition;
import kyle.leis.es.businessrule.productrule.dax.LoadProductruleResult;
import kyle.leis.es.businessrule.productrule.dax.ProductruleDemand;
import kyle.leis.es.price.expressprice.dax.SavedResult;

public class ProductruleService extends ABusinessruleService {

	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		String strBrId = (String)objPD.getParameter(0, String.class);
		LoadProductruleResult objLoadResult = ProductruleDemand.loadResults(strBrId);
		
		return objLoadResult.toString();
	}
	
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ProductruleCondition objProductruleCondition = (ProductruleCondition)objPD.getParameter(0, 
				ProductruleCondition.class);
		List objList = ProductruleDemand.query(objProductruleCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String queryChannelOfProduct(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		ChannelforproductCondition objCFPCondition = (ChannelforproductCondition)objPD.getParameter(0, 
				ChannelforproductCondition.class);
		List objList = ProductruleDemand.queryChannelOfProduct(objCFPCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}	
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 6, this);

		ProductruleColumns objProductruleColumns = (ProductruleColumns)objPD.getParameter(0, 
				ProductruleColumns.class);
		
		List listChnPRColumns = objPD.getParameterList(1, ChannelproductruleColumns.class);
		List listDistrictPRColumns = objPD.getParameterList(2, DistrictproductruleColumns.class);
		List listCorporationPRColumns = objPD.getParameterList(3, CorporationproductruleColumns.class);
		String strOperId = (String)objPD.getParameter(4, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(5, String.class));
		
		Productrule objProductrule = new Productrule();
		SavedResult objSavedResult = objProductrule.save(objProductruleColumns, 
				listChnPRColumns, 
				listDistrictPRColumns, 
				listCorporationPRColumns,
				strOperId, 
				isIgnoreNotice);
		
		return objSavedResult.toString();
	}
	
	protected ABusinessrule getBusinessrule() {
		return new Productrule();
	}
}
