package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import kyle.common.util.jlang.StringUtility;

public abstract class AbstractApiWebToken implements ApiWebToken {
	protected static final Logger LOGGER = Logger.getLogger(AbstractApiWebToken.class.getName());
	
	private final Map<String, AccessToken> tokens = new HashMap<String, AccessToken>();
	
	public String getTokenFromLocal(String cawtId) throws Exception {
		AccessToken accessToken = tokens.get(cawtId);
		if (accessToken != null && isValid(accessToken.getLastAccessTime())) {
			accessToken.setLastAccessTime(System.currentTimeMillis());
			return accessToken.getAccessToken();
		}
		synchronized (this) {
			accessToken = tokens.get(cawtId);
			// ˫�ؼ��
			if (accessToken != null && isValid(accessToken.getLastAccessTime())){
				accessToken.setLastAccessTime(System.currentTimeMillis());
				return accessToken.getAccessToken();
			}
			if (accessToken == null) {
				accessToken = new AccessToken();
			}
			String oldAccessToken = accessToken.getAccessToken();
			String newAccessToken = refreshAccessToken(cawtId, oldAccessToken);
			if (StringUtility.isNull(newAccessToken)) {
				return null;
			}
			
			accessToken.setAccessToken(newAccessToken);
			accessToken.setLastAccessTime(System.currentTimeMillis());
			tokens.put(cawtId, accessToken);
			return accessToken.getAccessToken();
		}
	}
	
	/**
	 * ˢ�·�������
	 * @param cawtId ���̱��
	 * @param oldAccessToken �ɵķ�������
	 * @return
	 * @throws Exception
	 */
	protected abstract String refreshAccessToken(String cawtId, 
			String oldAccessToken) throws Exception;

	/**
	 * ��ȡ����������Чʱ�䣬0Ϊ������Ч
	 * @return
	 */
	protected abstract long getValidTime();
	
	/**
	 * accessToken �Ƿ���Ч
	 * 
	 * @param lastAccessTime
	 * @param validTime
	 * @return
	 */
	private boolean isValid(long lastAccessTime) {
		if (getValidTime() == 0) {
			return true;
		}
		return (System.currentTimeMillis() - lastAccessTime) < getValidTime();
	}

	private static class AccessToken {
		private String accessToken;
		private long lastAccessTime;

		public AccessToken() {
		}

		public String getAccessToken() {
			return accessToken;
		}

		public void setAccessToken(String accessToken) {
			this.accessToken = accessToken;
		}

		public long getLastAccessTime() {
			return lastAccessTime;
		}

		public void setLastAccessTime(long lastAccessTime) {
			this.lastAccessTime = lastAccessTime;
		}
	}
}
