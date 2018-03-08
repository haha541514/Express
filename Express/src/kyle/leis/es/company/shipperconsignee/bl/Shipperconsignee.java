package kyle.leis.es.company.shipperconsignee.bl;

import kyle.leis.es.company.shipperconsignee.da.ShipperconsigneeColumns;
import kyle.leis.es.company.shipperconsignee.dax.ShipperconsigneeDemand;
import kyle.leis.es.company.shipperconsignee.tp.DeleteShipperconsigneeTrans;
import kyle.leis.es.company.shipperconsignee.tp.SaveShipperconsigneeTrans;

public class Shipperconsignee {
	public ShipperconsigneeColumns save(ShipperconsigneeColumns objSCColumns) throws Exception {
		SaveShipperconsigneeTrans objSSCTrans = new SaveShipperconsigneeTrans();
		objSSCTrans.setParam(objSCColumns);
		objSSCTrans.execute();
		// ·µ»ØÖµ
		String strScode = objSSCTrans.getSavedSCCode();
		return ShipperconsigneeDemand.load(strScode);
	}
	
	public void delete(String strSCCode) throws Exception {
		DeleteShipperconsigneeTrans objDSCTrans = new DeleteShipperconsigneeTrans();
		objDSCTrans.setParam(strSCCode);
		objDSCTrans.execute();

	}
}
