package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public class RedirectParam {
	private String cawtId; // ������
	private String reAuth; // ������Ȩ��ʶ
	private String apiwebType; // ��������
	private String storeName; // ��������
	private String clientID; // �������˺�
	private String appSecret; // ��������Կ

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
