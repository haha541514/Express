package kyle.leis.eo.operation.cwbimportlog.bl;

import java.util.Arrays;
import java.util.List;

import kyle.leis.eo.operation.cwbimportlog.da.CwbimportlogColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowQuery;
import kyle.leis.eo.operation.cwbimportlog.dax.CwbimportlogDemand;
import kyle.leis.eo.operation.cwbimportlog.tp.AddcwbimportlogTrans;
import kyle.leis.eo.operation.cwbimportlog.tp.AddcwbimportrowTrans;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderCell;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderRow;

public class Cwbimportrow {
	
	public void saveRow(CwbimportrowColumns cwbrColumns)throws Exception {
		AddcwbimportrowTrans addcwbimportrowTrans = new AddcwbimportrowTrans();
		addcwbimportrowTrans.setParam(cwbrColumns);
		addcwbimportrowTrans.execute();
	}
	
	/**
	 * 根据logId得到所有错误行数据
	 * @throws Exception 
	 */
	public List<CwbimportrowColumns> getCwbrColumnsbyCwlid(String cwlId) throws Exception{
		CwbimportrowQuery cwbrQuery = new CwbimportrowQuery();
		cwbrQuery.setCwlid(cwlId);
		return cwbrQuery.getResults();
	}
	
	/**
	 * 在PredictOrderRow中cellList的前面加一列
	 */
     public void setCellToFirst(PredictOrderRow pRow,List<PredictOrderCell> listPredictOrdercell,
    		      String cellname,String cellvalue){
			PredictOrderCell pCell = new PredictOrderCell();
			pCell.setCellname(cellname);
			pCell.setCellvalue(cellvalue);
			listPredictOrdercell.add(pCell);
			listPredictOrdercell.addAll(pRow.getListPredictOrdercell());
			pRow.getListPredictOrdercell().clear();
			pRow.getListPredictOrdercell().addAll(listPredictOrdercell);
     }
     
     /**
      * 在数组前加一列
      * @param cellTitile
      * @param titles
      * @return
      */
     public String[] addACellTitleInFirst(String cellTitile,String[] titles){
    	 String[] a = new String[titles.length+1];
 		 System.arraycopy(titles, 0, a, 1, titles.length);
 		 a[0]=cellTitile;
 		 return a;
     }
}
