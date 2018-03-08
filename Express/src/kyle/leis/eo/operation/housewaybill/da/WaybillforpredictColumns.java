package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillforpredictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillforpredictColumns() {
		m_astrColumns = new String[74];
	}
	
	public WaybillforpredictColumns(String cwCw_code, 
            String cwCw_customerewbcode, String cwCw_serverewbcode, 
            String cwCw_ewbcode, String pkPk_code, 
            String pkPk_sename, String cwDt_code_signin, 
            String dtDt_hubcode, String dtDt_ename, 
            String hwHw_shippercompany, String hwHw_shippername, 
            String hwHw_shipperaddress1, String hwHw_shipperaddress2, 
            String hwHw_shipperaddress3, String hwHw_shippertelephone, 
            String hwHw_shipperpostcode, String hwHw_shipperfax, 
            String hwHw_consigneename, String hwHw_consigneecompany, 
            String dtDt_statecode, String hwHw_consigneecity, 
            String hwHw_consigneeaddress1, String hwHw_consigneeaddress2, 
            String hwHw_consigneeaddress3, String hwHw_consigneetelephone, 
            String hwHw_consigneepostcode, String hwHw_consigneefax, 
            String hwHw_buyerid, String hwHw_transactionid, 
            String hwHw_remark, String cwCw_chargeweight, 
            String cwCw_customerchargeweight, String pwPwb_createdate, 
            String cwCw_pieces, String cwsCws_code, 
            String cwsCws_name, String ihsIhs_code, 
            String ihsIhs_name, String ccoCo_code, 
            String ccoCo_labelcode, String Scocode, 
            String Scolabelcode, String Scosename, 
            String chnChn_code, String chnChn_sename, 
            String chnChn_sname, String chnChn_customerlabel, 
            String Originorderid, String Alonecustomsign, 
            String hwHw_attacheinfosign, 
            String chnChn_customlable, String pwPwb_printdate, 
            String Hw_signindate, String pwPwb_declaredate, 
            String dtDt_name, String pkPk_showserverewbcode, 
            String hwHw_consigneeaddressex, String hwHw_consigneenameex, 
            String bwbBw_labelcode, String Eraccount, 
            String chnChn_selflablecode, String Subconame, 
            String ddtDt_code, String ddtDt_hubcode,
            String hwHw_consigneecityex, String hwPat_code, 
            String hwDt_code, String hwCgk_code, 
            String hwBk_code, String hwHw_dutypaidsign,
            String hwHw_dcustomssign, String itIpt_code, 
            String ifyIb_code, String hwHw_insurancevalue){
		m_astrColumns = new String[74];
		setCwcw_code(cwCw_code);
		setCwcw_customerewbcode(cwCw_customerewbcode);
		setCwcw_serverewbcode(cwCw_serverewbcode);
		setCwcw_ewbcode(cwCw_ewbcode);
		setPkpk_code(pkPk_code);
		setPkpk_sename(pkPk_sename);
		setCwdt_code_signin(cwDt_code_signin);
		setDtdt_hubcode(dtDt_hubcode);
		setDtdt_ename(dtDt_ename);
		setHwhw_shippercompany(hwHw_shippercompany);
		setHwhw_shippername(hwHw_shippername);
		setHwhw_shipperaddress1(hwHw_shipperaddress1);
		setHwhw_shipperaddress2(hwHw_shipperaddress2);
		setHwhw_shipperaddress3(hwHw_shipperaddress3);
		setHwhw_shippertelephone(hwHw_shippertelephone);
		setHwhw_shipperpostcode(hwHw_shipperpostcode);
		setHwhw_shipperfax(hwHw_shipperfax);
		setHwhw_consigneename(hwHw_consigneename);
		setHwhw_consigneecompany(hwHw_consigneecompany);
		setDtdt_statecode(dtDt_statecode);
		setHwhw_consigneecity(hwHw_consigneecity);
		setHwhw_consigneeaddress1(hwHw_consigneeaddress1);
		setHwhw_consigneeaddress2(hwHw_consigneeaddress2);
		setHwhw_consigneeaddress3(hwHw_consigneeaddress3);
		setHwhw_consigneetelephone(hwHw_consigneetelephone);
		setHwhw_consigneepostcode(hwHw_consigneepostcode);
		setHwhw_consigneefax(hwHw_consigneefax);
		setHwhw_buyerid(hwHw_buyerid);
		setHwhw_transactionid(hwHw_transactionid);
		setHwhw_remark(hwHw_remark);
		setCwcw_chargeweight(cwCw_chargeweight);
		setCwcw_customerchargeweight(cwCw_customerchargeweight);
		setPwpwb_createdate(pwPwb_createdate);
		setCwcw_pieces(cwCw_pieces);
		setCwscws_code(cwsCws_code);
		setCwscws_name(cwsCws_name);
		setIhsihs_code(ihsIhs_code);
		setIhsihs_name(ihsIhs_name);
		setCcoco_code(ccoCo_code);
		setCcoco_labelcode(ccoCo_labelcode);
		setScocode(Scocode);
		setScolabelcode(Scolabelcode);
		setScosename(Scosename);
		setChnchn_code(chnChn_code);
		setChnchn_sename(chnChn_sename);
		setChnchn_sname(chnChn_sname);
		setChnchn_customerlabel(chnChn_customerlabel);
		setOriginorderid(Originorderid);
		setAlonecustomsign(Alonecustomsign);
		setHwhw_attacheinfosign(hwHw_attacheinfosign);
		setChnchn_customlable(chnChn_customlable);
		setPwpwb_printdate(pwPwb_printdate);
		setHw_signindate(Hw_signindate);
		setPwpwb_declaredate(pwPwb_declaredate);
		setDtdt_name(dtDt_name);
		setPkpk_showserverewbcode(pkPk_showserverewbcode);
		setHwhw_consigneeaddressex(hwHw_consigneeaddressex);
		setHwhw_consigneenameex(hwHw_consigneenameex);
		setBwbbw_labelcode(bwbBw_labelcode);
		setEraccount(Eraccount);
		setChnchn_selflablecode(chnChn_selflablecode);
		setSubconame(Subconame);
		setDdtdt_code(ddtDt_code);
		setDdtdt_hubcode(ddtDt_hubcode);
		setHwhw_consigneecityex(hwHw_consigneecityex);
		setHwpat_code(hwPat_code);
		setHwdt_code(hwDt_code);
		setHwcgk_code(hwCgk_code);
		setHwbk_code(hwBk_code);		
		setHwhw_dutypaidsign(hwHw_dutypaidsign);
		setHwhw_dcustomssign(hwHw_dcustomssign);
		setItipt_code(itIpt_code);
		setIfyib_code(ifyIb_code);
		setHwhw_insurancevalue(hwHw_insurancevalue);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setCwcw_customerewbcode(String cwCw_customerewbcode) {
		this.setField(1, cwCw_customerewbcode);
	}

	public String getCwcw_customerewbcode() {
		return this.getField(1);
	}

	public void setCwcw_serverewbcode(String cwCw_serverewbcode) {
		this.setField(2, cwCw_serverewbcode);
	}

	public String getCwcw_serverewbcode() {
		return this.getField(2);
	}

	public void setCwcw_ewbcode(String cwCw_ewbcode) {
		this.setField(3, cwCw_ewbcode);
	}

	public String getCwcw_ewbcode() {
		return this.getField(3);
	}

	public void setPkpk_code(String pkPk_code) {
		this.setField(4, pkPk_code);
	}

	public String getPkpk_code() {
		return this.getField(4);
	}

	public void setPkpk_sename(String pkPk_sename) {
		this.setField(5, pkPk_sename);
	}

	public String getPkpk_sename() {
		return this.getField(5);
	}

	public void setCwdt_code_signin(String cwDt_code_signin) {
		this.setField(6, cwDt_code_signin);
	}

	public String getCwdt_code_signin() {
		return this.getField(6);
	}

	public void setDtdt_hubcode(String dtDt_hubcode) {
		this.setField(7, dtDt_hubcode);
	}

	public String getDtdt_hubcode() {
		return this.getField(7);
	}

	public void setDtdt_ename(String dtDt_ename) {
		this.setField(8, dtDt_ename);
	}

	public String getDtdt_ename() {
		return this.getField(8);
	}

	public void setHwhw_shippercompany(String hwHw_shippercompany) {
		this.setField(9, hwHw_shippercompany);
	}

	public String getHwhw_shippercompany() {
		return this.getField(9);
	}

	public void setHwhw_shippername(String hwHw_shippername) {
		this.setField(10, hwHw_shippername);
	}

	public String getHwhw_shippername() {
		return this.getField(10);
	}

	public void setHwhw_shipperaddress1(String hwHw_shipperaddress1) {
		this.setField(11, hwHw_shipperaddress1);
	}

	public String getHwhw_shipperaddress1() {
		return this.getField(11);
	}

	public void setHwhw_shipperaddress2(String hwHw_shipperaddress2) {
		this.setField(12, hwHw_shipperaddress2);
	}

	public String getHwhw_shipperaddress2() {
		return this.getField(12);
	}

	public void setHwhw_shipperaddress3(String hwHw_shipperaddress3) {
		this.setField(13, hwHw_shipperaddress3);
	}

	public String getHwhw_shipperaddress3() {
		return this.getField(13);
	}

	public void setHwhw_shippertelephone(String hwHw_shippertelephone) {
		this.setField(14, hwHw_shippertelephone);
	}

	public String getHwhw_shippertelephone() {
		return this.getField(14);
	}

	public void setHwhw_shipperpostcode(String hwHw_shipperpostcode) {
		this.setField(15, hwHw_shipperpostcode);
	}

	public String getHwhw_shipperpostcode() {
		return this.getField(15);
	}

	public void setHwhw_shipperfax(String hwHw_shipperfax) {
		this.setField(16, hwHw_shipperfax);
	}

	public String getHwhw_shipperfax() {
		return this.getField(16);
	}

	public void setHwhw_consigneename(String hwHw_consigneename) {
		this.setField(17, hwHw_consigneename);
	}

	public String getHwhw_consigneename() {
		return this.getField(17);
	}

	public void setHwhw_consigneecompany(String hwHw_consigneecompany) {
		this.setField(18, hwHw_consigneecompany);
	}

	public String getHwhw_consigneecompany() {
		return this.getField(18);
	}

	public void setDtdt_statecode(String dtDt_statecode) {
		this.setField(19, dtDt_statecode);
	}

	public String getDtdt_statecode() {
		return this.getField(19);
	}

	public void setHwhw_consigneecity(String hwHw_consigneecity) {
		this.setField(20, hwHw_consigneecity);
	}

	public String getHwhw_consigneecity() {
		return this.getField(20);
	}

	public void setHwhw_consigneeaddress1(String hwHw_consigneeaddress1) {
		this.setField(21, hwHw_consigneeaddress1);
	}

	public String getHwhw_consigneeaddress1() {
		return this.getField(21);
	}

	public void setHwhw_consigneeaddress2(String hwHw_consigneeaddress2) {
		this.setField(22, hwHw_consigneeaddress2);
	}

	public String getHwhw_consigneeaddress2() {
		return this.getField(22);
	}

	public void setHwhw_consigneeaddress3(String hwHw_consigneeaddress3) {
		this.setField(23, hwHw_consigneeaddress3);
	}

	public String getHwhw_consigneeaddress3() {
		return this.getField(23);
	}

	public void setHwhw_consigneetelephone(String hwHw_consigneetelephone) {
		this.setField(24, hwHw_consigneetelephone);
	}

	public String getHwhw_consigneetelephone() {
		return this.getField(24);
	}

	public void setHwhw_consigneepostcode(String hwHw_consigneepostcode) {
		this.setField(25, hwHw_consigneepostcode);
	}

	public String getHwhw_consigneepostcode() {
		return this.getField(25);
	}

	public void setHwhw_consigneefax(String hwHw_consigneefax) {
		this.setField(26, hwHw_consigneefax);
	}

	public String getHwhw_consigneefax() {
		return this.getField(26);
	}

	public void setHwhw_buyerid(String hwHw_buyerid) {
		this.setField(27, hwHw_buyerid);
	}

	public String getHwhw_buyerid() {
		return this.getField(27);
	}

	public void setHwhw_transactionid(String hwHw_transactionid) {
		this.setField(28, hwHw_transactionid);
	}

	public String getHwhw_transactionid() {
		return this.getField(28);
	}

	public void setHwhw_remark(String hwHw_remark) {
		this.setField(29, hwHw_remark);
	}

	public String getHwhw_remark() {
		return this.getField(29);
	}

	public void setCwcw_chargeweight(String cwCw_chargeweight) {
		this.setField(30, cwCw_chargeweight);
	}

	public String getCwcw_chargeweight() {
		return this.getField(30);
	}

	public void setCwcw_customerchargeweight(String cwCw_customerchargeweight) {
		this.setField(31, cwCw_customerchargeweight);
	}

	public String getCwcw_customerchargeweight() {
		return this.getField(31);
	}

	public void setPwpwb_createdate(String pwPwb_createdate) {
		this.setField(32, pwPwb_createdate);
	}

	public String getPwpwb_createdate() {
		return this.getField(32);
	}

	public void setCwcw_pieces(String cwCw_pieces) {
		this.setField(33, cwCw_pieces);
	}

	public String getCwcw_pieces() {
		return this.getField(33);
	}

	public void setCwscws_code(String cwsCws_code) {
		this.setField(34, cwsCws_code);
	}

	public String getCwscws_code() {
		return this.getField(34);
	}

	public void setCwscws_name(String cwsCws_name) {
		this.setField(35, cwsCws_name);
	}

	public String getCwscws_name() {
		return this.getField(35);
	}

	public void setIhsihs_code(String ihsIhs_code) {
		this.setField(36, ihsIhs_code);
	}

	public String getIhsihs_code() {
		return this.getField(36);
	}

	public void setIhsihs_name(String ihsIhs_name) {
		this.setField(37, ihsIhs_name);
	}

	public String getIhsihs_name() {
		return this.getField(37);
	}

	public void setCcoco_code(String ccoCo_code) {
		this.setField(38, ccoCo_code);
	}

	public String getCcoco_code() {
		return this.getField(38);
	}

	public void setCcoco_labelcode(String ccoCo_labelcode) {
		this.setField(39, ccoCo_labelcode);
	}

	public String getCcoco_labelcode() {
		return this.getField(39);
	}

	public void setScocode(String Scocode) {
		this.setField(40, Scocode);
	}

	public String getScocode() {
		return this.getField(40);
	}

	public void setScolabelcode(String Scolabelcode) {
		this.setField(41, Scolabelcode);
	}

	public String getScolabelcode() {
		return this.getField(41);
	}

	public void setScosename(String Scosename) {
		this.setField(42, Scosename);
	}

	public String getScosename() {
		return this.getField(42);
	}

	public void setChnchn_code(String chnChn_code) {
		this.setField(43, chnChn_code);
	}

	public String getChnchn_code() {
		return this.getField(43);
	}

	public void setChnchn_sename(String chnChn_sename) {
		this.setField(44, chnChn_sename);
	}

	public String getChnchn_sename() {
		return this.getField(44);
	}

	public void setChnchn_sname(String chnChn_sname) {
		this.setField(45, chnChn_sname);
	}

	public String getChnchn_sname() {
		return this.getField(45);
	}

	public void setChnchn_customerlabel(String chnChn_customerlabel) {
		this.setField(46, chnChn_customerlabel);
	}

	public String getChnchn_customerlabel() {
		return this.getField(46);
	}

	public void setOriginorderid(String Originorderid) {
		this.setField(47, Originorderid);
	}

	public String getOriginorderid() {
		return this.getField(47);
	}

	public void setAlonecustomsign(String Alonecustomsign) {
		this.setField(48, Alonecustomsign);
	}

	public String getAlonecustomsign() {
		return this.getField(48);
	}

	public void setHwhw_attacheinfosign(String hwHw_attacheinfosign) {
		this.setField(49, hwHw_attacheinfosign);
	}

	public String getHwhw_attacheinfosign() {
		return this.getField(49);
	}

	public void setChnchn_customlable(String chnChn_customlable) {
		this.setField(50, chnChn_customlable);
	}

	public String getChnchn_customlable() {
		return this.getField(50);
	}

	public void setPwpwb_printdate(String pwPwb_printdate) {
		this.setField(51, pwPwb_printdate);
	}

	public String getPwpwb_printdate() {
		return this.getField(51);
	}

	public void setHw_signindate(String Hw_signindate) {
		this.setField(52, Hw_signindate);
	}

	public String getHw_signindate() {
		return this.getField(52);
	}

	public void setPwpwb_declaredate(String pwPwb_declaredate) {
		this.setField(53, pwPwb_declaredate);
	}

	public String getPwpwb_declaredate() {
		return this.getField(53);
	}

	public void setDtdt_name(String dtDt_name) {
		this.setField(54, dtDt_name);
	}

	public String getDtdt_name() {
		return this.getField(54);
	}

	public void setPkpk_showserverewbcode(String pkPk_showserverewbcode) {
		this.setField(55, pkPk_showserverewbcode);
	}

	public String getPkpk_showserverewbcode() {
		return this.getField(55);
	}

	public void setHwhw_consigneeaddressex(String hwHw_consigneeaddressex) {
		this.setField(56, hwHw_consigneeaddressex);
	}

	public String getHwhw_consigneeaddressex() {
		return this.getField(56);
	}

	public void setHwhw_consigneenameex(String hwHw_consigneenameex) {
		this.setField(57, hwHw_consigneenameex);
	}

	public String getHwhw_consigneenameex() {
		return this.getField(57);
	}

	public void setBwbbw_labelcode(String bwbBw_labelcode) {
		this.setField(58, bwbBw_labelcode);
	}

	public String getBwbbw_labelcode() {
		return this.getField(58);
	}

	public void setEraccount(String Eraccount) {
		this.setField(59, Eraccount);
	}

	public String getEraccount() {
		return this.getField(59);
	}

	public void setChnchn_selflablecode(String chnChn_selflablecode) {
		this.setField(60, chnChn_selflablecode);
	}

	public String getChnchn_selflablecode() {
		return this.getField(60);
	}

	public void setSubconame(String Subconame) {
		this.setField(61, Subconame);
	}

	public String getSubconame() {
		return this.getField(61);
	}

	public void setDdtdt_code(String ddtDt_code) {
		this.setField(62, ddtDt_code);
	}

	public String getDdtdt_code() {
		return this.getField(62);
	}

	public void setDdtdt_hubcode(String ddtDt_hubcode) {
		this.setField(63, ddtDt_hubcode);
	}

	public String getDdtdt_hubcode() {
		return this.getField(63);
	}
	
	public void setHwhw_consigneecityex(String hwHw_consigneecityex) {
		this.setField(64, hwHw_consigneecityex);
	}

	public String getHwhw_consigneecityex() {
		return this.getField(64);
	}	
	
	public void setHwpat_code(String hwPat_code) {
		this.setField(65, hwPat_code);
	}

	public String getHwpat_code() {
		return this.getField(65);
	}

	public void setHwdt_code(String hwDt_code) {
		this.setField(66, hwDt_code);
	}

	public String getHwdt_code() {
		return this.getField(66);
	}

	public void setHwcgk_code(String hwCgk_code) {
		this.setField(67, hwCgk_code);
	}

	public String getHwcgk_code() {
		return this.getField(67);
	}

	public void setHwbk_code(String hwBk_code) {
		this.setField(68, hwBk_code);
	}

	public String getHwbk_code() {
		return this.getField(68);
	}	
	
	public void setHwhw_dutypaidsign(String hwHw_dutypaidsign) {
		this.setField(69, hwHw_dutypaidsign);
	}

	public String getHwhw_dutypaidsign() {
		return this.getField(69);
	}	
	public void setHwhw_dcustomssign(String hwHw_dcustomssign) {
		this.setField(70, hwHw_dcustomssign);
	}

	public String getHwhw_dcustomssign() {
		return this.getField(70);
	}

	public void setItipt_code(String itIpt_code) {
		this.setField(71, itIpt_code);
	}

	public String getItipt_code() {
		return this.getField(71);
	}

	public void setIfyib_code(String ifyIb_code) {
		this.setField(72, ifyIb_code);
	}

	public String getIfyib_code() {
		return this.getField(72);
	}
	public void setHwhw_insurancevalue(String hwHw_insurancevalue) {
		this.setField(73, hwHw_insurancevalue);
	}

	public String getHwhw_insurancevalue() {
		return this.getField(73);
	}
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
