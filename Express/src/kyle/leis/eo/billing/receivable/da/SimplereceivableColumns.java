package kyle.leis.eo.billing.receivable.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplereceivableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplereceivableColumns() {
		m_astrColumns = new String[14];
	}
	
	public SimplereceivableColumns(String rvRv_id, 
            String rvFs_code, String rvCo_code, 
            String rvCk_code, String rvFk_code, 
            String rvChn_code, String rvRv_op_id_creator, 
            String rvRv_createdate, String rvRv_op_id_modifier, 
            String rvRv_modifydate, String rvCw_code, 
            String rvBr_id, String rvRv_currencyrate, 
            String rvRv_actualtotal){
		m_astrColumns = new String[14];
		setRvrv_id(rvRv_id);
		setRvfs_code(rvFs_code);
		setRvco_code(rvCo_code);
		setRvck_code(rvCk_code);
		setRvfk_code(rvFk_code);
		setRvchn_code(rvChn_code);
		setRvrv_op_id_creator(rvRv_op_id_creator);
		setRvrv_createdate(rvRv_createdate);
		setRvrv_op_id_modifier(rvRv_op_id_modifier);
		setRvrv_modifydate(rvRv_modifydate);
		setRvcw_code(rvCw_code);
		setRvbr_id(rvBr_id);
		setRvrv_currencyrate(rvRv_currencyrate);
		setRvrv_actualtotal(rvRv_actualtotal);
	}

	public void setRvrv_id(String rvRv_id) {
		this.setField(0, rvRv_id);
	}

	public String getRvrv_id() {
		return this.getField(0);
	}

	public void setRvfs_code(String rvFs_code) {
		this.setField(1, rvFs_code);
	}

	public String getRvfs_code() {
		return this.getField(1);
	}

	public void setRvco_code(String rvCo_code) {
		this.setField(2, rvCo_code);
	}

	public String getRvco_code() {
		return this.getField(2);
	}

	public void setRvck_code(String rvCk_code) {
		this.setField(3, rvCk_code);
	}

	public String getRvck_code() {
		return this.getField(3);
	}

	public void setRvfk_code(String rvFk_code) {
		this.setField(4, rvFk_code);
	}

	public String getRvfk_code() {
		return this.getField(4);
	}

	public void setRvchn_code(String rvChn_code) {
		this.setField(5, rvChn_code);
	}

	public String getRvchn_code() {
		return this.getField(5);
	}

	public void setRvrv_op_id_creator(String rvRv_op_id_creator) {
		this.setField(6, rvRv_op_id_creator);
	}

	public String getRvrv_op_id_creator() {
		return this.getField(6);
	}

	public void setRvrv_createdate(String rvRv_createdate) {
		this.setField(7, rvRv_createdate);
	}

	public String getRvrv_createdate() {
		return this.getField(7);
	}

	public void setRvrv_op_id_modifier(String rvRv_op_id_modifier) {
		this.setField(8, rvRv_op_id_modifier);
	}

	public String getRvrv_op_id_modifier() {
		return this.getField(8);
	}

	public void setRvrv_modifydate(String rvRv_modifydate) {
		this.setField(9, rvRv_modifydate);
	}

	public String getRvrv_modifydate() {
		return this.getField(9);
	}

	public void setRvcw_code(String rvCw_code) {
		this.setField(10, rvCw_code);
	}

	public String getRvcw_code() {
		return this.getField(10);
	}

	public void setRvbr_id(String rvBr_id) {
		this.setField(11, rvBr_id);
	}

	public String getRvbr_id() {
		return this.getField(11);
	}

	public void setRvrv_currencyrate(String rvRv_currencyrate) {
		this.setField(12, rvRv_currencyrate);
	}

	public String getRvrv_currencyrate() {
		return this.getField(12);
	}

	public void setRvrv_actualtotal(String rvRv_actualtotal) {
		this.setField(13, rvRv_actualtotal);
	}

	public String getRvrv_actualtotal() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
