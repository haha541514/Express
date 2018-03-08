package kyle.leis.eo.operation.corewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplecorewaybillColumns extends GeneralColumns implements
		Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplecorewaybillColumns() {
		m_astrColumns = new String[11];
	}

	public SimplecorewaybillColumns(String cwCw_code, String cwCws_code,
			String cwCt_code, String cwPm_code, String cwPk_code,
			String cwIhs_code, String cwCo_code_customer,
			String cwCo_code_supplier, String cwChn_code_supplier,
			String cwCw_createdate, String cwCw_serverewbcode) {
		m_astrColumns = new String[11];
		setCwcw_code(cwCw_code);
		setCwcws_code(cwCws_code);
		setCwct_code(cwCt_code);
		setCwpm_code(cwPm_code);
		setCwpk_code(cwPk_code);
		setCwihs_code(cwIhs_code);
		setCwco_code_customer(cwCo_code_customer);
		setCwco_code_supplier(cwCo_code_supplier);
		setCwchn_code_supplier(cwChn_code_supplier);
		setCwcw_createdate(cwCw_createdate);
		setCwcw_serverewbcode(cwCw_serverewbcode);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcws_code(String cwCws_code) {
		this.setField(1, cwCws_code);
	}

	public String getCwcws_code() {
		return this.getField(1);
	}

	public void setCwct_code(String cwCt_code) {
		this.setField(2, cwCt_code);
	}

	public String getCwct_code() {
		return this.getField(2);
	}

	public void setCwpm_code(String cwPm_code) {
		this.setField(3, cwPm_code);
	}

	public String getCwpm_code() {
		return this.getField(3);
	}

	public void setCwpk_code(String cwPk_code) {
		this.setField(4, cwPk_code);
	}

	public String getCwpk_code() {
		return this.getField(4);
	}

	public void setCwihs_code(String cwIhs_code) {
		this.setField(5, cwIhs_code);
	}

	public String getCwihs_code() {
		return this.getField(5);
	}

	public void setCwco_code_customer(String cwCo_code_customer) {
		this.setField(6, cwCo_code_customer);
	}

	public String getCwco_code_customer() {
		return this.getField(6);
	}

	public void setCwco_code_supplier(String cwCo_code_supplier) {
		this.setField(7, cwCo_code_supplier);
	}

	public String getCwco_code_supplier() {
		return this.getField(7);
	}

	public void setCwchn_code_supplier(String cwChn_code_supplier) {
		this.setField(8, cwChn_code_supplier);
	}

	public String getCwchn_code_supplier() {
		return this.getField(8);
	}

	public void setCwcw_createdate(String cwCw_createdate) {
		this.setField(9, cwCw_createdate);
	}

	public String getCwcw_createdate() {
		return this.getField(9);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(10, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(10);
	}

	public String toString() {
		return "Code Generate By Kyle";
	}

}
