package kyle.leis.eo.operation.predictwaybill.baseorderimport;

public interface ApiWebToken {
	
	/**
	 * ��ȡ��������
	 * @param cawtId ���̱��
	 * @return
	 * @throws Exception
	 */
	String getTokenFromLocal(String cawtId) throws Exception;
	
	/**
	 * ��WebService��ȡ��ʽ����
	 * @param tempAuthCode ��ʱ����
	 * @param clientID �������˺�
	 * @param appSecret ��������Կ
	 * @return
	 * @throws Exception
	 */
	String getTokenFromWS(String tempAuthCode, String clientID, String appSecret) throws Exception;
}
