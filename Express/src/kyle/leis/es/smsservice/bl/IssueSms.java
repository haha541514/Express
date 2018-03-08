package kyle.leis.es.smsservice.bl;

import java.util.HashMap;
import java.util.Map;

import kyle.leis.eo.customerservice.issue.da.IssueColumns;
import kyle.leis.eo.customerservice.issue.dax.IssueDemand;

/**
 * 问题件通知
 * 
 * @author Administrator
 * 
 */
public class IssueSms extends WeChatSms {
	private static final String SNKCODE = "SNK004";
	private static final String ACTION = "issue";

	private String customer;
	private String customerwbCode;
	private String issueId;

	public IssueSms(String customer, String customerwbCode, String issueId) {
		this.customer = customer;
		this.customerwbCode = customerwbCode;
		this.issueId = issueId;
	}

	@Override
	protected String getAction() {
		return ACTION;
	}

	@Override
	protected Map<String, Object> getData() {
		Map<String, Object> params = new HashMap<String, Object>();
		try {
			IssueColumns issueColumns = IssueDemand.loadIssueById(issueId);
			params.put("customer", customer); // 客户公司
			params.put("customerwbCode", customerwbCode);
			params.put("date", issueColumns.getIsuisumodifydate());
			params.put("description", issueColumns.getIsuiscontent());
			params.put("status", issueColumns.getIhsihsname());
			params.put("remark", "温馨提示：为了您的货物能正常中转，请尽快处理问题件，以免耽误。谢谢！");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return params;
	}

	public String getSnkCode() {
		return SNKCODE;
	}
}
