package kyle.leis.eo.billing.calculate.chargeweight.bl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightParameter;
import kyle.leis.eo.billing.calculate.chargeweight.dax.ChargeweightResult;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.es.businessrule.weightrule.da.CalcweightvalueColumns;
import kyle.leis.es.businessrule.weightrule.da.CalcweightvalueQuery;
import kyle.leis.es.businessrule.weightrule.da.CarryweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.VolumeweightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightruleColumns;
import kyle.leis.es.businessrule.weightrule.da.WeightrulevalueColumns;
import kyle.leis.es.businessrule.weightrule.dax.WeightRuleDemand;
import kyle.leis.es.price.expressprice.dax.IExpressPriceBasicData;
import kyle.leis.es.price.zone.dax.ZoneDemand;

public class Chargeweight {
	private HashMap<String, String> s_hmZvalueDistrict = new HashMap<String, String>();
	private Map<Integer, String> m_hmLengthExceed = new HashMap<Integer, String>();
	private Map<Integer, String> m_hmWeightExceed = new HashMap<Integer, String>();
	private String m_strExpression;
	private static final String PRODUCTKIND_UPS_PREFIX = "U01";
	
	public ChargeweightResult calculate(String strCwcode) throws Exception {
		HousewaybillColumns objHousewaybillColumns = HousewaybillDemand.loadByCwcode(strCwcode);
		List listCWBPieces = CorewaybillpiecesDemand.load(strCwcode);
		ChargeweightParameter objCWParameter = CorewaybillDemand.transferToCWParameter(objHousewaybillColumns, 
				listCWBPieces);
		return calculate(objCWParameter);
	}
	
	public ChargeweightResult calculate(ChargeweightParameter objCWeightParameter) 
	throws Exception {
		ChargeweightResult objChargeweightResult = new ChargeweightResult();
		// 查找重量规则种类
		ChargeweightSearch objCwsearch = new ChargeweightSearch();
		String strWrkid = objCwsearch.searchWeightrulekind(objCWeightParameter.getPkcode(), 
				objCWeightParameter.getCocode(), 
				objCWeightParameter.getPdcode(),
				objCWeightParameter.getSearchDate());
		// 未找到规则种类
		WeightruleColumns objWeightruleColumns = null;
		if (!StringUtility.isNull(strWrkid)) {
			// 查找计费重量
			objWeightruleColumns = objCwsearch.searchWeightrule(strWrkid, objCWeightParameter.getSearchDate());
		}
		
		// 检查实重跟材积
		if (objCWeightParameter.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_SALES)) {
			checkWeightLengthExcced(objCWeightParameter, objWeightruleColumns);
		}
		// 缺省重量
		String strVolumeRate = "5000";
		// 获得销售体积重
		String strVolumeweight = "0";		
		if (objWeightruleColumns == null) {
			if (!StringUtility.isNull(objCWeightParameter.getPkcode()) && 
					objCWeightParameter.getPkcode().startsWith(PRODUCTKIND_UPS_PREFIX)) {
				// strVolumeRate = "6000";
				return calcUPSChargeWeight(objCWeightParameter.getWaybillpiecesCollection(),
						strVolumeRate,
						objCWeightParameter.getGrossWeight());
			} else {
				strVolumeweight = getVolumeWeight(objCWeightParameter.getWaybillpiecesCollection(),
						strVolumeRate,
						"");		
				return getDefaultChargeWeight(objCWeightParameter.getGrossWeight(),
						strVolumeweight);
			}
		}
		// 查找体积重系数
		strVolumeRate = getVolumeWeightRate(objWeightruleColumns.getWrbrid(), 
				objCWeightParameter);
		
