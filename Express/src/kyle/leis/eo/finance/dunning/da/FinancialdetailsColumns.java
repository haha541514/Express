package kyle.leis.eo.finance.dunning.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FinancialdetailsColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FinancialdetailsColumns() {
		m_astrColumns = new String[17];
	}
	
	public FinancialdetailsColumns(String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String cwCw_ewbcode, 
            String cwCw_pieces, String cwCw_grossweight, 
            String hwHw_signoutdate, String opOp_name, 
            String coCo_labelcode, String coCo_sname, 
            String cpCo_name, String chnChn_name, 
            String bwAdd_date, String Rvtotal, 
            String coCo_code, String cpCo_labelcode, 
            String cpCo_sname, String chnChn_sname){
		m_astrColumns = new String[17];
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_grossweight(cwCw_grossweight);
		setHwhw_signoutdate(hwHw_signoutdate);
		setOpop_name(opOp_name);
		setCoco_labelcode(coCo_labelcode);
		setCoco_sname(coCo_sname);
		setCpco_name(cpCo_name);
		setChnchn_name(chnChn_name);
		setBwadd_date(bwAdd_date);
		setRvtotal(Rvtotal);
		setCoco_code(coCo_code);
		setCpco_labelcode(cpCo_labelcode);
		setCpco_sname(cpCo_sname);
		setChnchn_sname(chnChn_sname);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(0, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(0);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(1, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(1);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(2, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(2);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(3, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(3);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(4, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(4);
	}

	public void setHwhw_signoutdate(String hwHw_signoutdate) {
		this.setField(5, hwHw_signoutdate);
	}

	public String getHwhw_signoutdate() {
		return this.getField(5);
	}

	public void setOpop_name(String opOp_name) {
		this.setField(6, opOp_name);
	}

	public String getOpop_name() {
		return this.getField(6);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(7, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(7);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(8, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(8);
	}

	public void setCpco_name(String cpCo_name) {
		this.setField(9, cpCo_name);
	}

	public String getCpco_name() {
		return this.getField(9);
	}

	public void setChnchn_name(String chnChn_name) {
		this.setField(10, chnChn_name);
	}

	public String getChnchn_name() {
		return this.getField(10);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(11, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(11);
	}

	public void setRvtotal(String Rvtotal) {
		this.setField(12, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(12);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(13, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(13);
	}

	public void setCpco_labelcode(String cpCo_labelcode) {
		this.setField(14, cpCo_labelcode);
	}

	public String getCpco_labelcode() {
		return this.getField(14);
	}

	public void setCpco_sname(String cpCo_sname) {
		this.setField(15, cpCo_sname);
	}

	public String getCpco_sname() {
		return this.getField(15);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(16, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(16);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
