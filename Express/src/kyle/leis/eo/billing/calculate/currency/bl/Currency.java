package kyle.leis.eo.billing.calculate.currency.bl;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.da.CurrencyvalueColumns;
import kyle.leis.es.price.currency.dax.CurrencyPriceDemand;

public class Currency {
	
	public String getCurrencyrate(String strCkcode) throws Exception {
		if ("EUR".equals(strCkcode))
			return "6.78";
		else if ("GBP".equals(strCkcode))
			return "9.65";	
		else if ("JPY".equals(strCkcode))
			return "0.05";	
		else if ("KRW".equals(strCkcode))
			return "0.005";	
		else if ("HKD".equals(strCkcode))
			return "0.82";
		else if ("AED".equals(strCkcode))
			return "1.73";	
		else if ("RMB".equals(strCkcode))
			return "1";
		else if ("USD".equals(strCkcode))
			return "6.39";	
		else if ("CAD".equals(strCkcode))
			return "4.79";
		else if ("AUD".equals(strCkcode))
			return "4.61";		
		else
			return "0";
	}	
	
	
	/**
	 * 获得汇率值
	 * @param strCocode
	 * @param strPkcode
	 * @param strSearchDate
	 * @param strEecode
	 * @param strCkcodeChange
	 * @return
	 * @throws Exception
	 */
	public String getCurrencyrate(String strCocode,
			String strPkcode,
			String strSearchDate,
			String strEecode,
			String strCkcodeChange) throws Exception {
		return getCurrencyrate(strCocode,
				strPkcode,
				strSearchDate,
				strEecode,
				"RMB",
				strCkcodeChange);
	}
	
	public String getCurrencyrate(String strCocode, 
			String strPkcode, 
			String strSearchDate,
			String strEecode,
			String strCkcodeBase,
			String strCkcodeChange) throws Exception {
		if (strCkcodeBase.equals(strCkcodeChange)) 
			return "1";
		if (StringUtility.isNull(strSearchDate))
			strSearchDate = DateFormatUtility.getStandardSysdate().substring(0, 10);
		// 查找价格表
		CurrencySearch objCurrencySearch = new CurrencySearch();
		CurrencyColumns objCurrencyColumns = objCurrencySearch.searchCurrency(strCocode, 
				strPkcode, 
				strSearchDate, 
				strEecode);
		// 装载汇率值
		List objList = CurrencyPriceDemand.loadCurrencyvalue(objCurrencyColumns.getCuepcode(), 
				true);
		if (objList == null || objList.size() < 1) return "0";
		// 查找币种
		for (int i = 0; i < objList.size(); i++) {
			CurrencyvalueColumns objCValueColumns = (CurrencyvalueColumns)objList.get(i);
			if (objCValueColumns.getCkbckcode().equals(strCkcodeBase) &&
					objCValueColumns.getCkcckcode().equals(strCkcodeChange))
				return objCValueColumns.getCvcvcurrencyrate();
		}
		return "0";
	}
	
}
