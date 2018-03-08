package wkq.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeekindjdbcColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeekindjdbcColumns() {
		m_astrColumns = new String[11];
	}
	
	public FeekindjdbcColumns(String foFk_code, 
            String foFk_name, String foFk_ename, 
            String foFk_referenceposition, String foFk_manualmodifysign, 
            String Sign, String foFk_remark, 
            String foFk_accountingonlysign, String foFk_declarevaluesign, 
            String siSs_code, String exEst_code){
		m_astrColumns = new String[11];
		setFofk_code(foFk_code);
		setFofk_name(foFk_name);
		setFofk_ename(foFk_ename);
		setFofk_referenceposition(foFk_referenceposition);
		setFofk_manualmodifysign(foFk_manualmodifysign);
		setSign(Sign);
		setFofk_remark(foFk_remark);
		setFofk_accountingonlysign(foFk_accountingonlysign);
		setFofk_declarevaluesign(foFk_declarevaluesign);
		setSiss_code(siSs_code);
		setExest_code(exEst_code);
	}

	public void setFofk_code(String foFk_code) {
		this.setField(0, foFk_code);
	}

	public String getFofk_code() {
		return this.getField(0);
	}

	public void setFofk_name(String foFk_name) {
		this.setField(1, foFk_name);
	}

	public String getFofk_name() {
		return this.getField(1);
	}

	public void setFofk_ename(String foFk_ename) {
		this.setField(2, foFk_ename);
	}

	public String getFofk_ename() {
		return this.getField(2);
	}

	public void setFofk_referenceposition(String foFk_referenceposition) {
		this.setField(3, foFk_referenceposition);
	}

	public String getFofk_referenceposition() {
		return this.getField(3);
	}

	public void setFofk_manualmodifysign(String foFk_manualmodifysign) {
		this.setField(4, foFk_manualmodifysign);
	}

	public String getFofk_manualmodifysign() {
		return this.getField(4);
	}

	public void setSign(String Sign) {
		this.setField(5, Sign);
	}

	public String getSign() {
		return this.getField(5);
	}

	public void setFofk_remark(String foFk_remark) {
		this.setField(6, foFk_remark);
	}

	public String getFofk_remark() {
		return this.getField(6);
	}

	public void setFofk_accountingonlysign(String foFk_accountingonlysign) {
		this.setField(7, foFk_accountingonlysign);
	}

	public String getFofk_accountingonlysign() {
		return this.getField(7);
	}

	public void setFofk_declarevaluesign(String foFk_declarevaluesign) {
		this.setField(8, foFk_declarevaluesign);
	}

	public String getFofk_declarevaluesign() {
		return this.getField(8);
	}

	public void setSiss_code(String siSs_code) {
		this.setField(9, siSs_code);
	}

	public String getSiss_code() {
		return this.getField(9);
	}

	public void setExest_code(String exEst_code) {
		this.setField(10, exEst_code);
	}

	public String getExest_code() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
