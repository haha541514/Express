package kyle.leis.es.company.customer.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.customer.da.CusoperationCondition;
import kyle.leis.es.company.customer.da.CusoperationQuery;
import kyle.leis.es.company.customer.da.CustomerColumns;
import kyle.leis.es.company.customer.da.CustomerCondition;
import kyle.leis.es.company.customer.da.CustomerQuery;
import kyle.leis.es.company.customer.da.CustomerforbillCondition;
import kyle.leis.es.company.customer.da.CustomerforbillQuery;
import kyle.leis.es.company.customer.da.MaxsamelevelcustomerColumns;
import kyle.leis.es.company.customer.da.MaxsamelevelcustomerQuery;
import kyle.leis.es.company.customer.da.SimplecustomerColumns;
import kyle.leis.es.company.customer.da.SimplecustomerCondition;
import kyle.leis.es.company.customer.da.SimplecustomerQuery;
import kyle.leis.es.company.supplier.da.SupplierColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TcoCustomerDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCustomertypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TcoCustomer;
import kyle.leis.hi.TcoSupplier;
import kyle.leis.hi.TdiCustomertype;
import kyle.leis.hi.TdiOperator;

public class CustomerDemand {
	public static List query(CustomerCondition objCustomerCondition) throws Exception {
		String strCocode = objCustomerCondition.getCocode();
		CustomerCondition objChangedCondition = objCustomerCondition;
		if (!StringUtility.isNull(strCocode)) {
			objChangedCondition = new CustomerCondition();
			objChangedCondition.setCocode(strCocode);
		}
		if(!StringUtility.isNull(objCustomerCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objCustomerCondition.getEestructurecode());
			objCustomerCondition.setEestructurecode(strEestructurecode);
		}
		CustomerQuery objCustomerQuery = new CustomerQuery();
		objCustomerQuery.setCondition(objChangedCondition);
		return objCustomerQuery.getResults();
	}
	
	public static List querySimpleCustomer(SimplecustomerCondition objSCCondition) throws Exception {
		SimplecustomerQuery objSimplecustomerQuery = new SimplecustomerQuery();
		objSimplecustomerQuery.setCondition(objSCCondition);
		return objSimplecustomerQuery.getResults();
	}
	
	public static SimplecustomerColumns loadSimpleCustomer(String strCocode) throws Exception {
		SimplecustomerCondition objSCCondition = new SimplecustomerCondition();
		objSCCondition.setCocode(strCocode);
		List listResults = querySimpleCustomer(objSCCondition);
		return (SimplecustomerColumns)listResults.get(0);
	}	
	
	
	public static CustomerColumns load(String strCocode) throws Exception {
		CustomerQuery objCustomerQuery = new CustomerQuery();
		objCustomerQuery.setCocode(strCocode);
		List objList = objCustomerQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (CustomerColumns)objList.get(0);
	}
	
	
	public static List queryForBill(CustomerforbillCondition objCFBCondition) throws Exception {
		CustomerforbillQuery objCFBQuery = new CustomerforbillQuery();
		
		if(!StringUtility.isNull(objCFBCondition.getEestructurecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objCFBCondition.getEestructurecode());
			objCFBCondition.setEestructurecode(strEestructurecode);
		}
		objCFBQuery.setCondition(objCFBCondition);
		return objCFBQuery.getResults();
	}
	
	public static String getTopParentCustomer(String strCocode) throws Exception {
		SimplecustomerColumns objSCColumns = loadSimpleCustomer(strCocode);
		String strStructurecode = objSCColumns.getCmcm_structruecode();
		if (StringUtility.isNull(strStructurecode))
			return strCocode;
		if (strStructurecode.indexOf("_") >= 0) {
			return strStructurecode.substring(0, strStructurecode.indexOf("_"));
		}
		return strCocode;
	}
	
	
	public static synchronized String buildCustomerStructure(String strCocodeParent) throws Exception {
		SimplecustomerColumns objSCColumns = loadSimpleCustomer(strCocodeParent);
		String strStructurecodePrefix = objSCColumns.getCmcm_structruecode(); 
		if (StringUtility.isNull(strStructurecodePrefix))
			strStructurecodePrefix = strCocodeParent;
		
		MaxsamelevelcustomerQuery objMSLCQ = new MaxsamelevelcustomerQuery();
		objMSLCQ.setCocodeparent(strCocodeParent);
		List listResult = objMSLCQ.getResults();
		if (listResult == null || listResult.size() < 1)
			return strStructurecodePrefix + "_001";
		MaxsamelevelcustomerColumns objMLCC = (MaxsamelevelcustomerColumns)listResult.get(0);
		String strMaxstructruecode = objMLCC.getMaxstructruecode();
		if (StringUtility.isNull(strMaxstructruecode))
			return strStructurecodePrefix + "_001";
		strMaxstructruecode = strMaxstructruecode.substring(strMaxstructruecode.length() - 3);
		int iNextstructruecode = Integer.parseInt(strMaxstructruecode) + 1;
		return strStructurecodePrefix + "_" + StringUtility.replaceZeroToLength(String.valueOf(iNextstructruecode), 3);
	}
	
	
	
