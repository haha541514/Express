package kyle.leis.eo.operation.predictwaybill.smerp.vo;

/**
 * ��ӡ��ǩ����ʱ������
 * */
public class ErrorData {

	private String TicketId;// ����Ψһ�����

	private String ErrorCode;// string �������

	private String Message; // string ������Ϣ

	private String RequestUri;// string ��������

	private String DateTime;// ʱ���ʽ "yyyy-MM-dd HH:mm:ss"//string

	public ErrorData() {

	}

	public ErrorData(String ticketId, String errorCode, String message,
			String requestUri, String dateTime) {
		super();
		TicketId = ticketId;
		ErrorCode = errorCode;
		Message = message;
		RequestUri = requestUri;
		DateTime = dateTime;
	}

	/************ Set && Get ***************/

	public String getTicketId() {
		return TicketId;
	}

	public void setTicketId(String ticketId) {
		TicketId = ticketId;
	}

	public String getErrorCode() {
		return ErrorCode;
	}

	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public String getRequestUri() {
		return RequestUri;
	}

	public void setRequestUri(String requestUri) {
		RequestUri = requestUri;
	}

	public String getDateTime() {
		return DateTime;
	}

	public void setDateTime(String dateTime) {
		DateTime = dateTime;
	}

	public String toString() {
		return "ErrorData [DateTime=" + DateTime + ", ErrorCode=" + ErrorCode
				+ ", Message=" + Message + ", RequestUri=" + RequestUri
				+ ", TicketId=" + TicketId + "]";
	}

}