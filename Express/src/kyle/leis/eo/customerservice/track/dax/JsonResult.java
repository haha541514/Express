package kyle.leis.eo.customerservice.track.dax;

public class JsonResult {
	public static String returnFail(String statusCode, String message){
		String result = "{\"status\": \"fail\", " +
				"\"statusCode\": \"" + statusCode + "\", " +
				"\"message\": \"" + message + "\"}";
		return result;
	}
	
	public static String returnSuccess(String message){
		String result = "{\"status\": \"success\", " +
				"\"statusCode\": \"0\", " +
				"\"message\": \"" + message + "\"}";
		return result;
	}
}
