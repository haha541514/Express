package kyle.leis.eo.operation.housewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.batchwaybill.da.SimplebatchwaybillColumns;
import kyle.leis.eo.operation.batchwaybill.dax.BatchWayBillDemand;
import kyle.leis.eo.operation.batchwaybill.tp.JSaveBWTrans;
import kyle.leis.eo.operation.cargoinfo.tp.JSaveCargoInfoTrans;
import kyle.leis.eo.operation.corewaybill.tp.JSaveCoreWayBillTrans;
import kyle.leis.eo.operation.corewaybillpieces.tp.JSaveCWBPTrans;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.tp.JModifyInputPWTrans;

public class JInputAllTransaction extends AbstractTransaction {
	private ForinputallColumns m_objFIAColumns;
	private String m_strOperId;
	private String m_strSavedCwcode;
	private List m_listCargo;
	private List m_listWaybillPieces;
	private PredictwaybillColumns m_objPredictwaybillColumns;
	
	public void setParam(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces,
			PredictwaybillColumns objPredictwaybillColumns) 
	throws Exception {
		// ���������ܵ�
		setParam(strOperId, objFIAColumns, listCargo, listWaybillPieces);
		m_objPredictwaybillColumns = objPredictwaybillColumns;
	}	
	
	public void setParam(String strOperId,
			ForinputallColumns objFIAColumns, 
			List listCargo,
			List listWaybillPieces) 
	throws Exception {
		// ���������ܵ�
		m_objFIAColumns = objFIAColumns;
		m_strOperId = strOperId;
		m_listCargo = listCargo;
		m_listWaybillPieces = listWaybillPieces;
	}
	
	public String getSavedCwcode() {
		return m_strSavedCwcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		String strBwcodeArrival = "";
		if (StringUtility.isNull(m_objFIAColumns.getBwcode())) {
			SimplebatchwaybillColumns objSBWBColumns = BatchWayBillDemand.getLatestUnCompleteBatchwaybill(m_objFIAColumns.getChncode_Cwcuschn(), 
					m_objFIAColumns.getCocode(), 
					m_objFIAColumns.getEecode(), 
					m_objFIAColumns.getAdddate(), 
					"A");
			if (objSBWBColumns != null &&
					!StringUtility.isNull(objSBWBColumns.getBwbwcode())) {
				strBwcodeArrival = objSBWBColumns.getBwbwcode();
			} else {
				// ����
				JSaveBWTrans objJSaveBWTrans = new JSaveBWTrans();
				objJSaveBWTrans.setArrivalParam(m_objFIAColumns.getCocode(), 
						m_objFIAColumns.getChncode_Cwcuschn(), 
						m_objFIAColumns.getAdddate(), 
						m_objFIAColumns.getEecode(), 
						m_strOperId, 
						null);
				objJSaveBWTrans.transaction(objSession);
				strBwcodeArrival = objJSaveBWTrans.getNewBwcode();
			}
		} else {
			// ������ڵ���������ʹ�ô�����
			strBwcodeArrival = m_objFIAColumns.getBwcode();
		}
		// �����˵�����
		JSaveCoreWayBillTrans objJSCWBTrans = new JSaveCoreWayBillTrans();
		objJSCWBTrans.setParam(m_strOperId, strBwcodeArrival, m_objFIAColumns);
		objJSCWBTrans.transaction(objSession);
		m_strSavedCwcode = objJSCWBTrans.getNewCwcode();
		// �����ֵ���
		JSaveHousewaybillTrans objJSHWBTrans = new JSaveHousewaybillTrans();
		objJSHWBTrans.setParam(m_strOperId, 
				objJSCWBTrans.getNewCwcode(), 
				objJSCWBTrans.getSavedCorewaybillTR(), 
				m_objFIAColumns);
		objJSHWBTrans.transaction(objSession);
		// ���������
		JSaveCWBPTrans objJSaveCWBPTrans = new JSaveCWBPTrans();
		objJSaveCWBPTrans.setParam(m_listWaybillPieces, objJSCWBTrans.getNewCwcode());
		objJSaveCWBPTrans.transaction(objSession);			
		// ������Ʊ��Ϣ
		if (m_listCargo != null && m_listCargo.size() > 0) {
			JSaveCargoInfoTrans objJSaveCargoInfoTrans = new JSaveCargoInfoTrans();
			objJSaveCargoInfoTrans.setParam(m_listCargo, 
					objJSCWBTrans.getNewCwcode());
			objJSaveCargoInfoTrans.transaction(objSession);
		}
		// ����Ԥ����Ϣ
		if (m_objPredictwaybillColumns != null) {
			JModifyInputPWTrans objJModifyInputPWTrans = new JModifyInputPWTrans();
			objJModifyInputPWTrans.setUploadParam(objJSCWBTrans.getNewCwcode(), 
					m_objPredictwaybillColumns.getPwbpwb_code(), 
					m_strOperId, 
					objJSCWBTrans.getSavedCorewaybillTR());
			objJModifyInputPWTrans.transaction(objSession);
			
			m_objPredictwaybillColumns.setPwbspwbs_code("CHU");
			m_objPredictwaybillColumns.setPwbspwbs_name("���ϴ�");
			m_objPredictwaybillColumns.setPwbpwb_serverewbcode(objJSCWBTrans.getSavedCorewaybillTR().getCw_serverewbcode());
			m_objPredictwaybillColumns.setPwbcw_code(objJSCWBTrans.getNewCwcode());
		}
	}
}
