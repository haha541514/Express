package kyle.leis.eo.operation.predictwaybill.tp;

import java.math.BigDecimal;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.table.TableAccess;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.da.ToppredictwaybillTR;
import kyle.leis.eo.operation.predictwaybill.dax.PredictSequence;
import kyle.leis.eo.operation.predictwaybill.dax.PredictwaybillDemand;

public class SavePredictwaybillTrans extends AbstractTransaction {

	private PredictwaybillColumns m_objPredictwaybillColumns;
	private List m_listCargoinfo;
	private String m_strOperID;

	public void setParam(PredictwaybillColumns objPredictwaybillColumns,
			List listCargoinfo, String strOperID) {
		m_objPredictwaybillColumns = objPredictwaybillColumns;
		m_listCargoinfo = listCargoinfo;
		m_strOperID = strOperID;
	}

	public PredictwaybillColumns getSavedPredictwaybillColumns() {
		return m_objPredictwaybillColumns;
	}

	public void transaction(Session objSession) throws Exception {
		TableAccess objTableAccess = new TableAccess(objSession.connection());
		ToppredictwaybillTR objToppredictwaybillTR = new ToppredictwaybillTR();
		if (StringUtility.isNull(m_objPredictwaybillColumns.getPwbpwb_code())) {
			objToppredictwaybillTR.setOp_id_creator(m_strOperID);
			objToppredictwaybillTR.setPwb_createdate(DateFormatUtility.getStandardSysdate());
			PredictSequence objPredictSequence = new PredictSequence();
			objToppredictwaybillTR.setPwb_code(objPredictSequence.getNewSequencecode());
		} else {
			objToppredictwaybillTR.setPwb_codeCondition(m_objPredictwaybillColumns.getPwbpwb_code());
		}
		objToppredictwaybillTR.setPwb_modifydate(DateFormatUtility.getStandardSysdate());
		objToppredictwaybillTR.setOp_id_modifier(m_strOperID);
		PredictwaybillDemand.setPredictwaybill(m_objPredictwaybillColumns,
				objToppredictwaybillTR, 
				objSession);
		// 品名
		if (m_listCargoinfo != null && m_listCargoinfo.size() > 0) {
			PredictcargoinfoColumns objPCIColumns = (PredictcargoinfoColumns) m_listCargoinfo.get(0);

			if (StringUtility.isNull(objPCIColumns.getPcipci_totalprice())) {
				BigDecimal objTotalprice = new BigDecimal(objPCIColumns.getPcipci_unitprice()).multiply(new BigDecimal(objPCIColumns.getPcipci_pieces()));
				objToppredictwaybillTR.setPwb_cargoamount(objTotalprice.divide(new BigDecimal("1"), 2, 2).toString());
			} else {
				objToppredictwaybillTR.setPwb_cargoamount(objPCIColumns.getPcipci_totalprice());
			}
			if (!StringUtility.isNull(objPCIColumns.getPcipci_name())) {
				objToppredictwaybillTR.setPwb_cargoename(objPCIColumns.getPcipci_ename()
						+ "|" + objPCIColumns.getPcipci_name());
			} else {
				objToppredictwaybillTR.setPwb_cargoename(objPCIColumns.getPcipci_ename());
			}
			objToppredictwaybillTR.setPwb_cargopieces(objPCIColumns.getPcipci_pieces());
		}
		if (StringUtility.isNull(m_objPredictwaybillColumns.getPwbpwb_code())) {
			objTableAccess.insertRecord(objToppredictwaybillTR);
			m_objPredictwaybillColumns.setPwbpwb_code(objToppredictwaybillTR.getPwb_code());
		} else {
			objTableAccess.updateRecord(objToppredictwaybillTR);
			m_objPredictwaybillColumns.setPwbpwb_code(m_objPredictwaybillColumns.getPwbpwb_code());
		}
		// 保存申报品名等
		SaveCargoinfoTrans objSCT = new SaveCargoinfoTrans();
		objSCT.setParam(m_listCargoinfo, m_objPredictwaybillColumns
				.getPwbpwb_code());
		objSCT.transaction(objSession);
	}

}
