package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import org.jdom.Element;

import kyle.common.conf.elementconfig.AElementConfig;

public class Info extends AElementConfig {
	private String OrderID;
	

	private String PackageOrDoc;
	private String InvoiceChoices;
	private String InsuranceChoices;	
	private String InsuranceAmount;
	private String InsuranceBeneficiary;
	private String Weight;
	private String VolumeWeight;
	private String FeeWeight;
	private String Amount;	
	private String D_Customs;
	private String ReceivingStation;
	private String Currency;
	
	public Info(Element objElement) {
		parseFather(objElement, "Info");
	}

	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void parse(Element objElement) {
		OrderID = objElement.getChildText("OrderID");
		PackageOrDoc = objElement.getChildText("PackageOrDoc");
		InvoiceChoices = objElement.getChildText("InvoiceChoices");
		InsuranceChoices = objElement.getChildText("InsuranceChoices");
		InsuranceAmount = objElement.getChildText("InsuranceAmount");
		InsuranceBeneficiary = objElement.getChildText("InsuranceBeneficiary");
		Weight = objElement.getChildText("Weight");
		VolumeWeight = objElement.getChildText("VolumeWeight");
		FeeWeight = objElement.getChildText("FeeWeight");
		Amount = objElement.getChildText("Amount ");
		D_Customs = objElement.getChildText("D_Customs");		
		ReceivingStation = objElement.getChildText("ReceivingStation");
		Currency = objElement.getChildText("ReceivingStation");
	
	}
	public String getOrderID() {
		return OrderID;
	}

	public void setOrderID(String orderID) {
		OrderID = orderID;
	}

	public String getPackageOrDoc() {
		return PackageOrDoc;
	}

	public void setPackageOrDoc(String packageOrDoc) {
		PackageOrDoc = packageOrDoc;
	}

	public String getInvoiceChoices() {
		return InvoiceChoices;
	}

	public void setInvoiceChoices(String invoiceChoices) {
		InvoiceChoices = invoiceChoices;
	}

	public String getInsuranceChoices() {
		return InsuranceChoices;
	}

	public void setInsuranceChoices(String insuranceChoices) {
		InsuranceChoices = insuranceChoices;
	}

	public String getInsuranceAmount() {
		return InsuranceAmount;
	}

	public void setInsuranceAmount(String insuranceAmount) {
		InsuranceAmount = insuranceAmount;
	}

	public String getInsuranceBeneficiary() {
		return InsuranceBeneficiary;
	}

	public void setInsuranceBeneficiary(String insuranceBeneficiary) {
		InsuranceBeneficiary = insuranceBeneficiary;
	}

	public String getWeight() {
		return Weight;
	}

	public void setWeight(String weight) {
		Weight = weight;
	}

	public String getVolumeWeight() {
		return VolumeWeight;
	}

	public void setVolumeWeight(String volumeWeight) {
		VolumeWeight = volumeWeight;
	}

	public String getFeeWeight() {
		return FeeWeight;
	}

	public void setFeeWeight(String feeWeight) {
		FeeWeight = feeWeight;
	}

	public String getAmount() {
		return Amount;
	}

	public void setAmount(String amount) {
		Amount = amount;
	}

	public String getD_Customs() {
		return D_Customs;
	}

	public void setD_Customs(String dCustoms) {
		D_Customs = dCustoms;
	}

	public String getReceivingStation() {
		return ReceivingStation;
	}

	public void setReceivingStation(String receivingStation) {
		ReceivingStation = receivingStation;
	}

	public String getCurrency() {
		return Currency;
	}

	public void setCurrency(String currency) {
		Currency = currency;
	}
}
