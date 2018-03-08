package kyle.leis.eo.operation.housewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.SimplecorewaybillColumns;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.corewaybill.dax.CorewaybillDemand;
import kyle.leis.eo.operation.corewaybillpieces.da.TopcorewaybillpiecesTR;
import kyle.leis.eo.operation.housewaybill.dax.DHLConnectImportColumns;

public class SaveCNDHLImportDataTrans extends AbstractTransaction {

	private List m_listDHLConnectImport;
	private String m_strOperID;
	
	public void setParam(List listDHLConnectImport,
			String strOperID) {
		m_listDHLConnectImport = listDHLConnectImport;
		m_strOperID = strOperID;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listDHLConnectImport == null || m_listDHLConnectImport.size() < 1)
			return;
		DHLConnectImportColumns objDCIColumns = (DHLConnectImportColumns)m_listDHLConnectImport.get(0);
		if (StringUtility.isNull(objDCIColumns.getReference())) {
			throw (new Exception ("�ͻ����Ų���Ϊ��"));
		}
		// ��ѯ�˵�����
		SimplecorewaybillColumns objSCWBColumns = CorewaybillDemand.loadSimpleCorewaybill(objDCIColumns.getReference(), "", true);
		if (objSCWBColumns == null || 
				StringUtility.isNull(objSCWBColumns.getCwcw_code())) {
			throw (new Exception ("ϵͳ������" + objDCIColumns.getReference() + "�Ŀͻ�����"));
		}
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		// ��ѯ������
		TopcorewaybillpiecesTR objTCWPTR = new TopcorewaybillpiecesTR();
		objTCWPTR.setCw_codeCondition(objSCWBColumns.getCwcw_code());
		List listPieces = objTableAccess.selectRecords(objTCWPTR, 1000);
		if (listPieces == null || 
				listPieces.size() != m_listDHLConnectImport.size()) {
			throw (new Exception ("ϵͳ����Ϊ" + listPieces.size() + "���������ݵļ���Ϊ" + m_listDHLConnectImport.size() + 
					",������ƥ��"));
		}
		// �޸��ӵ�
		for (int i = 0; i < listPieces.size(); i++) {
			TopcorewaybillpiecesTR objTopcorewaybillpiecesTR = (TopcorewaybillpiecesTR)listPieces.get(i);
			objDCIColumns = (DHLConnectImportColumns)m_listDHLConnectImport.get(i);
			
			TopcorewaybillpiecesTR objUpdateCWPTR = new TopcorewaybillpiecesTR();
			objUpdateCWPTR.setCw_codeCondition(objTopcorewaybillpiecesTR.getCw_code());
			objUpdateCWPTR.setCp_idCondition(objTopcorewaybillpiecesTR.getCp_id());
			objUpdateCWPTR.setCp_labelcode(objDCIColumns.getPieceid());
			objTableAccess.updateRecord(objUpdateCWPTR);
		}
		// �޸�����
		TopcorewaybillTR objModifyTCWTR = new TopcorewaybillTR();
		objModifyTCWTR.setCw_codeCondition(objSCWBColumns.getCwcw_code());
		objModifyTCWTR.setCw_serverewbcode(objDCIColumns.getAwbno());
		objModifyTCWTR.setCw_op_id_modifier(m_strOperID);
		objModifyTCWTR.setCw_modifydate(DateFormatUtility.getStandardSysdate());
		objTableAccess.updateRecord(objModifyTCWTR);
	}

}
