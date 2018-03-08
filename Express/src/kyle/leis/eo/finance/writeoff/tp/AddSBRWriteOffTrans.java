package kyle.leis.eo.finance.writeoff.tp;

import java.math.BigDecimal;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.writeoff.da.WriteoffColumns;
import kyle.leis.eo.finance.writeoff.dax.WriteoffDemand;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiCashrecord;
import kyle.leis.hi.TfiServerbillrecord;
import kyle.leis.hi.TfiWriteoff;
import net.sf.hibernate.Session;

public class AddSBRWriteOffTrans extends AbstractTransaction {
	private WriteoffColumns m_objWriteoffColumns;
	private TfiCashrecord m_objTfiCashrecord;
	private String[] m_astrSWBCode;
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
	
	public void setParam(TfiCashrecord objTfiCashrecord,
			String[] astrSWBCode,
			String strOperId) {
		BigDecimal objCrtotal = objTfiCashrecord.getCrTotal().multiply(objTfiCashrecord.getCrCurrencyrate());
		objCrtotal = objCrtotal.divide(new BigDecimal("1"), 2, 4);
		m_astrSWBCode = astrSWBCode;
		this.setParam("RMB",
				"根据服务商账单付款",
				objCrtotal.toString(),
				strOperId);
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
		if (m_astrSWBCode == null || m_astrSWBCode.length < 1)
			return;
		
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
		objSession.update(m_objTfiCashrecord);
		// 核销服务商账单
		for (int i = 0; i < m_astrSWBCode.length; i++) {
			String strSwbcode = m_astrSWBCode[i];
			TfiServerbillrecord objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
					Long.parseLong(strSwbcode));
			objTSBR.setTfiWriteoff(objTfiWriteoff);
			objSession.save(objTSBR);
		}
	}
}
