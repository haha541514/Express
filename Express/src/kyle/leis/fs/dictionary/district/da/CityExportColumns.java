package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class CityExportColumns extends GeneralColumns implements Serializable{

	public CityExportColumns(){
		m_astrColumns = new String[7];
	}
	
	public CityExportColumns(String hubcode, String stateEname, String sName,String ctName,
			String ctEname, String startPostCode, String endPostCode) {
		m_astrColumns = new String[7];
		setTdcHubcode(hubcode);
		setTdcStateEname(stateEname);
		setTdcsname(sName);
		setTdcctname(ctName);
		setTdcctename(ctEname);
		setTdcctstartpostcode(startPostCode);
		setTdcctendpostcode(endPostCode);
	}
	
	public void setTdcHubcode(String tdcCtcode) {
		this.setField(0, tdcCtcode);
	}

	public String getTdcHubcode() {
		return this.getField(0);
	}

	public void setTdcStateEname(String tdcCtsname) {
		this.setField(1, tdcCtsname);
	}

	public String getTdcStateEname() {
		return this.getField(1);
	}
	
	public void setTdcsname(String tdcsname){
		this.setField(2, tdcsname);
		
	}
	public String getTdcsname(){
		return this.getField(2);
	}
	

	public void setTdcctname(String tdcCtname) {
		this.setField(3, tdcCtname);
	}

	public String getTdcctname() {
		return this.getField(3);
	}

	public void setTdcctename(String tdcCtename) {
		this.setField(4, tdcCtename);
	}

	public String getTdcctename() {
		return this.getField(4);
	}

	public void setTdcctstartpostcode(String tdcCtstartpostcode) {
		this.setField(5, tdcCtstartpostcode);
	}

	public String getTdcctstartpostcode() {
		return this.getField(5);
	}

	public void setTdcctendpostcode(String tdcCtendpostcode) {
		this.setField(6, tdcCtendpostcode);
	}

	public String getTdcctendpostcode() {
		return this.getField(6);
	}


	
}
