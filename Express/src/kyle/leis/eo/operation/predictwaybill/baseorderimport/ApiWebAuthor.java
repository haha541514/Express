package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface ApiWebAuthor {
	/**
	 * 获取授权URL
	 * @param redirectUrl 回调地址
	 * @param redirectParam 回调参数
	 * @return
	 */
	public AuthUrlResult getAuthUrl(String redirectUrl, RedirectParam redirectParam) throws Exception;
	
	/**
	 * 是否已授权
	 * @param coCode 客户代码
	 * @param formalToken 
	 * @param clientID 
	 * @param appSecret 
	 * @return
	 * @throws Exception
	 */
	public IsAuthorizedResult isAuthorized(String coCode, String formalToken, String clientID, String appSecret) throws Exception;
}
