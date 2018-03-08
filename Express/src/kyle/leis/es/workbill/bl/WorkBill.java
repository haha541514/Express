package kyle.leis.es.workbill.bl;

import kyle.leis.es.workbill.da.WorkbillColumns;
import kyle.leis.es.workbill.da.WorkbillactionColumns;
import kyle.leis.es.workbill.tp.ModifyWorkbillTransaction;
import kyle.leis.es.workbill.tp.SaveWorkBillActionTrans;
import kyle.leis.es.workbill.tp.SaveWorkBillTransaction;

public class WorkBill {

	//新增工作单
	public void savaWorKBill(WorkbillColumns objWorkbill) throws Exception{
		SaveWorkBillTransaction objSaveWorkBillTrans=new SaveWorkBillTransaction();
		objSaveWorkBillTrans.setParam(objWorkbill);
		objSaveWorkBillTrans.execute();
	}
	
	//修改工作单
	public void modifyWorkBill(WorkbillColumns objWorkbill) throws Exception{
		ModifyWorkbillTransaction objModifyWorkbillTrans=new ModifyWorkbillTransaction();
		objModifyWorkbillTrans.setParam(objWorkbill);
		objModifyWorkbillTrans.execute();
	}
	//新增工作单的操作记录
	public void saveWorkBillRecord(WorkbillactionColumns objWorkRecord) throws Exception{
		SaveWorkBillActionTrans objWorkRecodeTrans=new SaveWorkBillActionTrans();
		objWorkRecodeTrans.setParam(objWorkRecord);
		objWorkRecodeTrans.execute();
	}
}
