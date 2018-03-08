package kyle.leis.eo.operation.predictwaybill.aliexpress;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressOrder;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressProduct;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressReceiptInfo;
import kyle.leis.eo.operation.predictwaybill.aliexpress.vo.AliexpressWayBill;
import net.sf.json.JSONObject;

public class Aliexpress {
	protected static final Logger LOGGER = Logger.getLogger(Aliexpress.class.getName());
	
	private static final String HOST = "gw.api.alibaba.com";
	private static final String SITE = "aliexpress";
	private static final String REDIRECT_URI = "urn:ietf:wg:oauth:2.0:oob";
	
	private final String CLIENT_ID;
	private final String APPSECRET;
	
	private static final String URL_HEAD = "http://" + HOST + "/openapi/";
	private static final String URL_PATH_PREFIX = "param2/1/aliexpress.open/";
	
	private static final int page_size = 50;
	
	public Aliexpress(String clientID, String appSecret){
		CLIENT_ID = clientID;
		APPSECRET = appSecret;
	}
	
	/**
     * 返回客户端和Web端授权时获取临时令牌code的url
     * @param host 请求的主机名，包括域名和端口
     * @param params 请求参数map，包括client_id,site,redirect_uri以及可选的state、scope和view
     * @param appSecretKey app签名密钥
     * @return 请求的完整url，用户在浏览器中打开此url然后输入自己的用户名密码进行授权，之后就会得到code
     */
    private String getClientAuthUrl(String host, Map<String, String> params, String appSecretKey){
        String url = "http://" + host + "/auth/authorize.htm";
        if(params == null){
            return null;
        }
        if(params.get("client_id") == null || params.get("site") == null
                || params.get("redirect_uri") == null){
            LOGGER.severe("params is invalid, lack neccessary key!");
            return null;
        }
        String signature = CommonUtil.signatureWithParamsOnly(params, appSecretKey);
        params.put("_aop_signature", signature);
        return CommonUtil.getWholeUrl(url, params);
    }
    
    /**
     * 通过临时令牌换取长时令牌和授权令牌
     * @param host 请求的主机名，包括域名和端口
     * @param params 请求参数，必填client_id、client_secret、redirect_uri和code，scope和view可选
     * @param needRefreshToken 是否需要返回refreshToken
     * @return getToken请求的json串
     */
    private String getToken(String host, Map<String, String> params, boolean needRefreshToken) {
        String urlHead = "https://" + host + "/openapi/";
        String namespace = "system.oauth2";
        String name = "getToken";
        int version = 1;
        String protocol = "http";
        if(params != null){
            if(params.get("client_id") == null || params.get("client_secret") == null
                    || params.get("redirect_uri") == null || params.get("code") == null){
            	LOGGER.severe("params is invalid, lack neccessary key!");
                return null;
            }
            params.put("grant_type", "authorization_code");
            params.put("need_refresh_token", Boolean.toString(needRefreshToken));
            String appKey = params.get("client_id");
            String urlPath = CommonUtil.buildInvokeUrlPath(namespace, name, version, protocol, appKey);
            String result = ApiCallService.callApi(urlHead, urlPath, null, params);
            return result;
        }
        return null;
    }
    
    /**
     * 通过长时令牌换取授权令牌
     * @param host 请求的主机名，包括域名和端口
     * @param params 请求参数，必填client_id、client_secret、redirect_uri和refresh_token，scope和view可选
     * @return
     */
    private String refreshToken(String host, Map<String, String> params) {
        String urlHead = "https://" + host + "/openapi/";
        String namespace = "system.oauth2";
        String name = "getToken";
        int version = 1;
        String protocol = "param2";
        if(params != null){
            if(params.get("client_id") == null || params.get("client_secret") == null
                    || params.get("redirect_uri") == null || params.get("refresh_token") == null){
            	LOGGER.severe("params is invalid, lack neccessary key!");
                return null;
            }
            params.put("grant_type", "refresh_token");
            String appKey = params.get("client_id");
            String urlPath = CommonUtil.buildInvokeUrlPath(namespace, name, version, protocol, appKey);
            String result = ApiCallService.callApi(urlHead, urlPath, null, params);
            return result;
        }
        return null;
    }
	
    /**
     * 获取授权URL
     * @return
     */
	public String getAuthUrl(String redirectUri, String state){
		//获取客户端授权的临时令牌code
        Map<String, String> params = new HashMap<String, String>();
        params.put("site", SITE);
        params.put("client_id", CLIENT_ID);
        params.put("redirect_uri", redirectUri);
        params.put("state", state);
        String getCodeForClientResult = getClientAuthUrl(HOST, params, APPSECRET);
        return getCodeForClientResult;
	}
	
	/**
	 * 获取长时令牌
	 * @param tempCode 临时令牌
	 * @return
	 */
	public String getRefreshToken(String tempCode) throws Exception{
		Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", CLIENT_ID);
        params.put("redirect_uri", REDIRECT_URI);
        params.put("client_secret", APPSECRET);
        params.put("code", tempCode);
        String getTokenResult = getToken(HOST, params, true);
        if (getTokenResult == null) {
        	throw new Exception("获取refresh_token失败！tempCode：" + tempCode);
		}
        JSONObject jsonObject = JSONObject.fromObject(getTokenResult);
        String refreshToken = (String) jsonObject.get("refresh_token");
        if (refreshToken == null) {
        	String error = (String) jsonObject.get("error_description");
        	if (error != null) {
        		throw new Exception(error);
			}
			throw new Exception(getTokenResult);
		}
        return refreshToken;
	}
	
