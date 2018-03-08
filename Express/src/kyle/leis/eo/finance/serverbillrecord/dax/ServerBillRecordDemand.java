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
	 * ��ѯ
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
	 * ͨ���������˵���ż�������
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
	 * װ�ط������˵���ϸ��¼
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
	 * ��ѯ�������˵���Ӧ������
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
	 * �Ƿ��Ѿ�������ͬ�˵��ķ���
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
	 * װ�ط������˵���¼
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
	 * ��ѯ�ջ������ͷ����������Ĳ���
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
	 * ������������б�
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
	 * ��÷��ò���
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
				// ������ò��
				String strFreightCharge = objDDColumns.getSwbswb_totalfreightcharge();
				if (StringUtility.isNull(strFreightCharge))
					strFreightCharge = "0";
				String strIncidentalCharge = objDDColumns.getSwbswb_totalincidentalcharge();
				if (StringUtility.isNull(strIncidentalCharge))
					strIncidentalCharge = "0";
				String strSurcharge = objDDColumns.getSwbswb_totalsurcharge();
				if (StringUtility.isNull(strSurcharge))
					strSurcharge = "0";
				// �˵�����
				BigDecimal objServerTotalCharge = new BigDecimal(strFreightCharge).add(new BigDecimal(strIncidentalCharge));
				objServerTotalCharge = objServerTotalCharge.add(new BigDecimal(strSurcharge));
				// ϵͳ����
				BigDecimal objFreightCharge = new BigDecimal(objDDColumns.getFreightcharge());
				BigDecimal objOilCharge = new BigDecimal(objDDColumns.getOilcharge());
				BigDecimal objOtherCharge = new BigDecimal(objDDColumns.getOthercharge());
				BigDecimal objTotalCharge = objFreightCharge.add(objOilCharge).add(objOtherCharge);
				// ���С��0.1Ԫ����Ϊ�����ڲ���
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
	 * ͳ�Ʒ������˵��ܷ���
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
	 * ͳ�Ʒ������˵�����
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
	 * ͳ�Ƶ�Ʊ���˵��е��˷�
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
	 * ͳ�Ƶ�Ʊ���˵��е�ȼ�ͷ�
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
	 * ͳ�Ƶ�Ʊ���˵��е��ӷ�
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
	 * ���÷������˵�����
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
		// �����ܶ�
		String strTotalCharge = objSBRColumns.getSbrsbrtotal();
		if (StringUtility.isNull(strTotalCharge))
			strTotalCharge = "0";
		objTSBR.setSbrTotal(new BigDecimal(strTotalCharge));
		// ��������
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
	 * ���÷������˵�����
	 * @param objSPYColumns
	 * @param objTfiServerwaybill
	 * @throws Exception
	 */
	public static void setServerWaybillByColumns(ServerpayableColumns objSPYColumns,
			TfiServerwaybill objTfiServerwaybill) throws Exception {
		BigDecimal objZero = new BigDecimal("0");
		// ����������
		String strChargeWeight = objSPYColumns.getSwbswbchargeweight();
		if (StringUtility.isNull(strChargeWeight))
			strChargeWeight = "0";
		BigDecimal objChargeWeight = new BigDecimal(strChargeWeight);
		if (objChargeWeight.compareTo(objZero) > 0)
			objTfiServerwaybill.setSwbChargeweight(new BigDecimal(strChargeWeight));
		else
			objTfiServerwaybill.setSwbChargeweight(objZero);
		// ����
		String strPieces = objSPYColumns.getSwbswbpieces();
		if (StringUtility.isNull(strPieces))
			strPieces = "1";
		objTfiServerwaybill.setSwbPieces(Integer.parseInt(strPieces));
		// ����ϵͳ���˵�ָ��
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
		// ����
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
		// �ҵ�����������������˵�
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
		// ��������
		TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objSPYColumns.getFkfkcode());
		objTfiServerpayable.setTdiFeekind(objTdiFeekind);
		// ��������
		String strOccurDate = objSPYColumns.getSpyspyoccurdate();
		if (StringUtility.isNull(strOccurDate)) {
			objTfiServerpayable.setSpyOccurdate(objDefaultOccurDate);
		} else {
			objTfiServerpayable.setSpyOccurdate(DateFormatUtility.getStandardDate(strOccurDate));
		}		
		// ����
		String strCkcode = objSPYColumns.getCkckcode();
		TdiCurrencykind objTCK = TdiCurrencykindDC.loadByKey(strCkcode);
		objTfiServerpayable.setTdiCurrencykind(objTCK);
		// ����
		objTfiServerpayable.setSpyCurrencyrate(new BigDecimal(objSPYColumns.getSpyspycurrencyrate()));		
	}
	
	/**
	 * ���÷�������
	 * @param objSPYColumns
	 * @throws Exception
	 */
	public static void setServerPayableFkcode(ServerpayableColumns objSPYColumns) throws Exception {
		// ���Ƚ��з�������ӳ��
		if (StringUtility.isNull(objSPYColumns.getSpyspyremark()))
			objSPYColumns.setFkfkcode("A0101");
		else if (StringUtility.isNull(objSPYColumns.getFkfkcode())) {
			String strFkcode = DictionaryDemand.getDHLFkcodeByServerFkname(objSPYColumns.getSpyspyremark());
			if (StringUtility.isNull(strFkcode)) {
				throw (new Exception("ͨ�������̷��������޷��ҵ���Ӧ��ϵͳ���ã������̷�������Ϊ" + 
						objSPYColumns.getSpyspyremark()));
			}
			objSPYColumns.setFkfkcode(strFkcode);
		}
	}
	
	
	/**
	 * ���÷������˵���ͳ����Ϣ
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
		// ����ϵͳ���˵�ָ��
		Long lReferencode = objTfiServerwaybill.getSwbReferencecode();
		if (lReferencode == null) {
			String strReferenceCode = getReferenceForServerwaybill(objSPYColumns);
			if (!StringUtility.isNull(strReferenceCode))
				objTfiServerwaybill.setSwbReferencecode(Long.parseLong(strReferenceCode));	
		}
		// �˷�
		if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_DETAIL_FREIGHT)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalfreightcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalfreightcharge(objTotalCharge);
		}
		// ȼ��
		if (strFkcode.equals(IFeeCalculateBasicData.FEEKIND_SURCHARGE_OIL)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalsurcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");			
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalsurcharge(objTotalCharge);
		}
		// �ӷ�
		if (strFkcode.startsWith(IFeeCalculateBasicData.FEEKIND_INCIDENTAL)) {
			BigDecimal objTotalCharge = objTfiServerwaybill.getSwbTotalincidentalcharge();
			if (objTotalCharge == null)
				objTotalCharge = new BigDecimal("0");			
			objTotalCharge = objTotalCharge.add(objColumnsTotalCharge);
			objTfiServerwaybill.setSwbTotalincidentalcharge(objTotalCharge);
		}
		// ����
		String strChargeWeight = objSPYColumns.getSwbswbchargeweight();
		if (StringUtility.isNull(strChargeWeight))
			strChargeWeight = "0";
		BigDecimal objChargeWeight = new BigDecimal(strChargeWeight);
		if (objChargeWeight.compareTo(new BigDecimal("0")) > 0)
			objTfiServerwaybill.setSwbChargeweight(objChargeWeight);
		// ����
		String strPieces = objSPYColumns.getSwbswbpieces();
		if (StringUtility.isNull(strPieces))
			strPieces = "0";
		if (Integer.parseInt(strPieces) > 0)
			objTfiServerwaybill.setSwbPieces(Integer.parseInt(strPieces));
	}
	
	/**
	 * ����Ӧ������
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
		// ����
		BigDecimal objTotalcharge = new BigDecimal(objServerpayableColumns.getSpyspytotalcharge());
		objPayableColumns.setPypyactualtotal(objTotalcharge);
		objPayableColumns.setPypycurrencyrate(new BigDecimal(objServerpayableColumns.getSpyspycurrencyrate()));
		// ��������
		objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(objServerpayableColumns.getSpyspyoccurdate()));
		objPayableColumns.setPypyremark("ϵͳ����ʱ�Զ����շ��ã���Ӧ�ķ������˵�" + 
				objServerpayableColumns.getSbrsbrlabelcode());
		objPayableColumns.setPypytotal(new BigDecimal(objServerpayableColumns.getSpyspytotalcharge()));
		// ����������
		BigDecimal objChargeweight = new BigDecimal(objSWBColumns.getSwbswb_chargeweight());
		objPayableColumns.setPypyunitnumber(objChargeweight);
		objPayableColumns.setPypyunitprice(objTotalcharge.divide(objChargeweight, 2, 4));
		return objPayableColumns;
	}
	
	/**
	 * ��ѯ©�յ��ӷ�
	 */
	public List getDifferenceincidentals(DifferenceincidentalsCondition objDifferenceincidentalsCondition) throws Exception{
		DifferenceincidentalsQuery objDifferenceincidentalsQuery=new DifferenceincidentalsQuery();
		objDifferenceincidentalsQuery.setCondition(objDifferenceincidentalsCondition);
		return objDifferenceincidentalsQuery.getResults();
	}
}
