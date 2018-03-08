package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class DhlremotedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DhlremotedistrictColumns() {
		m_astrColumns = new String[6];
	}
	
	public DhlremotedistrictColumns(String drdDrd_code, 
            String drdDrd_nationcode, String drdDrd_nationname, 
            String drdDrd_statename, String drdDrd_cityname, 
            String drdDrd_postcode){
		m_astrColumns = new String[6];
		setDrddrd_code(drdDrd_code);
		setDrddrd_nationcode(drdDrd_nationcode);
		setDrddrd_nationname(drdDrd_nationname);
		setDrddrd_statename(drdDrd_statename);
		setDrddrd_cityname(drdDrd_cityname);
		setDrddrd_postcode(drdDrd_postcode);
	}

	public void setDrddrd_code(String drdDrd_code) {
		this.setField(0, drdDrd_code);
	}

	public String getDrddrd_code() {
		return this.getField(0);
	}

	public void setDrddrd_nationcode(String drdDrd_nationcode) {
		this.setField(1, drdDrd_nationcode);
	}

	public String getDrddrd_nationcode() {
		return this.getField(1);
	}

	public void setDrddrd_nationname(String drdDrd_nationname) {
		this.setField(2, drdDrd_nationname);
	}

	public String getDrddrd_nationname() {
		return this.getField(2);
	}

	public void setDrddrd_statename(String drdDrd_statename) {
		this.setField(3, drdDrd_statename);
	}

	public String getDrddrd_statename() {
		return this.getField(3);
	}

	public void setDrddrd_cityname(String drdDrd_cityname) {
		this.setField(4, drdDrd_cityname);
	}

	public String getDrddrd_cityname() {
		return this.getField(4);
	}

	public void setDrddrd_postcode(String drdDrd_postcode) {
		this.setField(5, drdDrd_postcode);
	}

	public String getDrddrd_postcode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
