package kyle.leis.eo.operation.predictwaybill.aliexpress;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressOrder;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressProduct;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressReceiptInfo;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressWayBill;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractOrderImportor;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.OrderImportorInput;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class AliOrderImportor extends AbstractOrderImportor {

	@Override
	protected List<OrderImportorInput> getImportorInputs(String startdate,
			String enddate, String accessToken, String pkCode, String coCode, String cawtId) throws Exception{
		List<OrderImportorInput> importorInputs = new ArrayList<OrderImportorInput>();
		String[] appKey = getAppKey(cawtId);
		Aliexpress aliexpress = new Aliexpress(appKey[0], appKey[1]);
		List<AliexpressWayBill> aliexpressWayBills = aliexpress.queryAliexpressOrder(startdate, enddate, accessToken);
		for (AliexpressWayBill aliexpressWayBill : aliexpressWayBills) {
			List<AliexpressOrder> aliexpressOrders = aliexpressWayBill.getOrderList();
			for (AliexpressOrder aliexpressOrder : aliexpressOrders) {
				OrderImportorInput importorInput = new OrderImportorInput();
				// 设置订单信息
				PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();
				predictwaybillColumns.setPkpk_code(pkCode);
				predictwaybillColumns.setCoco_code(coCode);
				AliexpressReceiptInfo receiptInfo = aliexpress.queryReceiptInfo(accessToken, 
						aliexpressOrder.getOrderId());
				setOrder(aliexpressOrder, receiptInfo, predictwaybillColumns);
				// 设置物品信息
				List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
				List<AliexpressProduct> aliexpressProducts = aliexpressOrder.getProductList();
				for (AliexpressProduct product : aliexpressProducts) {
					PredictcargoinfoColumns cargoInfo = new PredictcargoinfoColumns();
					setCargoInfo(product, cargoInfo);
					listCargoInfo.add(cargoInfo);
				}
				importorInput.setPredictwaybillColumns(predictwaybillColumns);
				importorInput.setListCargoInfo(listCargoInfo);
				importorInputs.add(importorInput);
			}
		}
		return importorInputs;
	}

	/**
	 * @param cawtId
	 * @return
	 * @throws Exception
	 */
	private String[] getAppKey(String cawtId) throws Exception {
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCawtcawtid(cawtId);
		List<CustomerapiwebtokenColumns> list = CustomerApiWebDemand.query(condition);
		if (list.isEmpty()) {
			throw new Exception("Can not get appKey!");
		}
		String appAcount = list.get(0).getCawtcawtpassword();
		if (StringUtility.isNull(appAcount) || !appAcount.contains(",")) {
			throw new Exception("Can not get appKey!");
		}
		String[] appKey = appAcount.split(",");
		return appKey;
	}
	
	/**
	 * 设置订单信息
	 * @param aliexpressOrder
	 * @param predictwaybillColumns
	 */
	private void setOrder(AliexpressOrder aliexpressOrder, AliexpressReceiptInfo receiptInfo,
			PredictwaybillColumns predictwaybillColumns) {
		predictwaybillColumns.setPwbpwb_createdate(aliexpressOrder.getGmtCreate());
		predictwaybillColumns.setPwbpwb_modifydate(aliexpressOrder.getGmtModified());
		predictwaybillColumns.setPwbpwb_customremark(aliexpressOrder.getMemo());
		predictwaybillColumns.setPwbpwb_orderid(aliexpressOrder.getOrderId());
		try {
			predictwaybillColumns.setDtdt_code(DistrictDemand.getDtcodeByHubcode(receiptInfo.getCountry()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		predictwaybillColumns.setPwbpwb_consigneename(receiptInfo.getContactPerson());
		predictwaybillColumns.setPwbpwb_consigneetel(receiptInfo.getPhoneArea() + receiptInfo.getPhoneNumber());
		predictwaybillColumns.setPwbpwb_consigneeaddress1(receiptInfo.getDetailAddress());
		predictwaybillColumns.setPwbpwb_consigneeaddress2(receiptInfo.getAddress2());
		predictwaybillColumns.setPwbpwb_consigneecity(receiptInfo.getCity());
		predictwaybillColumns.setPwbpwb_consigneestate(receiptInfo.getProvince());
		predictwaybillColumns.setPwbpwb_consigneepostcode(receiptInfo.getZip());
	}
	
	/**
	 * 设置商品信息
	 * @param product
	 * @param cargoInfo
	 */
	private void setCargoInfo(AliexpressProduct product,
			PredictcargoinfoColumns cargoInfo) {
		cargoInfo.setPcick_code(product.getProductUnitPriceCur());
		cargoInfo.setPcipci_name(product.getProductName());
		cargoInfo.setPcipci_ename(product.getProductName());
		cargoInfo.setPcipci_pieces(product.getProductCount());
		cargoInfo.setPcipci_unitprice(product.getProductUnitPrice());
		BigDecimal totalPrice = new BigDecimal(product.getProductCount())
				.multiply(new BigDecimal(product.getProductUnitPrice()));
		cargoInfo.setPcipci_totalprice(totalPrice.divide(BigDecimal.ONE, 
				3, RoundingMode.HALF_UP).toString());
		cargoInfo.setPcipci_hscode(product.getSkuCode());
	}

}
