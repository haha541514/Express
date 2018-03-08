package kyle.leis.fs.corewaybillauditlog.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.fs.dictionary.dictionarys.da.TdiFinanceauditlogtypeDC;
import kyle.leis.fs.dictionary.dictionarys.da.TdiOperatorDC;
import kyle.leis.hi.TdiFinanceauditlogtype;
import kyle.leis.hi.TdiOperator;
import kyle.leis.hi.TfsCorewaybillauditlog;
import kyle.leis.hi.TfsCorewaybillauditlogPK;
import kyle.leis.hi.TopCorewaybill;

public class AddAuditlogTransaction extends AbstractTransaction {
	private String m_strCwcode;
	private String m_strFaltcode;
	private String m_strOperId;
	
	public void setParam(String strCwcode, 
			String strFaltcode, 
			String strOperId) {
		m_strCwcode = strCwcode;
		m_strFaltcode = strFaltcode;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (StringUtility.isNull(m_strCwcode)) return;
		
		TfsCorewaybillauditlog objTCAL = new TfsCorewaybillauditlog();
		
		TfsCorewaybillauditlogPK objTCAPK = new TfsCorewaybillauditlogPK();
		objTCAPK.setCwCode(Long.parseLong(m_strCwcode));
		objTCAPK.setFaltCode(m_strFaltcode);
		objTCAL.setComp_id(objTCAPK);
		
		TopCorewaybill objTCW = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
				Long.parseLong(m_strCwcode));
		objTCAL.setTopCorewaybill(objTCW);
		objTCAL.setCwalCreatedate(DateFormatUtility.getSysdate());
		
		TdiFinanceauditlogtype objTFALT = TdiFinanceauditlogtypeDC.loadByKey(m_strFaltcode);
		objTCAL.setTdiFinanceauditlogtype(objTFALT);
		
		TdiOperator objTOP = TdiOperatorDC.loadByKey(m_strOperId);
		objTCAL.setTdiOperator(objTOP);
		
		objSession.save(objTCAL);
	}

}
