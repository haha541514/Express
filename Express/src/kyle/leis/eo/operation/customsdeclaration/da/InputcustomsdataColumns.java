package kyle.leis.eo.operation.customsdeclaration.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class InputcustomsdataColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public InputcustomsdataColumns() {
		m_astrColumns = new String[25];
	}
	
	public InputcustomsdataColumns(String cwCw_serverewbcode, 
            String Baglabelcode, String hwHw_consigneename, 
            String Hw_consigneeaddress, String Hw_shipperaddress, 
            String Shippercountry, String shdtDt_ename, 
            String cddtDt_name, String Cargoinfo, 
            String Cargotype, String Cargono, 
            String Origincountry, String cwCw_pieces, 
            String cwCw_chargeweight, String Totalcargopcs, 
            String Cargounit, String Unitcargoinfo, 
            String Totalcargoinfo, String Currency, 
            String Customstype, String Commercetype, 
            String Levytype, String Company, 
            String Companytype, String Companyname){
		m_astrColumns = new String[25];
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setBaglabelcode(Baglabelcode);
		setHwhw_consigneename(hwHw_consigneename);
		setHw_consigneeaddress(Hw_consigneeaddress);
		setHw_shipperaddress(Hw_shipperaddress);
		setShippercountry(Shippercountry);
		setShdtdt_ename(shdtDt_ename);
		setCddtdt_name(cddtDt_name);
		setCargoinfo(Cargoinfo);
		setCargotype(Cargotype);
		setCargono(Cargono);
		setOrigincountry(Origincountry);
		setCwcw_pieces(cwCw_pieces);
		setCwcw_chargeweight(cwCw_chargeweight);
		setTotalcargopcs(Totalcargopcs);
		setCargounit(Cargounit);
		setUnitcargoinfo(Unitcargoinfo);
		setTotalcargoinfo(Totalcargoinfo);
		setCurrency(Currency);
		setCustomstype(Customstype);
		setCommercetype(Commercetype);
		setLevytype(Levytype);
		setCompany(Company);
		setCompanytype(Companytype);
		setCompanyname(Companyname);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(0, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(0);
	}

	public void setBaglabelcode(String Baglabelcode) {
		this.setField(1, Baglabelcode);
	}

	public String getBaglabelcode() {
		return this.getField(1);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(2, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(2);
	}

	public void setHw_consigneeaddress(String Hw_consigneeaddress) {
		this.setField(3, Hw_consigneeaddress);
	}

	public String getHw_consigneeaddress() {
		return this.getField(3);
	}

	public void setHw_shipperaddress(String Hw_shipperaddress) {
		this.setField(4, Hw_shipperaddress);
	}

	public String getHw_shipperaddress() {
		return this.getField(4);
	}

	public void setShippercountry(String Shippercountry) {
		this.setField(5, Shippercountry);
	}

	public String getShippercountry() {
		return this.getField(5);
	}

	public void setShdtdt_ename(String shdtDt_ename) {
		this.setField(6, shdtDt_ename);
	}

	public String getShdtdt_ename() {
		return this.getField(6);
	}

	public void setCddtdt_name(String cddtDt_name) {
		this.setField(7, cddtDt_name);
	}

	public String getCddtdt_name() {
		return this.getField(7);
	}

	public void setCargoinfo(String Cargoinfo) {
		this.setField(8, Cargoinfo);
	}

	public String getCargoinfo() {
		return this.getField(8);
	}

	public void setCargotype(String Cargotype) {
		this.setField(9, Cargotype);
	}

	public String getCargotype() {
		return this.getField(9);
	}

	public void setCargono(String Cargono) {
		this.setField(10, Cargono);
	}

	public String getCargono() {
		return this.getField(10);
	}

	public void setOrigincountry(String Origincountry) {
		this.setField(11, Origincountry);
	}

	public String getOrigincountry() {
		return this.getField(11);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(12, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(12);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(13, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(13);
	}

	public void setTotalcargopcs(String Totalcargopcs) {
		this.setField(14, Totalcargopcs);
	}

	public String getTotalcargopcs() {
		return this.getField(14);
	}

	public void setCargounit(String Cargounit) {
		this.setField(15, Cargounit);
	}

	public String getCargounit() {
		return this.getField(15);
	}

	public void setUnitcargoinfo(String Unitcargoinfo) {
		this.setField(16, Unitcargoinfo);
	}

	public String getUnitcargoinfo() {
		return this.getField(16);
	}

	public void setTotalcargoinfo(String Totalcargoinfo) {
		this.setField(17, Totalcargoinfo);
	}

	public String getTotalcargoinfo() {
		return this.getField(17);
	}

	public void setCurrency(String Currency) {
		this.setField(18, Currency);
	}

	public String getCurrency() {
		return this.getField(18);
	}

	public void setCustomstype(String Customstype) {
		this.setField(19, Customstype);
	}

	public String getCustomstype() {
		return this.getField(19);
	}

	public void setCommercetype(String Commercetype) {
		this.setField(20, Commercetype);
	}

	public String getCommercetype() {
		return this.getField(20);
	}

	public void setLevytype(String Levytype) {
		this.setField(21, Levytype);
	}

	public String getLevytype() {
		return this.getField(21);
	}

	public void setCompany(String Company) {
		this.setField(22, Company);
	}

	public String getCompany() {
		return this.getField(22);
	}

	public void setCompanytype(String Companytype) {
		this.setField(23, Companytype);
	}

	public String getCompanytype() {
		return this.getField(23);
	}

	public void setCompanyname(String Companyname) {
		this.setField(24, Companyname);
	}

	public String getCompanyname() {
		return this.getField(24);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
