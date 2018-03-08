package kyle.leis.eo.billing.receivable.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFeestatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TblReceivable;
import kyle.leis.hi.TdiFeestatus;
import kyle.leis.hi.TdiOperator;

public class ModifyReceivableStatusTrans extends AbstractTransaction {
	private String[] m_astrRvid;
	private String m_strOperId;
	private String m_strFscode;
	
	public void setParam(String[] astrRvid, 
			String strOperId, 
			String strFscode) {
		m_astrRvid = astrRvid;
		m_strOperId = strOperId;
		m_strFscode = strFscode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_astrRvid == null || m_astrRvid.length < 1) return;
		
		for (int i = 0; i < m_astrRvid.length; i++) {
			String strRvid = m_astrRvid[i];
			TblReceivable objTblReceivable = (TblReceivable)objSession.load(TblReceivable.class, 
					Long.parseLong(strRvid));
			// 只有作废和确定时才需要更新
			if ((m_strFscode.equals("E") || m_strFscode.equals("C")) &&
					objTblReceivable.getTdiFeestatus().getFsCode().equals("D")) {
				TdiFeestatus objTdiFeestatus = TdiFeestatusDC.loadByKey(m_strFscode);
				objTblReceivable.setTdiFeestatus(objTdiFeestatus);
				// 修改人和修改时间
				TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
				objTblReceivable.setTdiOperatorByRvOpIdModifier(objTdiOperator);
				objTblReceivable.setRvModifydate(DateFormatUtility.getSysdate());
				// 更新
				objSession.update(objTblReceivable);
			}
		}
	}

}
