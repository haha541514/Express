package kyle.leis.eo.operation.predictwaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.cargoinfo.da.CargoinfoColumns;
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
import kyle.leis.eo.operation.housewaybill.tp.InputAllTransaction;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;

public class MergePredictOrderbillTrans extends AbstractTransaction {
	private WaybillforpredictColumns m_objDestPOCEX;
	private PredictwaybillColumns m_objSourcePOCEX;
	private String m_strOperId;
	private List<CargoinfoColumns> m_listCargoInfo;
	
	public void setParam(WaybillforpredictColumns objDestPOCEX,
			PredictwaybillColumns objSourcePOCEX,
			String strOperId,
			List<CargoinfoColumns> listCargoInfo) throws Exception {
		m_objDestPOCEX = objDestPOCEX;
		m_objSourcePOCEX = objSourcePOCEX;
		m_strOperId = strOperId;
		m_listCargoInfo=listCargoInfo;
	}
	
	public void transaction(Session objSession) throws Exception {
		// 合并重量
		BigDecimal objSourceGrossweight = new BigDecimal(m_objSourcePOCEX.getPwbpwb_chargeweight());
		BigDecimal objDestGrossweight = new BigDecimal(m_objDestPOCEX.getCwcw_chargeweight());
		objDestGrossweight = objDestGrossweight.add(objSourceGrossweight);
		String strCwcode = m_objDestPOCEX.getCwcw_code();
		
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(strCwcode);		
		InputAllQReturn objIAR = HousewaybillDemand.queryInput(objFInputAllC);
		ForinputallColumns objFIC = (ForinputallColumns)objIAR.getHWBResults().get(0);		
		List listWaybillPieces = CorewaybillpiecesDemand.load(strCwcode);
		// 重新计算重量和渠道
		objFIC.setCwgrossweight(objDestGrossweight);
		objFIC.setCwcustomerchargeweight(objDestGrossweight);
		objFIC.setCwcode(Long.parseLong(strCwcode));
		PredictOrderDemand.buildChargeweightAndChannel(objFIC, new ArrayList<CorewaybillpiecesColumns>());
		
		List listRebuildWaybillPieces = PredictOrderDemand.rebuildWaybillpieces(listWaybillPieces, objDestGrossweight.toString());
		// 保存
		InputAllTransaction objIAT = new InputAllTransaction();
		objIAT.setParam(m_strOperId, 
				objFIC, 
				m_listCargoInfo, 
				listRebuildWaybillPieces);
		objIAT.transaction(objSession);		
		// 保存原客户订单号
		if (!m_objDestPOCEX.getCwcw_customerewbcode().equals(
				m_objSourcePOCEX.getPwbpwb_orderid())) {
			SaveCorewaybillcodeTrans objSCCT = new SaveCorewaybillcodeTrans();
			objSCCT.setParam(strCwcode, new String[] { m_objSourcePOCEX.getPwbpwb_orderid() });
			objSCCT.transaction(objSession);
		}
	}
}
