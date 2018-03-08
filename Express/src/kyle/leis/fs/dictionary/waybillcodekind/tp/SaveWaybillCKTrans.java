package kyle.leis.fs.dictionary.waybillcodekind.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.fs.dictionary.waybillcodekind.da.WaybillcodekindColumns;
import kyle.leis.fs.dictionary.waybillcodekind.dax.WaybillCKDemand;
import kyle.leis.hi.TdiWaybillcodekind;

public class SaveWaybillCKTrans extends AbstractTransaction{
	private WaybillcodekindColumns m_objColumns;
    private String m_strBckCode;
	public void setParam(WaybillcodekindColumns objColumns)throws Exception {
		m_objColumns = objColumns;
	}
	
	public String getBckCode(){
		return m_strBckCode;
	}
	public void transaction(Session objSession) throws Exception {
		if(m_objColumns == null)
			return;
		TdiWaybillcodekind objWaybillcodekind =  new TdiWaybillcodekind();
		WaybillCKDemand.setWaybillCKByColumns(objWaybillcodekind,m_objColumns, objSession);
		objSession.save(objWaybillcodekind);

		m_strBckCode = objWaybillcodekind.getBckCode();
	}

}
