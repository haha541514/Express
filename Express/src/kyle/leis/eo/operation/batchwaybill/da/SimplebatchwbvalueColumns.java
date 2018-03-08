package kyle.leis.eo.operation.batchwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplebatchwbvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplebatchwbvalueColumns() {
		m_astrColumns = new String[14];
	}
	
	public SimplebatchwbvalueColumns(String bwvBw_code, 
            String bwvCw_code, String bwvBwbv_serialno, 
            String bwvBwbv_baglabelcode, String bwvBwbv_zonename, 
            String bwvBwbv_id, String cwCw_serverchargeweight, 
            String cwCw_billcounts, String cwCw_bagcounts, 
            String pkPk_name, String pkPk_code, 
            String cwCw_ewbcode, String dtDt_hubcode, 
            String bwvBwbv_issuecontent){
		m_astrColumns = new String[14];
		setBwvbw_code(bwvBw_code);
		setBwvcw_code(bwvCw_code);
		setBwvbwbv_serialno(bwvBwbv_serialno);
		setBwvbwbv_baglabelcode(bwvBwbv_baglabelcode);
		setBwvbwbv_zonename(bwvBwbv_zonename);
		setBwvbwbv_id(bwvBwbv_id);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setCwcw_billcounts(cwCw_billcounts);
		setCwcw_bagcounts(cwCw_bagcounts);
		setPkpk_name(pkPk_name);
		setPkpk_code(pkPk_code);
		setCwcw_ewbcode(cwCw_ewbcode);
		setDtdt_hubcode(dtDt_hubcode);
		setBwvbwbv_issuecontent(bwvBwbv_issuecontent);
	}

	public void setBwvbw_code(String bwvBw_code) {
		this.setField(0, bwvBw_code);
	}

	public String getBwvbw_code() {
		return this.getField(0);
	}

	public void setBwvcw_code(String bwvCw_code) {
		this.setField(1, bwvCw_code);
	}

	public String getBwvcw_code() {
		return this.getField(1);
	}

	public void setBwvbwbv_serialno(String bwvBwbv_serialno) {
		this.setField(2, bwvBwbv_serialno);
	}

	public String getBwvbwbv_serialno() {
		return this.getField(2);
	}

	public void setBwvbwbv_baglabelcode(String bwvBwbv_baglabelcode) {
		this.setField(3, bwvBwbv_baglabelcode);
	}

	public String getBwvbwbv_baglabelcode() {
		return this.getField(3);
	}

	public void setBwvbwbv_zonename(String bwvBwbv_zonename) {
		this.setField(4, bwvBwbv_zonename);
	}

	public String getBwvbwbv_zonename() {
		return this.getField(4);
	}

	public void setBwvbwbv_id(String bwvBwbv_id) {
		this.setField(5, bwvBwbv_id);
	}

	public String getBwvbwbv_id() {
		return this.getField(5);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(6, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(6);
	}

	public void setCwcw_billcounts(String cwCw_billcounts) {
		this.setField(7, cwCw_billcounts);
	}

	public String getCwcw_billcounts() {
		return this.getField(7);
	}

	public void setCwcw_bagcounts(String cwCw_bagcounts) {
		this.setField(8, cwCw_bagcounts);
	}

	public String getCwcw_bagcounts() {
		return this.getField(8);
	}

	public void setPkpk_name(String pkPk_name) {
		this.setField(9, pkPk_name);
	}

	public String getPkpk_name() {
		return this.getField(9);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(10, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(10);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(11, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(11);
	}

	public void setDtdt_hubcode(String dtDt_hubcode) {
		this.setField(12, dtDt_hubcode);
	}

	public String getDtdt_hubcode() {
		return this.getField(12);
	}

	public void setBwvbwbv_issuecontent(String bwvBwbv_issuecontent) {
		this.setField(13, bwvBwbv_issuecontent);
	}

	public String getBwvbwbv_issuecontent() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
