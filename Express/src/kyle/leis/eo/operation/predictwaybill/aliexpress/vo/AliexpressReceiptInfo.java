package kyle.leis.eo.operation.predictwaybill.aliexpress.vo;

public class AliexpressReceiptInfo {
	private String zip; // 邮编
	private String address2; // 地址2
	private String detailAddress; // 详细地址
	private String country; // 国家
	private String city; // 城市
	private String phoneNumber; // 电话号码
	private String province; // 州
	private String phoneArea; // 电话区号
	private String phoneCountry; // 国家区号
	private String contactPerson; // 收件人
	private String mobileNo; // 手机号
	
	private String error_code;
	private String error_message;

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getDetailAddress() {
		return detailAddress;
	}

	public void setDetailAddress(String detailAddress) {
		this.detailAddress = detailAddress;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getPhoneArea() {
		return phoneArea;
	}

	public void setPhoneArea(String phoneArea) {
		this.phoneArea = phoneArea;
	}

	public String getPhoneCountry() {
		return phoneCountry;
	}

	public void setPhoneCountry(String phoneCountry) {
		this.phoneCountry = phoneCountry;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String errorCode) {
		error_code = errorCode;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String errorMessage) {
		error_message = errorMessage;
	}
	
}
