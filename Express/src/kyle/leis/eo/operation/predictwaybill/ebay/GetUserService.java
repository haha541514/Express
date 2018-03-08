package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetUserResponse;

import com.thoughtworks.xstream.XStream;

public class GetUserService extends AEbayService {

	public GetUserService() {
		super("GetUser");
	}

	@Override
	protected void configToResponse(XStream stream) {
		stream.alias("GetUserResponse", GetUserResponse.class);
	}

	@Override
	protected void configToXMLRequest(XStream stream) {
		stream.alias("GetUserRequest", EbayRequest.class);
	}
}
