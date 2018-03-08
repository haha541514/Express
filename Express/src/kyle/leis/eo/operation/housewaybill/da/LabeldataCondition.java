package kyle.leis.eo.operation.housewaybill.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class LabeldataCondition extends GeneralCondition {
	public LabeldataCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setLcns(String lcns) {
		this.setField(0, lcns);
	}

	public String getLcns() {
		return this.getField(0);
	}

	public void setProducts(String products) {
		this.setField(1, products);
	}

	public String getProducts() {
		return this.getField(1);
	}

	public void setMtype(String mtype) {
		this.setField(2, mtype);
	}

	public String getMtype() {
		return this.getField(2);
	}

	public void setCode(String code) {
		this.setField(3, code);
	}

	public String getCode() {
		return this.getField(3);
	}

	public void setLikeCode(String likeCode) {
		this.setField(4, likeCode);
	}

	public String getLikeCode() {
		return this.getField(4);
	}
}
