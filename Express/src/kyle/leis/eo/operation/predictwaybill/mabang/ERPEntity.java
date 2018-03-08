package kyle.leis.eo.operation.predictwaybill.mabang;

public class ERPEntity {
	private boolean IsSuccess;
	private String ErrorMessage;
	private String Code;
	private String ServiceNumber;
	public boolean isIsSuccess() {
		return IsSuccess;
	}
	public void setIsSuccess(boolean isSuccess) {
		IsSuccess = isSuccess;
	}
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	public String getServiceNumber() {
		return ServiceNumber;
	}
	public void setServiceNumber(String serviceNumber) {
		ServiceNumber = serviceNumber;
	}
	public String getCode() {
		return Code;
	}
	public void setCode(String code) {
		Code = code;
	}
}
