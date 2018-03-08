package kyle.leis.es.businessrule.productrule.dax;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.businessrule.productrule.da.BusinessproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.BusinessproductruleCondition;
import kyle.leis.es.businessrule.productrule.da.BusinessproductruleQuery;
import kyle.leis.es.businessrule.productrule.da.ChannelforproductCondition;
import kyle.leis.es.businessrule.productrule.da.ChannelforproductQuery;
import kyle.leis.es.businessrule.productrule.da.ChannelproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.ChannelproductruleQuery;
import kyle.leis.es.businessrule.productrule.da.CorporationproductruleQuery;
import kyle.leis.es.businessrule.productrule.da.DistrictproductruleColumns;
import kyle.leis.es.businessrule.productrule.da.DistrictproductruleQuery;
import kyle.leis.es.businessrule.productrule.da.ProductcswbColumns;
import kyle.leis.es.businessrule.productrule.da.ProductcswbCondition;
import kyle.leis.es.businessrule.productrule.da.ProductcswbQuery;
import kyle.leis.es.businessrule.productrule.da.ProductruleColumns;
import kyle.leis.es.businessrule.productrule.da.ProductruleCondition;
import kyle.leis.es.businessrule.productrule.da.ProductruleQuery;
import kyle.leis.es.price.zone.dax.ZoneDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBusinessrulekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiProductkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TbrBusinessrule;
import kyle.leis.hi.TbrProductrule;
import kyle.leis.hi.TdiBusinessrulekind;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiProductkind;
import kyle.leis.hi.TdiSimplestatus;

public class ProductruleDemand {
	private HashMap<String, String> m_hmZvalueDistrict = new HashMap<String, String>();

	public static List query(ProductruleCondition objProductruleCondition)
			throws Exception {
		ProductruleQuery objProductruleQuery = new ProductruleQuery();
		objProductruleQuery.setCondition(objProductruleCondition);
		return objProductruleQuery.getResults();
	}

	public static ProductruleColumns load(String strBrId) throws Exception {
		ProductruleCondition objProductruleCondition = new ProductruleCondition();
		objProductruleCondition.setBrid(strBrId);
		List objList = query(objProductruleCondition);

		if (objList == null || objList.size() < 1)
			return null;
		return (ProductruleColumns) objList.get(0);
	}

	public static List queryChannelProductRule(String strBrId) throws Exception {
		ChannelproductruleQuery objCPRQuery = new ChannelproductruleQuery();
		objCPRQuery.setBrid(strBrId);
		return objCPRQuery.getResults();
	}

	public static List queryDistrictProductRule(String strBrId)
			throws Exception {
		DistrictproductruleQuery objDPRQuery = new DistrictproductruleQuery();
		objDPRQuery.setBrid(strBrId);
		return objDPRQuery.getResults();
	}

	public static List queryCorporationProductRule(String strBrId)
			throws Exception {
		CorporationproductruleQuery objCPRQuery = new CorporationproductruleQuery();
		objCPRQuery.setBrid(strBrId);
		return objCPRQuery.getResults();
	}
	
	public static List queryChannelOfProduct(ChannelforproductCondition objCFPCondition)
	throws Exception {
		ChannelforproductQuery objCFPQ = new ChannelforproductQuery();
		objCFPQ.setCondition(objCFPCondition);
		return objCFPQ.getResults();
	}	
	
	
	public static LoadProductruleResult loadResults(String strBrId)
			throws Exception {
		LoadProductruleResult objLPRResult = new LoadProductruleResult();
		objLPRResult.setProductruleColumns(load(strBrId));
		objLPRResult.setDistrictPRColumns(queryDistrictProductRule(strBrId));
		objLPRResult.setChnPRColumns(queryChannelProductRule(strBrId));
		objLPRResult.setCorporationPRColumns(queryCorporationProductRule(strBrId));
		return objLPRResult;
	}

