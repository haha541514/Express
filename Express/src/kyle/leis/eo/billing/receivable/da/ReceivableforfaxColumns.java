package kyle.leis.eo.billing.receivable.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ReceivableforfaxColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ReceivableforfaxColumns() {
		m_astrColumns = new String[18];
	}
	
	public ReceivableforfaxColumns(String coCo_sname, 
            String coCo_labelcode, String bwBw_labelcode, 
            String cwCw_ewbcode, String cwCw_customerewbcode, 
            String coCo_code, String pkPk_sname, 
            String ctCt_name, String cwCw_pieces, 
            String cwCw_grossweight, String cwCw_chargeweight, 
            String cdtDt_name, String fkFk_name, 
            String rvRv_currencyrate, String rvRv_actualtotal, 
            String Sumrvactualtotal, 
            String ckCk_name, String rvRv_occurdate){
		m_astrColumns = new String[19];
		setCoco_sname(coCo_sname);
		setCoco_labelcode(coCo_labelcode);
		setBwbw_labelcode(bwBw_labelcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCoco_code(coCo_code);
		setPkpk_sname(pkPk_sname);
		setCtct_name(ctCt_name);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCdtdt_name(cdtDt_name);
		setFkfk_name(fkFk_name);
		setRvrv_currencyrate(rvRv_currencyrate);
		setRvrv_actualtotal(rvRv_actualtotal);
		setSumrvactualtotal(Sumrvactualtotal);
		setCkck_name(ckCk_name);
		setRvrv_occurdate(rvRv_occurdate);
	}

	public void setCoco_sname(String coCo_sname) {
		this.setField(0, coCo_sname);
	}

	public String getCoco_sname() {
		return this.getField(0);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(1, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(1);
	}

	public void setBwbw_labelcode(String bwBw_labelcode) {
		this.setField(2, bwBw_labelcode);
	}

	public String getBwbw_labelcode() {
		return this.getField(2);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(3, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(3);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(4, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(4);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(5, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(5);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(6, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(6);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(7, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(7);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(8, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(8);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(9, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(9);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(10, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(10);
	}

	public void setCdtdt_name(String cdtDt_name) {
		this.setField(11, cdtDt_name);
	}

	public String getCdtdt_name() {
		return this.getField(11);
	}

	public void setFkfk_name(String fkFk_name) {
		this.setField(12, fkFk_name);
	}

	public String getFkfk_name() {
		return this.getField(12);
	}

	public void setRvrv_currencyrate(String rvRv_currencyrate) {
		this.setField(13, rvRv_currencyrate);
	}

	public String getRvrv_currencyrate() {
		return this.getField(13);
	}

	public void setRvrv_actualtotal(String rvRv_actualtotal) {
		this.setField(14, rvRv_actualtotal);
	}

	public String getRvrv_actualtotal() {
		return this.getField(14);
	}

	public void setSumrvactualtotal(String Sumrvactualtotal) {
		this.setField(15, Sumrvactualtotal);
	}

	public String getSumrvactualtotal() {
		return this.getField(15);
	}

	public void setCkck_name(String ckCk_name) {
		this.setField(16, ckCk_name);
	}

	public String getCkck_name() {
		return this.getField(16);
	}

	public void setRvrv_occurdate(String rvRv_occurdate) {
		this.setField(17, rvRv_occurdate);
	}

	public String getRvrv_occurdate() {
		return this.getField(17);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
