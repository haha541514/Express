package kyle.leis.eo.operation.corewaybill.tp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import kyle.common.dbaccess.transaction.AbstractTransaction;
import kyle.leis.eo.operation.corewaybillpieces.da.CorewaybillpiecesColumns;
import kyle.leis.eo.operation.corewaybillpieces.dax.CorewaybillpiecesDemand;
import kyle.leis.eo.operation.housewaybill.da.ForinputallColumns;
import kyle.leis.eo.operation.housewaybill.da.ForinputallCondition;
import kyle.leis.eo.operation.housewaybill.da.InputAllQReturn;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
import kyle.leis.eo.operation.housewaybill.dax.PredictOrderDemand;
import kyle.leis.eo.operation.housewaybill.tp.InputAllTransaction;
import net.sf.hibernate.Session;

public class SimpleBQInputTrans extends AbstractTransaction {

	private String m_strNewServerEwbcode;
	private String m_strCwcode;
	private String m_strOperId;
	
	public void setParam(String strCwcode,
			String strNewServerEwbcode, 
			String strOperId) {
		m_strCwcode = strCwcode;
		m_strNewServerEwbcode = strNewServerEwbcode;
		m_strOperId = strOperId;
	}
	
	
	public void transaction(Session objSession) throws Exception {
		ForinputallCondition objFInputAllC = new ForinputallCondition();
		objFInputAllC.setCwcode(m_strCwcode);		
		InputAllQReturn objIAR = HousewaybillDemand.queryInput(objFInputAllC);		
		ForinputallColumns objFIC = (ForinputallColumns)objIAR.getHWBResults().get(0);
		List listWaybillPieces = CorewaybillpiecesDemand.load(m_strCwcode);
		// 重新计算重量和渠道
		objFIC.setCwgrossweight(new BigDecimal(objFIC.getCwgrossweight()));
		objFIC.setCwcustomerchargeweight(new BigDecimal(objFIC.getCwgrossweight()));
		objFIC.setCwserverewbcode(m_strNewServerEwbcode);
		objFIC.setCwcode(Long.parseLong(m_strCwcode));
		objFIC.setCwscode("IP");
		PredictOrderDemand.buildChargeweightAndChannel(objFIC, new ArrayList<CorewaybillpiecesColumns>());
		// 保存
		InputAllTransaction objIAT = new InputAllTransaction();
		objIAT.setParam(m_strOperId, 
				objFIC, 
				objIAR.getCargoInfoResults(),
				listWaybillPieces);
		objIAT.transaction(objSession);				
	}

}
