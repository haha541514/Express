package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class AlldhldistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AlldhldistrictColumns() {
		m_astrColumns = new String[7];
	}
	
	public AlldhldistrictColumns(String dhlDd_cityname, 
            String dhlDd_hubcode, String dhlDd_startpostcode, 
            String dhlDd_endtpostcode, String dhlDd_locationcode, 
            String dhlDd_statecode, String dhlDd_statename){
		m_astrColumns = new String[7];
		setDhldd_cityname(dhlDd_cityname);
		setDhldd_hubcode(dhlDd_hubcode);
		setDhldd_startpostcode(dhlDd_startpostcode);
		setDhldd_endtpostcode(dhlDd_endtpostcode);
		setDhldd_locationcode(dhlDd_locationcode);
		setDhldd_statecode(dhlDd_statecode);
		setDhldd_statename(dhlDd_statename);
	}

	public void setDhldd_cityname(String dhlDd_cityname) {
		this.setField(0, dhlDd_cityname);
	}

	public String getDhldd_cityname() {
		return this.getField(0);
	}

	public void setDhldd_hubcode(String dhlDd_hubcode) {
		this.setField(1, dhlDd_hubcode);
	}

	public String getDhldd_hubcode() {
		return this.getField(1);
	}

	public void setDhldd_startpostcode(String dhlDd_startpostcode) {
		this.setField(2, dhlDd_startpostcode);
	}

	public String getDhldd_startpostcode() {
		return this.getField(2);
	}

	public void setDhldd_endtpostcode(String dhlDd_endtpostcode) {
		this.setField(3, dhlDd_endtpostcode);
	}

	public String getDhldd_endtpostcode() {
		return this.getField(3);
	}

	public void setDhldd_locationcode(String dhlDd_locationcode) {
		this.setField(4, dhlDd_locationcode);
	}

	public String getDhldd_locationcode() {
		return this.getField(4);
	}

	public void setDhldd_statecode(String dhlDd_statecode) {
		this.setField(5, dhlDd_statecode);
	}

	public String getDhldd_statecode() {
		return this.getField(5);
	}

	public void setDhldd_statename(String dhlDd_statename) {
		this.setField(6, dhlDd_statename);
	}

	public String getDhldd_statename() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