	public HashSet<String> getChannels(ProductruleCondition objPRCondition,
			String strOdtcode, String strDdtcode, String strPostcode)
			throws Exception {
		HashSet<String> hsChannels = new HashSet<String>();
		// 查找产品规则
		List objList = ProductruleDemand.query(objPRCondition);
		if (objList == null || objList.size() < 1)
			return null;
		// 判断起运地
		for (int i = 0; i < objList.size(); i++) {
			ProductruleColumns objPRColumns = (ProductruleColumns) objList.get(i);
			List listDistrict = ProductruleDemand.queryDistrictProductRule(objPRColumns.getPrbrid());
			// 存在起运地
			if (isExistsDistrict(listDistrict, strOdtcode)) {
				List listChannels = queryChannelProductRule(objPRColumns.getPrbrid());
				// 获得渠道
				HashSet<String> hsRuleChannels = getChannels(listChannels,strDdtcode, strPostcode);
				if (hsRuleChannels != null && hsRuleChannels.size() > 0)
					hsChannels.addAll(hsRuleChannels);
			}
		}
		return hsChannels;
	}

	private HashSet<String> getChannels(List listProductChannels,
			String strDtcode, String strPostcode) throws Exception {
		HashSet<String> hsChannels = new HashSet<String>();
		if (listProductChannels == null || listProductChannels.size() < 1)
			return null;

		for (int i = 0; i < listProductChannels.size(); i++) {
			ChannelproductruleColumns objCPRColumns = (ChannelproductruleColumns) listProductChannels.get(i);
			String strZncode = objCPRColumns.getCprznid();
			String strZnvid = objCPRColumns.getCprznvid();
			// 分区为空
			if (StringUtility.isNull(strZncode)) {
				hsChannels.add(objCPRColumns.getChnchncode());
				continue;
			}
			String strActualZnvid = "";
			// 是否包含在缓冲中
			if (m_hmZvalueDistrict.containsKey(strZncode))
				strActualZnvid = m_hmZvalueDistrict.get(strZncode);
			else {
				strActualZnvid = String.valueOf(ZoneDemand.getZnvidByDistrict(
						strDtcode, strPostcode, strZncode));
				m_hmZvalueDistrict.put(strZncode, strActualZnvid);
			}
			if (StringUtility.isNull(strActualZnvid))
				continue;
			// 实际的分区值不为空
			if (StringUtility.isNull(strZnvid))
				hsChannels.add(objCPRColumns.getChnchncode());
			else if (strZnvid.equals(strActualZnvid))
				hsChannels.add(objCPRColumns.getChnchncode());
		}
		return hsChannels;
	}

	private static boolean isExistsDistrict(List listDistrict, String strDtcode) {
		if (listDistrict == null || listDistrict.size() < 1)
			return false;
		for (int i = 0; i < listDistrict.size(); i++) {
			DistrictproductruleColumns objDPRColumns = (DistrictproductruleColumns) listDistrict.get(i);
			if (objDPRColumns.getDtdtcode().equals(strDtcode))
				return true;
		}
		return false;
	}

	private static List queryBPRule(String startDate, 
			String endDate,
			String pkCode) throws Exception {
		BusinessproductruleCondition objBPRuleCondition = new BusinessproductruleCondition();
		objBPRuleCondition.setStartdate(startDate);
		objBPRuleCondition.setEnddate(endDate);
		objBPRuleCondition.setPkcode(pkCode);
		objBPRuleCondition.setSscode("ON");
		objBPRuleCondition.setUseCacheSign(true);
		BusinessproductruleQuery objBPRuleQuery = new BusinessproductruleQuery();
		objBPRuleQuery.setCondition(objBPRuleCondition);
		List<BusinessproductruleColumns> objList = objBPRuleQuery.getResults();
		return objList;
	}

	private static List queryProductCSWB(String brId, String coCode)
			throws Exception {
		ProductcswbCondition objPCSWBCondition = new ProductcswbCondition();
		objPCSWBCondition.setBrid(brId);
		objPCSWBCondition.setCocode(coCode);
		objPCSWBCondition.setUseCacheSign(true);
		ProductcswbQuery objPCSWBQuery = new ProductcswbQuery();
		objPCSWBQuery.setCondition(objPCSWBCondition);
		List<ProductcswbColumns> objList = objPCSWBQuery.getResults();
		return objList;
	}

