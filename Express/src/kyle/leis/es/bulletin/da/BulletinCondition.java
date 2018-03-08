package kyle.leis.es.bulletin.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class BulletinCondition extends GeneralCondition {
	public BulletinCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setBlheading(String blHeading) {
		this.setField(0, blHeading);
	}

	public String getBlheading() {
		return this.getField(0);
	}

	public void setBlcontentindex(String blContentindex) {
		this.setField(1, blContentindex);
	}

	public String getBlcontentindex() {
		return this.getField(1);
	}

	public void setStartvaliddate1(String StartValiddate1) {
		this.setField(2, StartValiddate1);
	}

	public String getStartvaliddate1() {
		return this.getField(2);
	}

	public void setEndvaliddate(String EndValiddate) {
		this.setField(3, EndValiddate);
	}

	public String getEndvaliddate() {
		return this.getField(3);
	}

	public void setOpid(String opId) {
		this.setField(4, opId);
	}

	public String getOpid() {
		return this.getField(4);
	}

	public void setBkcode(String bkCode) {
		this.setField(5, bkCode);
	}

	public String getBkcode() {
		return this.getField(5);
	}

	public void setBlcode(String blCode) {
		this.setField(6, blCode);
	}

	public String getBlcode() {
		return this.getField(6);
	}

	public void setBlid(String blId) {
		this.setField(7, blId);
	}

	public String getBlid() {
		return this.getField(7);
	}

}
