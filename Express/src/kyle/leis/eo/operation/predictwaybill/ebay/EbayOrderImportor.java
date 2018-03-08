package kyle.leis.eo.operation.predictwaybill.ebay;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractOrderImportor;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.OrderImportorInput;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.RequesterCredentials;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.GetOrdersRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetOrdersResponse;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Order;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.ShippingAddress;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Transaction;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.TransactionPrice;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Variation;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class EbayOrderImportor extends AbstractOrderImportor {

	@Override
	protected List<OrderImportorInput> getImportorInputs(String startdate,
			String enddate, String accessToken, String pkCode, String coCode, String cawtId) throws Exception {
		List<OrderImportorInput> importorInputs = new ArrayList<OrderImportorInput>();
		GetOrdersRequest request = new GetOrdersRequest();
		request.setRequesterCredentials(new RequesterCredentials(accessToken));
		request.setCreateTimeFrom(startdate);
		request.setCreateTimeTo(enddate);
		request.setOrderStatus("Completed");
		List<GetOrdersResponse> ordersResponses = EbayUtil.getOrdersResponses(request);
		for (GetOrdersResponse  ordersResponse : ordersResponses) {
			List<Order> orders = ordersResponse.getOrderArray();
			for (Order order : orders) {
				OrderImportorInput importorInput = new OrderImportorInput();
				// 设置订单信息
				PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();
				predictwaybillColumns.setPkpk_code(pkCode);
				predictwaybillColumns.setCoco_code(coCode);
				setOrder(order, predictwaybillColumns);
				// 设置物品信息
				List<PredictcargoinfoColumns> listCargoInfo = new ArrayList<PredictcargoinfoColumns>();
				PredictcargoinfoColumns cargoInfo = new PredictcargoinfoColumns();
				List<Transaction> transactions = order.getTransactionArray();
				for (Transaction transaction : transactions) {
					setCargoInfo(transaction, cargoInfo);
				}
				listCargoInfo.add(cargoInfo);
				
				importorInput.setPredictwaybillColumns(predictwaybillColumns);
				importorInput.setListCargoInfo(listCargoInfo);
				importorInputs.add(importorInput);
			}
		}
		return importorInputs;
	}


	/**
	 * 设置订单信息
	 * @param order
	 * @param predictwaybillColumns
	 */
	private void setOrder(Order order, PredictwaybillColumns predictwaybillColumns) {
		predictwaybillColumns.setPwbpwb_createdate(order.getCreatedTime());
		predictwaybillColumns.setPwbpwb_modifydate(order.getCheckoutStatus().getLastModifiedTime());
		predictwaybillColumns.setPwbpwb_customremark("");
		predictwaybillColumns.setPwbpwb_orderid(order.getOrderID());
		ShippingAddress shippingAddress = order.getShippingAddress();
		try {
			predictwaybillColumns.setDtdt_code(DistrictDemand.getDtcodeByHubcode(shippingAddress.getCountry()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		predictwaybillColumns.setPwbpwb_consigneename(shippingAddress.getName());
		predictwaybillColumns.setPwbpwb_consigneetel(shippingAddress.getPhone());
		predictwaybillColumns.setPwbpwb_consigneeaddress1(shippingAddress.getStreet1());
		predictwaybillColumns.setPwbpwb_consigneeaddress2(shippingAddress.getStreet2());
		predictwaybillColumns.setPwbpwb_consigneecity(shippingAddress.getCityName());
		predictwaybillColumns.setPwbpwb_consigneestate(shippingAddress.getStateOrProvince());
		predictwaybillColumns.setPwbpwb_consigneepostcode(shippingAddress.getPostalCode());
	}
	
	/**
	 * 设置物品信息
	 * @param transaction
	 * @param cargoInfo
	 */
	private void setCargoInfo(Transaction transaction, PredictcargoinfoColumns cargoInfo) {
		TransactionPrice transactionPrice = transaction.getTransactionPrice();
		cargoInfo.setPcick_code(transactionPrice.getCurrency());
		cargoInfo.setPcipci_name(transaction.getItem().getTitle());
		cargoInfo.setPcipci_ename(transaction.getItem().getTitle());
		cargoInfo.setPcipci_pieces(String.valueOf(transaction.getQuantityPurchased()));
		cargoInfo.setPcipci_unitprice(String.valueOf(transactionPrice.getValue()));
		BigDecimal totalPrice = new BigDecimal(transaction.getQuantityPurchased())
				.multiply(new BigDecimal(transactionPrice.getValue()));
		cargoInfo.setPcipci_totalprice(totalPrice.divide(BigDecimal.ONE, 
				3, RoundingMode.HALF_UP).toString());
		Variation variation = transaction.getVariation();
		if (variation != null) {
			cargoInfo.setPcipci_hscode(variation.getSKU());
		}
	}
}
