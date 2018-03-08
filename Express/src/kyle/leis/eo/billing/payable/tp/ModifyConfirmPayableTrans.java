package kyle.leis.eo.billing.payable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import net.sf.hibernate.Session;

public class ModifyConfirmPayableTrans extends AbstractTransaction  {
	private PayableColumns m_objPayableColumns;
	private String m_strOperId;
	private PayableColumns m_objOriginPyColumns;
	
	public void setParam(PayableColumns objPayableColumns,
			PayableColumns objOriginPyColumns,
			String strOperId) {
		m_objPayableColumns = objPayableColumns;
		m_strOperId = strOperId;
		m_objOriginPyColumns = objOriginPyColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objPayableColumns == null) return;
		if (StringUtility.isNull(m_objOriginPyColumns.getPypyid())) return;
		// 先删除
		DeleltePayableTrans objDeleteTrans = new DeleltePayableTrans();
		objDeleteTrans.setParam(m_objOriginPyColumns, "0");
		objDeleteTrans.transaction(objSession);
		// 再新增
		AddPayableTrans objAddPayableTrans = new AddPayableTrans();
		objAddPayableTrans.setParam(m_objPayableColumns, m_strOperId);
		objAddPayableTrans.transaction(objSession);
	}


}
