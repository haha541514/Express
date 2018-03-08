package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.List;

public class PushRequest {
	private String ApiUserName;
	private String ApiToken;
	private String AccessToken;
	private String CallbackUrl;
	private List<Order> OrderList;

	public String getApiUserName() {
		return ApiUserName;
	}

	public void setApiUserName(String apiUserName) {
		ApiUserName = apiUserName;
	}

	public String getApiToken() {
		return ApiToken;
	}

	public void setApiToken(String apiToken) {
		ApiToken = apiToken;
	}

	public String getAccessToken() {
		return AccessToken;
	}

	public void setAccessToken(String accessToken) {
		AccessToken = accessToken;
	}

	public String getCallbackUrl() {
		return CallbackUrl;
	}

	public void setCallbackUrl(String callbackUrl) {
		CallbackUrl = callbackUrl;
	}

	public List<Order> getOrderList() {
		return OrderList;
	}

	public void setOrderList(List<Order> orderList) {
		OrderList = orderList;
	}

}
