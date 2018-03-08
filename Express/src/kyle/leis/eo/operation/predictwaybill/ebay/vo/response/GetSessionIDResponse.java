package kyle.leis.eo.operation.predictwaybill.ebay.vo.response;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayResponse;

public class GetSessionIDResponse extends EbayResponse {
	private String SessionID;

	public String getSessionID() {
		return SessionID;
	}

	public void setSessionID(String sessionID) {
		SessionID = sessionID;
	}

}
