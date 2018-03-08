package kyle.leis.eo.finance.billrecord.dax;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.billrecord.da.AllbillrecordidColumns;
import kyle.leis.eo.finance.billrecord.da.AllbillrecordidCondition;
import kyle.leis.eo.finance.billrecord.da.AllbillrecordidQuery;
import kyle.leis.eo.finance.billrecord.da.BillrecordColumns;
import kyle.leis.eo.finance.billrecord.da.BillrecordCondition;
import kyle.leis.eo.finance.billrecord.da.BillrecordQuery;
import kyle.leis.eo.finance.billrecord.da.BillrecordfordunCondition;
import kyle.leis.eo.finance.billrecord.da.BillrecordfordunQuery;
import kyle.leis.eo.finance.cashrecord.da.CashrecordColumns;
import kyle.leis.eo.finance.cashrecord.dax.ICashRecordBasicData;
import kyle.leis.eo.finance.serverbillrecord.da.ServerbillrecordColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillingkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillrecordstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.enterpriseelement.dax.EnterpriseelementDemand;
import kyle.leis.hi.TchnChannel;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiBillingkind;
import kyle.leis.hi.TdiBillrecordstatus;
import kyle.leis.hi.TdiCurrencykind;
import kyle.leis.hi.TdiEnterpriseelement;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiBillrecord;
import kyle.leis.hi.TfiServerbillrecord;

public class BillRecordDemand {
	public static List query(BillrecordCondition objBillrecordCondition) 
	throws Exception {
		BillrecordQuery objBillrecordQuery = new BillrecordQuery();
		if(!StringUtility.isNull(objBillrecordCondition.getEecode())){
			String strEestructurecode = EnterpriseelementDemand.getEestructurecode(objBillrecordCondition.getEecode());
			objBillrecordCondition.setEestructurecode(strEestructurecode);
			objBillrecordCondition.setEecode(null);
		}	
		objBillrecordQuery.setCondition(objBillrecordCondition);
		return objBillrecordQuery.getResults();
	}
	
	public static BillrecordColumns load(String strBrid) throws Exception {
		BillrecordCondition objBillrecordCondition = new BillrecordCondition();
		objBillrecordCondition.setBrid(strBrid);
		List objList = query(objBillrecordCondition);
		if (objList == null || objList.size() < 1) return null;
		return (BillrecordColumns)objList.get(0);
	}
	
	public static List loadByWoId(String strWoId) 
	throws Exception {
		BillrecordQuery objBillrecordQuery = new BillrecordQuery();
		objBillrecordQuery.setWoid(strWoId);
		return objBillrecordQuery.getResults();
	}
	
	public static List queryForDun(String strCocode, 
			String strStartDate, 
			String strEndDate,
			String strCarryoversign,
			String strCkcode) throws Exception {
		BillrecordfordunCondition objBFDCondition = new BillrecordfordunCondition();
		objBFDCondition.setCo_code(strCocode);
		objBFDCondition.setCk_code(strCkcode);
		if (!StringUtility.isNull(strCarryoversign) && strCarryoversign.equals("Y"))
			objBFDCondition.setEndcarryoversigin("Y");
		if (!StringUtility.isNull(strCarryoversign) && strCarryoversign.equals("N"))
			objBFDCondition.setBegincarryoversign("N");
	
		BillrecordfordunQuery objBFDQuery = new BillrecordfordunQuery();
		objBFDQuery.setCondition(objBFDCondition);
		return objBFDQuery.getResults();
	}	
	
