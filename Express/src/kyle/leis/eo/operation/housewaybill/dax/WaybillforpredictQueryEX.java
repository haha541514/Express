package kyle.leis.eo.operation.housewaybill.dax;

import kyle.common.dbaccess.query.IColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictQuery;

public class WaybillforpredictQueryEX extends WaybillforpredictQuery {
	
	public IColumns createColumns() {
		// TODO Auto-generated method stub
		return new WaybillforpredictColumnsEX();
	}
}
