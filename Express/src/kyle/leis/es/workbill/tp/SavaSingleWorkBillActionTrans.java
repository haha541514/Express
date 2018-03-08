package kyle.leis.es.workbill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.workbill.da.WorkbillactionColumns;
import kyle.leis.es.workbill.dax.WorkbillDemand;
import kyle.leis.hi.TesWorkbillaction;
import net.sf.hibernate.Session;

public class SavaSingleWorkBillActionTrans extends AbstractTransaction {
	private WorkbillactionColumns m_workbillaction;
	public void setParam(WorkbillactionColumns workbillaction){
		m_workbillaction=workbillaction;
	}
	
	//保存工作单操作记录
	public void transaction(Session objSession) throws Exception {
		if(m_workbillaction!=null){	
			WorkbillDemand objdemand=new WorkbillDemand();
			TesWorkbillaction objTesWorkbillaction=objdemand.setWorkBillAction(m_workbillaction);
			objSession.save(objTesWorkbillaction);
		}
	}
}
