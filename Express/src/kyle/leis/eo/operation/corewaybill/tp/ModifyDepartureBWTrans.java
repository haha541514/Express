package kyle.leis.eo.operation.corewaybill.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.BatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import net.sf.hibernate.Session;

public class ModifyDepartureBWTrans extends AbstractTransaction {
	private String[] m_astrCwcode;
	private String m_strBwcodeDest;
	private String m_strOperId;
	
	public void setParam(String[] astrCwcode, 
			String strBwcodeDest,
			String strOperId) {
		m_astrCwcode = astrCwcode;
		m_strBwcodeDest = strBwcodeDest;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strBwcodeDest) || 
				m_astrCwcode == null ||
				m_astrCwcode.length < 1) 
			return;
		BatchwaybillColumns objBWColumns = BatchWayBillDemand.load(m_strBwcodeDest);
		if (objBWColumns == null) return;
		
		for (int i = 0; i < m_astrCwcode.length; i++) {
			String strUpdateSql = "update T_OP_COREWAYBILL cw SET cw.bw_code_departure = " +
			m_strBwcodeDest + 
			", cw.co_code_supplier = '" + objBWColumns.getCococode() + "'" +
			", cw.chn_code_supplier = '" + objBWColumns.getChnchncode() + "'" +
			", cw.CW_OP_ID_MODIFIER = " + m_strOperId + 
			", cw.CW_MODIFYDATE = SYSDATE WHERE cw.cw_code = " + m_astrCwcode[i];
			execute(objSession, strUpdateSql);
		}
	}
}
