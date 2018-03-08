package kyle.leis.eo.operation.batchwaybill.dax;

import java.util.List;

import kyle.leis.eo.operation.batchwaybill.da.CorebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.CorebatchwaybillQuery;


public class CoreBatchWayDemand {
	public static List query(CorebatchwaybillCondition objBWCondition) throws Exception {
		CorebatchwaybillQuery objBWQuery = new CorebatchwaybillQuery();	
		objBWQuery.setCondition(objBWCondition);
		return objBWQuery.getResults();
	}
	public static List queryAll() throws Exception {
		CorebatchwaybillQuery objBWQuery = new CorebatchwaybillQuery();	
		return objBWQuery.getResults();
	}
}
