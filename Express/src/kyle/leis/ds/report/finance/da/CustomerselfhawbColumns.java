package kyle.leis.ds.report.finance.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerselfhawbColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerselfhawbColumns() {
		m_astrColumns = new String[17];
	}
	
	public CustomerselfhawbColumns(String cwCw_customerewbcode, 
            String cwCw_serverewbcode, String Ciename, 
            String ctCt_name, String cwCw_pieces, 
            String cwCw_chargeweight, String Citotal, 
            String cdtDt_hubcode, String hwHw_shippercompany, 
            String hwHw_shippername, String Hw_shipperaddress, 
            String hwHw_consigneecompany, String hwHw_consigneename, 
            String Hw_consigneeaddress, String hwHw_signindate, 
            String hwHw_signoutdate, String eeEe_sname){
		m_astrColumns = new String[17];
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCiename(Ciename);
		setCtct_name(ctCt_name);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCitotal(Citotal);
		setCdtdt_hubcode(cdtDt_hubcode);
		setHwhw_shippercompany(hwHw_shippercompany);
		setHwhw_shippername(hwHw_shippername);
		setHw_shipperaddress(Hw_shipperaddress);
		setHwhw_consigneecompany(hwHw_consigneecompany);
		setHwhw_consigneename(hwHw_consigneename);
		setHw_consigneeaddress(Hw_consigneeaddress);
		setHwhw_signindate(hwHw_signindate);
		setHwhw_signoutdate(hwHw_signoutdate);
		setEeee_sname(eeEe_sname);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(0, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(0);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(1, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(1);
	}

	public void setCiename(String Ciename) {
		this.setField(2, Ciename);
	}

	public String getCiename() {
		return this.getField(2);
	}

	public void setCtct_name(String ctCt_name) {
		this.setField(3, ctCt_name);
	}

	public String getCtct_name() {
		return this.getField(3);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(4, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(4);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(5, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(5);
	}

	public void setCitotal(String Citotal) {
		this.setField(6, Citotal);
	}

	public String getCitotal() {
		return this.getField(6);
	}

	public void setCdtdt_hubcode(String cdtDt_hubcode) {
		this.setField(7, cdtDt_hubcode);
	}

	public String getCdtdt_hubcode() {
		return this.getField(7);
	}

	public void setHwhw_shippercompany(String hwHw_shippercompany) {
		this.setField(8, hwHw_shippercompany);
	}

	public String getHwhw_shippercompany() {
		return this.getField(8);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(9, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(9);
	}

	public void setHw_shipperaddress(String Hw_shipperaddress) {
		this.setField(10, Hw_shipperaddress);
	}

	public String getHw_shipperaddress() {
		return this.getField(10);
	}

	public void setHwhw_consigneecompany(String hwHw_consigneecompany) {
		this.setField(11, hwHw_consigneecompany);
	}

	public String getHwhw_consigneecompany() {
		return this.getField(11);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(12, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(12);
	}

	public void setHw_consigneeaddress(String Hw_consigneeaddress) {
		this.setField(13, Hw_consigneeaddress);
	}

	public String getHw_consigneeaddress() {
		return this.getField(13);
	}

	public void setHwhw_signindate(String hwHw_signindate) {
		this.setField(14, hwHw_signindate);
	}

	public String getHwhw_signindate() {
		return this.getField(14);
	}

	public void setHwhw_signoutdate(String hwHw_signoutdate) {
		this.setField(15, hwHw_signoutdate);
	}

	public String getHwhw_signoutdate() {
		return this.getField(15);
	}

	public void setEeee_sname(String eeEe_sname) {
		this.setField(16, eeEe_sname);
	}

	public String getEeee_sname() {
		return this.getField(16);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
