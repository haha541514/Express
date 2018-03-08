package kyle.leis.eo.operation.predictwaybill.ebay.vo;

public class RequesterCredentials {
	private String eBayAuthToken;

	public RequesterCredentials() {
	}

	public RequesterCredentials(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}

	public String geteBayAuthToken() {
		return eBayAuthToken;
	}

	public void seteBayAuthToken(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}

}
