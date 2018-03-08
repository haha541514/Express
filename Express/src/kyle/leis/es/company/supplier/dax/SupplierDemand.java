package kyle.leis.es.company.supplier.dax;

import java.util.List;

import kyle.leis.fs.cachecontainer.da.SupplierColumns;
import kyle.leis.fs.cachecontainer.da.SupplierCondition;
import kyle.leis.fs.cachecontainer.da.SupplierQuery;

public class SupplierDemand {
	/**
	 * ���Manifest����
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static String getManifestSeriesNumber(String strCocode) 
	throws Exception  {
		SupplierCondition objSupplierCondition = new SupplierCondition();
		objSupplierCondition.setCocode(strCocode);
		objSupplierCondition.setUseCacheSign(true);
		
		SupplierQuery objSupplierQuery = new SupplierQuery();
		objSupplierQuery.setCondition(objSupplierCondition);
		List objList = objSupplierQuery.getResults();
		if (objList == null || objList.size() < 1) return null;
		
		SupplierColumns objSupplierColumns = (SupplierColumns)objList.get(0);
		return objSupplierColumns.getSpmanifestseriesnumber();
	}
	
	/**
	 * ��ѯ
	 * @param objSPCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(SupplierCondition objSPCondition) throws Exception {
		kyle.leis.es.company.supplier.da.SupplierQuery objSupplierQuery = new kyle.leis.es.company.supplier.da.SupplierQuery();
		objSupplierQuery.setCondition(objSPCondition);
		return objSupplierQuery.getResults();
	}
	
	/**
	 * װ�ؼ�¼
	 * @param strCocode
	 * @return
	 * @throws Exception
	 */
	public static List load(String strCocode) throws Exception {
		kyle.leis.es.company.supplier.da.SupplierQuery objSupplierQuery = new kyle.leis.es.company.supplier.da.SupplierQuery();
		objSupplierQuery.setCocode(strCocode);
		return objSupplierQuery.getResults();		
	}
}
