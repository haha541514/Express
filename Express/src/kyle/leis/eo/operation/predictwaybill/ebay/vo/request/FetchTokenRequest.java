package kyle.leis.eo.operation.predictwaybill.ebay.vo.request;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;

public class FetchTokenRequest extends EbayRequest {
	private String SecretID;
	private String SessionID;

	public String getSecretID() {
		return SecretID;
	}

	public void setSecretID(String secretID) {
		SecretID = secretID;
	}

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

}
