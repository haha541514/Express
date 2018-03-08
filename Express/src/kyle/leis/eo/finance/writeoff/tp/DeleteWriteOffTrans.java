package kyle.leis.eo.finance.writeoff.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiWriteoff;

public class DeleteWriteOffTrans extends AbstractTransaction {
    private String m_strWoId;
    private String m_strOperId;
    
	public void setParam(String strWoId, 
			String strOperId) {
		m_strWoId = strWoId;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strWoId)) return;
		// 修改核销状态
		TfiWriteoff objTfiWriteoff = (TfiWriteoff)objSession.load(TfiWriteoff.class, 
				Long.parseLong(m_strWoId));
		// 修改人
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		objTfiWriteoff.setTdiOperator(objTdiOperator);
		// 失效状态
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey("OFF");
		objTfiWriteoff.setTdiSimplestatus(objTSS);
		objSession.save(objTfiWriteoff);
		// 修改费用状态
		execute(objSession, "UPDATE T_BL_RECEIVABLE rv " + 
				" SET FS_CODE = 'B'" +
				" WHERE exists (select br.br_id from t_fi_billrecord br" + 
				" where br.br_id = rv.br_id and br.wo_id = " + 
				m_strWoId + ")");
		// 修改应付费用
		execute(objSession, "UPDATE T_BL_PAYABLE py " + 
				" SET FS_CODE = 'B'" +
				" WHERE exists (select br.br_id from t_fi_billrecord br" + 
				" where br.br_id = py.br_id and br.wo_id = " + 
				m_strWoId + ")");
		// 修改账单状态
		execute(objSession, "update t_fi_billrecord br " + 
				"  SET br.brs_code = 'A'," +
				"      br.br_modifydate = sysdate," +
				"      br.br_op_id_modifier = " + m_strOperId + "," +
				"      br.wo_id = null" +
				"WHERE br.wo_id = " + m_strWoId);
		// 修改收付款状态
		execute(objSession, "update t_fi_cashrecord cr " + 
				" SET cr.crs_code = 'A'," +
				" cr.cr_modifydate = sysdate," +
				" cr.cr_op_id_modifier = " + m_strOperId + "," + 
				" cr.wo_id = null" +
				" WHERE cr.wo_id = " + m_strWoId + 
				"   AND cr.pt_code <> 'WT'");
		// 将冲销收付款的状态改为作废状态
		execute(objSession, "update t_fi_cashrecord cr " + 
				" SET cr.crs_code = 'E'," +
				" cr.cr_modifydate = sysdate," +
				" cr.cr_op_id_modifier = " + m_strOperId + "," + 
				" cr.wo_id = null" +
				" WHERE cr.wo_id = " + m_strWoId + 
				"   AND cr.pt_code = 'WT'");		
	}

}
