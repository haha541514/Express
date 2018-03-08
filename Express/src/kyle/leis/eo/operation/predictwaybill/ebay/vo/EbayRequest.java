package kyle.leis.eo.operation.predictwaybill.ebay.vo;

import com.thoughtworks.xstream.annotations.XStreamAsAttribute;


public class EbayRequest {
	private RequesterCredentials RequesterCredentials;
	@XStreamAsAttribute
	private String xmlns = "urn:ebay:apis:eBLBaseComponents";
	
	public RequesterCredentials getRequesterCredentials() {
		return RequesterCredentials;
	}

	public void setRequesterCredentials(RequesterCredentials requesterCredentials) {
		RequesterCredentials = requesterCredentials;
	}

	public String getXmlns() {
		return xmlns;
	}

	public void setXmlns(String xmlns) {
		this.xmlns = xmlns;
	}

}
