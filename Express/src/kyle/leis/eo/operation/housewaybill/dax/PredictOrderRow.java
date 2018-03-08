package kyle.leis.eo.operation.housewaybill.dax;

import java.util.ArrayList;
import java.util.List;

public class PredictOrderRow {
	private List<PredictOrderCell> listPredictOrdercell = new ArrayList<PredictOrderCell>();
	private int iExcelRowIndex;
	
	public List<PredictOrderCell> getListPredictOrdercell() {
		return listPredictOrdercell;
	}

	public void put(String strColumnName, String strColumnValue) {
		PredictOrderCell objPOC = new PredictOrderCell();
		objPOC.setCellname(strColumnName);
		objPOC.setCellvalue(strColumnValue);
		listPredictOrdercell.add(objPOC);
	}
	
	public int getExcelRowIndex() {
		return iExcelRowIndex;
	}

	public void setExcelRowIndex(int iExcelRowIndex) {
		this.iExcelRowIndex = iExcelRowIndex;
	}	
}
