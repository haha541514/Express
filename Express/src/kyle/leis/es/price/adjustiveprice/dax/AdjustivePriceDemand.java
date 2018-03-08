package kyle.leis.es.price.adjustiveprice.dax;

import java.util.List;

import kyle.common.util.jlang.CollectionUtility;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceCondition;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepriceQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiEnterpriseelementDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiExpresspricekindDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiPricestatusDC;
import kyle.leis.hi.TepExpressprice;

public class AdjustivePriceDemand {

	
	public static List query(AdjustivepriceCondition objAdjustivepriceCon) throws Exception
	{
		AdjustivepriceQuery objAdjustivepriceQue = new AdjustivepriceQuery();
		objAdjustivepriceQue.setCondition(objAdjustivepriceCon);
		return objAdjustivepriceQue.getResults();
	}
	
	
	public static AdjustivepriceColumns queryByEpCode(String strEpCode) throws Exception
	{
		AdjustivepriceCondition objAdjustivepriceCon = new AdjustivepriceCondition();
		objAdjustivepriceCon.setEpcode(strEpCode);
		List objList = query(objAdjustivepriceCon);
		if(!CollectionUtility.isNull(objList) && objList.size()==1)
			return (AdjustivepriceColumns) objList.get(0);
		return null;
	}
	
	/*
	 * 根据价格编号查询价格值集
	 */
	public static LoadAdjustivePriceResult loadByEpCode(String strEpCode) throws Exception
	{
		LoadAdjustivePriceResult objLoadAdjustivePriceResult = new LoadAdjustivePriceResult();
		AdjustivepriceColumns objAdjustivepriceCol = queryByEpCode(strEpCode);
		List listAdjustivepricevalue = AdjustivePriceValueDemand.queryByEpCode(strEpCode);
		objLoadAdjustivePriceResult.setListAdjustivepricevalue(listAdjustivepricevalue);
		objLoadAdjustivePriceResult.setObjAdjustivepriceCol(objAdjustivepriceCol);
		return objLoadAdjustivePriceResult;
	}
	
	
	public static void setExpresspriceByColumns(TepExpressprice objTepExpressprice,AdjustivepriceColumns objAdjustivepriceCol, String strOperId) throws Exception
	{
		objTepExpressprice.setEpEnddate(DateFormatUtility.getStandardDate(objAdjustivepriceCol.getEpependdate()+ " 23:59:59"));
		objTepExpressprice.setEpModifydate(DateFormatUtility.getSysdate());
		objTepExpressprice.setEpStartdate(DateFormatUtility.getStandardDate(objAdjustivepriceCol.getEpepstartdate() + " 00:00:00"));
		objTepExpressprice.setEpWithdrawsign("N");
		objTepExpressprice.setTdiEnterpriseelement(TdiEnterpriseelementDC.loadByKey(objAdjustivepriceCol.getEeeecode()));
		objTepExpressprice.setTdiExpresspricekind(TdiExpresspricekindDC.loadByKey(objAdjustivepriceCol.getEpkepkcode()));
		objTepExpressprice.setTdiOperatorByEpOpIdModify(TdiOperatorDC.loadByKey(strOperId));
		objTepExpressprice.setTdiPricestatus(TdiPricestatusDC.loadByKey(objAdjustivepriceCol.getPspscode()));
		if(!StringUtility.isNull(objAdjustivepriceCol.getEpepremark()))
			objTepExpressprice.setEpRemark(objAdjustivepriceCol.getEpepremark());
		
	}
	
}
