package kyle.leis.eo.customerservice.track.da;

import java.util.List;

public class WebaccesstrackColumns {
	
	public WebaccesstrackColumns(){
	}
	//业务代码
	private String strCwcode;
	
	//客户运单号
	private String strCwcustomerewbcode;
	
	// 运单号(服务商运单号)
	private String strCwserverewbcode;
	
	// 运单号(服务商运单号)
	private String strHawbcode;
	
	//货物类型
	private String strPksname;
	
	//重量
	private String strCwchargeweight;
	
	//目的国家
	private String strDtcode;
	
	//最新状态
	private String strWbtsname;
	
	//最新状态(英文)
	private String strWbtsename;
	
	//轨迹最新描述
	private String strLatesttrackdesc;
	
	//轨迹详情
	private List<WaybilltrackColumns>  listWBTColumns;
	
	//源始地(轨迹)
	private String strOrigin;
	
	//目的地(轨迹)
	private String strDdtename;
	
	//签收人(轨迹)
	private String strSignatory;
	
	private String pieces; // 件数
	
	private String countryEname; // 国家英文
	

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
