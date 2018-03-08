package kyle.leis.eo.operation.predictwaybill.smerp.vo;

/**
 * 打印标签错误时返回类
 * */
public class ErrorData {

	private String TicketId;// 贷代唯一处理号

	private String ErrorCode;// string 错误代码

	private String Message; // string 错误消息

	private String RequestUri;// string 请求链接

	private String DateTime;// 时间格式 "yyyy-MM-dd HH:mm:ss"//string

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