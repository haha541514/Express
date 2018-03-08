package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WaybillforpredictCondition extends GeneralCondition {
	public WaybillforpredictCondition() {
		m_astrConditions = new String[34];
	}	
	
	public void setBwblabelcode(String bwblabelcode) {
		this.setField(0, bwblabelcode);
	}

	public String getBwblabelcode() {
		return this.getField(0);
	}

	public void setNotcwscode(String notcwscode) {
		this.setField(1, notcwscode);
	}

	public String getNotcwscode() {
		return this.getField(1);
	}

	public void setIncwscode(String incwscode) {
		this.setField(2, incwscode);
	}

	public String getIncwscode() {
		return this.getField(2);
	}

	public void setLikehwconsigneename(String likehwconsigneename) {
		this.setField(3, likehwconsigneename);
	}

	public String getLikehwconsigneename() {
		return this.getField(3);
	}

	public void setLikehwconsigneecompany(String likehwconsigneecompany) {
		this.setField(4, likehwconsigneecompany);
	}

	public String getLikehwconsigneecompany() {
		return this.getField(4);
	}

	public void setHwconsigneename(String hwconsigneename) {
		this.setField(5, hwconsigneename);
	}

	public String getHwconsigneename() {
		return this.getField(5);
	}

	public void setHwconsigneecompany(String hwconsigneecompany) {
		this.setField(6, hwconsigneecompany);
	}

	public String getHwconsigneecompany() {
		return this.getField(6);
	}

	public void setHwbuyerid(String hwbuyerid) {
		this.setField(7, hwbuyerid);
	}

	public String getHwbuyerid() {
		return this.getField(7);
	}

	public void setHwtransactionid(String hwtransactionid) {
		this.setField(8, hwtransactionid);
	}

	public String getHwtransactionid() {
		return this.getField(8);
	}

	public void setLikehwconsigneeaddress(String likehwconsigneeaddress) {
		this.setField(9, likehwconsigneeaddress);
	}

	public String getLikehwconsigneeaddress() {
		return this.getField(9);
	}

	public void setPkpkcode(String pkpkcode) {
		this.setField(10, pkpkcode);
	}

	public String getPkpkcode() {
		return this.getField(10);
	}

	public void setCwcustomerewbcode(String cwcustomerewbcode) {
		this.setField(11, cwcustomerewbcode);
	}

	public String getCwcustomerewbcode() {
		return this.getField(11);
	}

	public void setCwewbcode(String cwewbcode) {
		this.setField(12, cwewbcode);
	}

	public String getCwewbcode() {
		return this.getField(12);
	}

	public void setCwserverewbcode(String cwserverewbcode) {
		this.setField(13, cwserverewbcode);
	}

	public String getCwserverewbcode() {
		return this.getField(13);
	}

	public void setCocodecustomer(String cocodecustomer) {
		this.setField(14, cocodecustomer);
	}

	public String getCocodecustomer() {
		return this.getField(14);
	}

	public void setDtcodesignin(String dtcodesignin) {
		this.setField(15, dtcodesignin);
	}

	public String getDtcodesignin() {
		return this.getField(15);
	}

	public void setCwcwscode(String cwcwscode) {
		this.setField(16, cwcwscode);
	}

	public String getCwcwscode() {
		return this.getField(16);
	}

	public void setCwcode(String cwcode) {
		this.setField(17, cwcode);
	}

	public String getCwcode() {
		return this.getField(17);
	}

	public void setIncwcode(String incwcode) {
		this.setField(18, incwcode);
	}

	public String getIncwcode() {
		return this.getField(18);
	}

	public void setStartcreatedate(String startcreatedate) {
		this.setField(19, startcreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(19);
	}

	public void setEndcreatedate(String endcreatedate) {
		this.setField(20, endcreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(20);
	}

	public void setExistsorderid(String existsorderid) {
		this.setField(21, existsorderid);
	}

	public String getExistsorderid() {
		return this.getField(21);
	}

	public void setStartlbprintdate(String startlbprintdate) {
		this.setField(22, startlbprintdate);
	}

	public String getStartlbprintdate() {
		return this.getField(22);
	}

	public void setEndlbprintdate(String endlbprintdate) {
		this.setField(23, endlbprintdate);
	}

	public String getEndlbprintdate() {
		return this.getField(23);
	}

	public void setStartsignindate(String startsignindate) {
		this.setField(24, startsignindate);
	}

	public String getStartsignindate() {
		return this.getField(24);
	}

	public void setEndsignindate(String endsignindate) {
		this.setField(25, endsignindate);
	}

	public String getEndsignindate() {
		return this.getField(25);
	}

	public void setStartdeclaredate(String startDeclaredate) {
		this.setField(26, startDeclaredate);
	}

	public String getStartdeclaredate() {
		return this.getField(26);
	}

	public void setEnddeclaredate(String endDeclaredate) {
		this.setField(27, endDeclaredate);
	}

	public String getEnddeclaredate() {
		return this.getField(27);
	}

	public void setExistscwcode(String existscwcode) {
		this.setField(28, existscwcode);
	}

	public String getExistscwcode() {
		return this.getField(28);
	}

	public void setCiciattacheinfo(String ciciattacheinfo) {
		this.setField(29, ciciattacheinfo);
	}

	public String getCiciattacheinfo() {
		return this.getField(29);
	}

	public void setHwattacheinfosign(String hwattacheinfosign) {
		this.setField(30, hwattacheinfosign);
	}

	public String getHwattacheinfosign() {
		return this.getField(30);
	}

	public void setColabelcode(String colabelcode) {
		this.setField(31, colabelcode);
	}

	public String getColabelcode() {
		return this.getField(31);
	}

	public void setLikecocode(String likecocode) {
		this.setField(32, likecocode);
	}

	public String getLikecocode() {
		return this.getField(32);
	}

}
