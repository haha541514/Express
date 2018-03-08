package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ServerwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerwaybillColumns() {
		m_astrColumns = new String[10];
	}
	
	public ServerwaybillColumns(String swbSwb_code, 
            String swbChn_code, String swbSwb_serverewbcode, 
            String swbSwb_customerewbcode, String swbSwb_pieces, 
            String swbSwb_chargeweight, String swbSwb_referencecode, 
            String swbSwb_totalfreightcharge, String swbSwb_totalincidentalcharge, 
            String swbSwb_totalsurcharge){
		m_astrColumns = new String[10];
		setSwbswb_code(swbSwb_code);
		setSwbchn_code(swbChn_code);
		setSwbswb_serverewbcode(swbSwb_serverewbcode);
		setSwbswb_customerewbcode(swbSwb_customerewbcode);
		setSwbswb_pieces(swbSwb_pieces);
		setSwbswb_chargeweight(swbSwb_chargeweight);
		setSwbswb_referencecode(swbSwb_referencecode);
		setSwbswb_totalfreightcharge(swbSwb_totalfreightcharge);
		setSwbswb_totalincidentalcharge(swbSwb_totalincidentalcharge);
		setSwbswb_totalsurcharge(swbSwb_totalsurcharge);
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

	public void setSwbswb_serverewbcode(String swbSwb_serverewbcode) {
		this.setField(2, swbSwb_serverewbcode);
	}

	public String getSwbswb_serverewbcode() {
		return this.getField(2);
	}

	public void setSwbswb_customerewbcode(String swbSwb_customerewbcode) {
		this.setField(3, swbSwb_customerewbcode);
	}

	public String getSwbswb_customerewbcode() {
		return this.getField(3);
	}

	public void setSwbswb_pieces(String swbSwb_pieces) {
		this.setField(4, swbSwb_pieces);
	}

	public String getSwbswb_pieces() {
		return this.getField(4);
	}

	public void setSwbswb_chargeweight(String swbSwb_chargeweight) {
		this.setField(5, swbSwb_chargeweight);
	}

	public String getSwbswb_chargeweight() {
		return this.getField(5);
	}

	public void setSwbswb_referencecode(String swbSwb_referencecode) {
		this.setField(6, swbSwb_referencecode);
	}

	public String getSwbswb_referencecode() {
		return this.getField(6);
	}

	public void setSwbswb_totalfreightcharge(String swbSwb_totalfreightcharge) {
		this.setField(7, swbSwb_totalfreightcharge);
	}

	public String getSwbswb_totalfreightcharge() {
		return this.getField(7);
	}

	public void setSwbswb_totalincidentalcharge(String swbSwb_totalincidentalcharge) {
		this.setField(8, swbSwb_totalincidentalcharge);
	}

	public String getSwbswb_totalincidentalcharge() {
		return this.getField(8);
	}

	public void setSwbswb_totalsurcharge(String swbSwb_totalsurcharge) {
		this.setField(9, swbSwb_totalsurcharge);
	}

	public String getSwbswb_totalsurcharge() {
		return this.getField(9);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
