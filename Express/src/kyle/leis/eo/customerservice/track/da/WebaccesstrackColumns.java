package kyle.leis.eo.customerservice.track.da;

import java.util.List;

public class WebaccesstrackColumns {
	
	public WebaccesstrackColumns(){
	}
	//ҵ�����
	private String strCwcode;
	
	//�ͻ��˵���
	private String strCwcustomerewbcode;
	
	// �˵���(�������˵���)
	private String strCwserverewbcode;
	
	// �˵���(�������˵���)
	private String strHawbcode;
	
	//��������
	private String strPksname;
	
	//����
	private String strCwchargeweight;
	
	//Ŀ�Ĺ���
	private String strDtcode;
	
	//����״̬
	private String strWbtsname;
	
	//����״̬(Ӣ��)
	private String strWbtsename;
	
	//�켣��������
	private String strLatesttrackdesc;
	
	//�켣����
	private List<WaybilltrackColumns>  listWBTColumns;
	
	//Դʼ��(�켣)
	private String strOrigin;
	
	//Ŀ�ĵ�(�켣)
	private String strDdtename;
	
	//ǩ����(�켣)
	private String strSignatory;
	
	private String pieces; // ����
	
	private String countryEname; // ����Ӣ��
	

	public String getStrCwcode() {
		return strCwcode;
	}

	public void setStrCwcode(String strCwcode) {
		this.strCwcode = strCwcode;
	}

	public String getStrCwcustomerewbcode() {
		return strCwcustomerewbcode;
	}

	public void setStrCwcustomerewbcode(String strCwcustomerewbcode) {
		this.strCwcustomerewbcode = strCwcustomerewbcode;
	}

	public String getStrCwserverewbcode() {
		return strCwserverewbcode;
	}

	public void setStrCwserverewbcode(String strCwserverewbcode) {
		this.strCwserverewbcode = strCwserverewbcode;
	}
	
	public String getStrHawbcode() {
		return strHawbcode;
	}

	public void setStrHawbcode(String strHawbcode) {
		this.strHawbcode = strHawbcode;
	}
	public String getStrPksname() {
		return strPksname;
	}

	public void setStrPksname(String strPksname) {
		this.strPksname = strPksname;
	}

	public String getStrCwchargeweight() {
		return strCwchargeweight;
	}

	public void setStrCwchargeweight(String strCwchargeweight) {
		this.strCwchargeweight = strCwchargeweight;
	}

	public String getStrDtcode() {
		return strDtcode;
	}

	public void setStrDtcode(String strDtcode) {
		this.strDtcode = strDtcode;
	}

	public String getStrWbtsname() {
		return strWbtsname;
	}

	public void setStrWbtsname(String strWbtsname) {
		this.strWbtsname = strWbtsname;
	}

	public String getStrWbtsename() {
		return strWbtsename;
	}

	public void setStrWbtsename(String strWbtsename) {
		this.strWbtsename = strWbtsename;
	}

	public String getStrLatesttrackdesc() {
		return strLatesttrackdesc;
	}

	public void setStrLatesttrackdesc(String strLatesttrackdesc) {
		this.strLatesttrackdesc = strLatesttrackdesc;
	}
	
	public List<WaybilltrackColumns> getListWBTColumns() {
		return listWBTColumns;
	}

	public void setListWBTColumns(List<WaybilltrackColumns> listWBTColumns) {
		this.listWBTColumns = listWBTColumns;
	}

	public String getStrOrigin() {
		return strOrigin;
	}

	public void setStrOrigin(String strOrigin) {
		this.strOrigin = strOrigin;
	}

	public String getStrDdtename() {
		return strDdtename;
	}

	public void setStrDdtename(String strDdtename) {
		this.strDdtename = strDdtename;
	}

	public String getStrSignatory() {
		return strSignatory;
	}

	public void setStrSignatory(String strSignatory) {
		this.strSignatory = strSignatory;
	}

	public String getPieces() {
		return pieces;
	}

	public void setPieces(String pieces) {
		this.pieces = pieces;
	}

	public String getCountryEname() {
		return countryEname;
	}

	public void setCountryEname(String countryEname) {
		this.countryEname = countryEname;
	}
	
}
