package kyle.leis.es.price.pricegroup.dax;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.pricegroup.da.CustomergroupandvalueCondition;
import kyle.leis.es.price.pricegroup.da.CustomergroupandvalueQuery;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupQuery;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueCondition;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupvalueQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpresspricekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricegroupDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricegroupkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiExpresspricekind;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPricegroup;
import kyle.leis.hi.TdiPricegroupkind;
import kyle.leis.hi.TdiPricestatus;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TepCustomerpricegroup;
import kyle.leis.hi.TepCustomerpricegroupvalue;
import kyle.leis.hi.TepExpressprice;

public class CustomerPricegroupDemand {
	public static List query(CustomerpricegroupCondition objCPGCondition) 
	throws Exception {
		CustomerpricegroupCondition objCPGChanged = objCPGCondition;
		String strEpcode = objCPGCondition.getEpcode();
		if (!StringUtility.isNull(objCPGCondition.getEpstartdate()))
			objCPGChanged.setEpstartdate2(objCPGCondition.getEpstartdate());
		
		if (!StringUtility.isNull(strEpcode))
		{
			objCPGChanged = new CustomerpricegroupCondition();
			objCPGChanged.setEpcode(strEpcode);
		}
		CustomerpricegroupQuery objCPGQuery = new CustomerpricegroupQuery();
		objCPGQuery.setCondition(objCPGChanged);
		return objCPGQuery.getResults();
	}
	
	public static List queryPGAndValue(CustomergroupandvalueCondition objCGAVCondition)
	throws Exception {
		CustomergroupandvalueCondition objCGAVChanged = objCGAVCondition;
		if (!StringUtility.isNull(objCGAVCondition.getEpstartdate()))
			objCGAVChanged.setEpstartdate2(objCGAVCondition.getEpstartdate());
		
		/*
		String strEpcode = objCGAVCondition.getEpcode();
		if (!StringUtility.isNull(strEpcode))
		{
			objCGAVChanged = new CustomergroupandvalueCondition();
			objCGAVChanged.setEpcode(strEpcode);
		}*/
		CustomergroupandvalueQuery objCPGVQuery = new CustomergroupandvalueQuery();
		objCPGVQuery.setCondition(objCGAVChanged);
		return objCPGVQuery.getResults();
	}
	
	public static List loadCPriceGroupvalue(String strEpcode) 
	throws Exception {
		CustomerpricegroupvalueCondition objCPGVCondition = new CustomerpricegroupvalueCondition();
		objCPGVCondition.setEpcode(strEpcode);
		
		CustomerpricegroupvalueQuery objCPGVQuery = new CustomerpricegroupvalueQuery();
		objCPGVQuery.setCondition(objCPGVCondition);
		return objCPGVQuery.getResults();
	}
	
	public static CustomerpricegroupColumns loadCPriceGroup(String strEpcode)
	throws Exception {
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		objCPGCondition.setEpcode(strEpcode);
		List objList = query(objCPGCondition);
		if (objList == null || objList.size() < 1) return null;
		
		return (CustomerpricegroupColumns)objList.get(0);
	}
	
	public static LoadCustomergroupResult load(String strEpcode) 
	throws Exception {
		LoadCustomergroupResult objLCGR = new LoadCustomergroupResult();
		objLCGR.setCPGColumns(loadCPriceGroup(strEpcode));
		objLCGR.setCPGValueColumns(loadCPriceGroupvalue(strEpcode));
		return objLCGR;
	}
	
	public static void setExpressPriceByColumns(TepExpressprice objTepExpressprice,
			CustomerpricegroupColumns objCPGColumns,
			String strOperId, 
			Session objSession) throws Exception {
		objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(objCPGColumns.getEpependdate() + " 23:59:59"));
		objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(objCPGColumns.getEpepstartdate() + " 00:00:00"));
		objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		objTepExpressprice.setEpRemark(objCPGColumns.getEpepremark());
		objTepExpressprice.setEpWithdrawsign("N");
		if (!StringUtility.isNull(objCPGColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objCPGColumns.getEeeecode());
			objTepExpressprice.setTdiEnterpriseelement(objTEE);
		}
		if (!StringUtility.isNull(objCPGColumns.getPspscode())) {
			TdiPricestatus objTPS = TdiPricestatusDC.loadByKey(objCPGColumns.getPspscode());
			objTepExpressprice.setTdiPricestatus(objTPS);
		}
		TdiOperator objTOP = (TdiOperator)objSession.load(TdiOperator.class, 
				Long.parseLong(strOperId));
		objTepExpressprice.setTdiOperatorByEpOpIdModify(objTOP);
		
		
		TdiExpresspricekind objTEPK = TdiExpresspricekindDC.loadByKey(IExpressPriceBasicData.PRICEKIND_CUSTOMER);
		objTepExpressprice.setTdiExpresspricekind(objTEPK);			
	}
	
	public static void setCustomerPricegroupByColumns(TepCustomerpricegroup objTCustomerpricegroup,
			CustomerpricegroupColumns objCPGColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objCPGColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objCPGColumns.getCococode());
			objTCustomerpricegroup.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objCPGColumns.getPgkpgkcode())) {
			TdiPricegroupkind objTdiPricegroupkind = TdiPricegroupkindDC.loadByKey(objCPGColumns.getPgkpgkcode());
			objTCustomerpricegroup.setTdiPricegroupkind(objTdiPricegroupkind);
		}
		if (!StringUtility.isNull(objCPGColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objCPGColumns.getPkpkcode());
			objTCustomerpricegroup.setTdiProductkind(objTdiProductkind);
		}
	}
	
	public static void setCPGroupvalueByColumns(TepCustomerpricegroupvalue objTCPGroupvalue,
			CustomerpricegroupvalueColumns objCPGValueColumns,
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objCPGValueColumns.getFkfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objCPGValueColumns.getFkfkcode());
			objTCPGroupvalue.setTdiFeekind(objTdiFeekind);
		}
		if (!StringUtility.isNull(objCPGValueColumns.getPgpgcode())) {
			TdiPricegroup objTdiPricegroup = TdiPricegroupDC.loadByKey(objCPGValueColumns.getPgpgcode());
			objTCPGroupvalue.setTdiPricegroup(objTdiPricegroup);
		}
	}
	
}
