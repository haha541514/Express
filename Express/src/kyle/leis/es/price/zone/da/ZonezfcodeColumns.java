package kyle.leis.es.price.zone.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ZonezfcodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZonezfcodeColumns() {
		m_astrColumns = new String[1];
	}
	
	public ZonezfcodeColumns(String Zn_code){
		m_astrColumns = new String[1];
		setZn_code(Zn_code);
	}

	public void setZn_code(String Zn_code) {
		this.setField(0, Zn_code);
	}

	public String getZn_code() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
