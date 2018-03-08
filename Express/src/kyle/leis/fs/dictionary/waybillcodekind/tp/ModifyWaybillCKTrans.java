package kyle.leis.fs.dictionary.waybillcodekind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.dax.WaybillCKDemand;
import kyle.leis.hi.TdiWaybillcodekind;

public class ModifyWaybillCKTrans extends AbstractTransaction{
	private WaybillcodekindColumns m_objColumns;
	
	public void setParam(WaybillcodekindColumns objColumns)throws Exception {
		m_objColumns = objColumns;
	}
	public void transaction(Session objSession) throws Exception {
		TdiWaybillcodekind objWaybillcodekind =  (TdiWaybillcodekind) objSession.load(TdiWaybillcodekind.class, 
				m_objColumns.getWbckbck_code());
		WaybillCKDemand.setWaybillCKByColumns(objWaybillcodekind,m_objColumns, objSession);
		objSession.update(objWaybillcodekind);
		
	}

}
