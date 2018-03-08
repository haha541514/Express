package kyle.leis.eo.finance.writeoff.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.writeoff.da.WriteoffColumns;
import kyle.leis.eo.finance.writeoff.dax.WriteoffDemand;
import kyle.leis.hi.TdiBillrecordstatus;
import kyle.leis.hi.TdiCashrecordstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiBillrecord;
import kyle.leis.hi.TfiCashrecord;
import kyle.leis.hi.TfiWriteoff;

public class AddWriteOffTrans extends AbstractTransaction {
	private WriteoffColumns m_objWriteoffColumns;
	private TfiBillrecord m_objTfiBillrecord;
	private TfiCashrecord m_objTfiCashrecord;
	private String m_strOperId;
	private Long m_lNewWoid;
	private TfiWriteoff m_objTfiWriteoff;
	
	public void setParam(String strCkcode, 
			String strRemark, 
			String strWototal, 
			String strOperId) {
		WriteoffColumns objWriteoffColumns = new WriteoffColumns();
		objWriteoffColumns.setCkckcode(strCkcode);
		objWriteoffColumns.setWoworemark(strRemark);
		objWriteoffColumns.setWowototal(new BigDecimal(strWototal));
		setParam(objWriteoffColumns, strOperId);
	}
	
	public void setParam(TfiBillrecord objTfiBillrecord,
			TfiCashrecord objTfiCashrecord,
			String strOperId) {
		BigDecimal objCrtotal = objTfiCashrecord.getCrTotal().multiply(objTfiCashrecord.getCrCurrencyrate());
		objCrtotal = objCrtotal.divide(new BigDecimal("1"), 2, 4);
		this.setParam("RMB",
				"直客收款直接核销",
				objCrtotal.toString(),
				strOperId);
		m_objTfiBillrecord = objTfiBillrecord;
		m_objTfiCashrecord = objTfiCashrecord;
	}
	
	public void setParam(WriteoffColumns objWriteoffColumns,
			String strOperId) {
		m_objWriteoffColumns = objWriteoffColumns;
		m_strOperId = strOperId;
	}
	
	public Long getNewWoid() {
		return m_lNewWoid;
	}
	
	public TfiWriteoff getSavedWriteoff() {
		return m_objTfiWriteoff;
	}
	
	public void transaction(Session objSession) throws Exception {
		TfiWriteoff objTfiWriteoff = new TfiWriteoff();
		WriteoffDemand.setWriteoffByColumns(objTfiWriteoff, 
				m_objWriteoffColumns,
				m_strOperId,
				objSession);
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTfiWriteoff.setTdiOperator(objTdiOperator);
			objTfiWriteoff.setWoCreatedate(DateFormatUtility.getSysdate());
		}
		objTfiWriteoff.setWoLabelcode(WriteoffDemand.getWoLabelcode(m_objWriteoffColumns));
		objSession.save(objTfiWriteoff);
		m_lNewWoid = objTfiWriteoff.getWoId();
		m_objTfiWriteoff = objTfiWriteoff;
		// 核销收款
		if (m_objTfiCashrecord == null) return;
		m_objTfiCashrecord.setTfiWriteoff(objTfiWriteoff);
		TdiCashrecordstatus objTCRStatus = (TdiCashrecordstatus)objSession.load(TdiCashrecordstatus.class,
				"W");
		m_objTfiCashrecord.setTdiCashrecordstatus(objTCRStatus);
		objSession.update(m_objTfiCashrecord);
		// 核销账单
		m_objTfiBillrecord.setTfiWriteoff(objTfiWriteoff);
		TdiBillrecordstatus objTBRStatus = (TdiBillrecordstatus)objSession.load(TdiBillrecordstatus.class, 
				"W");
		m_objTfiBillrecord.setTdiBillrecordstatus(objTBRStatus);	
		objSession.update(objTfiWriteoff);
		// 修改费用状态
		execute(objSession, "UPDATE T_BL_RECEIVABLE rv " + 
				" SET FS_CODE = 'W'" +
				" WHERE rv.BR_ID = " + m_objTfiBillrecord.getBrId());
		// 修改应付费用
		execute(objSession, "UPDATE T_BL_PAYABLE py " + 
				" SET FS_CODE = 'W'" +
				" WHERE py.BR_ID = " + m_objTfiBillrecord.getBrId());
	}

}
