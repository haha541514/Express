package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class DhldistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DhldistrictColumns() {
		m_astrColumns = new String[4];
	}
	
	public DhldistrictColumns(String dhlDd_cityname, 
            String dhlDd_hubcode, String dhlDd_startpostcode, 
            String dhlDd_endtpostcode){
		m_astrColumns = new String[4];
		setDhldd_cityname(dhlDd_cityname);
		setDhldd_hubcode(dhlDd_hubcode);
		setDhldd_startpostcode(dhlDd_startpostcode);
		setDhldd_endtpostcode(dhlDd_endtpostcode);
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

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
