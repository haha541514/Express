package kyle.leis.eo.operation.predictwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.predictwaybill.da.ToppredictwaybillTR;

public class JModifyInputPWTrans extends AbstractTransaction {

	private ToppredictwaybillTR m_objToppredictwaybillTR;
	private String m_strPwbcode;
	
	public void setUploadParam(String strCwcode,
			String strPwbcode,
			String strOperId,
			TopcorewaybillTR objTopcorewaybillTR) {
		m_objToppredictwaybillTR = new ToppredictwaybillTR();
		m_objToppredictwaybillTR.setCw_code(strCwcode);
		m_objToppredictwaybillTR.setPwbs_code("CHU");
		m_objToppredictwaybillTR.setOp_id_declarer(strOperId);
		m_objToppredictwaybillTR.setPwb_declaredate(DateFormatUtility.getStandardSysdate());	
		m_objToppredictwaybillTR.setPwb_serverewbcode(objTopcorewaybillTR.getCw_serverewbcode());
		
		m_strPwbcode = strPwbcode;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		
		if (StringUtility.isNull(m_strPwbcode)) return;
		
		m_objToppredictwaybillTR.setPwb_codeCondition(m_strPwbcode);
		objTableAccess.updateRecord(m_objToppredictwaybillTR);
	}
}
