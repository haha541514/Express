package kyle.leis.es.company.customer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SimplecustomerCondition extends GeneralCondition {
	public SimplecustomerCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setCocodeparent(String cocodeparent) {
		this.setField(0, cocodeparent);
	}

	public String getCocodeparent() {
		return this.getField(0);
	}

	public void setCocode(String cocode) {
		this.setField(1, cocode);
	}

	public String getCocode() {
		return this.getField(1);
	}

	public void setCtcode(String ctcode) {
		this.setField(2, ctcode);
	}

	public String getCtcode() {
		return this.getField(2);
	}

	public void setLikeconame(String likeconame) {
		this.setField(3, likeconame);
	}

	public String getLikeconame() {
		return this.getField(3);
	}

	public void setLikecosname(String likecosname) {
		this.setField(4, likecosname);
	}

	public String getLikecosname() {
		return this.getField(4);
	}

}
