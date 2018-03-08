package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerTypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerTypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public CustomerTypeColumns(String dtCt_code, 
            String dtCt_name, String dtCt_ename){
		m_astrColumns = new String[3];
		setDtct_code(dtCt_code);
		setDtct_name(dtCt_name);
		setDtct_ename(dtCt_ename);
	}

	public void setDtct_code(String dtCt_code) {
		this.setField(0, dtCt_code);
	}

	public String getDtct_code() {
		return this.getField(0);
	}

	public void setDtct_name(String dtCt_name) {
		this.setField(1, dtCt_name);
	}

	public String getDtct_name() {
		return this.getField(1);
	}

	public void setDtct_ename(String dtCt_ename) {
		this.setField(2, dtCt_ename);
	}

	public String getDtct_ename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
