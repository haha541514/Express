package kyle.leis.eo.billing.payable.dax;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.da.PayableCondition;
import kyle.leis.eo.billing.payable.da.PayableQuery;
import kyle.leis.eo.billing.payable.da.PayableforbillColumns;
import kyle.leis.eo.billing.payable.da.PayableforbillCondition;
import kyle.leis.eo.billing.payable.da.PayableforbillQuery;
import kyle.leis.eo.billing.payable.da.SumhasaccountpyColumns;
import kyle.leis.eo.billing.payable.da.SumhasaccountpyQuery;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.es.company.channel.da.ChannelColumns;
import kyle.leis.es.company.channel.da.ChannelCondition;
import kyle.leis.es.company.channel.dax.ChannelDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillingkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.feekind.da.FeekindColumns;
import kyle.leis.fs.dictionary.feekind.da.FeekindCondition;
import kyle.leis.fs.dictionary.feekind.dax.FeekindDemand;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiBillingkind;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiFeekind;
import kyle.leis.hi.TdiFeestatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiServerpayable;
import kyle.leis.hi.ThiPayable;
import kyle.leis.hi.TopCorewaybill;

public class PayableDemand {
	public static List query(PayableCondition objPayableCondition) 
	throws Exception {
		PayableQuery objPayableQuery = new PayableQuery();
		objPayableQuery.setCondition(objPayableCondition);
		return objPayableQuery.getResults();
	}
	
	public static List load(String strCwcode, String strBkcode) throws Exception {
		PayableCondition objPayableCondition = new PayableCondition();
		objPayableCondition.setCwcode(strCwcode);
		objPayableCondition.setBkcode(strBkcode);
		return query(objPayableCondition);
	}
	
	/**
	 * 是否存在草稿费用
	 * @param strCwcode
	 * @return
	 * @throws Exception
	 */
	public static boolean isExistsDraftFee(String strCwcode) throws Exception {
		PayableCondition objPayableCondition = new PayableCondition();
		objPayableCondition.setCwcode(strCwcode);
		objPayableCondition.setFscode("D");
		
		PayableQuery objPayableQuery = new PayableQuery();
		objPayableQuery.setCondition(objPayableCondition);
		List objList = objPayableQuery.getResults();
		if (objList == null || objList.size() < 1) return false;
		return true;
	}
	
	
	public static PayableColumns loadByPyid(String strPyid) throws Exception {
		PayableQuery objPayableQuery = new PayableQuery();
		objPayableQuery.setPyid(strPyid);
		List objList = objPayableQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		return (PayableColumns)objList.get(0);
	}
	
