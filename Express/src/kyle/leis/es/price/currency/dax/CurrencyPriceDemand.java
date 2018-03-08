package kyle.leis.es.price.currency.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.currency.da.CurrencyColumns;
import kyle.leis.es.price.currency.da.CurrencyCondition;
import kyle.leis.es.price.currency.da.CurrencyQuery;
import kyle.leis.es.price.currency.da.CurrencyvalueQuery;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricegroupDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiExpresspricekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPricegroup;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TepCurrency;
import kyle.leis.hi.TepExpressprice;

public class CurrencyPriceDemand {
	
	public static List query(CurrencyCondition objCurrencyCondition) 
	throws Exception {
		CurrencyQuery objCurrencyQuery = new CurrencyQuery();
		objCurrencyQuery.setCondition(objCurrencyCondition);
		return objCurrencyQuery.getResults();
	}
	
	public static LoadCurrencyResult load(String strEpcode) 
	throws Exception {
		LoadCurrencyResult objLCResult = new LoadCurrencyResult();
		objLCResult.setCurrencycolumns(loadCurrency(strEpcode));
		objLCResult.setCurrencyvalue(loadCurrencyvalue(strEpcode, false));
		return objLCResult;
	}	
	
	
	public static CurrencyColumns loadCurrency(String strEpcode) 
	throws Exception {
		CurrencyQuery objCurrencyQuery = new CurrencyQuery();
		objCurrencyQuery.setEpcode(strEpcode);
		List objList = objCurrencyQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (CurrencyColumns)objList.get(0);
	}	
	
	public static List loadCurrencyvalue(String strEpcode, 
			boolean isUseCache) throws Exception {
		CurrencyvalueQuery objCurrencyvalueQuery = new CurrencyvalueQuery();
		objCurrencyvalueQuery.setEpcode(strEpcode);
		objCurrencyvalueQuery.setUseCachesign(isUseCache);
		return objCurrencyvalueQuery.getResults();
	}
	
	public static void setExpressPriceByColumns(TepExpressprice objTepExpressprice,
			CurrencyColumns objCurrencyColumns,
			String strOperId, 
			Session objSession) throws Exception {
		objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(objCurrencyColumns.getEpependdate() + " 23:59:59"));
		objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(objCurrencyColumns.getEpepstartdate() + " 00:00:00"));
		objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		objTepExpressprice.setEpRemark(objCurrencyColumns.getEpepremark());
		objTepExpressprice.setEpWithdrawsign("N");
		if (!StringUtility.isNull(objCurrencyColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objCurrencyColumns.getEeeecode());
			objTepExpressprice.setTdiEnterpriseelement(objTEE);
		}
		if (!StringUtility.isNull(objCurrencyColumns.getPspscode())) {
			TdiPricestatus objTPS = TdiPricestatusDC.loadByKey(objCurrencyColumns.getPspscode());
			objTepExpressprice.setTdiPricestatus(objTPS);
		}
		TdiOperator objTOP = TdiOperatorDC.loadByKey(strOperId);
		objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
		TdiExpresspricekind objTEPK = (TdiExpresspricekind)objSession.load(TdiExpresspricekind.class, 
				IExpressPriceBasicData.PRICEKIND_CURRENCY);
		objTepExpressprice.setTdiExpresspricekind(objTEPK);
	}
	
	
	public static void setCurrencyByColumns(TepCurrency objTepCurrency,
			CurrencyColumns objCurrencyColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objCurrencyColumns.getCococode())) {
			TcoCorporation objTCO = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objCurrencyColumns.getCococode());
			objTepCurrency.setTcoCorporation(objTCO);
		}
		if (!StringUtility.isNull(objCurrencyColumns.getPgpgcode())) {
			TdiPricegroup objTdiPricegroup = TdiPricegroupDC.loadByKey(objCurrencyColumns.getPgpgcode());
			objTepCurrency.setTdiPricegroup(objTdiPricegroup);
		}
		TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objCurrencyColumns.getPkpkcode());
		objTepCurrency.setTdiProductkind(objTdiProductkind);
	}
	
}
