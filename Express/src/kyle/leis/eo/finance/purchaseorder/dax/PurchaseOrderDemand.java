package kyle.leis.eo.finance.purchaseorder.dax;

import java.util.List;

import kyle.common.util.jlang.DateFormatUtility;
import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.purchaseorder.da.PurchasefeeCondition;
import kyle.leis.eo.finance.purchaseorder.da.PurchasefeeQuery;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderCondition;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderQuery;
import kyle.leis.eo.finance.purchaseorder.da.PurchasepayableColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchasepayableCondition;
import kyle.leis.eo.finance.purchaseorder.da.PurchasepayableQuery;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillCondition;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillQuery;
import kyle.leis.eo.finance.purchaseorder.da.WaybillpayableCondition;
import kyle.leis.eo.finance.purchaseorder.da.WaybillpayableQuery;
import kyle.leis.fs.dictionary.dictionarys.da.TdiSimplestatusDC;
import kyle.leis.hi.TdiSimplestatus;
import kyle.leis.hi.TfiPurchaseorder;
@SuppressWarnings("unchecked")
public class PurchaseOrderDemand {
	/**
	 * ����PoId��ѯ���㵥��Ϣ
	 * @param strPoId
	 * @return
	 * @throws Exception
	 */
	public static PurchaseorderColumns load(String strPoId) throws Exception {
		PurchaseorderCondition objPurchaseorderCondition = new PurchaseorderCondition();
		PurchaseorderQuery objPurchaseorderQuery = new PurchaseorderQuery();
		objPurchaseorderCondition.setPoid(strPoId);
		objPurchaseorderQuery.setCondition(objPurchaseorderCondition);
		List objList = objPurchaseorderQuery.getResults();
		if (objList == null || objList.size() < 1) 
			return null;
		return (PurchaseorderColumns) objList.get(0);
	}
	/**
	 * ����������ѯ���㵥
	 * @param objCondition
	 * @return
	 * @throws Exception
	 */
	public static List query(PurchaseorderCondition objCondition) throws Exception {
		PurchaseorderQuery objPurchaseorderQuery = new PurchaseorderQuery();
		objPurchaseorderQuery.setCondition(objCondition);
		List objList = objPurchaseorderQuery.getResults();
		if (objList == null || objList.size() < 1) 
			return null;
		return  objList;
	}
	
    /**
     * �����˵���š����������ѯ��Ӧ���˵�Id
     * @param strCwCode
     * @param strFkCode
     * @return
     * @throws Exception
     */
    public static PurchasepayableColumns queryPurchasePayable(String strCwCode,
    		String strFkCode) throws Exception{
		
		PurchasepayableQuery objPurchasepayableQuery = new PurchasepayableQuery();
		PurchasepayableCondition objPurchasepayableCondition  = new PurchasepayableCondition();
		objPurchasepayableCondition.setCwcode(strCwCode);
		objPurchasepayableCondition.setFkcode(strFkCode);
		objPurchasepayableQuery.setCondition(objPurchasepayableCondition);
		List objList = objPurchasepayableQuery.getResults();
		if(objList == null || objList.size() == 0)
			return null;	
		return (PurchasepayableColumns) objList.get(0);
	}
	/**
	 * ����PoId��ѯ��Ӧ���˵�����
	 * @param strPoId
	 * @return
	 * @throws Exception
	 */
	public static List queryPurchaseWaybill(String strPoId) throws Exception{
		
		PurchasewaybillQuery objPurchasewaybillQuery = new PurchasewaybillQuery();
		PurchasewaybillCondition objPurchasewaybillCondition  = new PurchasewaybillCondition();
		objPurchasewaybillCondition.setPoid(strPoId);
		objPurchasewaybillQuery.setCondition(objPurchasewaybillCondition);
		List objList = objPurchasewaybillQuery.getResults();
		if(objList == null || objList.size() == 0)
			return null;
		return objList;
	}
	
	/**
	 * ����PoId��ѯ��Ӧ�ķ�������
	 * @param strPoId
	 * @return
	 * @throws Exception
	 */
    public static List queryPurchaseFee(String strPoId) throws Exception{
		
		PurchasefeeQuery objPurchasefeeQuery = new PurchasefeeQuery();
		PurchasefeeCondition objPurchasefeeCondition  = new PurchasefeeCondition();
		objPurchasefeeCondition.setPoid(strPoId);
		objPurchasefeeQuery.setCondition(objPurchasefeeCondition);
		List objList = objPurchasefeeQuery.getResults();
		if(objList == null || objList.size() == 0)
			return null;
		return objList;
	}
	
    public static List queryWaybillPayable (String strPoId) throws Exception{
		
	    WaybillpayableQuery objPurchasewaybillQuery = new WaybillpayableQuery();
	    WaybillpayableCondition objPurchasewaybillCondition  = new WaybillpayableCondition();
		objPurchasewaybillCondition.setPoid(strPoId);
		objPurchasewaybillQuery.setCondition(objPurchasewaybillCondition);
		List objList = objPurchasewaybillQuery.getResults();
		if(objList == null || objList.size() == 0)
			return null;
		return objList;
	}
	public static void setPurchaseOrderByColumns(TfiPurchaseorder objTfiPurchaseorder,
			PurchaseorderColumns objPurchaseorderColumns) throws Exception{
			
		objTfiPurchaseorder.setPoOccurdate(DateFormatUtility.getStandardDate(objPurchaseorderColumns.getPopo_occurdate()));
		if(!StringUtility.isNull(objPurchaseorderColumns.getPoss_code())){
			TdiSimplestatus objTdiSimplestatus = TdiSimplestatusDC.loadByKey(objPurchaseorderColumns.getPoss_code());
			objTfiPurchaseorder.setTdiSimplestatus(objTdiSimplestatus);
		}
	}
}
