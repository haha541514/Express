package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DistrictandcountryColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DistrictandcountryColumns() {
		m_astrColumns = new String[3];
	}
	
	public DistrictandcountryColumns(String dtDt_code, 
            String dtDt_hubcode, String dtDt_name){
		m_astrColumns = new String[3];
		setDtdt_code(dtDt_code);
		setDtdt_hubcode(dtDt_hubcode);
		setDtdt_name(dtDt_name);
	}

	public void setDtdt_code(String dtDt_code) {
		this.setField(0, dtDt_code);
	}

	public String getDtdt_code() {
		return this.getField(0);
	}

	public void setDtdt_hubcode(String dtDt_hubcode) {
		this.setField(1, dtDt_hubcode);
	}

	public String getDtdt_hubcode() {
		return this.getField(1);
	}

	public void setDtdt_name(String dtDt_name) {
		this.setField(2, dtDt_name);
	}

	public String getDtdt_name() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
