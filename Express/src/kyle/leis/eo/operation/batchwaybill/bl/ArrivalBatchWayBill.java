package kyle.leis.eo.operation.batchwaybill.bl;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.batchwaybill.blx.BatchWayBillCheck;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;

public class ArrivalBatchWayBill extends ABatchWayBill {
	
	protected PromptUtilityCollection checkBeforeSave(BatchwaybillColumns objBWColumns) {
		BatchWayBillCheck objBatchWayBillCheck = new BatchWayBillCheck();
		return objBatchWayBillCheck.checkArrivalSaving(objBWColumns);
	}
	
	protected void rebuildSavedColumns(BatchwaybillColumns objBWColumns) {
		objBWColumns.setAdtadtcode("A");
	}

}
