package kyle.leis.eo.finance.serverbillrecord.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.da.ServerwaybillColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.hi.TfiServerwaybill;

public class AddServerwaybillTrans extends AbstractTransaction {
	private ServerpayableColumns m_objSPYColumns;
	private TfiServerwaybill m_objTfiServerwaybill;
	private boolean m_isInsertWaybill;
	
	public void setParam(ServerpayableColumns objSPYColumns) throws Exception {
		m_objSPYColumns = objSPYColumns;
	}
	
	public TfiServerwaybill getSavedServerwaybill() {
		return m_objTfiServerwaybill;
	}
	
	public boolean isInsertWaybill() {
		return m_isInsertWaybill;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_objSPYColumns == null) return;
		
		String strReference = ServerBillRecordDemand.getReferenceForServerwaybill(m_objSPYColumns);
		ServerwaybillColumns objSWBColumns = ServerBillRecordDemand.loadServerwaybill(m_objSPYColumns.getSwbswbserverewbcode(), 
				m_objSPYColumns.getChnchncode(),
				strReference);
		TfiServerwaybill objTfiServerwaybill;
		m_isInsertWaybill = true;
		if (objSWBColumns == null) {
			// 新增并保存
			if (!StringUtility.isNull(strReference))
				m_objSPYColumns.setSwbswbreferencecode(Long.parseLong(strReference));
			objTfiServerwaybill = new TfiServerwaybill();
			ServerBillRecordDemand.setServerWaybillByColumns(m_objSPYColumns, objTfiServerwaybill);
			ServerBillRecordDemand.resetWaybillFee(objTfiServerwaybill, m_objSPYColumns);
			objSession.save(objTfiServerwaybill);		
		} else {
			// 修改
			objTfiServerwaybill = (TfiServerwaybill)objSession.load(TfiServerwaybill.class, 
					Long.parseLong(objSWBColumns.getSwbswb_code()));
			ServerBillRecordDemand.resetWaybillFee(objTfiServerwaybill, m_objSPYColumns);
			m_isInsertWaybill = false;
		}
		m_objTfiServerwaybill = objTfiServerwaybill;
	}

}
