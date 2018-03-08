package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TarazzdistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TarazzdistrictColumns() {
		m_astrColumns = new String[3];
	}
	
	public TarazzdistrictColumns(String tdTd_postcode, 
            String tdTd_localityname, String tdTd_statecode){
		m_astrColumns = new String[3];
		setTdtd_postcode(tdTd_postcode);
		setTdtd_localityname(tdTd_localityname);
		setTdtd_statecode(tdTd_statecode);
	}

	public void setTdtd_postcode(String tdTd_postcode) {
		this.setField(0, tdTd_postcode);
	}

	public String getTdtd_postcode() {
		return this.getField(0);
	}

	public void setTdtd_localityname(String tdTd_localityname) {
		this.setField(1, tdTd_localityname);
	}

	public String getTdtd_localityname() {
		return this.getField(1);
	}

	public void setTdtd_statecode(String tdTd_statecode) {
		this.setField(2, tdTd_statecode);
	}

	public String getTdtd_statecode() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
