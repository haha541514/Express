package kyle.leis.eo.operation.manifest.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ForcreatemanifestColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ForcreatemanifestColumns() {
		m_astrColumns = new String[23];
	}
	
	public ForcreatemanifestColumns(String ropOp_name, 
            String hwbHw_recorddate, String cwCw_code, 
            String cwCw_pieces, String cwCw_grossweight, 
            String cwCw_chargeweight, String cwCw_transferpieces, 
            String cwCw_transfergrossweight, String cwCw_transferchargeweight, 
            String cwCw_customerewbcode, String cwCw_serverewbcode, 
            String cwCw_ewbcode, String chnChn_code, 
            String chnChn_sname, String chnChn_sename, 
            String pkPk_code, String pkPk_sename, 
            String ctCt_code, String ctCt_name, 
            String pmPm_code, String pmPm_name, 
            String eeEe_code, String eeEe_sname){
		m_astrColumns = new String[23];
		setRopop_name(ropOp_name);
		setHwbhw_recorddate(hwbHw_recorddate);
		setCwcw_code(cwCw_code);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwcw_transferpieces(cwCw_transferpieces);
		setCwcw_transfergrossweight(cwCw_transfergrossweight);
		setCwcw_transferchargeweight(cwCw_transferchargeweight);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setChnchn_code(chnChn_code);
		setChnchn_sname(chnChn_sname);
		setChnchn_sename(chnChn_sename);
		setPkpk_code(pkPk_code);
		setPkpk_sename(pkPk_sename);
		setCtct_code(ctCt_code);
		setCtct_name(ctCt_name);
		setPmpm_code(pmPm_code);
		setPmpm_name(pmPm_name);
		setEeee_code(eeEe_code);
		setEeee_sname(eeEe_sname);
	}

	public void setRopop_name(String ropOp_name) {
		this.setField(0, ropOp_name);
	}

	public String getRopop_name() {
		return this.getField(0);
	}

	public void setHwbhw_recorddate(String hwbHw_recorddate) {
		this.setField(1, hwbHw_recorddate);
	}

	public String getHwbhw_recorddate() {
		return this.getField(1);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(2, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(2);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(3, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(3);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(4, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(4);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(5, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(5);
	}

	public void setCwcw_transferpieces(String cwCw_transferpieces) {
		this.setField(6, cwCw_transferpieces);
	}

	public String getCwcw_transferpieces() {
		return this.getField(6);
	}

	public void setCwcw_transfergrossweight(String cwCw_transfergrossweight) {
		this.setField(7, cwCw_transfergrossweight);
	}

	public String getCwcw_transfergrossweight() {
		return this.getField(7);
	}

	public void setCwcw_transferchargeweight(String cwCw_transferchargeweight) {
		this.setField(8, cwCw_transferchargeweight);
	}

	public String getCwcw_transferchargeweight() {
		return this.getField(8);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(9, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(9);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(10, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(10);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(11, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(11);
	}

	public void setChnchn_code(String chnChn_code) {
		this.setField(12, chnChn_code);
	}

	public String getChnchn_code() {
		return this.getField(12);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(13, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(13);
	}

	public void setChnchn_sename(String chnChn_sename) {
		this.setField(14, chnChn_sename);
	}

	public String getChnchn_sename() {
		return this.getField(14);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(15, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(15);
	}

	public void setPkpk_sename(String pkPk_sename) {
		this.setField(16, pkPk_sename);
	}

	public String getPkpk_sename() {
		return this.getField(16);
	}

	public void setCtct_code(String ctCt_code) {
		this.setField(17, ctCt_code);
	}

	public String getCtct_code() {
		return this.getField(17);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(18, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(18);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(19, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(19);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(20, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(20);
	}

	public void setEeee_code(String eeEe_code) {
		this.setField(21, eeEe_code);
	}

	public String getEeee_code() {
		return this.getField(21);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(22, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
