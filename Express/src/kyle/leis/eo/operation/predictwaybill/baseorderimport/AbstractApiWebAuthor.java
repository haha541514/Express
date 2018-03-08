package kyle.leis.eo.operation.predictwaybill.baseorderimport;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import kyle.common.util.jlang.StringUtility;
import net.sf.json.JSONObject;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.codec.binary.Base64;

public abstract class AbstractApiWebAuthor implements ApiWebAuthor {

	public AuthUrlResult getAuthUrl(String redirectUrl, RedirectParam redirectParam) throws Exception {
		String redirectParamStr = encodeRedirectParam(redirectParam);
		return getAuthUrl(redirectUrl, redirectParam, redirectParamStr);
	}

	/**
	 * 编码回调参数
	 * @param redirectParam
	 * @return
	 * @throws Exception
	 */
	static String encodeRedirectParam(RedirectParam redirectParam) throws Exception {
		String redirectParamStr = null;
		if (redirectParam != null) {
			RedirectParam copyParam = (RedirectParam) BeanUtils.cloneBean(redirectParam);
			// 先对appSecret加密
			String appSecret = copyParam.getAppSecret();
			if (!StringUtility.isNull(appSecret)) {
				String targetAppSecret = encryptionAppSecret(appSecret);
				copyParam.setAppSecret(targetAppSecret);
			}
			JSONObject jsonObject = JSONObject.fromObject(copyParam);
			String jsonStr = jsonObject.toString();
			byte[] encodeBytes = Base64.encodeBase64(jsonStr.getBytes("UTF-8"));
			redirectParamStr = new String(encodeBytes, "UTF-8");
			redirectParamStr = redirectParamStr.replace("+", "_"); // 处理特殊字符+
		}
		return redirectParamStr;
	}
	
	/**
	 * 解析回调参数
	 * @param paramStr
	 * @return
	 */
	static RedirectParam decodeRedirectParam(String paramStr){
		if (StringUtility.isNull(paramStr)) {
			return null;
		}
		paramStr = paramStr.replace("_", "+"); // 还原特殊字符
		try {
			byte[] decodeBytes = Base64.decodeBase64(paramStr.getBytes("UTF-8"));
			String jsonStr = new String(decodeBytes, "UTF-8");
			JSONObject jsonObject = JSONObject.fromObject(jsonStr);
			RedirectParam redirectParam = (RedirectParam) JSONObject.toBean(jsonObject, RedirectParam.class);
			if (redirectParam != null) {
				// 对appSecret解密
				String appSecret = redirectParam.getAppSecret();
				if (!StringUtility.isNull(appSecret)) {
					String originAppSecret = decryptionAppSecret(appSecret);
					redirectParam.setAppSecret(originAppSecret);
				}
			}
			return redirectParam;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * 对appSecret加密
	 * @param appSecret
	 * @return
	 * @throws Exception
	 */
	static String encryptionAppSecret(String appSecret) throws Exception {
		Cipher cipher = getCipher(Cipher.ENCRYPT_MODE);
		byte[] bs = appSecret.getBytes("UTF-8");
		return new String(Base64.encodeBase64(cipher.doFinal(bs)), "UTF-8");
	}

	/**
	 * 对appSecret解密
	 * @param targetAppSecret
	 * @return
	 * @throws Exception
	 */
	static String decryptionAppSecret(String targetAppSecret) throws Exception{
		Cipher cipher = getCipher(Cipher.DECRYPT_MODE);
		byte[] bs = Base64.decodeBase64(targetAppSecret.getBytes("UTF-8"));
		return new String(cipher.doFinal(bs), "UTF-8");
	}
	
	/**
	 * 
	 * @return
	 * @throws Exception
	 */
	private static Cipher getCipher(int cipherMode) throws Exception {
		final String SECRET_KEY = "!@#$%^&*";
		DESKeySpec desKeySpec = new DESKeySpec(SECRET_KEY.getBytes("UTF-8"));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
		Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
		cipher.init(cipherMode, secretKey);
		return cipher;
	}
	
	/**
	 * 
	 * @param redirectUrl
	 * @param redirectParam 回调参数
	 * @param redirectParamStr Base64编码后的参数
	 * @return
	 * @throws Exception
	 */
	protected abstract AuthUrlResult getAuthUrl(String redirectUrl,  
			RedirectParam redirectParam, String redirectParamStr) throws Exception;
	
}
