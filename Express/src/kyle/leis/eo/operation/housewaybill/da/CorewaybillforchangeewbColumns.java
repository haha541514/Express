package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillforchangeewbColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillforchangeewbColumns() {
		m_astrColumns = new String[1];
	}
	
	public CorewaybillforchangeewbColumns(String cwCw_code){
		m_astrColumns = new String[1];
		setCwcw_code(cwCw_code);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
