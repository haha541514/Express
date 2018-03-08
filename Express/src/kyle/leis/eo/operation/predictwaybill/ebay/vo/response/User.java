package kyle.leis.eo.operation.predictwaybill.ebay.vo.response;

public class User {
	private String UserID;
	private String Status;
	private boolean UserIDChanged;

	public String getUserID() {
		return UserID;
	}

	public void setUserID(String userID) {
		UserID = userID;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public boolean isUserIDChanged() {
		return UserIDChanged;
	}

	public void setUserIDChanged(boolean userIDChanged) {
		UserIDChanged = userIDChanged;
	}

}
