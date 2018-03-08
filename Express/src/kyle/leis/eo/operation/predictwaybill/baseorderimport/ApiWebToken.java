package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface ApiWebToken {
	
	/**
	 * 获取访问令牌
	 * @param cawtId 店铺编号
	 * @return
	 * @throws Exception
	 */
	String getTokenFromLocal(String cawtId) throws Exception;
	
	/**
	 * 从WebService获取正式令牌
	 * @param tempAuthCode 临时令牌
	 * @param clientID 开发者账号
	 * @param appSecret 开发者密钥
	 * @return
	 * @throws Exception
	 */
	String getTokenFromWS(String tempAuthCode, String clientID, String appSecret) throws Exception;
}
