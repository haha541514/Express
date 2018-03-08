package kyle.leis.es.workbill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.workbill.da.WorkbillColumns;
import kyle.leis.es.workbill.dax.WorkbillDemand;
import kyle.leis.hi.TesWorkbill;

public class SaveWorkBillTransaction extends AbstractTransaction {
	private WorkbillColumns m_workbill;
	public void setParam(WorkbillColumns workbill){
		m_workbill=workbill;
		
	}
	
	//保存工作单
	public void transaction(Session objSession) throws Exception {
		if(m_workbill!=null){
			WorkbillDemand objWorkbillDemand=new WorkbillDemand();
			TesWorkbill objTesWorkbill=objWorkbillDemand.setWorkBill(m_workbill);
			objSession.save(objTesWorkbill);
		}
	}
}
