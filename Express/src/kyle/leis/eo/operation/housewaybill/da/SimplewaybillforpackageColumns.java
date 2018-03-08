package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplewaybillforpackageColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplewaybillforpackageColumns() {
		m_astrColumns = new String[2];
	}
	
	public SimplewaybillforpackageColumns(String bwvCw_code, 
            String bwvBw_code){
		m_astrColumns = new String[2];
		setBwvcw_code(bwvCw_code);
		setBwvbw_code(bwvBw_code);
	}

	public void setBwvcw_code(String bwvCw_code) {
		this.setField(0, bwvCw_code);
	}

	public String getBwvcw_code() {
		return this.getField(0);
	}

	public void setBwvbw_code(String bwvBw_code) {
		this.setField(1, bwvBw_code);
	}

	public String getBwvbw_code() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
