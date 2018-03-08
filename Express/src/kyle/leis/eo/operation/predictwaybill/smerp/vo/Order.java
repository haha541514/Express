package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.List;

public class Order {
	private String Platform;
	private String PlatformOrderID;
	private String CreateTime;
	private String WaybillNumber;
	private String WaybillCompany;
	private String Remark;
	private Receiver Receiver;
	private List<Product> ProductList;
	private String DeliveryExpires;
	private String ShopName;
	private String SellerName;
	private String OrderRemark;
	private int ItemCount;
	private String CustomsName;
	private String CustomsNameCN;
	private double CustomsValue;
	private String CurrencyType;
	private double Weight;
	private String MailType;
	private String Channel;

	public String getPlatform() {
		return Platform;
	}

	public void setPlatform(String platform) {
		Platform = platform;
	}

	public String getPlatformOrderID() {
		return PlatformOrderID;
	}

	public void setPlatformOrderID(String platformOrderID) {
		PlatformOrderID = platformOrderID;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getWaybillNumber() {
		return WaybillNumber;
	}

	public void setWaybillNumber(String waybillNumber) {
		WaybillNumber = waybillNumber;
	}

	public String getWaybillCompany() {
		return WaybillCompany;
	}

	public void setWaybillCompany(String waybillCompany) {
		WaybillCompany = waybillCompany;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

	public Receiver getReceiver() {
		return Receiver;
	}

	public void setReceiver(Receiver receiver) {
		Receiver = receiver;
	}

	public List<Product> getProductList() {
		return ProductList;
	}

	public void setProductList(List<Product> productList) {
		ProductList = productList;
	}

	public String getDeliveryExpires() {
		return DeliveryExpires;
	}

	public void setDeliveryExpires(String deliveryExpires) {
		DeliveryExpires = deliveryExpires;
	}

	public String getShopName() {
		return ShopName;
	}

	public void setShopName(String shopName) {
		ShopName = shopName;
	}

	public String getSellerName() {
		return SellerName;
	}

	public void setSellerName(String sellerName) {
		SellerName = sellerName;
	}

	public String getOrderRemark() {
		return OrderRemark;
	}

	public void setOrderRemark(String orderRemark) {
		OrderRemark = orderRemark;
	}

	public int getItemCount() {
		return ItemCount;
	}

	public void setItemCount(int itemCount) {
		ItemCount = itemCount;
	}

	public String getCustomsName() {
		return CustomsName;
	}

	public void setCustomsName(String customsName) {
		CustomsName = customsName;
	}

	public String getCustomsNameCN() {
		return CustomsNameCN;
	}

	public void setCustomsNameCN(String customsNameCN) {
		CustomsNameCN = customsNameCN;
	}

	public double getCustomsValue() {
		return CustomsValue;
	}

	public void setCustomsValue(double customsValue) {
		CustomsValue = customsValue;
	}

	public double getWeight() {
		return Weight;
	}

	public void setWeight(double weight) {
		Weight = weight;
	}

	public String getMailType() {
		return MailType;
	}

	public void setMailType(String mailType) {
		MailType = mailType;
	}

	public String getChannel() {
		return Channel;
	}

	public void setChannel(String channel) {
		Channel = channel;
	}

	public String getCurrencyType() {
		return CurrencyType;
	}

	public void setCurrencyType(String currencyType) {
		CurrencyType = currencyType;
	}
}