	public boolean isSignOutByOriginEWB(String startDate, 
			String endDate, 
			String pkCode,
			String coCode) throws Exception {
		List<BusinessproductruleColumns> listBPRule = queryBPRule(startDate,endDate, pkCode);

		if (listBPRule == null || listBPRule.size() < 1) {
			return false;
		} 
		else {
			BusinessproductruleColumns objBPRule = listBPRule.get(0);
			if (!StringUtility.isNull(objBPRule.getPrprallcussignoutbyoriginwbsign())&& 
					objBPRule.getPrprallcussignoutbyoriginwbsign().equals("Y")) {
				return true;
			} 
			if (!StringUtility.isNull(objBPRule.getPrprallcussignoutbyoriginwbsign())&& 
					objBPRule.getPrprallcussignoutbyoriginwbsign().equals("N")) {
				List<ProductcswbColumns> listPCSWB = queryProductCSWB(objBPRule.getPrbrid(), coCode);
				if (listPCSWB == null || listPCSWB.size() < 1) {
					return false;
				} 
				else if (listPCSWB != null && listPCSWB.size() > 0) {
					return true;
				}
			}
		}
		return false;
	}

	public static void setProductruleByColumns(TbrProductrule objTbrProductrule,
			ProductruleColumns objProductruleColumns, 
			Session objSession) throws Exception {
		objTbrProductrule.setPrCollectsign(objProductruleColumns.getPrprcollectsign());
		objTbrProductrule.setPrSimpleinputsign(objProductruleColumns.getPrprsimpleinputsign());
		objTbrProductrule.setPrAllcussignoutbyoriginwbsign(objProductruleColumns.getPrprallcussignoutbyoriginwbsign());
		
		if (!StringUtility.isNull(objProductruleColumns.getPrprdoxtransfergw()))
			objTbrProductrule.setPrDoxtransfergw(new BigDecimal(objProductruleColumns.getPrprdoxtransfergw()));

		if (!StringUtility.isNull(objProductruleColumns.getPrprinsurancesign()))
			objTbrProductrule.setPrInsurancesign(objProductruleColumns.getPrprinsurancesign());

		if (!StringUtility.isNull(objProductruleColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objProductruleColumns.getCkckcode());
			objTbrProductrule.setTdiCurrencykind(objTdiCurrencykind);
		}
		if (!StringUtility.isNull(objProductruleColumns.getPkpkcode())) {
			TdiProductkind objTdiProductkind = TdiProductkindDC.loadByKey(objProductruleColumns.getPkpkcode());
			objTbrProductrule.setTdiProductkind(objTdiProductkind);
		}
	}

	public static void setBusinessruleByColumns(
			TbrBusinessrule objTbrBusinessrule,
			ProductruleColumns objProductruleColumns, String strOperId,
			Session objSession) throws Exception {
		objTbrBusinessrule.setBrEname(objProductruleColumns.getBrbrename());
		objTbrBusinessrule.setBrStartdate(DateFormatUtility.getStandardDate(objProductruleColumns.getBrbrstartdate()));
		objTbrBusinessrule.setBrEnddate(DateFormatUtility.getStandardDate(objProductruleColumns.getBrbrenddate()));
		objTbrBusinessrule.setBrName(objProductruleColumns.getBrbrname());
		objTbrBusinessrule.setBrRemark(objProductruleColumns.getBrbrremark());

		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTbrBusinessrule.setTdiOperatorByBrOpIdModifier(objTdiOperator);
			objTbrBusinessrule.setBrModifydate(DateFormatUtility.getSysdate());
		}
		if (!StringUtility.isNull(objProductruleColumns.getBrkbrkcode())) {
			TdiBusinessrulekind objTdiBusinessrulekind = TdiBusinessrulekindDC.loadByKey(objProductruleColumns.getBrkbrkcode());
			objTbrBusinessrule.setTdiBusinessrulekind(objTdiBusinessrulekind);
		}
		if (!StringUtility.isNull(objProductruleColumns.getSssscode())) {
			TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objProductruleColumns.getSssscode());
			objTbrBusinessrule.setTdiSimplestatus(objTdiSimplestatus);
		}
	}
}
