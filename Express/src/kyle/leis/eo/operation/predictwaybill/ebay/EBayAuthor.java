package kyle.leis.eo.operation.predictwaybill.ebay;

import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractApiWebAuthor;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.AuthUrlResult;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.IsAuthorizedResult;
import kyle.leis.eo.operation.predictwaybill.baseorderimport.RedirectParam;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;

public class EBayAuthor extends AbstractApiWebAuthor {
	private static final String RUNAME = "Xins-Xins102f1-b62d--crdfmbjcs";
//	private static final String RUNAME = "Xins-Xins7baf3-47df--nsmxs"; // ≤‚ ‘
	
	private static final String AUTHORIZATION_URL = "https://signin.ebay.com/ws/eBayISAPI.dll";
//	private static final String AUTHORIZATION_URL = "https://signin.sandbox.ebay.com/ws/eBayISAPI.dll"; // ≤‚ ‘

	@Override
	protected AuthUrlResult getAuthUrl(String redirectUrl, RedirectParam redirectParam, 
			String redirectParamStr) throws Exception {
		String sessionID = EbayUtil.getSessionID(RUNAME).getSessionID();
		String url = AUTHORIZATION_URL + "?SignIn&" + "RuName=" + RUNAME + "&SessID=" + sessionID;
		return new AuthUrlResult(url, sessionID);
	}

	public IsAuthorizedResult isAuthorized(String coCode, String formalToken, 
			String clientID, String appSecret) throws Exception {
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCococode(coCode);
		String storeID = EbayUtil.getUser(formalToken).getUser().getUserID();
		condition.setCawtcawtpassword(storeID);
		boolean isAuthorized = !CustomerApiWebDemand.query(condition).isEmpty();
		return new IsAuthorizedResult(isAuthorized, storeID);
	}

}
