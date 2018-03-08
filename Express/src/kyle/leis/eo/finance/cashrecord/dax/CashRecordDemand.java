package kyle.leis.eo.finance.cashrecord.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.common.util.msgpush.MsgPush;
import kyle.common.util.msgpush.MsgRequest;
import kyle.leis.eo.billing.calculate.currency.bl.Currency;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.da.CashrecordCondition;
import kyle.leis.eo.finance.cashrecord.da.CashrecordQuery;
import kyle.leis.eo.finance.cashrecord.da.CashrecordfordunCondition;
import kyle.leis.eo.finance.cashrecord.da.CashrecordfordunQuery;
import kyle.leis.eo.finance.dunning.da.FinancestatisticsColumns;
import kyle.leis.eo.finance.dunning.dax.DunningDemand;
import kyle.leis.es.company.supplier.dax.SupplierDemand;
import kyle.leis.es.systemproperty.dax.SystempropertyDemand;
import kyle.leis.fs.authoritys.user.da.UserColumns;
import kyle.leis.fs.authoritys.user.dax.UserQueryEX;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCashrecordkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCashrecordstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPaymenttypeDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCashrecordkind;
import kyle.leis.hi.TdiCashrecordstatus;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiPaymenttype;
import kyle.leis.hi.TfiCashrecord;
import net.sf.hibernate.Session;

public class CashRecordDemand {
	public static List query(CashrecordCondition objCrCondition) 
	throws Exception {
		CashrecordQuery objCashrecordQuery = new CashrecordQuery();
		if(!StringUtility.isNull(objCrCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objCrCondition.getEecode());
			objCrCondition.setEestructurecode(strEestructurecode);
			objCrCondition.setEecode(null);
		}	
		objCashrecordQuery.setCondition(objCrCondition);
		return objCashrecordQuery.getResults();
	}
	
	public static CashrecordColumns load(String strCrId) 
	throws Exception {
		CashrecordCondition objCrCondition = new CashrecordCondition();
		objCrCondition.setCrid(strCrId);
		List objList = query(objCrCondition);
		if (objList == null || objList.size() < 1) return null;
		return (CashrecordColumns)objList.get(0);
	}
	
	public static List loadByWoId(String strWoId) 
	throws Exception {
		CashrecordQuery objCashrecordQuery = new CashrecordQuery();
		objCashrecordQuery.setWoid(strWoId);
		return objCashrecordQuery.getResults();
	}	
	
	
	public static List queryForDun(String strCocode, 
			String strStartDate, 
			String strEndDate,
			String strCarryoversign,
			String strCkcode) throws Exception {
		CashrecordfordunCondition objCFDCondition = new CashrecordfordunCondition();
		objCFDCondition.setCo_code(strCocode);
		objCFDCondition.setCk_code(strCkcode);
		if (!StringUtility.isNull(strCarryoversign) && strCarryoversign.equals("Y"))
			objCFDCondition.setEndcarryoversigin("Y");
		if (!StringUtility.isNull(strCarryoversign) && strCarryoversign.equals("N"))
			objCFDCondition.setBegincarryoversign("N");
		boolean isNeedRelease = false;
		String strSystemPE = SystempropertyDemand.getEnterprise();
		if (!StringUtility.isNull(strSystemPE) && strSystemPE.startsWith("SLY"))
			isNeedRelease = true;
		CashrecordfordunQuery objCFDQuery = new CashrecordfordunQuery();
		if (!StringUtility.isNull(strCkcode)) {
			objCFDQuery = new CashrecordfordunQueryEX();
			List listResults = SupplierDemand.load(strCocode);
			if (listResults != null && listResults.size() > 0)
				objCFDQuery = new ServerCashrecordfordunQueryEX(isNeedRelease);
		}
		objCFDQuery.setCondition(objCFDCondition);
		return objCFDQuery.getResults();
	}
	