	public static void setCustomerByColumns(TcoCustomer objTcoCustomer,
			CustomerColumns objCustomerColumns) throws Exception {
		String strCreditLimit = objCustomerColumns.getCmcmcreditlimit();
		if (StringUtility.isNull(strCreditLimit))
			strCreditLimit = "0";
		TdiCustomertype objTCT = TdiCustomertypeDC.loadByKey(objCustomerColumns.getCtctcode());
		objTcoCustomer.setTdiCustomertype(objTCT);
		objTcoCustomer.setCmPayablebankaccount(objCustomerColumns.getCmcmpayablebankaccount());
		
		String strOperIdSale = objCustomerColumns.getSsopopid();
		String strOperIdCustomer = objCustomerColumns.getCsopopid();
		String strOperIdDun = objCustomerColumns.getDunopopid();
		
		// 销售员
		if (!StringUtility.isNull(strOperIdSale)) {
			TdiOperator objTOP = TdiOperatorDC.loadByKey(strOperIdSale);
			objTcoCustomer.setTdiOperatorByCmOpIdSale(objTOP);
		}
		// 客服员
		if (!StringUtility.isNull(strOperIdCustomer)) {
			TdiOperator objTOP = TdiOperatorDC.loadByKey(strOperIdCustomer);
			objTcoCustomer.setTdiOperatorByCmOpIdCservice(objTOP);
		}
		// 催款员
		if (!StringUtility.isNull(strOperIdDun)) {
			TdiOperator objTOP = TdiOperatorDC.loadByKey(strOperIdDun);
			objTcoCustomer.setTdiOperatorByCmOpIdDun(objTOP);
		}
		// 单月最大货量
		if (StringUtility.isNull(objCustomerColumns.getCmcmmaxreceivabletotal())) {
			objTcoCustomer.setCmMaxrttype(null);
			objTcoCustomer.setCmMaxreceivabletotal(new BigDecimal("0"));
		} else {
			objTcoCustomer.setCmMaxrttype(objCustomerColumns.getCmcmmaxrttype());
			objTcoCustomer.setCmMaxreceivabletotal(new BigDecimal(objCustomerColumns.getCmcmmaxreceivabletotal()));
		}
		if (StringUtility.isNull(objCustomerColumns.getCmcmprintchildlabelsign()))
			objTcoCustomer.setCmPrintchildlabelsign("N");
		else
			objTcoCustomer.setCmPrintchildlabelsign(objCustomerColumns.getCmcmprintchildlabelsign());
		
		if (StringUtility.isNull(objCustomerColumns.getCmcmwebinputchangeswbsign()))
			objTcoCustomer.setCmWebinputchangeswbsign("N");
		else
			objTcoCustomer.setCmWebinputchangeswbsign(objCustomerColumns.getCmcmprintchildlabelsign());
		// 欠费是否允许打印标签
		if (StringUtility.isNull(objCustomerColumns.getCmcmarrearprintlabelsign()))
			objTcoCustomer.setCmArrearprintlabelsign("Y");
		else
			objTcoCustomer.setCmArrearprintlabelsign(objCustomerColumns.getCmcmarrearprintlabelsign());
		
		// objTcoCustomer.setCmCreditlimit(new BigDecimal(strCreditLimit));
		objTcoCustomer.setCmInvoicesign(objCustomerColumns.getCmcminvoicesign());
		objTcoCustomer.setCmOdaholdsign(objCustomerColumns.getCmcmodaholdsign());
		objTcoCustomer.setCmOdanoticesign(objCustomerColumns.getCmcmodanoticesign());
		objTcoCustomer.setCmWebtrackneedlogin(objCustomerColumns.getCmcmwebtrackneedlogin());
		objTcoCustomer.setCmAllowprintlabelsign(objCustomerColumns.getCmcmallowprintlabelsign());
		// 公司层级关系以及父公司
		if (!StringUtility.isNull(objCustomerColumns.getCmparentcocode())) {
			TcoCustomer objParentTcoCustomer = TcoCustomerDC.loadByKey(objCustomerColumns.getCmparentcocode());
			objTcoCustomer.setTcoCustomer(objParentTcoCustomer);
		}
		if (StringUtility.isNull(objCustomerColumns.getCmcmstructruecode())) {
			if (StringUtility.isNull(objCustomerColumns.getCmcocode()) && 
					!StringUtility.isNull(objCustomerColumns.getCmparentcocode()))
				objTcoCustomer.setCmStructruecode(CustomerDemand.buildCustomerStructure(objCustomerColumns.getCmparentcocode()));
		}
		else {
			objTcoCustomer.setCmStructruecode(objCustomerColumns.getCmcmstructruecode());
		}
	}
	
	public static void setSupplierByColumns(TcoSupplier objTcoSupplier,
			SupplierColumns objSupplierColumns) throws Exception {
		objTcoSupplier.setSpAccount(objSupplierColumns.getSpspaccount());
		objTcoSupplier.setSpManifestseriesnumber(objSupplierColumns.getSpspmanifestseriesnumber());
	}	
	
	public static List queryCusSORestrict(String cmcoCode) throws Exception{
		CusoperationQuery objCusperationQuery = new CusoperationQuery();
		CusoperationCondition objCusperationCondition = new CusoperationCondition();
		objCusperationCondition.setCmcocode(cmcoCode);
		objCusperationQuery.setCondition(objCusperationCondition);
		return objCusperationQuery.getResults();
	}
	
	public static List queryCusSORestrict(CusoperationCondition objCusperationCondition) 
	throws Exception{
		CusoperationQuery objCusperationQuery = new CusoperationQuery();
		objCusperationQuery.setCondition(objCusperationCondition);
		return objCusperationQuery.getResults();
	}	
}