		String strTwrk_id = objWeightruleColumns.getTswkswkcode();
		String strOriginGrossweight = objCWeightParameter.getGrossWeight();
		String strOrignVolumeweight = "0";
		if (objCWeightParameter.getPdcode().equals(IExpressPriceBasicData.PRICEDOMAIN_SALES)) {
			if (objCWeightParameter.getPkcode().startsWith(PRODUCTKIND_UPS_PREFIX)) {
				return calcUPSChargeWeight(objCWeightParameter.getWaybillpiecesCollection(),
						strVolumeRate,
						objCWeightParameter.getGrossWeight());
			} else {
				strVolumeweight = getVolumeWeight(objCWeightParameter.getWaybillpiecesCollection(),
						strVolumeRate,
						objWeightruleColumns.getWrwrpeactualweight());
			}
		} else {
			strVolumeweight = getChannelVolumeWeight(objCWeightParameter.getVolumeweight(),
					strVolumeRate,
					objCWeightParameter.getOriginVolumerate());
			// 渠道按收货重量为准
			if (!StringUtility.isNull(strTwrk_id) && 
					strTwrk_id.equals("A01") && 
					!StringUtility.isNull(objCWeightParameter.getHawbGrossweight())) {
				// 根据收货尺寸计算材积
				String strSIVolumeweight = CorewaybillpiecesDemand.calcVolumeweight(objCWeightParameter.getWaybillpiecesCollection(),
						strVolumeRate);		
				// 计算原始的出货重量
				String[] astr = getWeightValue(objCWeightParameter,
						objWeightruleColumns,
						strVolumeweight);
				if (astr == null) {
					// 为空时使用默认的
					ChargeweightResult cwr = getDefaultChargeWeight(objCWeightParameter.getHawbGrossweight(),
							strSIVolumeweight);
					ChargeweightResult cwrResult = getDefaultChargeWeight(objCWeightParameter.getGrossWeight(),
							strVolumeweight);					
					cwr.setResultChargeweight(cwrResult.getChargeweight());
					return cwr;
				}
				objChargeweightResult.setResultChargeweight(astr[0]);
				strOrignVolumeweight = strVolumeweight;
				// 获得收货时计费用的重量
				objCWeightParameter.setGrossWeight(objCWeightParameter.getHawbGrossweight());
				strVolumeweight = strSIVolumeweight;				
			}
		}
		// 获得重量
		String[] astrWeightValue = getWeightValue(objCWeightParameter,
				objWeightruleColumns, strVolumeweight);
		// 缺省重量
		if (astrWeightValue == null) {
			return getDefaultChargeWeight(objCWeightParameter.getGrossWeight(),
					strVolumeweight);
		}
		String strWeightValue = astrWeightValue[0];
		// 获得进位重量
		String strCarryWeight = astrWeightValue[1];
		
