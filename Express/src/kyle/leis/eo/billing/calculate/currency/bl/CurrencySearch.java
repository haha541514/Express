package kyle.leis.eo.billing.calculate.currency.bl;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.dax.CurrencyDemand;
import kyle.leis.eo.billing.calculate.freight.bl.FreightSearch;
import kyle.leis.eo.billing.calculate.pricegroup.bl.PricegroupSearch;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;

public class CurrencySearch {
	static Logger s_objLogger = Logger.getLogger(FreightSearch.class.getName());
	
	public CurrencyColumns searchCurrency(String strCocode, 
			String strPkcode, 
			String strSearchDate,
			String strEecode) throws Exception {
		CurrencyCondition objCurrencyCondition = CurrencyDemand.transferToCondition(strCocode, 
				strPkcode, 
				strSearchDate, 
				strEecode);
		objCurrencyCondition.setUseCacheSign(true);
		return searchCurrency(objCurrencyCondition);
	}
	
	public CurrencyColumns searchCurrency(CurrencyCondition objCurrencyCondition) 
	throws Exception {
		// ����ԭ�۸��
		CurrencyCondition objOldCurrencyCondition = new CurrencyCondition();
		objOldCurrencyCondition.setFields(objCurrencyCondition.getFields());
		CurrencyColumns objCurrencyColumns = null;
		if (!StringUtility.isNull(objCurrencyCondition.getCocode())) {
			// ����˾���Ҽ۸��
			objCurrencyCondition.setPgcode("");
			// if (StringUtility.isNull(objCurrencyCondition.getPkcode()))
			objCurrencyCondition.setPkcode("A");
			objCurrencyCondition.setIspgcode("null");
			objCurrencyColumns = searchCurrencyPrice(objCurrencyCondition);
			if (objCurrencyColumns != null) return objCurrencyColumns;
			// ���ҵȼ�
			PricegroupSearch objPricegroupSearch = new PricegroupSearch();
			String strPricegroup = objPricegroupSearch.searchPricegroup(objCurrencyCondition);
			if (!StringUtility.isNull(strPricegroup)) {
				objCurrencyCondition.setIspgcode("");
				objCurrencyCondition.setPgcode(strPricegroup);
				objCurrencyCondition.setCocode("");
				objCurrencyCondition.setIscocode("null");
				objCurrencyColumns = searchCurrencyPrice(objCurrencyCondition);
				if (objCurrencyColumns != null) return objCurrencyColumns;
			}
		}
		// ����ȱʡ��Ʒ
		/*
		if (objCurrencyColumns == null &&
				!StringUtility.isNull(objOldCurrencyCondition.getPkcode()) &&
				!objOldCurrencyCondition.getPkcode().equals("A")) {
		*/
		if (objCurrencyColumns == null) {		
			objCurrencyCondition.setPgcode("");
			objCurrencyCondition.setPkcode("A");
			objCurrencyCondition.setCocode("");
			objCurrencyCondition.setIscocode("null");
			objCurrencyCondition.setIspgcode("");
			return searchCurrencyPrice(objCurrencyCondition);
		}
		return null;
	}
	
	private CurrencyColumns searchCurrencyPrice(CurrencyCondition objCurrencyCondition) 
	throws Exception {
		List objList = CurrencyPriceDemand.query(objCurrencyCondition);
		if (objList != null && objList.size() > 1) {
			s_objLogger.warning("���һ���ʱ�����������ҵ��������ϵĻ��ʼ۸��");
			throw (new Exception ("�������ʱ�����������ҵ��������ϵĻ��ʼ۸��"));
		}
		if (objList == null || objList.size() < 1) return null;
		return (CurrencyColumns)objList.get(0);
	}
}
