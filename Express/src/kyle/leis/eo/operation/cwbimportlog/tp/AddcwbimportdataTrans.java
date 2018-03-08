package kyle.leis.eo.operation.cwbimportlog.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.cwbimportlog.da.CwbimportdataColumns;
import kyle.leis.eo.operation.cwbimportlog.dax.CwbimportlogDemand;
import kyle.leis.hi.TopCwbimportdata;
import kyle.leis.hi.TopCwbimportrow;

public class AddcwbimportdataTrans extends AbstractTransaction {
	private CwbimportdataColumns m_Cwbimportdata;
	
	public void setParam(CwbimportdataColumns Cwbimportdata){
		if(Cwbimportdata==null)
			return;
		m_Cwbimportdata=Cwbimportdata;
	}
	public void transaction(Session objSession) throws Exception {
		TopCwbimportdata objTopCwbimportdata=new TopCwbimportdata();
		CwbimportlogDemand.setCwbimportdataByColumns(objTopCwbimportdata, m_Cwbimportdata);
		objSession.save(objTopCwbimportdata);
	}

}
