package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DhlairportlocationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DhlairportlocationColumns() {
		m_astrColumns = new String[4];
	}
	
	public DhlairportlocationColumns(String apAp_hubcode, 
            String apAp_ename, String apAp_cname, 
            String dtDt_name){
		m_astrColumns = new String[4];
		setApap_hubcode(apAp_hubcode);
		setApap_ename(apAp_ename);
		setApap_cname(apAp_cname);
		setDtdt_name(dtDt_name);
	}

	public void setApap_hubcode(String apAp_hubcode) {
		this.setField(0, apAp_hubcode);
	}

	public String getApap_hubcode() {
		return this.getField(0);
	}

	public void setApap_ename(String apAp_ename) {
		this.setField(1, apAp_ename);
	}

	public String getApap_ename() {
		return this.getField(1);
	}

	public void setApap_cname(String apAp_cname) {
		this.setField(2, apAp_cname);
	}

	public String getApap_cname() {
		return this.getField(2);
	}

	public void setDtdt_name(String dtDt_name) {
		this.setField(3, dtDt_name);
	}

	public String getDtdt_name() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
