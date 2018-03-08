package kyle.leis.eo.operation.predictwaybill.ebay;

import java.util.ArrayList;
import java.util.List;

import kyle.leis.eo.operation.predictwaybill.ebay.vo.EbayRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.RequesterCredentials;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.FetchTokenRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.GetOrdersRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.GetSessionIDRequest;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.request.Pagination;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.FetchTokenResponse;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetOrdersResponse;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetSessionIDResponse;
import kyle.leis.eo.operation.predictwaybill.ebay.vo.response.GetUserResponse;

public class EbayUtil {
	/**
	 * 获取SessionID
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static GetSessionIDResponse getSessionID(String ruName) throws Exception{
		GetSessionIDRequest request = new GetSessionIDRequest();
		request.setRuName(ruName);
		IEbayService service = new GetSessionIDService();
		GetSessionIDResponse response = service.getResponse(request);
		if (!"Success".equals(response.getAck())) {
			throw new Exception(response.getErrors().getLongMessage());
		}
		return response;
	}
	
	/**
	 * 获取Token
	 * @param sessionID
	 * @return
	 * @throws Exception
	 */
	public static String fetchToken(String sessionID) throws Exception{
		FetchTokenRequest request = new FetchTokenRequest();
		request.setSessionID(sessionID);
		FetchTokenService service = new FetchTokenService();
		FetchTokenResponse response = service.getResponse(request);
		if (!"Success".equals(response.getAck())) {
			throw new Exception(response.getErrors().getLongMessage());
		}
		return response.geteBayAuthToken();
	}
	
	/**
	 * 
	 * @param eBayAuthToken
	 * @return
	 * @throws Exception
	 */
	public static GetUserResponse getUser(String eBayAuthToken) throws Exception{
		GetUserService service = new GetUserService();
		EbayRequest request = new EbayRequest();
		request.setRequesterCredentials(new RequesterCredentials(eBayAuthToken));
		return service.getResponse(request);
	}
	
	/**
	 * 查询订单
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static List<GetOrdersResponse> getOrdersResponses(GetOrdersRequest request) throws Exception{
		List<GetOrdersResponse> ordersResponses = new ArrayList<GetOrdersResponse>();
		if (request.getPagination() == null) {
			Pagination pagination = new Pagination();
			pagination.setEntriesPerPage(25);
			pagination.setPageNumber(1);
			request.setPagination(pagination);
		}
		IEbayService ebayService = new GetOrdersService();
		setOrdersResponses(request, ebayService, ordersResponses);
		return ordersResponses;
	}
	
	private static void setOrdersResponses(GetOrdersRequest request, IEbayService service,
			List<GetOrdersResponse> ordersResponses) throws Exception{
		GetOrdersResponse response = service.getResponse(request);
		if (!"Success".equals(response.getAck())) {
			throw new Exception(response.getErrors().getLongMessage());
		}
		ordersResponses.add(response);
		if (response.getHasMoreOrders()) {
			int nextPage = request.getPagination().getPageNumber() + 1;
			request.getPagination().setPageNumber(nextPage);
			setOrdersResponses(request, service, ordersResponses);
		}
	}
	
	private EbayUtil() {
	}
}
