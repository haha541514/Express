package kyle.leis.eo.operation.predictwaybill.aliexpress;

import java.util.List;

import kyle.common.util.jlang.StringUtility;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractApiWebToken;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;

public class AliexpressToken extends AbstractApiWebToken {
	
	private static final long validTime = 10 * 60 * 60 * 1000 - 5 * 60 * 1000;
	
	@Override
	protected long getValidTime() {
		return validTime;
	}
	
	protected String refreshAccessToken(String cawtId, 
			String oldAccessToken) throws Exception{
		CustomerapiwebtokenColumns apiwebtoken = getCustomerapiwebtoken(cawtId);
		if (apiwebtoken == null 
				|| StringUtility.isNull(apiwebtoken.getCawtcawttoken())) {
			throw new Exception("refresh_token is null!");
		}
		String appAcount = apiwebtoken.getCawtcawtpassword();
		if (StringUtility.isNull(appAcount) || !appAcount.contains(",")) {
			throw new Exception("Can not get appKey!");
		}
		String[] appKey = appAcount.split(",");
		Aliexpress aliexpress = new Aliexpress(appKey[0], appKey[1]);
		return aliexpress.getAccessToken(apiwebtoken.getCawtcawttoken());
	}
	
	/**
	 * 获取长时令牌
	 * @param coCode
	 * @param capwtCode
	 * @param storeName
	 * @return
	 */
	private CustomerapiwebtokenColumns getCustomerapiwebtoken(String cawtCawtId) throws Exception{
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCawtcawtid(cawtCawtId);
		List<CustomerapiwebtokenColumns> list = CustomerApiWebDemand.query(condition);
		return list.isEmpty() ? null : list.get(0);
	}

	public String getTokenFromWS(String tempAuthCode, String clientID, String appSecret) throws Exception {
		Aliexpress aliexpress = new Aliexpress(clientID, appSecret);
		return aliexpress.getRefreshToken(tempAuthCode);
	}
}
