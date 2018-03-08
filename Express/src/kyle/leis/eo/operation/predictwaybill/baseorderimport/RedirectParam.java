package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public class RedirectParam {
	private String cawtId; // 网店编号
	private String reAuth; // 重新授权标识
	private String apiwebType; // 网店类型
	private String storeName; // 网店名称
	private String clientID; // 开发者账号
	private String appSecret; // 开发者密钥

	public RedirectParam() {
	}

	public RedirectParam(String cawtId, String reAuth, String apiwebType,
			String storeName) {
		this.cawtId = cawtId;
		this.reAuth = reAuth;
		this.apiwebType = apiwebType;
		this.storeName = storeName;
	}

	public String getCawtId() {
		return cawtId;
	}

	public void setCawtId(String cawtId) {
		this.cawtId = cawtId;
	}

	public String getReAuth() {
		return reAuth;
	}

	public void setReAuth(String reAuth) {
		this.reAuth = reAuth;
	}

	public String getApiwebType() {
		return apiwebType;
	}

	public void setApiwebType(String apiwebType) {
		this.apiwebType = apiwebType;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getClientID() {
		return clientID;
	}

	public void setClientID(String clientID) {
		this.clientID = clientID;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}

}
