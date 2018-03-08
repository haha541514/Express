package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillforprintorderColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillforprintorderColumns() {
		m_astrColumns = new String[17];
	}
	
	public WaybillforprintorderColumns(String cwCw_code, 
            String hwHw_shippername, String coCo_labelcode, 
            String hwHw_shippercompany, String hwHw_shipperaddress1, 
            String hwHw_shipperaddress2, String hwHw_shipperaddress3, 
            String hwHw_consigneename, String hwHw_consigneecompany, 
            String hwHw_consigneeaddress1, String hwHw_consigneeaddress2, 
            String hwHw_consigneeaddress3, String cdtDt_name, 
            String dtDt_ename, String hwHw_consigneetelephone, 
            String cwCw_customerewbcode, String pkPk_sename){
		m_astrColumns = new String[17];
		setCwcw_code(cwCw_code);
		setHwhw_shippername(hwHw_shippername);
		setCoco_labelcode(coCo_labelcode);
		setHwhw_shippercompany(hwHw_shippercompany);
		setHwhw_shipperaddress1(hwHw_shipperaddress1);
		setHwhw_shipperaddress2(hwHw_shipperaddress2);
		setHwhw_shipperaddress3(hwHw_shipperaddress3);
		setHwhw_consigneename(hwHw_consigneename);
		setHwhw_consigneecompany(hwHw_consigneecompany);
		setHwhw_consigneeaddress1(hwHw_consigneeaddress1);
		setHwhw_consigneeaddress2(hwHw_consigneeaddress2);
		setHwhw_consigneeaddress3(hwHw_consigneeaddress3);
		setCdtdt_name(cdtDt_name);
		setCdtdt_ename(dtDt_ename);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setPkpk_sename(pkPk_sename);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(1, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(1);
	}

	public void setCoco_labelcode(String coCo_labelcode) {
		this.setField(2, coCo_labelcode);
	}

	public String getCoco_labelcode() {
		return this.getField(2);
	}

	public void setHwhw_shippercompany(String hwHw_shippercompany) {
		this.setField(3, hwHw_shippercompany);
	}

	public String getHwhw_shippercompany() {
		return this.getField(3);
	}

	public void setHwhw_shipperaddress1(String hwHw_shipperaddress1) {
		this.setField(4, hwHw_shipperaddress1);
	}

	public String getHwhw_shipperaddress1() {
		return this.getField(4);
	}

	public void setHwhw_shipperaddress2(String hwHw_shipperaddress2) {
		this.setField(5, hwHw_shipperaddress2);
	}

	public String getHwhw_shipperaddress2() {
		return this.getField(5);
	}

	public void setHwhw_shipperaddress3(String hwHw_shipperaddress3) {
		this.setField(6, hwHw_shipperaddress3);
	}

	public String getHwhw_shipperaddress3() {
		return this.getField(6);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(7, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(7);
	}

	public void setHwhw_consigneecompany(String hwHw_consigneecompany) {
		this.setField(8, hwHw_consigneecompany);
	}

	public String getHwhw_consigneecompany() {
		return this.getField(8);
	}

	public void setHwhw_consigneeaddress1(String hwHw_consigneeaddress1) {
		this.setField(9, hwHw_consigneeaddress1);
	}

	public String getHwhw_consigneeaddress1() {
		return this.getField(9);
	}

	public void setHwhw_consigneeaddress2(String hwHw_consigneeaddress2) {
		this.setField(10, hwHw_consigneeaddress2);
	}

	public String getHwhw_consigneeaddress2() {
		return this.getField(10);
	}

	public void setHwhw_consigneeaddress3(String hwHw_consigneeaddress3) {
		this.setField(11, hwHw_consigneeaddress3);
	}

	public String getHwhw_consigneeaddress3() {
		return this.getField(11);
	}

	public void setCdtdt_name(String cdtDt_name) {
		this.setField(12, cdtDt_name);
	}

	public String getCdtdt_name() {
		return this.getField(12);
	}

	public void setCdtdt_ename(String dtDt_ename) {
		this.setField(13, dtDt_ename);
	}

	public String getCdtdt_ename() {
		return this.getField(13);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(14, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(14);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(15, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(15);
	}

	public void setPkpk_sename(String pkPk_sename) {
		this.setField(16, pkPk_sename);
	}

	public String getPkpk_sename() {
		return this.getField(16);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
