package kyle.leis.eo.operation.batchwaybill.sv;

import kyle.leis.eo.operation.batchwaybill.bl.ABatchWayBill;
import kyle.leis.eo.operation.batchwaybill.bl.DepartureBatchWayBill;

public class DepartureBWService extends ABatchWayBillService {
	
	protected ABatchWayBill getBatchWayBill() {
		return new DepartureBatchWayBill();
	}

}
