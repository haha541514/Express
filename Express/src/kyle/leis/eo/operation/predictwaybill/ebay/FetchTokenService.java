package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.FetchTokenResponse;

import com.thoughtworks.xstream.XStream;

public class FetchTokenService extends AEbayService {

	public FetchTokenService() {
		super("FetchToken");
	}

	@Override
	protected void configToResponse(XStream stream) {
		stream.alias("FetchTokenResponse", FetchTokenResponse.class);
	}

	@Override
	protected void configToXMLRequest(XStream stream) {
	}

}
