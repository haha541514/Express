package kyle.leis.eo.billing.calculate.feecalculate.test;

import java.util.List;
import java.util.logging.Logger;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.leis.eo.billing.calculate.feecalculate.bl.AutoFeeCalculate;
import kyle.leis.eo.operation.corewaybill.dax.ICorewaybillBasicData;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;

public class FeeCalculateTest {
	private static AutoFeeCalculate m_objAutoFeeCalculate = new AutoFeeCalculate();
	static Logger s_objLogger = Logger.getLogger(FeeCalculateTest.class.getName());
	
	public static void main(String[] args) {
		try {
			System.out.println(calcReceivable(true));
			// batchcalcReceivable();
			// recalculate();
		} catch (Exception ex) {
			ex.printStackTrace();
			System.out.println(ex.getMessage());
		}
	}
	
	public static String calcReceivable(boolean val) throws Exception {
		 // String strCwcode = "844719";
		 HousewaybillColumns objHWColumns = HousewaybillDemand.load("1100000", ICorewaybillBasicData.EWBCODE_TYPE_CUSTOMER);
		 m_objAutoFeeCalculate.calcReceivable(objHWColumns.getHwcwcode(), "");
		 //System.out.println(objHWColumns.getHwhwsignindate().substring(0, 19));
		 // m_objAutoFeeCalculate.calcServerpayable(objHWColumns.getHwcwcode(), "");
		 //m_objAutoFeeCalculate.recalculate(objHWColumns.getHwcwcode());
		 return objHWColumns.getHwcwcode();
		// return DateFormatUtility.getStandardSysdate().substring(0, 19);
	}
	
	public static String batchcalcReceivable() throws Exception {
		HousewaybillCondition objHWBCondition = new HousewaybillCondition();
		objHWBCondition.setStartsignindate("2010-03-01");
		objHWBCondition.setNotcwscode("EL");
		List objList = HousewaybillDemand.query(objHWBCondition);
		if (objList == null || objList.size() < 1)
			return "";
		for (int i = 0; i < objList.size(); i++) {
			HousewaybillColumns objHWBColumns = (HousewaybillColumns)objList.get(i);
			try {
				m_objAutoFeeCalculate.calcReceivable(objHWBColumns.getHwcwcode(), "");
			} catch (Exception ex) {
				ex.printStackTrace();
				s_objLogger.warning(objHWBColumns.getCcococode() + "<>" +
						ex.getMessage());
			}
		}
		return "";
	}
	
	public static String recalculate() throws Exception {
		String strCwcode = "58542";
		m_objAutoFeeCalculate.recalculate(strCwcode);
		return "";		
	}
}