	public static SumBillTotalResult sumBillRecordTotal(String[] astrBrId) throws Exception {
		BigDecimal objSumBrTotal = new BigDecimal("0");
		SumBillTotalResult objSBTResult = new SumBillTotalResult();
		// 收付款
		BillrecordColumns objBillrecordColumns = new BillrecordColumns();		
		if (astrBrId == null || astrBrId.length < 1) {
			objSBTResult.setSumBrTotal(objSumBrTotal);
			return objSBTResult;
		}
		for (int i = 0; i < astrBrId.length; i++) {
			objBillrecordColumns = load(astrBrId[i]);
			BigDecimal objBrTotal = new BigDecimal(objBillrecordColumns.getBrbrtotal());
			if (objBillrecordColumns.getBkbkcode().equals(IBillRecordBasicData.BK_SERVERCHARGEWEIGHT_PY))
				objBrTotal = objBrTotal.multiply(new BigDecimal("-1"));
			objSumBrTotal = objSumBrTotal.add(objBrTotal);
		}
		objSBTResult.setSumBrTotal(objSumBrTotal);
		objSBTResult.setBillrecordColumns(objBillrecordColumns);
		return objSBTResult;
	}
	
	
	public static BillrecordColumns buildByCashColumns(CashrecordColumns objCashrecordColumns) {
		BillrecordColumns objBillrecordColumns = new BillrecordColumns();
		if (objCashrecordColumns.getCrkcrkcode().equals(ICashRecordBasicData.CRK_RECEIVABLE_ACCOUNT))
			objBillrecordColumns.setBkbkcode(IBillRecordBasicData.BK_CHARGEWEIGHT_RV);
		else
			objBillrecordColumns.setBkbkcode(IBillRecordBasicData.BK_SERVERCHARGEWEIGHT_PY);
		
		objBillrecordColumns.setBrbroccurdate(DateFormatUtility.getStandardDate(objCashrecordColumns.getCrcroccurdate()));
		objBillrecordColumns.setBrbrdownloadtimes(0);
		objBillrecordColumns.setBrbrprinttimes(0);
		objBillrecordColumns.setBrbrremark("直客收款时直接生成账单");
		objBillrecordColumns.setBrsbrscode(IBillRecordBasicData.BRS_CONFIRM);
		objBillrecordColumns.setCkckcode("RMB");
		objBillrecordColumns.setCococode(objCashrecordColumns.getCococode());
		objBillrecordColumns.setEeeecode(objCashrecordColumns.getEeeecode());
		
		return objBillrecordColumns;
	}
	
