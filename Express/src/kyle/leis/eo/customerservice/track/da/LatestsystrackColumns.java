package kyle.leis.eo.customerservice.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class LatestsystrackColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public LatestsystrackColumns() {
		m_astrColumns = new String[1];
	}
	
	public LatestsystrackColumns(String Latestsystrack){
		m_astrColumns = new String[1];
		setLatestsystrack(Latestsystrack);
	}

	public void setLatestsystrack(String Latestsystrack) {
		this.setField(0, Latestsystrack);
	}

	public String getLatestsystrack() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
