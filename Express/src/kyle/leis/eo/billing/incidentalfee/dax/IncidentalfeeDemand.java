package kyle.leis.eo.billing.incidentalfee.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeCondition;
import kyle.leis.eo.billing.incidentalfee.da.IncidentalfeeQuery;
import kyle.leis.eo.billing.incidentalfee.da.SumincidentalfeeColumns;
import kyle.leis.eo.billing.incidentalfee.da.SumincidentalfeeQuery;
import kyle.leis.eo.billing.incidentalfee.da.SumincidentalfeeQueryEX;
import kyle.leis.fs.dictionary.dictionarys.da.TchnChannelDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiBillingkindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCurrencykindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblIncidentalfee;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TfiBillrecord;
import net.sf.hibernate.Session;

public class IncidentalfeeDemand {
	
	public static List query(IncidentalfeeCondition objIncidentalfeeCon) throws Exception
	{
		IncidentalfeeQuery objIncidentalfeeQue = new IncidentalfeeQuery();
		objIncidentalfeeQue.setCondition(objIncidentalfeeCon);
		return objIncidentalfeeQue.getResults();
	}

	public static IncidentalfeeColumns queryByIfId(String strIfId) throws Exception
	{
		IncidentalfeeCondition objIncidentalfeeCon = new IncidentalfeeCondition();
		objIncidentalfeeCon.setIfid(strIfId);
		List objList = query(objIncidentalfeeCon);
		if(!CollectionUtility.isNull(objList) && objList.size() == 1)
			return (IncidentalfeeColumns)objList.get(0);
		return null;
	}
	
	public static String sumHasAccountingAmount(String strCocode,
			String strBrid) throws Exception {
		SumincidentalfeeQuery objSIFQ = new SumincidentalfeeQuery();
		objSIFQ.setBrid(strBrid);
		objSIFQ.setCocode(strCocode);
		List listResults = objSIFQ.getResults();
		
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumincidentalfeeColumns)listResults.get(0)).getIncidentalfeetotal();
	}
	
	public static String sumHasAccountingOriginAmount(String strCocode,
			String strBrid) throws Exception {
		SumincidentalfeeQueryEX objSIFQ = new SumincidentalfeeQueryEX();
		objSIFQ.setBrid(strBrid);
		objSIFQ.setCocode(strCocode);
		List listResults = objSIFQ.getResults();
		
		if (listResults == null || listResults.size() < 1)
			return "0";
		return ((SumincidentalfeeColumns)listResults.get(0)).getIncidentalfeetotal();
	}	
	
	public static BigDecimal sumActualTotal(List listIncidentalfee)
	{
		BigDecimal objSumActualTotal = new BigDecimal("0");
		if(CollectionUtility.isNull(listIncidentalfee)) return objSumActualTotal;
		
		for(IncidentalfeeColumns objIncidentalfeeCol:(List<IncidentalfeeColumns>)listIncidentalfee)
		{
			BigDecimal objActualTotal = new BigDecimal(objIncidentalfeeCol.getIfifactualtotal());
			BigDecimal objCurrencyRate = new BigDecimal(objIncidentalfeeCol.getIfifcurrencyrate());
			
			BigDecimal obj = new BigDecimal("1");
			if (objIncidentalfeeCol.getBkbkcode().startsWith("A02")) {
				obj = new BigDecimal("-1");
			}
			objActualTotal = objActualTotal.multiply(objCurrencyRate).divide(obj, 2, 4);
			objSumActualTotal = objSumActualTotal.add(objActualTotal);
		}
		return objSumActualTotal;
	}
	
	public static void setIncidentalfeeByColumns(TblIncidentalfee objTblIncidentfalfee,IncidentalfeeColumns objIncidentfalfeeCol,String strOperId,Session objSession) throws Exception
	{
		objTblIncidentfalfee.setIfActualtotal(new BigDecimal(objIncidentfalfeeCol.getIfifactualtotal()));
		objTblIncidentfalfee.setIfCurrencyrate(new BigDecimal(objIncidentfalfeeCol.getIfifcurrencyrate()));
		objTblIncidentfalfee.setIfModifydate(DateFormatUtility.getStandardDate(objIncidentfalfeeCol.getIfifmodifydate()));
		objTblIncidentfalfee.setIfOccurdate(DateFormatUtility.getStandardDate(objIncidentfalfeeCol.getIfifoccurdate()));
		objTblIncidentfalfee.setIfRemark(objIncidentfalfeeCol.getIfifremark());
		objTblIncidentfalfee.setIfTotal(new BigDecimal(objIncidentfalfeeCol.getIfiftotal()));
		objTblIncidentfalfee.setTcoCorporation((TcoCorporation)objSession.load(TcoCorporation.class, objIncidentfalfeeCol.getCococode()));
//		objTblIncidentfalfee.setTcoCorporation(TcoCorporationDC.loadByKey(objIncidentfalfeeCol.getCococode()));
		objTblIncidentfalfee.setTdiBillingkind(TdiBillingkindDC.loadByKey(objIncidentfalfeeCol.getBkbkcode()));
		objTblIncidentfalfee.setTdiCurrencykind(TdiCurrencykindDC.loadByKey(objIncidentfalfeeCol.getCkckcode()));
		objTblIncidentfalfee.setTdiFeekind(TdiFeekindDC.loadByKey(objIncidentfalfeeCol.getFkfkcode()));
		objTblIncidentfalfee.setTdiFeestatus(TdiFeestatusDC.loadByKey(objIncidentfalfeeCol.getFsfscode()));
		objTblIncidentfalfee.setTdiOperatorByOpIdModify(TdiOperatorDC.loadByKey(strOperId));
//		
		if(!StringUtility.isNull(objIncidentfalfeeCol.getIfifidreference()))
			objTblIncidentfalfee.setIfIdReference(Long.valueOf(objIncidentfalfeeCol.getIfifidreference()));
		if(objIncidentfalfeeCol.getBkbkcode().startsWith("A02"))
			objTblIncidentfalfee.setTchnChannel(TchnChannelDC.loadByKey(objIncidentfalfeeCol.getChnchncode()));
		if(!StringUtility.isNull(objIncidentfalfeeCol.getBrbrid()))
			objTblIncidentfalfee.setTfiBillrecord((TfiBillrecord)objSession.load(TfiBillrecord.class, Long.valueOf(objIncidentfalfeeCol.getBrbrid())));
			//objTblIncidentfalfee.setTfiBillrecord(TfiBillrecordDC.loadByKey(objIncidentfalfeeCol.getBrbrid()));
		if(!StringUtility.isNull(objIncidentfalfeeCol.getIfifconfirmdate()))
			objTblIncidentfalfee.setIfConfirmdate(DateFormatUtility.getStandardDate(objIncidentfalfeeCol.getIfifconfirmdate()));
		if(!StringUtility.isNull(objIncidentfalfeeCol.getConfopopid()))
			objTblIncidentfalfee.setTdiOperatorByOpIdConfirm(TdiOperatorDC.loadByKey(objIncidentfalfeeCol.getConfopopid()));
		
		
	}
}
