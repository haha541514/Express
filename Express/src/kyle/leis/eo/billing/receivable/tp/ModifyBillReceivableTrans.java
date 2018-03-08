package kyle.leis.eo.billing.receivable.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.billing.receivable.da.ReceivableColumns;
import kyle.leis.eo.billing.receivable.dax.ReceivableDemand;
import kyle.leis.hi.TblReceivable;
import net.sf.hibernate.Session;

public class ModifyBillReceivableTrans extends AbstractTransaction  {
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
		// 先新增反向费用
		ReceivableColumns objOriginRecColumns = ReceivableDemand.loadByRvid(m_strOriginRvid);
		DelelteReceivableTrans objDeleteTrans = new DelelteReceivableTrans();
		objDeleteTrans.setParam(objOriginRecColumns, "0");
		objDeleteTrans.transaction(objSession);
		// 再新增新的费用
		AddReceivableTrans objAddReceivableTrans = new AddReceivableTrans();
		m_objRvColumns.setFsfscode("C");
		objAddReceivableTrans.setParam(m_objRvColumns, m_strOperId, true);
		objAddReceivableTrans.transaction(objSession);
		Long lNewRvid = objAddReceivableTrans.getNewRvid();
		// 修改原费用的指向
		TblReceivable objOriginTblReceivable = (TblReceivable)objSession.load(TblReceivable.class, 
				Long.parseLong(m_strOriginRvid));
		objOriginTblReceivable.setRvIdReference(lNewRvid);
		objSession.update(objOriginTblReceivable);
	}

}
