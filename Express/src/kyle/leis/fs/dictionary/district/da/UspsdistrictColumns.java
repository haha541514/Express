package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class UspsdistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UspsdistrictColumns() {
		m_astrColumns = new String[1];
	}
	
	public UspsdistrictColumns(String ddtDd_statename){
		m_astrColumns = new String[1];
		setDdtdd_statename(ddtDd_statename);
	}

	public void setDdtdd_statename(String ddtDd_statename) {
		this.setField(0, ddtDd_statename);
	}

	public String getDdtdd_statename() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
