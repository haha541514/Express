package kyle.leis.eo.operation.predictwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredictwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictwaybillColumns() {
		m_astrColumns = new String[47];
	}
	
	public PredictwaybillColumns(String pwbPwb_dutypaidsign, 
            String pwbPwb_code, String pwbPwb_createdate, 
            String pwbPwb_modifydate, String pwbPwb_consigneename, 
            String pwbPwb_consigneetel, String pwbPwb_consigneeaddress1, 
            String pwbPwb_consigneeaddress2, String pwbPwb_consigneecity, 
            String pwbPwb_consigneestate, String pwbPwb_consigneepostcode, 
            String pwbPwb_cargoename, String pwbPwb_cargopieces, 
            String pwbPwb_cargoamount, String pwbPwb_transactionid, 
            String pwbPwb_orderid, String pwbPwb_chargeweight, 
            String pwbPwb_customremark, String pwbsPwbs_code, 
            String pwbsPwbs_name, String pkPk_code, 
            String pkPk_sname, String coCo_code, 
            String coCo_labelcode, String copOp_id, 
            String copOp_name, String mopOp_id, 
            String mopOp_name, String chnChn_code, 
            String chnChn_sname, String pwbPwb_serverewbcode, 
            String dtDt_code, String dtDt_name, 
            String pwbCw_code, String pwbPwb_declaredate, 
            String pwbPwb_printdate, String popOp_id, 
            String popOp_name, String dopOp_id, 
            String dopOp_name, String pwbPwb_consigneenameex, 
            String pwbPwb_consigneeaddressex, String pwbPwb_consigneecityex, 
            String pwbPwb_buyerid, String pwbCk_code, 
            String Subconame, String pwbPwb_pieces){
		m_astrColumns = new String[47];
		setPwbpwb_dutypaidsign(pwbPwb_dutypaidsign);
		setPwbpwb_code(pwbPwb_code);
		setPwbpwb_createdate(pwbPwb_createdate);
		setPwbpwb_modifydate(pwbPwb_modifydate);
		setPwbpwb_consigneename(pwbPwb_consigneename);
		setPwbpwb_consigneetel(pwbPwb_consigneetel);
		setPwbpwb_consigneeaddress1(pwbPwb_consigneeaddress1);
		setPwbpwb_consigneeaddress2(pwbPwb_consigneeaddress2);
		setPwbpwb_consigneecity(pwbPwb_consigneecity);
		setPwbpwb_consigneestate(pwbPwb_consigneestate);
		setPwbpwb_consigneepostcode(pwbPwb_consigneepostcode);
		setPwbpwb_cargoename(pwbPwb_cargoename);
		setPwbpwb_cargopieces(pwbPwb_cargopieces);
		setPwbpwb_cargoamount(pwbPwb_cargoamount);
		setPwbpwb_transactionid(pwbPwb_transactionid);
		setPwbpwb_orderid(pwbPwb_orderid);
		setPwbpwb_chargeweight(pwbPwb_chargeweight);
		setPwbpwb_customremark(pwbPwb_customremark);
		setPwbspwbs_code(pwbsPwbs_code);
		setPwbspwbs_name(pwbsPwbs_name);
		setPkpk_code(pkPk_code);
		setPkpk_sname(pkPk_sname);
		setCoco_code(coCo_code);
		setCoco_labelcode(coCo_labelcode);
		setCopop_id(copOp_id);
		setCopop_name(copOp_name);
		setMopop_id(mopOp_id);
		setMopop_name(mopOp_name);
		setChnchn_code(chnChn_code);
		setChnchn_sname(chnChn_sname);
		setPwbpwb_serverewbcode(pwbPwb_serverewbcode);
		setDtdt_code(dtDt_code);
		setDtdt_name(dtDt_name);
		setPwbcw_code(pwbCw_code);
		setPwbpwb_declaredate(pwbPwb_declaredate);
		setPwbpwb_printdate(pwbPwb_printdate);
		setPopop_id(popOp_id);
		setPopop_name(popOp_name);
		setDopop_id(dopOp_id);
		setDopop_name(dopOp_name);
		setPwbpwb_consigneenameex(pwbPwb_consigneenameex);
		setPwbpwb_consigneeaddressex(pwbPwb_consigneeaddressex);
		setPwbpwb_consigneecityex(pwbPwb_consigneecityex);
		setPwbpwb_buyerid(pwbPwb_buyerid);
		setPwbck_code(pwbCk_code);
		setSubconame(Subconame);
		setPwbpwb_pieces(pwbPwb_pieces);
	}

	public void setPwbpwb_dutypaidsign(String pwbPwb_dutypaidsign) {
		this.setField(0, pwbPwb_dutypaidsign);
	}

	public String getPwbpwb_dutypaidsign() {
		return this.getField(0);
	}

	public void setPwbpwb_code(String pwbPwb_code) {
		this.setField(1, pwbPwb_code);
	}

	public String getPwbpwb_code() {
		return this.getField(1);
	}

	public void setPwbpwb_createdate(String pwbPwb_createdate) {
		this.setField(2, pwbPwb_createdate);
	}

	public String getPwbpwb_createdate() {
		return this.getField(2);
	}

	public void setPwbpwb_modifydate(String pwbPwb_modifydate) {
		this.setField(3, pwbPwb_modifydate);
	}

	public String getPwbpwb_modifydate() {
		return this.getField(3);
	}

	public void setPwbpwb_consigneename(String pwbPwb_consigneename) {
		this.setField(4, pwbPwb_consigneename);
	}

	public String getPwbpwb_consigneename() {
		return this.getField(4);
	}

	public void setPwbpwb_consigneetel(String pwbPwb_consigneetel) {
		this.setField(5, pwbPwb_consigneetel);
	}

	public String getPwbpwb_consigneetel() {
		return this.getField(5);
	}

	public void setPwbpwb_consigneeaddress1(String pwbPwb_consigneeaddress1) {
		this.setField(6, pwbPwb_consigneeaddress1);
	}

	public String getPwbpwb_consigneeaddress1() {
		return this.getField(6);
	}

	public void setPwbpwb_consigneeaddress2(String pwbPwb_consigneeaddress2) {
		this.setField(7, pwbPwb_consigneeaddress2);
	}

	public String getPwbpwb_consigneeaddress2() {
		return this.getField(7);
	}

	public void setPwbpwb_consigneecity(String pwbPwb_consigneecity) {
		this.setField(8, pwbPwb_consigneecity);
	}

	public String getPwbpwb_consigneecity() {
		return this.getField(8);
	}

	public void setPwbpwb_consigneestate(String pwbPwb_consigneestate) {
		this.setField(9, pwbPwb_consigneestate);
	}

	public String getPwbpwb_consigneestate() {
		return this.getField(9);
	}

	public void setPwbpwb_consigneepostcode(String pwbPwb_consigneepostcode) {
		this.setField(10, pwbPwb_consigneepostcode);
	}

	public String getPwbpwb_consigneepostcode() {
		return this.getField(10);
	}

	public void setPwbpwb_cargoename(String pwbPwb_cargoename) {
		this.setField(11, pwbPwb_cargoename);
	}

	public String getPwbpwb_cargoename() {
		return this.getField(11);
	}

	public void setPwbpwb_cargopieces(String pwbPwb_cargopieces) {
		this.setField(12, pwbPwb_cargopieces);
	}

	public String getPwbpwb_cargopieces() {
		return this.getField(12);
	}

	public void setPwbpwb_cargoamount(String pwbPwb_cargoamount) {
		this.setField(13, pwbPwb_cargoamount);
	}

	public String getPwbpwb_cargoamount() {
		return this.getField(13);
	}

	public void setPwbpwb_transactionid(String pwbPwb_transactionid) {
		this.setField(14, pwbPwb_transactionid);
	}

	public String getPwbpwb_transactionid() {
		return this.getField(14);
	}

	public void setPwbpwb_orderid(String pwbPwb_orderid) {
		this.setField(15, pwbPwb_orderid);
	}

	public String getPwbpwb_orderid() {
		return this.getField(15);
	}

	public void setPwbpwb_chargeweight(String pwbPwb_chargeweight) {
		this.setField(16, pwbPwb_chargeweight);
	}

	public String getPwbpwb_chargeweight() {
		return this.getField(16);
	}

	public void setPwbpwb_customremark(String pwbPwb_customremark) {
		this.setField(17, pwbPwb_customremark);
	}

	public String getPwbpwb_customremark() {
		return this.getField(17);
	}

	public void setPwbspwbs_code(String pwbsPwbs_code) {
		this.setField(18, pwbsPwbs_code);
	}

	public String getPwbspwbs_code() {
		return this.getField(18);
	}

	public void setPwbspwbs_name(String pwbsPwbs_name) {
		this.setField(19, pwbsPwbs_name);
	}

	public String getPwbspwbs_name() {
		return this.getField(19);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(20, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(20);
	}

	public void setPkpk_sname(String pkPk_sname) {
		this.setField(21, pkPk_sname);
	}

	public String getPkpk_sname() {
		return this.getField(21);
	}

	public void setCoco_code(String coCo_code) {
		this.setField(22, coCo_code);
	}

	public String getCoco_code() {
		return this.getField(22);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(23, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(23);
	}

	public void setCopop_id(String copOp_id) {
		this.setField(24, copOp_id);
	}

	public String getCopop_id() {
		return this.getField(24);
	}

	public void setCopop_name(String copOp_name) {
		this.setField(25, copOp_name);
	}

	public String getCopop_name() {
		return this.getField(25);
	}

	public void setMopop_id(String mopOp_id) {
		this.setField(26, mopOp_id);
	}

	public String getMopop_id() {
		return this.getField(26);
	}

	public void setMopop_name(String mopOp_name) {
		this.setField(27, mopOp_name);
	}

	public String getMopop_name() {
		return this.getField(27);
	}

	public void setChnchn_code(String chnChn_code) {
		this.setField(28, chnChn_code);
	}

	public String getChnchn_code() {
		return this.getField(28);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(29, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(29);
	}

	public void setPwbpwb_serverewbcode(String pwbPwb_serverewbcode) {
		this.setField(30, pwbPwb_serverewbcode);
	}

	public String getPwbpwb_serverewbcode() {
		return this.getField(30);
	}

	public void setDtdt_code(String dtDt_code) {
		this.setField(31, dtDt_code);
	}

	public String getDtdt_code() {
		return this.getField(31);
	}

	public void setDtdt_name(String dtDt_name) {
		this.setField(32, dtDt_name);
	}

	public String getDtdt_name() {
		return this.getField(32);
	}

	public void setPwbcw_code(String pwbCw_code) {
		this.setField(33, pwbCw_code);
	}

	public String getPwbcw_code() {
		return this.getField(33);
	}

	public void setPwbpwb_declaredate(String pwbPwb_declaredate) {
		this.setField(34, pwbPwb_declaredate);
	}

	public String getPwbpwb_declaredate() {
		return this.getField(34);
	}

	public void setPwbpwb_printdate(String pwbPwb_printdate) {
		this.setField(35, pwbPwb_printdate);
	}

	public String getPwbpwb_printdate() {
		return this.getField(35);
	}

	public void setPopop_id(String popOp_id) {
		this.setField(36, popOp_id);
	}

	public String getPopop_id() {
		return this.getField(36);
	}

	public void setPopop_name(String popOp_name) {
		this.setField(37, popOp_name);
	}

	public String getPopop_name() {
		return this.getField(37);
	}

	public void setDopop_id(String dopOp_id) {
		this.setField(38, dopOp_id);
	}

	public String getDopop_id() {
		return this.getField(38);
	}

	public void setDopop_name(String dopOp_name) {
		this.setField(39, dopOp_name);
	}

	public String getDopop_name() {
		return this.getField(39);
	}

	public void setPwbpwb_consigneenameex(String pwbPwb_consigneenameex) {
		this.setField(40, pwbPwb_consigneenameex);
	}

	public String getPwbpwb_consigneenameex() {
		return this.getField(40);
	}

	public void setPwbpwb_consigneeaddressex(String pwbPwb_consigneeaddressex) {
		this.setField(41, pwbPwb_consigneeaddressex);
	}

	public String getPwbpwb_consigneeaddressex() {
		return this.getField(41);
	}

	public void setPwbpwb_consigneecityex(String pwbPwb_consigneecityex) {
		this.setField(42, pwbPwb_consigneecityex);
	}

	public String getPwbpwb_consigneecityex() {
		return this.getField(42);
	}

	public void setPwbpwb_buyerid(String pwbPwb_buyerid) {
		this.setField(43, pwbPwb_buyerid);
	}

	public String getPwbpwb_buyerid() {
		return this.getField(43);
	}

	public void setPwbck_code(String pwbCk_code) {
		this.setField(44, pwbCk_code);
	}

	public String getPwbck_code() {
		return this.getField(44);
	}

	public void setSubconame(String Subconame) {
		this.setField(45, Subconame);
	}

	public String getSubconame() {
		return this.getField(45);
	}

	public void setPwbpwb_pieces(String pwbPwb_pieces) {
		this.setField(46, pwbPwb_pieces);
	}

	public String getPwbpwb_pieces() {
		return this.getField(46);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
