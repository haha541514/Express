package kyle.leis.eo.operation.corewaybillpieces.tp;

import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.hi.TopCorewaybill;

public class SaveServerLabelcodeTrans extends AbstractTransaction {
	private List m_listCWPieces;
	private String m_strCwcode;
	private String m_strServerEwbcode;
	
	public void setParam(List listCWPieces,
			String strCwcode,
			String strServerEwbcode) {
		m_strCwcode = strCwcode;
		m_listCWPieces = listCWPieces;
		m_strServerEwbcode = strServerEwbcode;
	}
	
	public void transaction(Session objSession) throws Exception {
		if (m_listCWPieces == null || m_listCWPieces.size() < 1)
			return;
		// 查询原件数据
		List listCorewaybillpieces = CorewaybillpiecesDemand.load(m_strCwcode);
		if (listCorewaybillpieces == null || 
				listCorewaybillpieces.size() != m_listCWPieces.size())
			return;
		for (int i = 0; i < listCorewaybillpieces.size(); i++) {
			CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)listCorewaybillpieces.get(i);
			objCWPColumns.setCpcplabelcode(((CorewaybillpiecesColumns)m_listCWPieces.get(i)).getCpcplabelcode());
		}
		TopCorewaybill objTopCorewaybill = (TopCorewaybill)objSession.load(TopCorewaybill.class, 
				Long.parseLong(m_strCwcode));
		// 保存子单数据
		SaveCWBPiecesTransaction objSCWBPT = new SaveCWBPiecesTransaction();
		objSCWBPT.setParam(listCorewaybillpieces, objTopCorewaybill);
		objSCWBPT.transaction(objSession);
		// 保存服务商单号
		if (!StringUtility.isNull(m_strServerEwbcode)) {
			objTopCorewaybill.setCwServerewbcode(m_strServerEwbcode);
			objSession.save(objTopCorewaybill);
		}
	}

}
