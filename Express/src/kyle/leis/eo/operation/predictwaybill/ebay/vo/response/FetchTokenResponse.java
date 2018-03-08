package kyle.leis.eo.operation.predictwaybill.ebay.vo.response;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayResponse;

public class FetchTokenResponse extends EbayResponse {
	private String eBayAuthToken;
	private String HardExpirationTime; // TokenÓÐÐ§ÆÚ

	public String geteBayAuthToken() {
		return eBayAuthToken;
	}

	public void seteBayAuthToken(String eBayAuthToken) {
		this.eBayAuthToken = eBayAuthToken;
	}

	public String getHardExpirationTime() {
		return HardExpirationTime;
	}

	public void setHardExpirationTime(String hardExpirationTime) {
		HardExpirationTime = hardExpirationTime;
	}

}
