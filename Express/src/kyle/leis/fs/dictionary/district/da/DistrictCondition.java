package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class DistrictCondition extends GeneralCondition {
	public DistrictCondition() {
		m_astrConditions = new String[24];
	}	
	
	public void setDtcode(String dtCode) {
		this.setField(0, dtCode);
	}

	public String getDtcode() {
		return this.getField(0);
	}

	public void setDthubcode(String dtHubcode) {
		this.setField(1, dtHubcode);
	}

	public String getDthubcode() {
		return this.getField(1);
	}

	public void setDtname(String dtName) {
		this.setField(2, dtName);
	}

	public String getDtname() {
		return this.getField(2);
	}

	public void setDtename(String dtEname) {
		this.setField(3, dtEname);
	}

	public String getDtename() {
		return this.getField(3);
	}

	public void setDtstatecode(String dtStatecode) {
		this.setField(4, dtStatecode);
	}

	public String getDtstatecode() {
		return this.getField(4);
	}

	public void setDtstatename(String dtStatename) {
		this.setField(5, dtStatename);
	}

	public String getDtstatename() {
		return this.getField(5);
	}

	public void setDtgrade(String dtGrade) {
		this.setField(6, dtGrade);
	}

	public String getDtgrade() {
		return this.getField(6);
	}

	public void setDtstartpostcode(String dtStartpostcode) {
		this.setField(7, dtStartpostcode);
	}

	public String getDtstartpostcode() {
		return this.getField(7);
	}

	public void setDtendpostcode(String dtEndpostcode) {
		this.setField(8, dtEndpostcode);
	}

	public String getDtendpostcode() {
		return this.getField(8);
	}

	public void setDtopcodecreator(String dtOpCodeCreator) {
		this.setField(9, dtOpCodeCreator);
	}

	public String getDtopcodecreator() {
		return this.getField(9);
	}

	public void setStartcreatedate(String StartCreatedate) {
		this.setField(10, StartCreatedate);
	}

	public String getStartcreatedate() {
		return this.getField(10);
	}

	public void setEndcreatedate(String EndCreatedate) {
		this.setField(11, EndCreatedate);
	}

	public String getEndcreatedate() {
		return this.getField(11);
	}

	public void setDtopcodemodifier(String dtOpCodeModifier) {
		this.setField(12, dtOpCodeModifier);
	}

	public String getDtopcodemodifier() {
		return this.getField(12);
	}

	public void setStartmodifydate(String StartModifydate) {
		this.setField(13, StartModifydate);
	}

	public String getStartmodifydate() {
		return this.getField(13);
	}

	public void setEndmodifydate(String EndModifydate) {
		this.setField(14, EndModifydate);
	}

	public String getEndmodifydate() {
		return this.getField(14);
	}

	public void setDtremark(String dtRemark) {
		this.setField(15, dtRemark);
	}

	public String getDtremark() {
		return this.getField(15);
	}

	public void setDtstartcitysign(String dtStartcitysign) {
		this.setField(16, dtStartcitysign);
	}

	public String getDtstartcitysign() {
		return this.getField(16);
	}

	public void setDtelevatedrisksign(String dtElevatedrisksign) {
		this.setField(17, dtElevatedrisksign);
	}

	public String getDtelevatedrisksign() {
		return this.getField(17);
	}

	public void setDtrestrictedsign(String dtRestrictedsign) {
		this.setField(18, dtRestrictedsign);
	}

	public String getDtrestrictedsign() {
		return this.getField(18);
	}

	public void setCountcode(String countCode) {
		this.setField(19, countCode);
	}

	public String getCountcode() {
		return this.getField(19);
	}

	public void setDkcode(String dkCode) {
		this.setField(20, dkCode);
	}

	public String getDkcode() {
		return this.getField(20);
	}

	public void setDkname(String dkName) {
		this.setField(21, dkName);
	}

	public String getDkname() {
		return this.getField(21);
	}

	public void setDkename(String dkEname) {
		this.setField(22, dkEname);
	}

	public String getDkename() {
		return this.getField(22);
	}

}
