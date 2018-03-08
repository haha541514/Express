package kyle.leis.eo.operation.batchwaybill.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.common.util.prompt.SavedResultUtility;
import kyle.leis.eo.operation.batchwaybill.bl.ABatchWayBill;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.CorebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.DeparturebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillCondition;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueColumns;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwbvalueCondition;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.dax.CoreBatchWayDemand;

public abstract class ABatchWayBillService extends AService {
	/**
	 * ≤È—Ø
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		BatchwaybillCondition objBWCondition = (BatchwaybillCondition)objPD.getParameter(0, 
				BatchwaybillCondition.class);
		List objList = BatchWayBillDemand.query(objBWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	//
	public String queryDepartureBW(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		DeparturebatchwaybillCondition objDBWCondition = (DeparturebatchwaybillCondition)objPD.getParameter(0, 
				DeparturebatchwaybillCondition.class);
		List objList = BatchWayBillDemand.queryDeparture(objDBWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String querySimpleBatchwaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		SimplebatchwaybillCondition objSBWCondition = (SimplebatchwaybillCondition)objPD.getParameter(0, 
				SimplebatchwaybillCondition.class);
		List objList = BatchWayBillDemand.querySimpleBatchwaybill(objSBWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	public String queryCoreBatch(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);

		CorebatchwaybillCondition objSBWCondition = (CorebatchwaybillCondition)objPD.getParameter(0, 
				CorebatchwaybillCondition.class);
		List objList = CoreBatchWayDemand.query(objSBWCondition);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strBwcode = (String)objPD.getParameter(0, String.class);
		BatchwaybillColumns objBWColumns = BatchWayBillDemand.load(strBwcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBWColumns);
		return objEncode.toString();
	}
	
	public String loadByBwLabelcode(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strBwLabelcode = (String)objPD.getParameter(0, String.class);
		BatchwaybillColumns objBWColumns = BatchWayBillDemand.loadByBwLabelcode(strBwLabelcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objBWColumns);
		return objEncode.toString();
	}
	
	public String loadBWVCorewaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		String strBwvid= (String)objPD.getParameter(0, String.class);
		SimplebatchwbvalueColumns objSBVC = BatchWayBillDemand.loadBWVCorewaybill(strBwvid);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objSBVC);
		return objEncode.toString();
	}
	
	public String checkPackageBillcounts(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 2, this);
		
		String strBwcode = (String)objPD.getParameter(0, String.class);
		String strAdtcode = (String)objPD.getParameter(1, String.class);
		
		String strResults = BatchWayBillDemand.checkPackageBillcounts(strBwcode, strAdtcode);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(strResults);
		return objEncode.toString();
	}	
	
	
	public String queryBWVCorewaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		
		SimplebatchwbvalueCondition objSBVC = (SimplebatchwbvalueCondition)objPD.getParameter(0, 
				SimplebatchwbvalueCondition.class);
		List listResults = BatchWayBillDemand.queryBWVCorewaybill(objSBVC);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(listResults);
		return objEncode.toString();
	}	
	
	
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		BatchwaybillColumns objBWColumns = (BatchwaybillColumns)objPD.getParameter(0, 
				BatchwaybillColumns.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		ABatchWayBill objBatchWayBill = getBatchWayBill();
		SavedResultUtility objSavedResultUtility = objBatchWayBill.save(objBWColumns, strOperId, isIgnoreNotice);
		
		return objSavedResultUtility.toString();
	}
	
	public String complete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strBwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		ABatchWayBill objBatchWayBill = getBatchWayBill();
		SavedResultUtility objSavedResultUtility = objBatchWayBill.complete(strBwcode, strOperId, isIgnoreNotice);
		
		return objSavedResultUtility.toString();
	}
	
	public String undocomplete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strBwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		ABatchWayBill objBatchWayBill = getBatchWayBill();
		SavedResultUtility objSavedResultUtility = objBatchWayBill.undocomplete(strBwcode, strOperId, isIgnoreNotice);
		
		return objSavedResultUtility.toString();
	}	
	
	public String eliminate(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 3, this);
		
		String strBwcode = (String)objPD.getParameter(0, String.class);
		String strOperId = (String)objPD.getParameter(1, String.class);
		boolean isIgnoreNotice = Boolean.parseBoolean((String)objPD.getParameter(2, String.class));
		
		ABatchWayBill objBatchWayBill = getBatchWayBill();
		SavedResultUtility objSavedResultUtility = objBatchWayBill.eliminate(strBwcode, strOperId, isIgnoreNotice);
		
		return objSavedResultUtility.toString();
	}
	
	protected abstract ABatchWayBill getBatchWayBill();
}
