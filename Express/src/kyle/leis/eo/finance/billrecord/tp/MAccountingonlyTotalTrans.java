package kyle.leis.eo.finance.billrecord.tp;

import java.math.BigDecimal;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;

public class MAccountingonlyTotalTrans extends AbstractTransaction {

	private String m_strBrid;
	private BigDecimal m_objAccountingOnly;
	private BigDecimal m_objAccountAmount;
	
	public void setParam(String strBrid, 
			BigDecimal objAccountingOnly,
			BigDecimal objAccountAmount) {
		m_strBrid = strBrid;
		m_objAccountingOnly = objAccountingOnly;
		m_objAccountAmount = objAccountAmount;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		execute(objSession, "UPDATE t_fi_billrecord br " + 
				" SET br.br_agencyfeetotal = " + m_objAccountingOnly.toString() +
				"    ,br.br_total = " + m_objAccountAmount.toString() +
				" WHERE br.BR_ID = " + m_strBrid);
	}

}
