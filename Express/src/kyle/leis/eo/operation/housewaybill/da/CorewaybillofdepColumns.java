package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillofdepColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillofdepColumns() {
		m_astrColumns = new String[1];
	}
	
	public CorewaybillofdepColumns(String cwCw_serverewbcode){
		m_astrColumns = new String[1];
		setCwcw_serverewbcode(cwCw_serverewbcode);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(0, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
