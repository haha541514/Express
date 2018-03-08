package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface ApiWebAuthor {
	/**
	 * ��ȡ��ȨURL
	 * @param redirectUrl �ص���ַ
	 * @param redirectParam �ص�����
	 * @return
	 */
	public AuthUrlResult getAuthUrl(String redirectUrl, RedirectParam redirectParam) throws Exception;
	
	/**
	 * �Ƿ�����Ȩ
	 * @param coCode �ͻ�����
	 * @param formalToken 
	 * @param clientID 
	 * @param appSecret 
	 * @return
	 * @throws Exception
	 */
	public IsAuthorizedResult isAuthorized(String coCode, String formalToken, String clientID, String appSecret) throws Exception;
}
