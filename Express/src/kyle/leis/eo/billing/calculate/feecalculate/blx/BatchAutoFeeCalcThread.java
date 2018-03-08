package kyle.leis.eo.billing.calculate.feecalculate.blx;

import java.util.List;
import java.util.logging.Logger;

import kyle.leis.eo.billing.calculate.feecalculate.bl.AutoFeeCalculate;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class BatchAutoFeeCalcThread extends Thread {
	private static Logger s_objLogger = Logger.getLogger(AutoFeeCalculateThread.class.getName());
	
	public void run() {
		try {
			HousewaybillCondition objHWBCondition = new HousewaybillCondition();
			objHWBCondition.setStartsignindate("2010-03-01");
			objHWBCondition.setNotcwscode("EL");
			List objList = HousewaybillDemand.query(objHWBCondition);
			if (objList == null || objList.size() < 1)
				return;
			for (int i = 0; i < objList.size(); i++) {
				HousewaybillColumns objHWBColumns = (HousewaybillColumns)objList.get(i);
				try {
					AutoFeeCalculate objAutoFeeCalculate = new AutoFeeCalculate();
					objAutoFeeCalculate.calcReceivable(objHWBColumns.getHwcwcode(), "");
				} catch (Exception ex) {
					ex.printStackTrace();
					s_objLogger.warning(objHWBColumns.getCcococode() + "<>" +
							ex.getMessage());
				}
			}
		} catch(Exception ex) {
			s_objLogger.warning(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
