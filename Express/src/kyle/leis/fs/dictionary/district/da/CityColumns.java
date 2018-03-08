package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CityColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CityColumns() {
		m_astrColumns = new String[14];
	}
	
	public CityColumns(String ciCtcode, 
            String ciCtsname, String ciCtname, 
            String ciCtename, String ciCtstartpostcode, 
            String ciCtendpostcode, String diDtcode, 
            String diDthubcode, String diDtname, 
            String diDtename, String stStcode, 
            String stStsname, String stStname, 
            String stStename){
		m_astrColumns = new String[14];
		setCictcode(ciCtcode);
		setCictsname(ciCtsname);
		setCictname(ciCtname);
		setCictename(ciCtename);
		setCictstartpostcode(ciCtstartpostcode);
		setCictendpostcode(ciCtendpostcode);
		setDidtcode(diDtcode);
		setDidthubcode(diDthubcode);
		setDidtname(diDtname);
		setDidtename(diDtename);
		setStstcode(stStcode);
		setStstsname(stStsname);
		setStstname(stStname);
		setStstename(stStename);
	}

	public void setCictcode(String ciCtcode) {
		this.setField(0, ciCtcode);
	}

	public String getCictcode() {
		return this.getField(0);
	}

	public void setCictsname(String ciCtsname) {
		this.setField(1, ciCtsname);
	}

	public String getCictsname() {
		return this.getField(1);
	}

	public void setCictname(String ciCtname) {
		this.setField(2, ciCtname);
	}

	public String getCictname() {
		return this.getField(2);
	}

	public void setCictename(String ciCtename) {
		this.setField(3, ciCtename);
	}

	public String getCictename() {
		return this.getField(3);
	}

	public void setCictstartpostcode(String ciCtstartpostcode) {
		this.setField(4, ciCtstartpostcode);
	}

	public String getCictstartpostcode() {
		return this.getField(4);
	}

	public void setCictendpostcode(String ciCtendpostcode) {
		this.setField(5, ciCtendpostcode);
	}

	public String getCictendpostcode() {
		return this.getField(5);
	}

	public void setDidtcode(String diDtcode) {
		this.setField(6, diDtcode);
	}

	public String getDidtcode() {
		return this.getField(6);
	}

	public void setDidthubcode(String diDthubcode) {
		this.setField(7, diDthubcode);
	}

	public String getDidthubcode() {
		return this.getField(7);
	}

	public void setDidtname(String diDtname) {
		this.setField(8, diDtname);
	}

	public String getDidtname() {
		return this.getField(8);
	}

	public void setDidtename(String diDtename) {
		this.setField(9, diDtename);
	}

	public String getDidtename() {
		return this.getField(9);
	}

	public void setStstcode(String stStcode) {
		this.setField(10, stStcode);
	}

	public String getStstcode() {
		return this.getField(10);
	}

	public void setStstsname(String stStsname) {
		this.setField(11, stStsname);
	}

	public String getStstsname() {
		return this.getField(11);
	}

	public void setStstname(String stStname) {
		this.setField(12, stStname);
	}

	public String getStstname() {
		return this.getField(12);
	}

	public void setStstename(String stStename) {
		this.setField(13, stStename);
	}

	public String getStstename() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
