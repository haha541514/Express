package kyle.leis.eo.operation.predictwaybill.aliexpress.vo;

import java.util.List;

public class AliexpressOrder {
	private String gmtModified; // �޸�ʱ��
	private String gmtCreate; // ����ʱ��
	private String memo; // ������ע
	private List<AliexpressProduct> productList;
	private String bizType; // ��������
	private String orderStatus; // ����״̬
	private String orderId; // ����ID

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
