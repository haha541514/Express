package kyle.leis.eo.operation.corewaybillpieces.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.fs.dictionary.dictionarys.da.TdiCorewaybillstatusDC;
import kyle.leis.hi.TdiCorewaybillstatus;
import kyle.leis.hi.TopCorewaybill;
import kyle.leis.hi.TopCorewaybillpiece;
import kyle.leis.hi.TopCorewaybillpiecePK;

public class SaveCWBPiecesTransaction extends AbstractTransaction {
	private List m_listCWPieces;
	private TopCorewaybill m_objTopCorewaybill;
	
	public void setParam(List listCWPieces,
			TopCorewaybill objCoreWayBill) {
		m_objTopCorewaybill = objCoreWayBill;
		m_listCWPieces = listCWPieces;
	}

	public void transaction(Session objSession) throws Exception {
		if (m_listCWPieces == null || m_listCWPieces.size() < 1)
			return;
		if (m_objTopCorewaybill == null || m_objTopCorewaybill.getCwCode() == null)
			return;
		// ÏÈÉ¾³ý
		objSession.delete(" from TopCorewaybillpiece as cp where cp.comp_id.cwCode = " + 
				String.valueOf(m_objTopCorewaybill.getCwCode()));
		for (int i = 0; i < m_listCWPieces.size(); i++) {
			CorewaybillpiecesColumns objCWPColumns = (CorewaybillpiecesColumns)m_listCWPieces.get(i);
			TopCorewaybillpiece objTopCorewaybillpiece = new TopCorewaybillpiece(); 
			// ÉèÖÃÖ÷¼ü
			TopCorewaybillpiecePK objCPPK = new TopCorewaybillpiecePK();
			objCPPK.setCpId(i);
			objCPPK.setCwCode(m_objTopCorewaybill.getCwCode());
			objTopCorewaybillpiece.setComp_id(objCPPK);
			
			objTopCorewaybillpiece.setCpLabelcode(objCWPColumns.getCpcplabelcode());
			objTopCorewaybillpiece.setCpBaglabelcode(objCWPColumns.getCpcpbaglabelcode());
			objTopCorewaybillpiece.setCpSibaglabelcode(objCWPColumns.getCpcpsibaglabelcode());
			objTopCorewaybillpiece.setCpGrossweight(new BigDecimal(objCWPColumns.getCpcpgrossweight()));
			objTopCorewaybillpiece.setCpHeight(new BigDecimal(objCWPColumns.getCpcpheight()));
			objTopCorewaybillpiece.setCpLength(new BigDecimal(objCWPColumns.getCpcplength()));
			objTopCorewaybillpiece.setCpWidth(new BigDecimal(objCWPColumns.getCpcpwidth()));
			objTopCorewaybillpiece.setCpBarcodelabelcode(objCWPColumns.getCpcpbarcodelabelcode());
			objTopCorewaybillpiece.setCpSelflabelcode(objCWPColumns.getCpcpselflabelcode());
			if (!StringUtility.isNull(objCWPColumns.getCwscwscode())) {
				TdiCorewaybillstatus objTCWS = TdiCorewaybillstatusDC.loadByKey(objCWPColumns.getCwscwscode());
				objTopCorewaybillpiece.setTdiCorewaybillstatus(objTCWS);
			}
			objTopCorewaybillpiece.setTopCorewaybill(m_objTopCorewaybill);
			objSession.save(objTopCorewaybillpiece);
		}
	}
	
	
	
}
