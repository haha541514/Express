package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetSessionIDResponse;

import com.thoughtworks.xstream.XStream;

public class GetSessionIDService extends AEbayService {

	public GetSessionIDService() {
		super("GetSessionID");
	}

	@Override
	protected void configToResponse(XStream stream) {
		stream.alias("GetSessionIDResponse", GetSessionIDResponse.class);
	}

	@Override
	protected void configToXMLRequest(XStream stream) {
		
	}

}
