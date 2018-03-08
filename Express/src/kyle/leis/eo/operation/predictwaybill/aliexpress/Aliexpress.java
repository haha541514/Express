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
     * ���ؿͻ��˺�Web����Ȩʱ��ȡ��ʱ����code��url
     * @param host ����������������������Ͷ˿�
     * @param params �������map������client_id,site,redirect_uri�Լ���ѡ��state��scope��view
     * @param appSecretKey appǩ����Կ
     * @return ���������url���û���������д򿪴�urlȻ�������Լ����û������������Ȩ��֮��ͻ�õ�code
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
     * ͨ����ʱ���ƻ�ȡ��ʱ���ƺ���Ȩ����
     * @param host ����������������������Ͷ˿�
     * @param params �������������client_id��client_secret��redirect_uri��code��scope��view��ѡ
     * @param needRefreshToken �Ƿ���Ҫ����refreshToken
     * @return getToken�����json��
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
     * ͨ����ʱ���ƻ�ȡ��Ȩ����
     * @param host ����������������������Ͷ˿�
     * @param params �������������client_id��client_secret��redirect_uri��refresh_token��scope��view��ѡ
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
     * ��ȡ��ȨURL
     * @return
     */
	public String getAuthUrl(String redirectUri, String state){
		//��ȡ�ͻ�����Ȩ����ʱ����code
        Map<String, String> params = new HashMap<String, String>();
        params.put("site", SITE);
        params.put("client_id", CLIENT_ID);
        params.put("redirect_uri", redirectUri);
        params.put("state", state);
        String getCodeForClientResult = getClientAuthUrl(HOST, params, APPSECRET);
        return getCodeForClientResult;
	}
	
	/**
	 * ��ȡ��ʱ����
	 * @param tempCode ��ʱ����
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
        	throw new Exception("��ȡrefresh_tokenʧ�ܣ�tempCode��" + tempCode);
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
	 * ��ȡ��������
	 * @param refreshToken ��ʱ����
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
        	throw new Exception("��ȡaccess_tokenʧ�ܣ�refresh_token��" + refreshToken);
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
	 * ��ѯ����
	 * @param createDateStart ��ʼʱ�䣬��ʽ: mm/dd/yyyy hh:mm:ss,��10/08/2013 00:00:00
	 * @param createDateEnd ����ʱ�䣬��ʽ: mm/dd/yyyy hh:mm:ss,��10/08/2013 00:00:00
	 * @param accessToken ��������
	 * @return
	 */
	public List<AliexpressWayBill> queryAliexpressOrder(String createDateStart,
			String createDateEnd, String accessToken) throws Exception{
		List<AliexpressWayBill> aliexpressWayBills = new ArrayList<AliexpressWayBill>();
		int page = 1;
		// ��ѯ��һҳ����
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
        // ��ѯ���¶���
        for (int i = page_size; i < totalSize; i += page_size) {
        	page++;
        	result = findOrderListSimpleQuery(createDateStart, createDateEnd, page, page_size, accessToken);
            aliexpressWayBill = parseOrder(result);
            // �ڷ�ҳ��ѯ�Ĺ����У��������ܻ��б仯��������Ҫ�������ж�
            if (aliexpressWayBill.getTotalItem() > 0) {
				aliexpressWayBills.add(aliexpressWayBill);
			}
		}
        return aliexpressWayBills;
	}
	
	/**
	 * ��ѯ�������ջ���Ϣ
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
	 * ��ѯ����ͨ����
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
	 * ��������
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
	 * ����ͨ�����ջ���Ϣ��ѯ
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
	 * �����ջ���Ϣ
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
