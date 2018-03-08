package kyle.leis.fs.dictionary.waybillcodekind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodekindColumns() {
		m_astrColumns = new String[7];
	}
	
	public WaybillcodekindColumns(String wbckBck_code, 
            String wbckBck_name, String wbckBck_ename, 
            String wbckBck_groupcode, String wbckBck_buildvaluesign, 
            String wbckBck_fromwebservicesign, String wbckLf_code){
		m_astrColumns = new String[7];
		setWbckbck_code(wbckBck_code);
		setWbckbck_name(wbckBck_name);
		setWbckbck_ename(wbckBck_ename);
		setWbckbck_groupcode(wbckBck_groupcode);
		setWbckbck_buildvaluesign(wbckBck_buildvaluesign);
		setWbckbck_fromwebservicesign(wbckBck_fromwebservicesign);
		setWbcklf_code(wbckLf_code);
	}

	public void setWbckbck_code(String wbckBck_code) {
		this.setField(0, wbckBck_code);
	}

	public String getWbckbck_code() {
		return this.getField(0);
	}

	public void setWbckbck_name(String wbckBck_name) {
		this.setField(1, wbckBck_name);
	}

	public String getWbckbck_name() {
		return this.getField(1);
	}

	public void setWbckbck_ename(String wbckBck_ename) {
		this.setField(2, wbckBck_ename);
	}

	public String getWbckbck_ename() {
		return this.getField(2);
	}

	public void setWbckbck_groupcode(String wbckBck_groupcode) {
		this.setField(3, wbckBck_groupcode);
	}

	public String getWbckbck_groupcode() {
		return this.getField(3);
	}

	public void setWbckbck_buildvaluesign(String wbckBck_buildvaluesign) {
		this.setField(4, wbckBck_buildvaluesign);
	}

	public String getWbckbck_buildvaluesign() {
		return this.getField(4);
	}

	public void setWbckbck_fromwebservicesign(String wbckBck_fromwebservicesign) {
		this.setField(5, wbckBck_fromwebservicesign);
	}

	public String getWbckbck_fromwebservicesign() {
		return this.getField(5);
	}

	public void setWbcklf_code(String wbckLf_code) {
		this.setField(6, wbckLf_code);
	}

	public String getWbcklf_code() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
