package kyle.leis.es.company.channel.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChannelCondition extends GeneralCondition {
	public ChannelCondition() {
		m_astrConditions = new String[19];
	}	
	
	public void setChncode(String chnCode) {
		this.setField(0, chnCode);
	}

	public String getChncode() {
		return this.getField(0);
	}

	public void setCstcode(String cstCode) {
		this.setField(1, cstCode);
	}

	public String getCstcode() {
		return this.getField(1);
	}

	public void setChnname(String chnName) {
		this.setField(2, chnName);
	}

	public String getChnname() {
		return this.getField(2);
	}

	public void setChnsname(String chnSname) {
		this.setField(3, chnSname);
	}

	public String getChnsname() {
		return this.getField(3);
	}

	public void setCscode(String csCode) {
		this.setField(4, csCode);
	}

	public String getCscode() {
		return this.getField(4);
	}

	public void setEecode(String eeCode) {
		this.setField(5, eeCode);
	}

	public String getEecode() {
		return this.getField(5);
	}

	public void setCocode(String coCode) {
		this.setField(6, coCode);
	}

	public String getCocode() {
		return this.getField(6);
	}

	public void setOpidcreator(String opidCreator) {
		this.setField(7, opidCreator);
	}

	public String getOpidcreator() {
		return this.getField(7);
	}

	public void setOpidmodifier(String opidModifier) {
		this.setField(8, opidModifier);
	}

	public String getOpidmodifier() {
		return this.getField(8);
	}

	public void setStartcreatedate(String StartCreateDate) {
		this.setField(9, StartCreateDate);
	}

	public String getStartcreatedate() {
		return this.getField(9);
	}

	public void setEndcreatedate(String EndCreateDate) {
		this.setField(10, EndCreateDate);
	}

	public String getEndcreatedate() {
		return this.getField(10);
	}

	public void setStartmodifydate(String StartModifyDate) {
		this.setField(11, StartModifyDate);
	}

	public String getStartmodifydate() {
		return this.getField(11);
	}

	public void setEndmodifydate(String EndModifyDate) {
		this.setField(12, EndModifyDate);
	}

	public String getEndmodifydate() {
		return this.getField(12);
	}

	public void setChnename(String chnEname) {
		this.setField(13, chnEname);
	}

	public String getChnename() {
		return this.getField(13);
	}

	public void setChnsename(String chnSename) {
		this.setField(14, chnSename);
	}

	public String getChnsename() {
		return this.getField(14);
	}

	public void setSsgcode(String ssgCode) {
		this.setField(15, ssgCode);
	}

	public String getSsgcode() {
		return this.getField(15);
	}

	public void setSbckcode(String sbckCode) {
		this.setField(16, sbckCode);
	}

	public String getSbckcode() {
		return this.getField(16);
	}

	public void setMbckcode(String mbckCode) {
		this.setField(17, mbckCode);
	}

	public String getMbckcode() {
		return this.getField(17);
	}

}
