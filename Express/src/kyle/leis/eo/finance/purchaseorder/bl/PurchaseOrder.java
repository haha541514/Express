package kyle.leis.eo.finance.purchaseorder.bl;

import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillColumns;
import kyle.leis.eo.finance.purchaseorder.dax.PurchaseOrderDemand;
import kyle.leis.eo.finance.purchaseorder.tp.DeletePurchaseOrderTrans;
import kyle.leis.eo.finance.purchaseorder.tp.DeleteWaybillFeeTrans;
import kyle.leis.eo.finance.purchaseorder.tp.SavePurchaseOrderTrans;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportColumns;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportCondition;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpurchaseColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpurchaseCondition;
import kyle.leis.eo.operation.housewaybill.dax.HousewaybillDemand;
@SuppressWarnings("unchecked")
public class PurchaseOrder {
	/**
	 * ���沢���ع��㵥��Ϣ
	 * @param objPurchaseorderColumns
	 * @param listPurchaseOrderWaybill
	 * @param listPurchaseOrderFee
	 * @param strOperId
	 * @return
	 * @throws Exception
	 */
	public PurchaseorderColumns save(PurchaseorderColumns objPurchaseorderColumns,
			List listPurchaseOrderWaybill,
			List listPurchaseOrderFee,
			String strOperId) throws Exception{
		//�޸�ʱ��ɾ��PurchaseWaybill��PurchaseFee
		if(!StringUtility.isNull(objPurchaseorderColumns.getPopo_id())){
			DeleteWaybillFeeTrans objDWFTrans = new DeleteWaybillFeeTrans();
			objDWFTrans.setParam(objPurchaseorderColumns.getPopo_id());
			objDWFTrans.execute();
		}
		//����
		SavePurchaseOrderTrans objSPOTrans = new SavePurchaseOrderTrans();
		objSPOTrans.setParam(objPurchaseorderColumns,listPurchaseOrderWaybill,listPurchaseOrderFee,strOperId);
		objSPOTrans.execute();
		//����ֵ
		String strPoId = String.valueOf(objSPOTrans.getPoId());
		PurchaseorderColumns objColumns = PurchaseOrderDemand.load(strPoId);		
		return objColumns;
	}
	
	/**
	 * ���Ϲ��㵥
	 * @param strPoId
	 * @param strSscode
	 * @throws Exception
	 */
	public void delete(String strPoId, 
			String strSscode) throws Exception{
		
		List objList = PurchaseOrderDemand.queryWaybillPayable(strPoId);
		DeletePurchaseOrderTrans objDPOTrans = new DeletePurchaseOrderTrans();
		objDPOTrans.setParam(strPoId, 
				strSscode,
				objList);
		objDPOTrans.execute();	
		
	}
	/**
	 * ��ѯ���㵥��Ϣ
	 * @param strPoId
	 * @return
	 * @throws Exception
	 */
	public List queryWaybill (String strPoId) throws Exception{
		
        List<PurchasewaybillColumns> objList = PurchaseOrderDemand.queryPurchaseWaybill(strPoId);		
		if(objList == null || objList.size() == 0)
			return null;
		List<HousewaybillforreportColumns> listHWB = new ArrayList();
		for(int i = 0;i<objList.size();i++){
			PurchasewaybillColumns objColumns = (PurchasewaybillColumns) objList.get(i);
			HousewaybillforreportCondition objHWBFRCondition = new HousewaybillforreportCondition();
			objHWBFRCondition.setCwcode(objColumns.getPowbcw_code());
			List listWaybill = HousewaybillDemand.queryForreport(objHWBFRCondition);
			if(listWaybill == null || listWaybill.size() == 0)
				continue;
			listHWB.add((HousewaybillforreportColumns) listWaybill.get(0));
		}
		return listHWB;		
	}	
	
	/**
	 * ��ѯ���㵥Ӧ������Ϣ
	 * @param strPoId
	 * @return
	 * @throws Exception
	 */
	public List queryWaybillforpurchase (String strPoId) throws Exception{
		
        List<PurchasewaybillColumns> objList = PurchaseOrderDemand.queryPurchaseWaybill(strPoId);		
		if(objList == null || objList.size() == 0)
			return null;
		List<WaybillforpurchaseColumns> listWBFB = new ArrayList();
		for(int i = 0;i<objList.size();i++){
			PurchasewaybillColumns objColumns = (PurchasewaybillColumns) objList.get(i);
			WaybillforpurchaseCondition objWBFPCondition = new WaybillforpurchaseCondition();
			objWBFPCondition.setCw_code(objColumns.getPowbcw_code());
			List listWaybill = HousewaybillDemand.queryForPurchase(objWBFPCondition);
			if(listWaybill == null || listWaybill.size() == 0)
				continue;
			listWBFB.add((WaybillforpurchaseColumns) listWaybill.get(0));
		}
		return listWBFB;		
	}	
}
