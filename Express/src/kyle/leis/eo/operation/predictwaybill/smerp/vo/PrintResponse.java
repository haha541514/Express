package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.ArrayList;
import java.util.List;


/**
 * ��ӡ������
 * */
public class PrintResponse{
	private boolean Success; // ������֤�Ƿ�ɹ�
	private String Message;//˵����Ϣ
	//private List<ErrorData> ErrorData = new ArrayList<ErrorData>();//����ʱErrorData����
	//private List<PrintResult> Result = new ArrayList<PrintResult>();//��ȷʱResult����
	private ErrorData ErrorData;
	private PrintResult Result;
	
	/*********Set && Get*************/

	public boolean isSuccess() {
		return Success;
	}
	public void setSuccess(boolean success) {
		Success = success;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
/*	public List<ErrorData> getErrorData() {
		return ErrorData;
	}
	public void setErrorData(List<ErrorData> errorData) {
		ErrorData = errorData;
	}
	public List<PrintResult> getResult() {
		return Result;
	}
	public void setResult(List<PrintResult> result) {
		Result = result;
	}*/
	@Override
	public String toString() {
		return "PrintResponse [ErrorData=" + ErrorData + ", Message=" + Message
				+ ", Result=" + Result + ", Success=" + Success + "]";
	}
	
	public ErrorData getErrorData() {
		return ErrorData;
	}
	public void setErrorData(ErrorData errorData) {
		ErrorData = errorData;
	}
	public PrintResult getResult() {
		return Result;
	}
	public void setResult(PrintResult result) {
		Result = result;
	}
	

	
	
	
	
}