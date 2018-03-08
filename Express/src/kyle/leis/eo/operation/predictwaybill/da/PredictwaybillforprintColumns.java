package kyle.leis.eo.operation.predictwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredictwaybillforprintColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictwaybillforprintColumns() {
		m_astrColumns = new String[16];
	}
	
	public PredictwaybillforprintColumns(String pwbPwb_code, 
            String pwbPwb_consigneetel, String pwbPwb_consigneecity, 
            String pwbPwb_consigneestate, String pwbPwb_consigneepostcode, 
            String pwbPwb_cargoename, String pwbPwb_cargopieces, 
            String pwbPwb_cargoamount, String pwbPwb_transactionid, 
            String pwbPwb_orderid, String pwbPwb_chargeweight, 
            String pwbPwb_customremark, String pwbPwb_consigneenameex, 
            String pwbPwb_consigneeaddressex, String pwbPwb_consigneecityex, 
            String pwbPwb_serverewbcode){
		m_astrColumns = new String[16];
		setPwbpwb_code(pwbPwb_code);
		setPwbpwb_consigneetel(pwbPwb_consigneetel);
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
		setPwbpwb_consigneenameex(pwbPwb_consigneenameex);
		setPwbpwb_consigneeaddressex(pwbPwb_consigneeaddressex);
		setPwbpwb_consigneecityex(pwbPwb_consigneecityex);
		setPwbpwb_serverewbcode(pwbPwb_serverewbcode);
	}

	public void setPwbpwb_code(String pwbPwb_code) {
		this.setField(0, pwbPwb_code);
	}

	public String getPwbpwb_code() {
		return this.getField(0);
	}

	public void setPwbpwb_consigneetel(String pwbPwb_consigneetel) {
		this.setField(1, pwbPwb_consigneetel);
	}

	public String getPwbpwb_consigneetel() {
		return this.getField(1);
	}

	public void setPwbpwb_consigneecity(String pwbPwb_consigneecity) {
		this.setField(2, pwbPwb_consigneecity);
	}

	public String getPwbpwb_consigneecity() {
		return this.getField(2);
	}

	public void setPwbpwb_consigneestate(String pwbPwb_consigneestate) {
		this.setField(3, pwbPwb_consigneestate);
	}

	public String getPwbpwb_consigneestate() {
		return this.getField(3);
	}

	public void setPwbpwb_consigneepostcode(String pwbPwb_consigneepostcode) {
		this.setField(4, pwbPwb_consigneepostcode);
	}

	public String getPwbpwb_consigneepostcode() {
		return this.getField(4);
	}

	public void setPwbpwb_cargoename(String pwbPwb_cargoename) {
		this.setField(5, pwbPwb_cargoename);
	}

	public String getPwbpwb_cargoename() {
		return this.getField(5);
	}

	public void setPwbpwb_cargopieces(String pwbPwb_cargopieces) {
		this.setField(6, pwbPwb_cargopieces);
	}

	public String getPwbpwb_cargopieces() {
		return this.getField(6);
	}

	public void setPwbpwb_cargoamount(String pwbPwb_cargoamount) {
		this.setField(7, pwbPwb_cargoamount);
	}

	public String getPwbpwb_cargoamount() {
		return this.getField(7);
	}

	public void setPwbpwb_transactionid(String pwbPwb_transactionid) {
		this.setField(8, pwbPwb_transactionid);
	}

	public String getPwbpwb_transactionid() {
		return this.getField(8);
	}

	public void setPwbpwb_orderid(String pwbPwb_orderid) {
		this.setField(9, pwbPwb_orderid);
	}

	public String getPwbpwb_orderid() {
		return this.getField(9);
	}

	public void setPwbpwb_chargeweight(String pwbPwb_chargeweight) {
		this.setField(10, pwbPwb_chargeweight);
	}

	public String getPwbpwb_chargeweight() {
		return this.getField(10);
	}

	public void setPwbpwb_customremark(String pwbPwb_customremark) {
		this.setField(11, pwbPwb_customremark);
	}

	public String getPwbpwb_customremark() {
		return this.getField(11);
	}

	public void setPwbpwb_consigneenameex(String pwbPwb_consigneenameex) {
		this.setField(12, pwbPwb_consigneenameex);
	}

	public String getPwbpwb_consigneenameex() {
		return this.getField(12);
	}

	public void setPwbpwb_consigneeaddressex(String pwbPwb_consigneeaddressex) {
		this.setField(13, pwbPwb_consigneeaddressex);
	}

	public String getPwbpwb_consigneeaddressex() {
		return this.getField(13);
	}

	public void setPwbpwb_consigneecityex(String pwbPwb_consigneecityex) {
		this.setField(14, pwbPwb_consigneecityex);
	}

	public String getPwbpwb_consigneecityex() {
		return this.getField(14);
	}

	public void setPwbpwb_serverewbcode(String pwbPwb_serverewbcode) {
		this.setField(15, pwbPwb_serverewbcode);
	}

	public String getPwbpwb_serverewbcode() {
		return this.getField(15);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