	public static BigDecimal sumActualTotal(List listPayable, boolean isOriginCurrency) {
		BigDecimal objSumActualTotal = new BigDecimal("0");
		if (listPayable == null || listPayable.size() < 1)
			return objSumActualTotal;
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			BigDecimal objActualTotal = new BigDecimal(objPayableColumns.getPypyactualtotal());
			BigDecimal objCurrencyRate = new BigDecimal(objPayableColumns.getPypycurrencyrate());
			
			if (!isOriginCurrency) {
				objActualTotal = objActualTotal.multiply(objCurrencyRate).divide(new BigDecimal("1"), 2, 4);
			}
			objSumActualTotal = objSumActualTotal.add(objActualTotal);
		}
		return objSumActualTotal;
	}
	
	public static String sumHasAccountingAmount(String strCocode,
			String strBrid) throws Exception {
		SumhasaccountpyQuery objSHAPQ = new SumhasaccountpyQuery();
		objSHAPQ.setBrid(strBrid);
		objSHAPQ.setCocode(strCocode);
		List listResults = objSHAPQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumhasaccountpyColumns)listResults.get(0)).getSumpytotal();
	}

	public static String sumHasAccountingOriginAmount(String strCocode,
			String strBrid) throws Exception {
		SumhasaccountpyQueryEX objSHAPQ = new SumhasaccountpyQueryEX();
		objSHAPQ.setBrid(strBrid);
		objSHAPQ.setCocode(strCocode);
		List listResults = objSHAPQ.getResults();
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumhasaccountpyColumns)listResults.get(0)).getSumpytotal();
	}	
	
	
	public static BigDecimal sumActualTotal(List listPayable, 
			String strFkcode,
			boolean isOriginCurrency) {
		BigDecimal objSumActualTotal = new BigDecimal("0");
		if (listPayable == null || listPayable.size() < 1)
			return objSumActualTotal;
		for (int i = 0; i < listPayable.size(); i++) {
			PayableColumns objPayableColumns = (PayableColumns)listPayable.get(i);
			// 是否包含多个费用
			if (strFkcode.indexOf(",") >= 0) {
				if (strFkcode.indexOf(objPayableColumns.getFkfkcode()) < 0)
					continue;
			} else {
				if (!objPayableColumns.getFkfkcode().equals(strFkcode))
					continue;
			}
			BigDecimal objActualTotal = new BigDecimal(objPayableColumns.getPypyactualtotal());
			BigDecimal objCurrencyRate = new BigDecimal(objPayableColumns.getPypycurrencyrate());
			if (!isOriginCurrency) {
				objActualTotal = objActualTotal.multiply(objCurrencyRate).divide(new BigDecimal("1"), 2, 4);
			}
			objSumActualTotal = objSumActualTotal.add(objActualTotal);
		}
		return objSumActualTotal;
	}	
	
	
	public static BigDecimal sumActualTotal(String strCwcode) throws Exception {
		List listPayable = load(strCwcode, "A0201");
		return sumActualTotal(listPayable, false);
	}	
	
	
	public static List queryForBill(PayableforbillCondition objPForbillCondition) 
	throws Exception {
		PayableforbillQuery objPForbillQuery = new PayableforbillQuery();
		
		if (!StringUtility.isNull(objPForbillCondition.getCcocode()))
			objPForbillCondition.setCocode(objPForbillCondition.getCcocode());
		if (!StringUtility.isNull(objPForbillCondition.getScocode()))
			objPForbillCondition.setCocode(objPForbillCondition.getScocode());
		if (!StringUtility.isNull(objPForbillCondition.getIncustomerewbcode()) &&
				StringUtility.isNull(objPForbillCondition.getInserverewbcode()))
			objPForbillCondition.setInserverewbcode("TTTXXX");
		if (StringUtility.isNull(objPForbillCondition.getIncustomerewbcode()) &&
				!StringUtility.isNull(objPForbillCondition.getInserverewbcode()))
			objPForbillCondition.setIncustomerewbcode("TTTXXX");
		
		// 临时用
		/*
		if (!StringUtility.isNull(objPForbillCondition.getPmcode()) &&
				objPForbillCondition.getPmcode().equals("AFR")) {
			objPForbillCondition.setCkcode("RMB");
			objPForbillCondition.setPmcode("");
		}
		*/
		objPForbillQuery.setCondition(objPForbillCondition);
		return objPForbillQuery.getResults();
	}
	
	public static List<PayableColumns> transferBillPyToPyColumns(List listCwPayable) {
		List<PayableColumns> listPFBColumns = new ArrayList<PayableColumns>();
		if (listCwPayable == null || listCwPayable.size() < 1) 
			return listPFBColumns;
		for (int i = 0; i < listCwPayable.size(); i++) {
			PayableColumns objPayableColumns = new PayableColumns();
			
			PayableforbillColumns objPFBColumns = (PayableforbillColumns)listCwPayable.get(i);
			objPayableColumns.setPypyid(Long.parseLong(objPFBColumns.getPypyid()));
			objPayableColumns.setPypyactualtotal(new BigDecimal(objPFBColumns.getPypyactualtotal()));
			objPayableColumns.setPypycurrencyrate(new BigDecimal(objPFBColumns.getPypycurrencyrate()));
			objPayableColumns.setFkfkcode(objPFBColumns.getFkfkcode());
			objPayableColumns.setCkckcode(objPFBColumns.getCkckcode());
			
			listPFBColumns.add(objPayableColumns);
		}
		return listPFBColumns;
	}	
	
	
	public static PayableColumns getOriginPayable(PayableColumns objPayableColumns, 
			List listOriginPayColumns) throws Exception {
		if (listOriginPayColumns == null || listOriginPayColumns.size() < 1)
			return null;
		for (int i = 0; i < listOriginPayColumns.size(); i++) {
			PayableColumns objOriginPayColumns = (PayableColumns)listOriginPayColumns.get(i);
			// 修改则判断主键是否一致
			if (!StringUtility.isNull(objPayableColumns.getPypyid())) {
				if (objPayableColumns.getPypyid().equals(objOriginPayColumns.getPypyid()))
					return objOriginPayColumns;
				continue;
			}
			/*
			if (objPayableColumns.getCococode().equals(objOriginPayColumns.getCococode()) &&
					objPayableColumns.getFkfkcode().equals(objOriginPayColumns.getFkfkcode()) &&
					objPayableColumns.getCwcwcode().equals(objOriginPayColumns.getCwcwcode()) &&
					StringUtility.isNull(objOriginPayColumns.getPypyidreference())) {
				// 如果有渠道则判断渠道是否一致
				if (!StringUtility.isNull(objPayableColumns.getChnchncode()) &&
						!StringUtility.isNull(objOriginPayColumns.getChnchncode()) && 
						objPayableColumns.getChnchncode().equals(objOriginPayColumns.getChnchncode()))
					return objOriginPayColumns;
				// 如果渠道同时为空，也认为一致
				if (StringUtility.isNull(objPayableColumns.getChnchncode()) && 
						StringUtility.isNull(objOriginPayColumns.getChnchncode()))
						return objOriginPayColumns;
			}
			*/
			if (objPayableColumns.getFkfkcode().equals(objOriginPayColumns.getFkfkcode()) &&
					objPayableColumns.getCwcwcode().equals(objOriginPayColumns.getCwcwcode()) &&
					StringUtility.isNull(objOriginPayColumns.getPypyidreference()) &&
					objPayableColumns.getBkbkcode().equals(objOriginPayColumns.getBkbkcode())) {
				return objOriginPayColumns;
			}		
		}
		return null;
	}
	
	public static PayableColumns buildPayable(HousewaybillColumns objHwcolumns,
			BigDecimal objUnitnumber,
			BigDecimal objFeeAmount,
			String strFkcode,
			String strCkcode,
			String strRemark,
			String strCurrencyrate,
			String strOperID,
			String strChncode) throws Exception {
		PayableColumns objPayableColumns = new PayableColumns();
		objPayableColumns.setBkbkcode("A0201");
		if (!StringUtility.isNull(strChncode)) {
			TchnChannel objTchnChannel = TchnChannelDC.loadByKey(strChncode);
			objPayableColumns.setCococode(objTchnChannel.getTcoCorporation().getCoCode());
			objPayableColumns.setChnchncode(objTchnChannel.getChnCode());
		} else {
			objPayableColumns.setCococode(objHwcolumns.getScococode());
			objPayableColumns.setChnchncode(objHwcolumns.getSchnchncode());
		}
		objPayableColumns.setCkckcode(strCkcode);
		objPayableColumns.setCopopid(Long.parseLong(strOperID));
		objPayableColumns.setCwcwcode(Long.parseLong(objHwcolumns.getHwcwcode()));
		objPayableColumns.setFkfkcode(strFkcode);
		objPayableColumns.setFsfscode("C");
		objPayableColumns.setMopopid(Long.parseLong(strOperID));
		objPayableColumns.setPypyactualtotal(objFeeAmount);
		objPayableColumns.setPypycreatedate(DateFormatUtility.getSysdate());
		objPayableColumns.setPypycurrencyrate(new BigDecimal(strCurrencyrate));
		objPayableColumns.setPypymodifydate(DateFormatUtility.getSysdate());
		objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(objHwcolumns.getDbwadddate()));
		objPayableColumns.setPypyremark(strRemark);
		objPayableColumns.setPypytotal(objFeeAmount);
		objPayableColumns.setPypyunitnumber(objUnitnumber);
		objPayableColumns.setPypycommissionrate(new BigDecimal("0"));
		
		if (objUnitnumber.compareTo(new BigDecimal("0")) == 0)
			objPayableColumns.setPypyunitprice(new BigDecimal("1"));
		else
			objPayableColumns.setPypyunitprice(objFeeAmount.divide(objUnitnumber, 2, 4));
		
		return objPayableColumns;
	}
	
	public static void setPayableFromColumns(TblPayable objTblPayable, 
			PayableColumns objPayableColumns, 
			String strOperId, 
			Session objSession) throws Exception {
		if (!StringUtility.isNull(objPayableColumns.getPyepcode()))
			objTblPayable.setEpCode(Long.parseLong(objPayableColumns.getPyepcode()));
		if (!StringUtility.isNull(objPayableColumns.getPyepvid()))
			objTblPayable.setEpvId(Integer.parseInt(objPayableColumns.getPyepvid()));
		if (!StringUtility.isNull(objPayableColumns.getPypyidreference()))
			objTblPayable.setPyIdReference(Long.parseLong(objPayableColumns.getPypyidreference()));
		
		objTblPayable.setPyActualtotal(new BigDecimal(objPayableColumns.getPypyactualtotal()));
		objTblPayable.setPyTotal(new BigDecimal(objPayableColumns.getPypytotal()));
		objTblPayable.setPyUnitnumber(new BigDecimal(objPayableColumns.getPypyunitnumber()));
		objTblPayable.setPyUnitprice(new BigDecimal(objPayableColumns.getPypyunitprice()));
		
		if (!StringUtility.isNull(objPayableColumns.getPypyauditdate()))
			objTblPayable.setPyAuditdate(DateFormatUtility.getStandardDate(objPayableColumns.getPypyauditdate()));
		
		String strCommissionrate = objPayableColumns.getPypycommissionrate();
		if (!StringUtility.isNull(strCommissionrate))
			objTblPayable.setPyCommissionrate(new BigDecimal(strCommissionrate));
		
		objTblPayable.setPyCurrencyrate(new BigDecimal(objPayableColumns.getPypycurrencyrate()));
		objTblPayable.setPyModifydate(DateFormatUtility.getSysdate());
		objTblPayable.setPyOccurdate(DateFormatUtility.getStandardDate(objPayableColumns.getPypyoccurdate()));
		objTblPayable.setPyRemark(objPayableColumns.getPypyremark());
		
		if (!StringUtility.isNull(objPayableColumns.getChnchncode())) {
			TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objPayableColumns.getChnchncode());
			objTblPayable.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(objPayableColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objPayableColumns.getCococode());
			objTblPayable.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objPayableColumns.getBkbkcode())) {
			TdiBillingkind objTdiBillingkind = TdiBillingkindDC.loadByKey(objPayableColumns.getBkbkcode());
			objTblPayable.setTdiBillingkind(objTdiBillingkind);
		}		
		if (!StringUtility.isNull(objPayableColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objPayableColumns.getCkckcode());
			objTblPayable.setTdiCurrencykind(objTdiCurrencykind);
		}
		if (!StringUtility.isNull(objPayableColumns.getFkfkcode())) {
			TdiFeekind objTdiFeekind = TdiFeekindDC.loadByKey(objPayableColumns.getFkfkcode());
			objTblPayable.setTdiFeekind(objTdiFeekind);
		}
		if (!StringUtility.isNull(objPayableColumns.getFsfscode())) {
			TdiFeestatus objTdiFeestatus = TdiFeestatusDC.loadByKey(objPayableColumns.getFsfscode());
			objTblPayable.setTdiFeestatus(objTdiFeestatus);
		}		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTblPayable.setTdiOperatorByPyOpIdModifier(objTdiOperator);
		}
		if (!StringUtility.isNull(objPayableColumns.getCwcwcode())) {
			TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
					Long.parseLong(objPayableColumns.getCwcwcode()));
			objTblPayable.setTopCorewaybill(objTopCorewaybill);
		}
		// 服务商账单对应的应付费用
		if (!StringUtility.isNull(objPayableColumns.getSpyspyid())) {
			TfiServerpayable objTSPY = (TfiServerpayable)objSession.load(TfiServerpayable.class, 
					Long.parseLong(objPayableColumns.getSpyspyid()));
			objTblPayable.setTfiServerpayable(objTSPY);
		}
	}
	
	public static List transferImportPyToColumns(HousewaybillColumns objHWColumns,
			List listPyColumnsforimport,
			String strOperid) throws Exception {
		List<PayableColumns> listPyColumns = new ArrayList<PayableColumns>();
		if (listPyColumnsforimport == null || listPyColumnsforimport.size() < 1) 
			return listPyColumns;
		for (int i = 0; i < listPyColumnsforimport.size(); i++) {
			PayableColumns objPayableColumns = new PayableColumns();
			PayableColumnsForImport objPYFImport = (PayableColumnsForImport)listPyColumnsforimport.get(i);
			
			objPayableColumns.setBkbkcode("A0201");
			objPayableColumns.setCkckcode(objPYFImport.getCkcode());
			objPayableColumns.setCwcwcode(Long.parseLong(objHWColumns.getHwcwcode()));
			// 服务商
			ChannelCondition objChannelCondition = new ChannelCondition();
			objChannelCondition.setChnsename(objPYFImport.getChnsname());
			List listChannel = ChannelDemand.query(objChannelCondition);
			if (listChannel == null || listChannel.size() < 1)
				throw (new Exception(objPYFImport.getChnsname() + "的渠道名称不存在"));
			objPayableColumns.setChnchncode(((ChannelColumns)listChannel.get(0)).getChnchncode());
			objPayableColumns.setCococode(((ChannelColumns)listChannel.get(0)).getCococode());
			// 查找费用
			FeekindCondition objFeekindCondition = new FeekindCondition();
			objFeekindCondition.setFkname(objPYFImport.getFkname());
			List listFeekind = FeekindDemand.query(objFeekindCondition);
			if (listFeekind == null || listFeekind.size() < 1 || 
					((FeekindColumns)listFeekind.get(0)).getFkfkcode().length() < 5)
				throw (new Exception(objPYFImport.getFkname() + "的费用名称不存在"));
			
			objPayableColumns.setFkfkcode(((FeekindColumns)listFeekind.get(0)).getFkfkcode());
			objPayableColumns.setFsfscode("C");
			objPayableColumns.setPypyactualtotal(new BigDecimal(objPYFImport.getPytotal()));
			// 取币种
			Currency objCurrency = new Currency();
			String strCurrencyrate = objCurrency.getCurrencyrate(objHWColumns.getScococode(), 
					"A", 
					objHWColumns.getAbwadddate(),
					objHWColumns.getEeeecode(), 
					objPYFImport.getCkcode());
			if (StringUtility.isNull(strCurrencyrate) || 
					new BigDecimal(strCurrencyrate).compareTo(new BigDecimal("0")) <= 0)
				throw (new Exception(objPYFImport.getCkcode() + "的币种无法获得对应的汇率"));
			objPayableColumns.setPypycurrencyrate(new BigDecimal(strCurrencyrate));
			objPayableColumns.setPypyoccurdate(DateFormatUtility.getStandardDate(objHWColumns.getAbwadddate()));
			objPayableColumns.setPypyremark("批量导入应付" + objPYFImport.getPyremark());
			objPayableColumns.setPypytotal(new BigDecimal(objPYFImport.getPytotal()));
			objPayableColumns.setPypyactualtotal(new BigDecimal(objPYFImport.getPytotal()));
			objPayableColumns.setPypyunitnumber(new BigDecimal(objHWColumns.getCwcwchargeweight()));
			objPayableColumns.setPypyunitprice(new BigDecimal(objPYFImport.getPytotal()).divide(new BigDecimal(objHWColumns.getCwcwserverchargeweight()), 2, 4));
			objPayableColumns.setMopopid(Long.parseLong(strOperid));
			objPayableColumns.setCopopid(Long.parseLong(strOperid));
			objPayableColumns.setPypycommissionrate(new BigDecimal("0"));
			
			listPyColumns.add(objPayableColumns);
		}
		return listPyColumns;
	}	
	
	
	/**
	 * 将应收费用复制为历史费用
	 * @param objThiPayable
	 * @param strOperId
	 * @param objSession
	 * @return
	 */
	public static ThiPayable createHiPayable(TblPayable objTblPayable,
			String strOperId,
			Session objSession) {
		ThiPayable objThiPayable = new ThiPayable();
		
		objThiPayable.setEpCode(objThiPayable.getEpCode());
		objThiPayable.setEpvId(objThiPayable.getEpvId());
		objThiPayable.setPyActualtotal(objTblPayable.getPyActualtotal());
		objThiPayable.setPyAuditdate(objTblPayable.getPyAuditdate());
		objThiPayable.setPyCommissionrate(objTblPayable.getPyCommissionrate());
		objThiPayable.setPyCreatedate(objTblPayable.getPyCreatedate());
		objThiPayable.setPyCurrencyrate(objTblPayable.getPyCurrencyrate());
		objThiPayable.setPyId(objTblPayable.getPyId());
		objThiPayable.setPyIdReference(objTblPayable.getPyIdReference());
		objThiPayable.setPyOccurdate(objTblPayable.getPyOccurdate());
		if (objTblPayable.getTdiOperatorByPyOpIdAuditor() != null)
			objThiPayable.setPyOpIdAuditor(objTblPayable.getTdiOperatorByPyOpIdAuditor().getOpId());
		if (objTblPayable.getTdiOperatorByPyOpIdCreator() != null)
			objThiPayable.setPyOpIdCreator(objTblPayable.getTdiOperatorByPyOpIdCreator().getOpId());
		
		objThiPayable.setPyRemark(objTblPayable.getPyRemark());
		objThiPayable.setPyTotal(objTblPayable.getPyTotal());
		objThiPayable.setPyUnitnumber(objTblPayable.getPyUnitnumber());
		objThiPayable.setPyUnitprice(objTblPayable.getPyUnitprice());
		
		objThiPayable.setPyOpIdModifier(Long.parseLong(strOperId));
		objThiPayable.setPyModifydate(DateFormatUtility.getSysdate());		
		
		// 
		objThiPayable.setTchnChannel(objTblPayable.getTchnChannel());
		objThiPayable.setTcoCorporation(objTblPayable.getTcoCorporation());
		objThiPayable.setTdiBillingkind(objTblPayable.getTdiBillingkind());
		objThiPayable.setTdiCurrencykind(objTblPayable.getTdiCurrencykind());
		objThiPayable.setTdiFeekind(objTblPayable.getTdiFeekind());
		objThiPayable.setTdiFeestatus(objTblPayable.getTdiFeestatus());
		//objThiPayable.setTfiBillrecord(objTblPayable.getBrId());
		objThiPayable.setTopCorewaybill(objTblPayable.getTopCorewaybill());
		
		return objThiPayable;
	}
}
