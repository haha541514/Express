package kyle.leis.eo.operation.customsdeclaration.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomsreportColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomsreportColumns() {
		m_astrColumns = new String[14];
	}
	
	public CustomsreportColumns(String cdCd_labelcode, 
            String cdCd_name, String cdCd_ename, 
            String cdCd_totalprice, String cdCd_grossweight, 
            String cdCd_pieces, String hwHw_consigneecompany, 
            String hwHw_consigneetelephone, String cwCw_ewbcode, 
            String cwCw_serverewbcode, String hwHw_signoutdate, 
            String cdCd_amount, String cdCd_unitname, 
            String cdCd_goodslabelcode){
		m_astrColumns = new String[14];
		setCdcd_labelcode(cdCd_labelcode);
		setCdcd_name(cdCd_name);
		setCdcd_ename(cdCd_ename);
		setCdcd_totalprice(cdCd_totalprice);
		setCdcd_grossweight(cdCd_grossweight);
		setCdcd_pieces(cdCd_pieces);
		setHwhw_consigneecompany(hwHw_consigneecompany);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setCwcw_ewbcode(cwCw_ewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setHwhw_signoutdate(hwHw_signoutdate);
		setCdcd_amount(cdCd_amount);
		setCdcd_unitname(cdCd_unitname);
		setCdcd_goodslabelcode(cdCd_goodslabelcode);
	}

	public void setCdcd_labelcode(String cdCd_labelcode) {
		this.setField(0, cdCd_labelcode);
	}

	public String getCdcd_labelcode() {
		return this.getField(0);
	}

	public void setCdcd_name(String cdCd_name) {
		this.setField(1, cdCd_name);
	}

	public String getCdcd_name() {
		return this.getField(1);
	}

	public void setCdcd_ename(String cdCd_ename) {
		this.setField(2, cdCd_ename);
	}

	public String getCdcd_ename() {
		return this.getField(2);
	}

	public void setCdcd_totalprice(String cdCd_totalprice) {
		this.setField(3, cdCd_totalprice);
	}

	public String getCdcd_totalprice() {
		return this.getField(3);
	}

	public void setCdcd_grossweight(String cdCd_grossweight) {
		this.setField(4, cdCd_grossweight);
	}

	public String getCdcd_grossweight() {
		return this.getField(4);
	}

	public void setCdcd_pieces(String cdCd_pieces) {
		this.setField(5, cdCd_pieces);
	}

	public String getCdcd_pieces() {
		return this.getField(5);
	}

	public void setHwhw_consigneecompany(String hwHw_consigneecompany) {
		this.setField(6, hwHw_consigneecompany);
	}

	public String getHwhw_consigneecompany() {
		return this.getField(6);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(7, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(7);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(8, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(8);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(9, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(9);
	}

	public void setHwhw_signoutdate(String hwHw_signoutdate) {
		this.setField(10, hwHw_signoutdate);
	}

	public String getHwhw_signoutdate() {
		return this.getField(10);
	}

	public void setCdcd_amount(String cdCd_amount) {
		this.setField(11, cdCd_amount);
	}

	public String getCdcd_amount() {
		return this.getField(11);
	}

	public void setCdcd_unitname(String cdCd_unitname) {
		this.setField(12, cdCd_unitname);
	}

	public String getCdcd_unitname() {
		return this.getField(12);
	}

	public void setCdcd_goodslabelcode(String cdCd_goodslabelcode) {
		this.setField(13, cdCd_goodslabelcode);
	}

	public String getCdcd_goodslabelcode() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
