package kyle.leis.es.company.companys.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorporationstatusDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TcoCorporation;
import kyle.leis.hi.TdiCorporationstatus;
import kyle.leis.hi.TdiOperator;

public class ModifyCompanysStatusTrans extends AbstractTransaction {
	private String m_strCocode;
	private String m_strCscode;
	private String m_strOperId;
	
	public void setParam(String strCocode, 
			String strCscode, 
			String strOperId) {
		m_strCocode = strCocode;
		m_strCscode = strCscode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		TcoCorporation objTcoCorporation = (TcoCorporation)objSession.load(TcoCorporation.class, 
				m_strCocode);
		// 修改人修改时间
		objTcoCorporation.setCoModifydate(DateFormatUtility.getSysdate());
		TdiOperator objTdiOperator = TdiOperatorDC.loadByKey(m_strOperId);
		objTcoCorporation.setTdiOperatorByCoOpIdModify(objTdiOperator);
		// 状态
		TdiCorporationstatus objTCS = TdiCorporationstatusDC.loadByKey(m_strCscode);
		objTcoCorporation.setTdiCorporationstatus(objTCS);
		// 保存
		objSession.save(objTcoCorporation);
	}

}
