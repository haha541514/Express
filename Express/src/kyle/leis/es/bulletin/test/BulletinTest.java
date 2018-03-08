package kyle.leis.es.bulletin.test;

import java.math.BigDecimal;
import java.util.List;

import kyle.leis.es.bulletin.da.BulletinColumns;
import kyle.leis.es.bulletin.da.BulletinCondition;
import kyle.leis.es.bulletin.dax.BulletinDemand;
import kyle.leis.es.bulletin.tp.SaveBulletinTrans;

public class BulletinTest {
	public static void main(String[] args) {
		try {
			//save();
			testStock();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public static void testStock() throws Exception {
		BigDecimal objSum = new BigDecimal("0");
		BigDecimal objRunning = new BigDecimal("19");
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 300; i++) {
			sb.append(objRunning.toString() + " + ");
			objSum = objSum.add(objRunning);
			objRunning = objRunning.add(new BigDecimal("-0.2"));
			if (objRunning.compareTo(new BigDecimal("13")) <= 0)
				break;
		}
		System.out.println(objSum.toString());
		System.out.println(sb.toString());
	}
	
	
	public static void save() throws Exception {
		SaveBulletinTrans objSaveBulletinTrans = new SaveBulletinTrans();
		BulletinCondition objBLCondition = new BulletinCondition();
		objBLCondition.setBlid("32481");
		List listResults = BulletinDemand.query(objBLCondition);
		
		objSaveBulletinTrans.setParam((BulletinColumns)listResults.get(0), "0");
		objSaveBulletinTrans.execute();
	}
}