	/**
	 * 获取访问令牌
	 * @param refreshToken 长时令牌
	 * @return
	 */
	public String getAccessToken(String refreshToken) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("client_id", CLIENT_ID);
        params.put("redirect_uri", REDIRECT_URI);
        params.put("client_secret", APPSECRET);
        params.put("refresh_token", refreshToken);
        String refreshTokenResult = refreshToken(HOST, params);
        if (refreshTokenResult == null) {
        	throw new Exception("获取access_token失败！refresh_token：" + refreshToken);
		}
        JSONObject jsonObject = JSONObject.fromObject(refreshTokenResult);
        String accessToken = (String) jsonObject.get("access_token");
        if (accessToken == null) {
        	String error = (String) jsonObject.get("error_description");
        	if (error != null) {
        		throw new Exception(error);
			}
			throw new Exception(refreshTokenResult);
		}
        return accessToken;
	}
	
	/**
	 * 查询订单
	 * @param createDateStart 开始时间，格式: mm/dd/yyyy hh:mm:ss,如10/08/2013 00:00:00
	 * @param createDateEnd 结束时间，格式: mm/dd/yyyy hh:mm:ss,如10/08/2013 00:00:00
	 * @param accessToken 访问令牌
	 * @return
	 */
	public List<AliexpressWayBill> queryAliexpressOrder(String createDateStart,
			String createDateEnd, String accessToken) throws Exception{
		List<AliexpressWayBill> aliexpressWayBills = new ArrayList<AliexpressWayBill>();
		int page = 1;
		// 查询第一页订单
        String result = findOrderListSimpleQuery(createDateStart, createDateEnd, page, page_size, accessToken);
        AliexpressWayBill aliexpressWayBill = parseOrder(result);
        if (!StringUtility.isNull(aliexpressWayBill.getError_code())) {
			throw new Exception(aliexpressWayBill.getError_message());
		}
        int totalSize = aliexpressWayBill.getTotalItem();
        if (totalSize < 1) {
			return aliexpressWayBills;
		}
        aliexpressWayBills.add(aliexpressWayBill);
        // 查询余下订单
        for (int i = page_size; i < totalSize; i += page_size) {
        	page++;
        	result = findOrderListSimpleQuery(createDateStart, createDateEnd, page, page_size, accessToken);
            aliexpressWayBill = parseOrder(result);
            // 在分页查询的过程中，订单可能会有变化，所以需要做如下判断
            if (aliexpressWayBill.getTotalItem() > 0) {
				aliexpressWayBills.add(aliexpressWayBill);
			}
		}
        return aliexpressWayBills;
	}
	
	/**
	 * 查询订单的收货信息
	 * @param accessToken
	 * @param orderId
	 * @return
	 */
	public AliexpressReceiptInfo queryReceiptInfo(String accessToken, String orderId) throws Exception{
		String result = findOrderReceiptInfo(accessToken, orderId);
		AliexpressReceiptInfo receiptInfo = parseReceiptInfo(result);
		if (!StringUtility.isNull(receiptInfo.getError_code())) {
			throw new Exception(receiptInfo.getError_message());
		}
		return receiptInfo;
	}
	
	/**
	 * 查询速卖通订单
	 * @param createDateStart
	 * @param createDateEnd
	 * @param page
	 * @param pageSize
	 * @param accessToken
	 * @return
	 */
	private String findOrderListSimpleQuery(String createDateStart, String createDateEnd, 
			int page, int pageSize, String accessToken){
		Map<String, String> param = new HashMap<String, String>();
        param.put("access_token", accessToken);
        param.put("page", String.valueOf(page));
        param.put("pageSize", String.valueOf(pageSize));
        param.put("createDateStart", createDateStart);
        param.put("createDateEnd", createDateEnd);
        param.put("orderStatus", "WAIT_BUYER_ACCEPT_GOODS");
        String result = ApiCallService.callApi(URL_HEAD, 
        		buildUrlPath("api.findOrderListSimpleQuery"), APPSECRET, param);
        LOGGER.log(Level.INFO, result);
        return result;
	}
	
	/**
	 * 解析订单
	 * @param result
	 * @return
	 */
	private AliexpressWayBill parseOrder(String result) {
		JSONObject jsonObject = JSONObject.fromObject(result);
		Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();
		classMap.put("orderList", AliexpressOrder.class);
		classMap.put("productList", AliexpressProduct.class);
		AliexpressWayBill aliexpressWayBill = (AliexpressWayBill) JSONObject.toBean(jsonObject, 
				AliexpressWayBill.class, classMap);
		return aliexpressWayBill;
	}
	
	/**
	 * 速卖通订单收货信息查询
	 * @param accessToken
	 * @param orderId
	 * @return
	 */
	private String findOrderReceiptInfo(String accessToken, String orderId){
		Map<String, String> param = new HashMap<String, String>();
		param.put("access_token", accessToken);
		param.put("orderId", orderId);
		String result = ApiCallService.callApi(URL_HEAD, 
        		buildUrlPath("api.findOrderReceiptInfo"), APPSECRET, param);
        LOGGER.log(Level.INFO, result);
        return result;
	}
	
	/**
	 * 解析收货信息
	 * @param result
	 * @return
	 */
	private AliexpressReceiptInfo parseReceiptInfo(String result){
		JSONObject jsonObject = JSONObject.fromObject(result);
		AliexpressReceiptInfo aliexpressReceiptInfo = (AliexpressReceiptInfo) JSONObject.toBean(jsonObject, 
				AliexpressReceiptInfo.class);
		return aliexpressReceiptInfo;
	}
	
	private String buildUrlPath(String action){
		return URL_PATH_PREFIX + action + "/"  + CLIENT_ID;
	}
}
