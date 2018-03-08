package kyle.leis.eo.finance.serverbillrecord.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.serverbillrecord.da.ServerpayableColumns;
import kyle.leis.eo.finance.serverbillrecord.dax.ServerBillRecordDemand;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiServerbillrecord;

public class ModifyStatusTransaction extends AbstractTransaction {
	private String m_strSbrId;
	private String m_strOperId;
	private String m_strSscode;
	
	public void setParam(String strSbrId, 
			String strOperId,
			String strSscode) {
		m_strSbrId = strSbrId;
		m_strOperId = strOperId;
		m_strSscode = strSscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strSbrId)) return;
		// װ�ؼ�¼
		TfiServerbillrecord objTSBR = (TfiServerbillrecord)objSession.load(TfiServerbillrecord.class, 
				Long.parseLong(m_strSbrId));
		// ����״̬���޸���
		TdiSimplestatus objTSS = TdiSimplestatusDC.loadByKey(m_strSscode);
		objTSBR.setTdiSimplestatus(objTSS);
		TdiOperator objTOP = TdiOperatorDC.loadByKey(m_strOperId);
		objTSBR.setTdiOperatorByOpIdModifier(objTOP);
		objTSBR.setSbrModifydate(DateFormatUtility.getSysdate());
		objSession.save(objTSBR);
		// �����Ӧ��ϵ
		if (!m_strSscode.equals("ON")) return;
		
		List objList = ServerBillRecordDemand.loadPayable(m_strSbrId);
		if (objList == null || objList.size() < 1) return;
		for (int i = 0; i < objList.size(); i++) {
			ServerpayableColumns objSPYColumns = (ServerpayableColumns)objList.get(i);
			String strReferencecode = objSPYColumns.getSwbswbreferencecode();
			String strFkcode = objSPYColumns.getFkfkcode();
			String strSpyid = objSPYColumns.getSpyspyid();
			if (StringUtility.isNull(strReferencecode)) continue;
			// ����ϵͳ�еķ����̷���
			String strUpdateSql = "update t_bl_payable py set py.spy_id = " + strSpyid +
			" where py.cw_code = " + strReferencecode + 
			" and py.fk_code = '" + strFkcode + "'" +
			" and py.spy_id is null";
			this.execute(objSession, strUpdateSql);
		}
	}

}
