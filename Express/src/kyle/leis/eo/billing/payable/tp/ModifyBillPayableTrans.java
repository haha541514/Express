package kyle.leis.eo.billing.payable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.payable.da.PayableColumns;
import kyle.leis.eo.billing.payable.dax.PayableDemand;
import kyle.leis.hi.TblPayable;
import net.sf.hibernate.Session;

public class ModifyBillPayableTrans extends AbstractTransaction  {
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
		// 先新增反向费用
		PayableColumns objOriginPayableColumns = PayableDemand.loadByPyid(m_strOriginPyid);
		DeleltePayableTrans objDeleteTrans = new DeleltePayableTrans();
		objDeleteTrans.setParam(objOriginPayableColumns, "0");
		objDeleteTrans.transaction(objSession);
		// 再新增新的费用
		AddPayableTrans objAddPayableTrans = new AddPayableTrans();
		m_objPayableColumns.setFsfscode("C");
		objAddPayableTrans.setParam(m_objPayableColumns, m_strOperId, true);
		objAddPayableTrans.transaction(objSession);
		Long lNewPyid = objAddPayableTrans.getNewPyid();
		// 修改原费用的指向
		TblPayable objOriginTblPayable = (TblPayable)objSession.load(TblPayable.class, 
				Long.parseLong(m_strOriginPyid));
		objOriginTblPayable.setPyIdReference(lNewPyid);
		objSession.update(objOriginTblPayable);
	}

}
