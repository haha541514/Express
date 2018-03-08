package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FdxremotedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FdxremotedistrictColumns() {
		m_astrColumns = new String[6];
	}
	
	public FdxremotedistrictColumns(String frdFrd_code, 
            String frdFrd_nationcode, String frdFrd_nationname, 
            String frdFrd_cityname, String frdFrd_startpostcode, 
            String frdFrd_endpostcode){
		m_astrColumns = new String[6];
		setFrdfrd_code(frdFrd_code);
		setFrdfrd_nationcode(frdFrd_nationcode);
		setFrdfrd_nationname(frdFrd_nationname);
		setFrdfrd_cityname(frdFrd_cityname);
		setFrdfrd_startpostcode(frdFrd_startpostcode);
		setFrdfrd_endpostcode(frdFrd_endpostcode);
	}

	public void setFrdfrd_code(String frdFrd_code) {
		this.setField(0, frdFrd_code);
	}

	public String getFrdfrd_code() {
		return this.getField(0);
	}

	public void setFrdfrd_nationcode(String frdFrd_nationcode) {
		this.setField(1, frdFrd_nationcode);
	}

	public String getFrdfrd_nationcode() {
		return this.getField(1);
	}

	public void setFrdfrd_nationname(String frdFrd_nationname) {
		this.setField(2, frdFrd_nationname);
	}

	public String getFrdfrd_nationname() {
		return this.getField(2);
	}

	public void setFrdfrd_cityname(String frdFrd_cityname) {
		this.setField(3, frdFrd_cityname);
	}

	public String getFrdfrd_cityname() {
		return this.getField(3);
	}

	public void setFrdfrd_startpostcode(String frdFrd_startpostcode) {
		this.setField(4, frdFrd_startpostcode);
	}

	public String getFrdfrd_startpostcode() {
		return this.getField(4);
	}

	public void setFrdfrd_endpostcode(String frdFrd_endpostcode) {
		this.setField(5, frdFrd_endpostcode);
	}

	public String getFrdfrd_endpostcode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
