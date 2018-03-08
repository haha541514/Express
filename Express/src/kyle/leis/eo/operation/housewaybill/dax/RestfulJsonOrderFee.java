package kyle.leis.eo.operation.housewaybill.dax;

public class RestfulJsonOrderFee {
	private String FeeName;
	private String FeeMoney;
	private String Feecode;
	public String getFeecode() {
		return Feecode;
	}
	public void setFeecode(String feecode) {
		Feecode = feecode;
	}
	
	public String getFeeName() {
		return FeeName;
	}
	public void setFeeName(String feeName) {
		FeeName = feeName;
	}
	public String getFeeMoney() {
		return FeeMoney;
	}
	public void setFeeMoney(String feeMoney) {
		FeeMoney = feeMoney;
	}
	
}
