package kyle.leis.eo.billing.calculate.incidental.bl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.FeeCalculateResult;
import kyle.leis.eo.billing.calculate.freight.bl.FreightSearch;
import kyle.leis.eo.billing.calculate.pricegroup.bl.PricegroupSearch;
import kyle.leis.eo.operation.specialtype.dax.SpecialtypeDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalpriceCondition;
import kyle.leis.es.price.incidentalprice.da.IncidentalpricevalueColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalstoragechannelColumns;
import kyle.leis.es.price.incidentalprice.da.IncidentalvaluebaseColumns;
import kyle.leis.es.price.incidentalprice.dax.IncidentalPriceDemand;
import kyle.leis.fs.cachecontainer.dax.CacheContainerDemand;

public class IncidentalSearch {
	static Logger s_objLogger = Logger.getLogger(FreightSearch.class.getName());
	private HashMap<String,FeeCalculateResult> m_hmCalculatResult;
	
	public void addIncidentalvalue(IncidentalpriceCondition objIPCondition,
			String strCtcode,
			String strPmcode,
			HashMap<String,FeeCalculateResult> hmCalculatResult,
			List listSpecialtypes,
			boolean isFilterDiffChannel) 
	throws Exception {
		IncidentalpriceCondition objOldIPCondition = new IncidentalpriceCondition();
		objOldIPCondition.setFields(objIPCondition.getFields());
		objOldIPCondition.setUseCacheSign(true);
		try {
			if (hmCalculatResult == null)
				hmCalculatResult = new HashMap<String,FeeCalculateResult>();
			m_hmCalculatResult = hmCalculatResult;
			// ���ҹ�˾�۸��
			objIPCondition.setUseCacheSign(true);
			IncidentalpriceColumns objIPColumns = searchCorPrice(objIPCondition);
			if (objIPColumns == null && 
					objIPCondition.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_COSTS))
				return;
			// �����ӷѼ۸�ֵ
			if (objIPColumns != null) {
				addIncidentalvalue(objIPColumns, strCtcode, 
						strPmcode, isFilterDiffChannel, 
						listSpecialtypes);
				return;
			}
			// ���ۼۼ������ҵȼ�
			// ���Ҽ۸���
			objIPCondition.setCocode("");
			objIPCondition.setChncode("");
			PricegroupSearch objPricegroupSearch = new PricegroupSearch();
			String strPricegroup = objPricegroupSearch.searchPricegroup(objOldIPCondition);
			// boolean isSearchCommonprice = false;
			// δ���ҵ��κμ۸��������ͨ�ü۸�
			if (StringUtility.isNull(strPricegroup)) {
				objIPColumns = searchCommonPrice(objIPCondition);
				if (objIPColumns != null && 
						!StringUtility.isNull(objIPColumns.getEpepcode())) {
					strPricegroup = objIPColumns.getPgpgcode();
					addIncidentalvalue(objIPColumns, strCtcode, strPmcode, isFilterDiffChannel, listSpecialtypes);
				}
				return;
			}
			// �ͻ��۸���ֵ
			int iPricegroup = Integer.parseInt(strPricegroup);
			// ѭ���ݼ�ȡ�۸��
			while (iPricegroup >= 0) {
				objIPCondition.setPgcode(String.valueOf(iPricegroup));
				objIPColumns = searchIncidentalPrice(objIPCondition);	
				if (objIPColumns != null && 
						!StringUtility.isNull(objIPColumns.getEpepcode()))
					addIncidentalvalue(objIPColumns, strCtcode, strPmcode, isFilterDiffChannel, listSpecialtypes);
				iPricegroup--;
				// ���ٵݼ�ȡ�۸��
				// 2015.01.16
				break;
			}
			// ����ͨ�ü۸�
			/*
			if (!isSearchCommonprice) {
				objIPColumns = searchCommonPrice(objIPCondition);
				if (objIPColumns != null && 
						!StringUtility.isNull(objIPColumns.getEpepcode()))
					addIncidentalvalue(objIPColumns, strCtcode, strPmcode);
			} */
		} finally {
			// ��ԭΪԭ����ֵ
			objIPCondition.setFields(objOldIPCondition.getFields());
		}
	}
	
	/**
	 * �����ӷѼ۸�ֵ
	 * @param strEpcode
	 * @param objCalcFeeParameter
	 * @throws Exception
	 */
	private void addIncidentalvalue(IncidentalpriceColumns objIPColumns, 
			String strCtcode,
			String strPmcode,
			boolean isFilterDiffChannel,
			List listSpecialtypes) throws Exception {
		List objList = IncidentalPriceDemand.loadIncidentalvalue(objIPColumns.getEpepcode());
		List listBaseFkcode = IncidentalPriceDemand.loadIncidentalvaluebase(objIPColumns.getEpepcode());
		List listStorageChncode = IncidentalPriceDemand.loadIncidentalstoragechannel(objIPColumns.getEpepcode());
		
		if (objList == null || objList.size() < 1) return;
		for (int i = 0; i < objList.size(); i++) {
			IncidentalpricevalueColumns objIPVColumns = (IncidentalpricevalueColumns)objList.get(i);
			// �Զ��Ʒ�
			if (!objIPVColumns.getIpvipvautocalculatesign().equals("Y"))
				continue;
			// ��������
			if (!objIPVColumns.getCtctcode().equals(strCtcode) && 
					!objIPVColumns.getCtctcode().equals("A"))
				continue;
			// ���ѷ�ʽ
			if (!objIPVColumns.getPmpmcode().equals(strPmcode) && 
					!objIPVColumns.getPmpmcode().equals("A"))
				continue;
			// ������ѡ��ʱ��Ҫ����������ͬ�ķ���
			String strStorageChncode = getStorageChncode(listStorageChncode,
					objIPVColumns.getIpvcomp_idipvid());
			if (isFilterDiffChannel &&
					!StringUtility.isNull(strStorageChncode) &&
					!objIPColumns.getChnchncode().equals(strStorageChncode))
				continue;
			
			String strEstcode = objIPVColumns.getEstestcode();
			// �۸�����������������ͣ�ҵ��δ���������������򲻼Ʒ�
			if (!StringUtility.isNull(strEstcode) && 
					!SpecialtypeDemand.isExistsSpecialtype(listSpecialtypes,
							strEstcode))
				continue;
			// ��������
			// ����������
			if (!m_hmCalculatResult.containsKey(objIPVColumns.getFkfkcode())) {
				FeeCalculateResult objClacFeeResult = new FeeCalculateResult();
				
				objClacFeeResult.setBasevalue("0");
				objClacFeeResult.setCkcode(objIPVColumns.getCkckcode());
				objClacFeeResult.setFkcode(objIPVColumns.getFkfkcode());
				objClacFeeResult.setMinimumvalue(objIPVColumns.getIpvipvminimumvalue());
				objClacFeeResult.setMaxvalue(objIPVColumns.getIpvipvmaxvalue());
				objClacFeeResult.setPricevalue(objIPVColumns.getIpvipvpricevalue());
				objClacFeeResult.setRemark(objIPVColumns.getIpvipvremark());
				objClacFeeResult.setReversesign(objIPVColumns.getIpvipvreversesign());
				objClacFeeResult.setUtcode(objIPVColumns.getUtutcode());
				objClacFeeResult.setBaseFkcode(getBaseFkcode(listBaseFkcode, 
						objIPVColumns.getIpvcomp_idipvid()));
				objClacFeeResult.setStorechannelcode(getStorageChncode(listStorageChncode,
						objIPVColumns.getIpvcomp_idipvid()));		
				objClacFeeResult.setCommissionirate(objIPVColumns.getIpvipvcommissionrate());
				objClacFeeResult.setPdcode(objIPColumns.getPdpdcode());
				objClacFeeResult.setEpcode(objIPColumns.getEpepcode());
				objClacFeeResult.setEpvid(objIPVColumns.getIpvcomp_idipvid());
				objClacFeeResult.setEstcode(objIPVColumns.getEstestcode());
				objClacFeeResult.setCarryweigh(objIPVColumns.getIpvipvcarryweight());
				// ����
				objClacFeeResult.setCurrencyrate("1");
				// ��ֵ
				m_hmCalculatResult.put(objIPVColumns.getFkfkcode(), objClacFeeResult);
			}
		}
	}
	
	private ArrayList<String> getBaseFkcode(List listBaseFkcode, String strIpvid) {
		if (listBaseFkcode == null || listBaseFkcode.size() < 1)
			return null;
		ArrayList<String> alBaseFkcode = new ArrayList<String>();
		for (int i = 0; i < listBaseFkcode.size(); i++) {
			IncidentalvaluebaseColumns objIvbaseColumns = (IncidentalvaluebaseColumns)listBaseFkcode.get(i);
			if (objIvbaseColumns.getIvbcomp_idipvid().equals(strIpvid))
				alBaseFkcode.add(objIvbaseColumns.getIvbcomp_idfkcode());	
		}
		return alBaseFkcode;
	}	
	
	private String getStorageChncode(List listStoreChancode, String strIpvid) {
		if (listStoreChancode == null || listStoreChancode.size() < 1)
			return null;
		for (int i = 0; i < listStoreChancode.size(); i++) {
			IncidentalstoragechannelColumns objISCColumns = (IncidentalstoragechannelColumns)listStoreChancode.get(i);
			if (objISCColumns.getIsccomp_idipvid().equals(strIpvid))
				return objISCColumns.getChnchncode();
		}
		return "";
	}
	
	private IncidentalpriceColumns searchCorPrice(IncidentalpriceCondition objIPCondition) 
	throws Exception {
		objIPCondition.setPgcode("");
		return searchIncidentalPrice(objIPCondition);
	}
	
	
	/**
	 * �����ӷѼ۸��
	 * @param objIPCondition
	 * @return
	 * @throws Exception
	 */
	private IncidentalpriceColumns searchIncidentalPrice(IncidentalpriceCondition objIPCondition) 
	throws Exception {
		List objList = IncidentalPriceDemand.query(objIPCondition);
		if (objList != null && objList.size() > 1) {
			s_objLogger.warning("�����ӷ�ʱ�����������ҵ��������ϵ��ӷѼ۸��");
			throw (new Exception ("�����ӷ�ʱ�����������ҵ��������ϵ��ӷѼ۸��"));
		}
		if (objList == null || objList.size() < 1) {
			if (StringUtility.isNull(objIPCondition.getEpecode()) &&
					!StringUtility.isNull(objIPCondition.getEecode())) {
				String strEecode = objIPCondition.getEecode();
				try {
					objIPCondition.setEpecode(strEecode);
					objIPCondition.setEecode("");
					IncidentalpriceColumns objIPColumns = searchIncidentalPrice(objIPCondition);
					return objIPColumns;
				} catch (Exception ex) {
					
				} finally {
					objIPCondition.setEpecode("");
					objIPCondition.setEecode(strEecode);	
				}		
			} else {
				return null;
			}
		}
		return (IncidentalpriceColumns)objList.get(0);
	}
	
	private IncidentalpriceColumns searchCommonPrice(IncidentalpriceCondition objIPCondition) throws Exception {
		String strPricegroup = CacheContainerDemand.getCommonpricegroup();
		if (StringUtility.isNull(strPricegroup))
			return null;
		objIPCondition.setPgcode(String.valueOf(strPricegroup));
		IncidentalpriceColumns objIPColumns = searchIncidentalPrice(objIPCondition);	
		if (objIPColumns != null && !StringUtility.isNull(objIPColumns.getEpepcode()))
			return objIPColumns;
		return null;
	}	
	
	
	public HashMap<String,FeeCalculateResult> getIncidentalvalueResult() {
		return m_hmCalculatResult;
	}	
	
}
