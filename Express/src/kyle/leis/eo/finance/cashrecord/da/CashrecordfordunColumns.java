package kyle.leis.eo.finance.cashrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CashrecordfordunColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CashrecordfordunColumns() {
		m_astrColumns = new String[5];
	}
	
	public CashrecordfordunColumns(String crCr_id, 
            String crCo_code, String crCr_occurdate, 
            String crCr_remark, String Crtotal){
		m_astrColumns = new String[5];
		setCrcr_id(crCr_id);
		setCrco_code(crCo_code);
		setCrcr_occurdate(crCr_occurdate);
		setCrcr_remark(crCr_remark);
		setCrtotal(Crtotal);
	}

	public void setCrcr_id(String crCr_id) {
		this.setField(0, crCr_id);
	}

	public String getCrcr_id() {
		return this.getField(0);
	}

	public void setCrco_code(String crCo_code) {
		this.setField(1, crCo_code);
	}

	public String getCrco_code() {
		return this.getField(1);
	}

	public void setCrcr_occurdate(String crCr_occurdate) {
		this.setField(2, crCr_occurdate);
	}

	public String getCrcr_occurdate() {
		return this.getField(2);
	}

	public void setCrcr_remark(String crCr_remark) {
		this.setField(3, crCr_remark);
	}

	public String getCrcr_remark() {
		return this.getField(3);
	}

	public void setCrtotal(String Crtotal) {
		this.setField(4, Crtotal);
	}

	public String getCrtotal() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
