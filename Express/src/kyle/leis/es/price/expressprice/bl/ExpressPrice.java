package kyle.leis.es.price.expressprice.bl;

import java.util.HashSet;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.company.customer.dax.CustomerDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupColumns;
import kyle.leis.es.price.pricegroup.da.CustomerpricegroupCondition;
import kyle.leis.es.price.pricegroup.dax.CustomerPricegroupDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.productkind.da.ProductkindColumns;
import kyle.leis.fs.dictionary.productkind.da.ProductkindCondition;
import kyle.leis.fs.dictionary.productkind.dax.ProductkindDemand;
import kyle.leis.hi.TdiOperator;

public class ExpressPrice {
	public HashSet<String> searchProductKind(String strCocode, 
			String strDtcode, 
			String strEecode) throws Exception {
		return searchProductKind(strCocode,
				strDtcode,
				strEecode,
				"");
	}
	
	public HashSet<String> searchProductKind(String strCocode, 
			String strDtcode, 
			String strEecode,
			String strOperID) throws Exception {
		HashSet<String> hsProductKind = new HashSet<String>();
		String strEnterprise = SystempropertyDemand.getEnterprise();
		// �N�ٹ��ʲ���Ҫ��ѯ����,�ں�̨�����޸ıȽϷ��㣬����Ҫ���·�������
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SR")) {
			return getAllUseProduct();
		}
		// ����ϵͳȡ���Ŀͻ�
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("SLYIM")) {
			strCocode = CustomerDemand.getTopParentCustomer(strCocode);
		}		
		FreightpriceCondition objFPCondition = buildSPKFreightCondition(strCocode,
				strDtcode,
				strEecode);
		// �����˷Ѽ۸�
		List objList = FreightPriceDemand.queryFreightPrice(objFPCondition);
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				FreightpriceColumns objFPColumns =(FreightpriceColumns)objList.get(i);
				if (objFPColumns.getCococode().equals(strCocode))
					hsProductKind.add(objFPColumns.getPkpkcode());
			}
		}
		// ���ҿͻ��۸���
		CustomerpricegroupCondition objCPGCondition = buildSPKPriceGroupCondition(strCocode, 
				strEecode);
		objList = CustomerPricegroupDemand.query(objCPGCondition);
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				CustomerpricegroupColumns objCPGColumns = (CustomerpricegroupColumns)objList.get(i);
				if (objCPGColumns.getCococode().equals(strCocode))
					hsProductKind.add(objCPGColumns.getPkpkcode());
			}
		}		
		// ������
		if (!StringUtility.isNull(strEnterprise) && strEnterprise.startsWith("WC")) {
			// �����Ƶ��ͻ�����ʾ������
			if (!StringUtility.isNull(strOperID)) {
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperID);
				if (objTdiOperator.getTcoCorporation() != null)
					return hsProductKind;
			}
		}
		String strCommonpg = CacheContainerDemand.getCommonpricegroup();
		if (!StringUtility.isNull(strCommonpg)) {
			objFPCondition.setIsnullsign(" null ");
			objFPCondition.setPgcode(strCommonpg);
			objFPCondition.setCocode("");
			objList = FreightPriceDemand.queryFreightPrice(objFPCondition);
			if (objList != null && objList.size() > 0) {
				for (int i = 0; i < objList.size(); i++) {
					FreightpriceColumns objFPColumns =(FreightpriceColumns)objList.get(i);
					hsProductKind.add(objFPColumns.getPkpkcode());
				}
			}			
		}
		return hsProductKind;
	}
	
	private HashSet<String> getAllUseProduct() throws Exception {
		HashSet<String> hsProductKind = new HashSet<String>();
		
		ProductkindCondition objPKCondition = new ProductkindCondition();
		objPKCondition.setSscode("ON");
		objPKCondition.setUseCacheSign(true);
		List listResults = ProductkindDemand.query(objPKCondition);
		if (listResults == null || listResults.size() < 1)
			return hsProductKind;
		
		for (int i = 0; i < listResults.size(); i++) {
			ProductkindColumns objPKC = (ProductkindColumns)listResults.get(i);
			hsProductKind.add(objPKC.getPkpkcode());
		}
		return hsProductKind;
	}
	
	
	private FreightpriceCondition buildSPKFreightCondition(String strCocode, 
			String strDtcode, 
			String strEecode) {
		FreightpriceCondition objFPCondition = new FreightpriceCondition();
		
		objFPCondition.setCocode(strCocode);
		objFPCondition.setIsnullsign("not null");
		objFPCondition.setDtcode(strDtcode);
		objFPCondition.setEecode(strEecode);
		objFPCondition.setEpkcode(IExpressPriceBasicData.PRICEKIND_FREIGHT);
		objFPCondition.setEpstartdate(DateFormatUtility.getStandardSysdate().substring(0, 10));
		objFPCondition.setEpstartdate2(DateFormatUtility.getStandardSysdate().substring(0, 10));
		objFPCondition.setPdcode(IExpressPriceBasicData.PRICEDOMAIN_SALES);
		objFPCondition.setPscode(IExpressPriceBasicData.PRICE_STATUS_RELEASE);
		objFPCondition.setUseCacheSign(true);
		
		return objFPCondition;
	}
	
	private CustomerpricegroupCondition buildSPKPriceGroupCondition(String strCocode, 
			String strEecode) {
		CustomerpricegroupCondition objCPGCondition = new CustomerpricegroupCondition();
		
		objCPGCondition.setCocode(strCocode);
		objCPGCondition.setEecode(strEecode);
		objCPGCondition.setEpstartdate(DateFormatUtility.getStandardSysdate().substring(0, 10));
		objCPGCondition.setEpstartdate2(DateFormatUtility.getStandardSysdate().substring(0, 10));
		objCPGCondition.setPscode(IExpressPriceBasicData.PRICE_STATUS_RELEASE);
		objCPGCondition.setUseCacheSign(true);
		
		return objCPGCondition;
	}	
}
