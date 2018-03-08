package kyle.leis.eo.operation.predictwaybill.tp;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.tp.ModifyCWStatusTrans;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class WithdrawPredictwaybillTrans extends AbstractTransaction {

	private PredictwaybillColumns m_objPredictwaybillColumns;
	private String m_strOperID;
	private String m_strNewstatus;
	
	public void setParam(PredictwaybillColumns objPredictwaybillColumns,
			String strNewstatus,
			String strOperID) {
		m_objPredictwaybillColumns = objPredictwaybillColumns;
		m_strOperID = strOperID;
		m_strNewstatus = strNewstatus;
	}
	
	public PredictwaybillColumns getSavedPredictwaybillColumns() {
		return m_objPredictwaybillColumns;
	}
	
	public void transaction(Session objSession) throws Exception {
		/*
		TopPredictwaybill objTopPredictwaybill = (TopPredictwaybill)objSession.load(TopPredictwaybill.class, 
				Long.parseLong(m_objPredictwaybillColumns.getPwbpwb_code()));
		
		objTopPredictwaybill.setPwbModifydate(DateFormatUtility.getSysdate());
		objTopPredictwaybill.setTdiOperatorByOpIdModifier(TdiOperatorDC.loadByKey(m_strOperID));
		objTopPredictwaybill.setTdiPredictwaybillstatus(TdiPredictwaybillstatusDC.loadByKey(m_strNewstatus));
		// 作废运单数据
		TopCorewaybill objTopCorewaybill = objTopPredictwaybill.getTopCorewaybill();
		if (objTopCorewaybill != null) {
			objTopCorewaybill.setTdiCorewaybillstatus(TdiCorewaybillstatusDC.loadByKey("EL"));
			objTopCorewaybill.setCwModifydate(DateFormatUtility.getSysdate());
			objTopCorewaybill.setCwOpIdModifier(new BigDecimal(m_strOperID));
			objSession.save(objTopCorewaybill);
		}
		
		// 重新申报，清空运单关联
		objTopPredictwaybill.setTopCorewaybill(null);
		objTopPredictwaybill.setPwbDeclaredate(null);
		objTopPredictwaybill.setTdiOperatorByOpIdDeclarer(null);
		objTopPredictwaybill.setTdiOperatorByOpIdPrinter(null);
		objTopPredictwaybill.setPwbPrintdate(null);		
		objSession.save(objTopPredictwaybill);
		*/
		// 作废运单数据
		if (!StringUtility.isNull(m_objPredictwaybillColumns.getPwbcw_code())) {
			ModifyCWStatusTrans objMCST = new ModifyCWStatusTrans();
			objMCST.setParam(m_objPredictwaybillColumns.getPwbcw_code(), 
					"EL", 
					m_strOperID);	
			objMCST.transaction(objSession);
		}
		// 撤销
		String strSQL = "update t_op_predictwaybill pwb " + 
					       "set pwb.pwbs_code = '" + m_strNewstatus + "'" +
		                   ",   pwb.op_id_modifier = " + m_strOperID +
		                   ",   pwb.cw_code = null"  +
		                   ",   pwb.PWB_DECLAREDATE = null"  +
		                   ",   pwb.PWB_PRINTDATE = null"  +
		                   ",   pwb.OP_ID_PRINTER = null"  +
		                   ",   pwb.pwb_modifydate = sysdate " +
				         "where pwb.pwb_code = " + m_objPredictwaybillColumns.getPwbpwb_code();
		execute(objSession, strSQL);
		m_objPredictwaybillColumns.setPwbspwbs_code(m_strNewstatus);
		if (m_strNewstatus.equals("CTS"))
			m_objPredictwaybillColumns.setPwbspwbs_name("暂存");
		if (m_strNewstatus.equals("CEL"))
			m_objPredictwaybillColumns.setPwbspwbs_name("作废");		
		m_objPredictwaybillColumns.setPwbcw_code("");
	}

}
