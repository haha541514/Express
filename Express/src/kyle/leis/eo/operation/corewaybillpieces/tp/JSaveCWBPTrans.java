package kyle.leis.eo.operation.corewaybillpieces.tp;

import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.da.TopcorewaybillpiecesTR;

public class JSaveCWBPTrans extends AbstractTransaction {
	private List m_listCWPieces;
	private String m_strCwcode;
	
	public void setParam(List listCWPieces,
			String strCwcode) {
		m_strCwcode = strCwcode;
		m_listCWPieces = listCWPieces;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_listCWPieces == null || m_listCWPieces.size() < 1)
			return;
		if (StringUtility.isNull(m_strCwcode))
			return;
		// 先删除
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		TopcorewaybillpiecesTR objTCWPTR = new TopcorewaybillpiecesTR();
		objTCWPTR.setCw_codeCondition(m_strCwcode);
		objTableAccess.deleteRecord(objTCWPTR);
		// 新增
		for (int i = 0; i < m_listCWPieces.size(); i++) {
			CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)m_listCWPieces.get(i);
			objTCWPTR = new TopcorewaybillpiecesTR();
			// 设置主键
			objTCWPTR.setCp_id(String.valueOf(i));
			objTCWPTR.setCw_code(m_strCwcode);
			
			objTCWPTR.setCp_labelcode(objCWPColumns.getCpcplabelcode());
			objTCWPTR.setCp_baglabelcode(objCWPColumns.getCpcpbaglabelcode());
			objTCWPTR.setCp_sibaglabelcode(objCWPColumns.getCpcpsibaglabelcode());
			objTCWPTR.setCp_grossweight(objCWPColumns.getCpcpgrossweight());
			objTCWPTR.setCp_height(objCWPColumns.getCpcpheight());
			objTCWPTR.setCp_length(objCWPColumns.getCpcplength());
			objTCWPTR.setCp_width(objCWPColumns.getCpcpwidth());
			objTCWPTR.setCp_barcodelabelcode(objCWPColumns.getCpcpbarcodelabelcode());
			objTCWPTR.setCp_selflabelcode(objCWPColumns.getCpcpselflabelcode());
			if (!StringUtility.isNull(objCWPColumns.getCwscwscode())) {
				objTCWPTR.setCws_code(objCWPColumns.getCwscwscode());
			}
			objTableAccess.insertRecord(objTCWPTR);
		}
	}
}
