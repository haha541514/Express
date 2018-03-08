package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillforbillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillforbillColumns() {
		m_astrColumns = new String[47];
	}
	
	public WaybillforbillColumns(String cwCw_code, 
            String cwCw_customerewbcode, String cwCw_ewbcode, 
            String cwCw_serverewbcode, String chnChn_sname, 
            String coCo_code, String coCo_labelcode, 
            String coCo_sname, String cwCw_grossweight, 
            String cwCw_pieces, String cwCw_chargeweight, 
            String cwCw_serverchargeweight, String pkPk_code, 
            String pkPk_sname, String pmPm_code, 
            String pmPm_name, String ctCt_code, 
            String ctCt_name, String odtDt_code, 
            String odtDt_hubcode, String sdtDt_code, 
            String sdtDt_hubcode, String ddtDt_code, 
            String ddtDt_hubcode, String cddtDt_code, 
            String cddtDt_hubcode, String bwBw_labelcode, 
            String bwAdd_date, String dbwBw_labelcode, 
            String dbwAdd_date, String cwsCws_code, 
            String cwsCws_name, String Rvtotal, 
            String Rvrmbtotal, String Pytotal, 
            String Pyrmbtotal, String Pyalltotal, 
            String scoCo_code, String scoCo_labelcode, 
            String scoCo_sname, String Rvusdtotal,
            String Pyusdtotal, String eeEe_code, 
            String eeEe_sname,  String Pyeurttoal,
            String cctCt_code, String cctCt_name){
		m_astrColumns = new String[47];
		setCwcw_code(cwCw_code);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setChnchn_sname(chnChn_sname);
		setCoco_code(coCo_code);
		setCoco_labelcode(coCo_labelcode);
		setCoco_sname(coCo_sname);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
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
		setCddtdt_code(cddtDt_code);
		setCddtdt_hubcode(cddtDt_hubcode);
		setBwbw_labelcode(bwBw_labelcode);
		setBwadd_date(bwAdd_date);
		setDbwbw_labelcode(dbwBw_labelcode);
		setDbwadd_date(dbwAdd_date);
		setCwscws_code(cwsCws_code);
		setCwscws_name(cwsCws_name);
		setRvtotal(Rvtotal);
		setRvrmbtotal(Rvrmbtotal);
		setPytotal(Pytotal);
		setPyrmbtotal(Pyrmbtotal);
		setPyalltotal(Pyalltotal);
		setScoco_code(scoCo_code);
		setScoco_labelcode(scoCo_labelcode);
		setScoco_sname(scoCo_sname);
		setRvusdtotal(Rvusdtotal);
		setPyusdtotal(Pyusdtotal);
		setEeee_code(eeEe_code);
		setEeee_sname(eeEe_sname);		
		setCctct_code(cctCt_code);
		setCctct_name(cctCt_name);
		setPyeurtotal(Pyeurttoal);
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

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(2, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(2);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(3, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(3);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(4, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(4);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(5, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(5);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(6, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(6);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(7, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(7);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(8, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(8);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(9, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(9);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(10, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(10);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(11, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(11);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(12, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(12);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(13, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(13);
	}

	public void setPmpm_code(String pmPm_code) {
		this.setField(14, pmPm_code);
	}

	public String getPmpm_code() {
		return this.getField(14);
	}

	public void setPmpm_name(String pmPm_name) {
		this.setField(15, pmPm_name);
	}

	public String getPmpm_name() {
		return this.getField(15);
	}

	public void setCtct_code(String ctCt_code) {
		this.setField(16, ctCt_code);
	}

	public String getCtct_code() {
		return this.getField(16);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(17, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(17);
	}

	public void setOdtdt_code(String odtDt_code) {
		this.setField(18, odtDt_code);
	}

	public String getOdtdt_code() {
		return this.getField(18);
	}

	public void setOdtdt_hubcode(String odtDt_hubcode) {
		this.setField(19, odtDt_hubcode);
	}

	public String getOdtdt_hubcode() {
		return this.getField(19);
	}

	public void setSdtdt_code(String sdtDt_code) {
		this.setField(20, sdtDt_code);
	}

	public String getSdtdt_code() {
		return this.getField(20);
	}

	public void setSdtdt_hubcode(String sdtDt_hubcode) {
		this.setField(21, sdtDt_hubcode);
	}

	public String getSdtdt_hubcode() {
		return this.getField(21);
	}

	public void setDdtdt_code(String ddtDt_code) {
		this.setField(22, ddtDt_code);
	}

	public String getDdtdt_code() {
		return this.getField(22);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(23, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(23);
	}

	public void setCddtdt_code(String cddtDt_code) {
		this.setField(24, cddtDt_code);
	}

	public String getCddtdt_code() {
		return this.getField(24);
	}

	public void setCddtdt_hubcode(String cddtDt_hubcode) {
		this.setField(25, cddtDt_hubcode);
	}

	public String getCddtdt_hubcode() {
		return this.getField(25);
	}

	public void setBwbw_labelcode(String bwBw_labelcode) {
		this.setField(26, bwBw_labelcode);
	}

	public String getBwbw_labelcode() {
		return this.getField(26);
	}

	public void setBwadd_date(String bwAdd_date) {
		this.setField(27, bwAdd_date);
	}

	public String getBwadd_date() {
		return this.getField(27);
	}

	public void setDbwbw_labelcode(String dbwBw_labelcode) {
		this.setField(28, dbwBw_labelcode);
	}

	public String getDbwbw_labelcode() {
		return this.getField(28);
	}

	public void setDbwadd_date(String dbwAdd_date) {
		this.setField(29, dbwAdd_date);
	}

	public String getDbwadd_date() {
		return this.getField(29);
	}

	public void setCwscws_code(String cwsCws_code) {
		this.setField(30, cwsCws_code);
	}

	public String getCwscws_code() {
		return this.getField(30);
	}

	public void setCwscws_name(String cwsCws_name) {
		this.setField(31, cwsCws_name);
	}

	public String getCwscws_name() {
		return this.getField(31);
	}

	public void setRvtotal(String Rvtotal) {
		this.setField(32, Rvtotal);
	}

	public String getRvtotal() {
		return this.getField(32);
	}

	public void setRvrmbtotal(String Rvrmbtotal) {
		this.setField(33, Rvrmbtotal);
	}

	public String getRvrmbtotal() {
		return this.getField(33);
	}

	public void setPytotal(String Pytotal) {
		this.setField(34, Pytotal);
	}

	public String getPytotal() {
		return this.getField(34);
	}

	public void setPyrmbtotal(String Pyrmbtotal) {
		this.setField(35, Pyrmbtotal);
	}

	public String getPyrmbtotal() {
		return this.getField(35);
	}

	public void setPyalltotal(String Pyalltotal) {
		this.setField(36, Pyalltotal);
	}

	public String getPyalltotal() {
		return this.getField(36);
	}

	public void setScoco_code(String scoCo_code) {
		this.setField(37, scoCo_code);
	}

	public String getScoco_code() {
		return this.getField(37);
	}

	public void setScoco_labelcode(String scoCo_labelcode) {
		this.setField(38, scoCo_labelcode);
	}

	public String getScoco_labelcode() {
		return this.getField(38);
	}

	public void setScoco_sname(String scoCo_sname) {
		this.setField(39, scoCo_sname);
	}

	public String getScoco_sname() {
		return this.getField(39);
	}
	
	public void setRvusdtotal(String Rvusdtotal) {
		this.setField(40, Rvusdtotal);
	}

	public String getRvusdtotal() {
		return this.getField(40);
	}	
	
	public void setPyusdtotal(String Pyusdtotal) {
		this.setField(41, Pyusdtotal);
	}

	public String getPyusdtotal() {
		return this.getField(41);
	}	
	
	public void setEeee_code(String eeEe_code) {
		this.setField(42, eeEe_code);
	}

	public String getEeee_code() {
		return this.getField(42);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(43, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(43);
	}	
	
	public void setPyeurtotal(String Pyusdtotal) {
		this.setField(44, Pyusdtotal);
	}

	public String getPyeurtotal() {
		return this.getField(44);
	}		
	
	public void setCctct_code(String cctCt_code) {
		this.setField(45, cctCt_code);
	}

	public String getCctct_code() {
		return this.getField(45);
	}

	public void setCctct_name(String cctCt_name) {
		this.setField(46, cctCt_name);
	}

	public String getCctct_name() {
		return this.getField(46);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
