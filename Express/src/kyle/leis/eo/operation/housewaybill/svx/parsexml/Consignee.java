package kyle.leis.eo.operation.housewaybill.svx.parsexml;

import kyle.common.conf.elementconfig.AElementConfig;

import org.jdom.Element;

public class Consignee extends AElementConfig {
	
	private String PersonName;
	private String CompanyName;
	private String Address1;
	private String Address2;	
	private String Address3;
	private String City;
	private String PostalCode;
	private String CountryCode;
	private String PhoneNumber;
	private String FaxNumber;	
	private String PackageType;
	private String DeliveryType;
	private String CargoKind;
	private String BatteryKind;		
	private String DutyPaid;
	
	public Consignee(Element objElement) {
		parseFather(objElement, "Consignee");
	}
	
	@Override
	protected void check(StringBuffer sbCheck) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void parse(Element objElement) {
		PersonName = objElement.getChildText("PersonName");
		CompanyName = objElement.getChildText("CompanyName");
		Address1 = objElement.getChildText("Address1");
		Address2 = objElement.getChildText("Address2");
		Address3 = objElement.getChildText("Address3");
		City = objElement.getChildText("City");
		PostalCode = objElement.getChildText("PostalCode");
		CountryCode = objElement.getChildText("CountryCode");
		PhoneNumber = objElement.getChildText("PhoneNumber");
		FaxNumber = objElement.getChildText("FaxNumber");
		PackageType = objElement.getChildText("FaxNumber");
		
		DeliveryType = objElement.getChildText("DeliveryType");
		CargoKind = objElement.getChildText("CargoKind");
		BatteryKind = objElement.getChildText("BatteryKind");
		
		DutyPaid = objElement.getChildText("DutyPaid");
	}

	public String getAddress1() {
		return Address1;
	}

	public String getAddress2() {
		return Address2;
	}

	public String getAddress3() {
		return Address3;
	}

	public String getCity() {
		return City;
	}

	public String getCompanyName() {
		return CompanyName;
	}

	public String getCountryCode() {
		return CountryCode;
	}

	public String getFaxNumber() {
		return FaxNumber;
	}

	public String getPersonName() {
		return PersonName;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}

	public String getPostalCode() {
		return PostalCode;
	}

	public String getBatteryKind() {
		return BatteryKind;
	}

	public void setBatteryKind(String batteryKind) {
		BatteryKind = batteryKind;
	}

	public String getCargoKind() {
		return CargoKind;
	}

	public void setCargoKind(String cargoKind) {
		CargoKind = cargoKind;
	}

	public String getDeliveryType() {
		return DeliveryType;
	}

	public void setDeliveryType(String deliveryType) {
		DeliveryType = deliveryType;
	}

	public String getPackageType() {
		return PackageType;
	}

	public void setPackageType(String packageType) {
		PackageType = packageType;
	}

	public String getDutyPaid() {
		return DutyPaid;
	}

	public void setDutyPaid(String dutyPaid) {
		DutyPaid = dutyPaid;
	}
	
	
}
