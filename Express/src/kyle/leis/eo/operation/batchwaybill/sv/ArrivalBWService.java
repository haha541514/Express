package kyle.leis.eo.operation.batchwaybill.sv;

import kyle.leis.eo.operation.batchwaybill.bl.ABatchWayBill;
import kyle.leis.eo.operation.batchwaybill.bl.ArrivalBatchWayBill;

public class ArrivalBWService extends ABatchWayBillService {
	
	protected ABatchWayBill getBatchWayBill() {
		return new ArrivalBatchWayBill();
	}

}
