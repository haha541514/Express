package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TntremotedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TntremotedistrictColumns() {
		m_astrColumns = new String[5];
	}
	
	public TntremotedistrictColumns(String trdTrd_code, 
            String trdTrd_nationcode, String trdTrd_nationname, 
            String trdTrd_startpostcode, String trdTrd_endpostcode){
		m_astrColumns = new String[5];
		setTrdtrd_code(trdTrd_code);
		setTrdtrd_nationcode(trdTrd_nationcode);
		setTrdtrd_nationname(trdTrd_nationname);
		setTrdtrd_startpostcode(trdTrd_startpostcode);
		setTrdtrd_endpostcode(trdTrd_endpostcode);
	}

	public void setTrdtrd_code(String trdTrd_code) {
		this.setField(0, trdTrd_code);
	}

	public String getTrdtrd_code() {
		return this.getField(0);
	}

	public void setTrdtrd_nationcode(String trdTrd_nationcode) {
		this.setField(1, trdTrd_nationcode);
	}

	public String getTrdtrd_nationcode() {
		return this.getField(1);
	}

	public void setTrdtrd_nationname(String trdTrd_nationname) {
		this.setField(2, trdTrd_nationname);
	}

	public String getTrdtrd_nationname() {
		return this.getField(2);
	}

	public void setTrdtrd_startpostcode(String trdTrd_startpostcode) {
		this.setField(3, trdTrd_startpostcode);
	}

	public String getTrdtrd_startpostcode() {
		return this.getField(3);
	}

	public void setTrdtrd_endpostcode(String trdTrd_endpostcode) {
		this.setField(4, trdTrd_endpostcode);
	}

	public String getTrdtrd_endpostcode() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
