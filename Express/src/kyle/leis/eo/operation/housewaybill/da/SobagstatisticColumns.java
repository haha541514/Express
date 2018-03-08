package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SobagstatisticColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SobagstatisticColumns() {
		m_astrColumns = new String[2];
	}
	
	public SobagstatisticColumns(String cwCw_serverchargeweight, 
            String Bagno){
		m_astrColumns = new String[2];
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setBagno(Bagno);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(0, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(0);
	}

	public void setBagno(String Bagno) {
		this.setField(1, Bagno);
	}

	public String getBagno() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
