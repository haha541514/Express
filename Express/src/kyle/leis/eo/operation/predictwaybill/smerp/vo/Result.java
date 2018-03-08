package kyle.leis.eo.operation.predictwaybill.smerp.vo;

public class Result {
	private String PlatformOrderID;
	private boolean Success;
	private String TrackingNumber;
	private String FreightNumber;
	private String ErrorMessage;
	/**新增面单地址**/
	private String LabelUrl;
	
	public String getPlatformOrderID() {
		return PlatformOrderID;
	}

	public void setPlatformOrderID(String platformOrderID) {
		PlatformOrderID = platformOrderID;
	}

	public boolean isSuccess() {
		return Success;
	}

	public void setSuccess(boolean success) {
		Success = success;
	}

	public String getTrackingNumber() {
		return TrackingNumber;
	}

	public void setTrackingNumber(String trackingNumber) {
		TrackingNumber = trackingNumber;
	}

	public String getFreightNumber() {
		return FreightNumber;
	}

	public void setFreightNumber(String freightNumber) {
		FreightNumber = freightNumber;
	}

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}

	public String getLabelUrl() {
		return LabelUrl;
	}

	public void setLabelUrl(String labelUrl) {
		LabelUrl = labelUrl;
	}

	@Override
	public String toString() {
		return "Result [ErrorMessage=" + ErrorMessage + ", FreightNumber="
				+ FreightNumber + ", LabelUrl=" + LabelUrl
				+ ", PlatformOrderID=" + PlatformOrderID + ", Success="
				+ Success + ", TrackingNumber=" + TrackingNumber + "]";
	}

	
	
}
