package kyle.leis.eo.finance.dunning.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FinancialcustomerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FinancialcustomerColumns() {
		m_astrColumns = new String[6];
	}
	
	public FinancialcustomerColumns(String coCo_code, 
            String coCo_labelcode, String coCo_sname, 
            String pkPk_name, String cuCm_arrearallowsignout, 
            String cuCo_code){
		m_astrColumns = new String[6];
		setCoco_code(coCo_code);
		setCoco_labelcode(coCo_labelcode);
		setCoco_sname(coCo_sname);
		setPkpk_name(pkPk_name);
		setCucm_arrearallowsignout(cuCm_arrearallowsignout);
		setCuco_code(cuCo_code);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(0, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(0);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(1, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(1);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(2, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(2);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(3, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(3);
	}

	public void setCucm_arrearallowsignout(String cuCm_arrearallowsignout) {
		this.setField(4, cuCm_arrearallowsignout);
	}

	public String getCucm_arrearallowsignout() {
		return this.getField(4);
	}

	public void setCuco_code(String cuCo_code) {
		this.setField(5, cuCo_code);
	}

	public String getCuco_code() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
