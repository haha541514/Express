package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class StatistictcwColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public StatistictcwColumns() {
		m_astrColumns = new String[2];
	}
	
	public StatistictcwColumns(String Billcounts, 
            String Sumchargeweight){
		m_astrColumns = new String[2];
		setBillcounts(Billcounts);
		setSumchargeweight(Sumchargeweight);
	}

	public void setBillcounts(String Billcounts) {
		this.setField(0, Billcounts);
	}

	public String getBillcounts() {
		return this.getField(0);
	}

	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(1, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
