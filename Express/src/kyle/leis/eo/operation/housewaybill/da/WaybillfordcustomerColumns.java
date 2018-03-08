package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillfordcustomerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillfordcustomerColumns() {
		m_astrColumns = new String[20];
	}
	
	public WaybillfordcustomerColumns(String cwCw_code, 
            String cwCw_customerewbcode, String cwCw_chargeweight, 
            String pkPk_code, String pkPk_sname, 
            String pmPm_code, String pmPm_name, 
            String ctCt_code, String ctCt_name, 
            String odtDt_code, String odtDt_hubcode, 
            String sdtDt_code, String sdtDt_hubcode, 
            String ddtDt_code, String ddtDt_hubcode, 
            String cddtDt_hubcode, String bwBw_labelcode, 
            String bwAdd_date, String Rvtotal,
            String cwCw_ewbcode){
		m_astrColumns = new String[20];
		setCwcw_code(cwCw_code);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_chargeweight(cwCw_chargeweight);
		setPkpk_code(pkPk_code);
		setPkpk_sname(pkPk_sname);
		setPmpm_code(pmPm_code);
		setPmpm_name(pmPm_name);
		setCtct_code(ctCt_code);
		setCtct_name(ctCt_name);
		setOdtdt_code(odtDt_code);
		setOdtdt_hubcode(odtDt_hubcode);
		setSdtdt_code(sdtDt_code);
		setSdtdt_hubcode(sdtDt_hubcode);
		setDdtdt_code(ddtDt_code);
		setDdtdt_hubcode(ddtDt_hubcode);
		setCddtdt_hubcode(cddtDt_hubcode);
		setBwbw_labelcode(bwBw_labelcode);
		setBwadd_date(bwAdd_date);
		setRvtotal(Rvtotal);
		setCwcw_ewbcode(cwCw_ewbcode);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(1, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(1);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(2, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(2);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(3, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(3);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(4, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(4);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(5, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(5);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(6, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(6);
	}

	public void setCtct_code(String ctCt_code) {
		this.setField(7, ctCt_code);
	}

	public String getCtct_code() {
		return this.getField(7);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(8, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(8);
	}

	public void setOdtdt_code(String odtDt_code) {
		this.setField(9, odtDt_code);
	}

	public String getOdtdt_code() {
		return this.getField(9);
	}

	public void setOdtdt_hubcode(String odtDt_hubcode) {
		this.setField(10, odtDt_hubcode);
	}

	public String getOdtdt_hubcode() {
		return this.getField(10);
	}

	public void setSdtdt_code(String sdtDt_code) {
		this.setField(11, sdtDt_code);
	}

	public String getSdtdt_code() {
		return this.getField(11);
	}

	public void setSdtdt_hubcode(String sdtDt_hubcode) {
		this.setField(12, sdtDt_hubcode);
	}

	public String getSdtdt_hubcode() {
		return this.getField(12);
	}

	public void setDdtdt_code(String ddtDt_code) {
		this.setField(13, ddtDt_code);
	}

	public String getDdtdt_code() {
		return this.getField(13);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(14, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(14);
	}

	public void setCddtdt_hubcode(String cddtDt_hubcode) {
		this.setField(15, cddtDt_hubcode);
	}

	public String getCddtdt_hubcode() {
		return this.getField(15);
	}

	public void setBwbw_labelcode(String bwBw_labelcode) {
		this.setField(16, bwBw_labelcode);
	}

	public String getBwbw_labelcode() {
		return this.getField(16);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(17, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(17);
	}

	public void setRvtotal(String Rvtotal) {
		this.setField(18, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(18);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(19, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(19);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
