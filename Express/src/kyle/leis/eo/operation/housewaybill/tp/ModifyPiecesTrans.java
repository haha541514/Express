package kyle.leis.eo.operation.housewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.corewaybillpieces.tp.JSaveCWBPTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.TophousewaybillTR;

public class ModifyPiecesTrans extends AbstractTransaction {
	
	private HousewaybillColumns m_objHwColumns;
	private List m_listCorewaybillpieces;
	private String m_strOperId;
	private boolean m_isChangeServerHawb;
	
	public void setParam(HousewaybillColumns objHwColumns,
			List listCorewaybillpieces,
			String strOperId,
			boolean isChangeServerHawb) {
		m_objHwColumns = objHwColumns;
		m_listCorewaybillpieces = listCorewaybillpieces;
		m_strOperId = strOperId;
		m_isChangeServerHawb = isChangeServerHawb;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		
		TopcorewaybillTR objTopcorewaybillTR = new TopcorewaybillTR();		
		// 修改corewaybill
		objTopcorewaybillTR.setCw_grossweight(m_objHwColumns.getCwcwgrossweight());
		objTopcorewaybillTR.setCw_chargeweight(m_objHwColumns.getCwcwchargeweight());
		objTopcorewaybillTR.setCw_op_id_modifier(m_strOperId);
		objTopcorewaybillTR.setCw_modifydate(DateFormatUtility.getStandardSysdate());
		objTopcorewaybillTR.setCw_pieces(String.valueOf(m_listCorewaybillpieces.size()));
		objTopcorewaybillTR.setCw_transferpieces(String.valueOf(m_listCorewaybillpieces.size()));
		if (m_isChangeServerHawb) {
			objTopcorewaybillTR.setChn_code_supplier(null);
			// 修改housewaybill的换单标记
			TophousewaybillTR objTophousewaybillTR = new TophousewaybillTR();
			objTophousewaybillTR.setHw_serverewbchangedsign("N");
			
			objTophousewaybillTR.setCw_codeCondition(m_objHwColumns.getHwcwcode());
			objTableAccess.updateRecord(objTophousewaybillTR);
		}
		objTopcorewaybillTR.setCw_codeCondition(m_objHwColumns.getHwcwcode());
		objTableAccess.updateRecord(objTopcorewaybillTR);
		// 修改件信息
		JSaveCWBPTrans objJSaveCWBPTrans = new JSaveCWBPTrans();
		objJSaveCWBPTrans.setParam(m_listCorewaybillpieces, 
				m_objHwColumns.getHwcwcode());
		objJSaveCWBPTrans.transaction(objSession);				
	}

}
