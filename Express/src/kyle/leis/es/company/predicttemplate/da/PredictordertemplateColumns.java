package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredictordertemplateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictordertemplateColumns() {
		m_astrColumns = new String[8];
	}
	
	public PredictordertemplateColumns(String potPot_id, 
            String potCo_code, String potOp_id_creator, 
            String potOp_id_modifier, String potPot_name, 
            String potPot_remark, String potPot_createdate, 
            String potPot_modifydate){
		m_astrColumns = new String[8];
		setPotpot_id(potPot_id);
		setPotco_code(potCo_code);
		setPotop_id_creator(potOp_id_creator);
		setPotop_id_modifier(potOp_id_modifier);
		setPotpot_name(potPot_name);
		setPotpot_remark(potPot_remark);
		setPotpot_createdate(potPot_createdate);
		setPotpot_modifydate(potPot_modifydate);
	}

	public void setPotpot_id(String potPot_id) {
		this.setField(0, potPot_id);
	}

	public String getPotpot_id() {
		return this.getField(0);
	}

	public void setPotco_code(String potCo_code) {
		this.setField(1, potCo_code);
	}

	public String getPotco_code() {
		return this.getField(1);
	}

	public void setPotop_id_creator(String potOp_id_creator) {
		this.setField(2, potOp_id_creator);
	}

	public String getPotop_id_creator() {
		return this.getField(2);
	}

	public void setPotop_id_modifier(String potOp_id_modifier) {
		this.setField(3, potOp_id_modifier);
	}

	public String getPotop_id_modifier() {
		return this.getField(3);
	}

	public void setPotpot_name(String potPot_name) {
		this.setField(4, potPot_name);
	}

	public String getPotpot_name() {
		return this.getField(4);
	}

	public void setPotpot_remark(String potPot_remark) {
		this.setField(5, potPot_remark);
	}

	public String getPotpot_remark() {
		return this.getField(5);
	}

	public void setPotpot_createdate(String potPot_createdate) {
		this.setField(6, potPot_createdate);
	}

	public String getPotpot_createdate() {
		return this.getField(6);
	}

	public void setPotpot_modifydate(String potPot_modifydate) {
		this.setField(7, potPot_modifydate);
	}

	public String getPotpot_modifydate() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
