package kyle.leis.eo.operation.cwbimportlog.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportrowColumns;
import kyle.leis.eo.operation.cwbimportlog.dax.CwbimportlogDemand;
import kyle.leis.hi.TopCwbimportrow;

public class AddcwbimportrowTrans extends AbstractTransaction{
	private CwbimportrowColumns m_Cwbimportrow;
	public void setParam(CwbimportrowColumns Cwbimportrow){
		if(Cwbimportrow==null)
			return;
		m_Cwbimportrow=Cwbimportrow;
	}
	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		TopCwbimportrow objTopCwbimportrow=new TopCwbimportrow();
		CwbimportlogDemand.setCwbimportrowByColumns(objTopCwbimportrow, m_Cwbimportrow);
		objSession.save(objTopCwbimportrow);
	}
}
