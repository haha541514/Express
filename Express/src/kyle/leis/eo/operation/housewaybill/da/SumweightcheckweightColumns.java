package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumweightcheckweightColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumweightcheckweightColumns() {
		m_astrColumns = new String[3];
	}
	
	public SumweightcheckweightColumns(String Sumchargeweight, String Sumcmchargeweight, 
            String Billcounts){
		m_astrColumns = new String[3];
		setSumchargeweight(Sumchargeweight);
		setSumcmchargeweight(Sumcmchargeweight);
		setBillcounts(Billcounts);
	}

	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(0, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(0);
	}

	public void setSumcmchargeweight(String Sumcmchargeweight) {
		this.setField(1, Sumcmchargeweight);
	}

	public String getSumcmchargeweight() {
		return this.getField(1);
	}

	public void setBillcounts(String Billcounts) {
		this.setField(2, Billcounts);
	}

	public String getBillcounts() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
