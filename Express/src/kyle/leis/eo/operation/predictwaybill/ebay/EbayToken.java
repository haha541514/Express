package kyle.leis.eo.operation.predictwaybill.ebay;

import java.util.List;

import kyle.leis.eo.operation.predictwaybill.baseorderimport.AbstractApiWebToken;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenColumns;
import kyle.leis.es.company.customer.da.CustomerapiwebtokenCondition;
import kyle.leis.es.company.customer.dax.CustomerApiWebDemand;

public class EbayToken extends AbstractApiWebToken {

	@Override
	protected long getValidTime() {
		return 0;
	}

	@Override
	protected String refreshAccessToken(String cawtId, String oldAccessToken)
			throws Exception {
		CustomerapiwebtokenCondition condition = new CustomerapiwebtokenCondition();
		condition.setCawtcawtid(cawtId);
		List<CustomerapiwebtokenColumns> list = CustomerApiWebDemand.query(condition);
		if (!list.isEmpty()) {
			return list.get(0).getCawtcawttoken();
		}
		return null;
	}

	public String getTokenFromWS(String tempAuthCode, String clientID, String appSecret) throws Exception {
		return EbayUtil.fetchToken(tempAuthCode);
	}

}
