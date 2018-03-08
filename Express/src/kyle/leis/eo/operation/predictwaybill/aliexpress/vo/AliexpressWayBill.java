package kyle.leis.eo.operation.predictwaybill.aliexpress.vo;

import java.util.List;

/** ∑÷“≥∂©µ• **/
public class AliexpressWayBill {
	private int totalItem;
	private List<AliexpressOrder> orderList;
	
	private String error_code;
	private String error_message;

	public int getTotalItem() {
		return totalItem;
	}

	public void setTotalItem(int totalItem) {
		this.totalItem = totalItem;
	}

	public List<AliexpressOrder> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<AliexpressOrder> orderList) {
		this.orderList = orderList;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String errorCode) {
		error_code = errorCode;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String errorMessage) {
		error_message = errorMessage;
	}

}
