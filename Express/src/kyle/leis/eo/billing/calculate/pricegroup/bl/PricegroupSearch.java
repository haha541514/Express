package kyle.leis.eo.billing.calculate.pricegroup.bl;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.calculate.pricegroup.dax.PricegroupDemand;
import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.pricegroup.da.CustomergroupandvalueColumns;
import kyle.leis.es.price.pricegroup.da.CustomergroupandvalueCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;

public class PricegroupSearch {
	static Logger s_objLogger = Logger.getLogger(PricegroupSearch.class.getName());
	
	public String searchPricegroup(CurrencyCondition objCurrencyCondition) throws Exception {
		return searchPricegroup(PricegroupDemand.transferByCurrency(objCurrencyCondition), 
				IFeeCalculateBasicData.FEEKIND_CURRENCY);
	}	
	
	public String searchPricegroup(IncidentalpriceCondition objIPCondition) throws Exception {
		return searchPricegroup(PricegroupDemand.transferByIncidental(objIPCondition), 
				IFeeCalculateBasicData.FEEKIND_INCIDENTAL);
	}
	
	/**
	 * 根据运费条件查找客户价格组
	 * @param objFPCondition
	 * @param strFkcode
	 * @return
	 * @throws Exception
	 */
	public String searchPricegroup(FreightpriceCondition objFPCondition) throws Exception {
		return searchPricegroup(PricegroupDemand.transferByFreight(objFPCondition), 
				IFeeCalculateBasicData.FEEKIND_FREIGHT);
	}
	
	/**
	 * 查找客户价格组
	 * @param objCPGCondition
	 * @param strFkcode
	 * @return
	 * @throws Exception
	 */
	public String searchPricegroup(CustomerpricegroupCondition objCPGCondition, 
			String strFkcode) throws Exception {
		// 查找客户价格组
		objCPGCondition.setUseCacheSign(true);
		CustomerpricegroupColumns objCPGColumns = searchCustomerpricegroup(objCPGCondition);
		if (objCPGColumns == null)
			return "";
		// 查找客户价格组值
		String strEpcode = objCPGColumns.getCpgepcode();
		if (!StringUtility.isNull(strEpcode)) {
			String strPgcode = searchCustomerPGValue(strEpcode, strFkcode);
			if (!StringUtility.isNull(strPgcode) &&
					strPgcode.equals(IFeeCalculateBasicData.SEARCH_PRICE_ERROR))
				return "";
			if (!StringUtility.isNull(strPgcode))
				return strPgcode;
		}
		// 查找价格组种类
		String strPgkcode = objCPGColumns.getPgkpgkcode();
		// 查找默认的价格组种类
		if (StringUtility.isNull(strPgkcode))
			strPgkcode = "";
		// 查找价格组
		
		// 查找等级
		
		return "";
	}
	
	/**
	 * 查找客户价格组
	 * @param objCPGCondition
	 * @return
	 * @throws Exception
	 */
	private CustomerpricegroupColumns searchCustomerpricegroup(CustomerpricegroupCondition objCPGCondition) 
	throws Exception {
		List objList = CustomerPricegroupDemand.query(objCPGCondition);
		// 未查找到任何客户价格组
		if (objList == null || objList.size() < 1) return null;
		// 查找到多行价格表则返回错误
		if (objList != null && objList.size() > 1) { 
			s_objLogger.warning("查找到多行客户价格组值，请检查计费条件");
			throw (new Exception("查找到多行客户价格组值，请检查计费条件"));
		}
		// 刚好查找到一行价格表
		return (CustomerpricegroupColumns)objList.get(0);
	}
	
	/**
	 * 查找客户价格组值
	 * @param strEpcode
	 * @return
	 * @throws Exception
	 */
	private String searchCustomerPGValue(String strEpcode, 
			String strFkcode) throws Exception {
		CustomergroupandvalueCondition objCPGVCondition = new CustomergroupandvalueCondition();
		objCPGVCondition.setUseCacheSign(true);
		objCPGVCondition.setEpcode(strEpcode);
		objCPGVCondition.setFkcode(strFkcode);
		List objList = CustomerPricegroupDemand.queryPGAndValue(objCPGVCondition);
		if (objList == null || objList.size() < 1) return "";
		// 查找到多行价格表则返回错误
		if (objList != null && objList.size() > 1) { 
			s_objLogger.warning("查找到多行" + strFkcode + "的客户价格组值，请检查计费条件");
			return IFeeCalculateBasicData.SEARCH_PRICE_ERROR;
		}
		CustomergroupandvalueColumns objCPGVColumns = (CustomergroupandvalueColumns)objList.get(0);
		return objCPGVColumns.getPgpgcode();
	}
}
