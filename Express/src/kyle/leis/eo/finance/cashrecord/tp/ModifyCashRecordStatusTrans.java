package kyle.leis.eo.finance.cashrecord.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.writeoff.tp.DeleteSBRWriteOffTrans;
import kyle.leis.hi.TdiCashrecordstatus;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfiCashrecord;

public class ModifyCashRecordStatusTrans extends AbstractTransaction {
	private String m_strCrid;
	private String m_strCrscode;
	private String m_strOperId;
	private String m_strOriginCrtotal; 
	private TfiCashrecord m_objTfiCashrecord;
	
	public void setParam(String strCrid, 
			String strCrscode, 
			String strOperId) {
		m_strCrid = strCrid;
		m_strCrscode = strCrscode;
		m_strOperId = strOperId;
	}
	
	public TfiCashrecord getSavedCashrecord() {
		return m_objTfiCashrecord;
	}	
	
	public String getOriginCrtotal() {
		return m_strOriginCrtotal;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCrid)) return;
		
		TfiCashrecord objTfiCashrecord = (TfiCashrecord)objSession.load(TfiCashrecord.class, 
				Long.parseLong(m_strCrid));
		if (!StringUtility.isNull(m_strCrscode)) {
			// 状态
			TdiCashrecordstatus objTdiCashrecordstatus = (TdiCashrecordstatus)objSession.load(TdiCashrecordstatus.class, 
					m_strCrscode);
			objTfiCashrecord.setTdiCashrecordstatus(objTdiCashrecordstatus);
		}
		
		if (!StringUtility.isNull(m_strOperId)) {
			TdiOperator objTdiOperator = (TdiOperator)objSession.load(TdiOperator.class, 
					Long.parseLong(m_strOperId));
			objTfiCashrecord.setTdiOperatorByCrOpIdModifier(objTdiOperator);
			objTfiCashrecord.setCrModifydate(DateFormatUtility.getSysdate());
		}		
		// 原收付款金额
		BigDecimal objOriginCrtotal = objTfiCashrecord.getCrTotal().multiply(objTfiCashrecord.getCrCurrencyrate());
		objOriginCrtotal = objOriginCrtotal.divide(new BigDecimal("1"), 2, 4);
		m_strOriginCrtotal = objOriginCrtotal.toString();
		// 如果是作废则还需要再查找核销记录，并作废核销记录		
		if (m_strCrscode.equals("E") && objTfiCashrecord.getTfiWriteoff() != null) {
			DeleteSBRWriteOffTrans objDSBRWOT = new DeleteSBRWriteOffTrans();
			objDSBRWOT.setParam(String.valueOf(objTfiCashrecord.getTfiWriteoff().getWoId()), 
					m_strOperId);
			objDSBRWOT.transaction(objSession);
			objTfiCashrecord.setTfiWriteoff(null);
		}
		// 保存
		objSession.save(objTfiCashrecord);
		m_objTfiCashrecord = objTfiCashrecord;
	}

}
