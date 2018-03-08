package kyle.leis.es.workbill.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.es.workbill.bl.WorkBill;
import kyle.leis.es.workbill.da.WorkbillColumns;
import kyle.leis.es.workbill.da.WorkbillCondition;
import kyle.leis.es.workbill.da.WorkbillactionColumns;
import kyle.leis.es.workbill.dax.WorkbillDemand;

public class WorkBillService extends AService{
	/**
	 * 获得工作单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String allWorkBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		WorkbillCondition objWorkbillCondition=(WorkbillCondition) objPD.getParameter(0, WorkbillCondition.class);
		
		WorkbillDemand objWorkBillDemand=new WorkbillDemand();
		List objList=objWorkBillDemand.allWorkBill(objWorkbillCondition);
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	/**
	 * 获得一个工作单的操作记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String getWorkBillAction(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String wbId=(String) objPD.getParameter(0, String.class);
		
		WorkbillDemand objWorkBillDemand=new WorkbillDemand();
		List objList=objWorkBillDemand.getWorkbillaction(wbId);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objList);
		return objEncode.toString();
	}
	/**
	 * 新增工作单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String saveWorkBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		WorkbillColumns objWorkbill=(WorkbillColumns) objPD.getParameter(0, WorkbillColumns.class);
		
		WorkBill objWorkBill=new WorkBill();
		objWorkBill.savaWorKBill(objWorkbill);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWorkbill);
		return objEncode.toString();
	}
	
	/**
	 * 修改工作单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String modifyWorkBill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		WorkbillColumns objWorkbill=(WorkbillColumns) objPD.getParameter(0, WorkbillColumns.class);
		
		WorkBill objWorkBill=new WorkBill();
		objWorkBill.modifyWorkBill(objWorkbill);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWorkbill);
		return objEncode.toString();
	}
	/**
	 * 保存工作单的操作记录
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String saveWorkBillaction(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		WorkbillactionColumns objWorkRecord=(WorkbillactionColumns) objPD.getParameter(0, WorkbillactionColumns.class);
		//System.out.println(objWorkRecord.getWbawbacontent());
		WorkBill objWorkBill=new WorkBill();
		objWorkBill.saveWorkBillRecord(objWorkRecord);
		
		Encoder objEncode = new Encoder();
		objEncode.addParameter(objWorkRecord);
		return objEncode.toString();
	}
}
