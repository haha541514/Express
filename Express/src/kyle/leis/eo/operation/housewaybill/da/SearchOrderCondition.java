package kyle.leis.eo.operation.housewaybill.da;

public class SearchOrderCondition {

	//订单号
	private String  strCustomerewbcode;
	
	//目的国家(目的城市代码)
	private String strDestcode;
	
	//订单创建时间
	private String strStartdate;
	
	private String strEnddate;
	
	//收件人名
	//private String 
	//产品
	private String strPkcode;
	
	//货物类型
	private String strCtcode;
	
	//付费模式
	private String strPmcode;

	public String getStrCustomerewbcode() {
		return strCustomerewbcode;
	}

	public void setStrCustomerewbcode(String strCustomerewbcode) {
		this.strCustomerewbcode = strCustomerewbcode;
	}

	public String getStrDestcode() {
		return strDestcode;
	}

	public void setStrDestcode(String strDestcode) {
		this.strDestcode = strDestcode;
	}

	public String getStrStartdate() {
		return strStartdate;
	}

	public void setStrStartdate(String strStartdate) {
		this.strStartdate = strStartdate;
	}

	public String getStrEnddate() {
		return strEnddate;
	}

	public void setStrEnddate(String strEnddate) {
		this.strEnddate = strEnddate;
	}

	public String getStrPkcode() {
		return strPkcode;
	}

	public void setStrPkcode(String strPkcode) {
		this.strPkcode = strPkcode;
	}

	public String getStrCtcode() {
		return strCtcode;
	}

	public void setStrCtcode(String strCtcode) {
		this.strCtcode = strCtcode;
	}

	public String getStrPmcode() {
		return strPmcode;
	}

	public void setStrPmcode(String strPmcode) {
		this.strPmcode = strPmcode;
	}

	
}
