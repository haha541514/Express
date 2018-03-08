package kyle.leis.eo.operation.predictwaybill.tp;

import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybill.tp.ModifyCWStatusTrans;
import kyle.leis.eo.operation.predictwaybill.da.ToppredictwaybillTR;
import net.sf.hibernate.Session;

public class ModifyPredictwaybillstatusTrans extends AbstractTransaction {

	private String m_strPwbcode;
	private String m_strOperID;
	private String m_strNewstatus;
	
	public void setParam(String strPwbcode,
			String strNewstatus,
			String strOperID) {
		m_strPwbcode = strPwbcode;
		m_strOperID = strOperID;
		m_strNewstatus = strNewstatus;
	}
	
	public void transaction(Session objSession) throws Exception {
		/*
		TopPredictwaybill objTopPredictwaybill = (TopPredictwaybill)objSession.load(TopPredictwaybill.class, 
				Long.parseLong(m_strPwbcode));
		*/
		TableAccess objTA = new TableAccess(objSession.connection());
		ToppredictwaybillTR objTPWBTR = new ToppredictwaybillTR();
		objTPWBTR.setPwb_codeCondition(m_strPwbcode);
		objTA.selectRecord(objTPWBTR);
		
		String strSQL = "update t_op_predictwaybill pwb " + 
			    "set pwb.pwbs_code = '" + m_strNewstatus + "'" +
	            ",   pwb.op_id_modifier = " + m_strOperID +
	            ",   pwb.pwb_modifydate = sysdate " +
			  "where pwb.pwb_code = " + m_strPwbcode;
		if (m_strNewstatus.equals("CHP")) {
			strSQL = "update t_op_predictwaybill pwb " + 
					    "set pwb.pwbs_code = '" + m_strNewstatus + "'" +
			            ",   pwb.op_id_modifier = " + m_strOperID +
			            ",   pwb.pwb_modifydate = sysdate " +
			            ",   pwb.OP_ID_PRINTER = " + m_strOperID +
			            ",   pwb.PWB_PRINTDATE = sysdate " +
					  "where pwb.pwb_code = " + m_strPwbcode;
		}
		execute(objSession, strSQL);
		// ÐÞ¸ÄÔËµ¥×´Ì¬
		//TopCorewaybill objTopCorewaybill = objTopPredictwaybill.getTopCorewaybill();
		ModifyCWStatusTrans modifytrans =  new ModifyCWStatusTrans();
		modifytrans.setParam(objTPWBTR.getCw_code(), m_strNewstatus, m_strOperID);
		modifytrans.transaction(objSession);
	}
}
