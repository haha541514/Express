package kyle.leis.eo.operation.housewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class SobagstatisticColumnsEX extends GeneralColumns implements Serializable {

	private static final long serialVersionUID = -8265284628323765505L;

	public SobagstatisticColumnsEX() {
		m_astrColumns = new String[3];
	}
	
	public SobagstatisticColumnsEX(String Bagno, 
            String Billcounts, String Sumchargeweight){
		m_astrColumns = new String[3];
		setBagno(Bagno);
		setBillcounts(Billcounts);
		setSumchargeweight(Sumchargeweight);
	}
	public void setBagno(String Bagno) {
		this.setField(0, Bagno);
	}

	public String getBagno() {
		return this.getField(0);
	}
	
	public void setBillcounts(String Billcounts) {
		this.setField(1, Billcounts);
	}

	public String getBillcounts() {
		return this.getField(1);
	}	
	
	public void setSumchargeweight(String Sumchargeweight) {
		this.setField(2, Sumchargeweight);
	}

	public String getSumchargeweight() {
		return this.getField(2);
	}		
	
    public String toString() {
        return "Code Generate By Kyle";
    }		
	
}
