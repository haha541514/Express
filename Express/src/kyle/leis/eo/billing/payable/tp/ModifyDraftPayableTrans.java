package kyle.leis.eo.billing.payable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.hi.TblPayable;
import net.sf.hibernate.Session;

public class ModifyDraftPayableTrans extends AbstractTransaction  {
	private PayableColumns m_objPayableColumns;
	private String m_strOperId;
	private String m_strOriginPyid;
	
	public void setParam(PayableColumns objPayableColumns,
			String strOriginPyid,
			String strOperId) {
		m_objPayableColumns = objPayableColumns;
		m_strOperId = strOperId;
		m_strOriginPyid = strOriginPyid;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objPayableColumns == null) return;
		if (StringUtility.isNull(m_strOriginPyid)) return;
		
		TblPayable objTblPayable = (TblPayable)objSession.load(TblPayable.class, 
				Long.parseLong(m_strOriginPyid));
		PayableDemand.setPayableFromColumns(objTblPayable, 
				m_objPayableColumns, 
				m_strOperId, 
				objSession);
		objSession.save(objTblPayable);
	}

}
