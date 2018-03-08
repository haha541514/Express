package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class AlldistictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AlldistictColumns() {
		m_astrColumns = new String[5];
	}
	
	public AlldistictColumns(String dtDt_code, 
            String dtDt_hubcode, String dtDt_name, 
            String dtDt_ename, String dtDt_statename){
		m_astrColumns = new String[5];
		setDtdt_code(dtDt_code);
		setDtdt_hubcode(dtDt_hubcode);
		setDtdt_name(dtDt_name);
		setDtdt_ename(dtDt_ename);
		setDtdt_statename(dtDt_statename);
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

	public void setDtdt_ename(String dtDt_ename) {
		this.setField(3, dtDt_ename);
	}

	public String getDtdt_ename() {
		return this.getField(3);
	}

	public void setDtdt_statename(String dtDt_statename) {
		this.setField(4, dtDt_statename);
	}

	public String getDtdt_statename() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
