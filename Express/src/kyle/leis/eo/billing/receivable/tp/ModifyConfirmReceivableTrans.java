package kyle.leis.eo.billing.receivable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import net.sf.hibernate.Session;

public class ModifyConfirmReceivableTrans extends AbstractTransaction  {
	private ReceivableColumns m_objRvColumns;
	private String m_strOperId;
	private ReceivableColumns m_objOriginRvColumns;
	
	public void setParam(ReceivableColumns objRvColumns,
			ReceivableColumns objOriginRvColumns,
			String strOperId) {
		m_objRvColumns = objRvColumns;
		m_strOperId = strOperId;
		m_objOriginRvColumns = objOriginRvColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objRvColumns == null) return;
		if (StringUtility.isNull(m_objOriginRvColumns.getRvrvid())) 
			return;
		// 先删除原数据
		DelelteReceivableTrans objDeleteTrans = new DelelteReceivableTrans();
		objDeleteTrans.setParam(m_objOriginRvColumns, "0");
		objDeleteTrans.transaction(objSession);
		// 再新增
		AddReceivableTrans objAddReceivableTrans = new AddReceivableTrans();
		objAddReceivableTrans.setParam(m_objRvColumns, m_strOperId);
		objAddReceivableTrans.transaction(objSession);
	}

}
