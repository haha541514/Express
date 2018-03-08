package kyle.fetcher.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PoddatevalidateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PoddatevalidateColumns() {
		m_astrColumns = new String[1];
	}
	
	public PoddatevalidateColumns(String Validaterow){
		m_astrColumns = new String[1];
		setValidaterow(Validaterow);
	}

	public void setValidaterow(String Validaterow) {
		this.setField(0, Validaterow);
	}

	public String getValidaterow() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
