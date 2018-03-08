package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;

public interface IEbayService {
	<T> T getResponse(EbayRequest request) throws Exception;
}
