package kyle.leis.eo.operation.predictwaybill.smerp.vo;

import java.util.ArrayList;
import java.util.List;

public class PushResponse {
	private boolean Success; // 数据验证是否成功
	private String Message;
	private List<Result> Result = new ArrayList<Result>();

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

	public List<Result> getResult() {
		return Result;
	}

	public void setResult(List<Result> result) {
		Result = result;
	}
	
	public void addResult(Result result){
		Result.add(result);
	}

	@Override
	public String toString() {
		return "PushResponse [Message=" + Message + ", Result=" + Result
				+ ", Success=" + Success + "]";
	}

}
