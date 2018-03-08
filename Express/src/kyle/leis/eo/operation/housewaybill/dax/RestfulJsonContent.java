package kyle.leis.eo.operation.housewaybill.dax;

public class RestfulJsonContent {
	private String Amount;
	private String UnitPrice;
	private String TotalPrice;
	private String Attacheinfo ;	
	private String Remark;
	private String CurrencyCurrency;
	private String Description;
	private String MadeIn;
	private String DescriptionCN;
	
	public String getDescriptionCN() {
		return DescriptionCN;
	}
	public void setDescriptionCN(String descriptionCN) {
		DescriptionCN = descriptionCN;
	}
	public String getAmount() {
		return Amount;
	}
	public void setAmount(String amount) {
		Amount = amount;
	}
	public String getUnitPrice() {
		return UnitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		UnitPrice = unitPrice;
	}
	public String getTotalPrice() {
		return TotalPrice;
	}
	public void setTotalPrice(String totalPrice) {
		TotalPrice = totalPrice;
	}
	public String getAttacheinfo() {
		return Attacheinfo;
	}
	public void setAttacheinfo(String attacheinfo) {
		Attacheinfo = attacheinfo;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getCurrencyCurrency() {
		return CurrencyCurrency;
	}
	public void setCurrencyCurrency(String currencyCurrency) {
		CurrencyCurrency = currencyCurrency;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getMadeIn() {
		return MadeIn;
	}
	public void setMadeIn(String madeIn) {
		MadeIn = madeIn;
	}
	
}
