package kyle.leis.eo.billing.calculate.freight.bl;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.pricegroup.bl.PricegroupSearch;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.freightprice.da.FreightpriceColumns;
import kyle.leis.es.price.freightprice.da.FreightpriceCondition;
import kyle.leis.es.price.freightprice.dax.FreightPriceDemand;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;

public class FreightSearch {
	static Logger s_objLogger = Logger.getLogger(FreightSearch.class.getName());
	// �����˷Ѽ۸��
	public FreightpriceColumns search(FreightpriceCondition objFPCondition) 
	throws Exception {
		FreightpriceCondition objOldFPCondition = new FreightpriceCondition();
		objOldFPCondition.setFields(objFPCondition.getFields());
		objOldFPCondition.setUseCacheSign(true);
		// ���Ȳ��ҹ�˾�۸�
		try {
			objFPCondition.setUseCacheSign(true);
			FreightpriceColumns objFPColumns = searchCorFreightprice(objFPCondition);
			// �ɹ�����δ���ҵ��۸�
			if (objFPColumns == null &&
					objFPCondition.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS)) {
				s_objLogger.warning("���Ҳɹ��˷Ѽ۸�ʱ��δ�ҵ��κμ۸������Ʒ�����");
				return null;
			}
			// �ɹ��۸�
			if (objFPColumns != null)
				// objFPCondition.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS))
				return objFPColumns;
			// ���ۼ����Ҳ�����Ӧ�Ŀͻ��۸�����ҿͻ���Ӧ�ļ۸���
			// ���Ҽ۸���
			objFPCondition.setCocode("");
			objFPCondition.setChncode("");
			PricegroupSearch objPricegroupSearch = new PricegroupSearch();
			String strPricegroup = objPricegroupSearch.searchPricegroup(objOldFPCondition);
			// δ���ü۸�������ҹ�����
			if (StringUtility.isNull(strPricegroup)) {
				objFPColumns = searchCommonPrice(objFPCondition);
				if (objFPColumns != null) {
					return objFPColumns;
				} else {
					s_objLogger.warning("δ���ҵ��κοͻ��۸��飬����Ʒ�����");
					return null;				
				}
			}
			// �ͻ��۸���ֵ
			int iPricegroup = Integer.parseInt(strPricegroup);
			// ѭ���ݼ�ȡ�۸��
			while (iPricegroup >= 0) {
				objFPCondition.setPgcode(String.valueOf(iPricegroup));
				objFPColumns = searchFreightprice(objFPCondition);	
				if (objFPColumns != null && !StringUtility.isNull(objFPColumns.getFpepcode()))
					return objFPColumns;
				iPricegroup--;
			}
			// ��Ȼδ��ѯ���κμ۸�����ҹ�����
			return searchCommonPrice(objFPCondition);
		} finally {
			objFPCondition.setFields(objOldFPCondition.getFields());
		}
	}
	
	/**
	 * ���ҹ�˾�۸��
	 * @param objFPCondition
	 * @return
	 * @throws Exception
	 */
	private FreightpriceColumns searchCorFreightprice(FreightpriceCondition objFPCondition) 
	throws Exception {
		objFPCondition.setPgcode("");
		return searchFreightprice(objFPCondition);		
	}
	
	/**
	 * ���Ҽ۸���жϽ���Ƿ����Ҫ��
	 * @param objFPCondition
	 * @return
	 * @throws Exception
	 */
	private FreightpriceColumns searchFreightprice(FreightpriceCondition objFPCondition) 
	throws Exception {
		List objList = FreightPriceDemand.queryFreightPrice(objFPCondition);
		if (objList != null && objList.size() > 1) {
			s_objLogger.warning("���ҹ�˾�˷Ѽ۸�ʱ���ҵ�1�����ϵļ۸������Ʒ�����");
			throw new Exception("���ҹ�˾�˷Ѽ۸�ʱ���ҵ�1�����ϵļ۸������Ʒ�����");
		}
		// �����ҵ��۸����ֱ�ӷ�����ȷ���
		if (objList != null && objList.size() == 1) {
			FreightpriceColumns objFPColumns = (FreightpriceColumns)objList.get(0);
			return objFPColumns;
		} else if (StringUtility.isNull(objFPCondition.getEpecode()) && 
				!StringUtility.isNull(objFPCondition.getEecode())){
			// ���Ҽ۸�֧�ֵĶ���ֹ�˾
			String strEecode = objFPCondition.getEecode();
			try {
				objFPCondition.setEpecode(strEecode);
				objFPCondition.setEecode("");
				FreightpriceColumns objFPColumns = searchFreightprice(objFPCondition);
				return objFPColumns;
			} catch (Exception ex) {
				
			} finally {
				objFPCondition.setEpecode("");
				objFPCondition.setEecode(strEecode);	
			}
		}
		return null;
	}
	
	private FreightpriceColumns searchCommonPrice(FreightpriceCondition objFPCondition) throws Exception {
		String strPricegroup = CacheContainerDemand.getCommonpricegroup();
		if (StringUtility.isNull(strPricegroup))
			return null;
		objFPCondition.setPgcode(String.valueOf(strPricegroup));
		FreightpriceColumns objFPColumns = searchFreightprice(objFPCondition);	
		if (objFPColumns != null && !StringUtility.isNull(objFPColumns.getFpepcode()))
			return objFPColumns;
		return null;
	}
	
}
