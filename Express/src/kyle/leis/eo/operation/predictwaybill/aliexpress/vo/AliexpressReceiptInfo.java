package kyle.leis.eo.operation.predictwaybill.aliexpress.vo;

public class AliexpressReceiptInfo {
	private String zip; // �ʱ�
	private String address2; // ��ַ2
	private String detailAddress; // ��ϸ��ַ
	private String country; // ����
	private String city; // ����
	private String phoneNumber; // �绰����
	private String province; // ��
	private String phoneArea; // �绰����
	private String phoneCountry; // ��������
	private String contactPerson; // �ռ���
	private String mobileNo; // �ֻ���
	
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
