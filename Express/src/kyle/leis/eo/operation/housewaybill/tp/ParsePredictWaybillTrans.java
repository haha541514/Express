package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.eo.operation.corewaybillcode.tp.SaveCorewaybillcodeTrans;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderColumnsEX;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;

public class ParsePredictWaybillTrans extends AbstractTransaction {

	private String m_strCwcode;
	private List<PredictOrderColumnsEX> m_listCargoInfo;
	private String m_strOperId;
	
	public void setParam(String strCwcode, 
			List<PredictOrderColumnsEX> listCargoInfo,
			String strOperId) {
		m_strCwcode = strCwcode;
		m_listCargoInfo = listCargoInfo;
		m_strOperId = strOperId;
	}
	
	public void transaction(Session objSession) throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(m_strCwcode);
		
		InputAllQReturn objIAR = HousewaybillDemand.queryInput(objFInputAllC);
		ForinputallColumns objFIC = (ForinputallColumns)objIAR.getHWBResults().get(0);
		// 件信息
		List listWaybillPieces = CorewaybillpiecesDemand.load(m_strCwcode);
		List listCorewaybillcode = CorewaybillcodeDemand.load(m_strCwcode);
		
		for (int i = 0; i < m_listCargoInfo.size(); i++) {
			PredictOrderColumnsEX objPOCEX = m_listCargoInfo.get(i);
			WaybillforpredictColumns objWFPC = objPOCEX.getWaybillforpredict();
			if (i > 0) {
				objFIC.setCwcode(null);
				String strEwbcode = PredictOrderDemand.buildEwbcode();
				objFIC.setCwserverewbcode(strEwbcode);
				objFIC.setCwewbcode(strEwbcode);					
				
			}
			objFIC.setCwgrossweight(new BigDecimal(objWFPC.getCwcw_customerchargeweight()));
			objFIC.setCwcustomerchargeweight(new BigDecimal(objWFPC.getCwcw_customerchargeweight()));
			PredictOrderDemand.buildChargeweightAndChannel(objFIC, new ArrayList<CorewaybillpiecesColumns>());
			// 重新设置体积重
			List listRebuildPieces = PredictOrderDemand.rebuildWaybillpieces(listWaybillPieces,
					objWFPC.getCwcw_customerchargeweight());
			InputAllTransaction objIAT = new InputAllTransaction();
			objIAT.setParam(m_strOperId, 
					objFIC, 
					objPOCEX.getListCargoInfo(), 
					listRebuildPieces);
			objIAT.transaction(objSession);
			if (i > 0) {
				SaveCorewaybillcodeTrans objSCWBCT = new SaveCorewaybillcodeTrans();
				objSCWBCT.setParam(listCorewaybillcode, objIAT.getSavedCwcode());
				objSCWBCT.transaction(objSession);
			}
		}
	}
}
