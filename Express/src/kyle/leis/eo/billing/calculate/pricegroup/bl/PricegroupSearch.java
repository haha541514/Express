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
	 * �����˷��������ҿͻ��۸���
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
	 * ���ҿͻ��۸���
	 * @param objCPGCondition
	 * @param strFkcode
	 * @return
	 * @throws Exception
	 */
	public String searchPricegroup(CustomerpricegroupCondition objCPGCondition, 
			String strFkcode) throws Exception {
		// ���ҿͻ��۸���
		objCPGCondition.setUseCacheSign(true);
		CustomerpricegroupColumns objCPGColumns = searchCustomerpricegroup(objCPGCondition);
		if (objCPGColumns == null)
			return "";
		// ���ҿͻ��۸���ֵ
		String strEpcode = objCPGColumns.getCpgepcode();
		if (!StringUtility.isNull(strEpcode)) {
			String strPgcode = searchCustomerPGValue(strEpcode, strFkcode);
			if (!StringUtility.isNull(strPgcode) &&
					strPgcode.equals(IFeeCalculateBasicData.SEARCH_PRICE_ERROR))
				return "";
			if (!StringUtility.isNull(strPgcode))
				return strPgcode;
		}
		// ���Ҽ۸�������
		String strPgkcode = objCPGColumns.getPgkpgkcode();
		// ����Ĭ�ϵļ۸�������
		if (StringUtility.isNull(strPgkcode))
			strPgkcode = "";
		// ���Ҽ۸���
		
		// ���ҵȼ�
		
		return "";
	}
	
	/**
	 * ���ҿͻ��۸���
	 * @param objCPGCondition
	 * @return
	 * @throws Exception
	 */
	private CustomerpricegroupColumns searchCustomerpricegroup(CustomerpricegroupCondition objCPGCondition) 
	throws Exception {
		List objList = CustomerPricegroupDemand.query(objCPGCondition);
		// δ���ҵ��κοͻ��۸���
		if (objList == null || objList.size() < 1) return null;
		// ���ҵ����м۸���򷵻ش���
		if (objList != null && objList.size() > 1) { 
			s_objLogger.warning("���ҵ����пͻ��۸���ֵ������Ʒ�����");
			throw (new Exception("���ҵ����пͻ��۸���ֵ������Ʒ�����"));
		}
		// �պò��ҵ�һ�м۸��
		return (CustomerpricegroupColumns)objList.get(0);
	}
	
	/**
	 * ���ҿͻ��۸���ֵ
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
		// ���ҵ����м۸���򷵻ش���
		if (objList != null && objList.size() > 1) { 
			s_objLogger.warning("���ҵ�����" + strFkcode + "�Ŀͻ��۸���ֵ������Ʒ�����");
			return IFeeCalculateBasicData.SEARCH_PRICE_ERROR;
		}
		CustomergroupandvalueColumns objCPGVColumns = (CustomergroupandvalueColumns)objList.get(0);
		return objCPGVColumns.getPgpgcode();
	}
}