	public static BillrecordColumns buildByServerBillrecord(ServerbillrecordColumns objSBRColumns) {
		BillrecordColumns objBillrecordColumns = new BillrecordColumns();
		
		objBillrecordColumns.setBkbkcode(IBillRecordBasicData.BK_SERVERCHARGEWEIGHT_PY);
		objBillrecordColumns.setBrbroccurdate(DateFormatUtility.getStandardDate(objSBRColumns.getSbrsbroccurdate()));
		objBillrecordColumns.setBrbrdownloadtimes(0);
		objBillrecordColumns.setBrbrprinttimes(0);
		objBillrecordColumns.setBrbrremark("根据服务商账单生成系统应付账单，对应的服务商账单为" + objSBRColumns.getSbrsbrlabelcode());
		objBillrecordColumns.setBrsbrscode(IBillRecordBasicData.BRS_CONFIRM);
		objBillrecordColumns.setCkckcode(objSBRColumns.getCkckcode());
		objBillrecordColumns.setCococode(objSBRColumns.getCococode());
		objBillrecordColumns.setEeeecode(objSBRColumns.getEeeecode());
		objBillrecordColumns.setSbrsbrid(Long.parseLong(objSBRColumns.getSbrsbrid()));
		
		return objBillrecordColumns;
	}
	
	
	public static void setBillRecordByColumns(TfiBillrecord objTfiBillrecord,
			BillrecordColumns objBillrecordColumns,
			String strOperId, 
			Session objSession) throws Exception {
		// 下载次数
		if (StringUtility.isNull(objBillrecordColumns.getBrbrdownloadtimes()))
			objTfiBillrecord.setBrDownloadtimes(0);
		else
			objTfiBillrecord.setBrDownloadtimes(Integer.parseInt(objBillrecordColumns.getBrbrdownloadtimes()));
		// 打印次数
		if (StringUtility.isNull(objBillrecordColumns.getBrbrprinttimes()))
			objTfiBillrecord.setBrPrinttimes(0);
		else
			objTfiBillrecord.setBrPrinttimes(Integer.parseInt(objBillrecordColumns.getBrbrprinttimes()));
		// 账单日期
		if (!StringUtility.isNull(objBillrecordColumns.getBrbroccurdate()))
			objTfiBillrecord.setBrOccurdate(DateFormatUtility.getStandardDate(objBillrecordColumns.getBrbroccurdate()));		
		
		objTfiBillrecord.setBrModifydate(DateFormatUtility.getSysdate());
		objTfiBillrecord.setBrRemark(objBillrecordColumns.getBrbrremark());
		
		if (!StringUtility.isNull(objBillrecordColumns.getChnchncode())) {
			TchnChannel objTchnChannel = (TchnChannel)objSession.load(TchnChannel.class, 
					objBillrecordColumns.getChnchncode());
			objTfiBillrecord.setTchnChannel(objTchnChannel);
		}
		if (!StringUtility.isNull(objBillrecordColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objBillrecordColumns.getCococode());
			objTfiBillrecord.setTcoCorporation(objTcoCorporation);
		}
		if (!StringUtility.isNull(objBillrecordColumns.getBkbkcode())) {
			TdiBillingkind objTdiBillingkind = TdiBillingkindDC.loadByKey(objBillrecordColumns.getBkbkcode());
			objTfiBillrecord.setTdiBillingkind(objTdiBillingkind);
		}		
		if (!StringUtility.isNull(objBillrecordColumns.getBrsbrscode())) {
			TdiBillrecordstatus objTdiBillrecordstatus = TdiBillrecordstatusDC.loadByKey(objBillrecordColumns.getBrsbrscode());
			objTfiBillrecord.setTdiBillrecordstatus(objTdiBillrecordstatus);
		}		
		if (!StringUtility.isNull(objBillrecordColumns.getCkckcode())) {
			TdiCurrencykind objTdiCurrencykind = TdiCurrencykindDC.loadByKey(objBillrecordColumns.getCkckcode());
			objTfiBillrecord.setTdiCurrencykind(objTdiCurrencykind);
		}		
		if (!StringUtility.isNull(objBillrecordColumns.getCococode())) {
			TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
					objBillrecordColumns.getCococode());
			TdiEnterpriseelement objTdiEnterpriseelement = objTcoCorporation.getTdiEnterpriseelement();
			objTfiBillrecord.setTdiEnterpriseelement(objTdiEnterpriseelement);
		}
		if (!StringUtility.isNull(strOperId)) {
			TdiOperator objMOP = TdiOperatorDC.loadByKey(strOperId);
			objTfiBillrecord.setTdiOperatorByBrOpIdModifier(objMOP);
		}
		if (!StringUtility.isNull(objBillrecordColumns.getSbrsbrid())) {
			TfiServerbillrecord objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
					Long.parseLong(objBillrecordColumns.getSbrsbrid()));
			objTfiBillrecord.setTfiServerbillrecord(objTSBR);
		}
		
	}
	
	public static String buildBillLabelcode(BillrecordColumns objBillrecordColumns) {
		return "TEMP001";
	}
	public AllbillrecordidColumns queryAllBillrecordID(String cwCode) throws Exception{
		AllbillrecordidCondition objAllbillrecordidCondition=new AllbillrecordidCondition();
		objAllbillrecordidCondition.setCwcwcode(cwCode);
		AllbillrecordidQuery objAllbillrecordidQuery=new AllbillrecordidQuery();
		objAllbillrecordidQuery.setCondition(objAllbillrecordidCondition);
		List list=objAllbillrecordidQuery.getResults();
		if(list.size()==0){
			return null;
		}
	    return	(AllbillrecordidColumns) list.get(0);
		
	}
}
