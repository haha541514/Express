package kyle.leis.eo.operation.housewaybill.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybill.da.TopcorewaybillTR;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.corewaybillpieces.tp.JSaveCWBPTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.TophousewaybillTR;

public class JSignoutTran extends AbstractTransaction {
	private HousewaybillColumns m_objHwColumns;
	private List m_listCWPieces;
	private String m_strOperId;
	private boolean m_isAllPiecesSignout;
	
	public void setSignOutParam(HousewaybillColumns objHwbColumns,
			String strBwcodeDeparture,
			List listCWPieces,
			String strOperId,
			String strSelfLabelcode) {
		objHwbColumns.setDbwbwcode(Long.parseLong(strBwcodeDeparture));
		boolean isAllPiecesSignout = true;
		if (!StringUtility.isNull(strSelfLabelcode)) {
			isAllPiecesSignout = CorewaybillpiecesDemand.isAllSignout(listCWPieces);
		}
		if (isAllPiecesSignout) {
			objHwbColumns.setCwscwscode(ICorewaybillBasicData.COREWAYBILL_STATUS_SIGNOUT);
			objHwbColumns.setHwhwsignoutdate(DateFormatUtility.getSysdate());
			objHwbColumns.setHwhwopidsignout(Long.parseLong(strOperId));
		}
		m_isAllPiecesSignout = isAllPiecesSignout;
		m_objHwColumns = objHwbColumns;
		m_strOperId = strOperId;
		m_listCWPieces = listCWPieces;
	}
	
	
	
	public void transaction(Session objSession) throws Exception {
		// TODO Auto-generated method stub
		if (m_objHwColumns == null || 
				StringUtility.isNull(m_objHwColumns.getHwcwcode()))
			return;
		if (m_isAllPiecesSignout) {
			TableAccess objTableAccess = new TableAccess(objSession.connection());
			
			TopcorewaybillTR objTopcorewaybillTR = new TopcorewaybillTR();
			objTopcorewaybillTR.setCw_codeCondition(m_objHwColumns.getHwcwcode());
			objTopcorewaybillTR.setCw_op_id_modifier(m_strOperId);
			objTopcorewaybillTR.setCw_modifydate(DateFormatUtility.getStandardSysdate());
			objTopcorewaybillTR.setCws_code(ICorewaybillBasicData.COREWAYBILL_STATUS_SIGNOUT);
			objTopcorewaybillTR.setBw_code_departure(m_objHwColumns.getDbwbwcode());
			objTableAccess.updateRecord(objTopcorewaybillTR);
			
			TophousewaybillTR objTophousewaybillTR = new TophousewaybillTR();
			objTophousewaybillTR.setCw_codeCondition(m_objHwColumns.getHwcwcode());
			objTophousewaybillTR.setHw_op_id_signout(m_strOperId);
			objTophousewaybillTR.setHw_signoutdate(DateFormatUtility.getStandardSysdate());
			objTableAccess.updateRecord(objTophousewaybillTR);
		}
		// 保存件信息
		JSaveCWBPTrans objJSCWBPT = new JSaveCWBPTrans();
		objJSCWBPT.setParam(m_listCWPieces, m_objHwColumns.getHwcwcode());
		objJSCWBPT.transaction(objSession);		
	}
	

}
