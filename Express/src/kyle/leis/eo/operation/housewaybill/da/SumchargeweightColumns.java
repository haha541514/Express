package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumchargeweightColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumchargeweightColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumchargeweightColumns(String Sumchargeweight){
		m_astrColumns = new String[1];
		setSumchargeweight(Sumchargeweight);
	}

	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(0, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
