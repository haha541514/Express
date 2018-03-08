package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class PredictwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictwaybillColumns() {
		m_astrColumns = new String[19];
	}
	
	public PredictwaybillColumns(String cwCwcustomerewbcode, 
            BigDecimal cwCwgrossweight, int cwCwpieces, 
            Date cwCwcreatedate, String cwpkPkcode, 
            String cwpkPkname, String cwcocusCocode, 
            String cwdtocodeDtcode, String cwdtocodeDtname, 
            String cwhwHwconsigneename, String cwhwHwconsigneepostcode, 
            String cwhwHwconsigneetelephone, String cwhwHwconsigneeaddress1, 
            String cwhwHwconsigneeaddress2, String cwhwHwconsigneeaddress3, 
            String cwhwHwremark, BigDecimal cwCwtransferchargeweight,
            String cwPostcodeDestination, BigDecimal cwCwChargeweight){
		m_astrColumns = new String[19];
		setCwcwcustomerewbcode(cwCwcustomerewbcode);
		setCwcwgrossweight(cwCwgrossweight);
		setCwcwpieces(cwCwpieces);
		setCwcwcreatedate(cwCwcreatedate);
		setCwpkpkcode(cwpkPkcode);
		setCwpkpkname(cwpkPkname);
		setCwcocuscocode(cwcocusCocode);
		setCwdtocodedtcode(cwdtocodeDtcode);
		setCwdtocodedtname(cwdtocodeDtname);
		setCwhwhwconsigneename(cwhwHwconsigneename);
		setCwhwhwconsigneepostcode(cwhwHwconsigneepostcode);
		setCwhwhwconsigneetelephone(cwhwHwconsigneetelephone);
		setCwhwhwconsigneeaddress1(cwhwHwconsigneeaddress1);
		setCwhwhwconsigneeaddress2(cwhwHwconsigneeaddress2);
		setCwhwhwconsigneeaddress3(cwhwHwconsigneeaddress3);
		setCwhwhwremark(cwhwHwremark);
		setCwcwtransferchargeweight(cwCwtransferchargeweight);
		setCwPostcodeDestination(cwPostcodeDestination);
		setCwcwchargeweight(cwCwChargeweight);
	}

	public void setCwcwcustomerewbcode(String cwCwcustomerewbcode) {
		this.setField(0, cwCwcustomerewbcode);
	}

	public String getCwcwcustomerewbcode() {
		return this.getField(0);
	}

	public void setCwcwgrossweight(BigDecimal cwCwgrossweight) {
		this.setField(1, cwCwgrossweight);
	}

	public String getCwcwgrossweight() {
		return this.getField(1);
	}

	public void setCwcwpieces(int cwCwpieces) {
		this.setField(2, cwCwpieces);
	}

	public String getCwcwpieces() {
		return this.getField(2);
	}

	public void setCwcwcreatedate(Date cwCwcreatedate) {
		this.setField(3, cwCwcreatedate);
	}

	public String getCwcwcreatedate() {
		return this.getField(3);
	}

	public void setCwpkpkcode(String cwpkPkcode) {
		this.setField(4, cwpkPkcode);
	}

	public String getCwpkpkcode() {
		return this.getField(4);
	}

	public void setCwpkpkname(String cwpkPkname) {
		this.setField(5, cwpkPkname);
	}

	public String getCwpkpkname() {
		return this.getField(5);
	}

	public void setCwcocuscocode(String cwcocusCocode) {
		this.setField(6, cwcocusCocode);
	}

	public String getCwcocuscocode() {
		return this.getField(6);
	}

	public void setCwdtocodedtcode(String cwdtocodeDtcode) {
		this.setField(7, cwdtocodeDtcode);
	}

	public String getCwdtocodedtcode() {
		return this.getField(7);
	}

	public void setCwdtocodedtname(String cwdtocodeDtname) {
		this.setField(8, cwdtocodeDtname);
	}

	public String getCwdtocodedtname() {
		return this.getField(8);
	}

	public void setCwhwhwconsigneename(String cwhwHwconsigneename) {
		this.setField(9, cwhwHwconsigneename);
	}

	public String getCwhwhwconsigneename() {
		return this.getField(9);
	}

	public void setCwhwhwconsigneepostcode(String cwhwHwconsigneepostcode) {
		this.setField(10, cwhwHwconsigneepostcode);
	}

	public String getCwhwhwconsigneepostcode() {
		return this.getField(10);
	}

	public void setCwhwhwconsigneetelephone(String cwhwHwconsigneetelephone) {
		this.setField(11, cwhwHwconsigneetelephone);
	}

	public String getCwhwhwconsigneetelephone() {
		return this.getField(11);
	}

	public void setCwhwhwconsigneeaddress1(String cwhwHwconsigneeaddress1) {
		this.setField(12, cwhwHwconsigneeaddress1);
	}

	public String getCwhwhwconsigneeaddress1() {
		return this.getField(12);
	}

	public void setCwhwhwconsigneeaddress2(String cwhwHwconsigneeaddress2) {
		this.setField(13, cwhwHwconsigneeaddress2);
	}

	public String getCwhwhwconsigneeaddress2() {
		return this.getField(13);
	}

	public void setCwhwhwconsigneeaddress3(String cwhwHwconsigneeaddress3) {
		this.setField(14, cwhwHwconsigneeaddress3);
	}

	public String getCwhwhwconsigneeaddress3() {
		return this.getField(14);
	}

	public void setCwhwhwremark(String cwhwHwremark) {
		this.setField(15, cwhwHwremark);
	}

	public String getCwhwhwremark() {
		return this.getField(15);
	}

	public void setCwcwtransferchargeweight(BigDecimal cwCwtransferchargeweight) {
		this.setField(16, cwCwtransferchargeweight);
	}

	public String getCwcwtransferchargeweight() {
		return this.getField(16);
	}
	
	public void setCwPostcodeDestination(String cwPostcodeDestination) {
		this.setField(17, cwPostcodeDestination);
	}

	public String getCwPostcodeDestination() {
		return this.getField(17);
	}
	
	public void setCwcwchargeweight(BigDecimal cwCwchargeweight) {
		this.setField(18, cwCwchargeweight);
	}

	public String getCwcwchargeweight() {
		return this.getField(18);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
