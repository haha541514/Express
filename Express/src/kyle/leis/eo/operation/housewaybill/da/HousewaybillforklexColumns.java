package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillforklexColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillforklexColumns() {
		m_astrColumns = new String[45];
	}
	
	public HousewaybillforklexColumns(String cwCw_code, 
            String cwCw_grossweight, String cwCw_pieces, 
            String dbwAdd_date, String Cons1, 
            String Cons2, String Mawblabelcode, 
            String cwCw_serverewbcode, String Route, 
            String Routedest, String Origincountry, 
            String Exportcountry, String Originlocation, 
            String Shippercountry, String Shipperstate, 
            String shdtDt_ename, String Hw_shipperaddress1, 
            String Shipperaddress2, String hwHw_shipperpostcode, 
            String hwHw_shippercompany, String Shipperweb, 
            String Shipperemail, String hwHw_shippername, 
            String hwHw_shippertelephone, String Totalcargoinfo, 
            String Cargocurrency, String cddtDt_hubcode, 
            String ddtDt_hubcode, String Consigneecountry, 
            String Consigneestate, String hwHw_consigneecity, 
            String Hw_consigneeaddress1, String Consigneeaddress2, 
            String hwHw_consigneepostcode, String hwHw_consigneecompany, 
            String hwHw_consigneename, String Consigneeemail, 
            String hwHw_consigneetelephone, String Cargoinfocname, 
            String Blankmark, String Totalcargopcs, 
            String cwCw_serverchargeweight, String Chargeableweight, 
            String Weightcode, String Hscode){
		m_astrColumns = new String[45];
		setCwcw_code(cwCw_code);
		setCwcw_grossweight(cwCw_grossweight);
		setCwcw_pieces(cwCw_pieces);
		setDbwadd_date(dbwAdd_date);
		setCons1(Cons1);
		setCons2(Cons2);
		setMawblabelcode(Mawblabelcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setRoute(Route);
		setRoutedest(Routedest);
		setOrigincountry(Origincountry);
		setExportcountry(Exportcountry);
		setOriginlocation(Originlocation);
		setShippercountry(Shippercountry);
		setShipperstate(Shipperstate);
		setShdtdt_ename(shdtDt_ename);
		setHw_shipperaddress1(Hw_shipperaddress1);
		setShipperaddress2(Shipperaddress2);
		setHwhw_shipperpostcode(hwHw_shipperpostcode);
		setHwhw_shippercompany(hwHw_shippercompany);
		setShipperweb(Shipperweb);
		setShipperemail(Shipperemail);
		setHwhw_shippername(hwHw_shippername);
		setHwhw_shippertelephone(hwHw_shippertelephone);
		setTotalcargoinfo(Totalcargoinfo);
		setCargocurrency(Cargocurrency);
		setCddtdt_hubcode(cddtDt_hubcode);
		setDdtdt_hubcode(ddtDt_hubcode);
		setConsigneecountry(Consigneecountry);
		setConsigneestate(Consigneestate);
		setHwhw_consigneecity(hwHw_consigneecity);
		setHw_consigneeaddress1(Hw_consigneeaddress1);
		setConsigneeaddress2(Consigneeaddress2);
		setHwhw_consigneepostcode(hwHw_consigneepostcode);
		setHwhw_consigneecompany(hwHw_consigneecompany);
		setHwhw_consigneename(hwHw_consigneename);
		setConsigneeemail(Consigneeemail);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setCargoinfocname(Cargoinfocname);
		setBlankmark(Blankmark);
		setTotalcargopcs(Totalcargopcs);
		setCwcw_serverchargeweight(cwCw_serverchargeweight);
		setChargeableweight(Chargeableweight);
		setWeightcode(Weightcode);
		setHscode(Hscode);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcw_grossweight(String cwCw_grossweight) {
		this.setField(1, cwCw_grossweight);
	}

	public String getCwcw_grossweight() {
		return this.getField(1);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(2, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(2);
	}

	public void setDbwadd_date(String dbwAdd_date) {
		this.setField(3, dbwAdd_date);
	}

	public String getDbwadd_date() {
		return this.getField(3);
	}

	public void setCons1(String Cons1) {
		this.setField(4, Cons1);
	}

	public String getCons1() {
		return this.getField(4);
	}

	public void setCons2(String Cons2) {
		this.setField(5, Cons2);
	}

	public String getCons2() {
		return this.getField(5);
	}

	public void setMawblabelcode(String Mawblabelcode) {
		this.setField(6, Mawblabelcode);
	}

	public String getMawblabelcode() {
		return this.getField(6);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(7, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(7);
	}

	public void setRoute(String Route) {
		this.setField(8, Route);
	}

	public String getRoute() {
		return this.getField(8);
	}

	public void setRoutedest(String Routedest) {
		this.setField(9, Routedest);
	}

	public String getRoutedest() {
		return this.getField(9);
	}

	public void setOrigincountry(String Origincountry) {
		this.setField(10, Origincountry);
	}

	public String getOrigincountry() {
		return this.getField(10);
	}

	public void setExportcountry(String Exportcountry) {
		this.setField(11, Exportcountry);
	}

	public String getExportcountry() {
		return this.getField(11);
	}

	public void setOriginlocation(String Originlocation) {
		this.setField(12, Originlocation);
	}

	public String getOriginlocation() {
		return this.getField(12);
	}

	public void setShippercountry(String Shippercountry) {
		this.setField(13, Shippercountry);
	}

	public String getShippercountry() {
		return this.getField(13);
	}

	public void setShipperstate(String Shipperstate) {
		this.setField(14, Shipperstate);
	}

	public String getShipperstate() {
		return this.getField(14);
	}

	public void setShdtdt_ename(String shdtDt_ename) {
		this.setField(15, shdtDt_ename);
	}

	public String getShdtdt_ename() {
		return this.getField(15);
	}

	public void setHw_shipperaddress1(String Hw_shipperaddress1) {
		this.setField(16, Hw_shipperaddress1);
	}

	public String getHw_shipperaddress1() {
		return this.getField(16);
	}

	public void setShipperaddress2(String Shipperaddress2) {
		this.setField(17, Shipperaddress2);
	}

	public String getShipperaddress2() {
		return this.getField(17);
	}

	public void setHwhw_shipperpostcode(String hwHw_shipperpostcode) {
		this.setField(18, hwHw_shipperpostcode);
	}

	public String getHwhw_shipperpostcode() {
		return this.getField(18);
	}

	public void setHwhw_shippercompany(String hwHw_shippercompany) {
		this.setField(19, hwHw_shippercompany);
	}

	public String getHwhw_shippercompany() {
		return this.getField(19);
	}

	public void setShipperweb(String Shipperweb) {
		this.setField(20, Shipperweb);
	}

	public String getShipperweb() {
		return this.getField(20);
	}

	public void setShipperemail(String Shipperemail) {
		this.setField(21, Shipperemail);
	}

	public String getShipperemail() {
		return this.getField(21);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(22, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(22);
	}

	public void setHwhw_shippertelephone(String hwHw_shippertelephone) {
		this.setField(23, hwHw_shippertelephone);
	}

	public String getHwhw_shippertelephone() {
		return this.getField(23);
	}

	public void setTotalcargoinfo(String Totalcargoinfo) {
		this.setField(24, Totalcargoinfo);
	}

	public String getTotalcargoinfo() {
		return this.getField(24);
	}

	public void setCargocurrency(String Cargocurrency) {
		this.setField(25, Cargocurrency);
	}

	public String getCargocurrency() {
		return this.getField(25);
	}

	public void setCddtdt_hubcode(String cddtDt_hubcode) {
		this.setField(26, cddtDt_hubcode);
	}

	public String getCddtdt_hubcode() {
		return this.getField(26);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(27, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(27);
	}

	public void setConsigneecountry(String Consigneecountry) {
		this.setField(28, Consigneecountry);
	}

	public String getConsigneecountry() {
		return this.getField(28);
	}

	public void setConsigneestate(String Consigneestate) {
		this.setField(29, Consigneestate);
	}

	public String getConsigneestate() {
		return this.getField(29);
	}

	public void setHwhw_consigneecity(String hwHw_consigneecity) {
		this.setField(30, hwHw_consigneecity);
	}

	public String getHwhw_consigneecity() {
		return this.getField(30);
	}

	public void setHw_consigneeaddress1(String Hw_consigneeaddress1) {
		this.setField(31, Hw_consigneeaddress1);
	}

	public String getHw_consigneeaddress1() {
		return this.getField(31);
	}

	public void setConsigneeaddress2(String Consigneeaddress2) {
		this.setField(32, Consigneeaddress2);
	}

	public String getConsigneeaddress2() {
		return this.getField(32);
	}

	public void setHwhw_consigneepostcode(String hwHw_consigneepostcode) {
		this.setField(33, hwHw_consigneepostcode);
	}

	public String getHwhw_consigneepostcode() {
		return this.getField(33);
	}

	public void setHwhw_consigneecompany(String hwHw_consigneecompany) {
		this.setField(34, hwHw_consigneecompany);
	}

	public String getHwhw_consigneecompany() {
		return this.getField(34);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(35, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(35);
	}

	public void setConsigneeemail(String Consigneeemail) {
		this.setField(36, Consigneeemail);
	}

	public String getConsigneeemail() {
		return this.getField(36);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(37, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(37);
	}

	public void setCargoinfocname(String Cargoinfocname) {
		this.setField(38, Cargoinfocname);
	}

	public String getCargoinfocname() {
		return this.getField(38);
	}

	public void setBlankmark(String Blankmark) {
		this.setField(39, Blankmark);
	}

	public String getBlankmark() {
		return this.getField(39);
	}

	public void setTotalcargopcs(String Totalcargopcs) {
		this.setField(40, Totalcargopcs);
	}

	public String getTotalcargopcs() {
		return this.getField(40);
	}

	public void setCwcw_serverchargeweight(String cwCw_serverchargeweight) {
		this.setField(41, cwCw_serverchargeweight);
	}

	public String getCwcw_serverchargeweight() {
		return this.getField(41);
	}

	public void setChargeableweight(String Chargeableweight) {
		this.setField(42, Chargeableweight);
	}

	public String getChargeableweight() {
		return this.getField(42);
	}

	public void setWeightcode(String Weightcode) {
		this.setField(43, Weightcode);
	}

	public String getWeightcode() {
		return this.getField(43);
	}

	public void setHscode(String Hscode) {
		this.setField(44, Hscode);
	}

	public String getHscode() {
		return this.getField(44);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
