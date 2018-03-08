package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DifferenceincidentalsColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DifferenceincidentalsColumns() {
		m_astrColumns = new String[12];
	}
	
	public DifferenceincidentalsColumns(String cwCw_code, 
            String spaFk_code, String coCo_name, 
            String pkPk_name, String cwCw_chargeweight, 
            String swbSwb_serverewbcode, String Swb_customerewbcode, 
            String fkFk_name, String spaCk_code, 
            String cwCw_grossweight, String spaSpy_totalcharge, 
            String pkPk_code){
		m_astrColumns = new String[12];
		setCwcw_code(cwCw_code);
		setSpafk_code(spaFk_code);
		setCoco_name(coCo_name);
		setPkpk_name(pkPk_name);
		setCwcw_chargeweight(cwCw_chargeweight);
		setSwbswb_serverewbcode(swbSwb_serverewbcode);
		setSwb_customerewbcode(Swb_customerewbcode);
		setFkfk_name(fkFk_name);
		setSpack_code(spaCk_code);
		setCwcw_grossweight(cwCw_grossweight);
		setSpaspy_totalcharge(spaSpy_totalcharge);
		setPkpk_code(pkPk_code);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setSpafk_code(String spaFk_code) {
		this.setField(1, spaFk_code);
	}

	public String getSpafk_code() {
		return this.getField(1);
	}

	public void setCoco_name(String coCo_name) {
		this.setField(2, coCo_name);
	}

	public String getCoco_name() {
		return this.getField(2);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(3, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(3);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(4, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(4);
	}

	public void setSwbswb_serverewbcode(String swbSwb_serverewbcode) {
		this.setField(5, swbSwb_serverewbcode);
	}

	public String getSwbswb_serverewbcode() {
		return this.getField(5);
	}

	public void setSwb_customerewbcode(String Swb_customerewbcode) {
		this.setField(6, Swb_customerewbcode);
	}

	public String getSwb_customerewbcode() {
		return this.getField(6);
	}

	public void setFkfk_name(String fkFk_name) {
		this.setField(7, fkFk_name);
	}

	public String getFkfk_name() {
		return this.getField(7);
	}

	public void setSpack_code(String spaCk_code) {
		this.setField(8, spaCk_code);
	}

	public String getSpack_code() {
		return this.getField(8);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(9, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(9);
	}

	public void setSpaspy_totalcharge(String spaSpy_totalcharge) {
		this.setField(10, spaSpy_totalcharge);
	}

	public String getSpaspy_totalcharge() {
		return this.getField(10);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(11, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(11);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
