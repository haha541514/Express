package kyle.leis.es.price.adjustiveprice.dax;

import java.math.BigDecimal;
import java.util.List;

import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueColumns;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueCondition;
import kyle.leis.es.price.adjustiveprice.da.AdjustivepricevalueQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCargotypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiDistrictDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiUnittypeDC;
import kyle.leis.hi.TepAdjustivepricevalue;
import kyle.leis.hi.TepAdjustivepricevaluePK;

public class AdjustivePriceValueDemand {

	/*
	 * 查询
	 */
	public static List query(AdjustivepricevalueCondition objAdjustivepricevalueCon) throws Exception
	{
		AdjustivepricevalueQuery objAdjustivepricevalueQue = new AdjustivepricevalueQuery();
		objAdjustivepricevalueQue.setCondition(objAdjustivepricevalueCon);
		return objAdjustivepricevalueQue.getResults();
	}
	
	/*
	 * 根据价格编号查询价格值
	 */
	public static List queryByEpCode(String strEpCode) throws Exception
	{
		AdjustivepricevalueCondition objAdjustivepricevalueCon = new AdjustivepricevalueCondition();
		objAdjustivepricevalueCon.setEpcode(strEpCode);
		return query(objAdjustivepricevalueCon);
	}
	
	public static void setAdjustivepricevalueByColumns(TepAdjustivepricevalue objTepAdjustivepricevalue,AdjustivepricevalueColumns objAdjustivepricevalueCol) throws Exception
	{
		objTepAdjustivepricevalue.setApvPricevalue(new BigDecimal(objAdjustivepricevalueCol.getApvapvpricevalue()));
		objTepAdjustivepricevalue.setApvWeekday(objAdjustivepricevalueCol.getApvapvweekday());
		
		TepAdjustivepricevaluePK comp_id = new TepAdjustivepricevaluePK();
		comp_id.setApvId(Integer.valueOf(objAdjustivepricevalueCol.getApvcomp_idapvid()));
		comp_id.setEpCode(Long.valueOf(objAdjustivepricevalueCol.getApvcomp_idepcode()));
		
		objTepAdjustivepricevalue.setComp_id(comp_id);
		objTepAdjustivepricevalue.setTdiCargotype(TdiCargotypeDC.loadByKey(objAdjustivepricevalueCol.getCtctcode()));
		objTepAdjustivepricevalue.setTdiDistrict(TdiDistrictDC.loadByKey(objAdjustivepricevalueCol.getDtdtcode()));
		objTepAdjustivepricevalue.setTdiUnittype(TdiUnittypeDC.loadByKey(objAdjustivepricevalueCol.getUtutcode()));
	}
	
}
