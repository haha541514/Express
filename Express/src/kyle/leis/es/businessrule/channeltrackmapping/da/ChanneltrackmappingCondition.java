package kyle.leis.es.businessrule.channeltrackmapping.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ChanneltrackmappingCondition extends GeneralCondition {
	public ChanneltrackmappingCondition() {
		m_astrConditions = new String[8];
	}	
	
	public void setCtmsourcetrackdesc(String ctmSourcetrackdesc) {
		this.setField(0, ctmSourcetrackdesc);
	}

	public String getCtmsourcetrackdesc() {
		return this.getField(0);
	}

	public void setCopid(String copid) {
		this.setField(1, copid);
	}

	public String getCopid() {
		return this.getField(1);
	}

	public void setMopid(String mopid) {
		this.setField(2, mopid);
	}

	public String getMopid() {
		return this.getField(2);
	}

	public void setCtmid(String ctmid) {
		this.setField(3, ctmid);
	}

	public String getCtmid() {
		return this.getField(3);
	}

	public void setStartdate(String startdate) {
		this.setField(4, startdate);
	}

	public String getStartdate() {
		return this.getField(4);
	}

	public void setEnddate(String enddate) {
		this.setField(5, enddate);
	}

	public String getEnddate() {
		return this.getField(5);
	}

	public void setIsnullwbtscode(String isNullWbtscode) {
		this.setField(6, isNullWbtscode);
	}

	public String getIsnullwbtscode() {
		return this.getField(6);
	}

}
