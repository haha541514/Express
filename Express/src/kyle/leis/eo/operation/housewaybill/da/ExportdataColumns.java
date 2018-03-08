package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ExportdataColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ExportdataColumns() {
		m_astrColumns = new String[43];
	}
	
	public ExportdataColumns(String Cwchn_code_supplier, 
            String cwCw_customerewbcode, String hwHw_shippername, 
            String hwHw_consigneename, String hwHw_consigneeaddress1, 
            String hwHw_consigneeaddress2, String hwHw_consigneecity, 
            String Dt_statecode, 
            String cddtDt_statename, String hwHw_consigneepostcode, 
            String cddtDt_ename, String hwHw_consigneetelephone, 
            String cwCw_serverewbcode, String cwCo_code_customer, 
            String cwCw_customerchargeweight, String cwCw_code, 
            String hwHw_labelprintremark,
            String ciCi_currency,String totalDeclaredValue,
            String detailedGoodsDescription1,String detailedGoodsDescription2,
            String detailedGoodsDescription3,String detailedGoodsDescription4,
            String HSCode1,String HSCode2,
            String HSCode3,String HSCode4,
            String quantity1,String quantity2,
            String quantity3,String quantity4,
            String value1,String value2,
            String value3,String value4,
            String countryOfOrigin1,String countryOfOrigin2,
            String countryOfOrigin3,String countryOfOrigin4,
            String itemCode1,String itemCode2,
            String itemCode3,String itemCode4){
		m_astrColumns = new String[43];
		setCwchn_code_supplier (Cwchn_code_supplier);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setHwhw_shippername(hwHw_shippername);
		setHwhw_consigneename(hwHw_consigneename);
		setHwhw_consigneeaddress1(hwHw_consigneeaddress1);
		setHwhw_consigneeaddress2(hwHw_consigneeaddress2);
		setHwhw_consigneecity(hwHw_consigneecity);
		setDt_statecode(Dt_statecode);
		setCddtdt_statename(cddtDt_statename);
		setHwhw_consigneepostcode(hwHw_consigneepostcode);
		setCddtdt_ename(cddtDt_ename);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwco_code_customer(cwCo_code_customer);
		setCwcw_customerchargeweight(cwCw_customerchargeweight);
		setCwcw_code(cwCw_code);
		setHwhw_labelprintremark(hwHw_labelprintremark);
		setCici_currency(ciCi_currency);
		setTotalDeclaredValue(totalDeclaredValue);
		setDetailedGoodsDescription1(detailedGoodsDescription1);
		setDetailedGoodsDescription2(detailedGoodsDescription2);
		setDetailedGoodsDescription3(detailedGoodsDescription3);
		setDetailedGoodsDescription4(detailedGoodsDescription4);
		setHSCode1(HSCode1);
		setHSCode2(HSCode2);
		setHSCode3(HSCode3);
		setHSCode4(HSCode4);
		setQuantity1(quantity1);
		setQuantity2(quantity2);
		setQuantity3(quantity3);
		setQuantity4(quantity4);
		setValue1(value1);
		setValue2(value2);
		setValue3(value3);
		setValue4(value4);
		setCountryOfOrigin1(countryOfOrigin1);
		setCountryOfOrigin2(countryOfOrigin2);
		setCountryOfOrigin3(countryOfOrigin3);
		setCountryOfOrigin4(countryOfOrigin4);
		setItemCode1(itemCode1);
		setItemCode2(itemCode2);
		setItemCode3(itemCode3);
		setItemCode4(itemCode4);
	}

	public void setCwchn_code_supplier (String Cwchn_code_supplier) {
		this.setField(0, Cwchn_code_supplier);
	}

	public String getCwchn_code_supplier() {
		return this.getField(0);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(1, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(1);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(2, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(2);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(3, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(3);
	}

	public void setHwhw_consigneeaddress1(String hwHw_consigneeaddress1) {
		this.setField(4, hwHw_consigneeaddress1);
	}

	public String getHwhw_consigneeaddress1() {
		return this.getField(4);
	}

	public void setHwhw_consigneeaddress2(String hwHw_consigneeaddress2) {
		this.setField(5, hwHw_consigneeaddress2);
	}

	public String getHwhw_consigneeaddress2() {
		return this.getField(5);
	}

	public void setHwhw_consigneecity(String hwHw_consigneecity) {
		this.setField(6, hwHw_consigneecity);
	}

	public String getHwhw_consigneecity() {
		return this.getField(6);
	}
	public void setDt_statecode(String Dt_statecode) {
		this.setField(7, Dt_statecode);
	}

	public String getDt_statecode() {
		return this.getField(7);
	}

	public void setCddtdt_statename(String cddtDt_statename) {
		this.setField(8, cddtDt_statename);
	}

	public String getCddtdt_statename() {
		return this.getField(8);
	}

	public void setHwhw_consigneepostcode(String hwHw_consigneepostcode) {
		this.setField(9, hwHw_consigneepostcode);
	}

	public String getHwhw_consigneepostcode() {
		return this.getField(9);
	}

	public void setCddtdt_ename(String cddtDt_ename) {
		this.setField(10, cddtDt_ename);
	}

	public String getCddtdt_ename() {
		return this.getField(10);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(11, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(11);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(12, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(12);
	}

	public void setCwco_code_customer(String cwCo_code_customer) {
		this.setField(13, cwCo_code_customer);
	}

	public String getCwco_code_customer() {
		return this.getField(13);
	}

	public void setCwcw_customerchargeweight(String cwCw_customerchargeweight) {
		this.setField(14, cwCw_customerchargeweight);
	}

	public String getCwcw_customerchargeweight() {
		return this.getField(14);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(15, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(15);
	}

	public void setHwhw_labelprintremark(String hwHw_labelprintremark) {
		this.setField(16, hwHw_labelprintremark);
	}

	public String getHwhw_labelprintremark() {
		return this.getField(16);
	}

	
	public void setCici_currency(String ciCi_currency) {
		this.setField(17, ciCi_currency);
	}
	public String getCici_currency() {
		return this.getField(17);
	}
	public void setTotalDeclaredValue(String totalDeclaredValue) {
		this.setField(18, totalDeclaredValue);
	}

	public String getTotalDeclaredValue() {
		return this.getField(18);
	}
	public void setDetailedGoodsDescription1(String detailedGoodsDescription1) {
		this.setField(19, detailedGoodsDescription1);
	}

	public String getDetailedGoodsDescription1() {
		return this.getField(19);
	}
	public void setDetailedGoodsDescription2(String detailedGoodsDescription2) {
		this.setField(20, detailedGoodsDescription2);
	}

	public String getDetailedGoodsDescription2() {
		return this.getField(20);
	}
	public void setDetailedGoodsDescription3(String detailedGoodsDescription3) {
		this.setField(21, detailedGoodsDescription3);
	}

	public String getDetailedGoodsDescription3() {
		return this.getField(21);
	}
	public void setDetailedGoodsDescription4(String detailedGoodsDescription4) {
		this.setField(22, detailedGoodsDescription4);
	}

	public String getDetailedGoodsDescription4() {
		return this.getField(22);
	}
	public void setHSCode1(String HSCode1) {
		this.setField(23, HSCode1);
	}

	public String getHSCode1() {
		return this.getField(23);
	}
	public void setHSCode2(String HSCode2) {
		this.setField(24, HSCode2);
	}

	public String getHSCode2() {
		return this.getField(24);
	}
	public void setHSCode3(String HSCode3) {
		this.setField(25, HSCode3);
	}

	public String getHSCode3() {
		return this.getField(25);
	}
	public void setHSCode4(String HSCode4) {
		this.setField(26, HSCode4);
	}

	public String getHSCode4() {
		return this.getField(26);
	}
	public void setQuantity1(String quantity1) {
		this.setField(27, quantity1);
	}

	public String getQuantity1() {
		return this.getField(27);
	}
	public void setQuantity2(String quantity2) {
		this.setField(28, quantity2);
	}

	public String getQuantity2() {
		return this.getField(28);
	}
	public void setQuantity3(String quantity3) {
		this.setField(29, quantity3);
	}

	public String getQuantity3() {
		return this.getField(29);
	}
	public void setQuantity4(String quantity4) {
		this.setField(30, quantity4);
	}

	public String getQuantity4() {
		return this.getField(30);
	}
	public void setValue1(String value1) {
		this.setField(31,value1);
	}

	public String getValue1() {
		return this.getField(31);
	}
	public void setValue2(String value2) {
		this.setField(32,value2);
	}

	public String getValue2() {
		return this.getField(32);
	}
	public void setValue3(String value3) {
		this.setField(33,value3);
	}

	public String getValue3() {
		return this.getField(33);
	}
	public void setValue4(String value4) {
		this.setField(34,value4);
	}

	public String getValue4() {
		return this.getField(34);
	}
	public void setCountryOfOrigin1(String countryOfOrigin1) {
		this.setField(35,countryOfOrigin1);
	}

	public String getCountryOfOrigin1() {
		return this.getField(35);
	}
	public void setCountryOfOrigin2(String countryOfOrigin2) {
		this.setField(36,countryOfOrigin2);
	}

	public String getCountryOfOrigin2() {
		return this.getField(36);
	}
	public void setCountryOfOrigin3(String countryOfOrigin3) {
		this.setField(37,countryOfOrigin3);
	}

	public String getCountryOfOrigin3() {
		return this.getField(37);
	}
	public void setCountryOfOrigin4(String countryOfOrigin4) {
		this.setField(38,countryOfOrigin4);
	}

	public String getCountryOfOrigin4() {
		return this.getField(38);
	}
	public void setItemCode1(String itemCode1) {
		this.setField(39,itemCode1);
	}

	public String getItemCode1() {
		return this.getField(39);
	}
	public void setItemCode2(String itemCode2) {
		this.setField(40,itemCode2);
	}

	public String getItemCode2() {
		return this.getField(40);
	}
	public void setItemCode3(String itemCode3) {
		this.setField(41,itemCode3);
	}

	public String getItemCode3() {
		return this.getField(41);
	}
	public void setItemCode4(String itemCode4) {
		this.setField(42,itemCode4);
	}

	public String getItemCode4() {
		return this.getField(42);
	}
    public String toString() {
        return "Code Generate By Kyle";
    }		
	
}