		objChargeweightResult.setServerweightrulekind(objWeightruleColumns.getSwkswkcode());
		objChargeweightResult.setTransferWRK(objWeightruleColumns.getTswkswkcode());
		objChargeweightResult.setChargeweight(strWeightValue);
		objChargeweightResult.setCarryweight(strCarryWeight);
		objChargeweightResult.setExpression(m_strExpression);
		objChargeweightResult.setVolumeRate(strVolumeRate);
		objChargeweightResult.setVolumeweight(strVolumeweight);
		objChargeweightResult.setGrossweight(objCWeightParameter.getGrossWeight());
		// 按收货重量计算，实重和材积替换为原值
		if (!StringUtility.isNull(objChargeweightResult.getResultChargeweight())) {
			objChargeweightResult.setGrossweight(strOriginGrossweight);
			objChargeweightResult.setVolumeweight(strOrignVolumeweight);
		}
		return objChargeweightResult;
	}
	
	private String[] getWeightValue(ChargeweightParameter objCWeightParameter,
			WeightruleColumns objWeightruleColumns,
			String strVolumeweight) throws Exception {
		// 获得重量
		String strWeightValue = calcWeightValue(objWeightruleColumns.getWrbrid(),
				strVolumeweight,
				objCWeightParameter);
		// 缺省重量
		if (StringUtility.isNull(strWeightValue))
			return null;
		// 获得进位重量
		String strCarryWeight = getCarryWeight(objWeightruleColumns.getWrbrid(),
				strWeightValue);
		if (!StringUtility.isNull(strCarryWeight))
			strWeightValue = getCarriedWeight(strWeightValue, 
					strCarryWeight, objCWeightParameter.getPdcode());
		return new String[] {strWeightValue, strCarryWeight};
	}
	
	
	private void checkWeightLengthExcced(ChargeweightParameter objCWeightParameter,
			WeightruleColumns objWeightruleColumns) {
		List listWaybillpieces = objCWeightParameter.getWaybillpiecesCollection();
		if (listWaybillpieces == null || listWaybillpieces.size() < 1)
			return;
		if (objWeightruleColumns == null) return;
		if (StringUtility.isNull(objWeightruleColumns.getWrwrpeweightformula()) && 
				StringUtility.isNull(objWeightruleColumns.getWrwrpelenghtformula()))
			return;
			
		String strPgwrestrictdesc = "";
		String strPvwrestrictdesc = "";
		if (!StringUtility.isNull(objWeightruleColumns.getWrwrpeweightformula()))
			strPgwrestrictdesc = objWeightruleColumns.getWrwrpeweightformula().toUpperCase();
		if (!StringUtility.isNull(objWeightruleColumns.getWrwrpelenghtformula()))
			strPvwrestrictdesc = objWeightruleColumns.getWrwrpelenghtformula().toUpperCase();
		
		BigDecimal objGrossweight = new BigDecimal("0");
		try {
			for (int i = 0; i < listWaybillpieces.size(); i++) {
				CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
				// 超重判断
				if (!StringUtility.isNull(strPgwrestrictdesc)) {
					strPgwrestrictdesc = strPgwrestrictdesc.replaceAll("GW", objCWPColumns.getCpcpgrossweight());
					strPgwrestrictdesc = strPgwrestrictdesc.replaceAll(";", " and ");
					
					String strSqlText = "select count(1) from dual where " + strPgwrestrictdesc; 
					CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
					List objList = objCalcWVQuery.getResults(strSqlText);
					// 超重
					if (objList != null && objList.size() > 0) {
						CalcweightvalueColumns objCalcweightvalueColumns = (CalcweightvalueColumns)objList.get(0);
						String strWeightvalue = objCalcweightvalueColumns.getWeightvalue();
						if (!strWeightvalue.equals("0"))
							m_hmWeightExceed.put(i, "EW");
					}
				}	
				// 超长判断
				if (!StringUtility.isNull(strPvwrestrictdesc)) {
					strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("L", objCWPColumns.getCpcplength());
					strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("W", objCWPColumns.getCpcpwidth());
					strPvwrestrictdesc = strPvwrestrictdesc.replaceAll("H", objCWPColumns.getCpcpheight());
					strPvwrestrictdesc = strPvwrestrictdesc.replaceAll(";", " and ");
					
					String strSqlText = "select count(1) from dual where " + strPgwrestrictdesc; 
					CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
					List objList = objCalcWVQuery.getResults(strSqlText);
					// 超重
					if (objList != null && objList.size() > 0) {
						CalcweightvalueColumns objCalcweightvalueColumns = (CalcweightvalueColumns)objList.get(0);
						String strWeightvalue = objCalcweightvalueColumns.getWeightvalue();
						if (!strWeightvalue.equals("0"))
							m_hmLengthExceed.put(i, "EL");
					}					
				}
			}
			// 如果超长或者超重则重新计算实重
			if (m_hmWeightExceed != null && m_hmWeightExceed.size() > 0) {
				for (int i = 0; i < listWaybillpieces.size(); i++) {
					CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
					if (m_hmWeightExceed.containsKey(i)) {
						objGrossweight = objGrossweight.add(new BigDecimal(objCWPColumns.getCpcpgrossweight()));
					} else {
						// 不超重按设置的重量来计算
						objGrossweight = objGrossweight.add(new BigDecimal(objWeightruleColumns.getWrwrpeactualweight()));
					}
				}
			}
		} catch (Exception ex) {
			
		}
		if (objGrossweight.compareTo(new BigDecimal("0")) > 0) {
			objCWeightParameter.setGrossWeight(objGrossweight.toString());
		}
	}
	
	
	
	/**
	 * 获得体积重
	 * @param listWaybillpieces
	 * @param strVWeightRate
	 * @return
	 */
	public String getVolumeWeight(List listWaybillpieces, String strVWeightRate,
			String strWrpeactualweight) {
		if (listWaybillpieces == null || listWaybillpieces.size() < 1)
			return "0";
		BigDecimal objVolumeWeight = new BigDecimal("0");
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWBPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			
			BigDecimal objLength = new BigDecimal(objCWBPiecesColumns.getCpcplength());
			BigDecimal objHeight = new BigDecimal(objCWBPiecesColumns.getCpcpheight());
			BigDecimal objWidth = new BigDecimal(objCWBPiecesColumns.getCpcpwidth());
			BigDecimal objVWPiece = objLength.multiply(objHeight).multiply(objWidth).divide(new BigDecimal(strVWeightRate), 2, 4);

			if (m_hmLengthExceed != null && m_hmLengthExceed.size() > 0) {
				if (!m_hmLengthExceed.containsKey(i)){
					if (!StringUtility.isNull(strWrpeactualweight))
						objVolumeWeight = objVolumeWeight.add(new BigDecimal(strWrpeactualweight));
					else
						objVolumeWeight = objVolumeWeight.add(objVWPiece);
				}
			} else {
				objVolumeWeight = objVolumeWeight.add(objVWPiece);						
			}
		}
		return objVolumeWeight.toString();
	}
	
	private ChargeweightResult calcUPSChargeWeight(List listWaybillpieces, 
			String strVWeightRate,
			String strGrossweight) {
		if (StringUtility.isNull(strVWeightRate))
			strVWeightRate = "6000";
		if (listWaybillpieces == null || listWaybillpieces.size() < 1) {			
			return getDefaultChargeWeight(strGrossweight, "0");
		}
		BigDecimal objVolumeWeight = new BigDecimal("0");
		BigDecimal objChargeWeight = new BigDecimal("0");
		
		for (int i = 0; i < listWaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWBPiecesColumns = (CorewaybillpiecesColumns)listWaybillpieces.get(i);
			
			BigDecimal objLength = new BigDecimal(objCWBPiecesColumns.getCpcplength());
			BigDecimal objHeight = new BigDecimal(objCWBPiecesColumns.getCpcpheight());
			BigDecimal objWidth = new BigDecimal(objCWBPiecesColumns.getCpcpwidth());
			BigDecimal objVWPiece = objLength.multiply(objHeight).multiply(objWidth).divide(new BigDecimal(strVWeightRate), 2, 4);
			
			BigDecimal objGWPiece = new BigDecimal(strGrossweight);
			if (!StringUtility.isNull(objCWBPiecesColumns.getCpcpgrossweight()))
				objGWPiece = new BigDecimal(objCWBPiecesColumns.getCpcpgrossweight());
			BigDecimal objCWPiece = objGWPiece;
			if (objVWPiece.compareTo(objGWPiece) > 0)
				objCWPiece = objVWPiece;
			objCWPiece = objCWPiece.divide(new BigDecimal("0.5"), 0, 2).multiply(new BigDecimal("0.5"));
			
			objVolumeWeight = objVolumeWeight.add(objVWPiece);
			objChargeWeight = objChargeWeight.add(objCWPiece);
		}
		objChargeWeight = objChargeWeight.divide(new BigDecimal("0.5"), 0, 2).multiply(new BigDecimal("0.5"));
		ChargeweightResult objChargeweightResult = new ChargeweightResult();
		objChargeweightResult.setCarryweight("0.5");
		objChargeweightResult.setVolumeRate(strVWeightRate);
		objChargeweightResult.setVolumeweight(objVolumeWeight.toString());
		objChargeweightResult.setChargeweight(objChargeWeight.toString());
		
		return objChargeweightResult;		
	}
	
	
	
	
	
	/**
	 * 由于出货体积重不再通过裁定体积来获得，只能通过原渠道的系数和新渠道的系数对比来获得
	 * @param strVolumeweight
	 * @param strVolumerate
	 * @param strOriginVolumerate
	 * @return
	 */
	public String getChannelVolumeWeight(String strVolumeweight, 
			String strVolumerate, 
			String strOriginVolumerate) {
		if (StringUtility.isNull(strOriginVolumerate))
			strOriginVolumerate = strVolumerate;
		if (StringUtility.isNull(strVolumeweight))
			return "0";
		BigDecimal objRelative = new BigDecimal(strOriginVolumerate).divide(new BigDecimal(strVolumerate), 2, 4);
		BigDecimal objVolumeweight = new BigDecimal(strVolumeweight).multiply(objRelative).divide(new BigDecimal("1"), 2, 4);
		return objVolumeweight.toString();
	}
	
	
	public String getCarriedWeight(String strSourceWeight, 
			String strCarryWeight, 
			String strPdcode) {
		if (strPdcode.equals(IExpressPriceBasicData.PRICEDOMAIN_SALES)) {
			BigDecimal objSourceWeight = new BigDecimal(strSourceWeight);
			BigDecimal objCarriedWeight = objSourceWeight.divide(new BigDecimal(strCarryWeight), 0, 2).multiply(new BigDecimal(strCarryWeight));
			return objCarriedWeight.toString();
		} else {
			BigDecimal objSourceWeight = new BigDecimal(strSourceWeight);
			// BigDecimal objCarriedWeight = objSourceWeight.divide(new BigDecimal(strCarryWeight), 0, 1).multiply(new BigDecimal(strCarryWeight));
			BigDecimal objCarriedWeight = objSourceWeight.divide(new BigDecimal(strCarryWeight), 0, 2).multiply(new BigDecimal(strCarryWeight));
			if (objCarriedWeight.compareTo(new BigDecimal("0")) == 0)
				return "0.5";
			return objCarriedWeight.toString();			
		}
	}
	
	private String calcWeightValue(String strBrid,
			String strVolumeWeight,
			ChargeweightParameter objCWeightParameter) throws Exception {
		List objList = WeightRuleDemand.loadWeightrulevalue(strBrid);
		if (objList == null || objList.size() < 1)
			return null;
		
		ArrayList<Integer> alNullZncodeIndex = new ArrayList<Integer>();
		ArrayList<Integer> alNullZnvidIndex = new ArrayList<Integer>();
		for (int i = 0; i < objList.size(); i++) {
			WeightrulevalueColumns objWRVColumns = (WeightrulevalueColumns)objList.get(i);
			String strZncode = objWRVColumns.getWrvznid();
			String strZnvid = objWRVColumns.getWrvznvid();
			if (StringUtility.isNull(strZncode)) {
				alNullZncodeIndex.add(i);
				continue;
			}
			String strActualZnvid = "";
			// 是否包含在缓冲中
			if (s_hmZvalueDistrict.containsKey(strZncode))
				strActualZnvid = s_hmZvalueDistrict.get(strZncode);
			else {
				strActualZnvid = String.valueOf(ZoneDemand.getZnvidByDistrict(objCWeightParameter.getDtcode(), 
						objCWeightParameter.getPostcode(), strZncode));
				s_hmZvalueDistrict.put(strZncode, strActualZnvid);
			}
			if (StringUtility.isNull(strActualZnvid)) continue;
			// 
			if (StringUtility.isNull(strZnvid)) {
				alNullZnvidIndex.add(i);
			} else if (strZnvid.equals(strActualZnvid)) {
				String strChargeWeight = getChargeWeight(objWRVColumns.getWrvznvcondition(),
						objWRVColumns.getWrvznvexpression(),
						objCWeightParameter.getGrossWeight(),
						strVolumeWeight);
				if (!StringUtility.isNull(strChargeWeight))
					return strChargeWeight;
			}
		}
		// 查找分区或分区值为空的记录
		ArrayList<Integer> alNullIndex = alNullZnvidIndex;
		if (alNullIndex == null || alNullIndex.size() < 1)
			alNullIndex = alNullZncodeIndex;
		if (alNullIndex == null || alNullIndex.size() < 1) return "";
		
		for (int i = 0; i < alNullIndex.size(); i++) {
			int iNullIndex = alNullIndex.get(i);
			WeightrulevalueColumns objWRVColumns = (WeightrulevalueColumns)objList.get(iNullIndex);
			String strChargeWeight =  getChargeWeight(objWRVColumns.getWrvznvcondition(),
					objWRVColumns.getWrvznvexpression(),
					objCWeightParameter.getGrossWeight(),
					strVolumeWeight);
			if (!StringUtility.isNull(strChargeWeight))
				return strChargeWeight;			
		}
		return "";
	}
	
	private String getChargeWeight(String strCondition, 
			String strExpression, 
			String strGrossWeight, 
			String strVolumeWeight) throws Exception {
		m_strExpression = strExpression;
		strExpression = strExpression.toLowerCase().replace("gw", strGrossWeight);
		strExpression = strExpression.toLowerCase().replace("vw", strVolumeWeight);
		strCondition = strCondition.toLowerCase().replace("gw", strGrossWeight);
		strCondition = strCondition.toLowerCase().replace("vw", strVolumeWeight);
		
		String strSqlText = "select " + strExpression + " from dual where " + strCondition; 
		if (StringUtility.isNull(strCondition))
			strSqlText = "select " + strExpression + " from dual "; 
		CalcweightvalueQuery objCalcWVQuery = new CalcweightvalueQuery();
		List objList = objCalcWVQuery.getResults(strSqlText);
		if (objList == null || objList.size() < 1) return "";
		CalcweightvalueColumns objCalcWVColumns = (CalcweightvalueColumns)objList.get(0);
		if (objCalcWVColumns == null || StringUtility.isNull(objCalcWVColumns.getWeightvalue()))
			return "";
		return objCalcWVColumns.getWeightvalue();
	}
	
	
	public ChargeweightResult getDefaultChargeWeight(String strGrossWeight, 
			String strVolumeWeight) {
		
		if (StringUtility.isNull(strGrossWeight))
			strGrossWeight = "0";
		if (StringUtility.isNull(strVolumeWeight))
			strVolumeWeight = "0";
		BigDecimal objGrossWeight = new BigDecimal(strGrossWeight);
		BigDecimal objVolumeWeight = new BigDecimal(strVolumeWeight);
		BigDecimal objChargeWeight = objGrossWeight;
		if (objVolumeWeight.compareTo(objGrossWeight) > 0)
			objChargeWeight = objVolumeWeight;
		
		objChargeWeight = objChargeWeight.divide(new BigDecimal("0.5"), 0, 2).multiply(new BigDecimal("0.5"));
		
		ChargeweightResult objChargeweightResult = new ChargeweightResult();
		objChargeweightResult.setCarryweight("0.5");
		objChargeweightResult.setVolumeRate("5000");
		objChargeweightResult.setVolumeweight(strVolumeWeight);
		objChargeweightResult.setChargeweight(objChargeWeight.toString());
		
		return objChargeweightResult;		
	}
	
	private String getVolumeWeightRate(String strBrid,
			ChargeweightParameter objCWeightParameter) throws Exception {
		String strVolumeWeightRate = "6000";
		String strNullZncodeVWRate = "";
		String strNullZnvidVWRate = "";
		
		List objList = WeightRuleDemand.loadVolumeweightrulevalue(strBrid);
		if (objList != null && objList.size() > 0) {
			for (int i = 0; i < objList.size(); i++) {
				VolumeweightrulevalueColumns objVWRValueColumns = (VolumeweightrulevalueColumns)objList.get(i);
				String strZncode = objVWRValueColumns.getVwrvznid();
				String strZnvid = objVWRValueColumns.getVwrvznvid();
				if (StringUtility.isNull(strZncode)) {
					strNullZncodeVWRate = objVWRValueColumns.getVwrvvwrvvalue();
					continue;
				}
				String strActualZnvid = "";
				// 是否包含在缓冲中
				if (s_hmZvalueDistrict.containsKey(strZncode))
					strActualZnvid = s_hmZvalueDistrict.get(strZncode);
				else {
					strActualZnvid = String.valueOf(ZoneDemand.getZnvidByDistrict(objCWeightParameter.getDtcode(), 
							objCWeightParameter.getPostcode(), strZncode));
					s_hmZvalueDistrict.put(strZncode, strActualZnvid);
				}
				if (StringUtility.isNull(strActualZnvid)) continue;
				// 
				if (StringUtility.isNull(strZnvid)) {
					strNullZnvidVWRate = objVWRValueColumns.getVwrvvwrvvalue();
				} else if (strZnvid.equals(strActualZnvid)) {
					return objVWRValueColumns.getVwrvvwrvvalue();
				}
			}
		}
		if (!StringUtility.isNull(strNullZnvidVWRate)) return strNullZnvidVWRate;
		if (!StringUtility.isNull(strNullZncodeVWRate)) return strNullZncodeVWRate;
		return strVolumeWeightRate;
	}
	
	private String getCarryWeight(String strBrid, 
			String strChargeWeight) throws Exception {
		List objList = WeightRuleDemand.loadCarryweightrulevalue(strBrid);
		if (objList == null || objList.size() < 1) return "";
		
		BigDecimal objChargeWeight = new BigDecimal(strChargeWeight);
		for (int i = 0; i < objList.size(); i++) {
			CarryweightrulevalueColumns objCWRValueColumns = (CarryweightrulevalueColumns)objList.get(i);
			String strWeightGrade = objCWRValueColumns.getCwrvcwrvweightgrade();
			BigDecimal objWeightGrade = new BigDecimal(strWeightGrade);
			
			CarryweightrulevalueColumns objLastCWRValueColumns = (CarryweightrulevalueColumns)objList.get(objList.size() - 1);
			String strLastWeightGrade = objLastCWRValueColumns.getCwrvcwrvweightgrade();
			BigDecimal objLastWeightGrade = new BigDecimal(strLastWeightGrade);
			// 开始行和最后一行
			if (objChargeWeight.compareTo(objWeightGrade) <= 0)
				return objCWRValueColumns.getCwrvcwrvvalue();
			if (objChargeWeight.compareTo(objLastWeightGrade) >= 0)
				return objLastCWRValueColumns.getCwrvcwrvvalue();
			// 取中间值
			if (i < objList.size() - 1) {
				CarryweightrulevalueColumns objNextCWRValueColumns = (CarryweightrulevalueColumns)objList.get(i + 1);
				String strNextWeightGrade = objNextCWRValueColumns.getCwrvcwrvweightgrade();
				BigDecimal objNextWeightGrade = new BigDecimal(strNextWeightGrade);
				if (objChargeWeight.compareTo(objWeightGrade) > 0 && 
						objChargeWeight.compareTo(objNextWeightGrade) < 0)
					return objNextCWRValueColumns.getCwrvcwrvvalue();
			}
		}
		return "";
	}
}
