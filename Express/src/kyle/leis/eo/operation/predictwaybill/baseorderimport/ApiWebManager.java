package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.util.HashMap;
import java.util.Map;

import kyle.leis.eo.operation.predictwaybill.aliexpress.AliOrderImportor;
import kyle.leis.eo.operation.predictwaybill.aliexpress.AliexpressAuthor;
import kyle.leis.eo.operation.predictwaybill.aliexpress.AliexpressToken;
import kyle.leis.eo.operation.predictwaybill.ebay.EBayAuthor;
import kyle.leis.eo.operation.predictwaybill.ebay.EbayOrderImportor;
import kyle.leis.eo.operation.predictwaybill.ebay.EbayToken;

public class ApiWebManager {
	private static class ApiWebHolder {
		static Map<String, ApiWebToken> apiWebToken = new HashMap<String, ApiWebToken>();
		static Map<String, ApiWebAuthor> apiWebAuthor = new HashMap<String, ApiWebAuthor>();
		static Map<String, OrderImportor> orderImportor = new HashMap<String, OrderImportor>();
		
		static {
			apiWebToken.put(ApiWebType.ALIEXPRESS, new AliexpressToken());
			apiWebToken.put(ApiWebType.EBAY, new EbayToken());
			
			apiWebAuthor.put(ApiWebType.ALIEXPRESS, new AliexpressAuthor());
			apiWebAuthor.put(ApiWebType.EBAY, new EBayAuthor());
			
			orderImportor.put(ApiWebType.ALIEXPRESS, new AliOrderImportor());
			orderImportor.put(ApiWebType.EBAY, new EbayOrderImportor());
		}
	}

	/**
	 * 获取Token实例
	 * @param capwtCode 店铺类型
	 * @return
	 */
	public static ApiWebToken getApiWebToken(String capwtCode) {
		return ApiWebHolder.apiWebToken.get(capwtCode);
	}
	
	/**
	 * 获取ApiWebAuthor
	 * @param capwtCode 店铺类型
	 * @return
	 */
	public static ApiWebAuthor getApiWebAuthor(String capwtCode) {
		return ApiWebHolder.apiWebAuthor.get(capwtCode);
	}
	
	/**
	 * 
	 * @param capwtCode 店铺类型
	 * @return
	 */
	public static OrderImportor getOrderImportor(String capwtCode){
		return ApiWebHolder.orderImportor.get(capwtCode);
	}
	
	/**
	 * 解析回调参数
	 * @param paramStr
	 * @return
	 */
	public static RedirectParam decodeRedirectParam(String paramStr){
		return AbstractApiWebAuthor.decodeRedirectParam(paramStr);
	}
	
	/**
	 * 网店类型
	 * @author Administrator
	 *
	 */
	public static class ApiWebType {
		/** 速卖通 **/
		public static final String ALIEXPRESS = "ALE";
		/** 易贝 **/
		public static final String EBAY = "EBY";
	}
}
