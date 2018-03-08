package kyle.leis.eo.finance.purchaseorder.sv;

import java.util.List;

import kyle.common.connectors.servlet.AService;
import kyle.common.connectors.util.Decoder;
import kyle.common.connectors.util.Encoder;
import kyle.leis.eo.finance.purchaseorder.bl.PurchaseOrder;
import kyle.leis.eo.finance.purchaseorder.da.PurchasefeeColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderColumns;
import kyle.leis.eo.finance.purchaseorder.da.PurchaseorderCondition;
import kyle.leis.eo.finance.purchaseorder.da.PurchasewaybillColumns;
import kyle.leis.eo.finance.purchaseorder.dax.PurchaseOrderDemand;
import kyle.leis.eo.operation.housewaybill.da.HousewaybillforreportColumns;
import kyle.leis.eo.operation.housewaybill.da.WaybillforpurchaseColumns;

@SuppressWarnings("unchecked")
public class PurchaseOrderService extends AService {

	/**
	 * 保存
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String save(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 4, this);
		PurchaseorderColumns objPurchaseorderColumns = (PurchaseorderColumns) objPD.getParameter(0, 
				PurchaseorderColumns.class);
		List listPurchaseOrderWaybill = objPD.getParameterList(1,PurchasewaybillColumns.class);
		List listPurchaseOrderFee = objPD.getParameterList(2,PurchasefeeColumns.class);
		String strOperId = (String) objPD.getParameter(3, String.class);

		PurchaseOrder objPurchaseOrder = new PurchaseOrder();
		PurchaseorderColumns objColumns = objPurchaseOrder.save(objPurchaseorderColumns, 
				listPurchaseOrderWaybill,
				listPurchaseOrderFee, 
				strOperId);

		List<PurchasewaybillColumns> listWaybill = objPurchaseOrder.queryWaybill(objColumns.getPopo_id());
		List<PurchasefeeColumns> listFee = PurchaseOrderDemand.queryPurchaseFee(objColumns.getPopo_id());

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objColumns);
		objEncode.addParameter(listWaybill);
		objEncode.addParameter(listFee);
		return objEncode.toString();

	}

	/**
	 * 作废购汇单
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String delete(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strPoId = (String) objPD.getParameter(0, String.class);
		PurchaseOrder objPurchaseOrder = new PurchaseOrder();
		objPurchaseOrder.delete(strPoId, "OFF");
		return "";
	}

	/**
	 * 加载购汇单对应信息
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String load(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strPoId = (String) objPD.getParameter(0, String.class);
		PurchaseorderColumns objColumns = PurchaseOrderDemand.load(strPoId);
		PurchaseOrder objPurchaseOrder = new PurchaseOrder();
		List<HousewaybillforreportColumns> listWaybill = objPurchaseOrder.queryWaybill(strPoId);
		List<PurchasefeeColumns> listFee = PurchaseOrderDemand.queryPurchaseFee(strPoId);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objColumns);
		objEncode.addParameter(listWaybill);
		objEncode.addParameter(listFee);
		return objEncode.toString();

	}

	/**
	 * 根据条件查询购汇单信息
	 * 
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String query(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		PurchaseorderCondition objCondition = (PurchaseorderCondition) objPD.getParameter(0, 
				PurchaseorderCondition.class);
		List<PurchaseorderColumns> listPurchaseOrder = PurchaseOrderDemand.query(objCondition);

		Encoder objEncode = new Encoder();
		objEncode.addParameter(listPurchaseOrder);
		return objEncode.toString();
	}
	
	/**
	 * 查询购汇单应付费用信息
	 * 
	 * @param objPD
	 * @return
	 * @throws Exception
	 */
	public String queryOrderWaybill(Decoder objPD) throws Exception {
		checkParameterCount(objPD, 1, this);
		String strPoId = (String) objPD.getParameter(0, String.class);
		PurchaseorderColumns objColumns = PurchaseOrderDemand.load(strPoId);
		PurchaseOrder objPurchaseOrder = new PurchaseOrder();
		List<WaybillforpurchaseColumns> listWaybill = objPurchaseOrder.queryWaybillforpurchase(strPoId);		

		Encoder objEncode = new Encoder();
		objEncode.addParameter(objColumns);
		objEncode.addParameter(listWaybill);
		return objEncode.toString();
	}

}