	/**
	 * 统计收款总额
	 * @param astrCrId
	 * @return
	 * @throws Exception
	 */
	public static SumCashTotalResult sumCashTotal(String[] astrCrId) throws Exception {
		BigDecimal objSumCrTotal = new BigDecimal("0");
		SumCashTotalResult objSCTResult = new SumCashTotalResult();
		// 收付款
		CashrecordColumns objCashrecordColumns = new CashrecordColumns();		
		if (astrCrId == null || astrCrId.length < 1) {
			objSCTResult.setSumCrTotal(objSumCrTotal);
			return objSCTResult;
		}
		for (int i = 0; i < astrCrId.length; i++) {
			objCashrecordColumns = load(astrCrId[i]);
			String strCurrencyRate = objCashrecordColumns.getCrcrcurrencyrate();
			String strCrTotal = objCashrecordColumns.getCrcrtotal();
			BigDecimal objCrTotal = new BigDecimal(strCrTotal).multiply(new BigDecimal(strCurrencyRate));
			objCrTotal = objCrTotal.divide(new BigDecimal("1"), 2, 4);
			if (objCashrecordColumns.getCrkcrkcode().equals(ICashRecordBasicData.CRK_PAYABLE_ACCOUNT))
				objCrTotal = objCrTotal.multiply(new BigDecimal("-1"));
			objSumCrTotal = objSumCrTotal.add(objCrTotal);
		}
		objSCTResult.setSumCrTotal(objSumCrTotal);
		objSCTResult.setCashrecordColumns(objCashrecordColumns);
		return objSCTResult;
	}
	
	
	public static String getCurrencyrate(CashrecordColumns objCashrecordColumns) 
	throws Exception {
		Currency objCurrency = new Currency();
		return objCurrency.getCurrencyrate(objCashrecordColumns.getCococode(), 
				"A", 
				objCashrecordColumns.getCrcroccurdate(), 
				objCashrecordColumns.getEeeecode(), 
				objCashrecordColumns.getCkckcode());
	}
	
	
	public static void setCashRecordByColumns(TfiCashrecord objTfiCashrecord,
			CashrecordColumns objCashrecordColumns,
			String strOperId,
			Session objSession) throws Exception {
		// 获得汇率
		// objTfiCashrecord.setCrCurrencyrate(new BigDecimal(getCurrencyrate(objCashrecordColumns)));
		objTfiCashrecord.setCrCurrencyrate(new BigDecimal(objCashrecordColumns.getCrcrcurrencyrate()));
		objTfiCashrecord.setCrOccurdate(DateFormatUtility.getStandardDate(objCashrecordColumns.getCrcroccurdate() + " 00:00:00"));
		objTfiCashrecord.setCrReceiptlabelcode(objCashrecordColumns.getCrcrreceiptlabelcode());
		objTfiCashrecord.setCrRemark(objCashrecordColumns.getCrcrremark());
		objTfiCashrecord.setCrTotal(new BigDecimal(objCashrecordColumns.getCrcrtotal()));
		
		String strCrLabelcode = objCashrecordColumns.getCrcrlabelcode();
		if (StringUtility.isNull(strCrLabelcode))
			strCrLabelcode = "TEMP";
		objTfiCashrecord.setCrLabelcode(strCrLabelcode);
		
		if (StringUtility.isNull(objCashrecordColumns.getCrcrreceiptprinttime()))
			objTfiCashrecord.setCrReceiptprinttime(0);
		else
			objTfiCashrecord.setCrReceiptprinttime(Integer.parseInt(objCashrecordColumns.getCrcrreceiptprinttime()));		
		
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(strOperId);
			objTfiCashrecord.setTdiOperatorByCrOpIdModifier(objTdiOperator);
			objTfiCashrecord.setCrModifydate(DateFormatUtility.getSysdate());
		}
		
		if (!StringUtility.isNull(objCashrecordColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objCashrecordColumns.getCococode());
			objTfiCashrecord.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objCashrecordColumns.getCrkcrkcode())) {
			TdiCashrecordkind objTdiCashrecordkind = TdiCashrecordkindDC.loadByKey(objCashrecordColumns.getCrkcrkcode());
			objTfiCashrecord.setTdiCashrecordkind(objTdiCashrecordkind);
		}
		if (!StringUtility.isNull(objCashrecordColumns.getCrscrscode())) {
			TdiCashrecordstatus objTdiCashrecordstatus = TdiCashrecordstatusDC.loadByKey(objCashrecordColumns.getCrscrscode());
			objTfiCashrecord.setTdiCashrecordstatus(objTdiCashrecordstatus);
		}	
		if (!StringUtility.isNull(objCashrecordColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objCashrecordColumns.getCkckcode());
			objTfiCashrecord.setTdiCurrencykind(objTdiCurrencykind);
		}
		if (!StringUtility.isNull(objCashrecordColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objCashrecordColumns.getCococode());
			TdiEnterpriseelement objTdiEnterpriseelement = objTcoCorporation.getTdiEnterpriseelement();
			objTfiCashrecord.setTdiEnterpriseelement(objTdiEnterpriseelement);
		}
		if (!StringUtility.isNull(objCashrecordColumns.getPtptcode())) {
			TdiPaymenttype objTdiPaymenttype = TdiPaymenttypeDC.loadByKey(objCashrecordColumns.getPtptcode());
			objTfiCashrecord.setTdiPaymenttype(objTdiPaymenttype);
		}
	}
	
	public static String buildLabelcode(TfiCashrecord objTfiCashrecord) {
		return String.valueOf(objTfiCashrecord.getCrId());
	}
	
	/**
	 * 发送移动端交款通知
	 * @param coCode
	 * @param cashier 交款额
	 */
	public static void sendMessage(String coCode, String cashier){
		MsgPush msgPush = new MsgPush();
		MsgRequest request = null;
		String msgTitle = "交款通知";
		String msgContent = "";
		UserQueryEX queryEX = new UserQueryEX(new String[]{"G", "GFI"});
		queryEX.setCocode(coCode);
		try {
			FinancestatisticsColumns objFSColumns = DunningDemand.load(coCode);
			BigDecimal objBalanceAmount = new BigDecimal(objFSColumns.getFsfsbalanceamount());
			if(objBalanceAmount.compareTo(new BigDecimal("0")) >= 0) {
				msgContent = "温馨提示：贵司于" + DateFormatUtility.getStandardSysdate().substring(0, 10) 
						+ "交款" + cashier + "，帐户余额为" + objBalanceAmount + "，请保证账户余额充足。";
			} else {
				msgContent = "温馨提示：贵司于" + DateFormatUtility.getStandardSysdate().substring(0, 10) 
						+ "交款" + cashier + "，欠款额为" + objBalanceAmount.abs() + "，请速补足运费。";
			}
			List<?> users = queryEX.getResults();
			for (Object object : users) {
				UserColumns userColumns = (UserColumns) object;
				request = new MsgRequest();
				request.setOpcode(userColumns.getOpopcode());
				request.setMsgTitle(msgTitle);
				request.setMsgContent(msgContent);
				msgPush.sendMsg(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
