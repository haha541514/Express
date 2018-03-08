package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransportcorewaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportcorewaybillColumns() {
		m_astrColumns = new String[10];
	}
	
	public TransportcorewaybillColumns(String twbvTwb_id, 
            String bwBw_code, String cwCw_code, 
            String cwCw_ewbcode, String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cwCw_chargeweight, 
            String cwCt_code, String cwPm_code, 
            String cwCw_grossweight){
		m_astrColumns = new String[10];
		setTwbvtwb_id(twbvTwb_id);
		setBwbw_code(bwBw_code);
		setCwcw_code(cwCw_code);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwct_code(cwCt_code);
		setCwpm_code(cwPm_code);
		setCwcw_grossweight(cwCw_grossweight);
	}

	public void setTwbvtwb_id(String twbvTwb_id) {
		this.setField(0, twbvTwb_id);
	}

	public String getTwbvtwb_id() {
		return this.getField(0);
	}

	public void setBwbw_code(String bwBw_code) {
		this.setField(1, bwBw_code);
	}

	public String getBwbw_code() {
		return this.getField(1);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(2, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(2);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(3, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(3);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(4, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(4);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(5, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(5);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(6, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(6);
	}

	public void setCwct_code(String cwCt_code) {
		this.setField(7, cwCt_code);
	}

	public String getCwct_code() {
		return this.getField(7);
	}

	public void setCwpm_code(String cwPm_code) {
		this.setField(8, cwPm_code);
	}

	public String getCwpm_code() {
		return this.getField(8);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(9, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
