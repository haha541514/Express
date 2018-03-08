package kyle.leis.es.company.supplier.bl;

import java.util.List;

import kyle.leis.es.company.supplier.da.SupplierColumns;
import kyle.leis.es.company.supplier.dax.SupplierDemand;
import kyle.leis.es.company.supplier.tp.SaveSupplierTransaction;

public class Supplier {
	/**
	 * ±£´æ
	 * @param objSupplierColumns
	 * @param strOperId
	 * @throws Exception
	 */
	public List save(SupplierColumns objSupplierColumns,
			String strOperId) throws Exception {
		SaveSupplierTransaction objSSPTrans = new SaveSupplierTransaction();
		objSSPTrans.setParam(objSupplierColumns, strOperId);
		objSSPTrans.execute();
		
		String strCocode = objSSPTrans.getSavedCocode();
		return SupplierDemand.load(strCocode);
	}
	
}
