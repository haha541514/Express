package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class LatestcreatedateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public LatestcreatedateColumns() {
		m_astrColumns = new String[1];
	}
	
	public LatestcreatedateColumns(String Latestcreatedate){
		m_astrColumns = new String[1];
		setLatestcreatedate(Latestcreatedate);
	}

	public void setLatestcreatedate(String Latestcreatedate) {
		this.setField(0, Latestcreatedate);
	}

	public String getLatestcreatedate() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
