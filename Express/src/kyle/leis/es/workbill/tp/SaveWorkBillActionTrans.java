package kyle.leis.es.workbill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.es.workbill.da.WorkbillactionColumns;

public class SaveWorkBillActionTrans extends AbstractTransaction {
	private WorkbillactionColumns m_workbillaction;
	public void setParam(WorkbillactionColumns workbillaction){
		m_workbillaction=workbillaction;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_workbillaction!=null){
			//增加操作记录
			SavaSingleWorkBillActionTrans objSSWBATrans=new SavaSingleWorkBillActionTrans();
			objSSWBATrans.setParam(m_workbillaction);
			objSSWBATrans.transaction(objSession);
			
			//修改工作单状态
			ModifyWorkbillAkcodeTrans objAkCodeTransation=new ModifyWorkbillAkcodeTrans();
			objAkCodeTransation.setParam(m_workbillaction.getAkakcode(), m_workbillaction.getWbwbid());
			objAkCodeTransation.transaction(objSession);
		}
	}
}
