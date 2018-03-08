package kyle.leis.eo.operation.batchwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DeparturebatchwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DeparturebatchwaybillColumns() {
		m_astrColumns = new String[21];
	}
	
	public DeparturebatchwaybillColumns(String bwBw_code, 
            String bwBw_createdate, String bwBw_modifydate, 
            String bwBw_completedate, String bwBw_auditdate, 
            String bwBw_approvedate, String bwAdd_date, 
            String bwBw_remark, String bwBw_totalgrossweight, 
            String bwBw_totalpieces, String bwBw_labelcode, 
            String bwBw_batchnumber, String bwsBws_code, 
            String bwsBws_name, String adtAdt_code, 
            String adtAdt_name, String coCo_code, 
            String coCo_name, String coCo_sname, 
            String coCo_sename, String cwpCp_baglabelcode){
		m_astrColumns = new String[21];
		setBwbw_code(bwBw_code);
		setBwbw_createdate(bwBw_createdate);
		setBwbw_modifydate(bwBw_modifydate);
		setBwbw_completedate(bwBw_completedate);
		setBwbw_auditdate(bwBw_auditdate);
		setBwbw_approvedate(bwBw_approvedate);
		setBwadd_date(bwAdd_date);
		setBwbw_remark(bwBw_remark);
		setBwbw_totalgrossweight(bwBw_totalgrossweight);
		setBwbw_totalpieces(bwBw_totalpieces);
		setBwbw_labelcode(bwBw_labelcode);
		setBwbw_batchnumber(bwBw_batchnumber);
		setBwsbws_code(bwsBws_code);
		setBwsbws_name(bwsBws_name);
		setAdtadt_code(adtAdt_code);
		setAdtadt_name(adtAdt_name);
		setCoco_code(coCo_code);
		setCoco_name(coCo_name);
		setCoco_sname(coCo_sname);
		setCoco_sename(coCo_sename);
		setCwpcp_baglabelcode(cwpCp_baglabelcode);
	}

	public void setBwbw_code(String bwBw_code) {
		this.setField(0, bwBw_code);
	}

	public String getBwbw_code() {
		return this.getField(0);
	}

	public void setBwbw_createdate(String bwBw_createdate) {
		this.setField(1, bwBw_createdate);
	}

	public String getBwbw_createdate() {
		return this.getField(1);
	}

	public void setBwbw_modifydate(String bwBw_modifydate) {
		this.setField(2, bwBw_modifydate);
	}

	public String getBwbw_modifydate() {
		return this.getField(2);
	}

	public void setBwbw_completedate(String bwBw_completedate) {
		this.setField(3, bwBw_completedate);
	}

	public String getBwbw_completedate() {
		return this.getField(3);
	}

	public void setBwbw_auditdate(String bwBw_auditdate) {
		this.setField(4, bwBw_auditdate);
	}

	public String getBwbw_auditdate() {
		return this.getField(4);
	}

	public void setBwbw_approvedate(String bwBw_approvedate) {
		this.setField(5, bwBw_approvedate);
	}

	public String getBwbw_approvedate() {
		return this.getField(5);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(6, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(6);
	}

	public void setBwbw_remark(String bwBw_remark) {
		this.setField(7, bwBw_remark);
	}

	public String getBwbw_remark() {
		return this.getField(7);
	}

	public void setBwbw_totalgrossweight(String bwBw_totalgrossweight) {
		this.setField(8, bwBw_totalgrossweight);
	}

	public String getBwbw_totalgrossweight() {
		return this.getField(8);
	}

	public void setBwbw_totalpieces(String bwBw_totalpieces) {
		this.setField(9, bwBw_totalpieces);
	}

	public String getBwbw_totalpieces() {
		return this.getField(9);
	}

	public void setBwbw_labelcode(String bwBw_labelcode) {
		this.setField(10, bwBw_labelcode);
	}

	public String getBwbw_labelcode() {
		return this.getField(10);
	}

	public void setBwbw_batchnumber(String bwBw_batchnumber) {
		this.setField(11, bwBw_batchnumber);
	}

	public String getBwbw_batchnumber() {
		return this.getField(11);
	}

	public void setBwsbws_code(String bwsBws_code) {
		this.setField(12, bwsBws_code);
	}

	public String getBwsbws_code() {
		return this.getField(12);
	}

	public void setBwsbws_name(String bwsBws_name) {
		this.setField(13, bwsBws_name);
	}

	public String getBwsbws_name() {
		return this.getField(13);
	}

	public void setAdtadt_code(String adtAdt_code) {
		this.setField(14, adtAdt_code);
	}

	public String getAdtadt_code() {
		return this.getField(14);
	}

	public void setAdtadt_name(String adtAdt_name) {
		this.setField(15, adtAdt_name);
	}

	public String getAdtadt_name() {
		return this.getField(15);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(16, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(16);
	}

	public void setCoco_name(String coCo_name) {
		this.setField(17, coCo_name);
	}

	public String getCoco_name() {
		return this.getField(17);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(18, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(18);
	}

	public void setCoco_sename(String coCo_sename) {
		this.setField(19, coCo_sename);
	}

	public String getCoco_sename() {
		return this.getField(19);
	}

	public void setCwpcp_baglabelcode(String cwpCp_baglabelcode) {
		this.setField(20, cwpCp_baglabelcode);
	}

	public String getCwpcp_baglabelcode() {
		return this.getField(20);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
