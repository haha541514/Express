package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DifferencedetailColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DifferencedetailColumns() {
		m_astrColumns = new String[18];
	}
	
	public DifferencedetailColumns(String swbSwb_code, 
            String swbChn_code, String chnChn_sname, 
            String swbSwb_serverewbcode, String swbSwb_customerewbcode, 
            String swbSwb_pieces, String swbSwb_chargeweight, 
            String swbSwb_totalfreightcharge, String swbSwb_totalsurcharge, 
            String swbSwb_totalincidentalcharge, String cwCw_transferpieces, 
            String cwCw_serverchargeweight, String cwCw_chargeweight, 
            String Freightcharge, String Oilcharge, 
            String Othercharge, String cwCw_code, 
            String cwCw_transferchargeweight){
		m_astrColumns = new String[18];
		setSwbswb_code(swbSwb_code);
		setSwbchn_code(swbChn_code);
		setChnchn_sname(chnChn_sname);
		setSwbswb_serverewbcode(swbSwb_serverewbcode);
		setSwbswb_customerewbcode(swbSwb_customerewbcode);
		setSwbswb_pieces(swbSwb_pieces);
		setSwbswb_chargeweight(swbSwb_chargeweight);
		setSwbswb_totalfreightcharge(swbSwb_totalfreightcharge);
		setSwbswb_totalsurcharge(swbSwb_totalsurcharge);
		setSwbswb_totalincidentalcharge(swbSwb_totalincidentalcharge);
		setCwcw_transferpieces(cwCw_transferpieces);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setCwcw_chargeweight(cwCw_chargeweight);
		setFreightcharge(Freightcharge);
		setOilcharge(Oilcharge);
		setOthercharge(Othercharge);
		setCwcw_code(cwCw_code);
		setCwcw_transferchargeweight(cwCw_transferchargeweight);
	}

	public void setSwbswb_code(String swbSwb_code) {
		this.setField(0, swbSwb_code);
	}

	public String getSwbswb_code() {
		return this.getField(0);
	}

	public void setSwbchn_code(String swbChn_code) {
		this.setField(1, swbChn_code);
	}

	public String getSwbchn_code() {
		return this.getField(1);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(2, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(2);
	}

	public void setSwbswb_serverewbcode(String swbSwb_serverewbcode) {
		this.setField(3, swbSwb_serverewbcode);
	}

	public String getSwbswb_serverewbcode() {
		return this.getField(3);
	}

	public void setSwbswb_customerewbcode(String swbSwb_customerewbcode) {
		this.setField(4, swbSwb_customerewbcode);
	}

	public String getSwbswb_customerewbcode() {
		return this.getField(4);
	}

	public void setSwbswb_pieces(String swbSwb_pieces) {
		this.setField(5, swbSwb_pieces);
	}

	public String getSwbswb_pieces() {
		return this.getField(5);
	}

	public void setSwbswb_chargeweight(String swbSwb_chargeweight) {
		this.setField(6, swbSwb_chargeweight);
	}

	public String getSwbswb_chargeweight() {
		return this.getField(6);
	}

	public void setSwbswb_totalfreightcharge(String swbSwb_totalfreightcharge) {
		this.setField(7, swbSwb_totalfreightcharge);
	}

	public String getSwbswb_totalfreightcharge() {
		return this.getField(7);
	}

	public void setSwbswb_totalsurcharge(String swbSwb_totalsurcharge) {
		this.setField(8, swbSwb_totalsurcharge);
	}

	public String getSwbswb_totalsurcharge() {
		return this.getField(8);
	}

	public void setSwbswb_totalincidentalcharge(String swbSwb_totalincidentalcharge) {
		this.setField(9, swbSwb_totalincidentalcharge);
	}

	public String getSwbswb_totalincidentalcharge() {
		return this.getField(9);
	}

	public void setCwcw_transferpieces(String cwCw_transferpieces) {
		this.setField(10, cwCw_transferpieces);
	}

	public String getCwcw_transferpieces() {
		return this.getField(10);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(11, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(11);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(12, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(12);
	}

	public void setFreightcharge(String Freightcharge) {
		this.setField(13, Freightcharge);
	}

	public String getFreightcharge() {
		return this.getField(13);
	}

	public void setOilcharge(String Oilcharge) {
		this.setField(14, Oilcharge);
	}

	public String getOilcharge() {
		return this.getField(14);
	}

	public void setOthercharge(String Othercharge) {
		this.setField(15, Othercharge);
	}

	public String getOthercharge() {
		return this.getField(15);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(16, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(16);
	}

	public void setCwcw_transferchargeweight(String cwCw_transferchargeweight) {
		this.setField(17, cwCw_transferchargeweight);
	}

	public String getCwcw_transferchargeweight() {
		return this.getField(17);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
