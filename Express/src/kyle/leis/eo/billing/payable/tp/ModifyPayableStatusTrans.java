package kyle.leis.eo.billing.payable.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblPayable;
import kyle.leis.hi.TdiFeestatus;
import kyle.leis.hi.TdiOperator;

public class ModifyPayableStatusTrans extends AbstractTransaction {
	private String[] m_astrPyid;
	private String m_strOperId;
	private String m_strFscode;
	
	public void setParam(String[] astrPyid, 
			String strOperId, 
			String strFscode) {
		m_astrPyid = astrPyid;
		m_strOperId = strOperId;
		m_strFscode = strFscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrPyid == null || m_astrPyid.length < 1) return;
		
		for (int i = 0; i < m_astrPyid.length; i++) {
			String strPyid = m_astrPyid[i];
			TblPayable objTblPayable = (TblPayable)objSession.load(TblPayable.class, 
					Long.parseLong(strPyid));
			// 只有作废和确定时才需要更新
			if ((m_strFscode.equals("E") || m_strFscode.equals("C")) &&
					objTblPayable.getTdiFeestatus().getFsCode().equals("D")) {
				TdiFeestatus objTdiFeestatus = TdiFeestatusDC.loadByKey(m_strFscode);
				objTblPayable.setTdiFeestatus(objTdiFeestatus);
				// 修改人和修改时间
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
				objTblPayable.setTdiOperatorByPyOpIdModifier(objTdiOperator);
				objTblPayable.setPyModifydate(DateFormatUtility.getSysdate());
				// 更新
				objSession.update(objTblPayable);
			}
		}		
	}

}
