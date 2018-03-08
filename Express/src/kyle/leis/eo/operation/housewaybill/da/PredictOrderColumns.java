package kyle.leis.eo.operation.housewaybill.da;

public class PredictOrderColumns {

	//�˵�����
	private String strCwcwcode;
	
	//������
	private String  strCwcustomerewbcode;
	
	//��Ʒ��
	private String strPk_code;
	
	//��˾
	private String strCocode;
	
	//������
	private String strCusCocode;

	//Ŀ�Ĺ���(Ŀ�ĳ��д���)
	private String strDtcode;
	
	// �ռ���Ӣ����(������)
	private String strHwconsigneename;
	
	private String m_strHwconsigneecompanyname;
	
	// �ռ��˵绰
	private String strHwconsigneetelephone;
	
	// Ŀ���ʱ�
	private String strCwpostcodedestination;
	
	//�ռ��˵�ַ
	private String strHwconsigneeaddress1;
	private String strHwconsigneeaddress2;
	private String strHwconsigneeaddress3;
	
	//��������
	private String[] strCiename;
	
	//�������
	private String[] strCipieces;

	// ���ﵥ��
	private String[] strCiunitprice;
	
	
	// �����Ϣ
	private String[] astrAttacheInfo;	
	
	//ë��
	private String strCwgrossweight;
	
	//����(����)
	private String strCwpieces;
	
	//��ע 
	private String strHwremark; 
	
	//�������
	private String strAdddate;

	//�ͻ�
	private String strCustomerCode;
	
	//������
	private String strSupplierCode;
	
	
	private String m_strShipperInfoLabelcode;
	
	private String m_strEecode;
	
	public PredictOrderColumns() {
		
	}
	
	
	public String getShipperInfoLabelcode() {
		return m_strShipperInfoLabelcode;
	}

	public void setShipperInfoLabelcode(String strShipperInfoLabelcode) {
		m_strShipperInfoLabelcode = strShipperInfoLabelcode;
	}	
	
	
	public String getStrCwcwcode() {
		return strCwcwcode;
	}

	public void setStrCwcwcode(String strCwcwcode) {
		this.strCwcwcode = strCwcwcode;
	}
	
	public String getStrCwcustomerewbcode() {
		return strCwcustomerewbcode;
	}

	public void setStrCwcustomerewbcode(String strCwcustomerewbcode) {
		this.strCwcustomerewbcode = strCwcustomerewbcode;
	}

	public String getStrPk_code() {
		return strPk_code;
	}

	public void setStrPk_code(String strPk_code) {
		this.strPk_code = strPk_code;
	}

	public String getStrCocode() {
		return strCocode;
	}

	public void setStrCocode(String strCocode) {
		this.strCocode = strCocode;
	}

	public String getStrDtcode() {
		return strDtcode;
	}

	public void setStrDtcode(String strDtcode) {
		this.strDtcode = strDtcode;
	}

	public String getStrHwconsigneename() {
		return strHwconsigneename;
	}

	public void setStrHwconsigneename(String strHwconsigneename) {
		this.strHwconsigneename = strHwconsigneename;
	}
	
	public String getHwconsigneecompanyname() {
		return m_strHwconsigneecompanyname;
	}

	public void setHwconsigneecompanyname(String strHwconsigneecompanyname) {
		this.m_strHwconsigneecompanyname = strHwconsigneecompanyname;
	}	
	
	public String getStrHwconsigneetelephone() {
		return strHwconsigneetelephone;
	}

	public void setStrHwconsigneetelephone(String strHwconsigneetelephone) {
		this.strHwconsigneetelephone = strHwconsigneetelephone;
	}

	public String getStrCwpostcodedestination() {
		return strCwpostcodedestination;
	}

	public void setStrCwpostcodedestination(String strCwpostcodedestination) {
		this.strCwpostcodedestination = strCwpostcodedestination;
	}

	public String getStrHwconsigneeaddress1() {
		return strHwconsigneeaddress1;
	}

	public void setStrHwconsigneeaddress1(String strHwconsigneeaddress1) {
		this.strHwconsigneeaddress1 = strHwconsigneeaddress1;
	}

	public String getStrHwconsigneeaddress2() {
		return strHwconsigneeaddress2;
	}

	public void setStrHwconsigneeaddress2(String strHwconsigneeaddress2) {
		this.strHwconsigneeaddress2 = strHwconsigneeaddress2;
	}

	public String getStrHwconsigneeaddress3() {
		return strHwconsigneeaddress3;
	}

	public void setStrHwconsigneeaddress3(String strHwconsigneeaddress3) {
		this.strHwconsigneeaddress3 = strHwconsigneeaddress3;
	}

	public String[] getStrCiename() {
		return strCiename;
	}

	public void setStrCiename(String[] strCiename) {
		this.strCiename = strCiename;
	}

	public String[] getStrCipieces() {
		return strCipieces;
	}

	public void setStrCipieces(String[] strCipieces) {
		this.strCipieces = strCipieces;
	}

	public String[] getStrCiunitprice() {
		return strCiunitprice;
	}

	public void setStrCiunitprice(String[] strCiunitprice) {
		this.strCiunitprice = strCiunitprice;
	}
	
	public String[] getAttacheInfo() {
		return astrAttacheInfo;
	}

	public void setAttacheInfo(String[] astrAttacheInfo) {
		this.astrAttacheInfo = astrAttacheInfo;
	}	
	
	public String getStrCwgrossweight() {
		return strCwgrossweight;
	}

	public void setStrCwgrossweight(String strCwgrossweight) {
		this.strCwgrossweight = strCwgrossweight;
	}

	public String getStrCwpieces() {
		return strCwpieces;
	}

	public void setStrCwpieces(String strCwpieces) {
		this.strCwpieces = strCwpieces;
	}

	public String getStrHwremark() {
		return strHwremark;
	}

	public void setStrHwremark(String strHwremark) {
		this.strHwremark = strHwremark;
	}

	public String getStrAdddate() {
		return strAdddate;
	}

	public void setStrAdddate(String strAdddate) {
		this.strAdddate = strAdddate;
	}
	
	public String getStrCusCocode() {
		return strCusCocode;
	}

	public void setStrCusCocode(String strCusCocode) {
		this.strCusCocode = strCusCocode;
	}
	
	public String getStrCustomerCode() {
		return strCustomerCode;
	}

	public void setStrCustomerCode(String strCustomerCode) {
		this.strCustomerCode = strCustomerCode;
	}

	public String getStrSupplierCode() {
		return strSupplierCode;
	}

	public void setStrSupplierCode(String strSupplierCode) {
		this.strSupplierCode = strSupplierCode;
	}
	
	public String getEeCode() {
		return m_strEecode;
	}

	public void setEeCode(String strEecode) {
		this.m_strEecode = strEecode;
	}	
	
}
