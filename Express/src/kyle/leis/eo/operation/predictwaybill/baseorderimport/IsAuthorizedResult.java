package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public class IsAuthorizedResult {
	private boolean isAuthorized;
	private String storeID;

	public IsAuthorizedResult() {
	}

	public IsAuthorizedResult(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public IsAuthorizedResult(boolean isAuthorized, String storeID) {
		this.isAuthorized = isAuthorized;
		this.storeID = storeID;
	}

	public boolean isAuthorized() {
		return isAuthorized;
	}

	public void setAuthorized(boolean isAuthorized) {
		this.isAuthorized = isAuthorized;
	}

	public String getStoreID() {
		return storeID;
	}

	public void setStoreID(String storeID) {
		this.storeID = storeID;
	}

}
