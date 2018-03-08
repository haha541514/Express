package kyle.leis.eo.operation.predictwaybill.ebay.vo.response;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayResponse;

public class GetUserResponse extends EbayResponse {
	private User User;

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

}
