package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.GetOrdersRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetOrdersResponse;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Order;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.Transaction;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.collections.CollectionConverter;
import com.thoughtworks.xstream.mapper.ClassAliasingMapper;

public class GetOrdersService extends AEbayService{
	
	public GetOrdersService() {
		super("GetOrders");
	}

	@Override
	protected void configToResponse(XStream stream) {
		stream.alias("GetOrdersResponse", GetOrdersResponse.class);
		stream.alias("Order", Order.class);
		stream.alias("Transaction", Transaction.class);
		stream.registerConverter(new CurrencyConverter());
	}

	@Override
	protected void configToXMLRequest(XStream stream) {
		ClassAliasingMapper mapper = new ClassAliasingMapper(stream.getMapper());
		mapper.addClassAlias("OrderID", String.class);
		stream.registerLocalConverter(GetOrdersRequest.class, "OrderIDArray", new CollectionConverter(mapper));
	}
}
