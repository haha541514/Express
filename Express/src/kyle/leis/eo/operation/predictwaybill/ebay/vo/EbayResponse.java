package kyle.leis.eo.operation.predictwaybill.ebay.vo;


public class EbayResponse {
	private String Timestamp;
	private String Ack;
	private String Version;
	private String Build;
	private Errors Errors;

	public String getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(String timestamp) {
		Timestamp = timestamp;
	}

	public String getAck() {
		return Ack;
	}

	public void setAck(String ack) {
		Ack = ack;
	}

	public String getVersion() {
		return Version;
	}

	public void setVersion(String version) {
		Version = version;
	}

	public String getBuild() {
		return Build;
	}

	public void setBuild(String build) {
		Build = build;
	}

	public Errors getErrors() {
		return Errors;
	}

	public void setErrors(Errors Errors) {
		this.Errors = Errors;
	}

}
