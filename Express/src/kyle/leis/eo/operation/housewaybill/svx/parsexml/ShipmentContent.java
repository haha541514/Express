package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import kyle.common.conf.elementconfig.AElementConfig;

import org.jdom.Element;

public class ShipmentContent extends AElementConfig {
	
	private String Amount;
	private String UnitPrice;
	private String TotalPrice;
	private String Currency;	
	private String Description;
	private String DescriptionCN;
	private String Attacheinfo;
	private String Remark;
	private String Weight;
	
	public ShipmentContent(Element objElement) {
		parseSelf(objElement);
	}
	
	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void parse(Element objElement) {
		Amount = objElement.getChildText("Amount");
		UnitPrice = objElement.getChildText("UnitPrice");
		TotalPrice = objElement.getChildText("TotalPrice");
		Currency = objElement.getChildText("Currency");
		Description = objElement.getChildText("Description");
		DescriptionCN = objElement.getChildText("DescriptionCN");
		Attacheinfo = objElement.getChildText("Attacheinfo");
		Remark = objElement.getChildText("Remark");
		Weight = objElement.getChildText("Weight");
	}

	public String getAmount() {
		return Amount;
	}

	public String getCurrency() {
		return Currency;
	}

	public String getDescription() {
		return Description;
	}
	
	public String getDescriptionCN() {
		return DescriptionCN;
	}	
	
	public String getTotalPrice() {
		return TotalPrice;
	}

	public String getUnitPrice() {
		return UnitPrice;
	}

	public String getAttacheinfo() {
		return Attacheinfo;
	}

	public String getRemark() {
		return Remark;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}
	
	
}
