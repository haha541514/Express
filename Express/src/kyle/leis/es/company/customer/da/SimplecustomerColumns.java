package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplecustomerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplecustomerColumns() {
		m_astrColumns = new String[9];
	}
	
	public SimplecustomerColumns(String cmCo_code, 
            String cmCo_code_parent, String cmCm_structruecode, 
            String coCs_code, String coCo_name, 
            String coCo_sname, String coCo_address, 
            String coCo_remark, String cmCt_code){
		m_astrColumns = new String[9];
		setCmco_code(cmCo_code);
		setCmco_code_parent(cmCo_code_parent);
		setCmcm_structruecode(cmCm_structruecode);
		setCocs_code(coCs_code);
		setCoco_name(coCo_name);
		setCoco_sname(coCo_sname);
		setCoco_address(coCo_address);
		setCoco_remark(coCo_remark);
		setCmct_code(cmCt_code);
	}

	public void setCmco_code(String cmCo_code) {
		this.setField(0, cmCo_code);
	}

	public String getCmco_code() {
		return this.getField(0);
	}

	public void setCmco_code_parent(String cmCo_code_parent) {
		this.setField(1, cmCo_code_parent);
	}

	public String getCmco_code_parent() {
		return this.getField(1);
	}

	public void setCmcm_structruecode(String cmCm_structruecode) {
		this.setField(2, cmCm_structruecode);
	}

	public String getCmcm_structruecode() {
		return this.getField(2);
	}

	public void setCocs_code(String coCs_code) {
		this.setField(3, coCs_code);
	}

	public String getCocs_code() {
		return this.getField(3);
	}

	public void setCoco_name(String coCo_name) {
		this.setField(4, coCo_name);
	}

	public String getCoco_name() {
		return this.getField(4);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(5, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(5);
	}

	public void setCoco_address(String coCo_address) {
		this.setField(6, coCo_address);
	}

	public String getCoco_address() {
		return this.getField(6);
	}

	public void setCoco_remark(String coCo_remark) {
		this.setField(7, coCo_remark);
	}

	public String getCoco_remark() {
		return this.getField(7);
	}

	public void setCmct_code(String cmCt_code) {
		this.setField(8, cmCt_code);
	}

	public String getCmct_code() {
		return this.getField(8);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
