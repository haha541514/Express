package kyle.leis.ds.report.finance.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class FeegroupbyonylcoCondition extends GeneralCondition {
	public FeegroupbyonylcoCondition() {
		m_astrConditions = new String[15];
	}	
	
	public void setStartadddate(String startadddate) {
		this.setField(0, startadddate);
	}

	public String getStartadddate() {
		return this.getField(0);
	}

	public void setEndadddate(String Endadddate) {
		this.setField(1, Endadddate);
	}

	public String getEndadddate() {
		return this.getField(1);
	}

	public void setPmcode(String pmcode) {
		this.setField(2, pmcode);
	}

	public String getPmcode() {
		return this.getField(2);
	}

	public void setCtcode(String ctcode) {
		this.setField(3, ctcode);
	}

	public String getCtcode() {
		return this.getField(3);
	}

	public void setCocode(String cocode) {
		this.setField(4, cocode);
	}

	public String getCocode() {
		return this.getField(4);
	}

	public void setSpcode(String spcode) {
		this.setField(5, spcode);
	}

	public String getSpcode() {
		return this.getField(5);
	}

	public void setPkcode(String pkcode) {
		this.setField(6, pkcode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

	public void setChncode(String chncode) {
		this.setField(7, chncode);
	}

	public String getChncode() {
		return this.getField(7);
	}

	public void setCmopidsale(String cmOpidsale) {
		this.setField(8, cmOpidsale);
	}

	public String getCmopidsale() {
		return this.getField(8);
	}

	public void setBegincarryoversign(String BeginCarryoversign) {
		this.setField(9, BeginCarryoversign);
	}

	public String getBegincarryoversign() {
		return this.getField(9);
	}

	public void setEndcarryoversigin(String EndCarryoversigin) {
		this.setField(10, EndCarryoversigin);
	}

	public String getEndcarryoversigin() {
		return this.getField(10);
	}

	public void setStartfeecreatedate(String StartFeeCreatedate) {
		this.setField(11, StartFeeCreatedate);
	}

	public String getStartfeecreatedate() {
		return this.getField(11);
	}
	public void setStartfeeenddate(String StartFeeEnddate) {
		this.setField(12, StartFeeEnddate);
	}

	public String getStartfeeenddate() {
		return this.getField(12);
	}

	public void setEestructurecode(String eeStructurecode) {
		this.setField(13, eeStructurecode);
	}

	public String getEestructurecode() {
		return this.getField(13);
	}

}
