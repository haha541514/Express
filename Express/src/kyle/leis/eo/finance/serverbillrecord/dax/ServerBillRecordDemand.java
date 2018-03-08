package kyle.leis.eo.finance.serverbillrecord.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import net.sf.hibernate.Session;


import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.DateUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.feecalculate.dax.IFeeCalculateBasicData;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ChargeweightdifferenceQuery;
import kyle.leis.eo.finance.serverbillrecord.da.CountserverpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.CountserverpayableCondition;
import kyle.leis.eo.finance.serverbillrecord.da.CountserverpayableQuery;
import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailColumns;
import kyle.leis.eo.finance.serverbillrecord.da.DifferencedetailQuery;
import kyle.leis.eo.finance.serverbillrecord.da.DifferenceincidentalsCondition;
import kyle.leis.eo.finance.serverbillrecord.da.DifferenceincidentalsQuery;
import kyle.leis.eo.finance.serverbillrecord.da.ListserverwaybillCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ListserverwaybillQuery;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordQuery;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableCondition;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableQuery;
import kyle.leis.eo.finance.serverbillrecord.da.ServerwaybillColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerwaybillQuery;
import kyle.leis.eo.finance.serverbillrecord.da.SumserverpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.SumserverpayableQuery;
import kyle.leis.eo.finance.serverbillrecord.da.SumserverwaybillColumns;
import kyle.leis.eo.finance.serverbillrecord.da.SumserverwaybillCondition;
import kyle.leis.eo.finance.serverbillrecord.da.SumserverwaybillQuery;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.fs.dictionary.dictionarys.dax.DictionaryDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiServerbillrecord;
import kyle.leis.hi.TfiServerpayable;
import kyle.leis.hi.TfiServerwaybill;

