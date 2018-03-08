package kyle.leis.eo.operation.housewaybill.da;

public class PredictOrderColumns {

	//运单主键
	private String strCwcwcode;
	
	//订单号
	private String  strCwcustomerewbcode;
	
	//产品号
	private String strPk_code;
	
	//公司
	private String strCocode;
	
	//代理商
	private String strCusCocode;

	//目的国家(目的城市代码)
	private String strDtcode;
	
	// 收件人英文名(中文名)
	private String strHwconsigneename;
	
	private String m_strHwconsigneecompanyname;
	
	// 收件人电话
	private String strHwconsigneetelephone;
	
	// 目的邮编
	private String strCwpostcodedestination;
	
	//收件人地址
	private String strHwconsigneeaddress1;
	private String strHwconsigneeaddress2;
	private String strHwconsigneeaddress3;
	
	//货物名称
	private String[] strCiename;
	
	//货物件数
	private String[] strCipieces;

	// 货物单价
	private String[] strCiunitprice;
	
	
	// 配货信息
	private String[] astrAttacheInfo;	
	
	//毛重
	private String strCwgrossweight;
	
	//件数(主单)
	private String strCwpieces;
	
	//备注 
	private String strHwremark; 
	
	//添加日期
	private String strAdddate;

	//客户
	private String strCustomerCode;
	
	//服务商
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
