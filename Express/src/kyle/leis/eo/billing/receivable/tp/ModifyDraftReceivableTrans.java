package kyle.leis.eo.billing.receivable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.hi.TblReceivable;
import net.sf.hibernate.Session;

public class ModifyDraftReceivableTrans extends AbstractTransaction  {
	private ReceivableColumns m_objRvColumns;
	private String m_strOperId;
	private String m_strOriginRvid;
	
	public void setParam(ReceivableColumns objRvColumns,
			String strOriginRvid,
			String strOperId) {
		m_objRvColumns = objRvColumns;
		m_strOperId = strOperId;
		m_strOriginRvid = strOriginRvid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objRvColumns == null) return;
		if (StringUtility.isNull(m_strOriginRvid)) return;
		
		TblReceivable objTblReceivable = (TblReceivable)objSession.load(TblReceivable.class, 
				Long.parseLong(m_strOriginRvid));
		ReceivableDemand.setReceivalbeFromColumns(objTblReceivable, 
				m_objRvColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTblReceivable);
	}
}
