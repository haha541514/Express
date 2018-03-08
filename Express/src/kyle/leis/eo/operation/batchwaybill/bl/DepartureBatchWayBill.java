package kyle.leis.eo.operation.batchwaybill.bl;

import kyle.common.util.prompt.PromptUtilityCollection;
import kyle.leis.eo.operation.batchwaybill.blx.BatchWayBillCheck;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;

public class DepartureBatchWayBill extends ABatchWayBill {
	
	protected PromptUtilityCollection checkBeforeSave(BatchwaybillColumns objBWColumns) {
		BatchWayBillCheck objBatchWayBillCheck = new BatchWayBillCheck();
		return objBatchWayBillCheck.checkDepartureSaving(objBWColumns);
	}
	
	protected void rebuildSavedColumns(BatchwaybillColumns objBWColumns) {
		objBWColumns.setAdtadtcode("D");
	}

}
