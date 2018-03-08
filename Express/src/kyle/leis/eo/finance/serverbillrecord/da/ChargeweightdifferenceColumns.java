package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChargeweightdifferenceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChargeweightdifferenceColumns() {
		m_astrColumns = new String[24];
	}
	
	public ChargeweightdifferenceColumns(String hwbHw_owninputcwauditsign, 
            String Status, String Rvtotal, 
            String Pytotal, String swbSwb_code, 
            String swbChn_code, String chnChn_sname, 
            String swbSwb_serverewbcode, String swbSwb_customerewbcode, 
            String swbSwb_pieces, String swbSwb_chargeweight, 
            String swbSwb_totalfreightcharge, String swbSwb_totalsurcharge, 
            String swbSwb_totalincidentalcharge, String cwCw_transferpieces, 
            String cwCw_serverchargeweight, String cwCw_chargeweight, 
            String cwCw_code, String coCo_code, 
            String coCo_sname, String coCo_labelcode, 
            String bwAdd_date, String pkPk_code, 
            String pkPk_name){
		m_astrColumns = new String[24];
		setHwbhw_owninputcwauditsign(hwbHw_owninputcwauditsign);
		setStatus(Status);
		setRvtotal(Rvtotal);
		setPytotal(Pytotal);
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
		setCwcw_code(cwCw_code);
		setCoco_code(coCo_code);
		setCoco_sname(coCo_sname);
		setCoco_labelcode(coCo_labelcode);
		setBwadd_date(bwAdd_date);
		setPkpk_code(pkPk_code);
		setPkpk_name(pkPk_name);
	}

	public void setHwbhw_owninputcwauditsign(String hwbHw_owninputcwauditsign) {
		this.setField(0, hwbHw_owninputcwauditsign);
	}

	public String getHwbhw_owninputcwauditsign() {
		return this.getField(0);
	}

	public void setStatus(String Status) {
		this.setField(1, Status);
	}

	public String getStatus() {
		return this.getField(1);
	}

	public void setRvtotal(String Rvtotal) {
		this.setField(2, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(2);
	}

	public void setPytotal(String Pytotal) {
		this.setField(3, Pytotal);
	}

	public String getPytotal() {
		return this.getField(3);
	}

	public void setSwbswb_code(String swbSwb_code) {
		this.setField(4, swbSwb_code);
	}

	public String getSwbswb_code() {
		return this.getField(4);
	}

	public void setSwbchn_code(String swbChn_code) {
		this.setField(5, swbChn_code);
	}

	public String getSwbchn_code() {
		return this.getField(5);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(6, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(6);
	}

	public void setSwbswb_serverewbcode(String swbSwb_serverewbcode) {
		this.setField(7, swbSwb_serverewbcode);
	}

	public String getSwbswb_serverewbcode() {
		return this.getField(7);
	}

	public void setSwbswb_customerewbcode(String swbSwb_customerewbcode) {
		this.setField(8, swbSwb_customerewbcode);
	}

	public String getSwbswb_customerewbcode() {
		return this.getField(8);
	}

	public void setSwbswb_pieces(String swbSwb_pieces) {
		this.setField(9, swbSwb_pieces);
	}

	public String getSwbswb_pieces() {
		return this.getField(9);
	}

	public void setSwbswb_chargeweight(String swbSwb_chargeweight) {
		this.setField(10, swbSwb_chargeweight);
	}

	public String getSwbswb_chargeweight() {
		return this.getField(10);
	}

	public void setSwbswb_totalfreightcharge(String swbSwb_totalfreightcharge) {
		this.setField(11, swbSwb_totalfreightcharge);
	}

	public String getSwbswb_totalfreightcharge() {
		return this.getField(11);
	}

	public void setSwbswb_totalsurcharge(String swbSwb_totalsurcharge) {
		this.setField(12, swbSwb_totalsurcharge);
	}

	public String getSwbswb_totalsurcharge() {
		return this.getField(12);
	}

	public void setSwbswb_totalincidentalcharge(String swbSwb_totalincidentalcharge) {
		this.setField(13, swbSwb_totalincidentalcharge);
	}

	public String getSwbswb_totalincidentalcharge() {
		return this.getField(13);
	}

	public void setCwcw_transferpieces(String cwCw_transferpieces) {
		this.setField(14, cwCw_transferpieces);
	}

	public String getCwcw_transferpieces() {
		return this.getField(14);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(15, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(15);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(16, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(16);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(17, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(17);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(18, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(18);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(19, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(19);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(20, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(20);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(21, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(21);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(22, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(22);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(23, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(23);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
