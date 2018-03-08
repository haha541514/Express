package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class StateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public StateColumns() {
		m_astrColumns = new String[8];
	}
	
	public StateColumns(String stStcode, 
            String stStsname, String stStname, 
            String stStename, String diDtcode, 
            String diDthubcode, String diDtname, 
            String diDtename){
		m_astrColumns = new String[8];
		setStstcode(stStcode);
		setStstsname(stStsname);
		setStstname(stStname);
		setStstename(stStename);
		setDidtcode(diDtcode);
		setDidthubcode(diDthubcode);
		setDidtname(diDtname);
		setDidtename(diDtename);
	}

	public void setStstcode(String stStcode) {
		this.setField(0, stStcode);
	}

	public String getStstcode() {
		return this.getField(0);
	}

	public void setStstsname(String stStsname) {
		this.setField(1, stStsname);
	}

	public String getStstsname() {
		return this.getField(1);
	}

	public void setStstname(String stStname) {
		this.setField(2, stStname);
	}

	public String getStstname() {
		return this.getField(2);
	}

	public void setStstename(String stStename) {
		this.setField(3, stStename);
	}

	public String getStstename() {
		return this.getField(3);
	}

	public void setDidtcode(String diDtcode) {
		this.setField(4, diDtcode);
	}

	public String getDidtcode() {
		return this.getField(4);
	}

	public void setDidthubcode(String diDthubcode) {
		this.setField(5, diDthubcode);
	}

	public String getDidthubcode() {
		return this.getField(5);
	}

	public void setDidtname(String diDtname) {
		this.setField(6, diDtname);
	}

	public String getDidtname() {
		return this.getField(6);
	}

	public void setDidtename(String diDtename) {
		this.setField(7, diDtename);
	}

	public String getDidtename() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
