package kyle.leis.eo.finance.writeoff.tp;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiWriteoff;
import net.sf.hibernate.Session;

public class DeleteSBRWriteOffTrans  extends AbstractTransaction {
    private String m_strWoId;
    private String m_strOperId;
    
	public void setParam(String strWoId, 
			String strOperId) {
		m_strWoId = strWoId;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strWoId)) return;
		// �޸ĺ���״̬
		TfiWriteoff objTfiWriteoff = (TfiWriteoff)objSession.load(TfiWriteoff.class, 
				Long.parseLong(m_strWoId));
		// �޸���
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		objTfiWriteoff.setTdiOperator(objTdiOperator);
		// ʧЧ״̬
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey("OFF");
		objTfiWriteoff.setTdiSimplestatus(objTSS);
		objSession.save(objTfiWriteoff);
		// �޸ķ������˵�
		// �޸��˵�״̬
		execute(objSession, "update t_fi_serverbillrecord sbr " + 
				"  SET sbr.sbr_modifydate = sysdate," +
				"      sbr.op_id_modifier = " + m_strOperId + "," +
				"      sbr.wo_id = null " +
				"WHERE sbr.wo_id = " + m_strWoId);		
	}

}
