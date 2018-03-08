package kyle.leis.eo.operation.housewaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import net.sf.hibernate.Session;
import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.cargoinfo.dax.CargoInfoDemand;
import kyle.leis.eo.operation.corewaybillcode.dax.CorewaybillcodeDemand;
import kyle.leis.eo.operation.corewaybillcode.tp.SaveCorewaybillcodeTrans;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpredictColumns;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.specialtype.tp.SaveSingleSpecialtypeTrans;

public class MergePredictWaybillTrans extends AbstractTransaction {

	private String[] m_astrCwcode;
	private String m_strOperId;
	
	public void setParam(String[] astrCwcode,
			String strOperId) throws Exception {
		m_astrCwcode = astrCwcode;
		m_strOperId = strOperId;
	}
	
	
	
	@SuppressWarnings("unchecked")
	public void transaction(Session objSession) throws Exception {
		if (m_astrCwcode == null || m_astrCwcode.length < 1)
			return;
		if (m_astrCwcode.length < 1)
			return;
		List listDestCargoInfo = CargoInfoDemand.queryByCwCode(m_astrCwcode[0]);
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(m_astrCwcode[0]);		
		InputAllQReturn objIAR = HousewaybillDemand.queryInput(objFInputAllC);
		ForinputallColumns objFIC = (ForinputallColumns)objIAR.getHWBResults().get(0);		
		List listWaybillPieces = CorewaybillpiecesDemand.load(m_astrCwcode[0]);
		
		BigDecimal objGrossweight = new BigDecimal(objFIC.getCwgrossweight());
		SaveCorewaybillcodeTrans objSCCT = new SaveCorewaybillcodeTrans();
		boolean isAlonecustom = false;
		for (int i = 1; i < m_astrCwcode.length; i++) {
			String strCwcode = m_astrCwcode[i];
			List listSourceCargoInfo = CargoInfoDemand.queryByCwCode(strCwcode);
			PredictOrderDemand.merge(listSourceCargoInfo, listDestCargoInfo);
			WaybillforpredictColumns objWFPC = HousewaybillDemand.loadForPredict(strCwcode);
			objGrossweight = objGrossweight.add(new BigDecimal(objWFPC.getCwcw_customerchargeweight()));
			// 将源运单作废
			SaveHousewaybillTrans objSHT = new SaveHousewaybillTrans();
			objSHT.setCWStatusParam(strCwcode, "EL", m_strOperId);
			objSHT.transaction(objSession);
			// 原订单号
			List listCWBCodeResults = CorewaybillcodeDemand.load(strCwcode);
			objSCCT.setParam(listCWBCodeResults, objFIC.getCwcustomerewbcode(), 
					objWFPC.getCwcw_customerewbcode(), m_astrCwcode[0]);
			if (objWFPC.getAlonecustomsign().equals("Y"))
				isAlonecustom = true;
		}
		
		// 重新计算重量和渠道
		objFIC.setCwgrossweight(objGrossweight);
		objFIC.setCwcustomerchargeweight(objGrossweight);
		objFIC.setCwcode(Long.parseLong(m_astrCwcode[0]));
		PredictOrderDemand.buildChargeweightAndChannel(objFIC, new ArrayList<CorewaybillpiecesColumns>());
		
		// 重新设置体积重
		List listRebuildPieces = PredictOrderDemand.rebuildWaybillpieces(listWaybillPieces,
				objGrossweight.toString());		
		
		InputAllTransaction objIAT = new InputAllTransaction();
		objIAT.setParam(m_strOperId, 
				objFIC, 
				listDestCargoInfo, 
				listRebuildPieces);
		objIAT.transaction(objSession);		
		// 保存原客户订单号
		objSCCT.transaction(objSession);
		// 单独报关
		if (isAlonecustom) {
			SaveSingleSpecialtypeTrans objSSSTTrans = new SaveSingleSpecialtypeTrans();
			objSSSTTrans.setParam(m_astrCwcode[0], 
					"A0101", 
					m_strOperId, 
					"网上合并订单后重设单独报关");
			objSSSTTrans.transaction(objSession);
		}
			
			
	}
}