public class ServerBillRecordDemand {
	/**
	 * 查询
	 * @param objSBRCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(ServerbillrecordCondition objSBRCondition) throws Exception {
		ServerbillrecordQuery objSBRQuery = new ServerbillrecordQuery();
		objSBRQuery.setCondition(objSBRCondition);
		return objSBRQuery.getResults();
	}
	
	public static ServerbillrecordColumns load(String strSbrId) throws Exception {
		ServerbillrecordQuery objSBRQuery = new ServerbillrecordQuery();
		objSBRQuery.setSbrid(strSbrId);
		List objList = objSBRQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ServerbillrecordColumns)objList.get(0);
	}	
	
	public static List queryWaybill(String strSbrId) throws Exception {
			
		  ListserverwaybillQuery objSWBQuery = new ListserverwaybillQuery();
		  ListserverwaybillCondition objSWBCondition = new ListserverwaybillCondition();
		  objSWBCondition.setSbrid(strSbrId);		  
		  objSWBQuery.setCondition(objSWBCondition);
		  List objList = objSWBQuery.getResults();
		  if(objList == null || objList.size() == 0)
			  return null;		 
		  return objList;	 
	}
	
	public static CountserverpayableColumns CountPayable(String strSwbcode) throws Exception {
			
		  CountserverpayableQuery objSPBQuery = new CountserverpayableQuery();
		  CountserverpayableCondition objSPBCondition = new CountserverpayableCondition();
		  objSPBCondition.setSwbcode(strSwbcode);
		  objSPBQuery.setCondition(objSPBCondition);
		  List listPayable = objSPBQuery.getResults();
		  if(listPayable == null || listPayable.size() == 0)
			  return null;		
		  CountserverpayableColumns objCSPBColumns = (CountserverpayableColumns) listPayable.get(0);
		return objCSPBColumns;
	}
	
	/**
	 * 通过服务商账单编号加载数据
	 * @param strSBRLabelcode
	 * @return
	 * @throws Exception
	 */
	public static ServerbillrecordColumns loadByLabelcode(String strSBRLabelcode,
			String strChncode) throws Exception {
		ServerbillrecordQuery objSBRQuery = new ServerbillrecordQuery();
		objSBRQuery.setSbrlabelcode(strSBRLabelcode);
		objSBRQuery.setChncode(strChncode);
		List objList = objSBRQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ServerbillrecordColumns)objList.get(0);
	}	
	
	
	/**
	 * 装载服务商账单明细记录
	 * @param strSbrid
	 * @return
	 * @throws Exception
	 */
	public static List loadPayable(String strSbrid) throws Exception {
		ServerpayableQuery objSPYQuery = new ServerpayableQuery();
		objSPYQuery.setSbrid(strSbrid);
		return objSPYQuery.getResults();
	}
	
	/**
	 * 查询服务商账单的应付费用
	 * @param objSPYCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryPayable(ServerpayableCondition objSPYCondition) throws Exception {
		ServerpayableQuery objSPYQuery = new ServerpayableQuery();
		objSPYQuery.setCondition(objSPYCondition);
		return objSPYQuery.getResults();
	}
	
	public static ServerwaybillColumns loadPayableByEwbcode(String strSbrid, 
			String strEwbcode) throws Exception {
		ServerpayableQuery objSPYQuery = new ServerpayableQuery();
		objSPYQuery.setSbrid(strSbrid);
		objSPYQuery.setSbdserverewbcode(strEwbcode);
		List objList = objSPYQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ServerwaybillColumns)objList.get(0);
	}
	
	/**
	 * 是否已经存在相同运单的费用
	 * @param strServerEwbcode
	 * @param strFkcode
	 * @param strChncode
	 * @return
	 * @throws Exception
	 */
	public static boolean isExistsPayable(String strServerEwbcode, 
			String strFkcode, 
			String strChncode,
			String strSbrLabelcode) throws Exception {
		ServerpayableQuery objSPYQuery = new ServerpayableQuery();
		objSPYQuery.setSbdserverewbcode(strServerEwbcode);
		objSPYQuery.setFkcode(strFkcode);
		objSPYQuery.setChncode(strChncode);
		objSPYQuery.setSbrlabelcode(strSbrLabelcode);
		List objList = objSPYQuery.getResults();
		if (objList == null || objList.size() < 1)
			return false;
		return true;
	}	
	
	
	/**
	 * 装载服务商运单记录
	 * @param strServerEwbcode
	 * @param strChncode
	 * @return
	 * @throws Exception
	 */
	public static ServerwaybillColumns loadServerwaybill(String strServerEwbcode, 
			String strChncode,
			String strReference) throws Exception {
		ServerwaybillQuery objSWBQuery = new ServerwaybillQuery();
		objSWBQuery.setChncode(strChncode);
		objSWBQuery.setSbdserverewbcode(strServerEwbcode);
		objSWBQuery.setSbdreferencecode(strReference);
		List objList = objSWBQuery.getResults();
		if (objList == null || objList.size() < 1)
			return null;
		return (ServerwaybillColumns)objList.get(0);
	}
	
	/**
	 * 查询收货重量和服务商重量的差异
	 * @param objCWDCondition
	 * @return
	 * @throws Exception
	 */
	public static List queryCWDifference(ChargeweightdifferenceCondition objCWDCondition,
			boolean isHasNoServerBillRecord) 
	throws Exception {
		ChargeweightdifferenceQuery objCWDQuery = new ChargeweightdifferenceQuery();
		if (isHasNoServerBillRecord)
			objCWDQuery = new ChargeweightdifferenceQueryEX();
		objCWDQuery.setCondition(objCWDCondition);
		return objCWDQuery.getResults();
	}
	
	
	/**
	 * 获得重量差异列表
	 * @param strSbrid
	 * @return
	 * @throws Exception
	 */
	public static List queryServerCWDifference(String strSbrid) throws Exception {
		DifferencedetailQuery objDDQuery = new DifferencedetailQuery();
		objDDQuery.setSbrid(strSbrid);
		return objDDQuery.getResults();
	}
	
	/**
	 * 获得费用差异
	 * @param strSbrid
	 * @return
	 * @throws Exception
	 */
	public static List queryChargeDifference(String strSbrid, boolean isChargeEqual) throws Exception {
		CharegeweightEqualQuery objCWEQuery = new CharegeweightEqualQuery();
		objCWEQuery.setSbrid(strSbrid);
		List listChargeweightEqual = objCWEQuery.getResults();
		List<DifferencedetailColumns> listChargeDifference = new ArrayList<DifferencedetailColumns>();
		if (listChargeweightEqual != null && listChargeweightEqual.size() > 0)
			for (int i = 0; i < listChargeweightEqual.size(); i++) {
				DifferencedetailColumns objDDColumns = (DifferencedetailColumns)listChargeweightEqual.get(i);
				// 计算费用差额
				String strFreightCharge = objDDColumns.getSwbswb_totalfreightcharge();
				if (StringUtility.isNull(strFreightCharge))
					strFreightCharge = "0";
				String strIncidentalCharge = objDDColumns.getSwbswb_totalincidentalcharge();
				if (StringUtility.isNull(strIncidentalCharge))
					strIncidentalCharge = "0";
				String strSurcharge = objDDColumns.getSwbswb_totalsurcharge();
				if (StringUtility.isNull(strSurcharge))
					strSurcharge = "0";
				// 账单费用
				BigDecimal objServerTotalCharge = new BigDecimal(strFreightCharge).add(new BigDecimal(strIncidentalCharge));
				objServerTotalCharge = objServerTotalCharge.add(new BigDecimal(strSurcharge));
				// 系统费用
				BigDecimal objFreightCharge = new BigDecimal(objDDColumns.getFreightcharge());
				BigDecimal objOilCharge = new BigDecimal(objDDColumns.getOilcharge());
				BigDecimal objOtherCharge = new BigDecimal(objDDColumns.getOthercharge());
				BigDecimal objTotalCharge = objFreightCharge.add(objOilCharge).add(objOtherCharge);
				// 差额小于0.1元则认为不存在差异
				BigDecimal objCompareTotalCharge = objServerTotalCharge.add(objTotalCharge.multiply(new BigDecimal("-1")));
				objCompareTotalCharge = objCompareTotalCharge.abs();
				if (!isChargeEqual && objCompareTotalCharge.compareTo(new BigDecimal("0.1")) > 0)
					listChargeDifference.add(objDDColumns);
				else if (isChargeEqual && objCompareTotalCharge.compareTo(new BigDecimal("0.1")) <= 0)
					listChargeDifference.add(objDDColumns);
				
			}
		return listChargeDifference;
	}
	
	
	/**
	 * 统计服务商账单总费用
	 * @param strSbrId
	 * @return
	 * @throws Exception
	 */
	public static String sumServerBillDetailTotal(String strSbrId) throws Exception {
		SumserverpayableQuery objSPYQuery = new SumserverpayableQuery();
		objSPYQuery.setSprid(strSbrId);
		List objList = objSPYQuery.getResults();
		if (objList == null || objList.size() < 1)
			return "0";
		return ((SumserverpayableColumns)objList.get(0)).getTotalcharge();
	}
	
	/**
	 * 统计服务商运单费用
	 * @param objSSWBCondition
	 * @return
	 * @throws Exception
	 */
	public static String sumServerWaybillCharge(SumserverwaybillCondition objSSWBCondition) throws Exception {
		SumserverwaybillQuery objSSWBQuery = new SumserverwaybillQuery();
		objSSWBQuery.setCondition(objSSWBCondition);
		List objList = objSSWBQuery.getResults();
		if (objList == null || objList.size() < 1)
			return "0";
		return ((SumserverwaybillColumns)objList.get(0)).getTotalcharge();
	}
	
	/**
	 * 统计单票件账单中的运费
	 * @param strServerEwbcode
	 * @return
	 * @throws Exception
	 */
	public static String sumServerFreightCharge(String strServerEwbcode,
			String strReferencecode) throws Exception {
		SumserverwaybillCondition objSSWBCondition = new SumserverwaybillCondition();
		objSSWBCondition.setSwbserverewbcode(strServerEwbcode);
		objSSWBCondition.setInfkcode("A0101");
		objSSWBCondition.setSwbreferencecode(strReferencecode);
		return sumServerWaybillCharge(objSSWBCondition);
	}
	
	/**
	 * 统计单票件账单中的燃油费
	 * @param strServerEwbcode
	 * @return
	 * @throws Exception
	 */
	public static String sumServerOilCharge(String strServerEwbcode,
			String strReferencecode) throws Exception {
		SumserverwaybillCondition objSSWBCondition = new SumserverwaybillCondition();
		objSSWBCondition.setSwbserverewbcode(strServerEwbcode);
		objSSWBCondition.setInfkcode("A0102");
		objSSWBCondition.setSwbreferencecode(strReferencecode);
		return sumServerWaybillCharge(objSSWBCondition);
	}	
	
	/**
	 * 统计单票件账单中的杂费
	 * @param strServerEwbcode
	 * @return
	 * @throws Exception
	 */
	public static String sumServerIncidentalCharge(String strServerEwbcode,
			String strReferencecode) throws Exception {
		SumserverwaybillCondition objSSWBCondition = new SumserverwaybillCondition();
		objSSWBCondition.setSwbserverewbcode(strServerEwbcode);
		objSSWBCondition.setNotinfkcode("A0101,A0102");
		objSSWBCondition.setSwbreferencecode(strReferencecode);
		return sumServerWaybillCharge(objSSWBCondition);
	}		
	
	/**
	 * 设置服务商账单属性
	 * @param objSBRColumns
	 * @param objTSBR
	 * @param strOperId
	 * @param objSession
	 * @throws Exception
	 */
	public static void setBillRecordByColumns(ServerbillrecordColumns objSBRColumns,
			TfiServerbillrecord objTSBR,
			String strOperId,
			Session objSession) throws Exception {
		objTSBR.setSbrLabelcode(objSBRColumns.getSbrsbrlabelcode());
		objTSBR.setSbrModifydate(DateFormatUtility.getSysdate());
		objTSBR.setSbrOccurdate(DateFormatUtility.getStandardDate(objSBRColumns.getSbrsbroccurdate()));
		objTSBR.setSbrRemark(objSBRColumns.getSbrsbrremark());
		// 费用总额
		String strTotalCharge = objSBRColumns.getSbrsbrtotal();
		if (StringUtility.isNull(strTotalCharge))
			strTotalCharge = "0";
		objTSBR.setSbrTotal(new BigDecimal(strTotalCharge));
		// 服务渠道
		if (!StringUtility.isNull(objSBRColumns.getChnchncode())) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objSBRColumns.getChnchncode());
			objTSBR.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(objSBRColumns.getEeeecode())) {
			TdiEnterpriseelement objTEE = TdiEnterpriseelementDC.loadByKey(objSBRColumns.getEeeecode());
			objTSBR.setTdiEnterpriseelement(objTEE);
		}
		if (!StringUtility.isNull(objSBRColumns.getCkckcode())) {
			TdiCurrencykind objTCK = TdiCurrencykindDC.loadByKey(objSBRColumns.getCkckcode());
			objTSBR.setTdiCurrencykind(objTCK);
		}
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
		objTSBR.setTdiOperatorByOpIdModifier(objTdiOperator);
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey("NW");
		objTSBR.setTdiSimplestatus(objTSS);
	}
	
	/**
	 * 设置服务商运单属性
	 * @param objSPYColumns
	 * @param objTfiServerwaybill
	 * @throws Exception
	 */
	public static void setServerWaybillByColumns(ServerpayableColumns objSPYColumns,
			TfiServerwaybill objTfiServerwaybill) throws Exception {
		BigDecimal objZero = new BigDecimal("0");
		// 服务商重量
		String strChargeWeight = objSPYColumns.getSwbswbchargeweight();
		if (StringUtility.isNull(strChargeWeight))
			strChargeWeight = "0";
		BigDecimal objChargeWeight = new BigDecimal(strChargeWeight);
		if (objChargeWeight.compareTo(objZero) > 0)
			objTfiServerwaybill.setSwbChargeweight(new BigDecimal(strChargeWeight));
		else
			objTfiServerwaybill.setSwbChargeweight(objZero);
		// 件数
		String strPieces = objSPYColumns.getSwbswbpieces();
		if (StringUtility.isNull(strPieces))
			strPieces = "1";
		objTfiServerwaybill.setSwbPieces(Integer.parseInt(strPieces));
		// 设置系统中运单指向
		/*
		String strServerEwbcode = objSPYColumns.getSwbswbserverewbcode();
		SimplecorewaybillColumns objSCColumns = CorewaybillDemand.loadByServerEwbcode(strServerEwbcode, 
				objSPYColumns.getChnchncode());
		if (objSCColumns != null && 
				!StringUtility.isNull(objSCColumns.getCwcw_code()))
			objTfiServerwaybill.setSwbReferencecode(Long.parseLong(objSCColumns.getCwcw_code()));
		*/
		if (!StringUtility.isNull(objSPYColumns.getSwbswbreferencecode()))
			objTfiServerwaybill.setSwbReferencecode(Long.parseLong(objSPYColumns.getSwbswbreferencecode()));
		// 渠道
		TchnChannel objTchnChannel = TchnChannelDC.loadByKey(objSPYColumns.getChnchncode());
		objTfiServerwaybill.setTchnChannel(objTchnChannel);
		objTfiServerwaybill.setSwbServerewbcode(objSPYColumns.getSwbswbserverewbcode());
		objTfiServerwaybill.setSwbCustomerewbcode(objSPYColumns.getSwbswbcustomerewbcode());
	}
	
	public static String getReferenceForServerwaybill(ServerpayableColumns objSPYColumns) throws Exception {
		List listResults = CorewaybillDemand.loadByServerEwbcode(objSPYColumns.getSwbswbserverewbcode(), 
				objSPYColumns.getChnchncode());
		if (listResults == null || listResults.size() < 1)
			return "";
		if (listResults.size() == 1)
			return ((SimplecorewaybillColumns)listResults.get(0)).getCwcw_code();
		// 找到跟费用日期最近的运单
		SimplecorewaybillColumns objSCBCLatest = null;
		Long llLatest = 999999999999L;
		for (int i = 0; i < listResults.size(); i++) {
			SimplecorewaybillColumns objSCBC = (SimplecorewaybillColumns)listResults.get(i);
			GregorianCalendar objGCCreate = DateUtility.getCalendar(objSCBC.getCwcw_createdate());
			Long ll = Math.abs(DateUtility.getCalendar(objSPYColumns.getSpyspyoccurdate()).getTimeInMillis() - objGCCreate.getTimeInMillis());
			if (ll < llLatest) {
				llLatest = ll;
				objSCBCLatest = objSCBC;
			}
		}
		if (objSCBCLatest != null)
			return objSCBCLatest.getCwcw_code();
		return "";
	}
	
	
	public static void setServerPayableByColumns(ServerpayableColumns objSPYColumns,
			TfiServerpayable objTfiServerpayable,
			Date objDefaultOccurDate) throws Exception {
		objTfiServerpayable.setSpyRemark(objSPYColumns.getSpyspyremark());
		objTfiServerpayable.setSpyTotalcharge(new BigDecimal(objSPYColumns.getSpyspytotalcharge()));
		// 费用类型
		TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objSPYColumns.getFkfkcode());
		objTfiServerpayable.setTdiFeekind(objTdiFeekind);
		// 发生日期
		String strOccurDate = objSPYColumns.getSpyspyoccurdate();
		if (StringUtility.isNull(strOccurDate)) {
			objTfiServerpayable.setSpyOccurdate(objDefaultOccurDate);
		} else {
			objTfiServerpayable.setSpyOccurdate(DateFormatUtility.getStandardDate(strOccurDate));
		}		
		// 币种
		String strCkcode = objSPYColumns.getCkckcode();
		TdiCurrencykind objTCK = TdiCurrencykindDC.loadByKey(strCkcode);
		objTfiServerpayable.setTdiCurrencykind(objTCK);
		// 汇率
		objTfiServerpayable.setSpyCurrencyrate(new BigDecimal(objSPYColumns.getSpyspycurrencyrate()));		
	}
	
	/**
	 * 设置费用类型
	 * @param objSPYColumns
	 * @throws Exception
	 */
	public static void setServerPayableFkcode(ServerpayableColumns objSPYColumns) throws Exception {
		// 首先进行费用种类映射
		if (StringUtility.isNull(objSPYColumns.getSpyspyremark()))
			objSPYColumns.setFkfkcode("A0101");
		else if (StringUtility.isNull(objSPYColumns.getFkfkcode())) {
			String strFkcode = DictionaryDemand.getDHLFkcodeByServerFkname(objSPYColumns.getSpyspyremark());
			if (StringUtility.isNull(strFkcode)) {
				throw (new Exception("通过服务商费用类型无法找到对应的系统费用，服务商费用名称为" + 
						objSPYColumns.getSpyspyremark()));
			}
			objSPYColumns.setFkfkcode(strFkcode);
		}
	}
	
	
	/**
	 * 重置服务商运单的统计信息
	 * @param objTfiServerwaybill
	 * @param objSPYColumns
	 * @throws Exception
	 */
	public static void resetWaybillFee(TfiServerwaybill objTfiServerwaybill,
			ServerpayableColumns objSPYColumns) throws Exception {
		String strTotalCharge = objSPYColumns.getSpyspytotalcharge();
		if (StringUtility.isNull(strTotalCharge))
			strTotalCharge = "0";
		// String strCurrencyRate = objSPYColumns.getSpyspycurrencyrate();
		// BigDecimal objColumnsTotalCharge = new BigDecimal(strTotalCharge).multiply(new BigDecimal(strCurrencyRate));
		BigDecimal objColumnsTotalCharge = new BigDecimal(strTotalCharge);
		objColumnsTotalCharge = objColumnsTotalCharge.divide(new BigDecimal("1"), 2, 4);
		String strFkcode = objSPYColumns.getFkfkcode();
		// 设置系统中运单指向
		Long lReferencode = objTfiServerwaybill.getSwbReferencecode();
		if (lReferencode == null) {
			String strReferenceCode = getReferenceForServerwaybill(objSPYColumns);
			if (!StringUtility.isNull(strReferenceCode))
				objTfiServerwaybill.setSwbReferencecode(Long.parseLong(strReferenceCode));	
		}
		// 运费
		if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalfreightcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalfreightcharge(objTotalCharge);
		}
		// 燃油
		if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalsurcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");			
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalsurcharge(objTotalCharge);
		}
		// 杂费
		if (strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalincidentalcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");			
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalincidentalcharge(objTotalCharge);
		}
		// 重量
		String strChargeWeight = objSPYColumns.getSwbswbchargeweight();
		if (StringUtility.isNull(strChargeWeight))
			strChargeWeight = "0";
		BigDecimal objChargeWeight = new BigDecimal(strChargeWeight);
		if (objChargeWeight.compareTo(new BigDecimal("0")) > 0)
			objTfiServerwaybill.setSwbChargeweight(objChargeWeight);
		// 件数
		String strPieces = objSPYColumns.getSwbswbpieces();
		if (StringUtility.isNull(strPieces))
			strPieces = "0";
		if (Integer.parseInt(strPieces) > 0)
			objTfiServerwaybill.setSwbPieces(Integer.parseInt(strPieces));
	}
	
	/**
	 * 构造应付费用
	 * @param objDDColumns
	 * @return
	 */
	public static PayableColumns buildByServerPayable(ServerpayableColumns objServerpayableColumns,
			ServerwaybillColumns objSWBColumns,
			String strOperId) {
		PayableColumns objPayableColumns = new PayableColumns();
		objPayableColumns.setBkbkcode("A0201");
		objPayableColumns.setChnchncode(objServerpayableColumns.getChnchncode());
		objPayableColumns.setCkckcode(objServerpayableColumns.getCkckcode());
		objPayableColumns.setCococode(objServerpayableColumns.getCococode());
		objPayableColumns.setCwcwcode(Long.parseLong(objServerpayableColumns.getSwbswbreferencecode()));
		objPayableColumns.setPypycommissionrate(new BigDecimal("0"));
		objPayableColumns.setFkfkcode(objServerpayableColumns.getFkfkcode());
		objPayableColumns.setFsfscode("C");
		objPayableColumns.setSpyspyid(Long.parseLong(objServerpayableColumns.getSpyspyid()));
		objPayableColumns.setMopopid(Long.parseLong(strOperId));
		objPayableColumns.setCopopid(Long.parseLong(strOperId));
		// 费用
		BigDecimal objTotalcharge = new BigDecimal(objServerpayableColumns.getSpyspytotalcharge());
		objPayableColumns.setPypyactualtotal(objTotalcharge);
		objPayableColumns.setPypycurrencyrate(new BigDecimal(objServerpayableColumns.getSpyspycurrencyrate()));
		// 发生日期
		objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(objServerpayableColumns.getSpyspyoccurdate()));
		objPayableColumns.setPypyremark("系统对账时自动加收费用，对应的服务商账单" + 
				objServerpayableColumns.getSbrsbrlabelcode());
		objPayableColumns.setPypytotal(new BigDecimal(objServerpayableColumns.getSpyspytotalcharge()));
		// 服务商重量
		BigDecimal objChargeweight = new BigDecimal(objSWBColumns.getSwbswb_chargeweight());
		objPayableColumns.setPypyunitnumber(objChargeweight);
		objPayableColumns.setPypyunitprice(objTotalcharge.divide(objChargeweight, 2, 4));
		return objPayableColumns;
	}
	
	/**
	 * 查询漏收的杂费
	 */
	public List getDifferenceincidentals(DifferenceincidentalsCondition objDifferenceincidentalsCondition) throws Exception{
		DifferenceincidentalsQuery objDifferenceincidentalsQuery=new DifferenceincidentalsQuery();
		objDifferenceincidentalsQuery.setCondition(objDifferenceincidentalsCondition);
		return objDifferenceincidentalsQuery.getResults();
	}
}
