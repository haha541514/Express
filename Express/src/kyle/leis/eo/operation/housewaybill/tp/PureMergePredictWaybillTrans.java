package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybillcode.tp.SaveCorewaybillcodeTrans;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;

public class PureMergePredictWaybillTrans extends AbstractTransaction {
	private PredictOrderColumnsEX m_objDestPOCEX;
	private PredictOrderColumnsEX m_objSourcePOCEX;
	private String m_strOperId;
	
	public void setParam(PredictOrderColumnsEX objDestPOCEX,
			PredictOrderColumnsEX objSourcePOCEX,
			String strOperId) throws Exception {
		m_objDestPOCEX = objDestPOCEX;
		m_objSourcePOCEX = objSourcePOCEX;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		// �ϲ�����
		BigDecimal objSourceGrossweight = new BigDecimal(m_objSourcePOCEX.getWaybillforpredict().getCwcw_customerchargeweight());
		BigDecimal objDestGrossweight = new BigDecimal(m_objDestPOCEX.getWaybillforpredict().getCwcw_customerchargeweight());
		objDestGrossweight = objDestGrossweight.add(objSourceGrossweight);
		String strCwcode = m_objDestPOCEX.getWaybillforpredict().getCwcw_code();
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(strCwcode);		
		InputAllQReturn objIAR = HousewaybillDemand.queryInput(objFInputAllC);
		ForinputallColumns objFIC = (ForinputallColumns)objIAR.getHWBResults().get(0);		
		List listWaybillPieces = CorewaybillpiecesDemand.load(strCwcode);
		// ���¼�������������
		objFIC.setCwgrossweight(objDestGrossweight);
		objFIC.setCwcustomerchargeweight(objDestGrossweight);
		objFIC.setCwcode(Long.parseLong(strCwcode));
		PredictOrderDemand.buildChargeweightAndChannel(objFIC, new ArrayList<CorewaybillpiecesColumns>());
		
		List listRebuildWaybillPieces = PredictOrderDemand.rebuildWaybillpieces(listWaybillPieces, objDestGrossweight.toString());
		// ����
		InputAllTransaction objIAT = new InputAllTransaction();
		objIAT.setParam(m_strOperId, 
				objFIC, 
				m_objDestPOCEX.getListCargoInfo(), 
				listRebuildWaybillPieces);
		objIAT.transaction(objSession);		
		// ����ԭ�ͻ�������
		if (!m_objDestPOCEX.getWaybillforpredict().getCwcw_customerewbcode().equals(
				m_objSourcePOCEX.getWaybillforpredict().getCwcw_customerewbcode())) {
			SaveCorewaybillcodeTrans objSCCT = new SaveCorewaybillcodeTrans();
			objSCCT.setParam(strCwcode, new String[] { m_objSourcePOCEX.getWaybillforpredict().getCwcw_customerewbcode() });
			objSCCT.transaction(objSession);
		}
		
		
	}
	
}
