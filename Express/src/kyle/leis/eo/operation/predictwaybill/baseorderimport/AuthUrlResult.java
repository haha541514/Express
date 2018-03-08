package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public class AuthUrlResult {
	private String url;
	private String tempAuthCode;

	public AuthUrlResult() {
	}

	public AuthUrlResult(String url) {
		this.url = url;
	}

	public AuthUrlResult(String url, String tempAuthCode) {
		this.url = url;
		this.tempAuthCode = tempAuthCode;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTempAuthCode() {
		return tempAuthCode;
	}

	public void setTempAuthCode(String tempAuthCode) {
		this.tempAuthCode = tempAuthCode;
	}

}
