package kyle.leis.es.company.shipperblacklist.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ShipperblacklistCondition extends GeneralCondition {
	public ShipperblacklistCondition() {
		m_astrConditions = new String[4];
	}	
	
	public void setSblcode(String sblCode) {
		this.setField(0, sblCode);
	}

	public String getSblcode() {
		return this.getField(0);
	}

	public void setSbllikecompanyname(String sblLikeCompanyname) {
		this.setField(1, sblLikeCompanyname);
	}

	public String getSbllikecompanyname() {
		return this.getField(1);
	}

	public void setSblcompanyname(String sblCompanyname) {
		this.setField(2, sblCompanyname);
	}

	public String getSblcompanyname() {
		return this.getField(2);
	}

	public void setSsgcode(String ssgCode) {
		this.setField(3, ssgCode);
	}

	public String getSsgcode() {
		return this.getField(3);
	}

}
