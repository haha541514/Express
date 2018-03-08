package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class XinshdistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public XinshdistrictColumns() {
		m_astrColumns = new String[1];
	}
	
	public XinshdistrictColumns(String Dt_statecode){
		m_astrColumns = new String[1];
		setDt_statecode(Dt_statecode);
	}

	public void setDt_statecode(String Dt_statecode) {
		this.setField(0, Dt_statecode);
	}

	public String getDt_statecode() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
