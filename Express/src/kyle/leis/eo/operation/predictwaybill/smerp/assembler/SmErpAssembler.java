package kyle.leis.eo.operation.predictwaybill.smerp.assembler;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.da.PredictcargoinfoColumns;
import kyle.leis.eo.operation.predictwaybill.da.PredictwaybillColumns;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Order;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Product;
import kyle.leis.eo.operation.predictwaybill.smerp.vo.Receiver;
import kyle.leis.fs.dictionary.district.dax.DistrictDemand;

public class SmErpAssembler {
	
	public static PredictwaybillColumns toPredictwaybill(Order order){
		PredictwaybillColumns predictwaybillColumns = new PredictwaybillColumns();
		predictwaybillColumns.setPwbpwb_createdate(order.getCreateTime());
		predictwaybillColumns.setPwbpwb_modifydate(order.getCreateTime());
		predictwaybillColumns.setPwbpwb_cargoename(order.getCustomsName());
		predictwaybillColumns.setPwbck_code(order.getCurrencyType());
		predictwaybillColumns.setPwbpwb_customremark(order.getRemark());
		predictwaybillColumns.setPwbpwb_orderid(order.getPlatformOrderID());
		predictwaybillColumns.setPwbpwb_chargeweight(new BigDecimal(order.getWeight())
				.divide(new BigDecimal(1000), 3, RoundingMode.HALF_UP).toString());
		Receiver receiver = order.getReceiver();
		try {
			predictwaybillColumns.setDtdt_code(DistrictDemand.getDtcodeByHubcode(receiver.getCountryCode()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		predictwaybillColumns.setPwbpwb_consigneename(receiver.getName());
		String tel = receiver.getTel();
		if (StringUtility.isNull(tel)) {
			tel = receiver.getMobile();
		}
		predictwaybillColumns.setPwbpwb_consigneetel(StringUtility.replaceWhenNull(tel, "0000000"));
		predictwaybillColumns.setPwbpwb_consigneeaddress1(receiver.getAddress());
		predictwaybillColumns.setPwbpwb_consigneecity(receiver.getCity());
		predictwaybillColumns.setPwbpwb_consigneestate(receiver.getProvince());
		predictwaybillColumns.setPwbpwb_consigneepostcode(receiver.getPostCode());
		return predictwaybillColumns;
	}
	
	public static PredictcargoinfoColumns toPredictcargoinfo(Product product){
		PredictcargoinfoColumns cargoInfo = new PredictcargoinfoColumns();
		cargoInfo.setPcick_code(StringUtility.replaceWhenNull(product.getCurrencyType(), "USD"));
		cargoInfo.setPcipci_name(product.getCustomsCnName());
		cargoInfo.setPcipci_ename(product.getCustomsName());
		cargoInfo.setPcipci_pieces(String.valueOf(product.getQuantity()));
		BigDecimal unitPrice = new BigDecimal(product.getDeclareValue())
				.divide(new BigDecimal(product.getQuantity()), 3, RoundingMode.HALF_UP);
		cargoInfo.setPcipci_unitprice(unitPrice.toString());
		cargoInfo.setPcipci_totalprice(String.valueOf(product.getDeclareValue()));
		cargoInfo.setPcipci_weight(String.valueOf(product.getWeight()));
		cargoInfo.setPcipci_hscode(product.getHSCode());
		cargoInfo.setPcipci_attacheinfo(product.getSKU());
		return cargoInfo;
	}
	
	
	private SmErpAssembler(){}
}
