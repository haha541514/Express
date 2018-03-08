package kyle.leis.eo.finance.billrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BillrecordfordunColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BillrecordfordunColumns() {
		m_astrColumns = new String[5];
	}
	
	public BillrecordfordunColumns(String brBr_id, 
            String brCo_code, String brBr_occurdate, 
            String brBr_remark, String Brtotal){
		m_astrColumns = new String[5];
		setBrbr_id(brBr_id);
		setBrco_code(brCo_code);
		setBrbr_occurdate(brBr_occurdate);
		setBrbr_remark(brBr_remark);
		setBrtotal(Brtotal);
	}

	public void setBrbr_id(String brBr_id) {
		this.setField(0, brBr_id);
	}

	public String getBrbr_id() {
		return this.getField(0);
	}

	public void setBrco_code(String brCo_code) {
		this.setField(1, brCo_code);
	}

	public String getBrco_code() {
		return this.getField(1);
	}

	public void setBrbr_occurdate(String brBr_occurdate) {
		this.setField(2, brBr_occurdate);
	}

	public String getBrbr_occurdate() {
		return this.getField(2);
	}

	public void setBrbr_remark(String brBr_remark) {
		this.setField(3, brBr_remark);
	}

	public String getBrbr_remark() {
		return this.getField(3);
	}

	public void setBrtotal(String Brtotal) {
		this.setField(4, Brtotal);
	}

	public String getBrtotal() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
