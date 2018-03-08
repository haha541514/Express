package kyle.leis.eo.operation.customsdeclaration.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class AirportcustomsdataColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AirportcustomsdataColumns() {
		m_astrColumns = new String[19];
	}
	
	public AirportcustomsdataColumns(String cwCw_serverewbcode, 
            String Customstype, String Cargoinfo, 
            String Cargotype, String Cargono, 
            String cwCw_pieces, String Totalcargopcs, 
            String Unit, String cwCw_chargeweight, 
            String Totalcargoinfo, String Currency, 
            String Shippercountry, String Origincountry, 
            String hwHw_consigneename, String Hw_consigneeaddress, 
            String hwHw_consigneetelephone, String hwHw_shippername, 
            String Hw_shipperaddress, String hwHw_shippertelephone){
		m_astrColumns = new String[19];
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCustomstype(Customstype);
		setCargoinfo(Cargoinfo);
		setCargotype(Cargotype);
		setCargono(Cargono);
		setCwcw_pieces(cwCw_pieces);
		setTotalcargopcs(Totalcargopcs);
		setUnit(Unit);
		setCwcw_chargeweight(cwCw_chargeweight);
		setTotalcargoinfo(Totalcargoinfo);
		setCurrency(Currency);
		setShippercountry(Shippercountry);
		setOrigincountry(Origincountry);
		setHwhw_consigneename(hwHw_consigneename);
		setHw_consigneeaddress(Hw_consigneeaddress);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setHwhw_shippername(hwHw_shippername);
		setHw_shipperaddress(Hw_shipperaddress);
		setHwhw_shippertelephone(hwHw_shippertelephone);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(0, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(0);
	}

	public void setCustomstype(String Customstype) {
		this.setField(1, Customstype);
	}

	public String getCustomstype() {
		return this.getField(1);
	}

	public void setCargoinfo(String Cargoinfo) {
		this.setField(2, Cargoinfo);
	}

	public String getCargoinfo() {
		return this.getField(2);
	}

	public void setCargotype(String Cargotype) {
		this.setField(3, Cargotype);
	}

	public String getCargotype() {
		return this.getField(3);
	}

	public void setCargono(String Cargono) {
		this.setField(4, Cargono);
	}

	public String getCargono() {
		return this.getField(4);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(5, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(5);
	}

	public void setTotalcargopcs(String Totalcargopcs) {
		this.setField(6, Totalcargopcs);
	}

	public String getTotalcargopcs() {
		return this.getField(6);
	}

	public void setUnit(String Unit) {
		this.setField(7, Unit);
	}

	public String getUnit() {
		return this.getField(7);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(8, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(8);
	}

	public void setTotalcargoinfo(String Totalcargoinfo) {
		this.setField(9, Totalcargoinfo);
	}

	public String getTotalcargoinfo() {
		return this.getField(9);
	}

	public void setCurrency(String Currency) {
		this.setField(10, Currency);
	}

	public String getCurrency() {
		return this.getField(10);
	}

	public void setShippercountry(String Shippercountry) {
		this.setField(11, Shippercountry);
	}

	public String getShippercountry() {
		return this.getField(11);
	}

	public void setOrigincountry(String Origincountry) {
		this.setField(12, Origincountry);
	}

	public String getOrigincountry() {
		return this.getField(12);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(13, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(13);
	}

	public void setHw_consigneeaddress(String Hw_consigneeaddress) {
		this.setField(14, Hw_consigneeaddress);
	}

	public String getHw_consigneeaddress() {
		return this.getField(14);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(15, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(15);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(16, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(16);
	}

	public void setHw_shipperaddress(String Hw_shipperaddress) {
		this.setField(17, Hw_shipperaddress);
	}

	public String getHw_shipperaddress() {
		return this.getField(17);
	}

	public void setHwhw_shippertelephone(String hwHw_shippertelephone) {
		this.setField(18, hwHw_shippertelephone);
	}

	public String getHwhw_shippertelephone() {
		return this.getField(18);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
