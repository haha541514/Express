package kyle.leis.eo.operation.predictwaybill.aliexpress;

import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractApiWebAuthor;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.AuthUrlResult;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.IsAuthorizedResult;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.RedirectParam;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;

public class AliexpressAuthor extends AbstractApiWebAuthor {

	protected AuthUrlResult getAuthUrl(String redirectUrl, 
			RedirectParam redirectParam, String redirectParamStr) throws Exception {
		Aliexpress aliexpress = new Aliexpress(redirectParam.getClientID(), 
				redirectParam.getAppSecret());
		String url = aliexpress.getAuthUrl(redirectUrl, redirectParamStr);
		AuthUrlResult result = new AuthUrlResult(url);
		return result;
	}

	public IsAuthorizedResult isAuthorized(String coCode, String formalToken, 
			String clientID, String appSecret) throws Exception {
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCococode(coCode);
		String storeID = clientID + "," + appSecret;
		condition.setCawtcawtpassword(storeID);
		boolean isAuthorized = !CustomerApiWebDemand.query(condition).isEmpty();
		return new IsAuthorizedResult(isAuthorized, storeID);
	}

}
