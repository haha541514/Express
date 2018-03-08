package kyle.leis.eo.operation.predictwaybill.aliexpress.vo;

import java.util.List;

public class AliexpressOrder {
	private String gmtModified; // 修改时间
	private String gmtCreate; // 创建时间
	private String memo; // 订单备注
	private List<AliexpressProduct> productList;
	private String bizType; // 订单类型
	private String orderStatus; // 订单状态
	private String orderId; // 订单ID

	public String getGmtModified() {
		return gmtModified;
	}

	public void setGmtModified(String gmtModified) {
		this.gmtModified = gmtModified;
	}

	public String getGmtCreate() {
		return gmtCreate;
	}

	public void setGmtCreate(String gmtCreate) {
		this.gmtCreate = gmtCreate;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public List<AliexpressProduct> getProductList() {
		return productList;
	}

	public void setProductList(List<AliexpressProduct> productList) {
		this.productList = productList;
	}

	public String getBizType() {
		return bizType;
	}

	public void setBizType(String bizType) {
		this.bizType = bizType;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
