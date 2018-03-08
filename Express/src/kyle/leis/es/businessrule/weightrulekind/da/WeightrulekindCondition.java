package kyle.leis.es.businessrule.weightrulekind.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class WeightrulekindCondition extends GeneralCondition {
	public WeightrulekindCondition() {
		m_astrConditions = new String[7];
	}	
	
	public void setWrkid(String wrkId) {
		this.setField(0, wrkId);
	}

	public String getWrkid() {
		return this.getField(0);
	}

	public void setWrkname(String wrkName) {
		this.setField(1, wrkName);
	}

	public String getWrkname() {
		return this.getField(1);
	}

	public void setWrkename(String wrkEname) {
		this.setField(2, wrkEname);
	}

	public String getWrkename() {
		return this.getField(2);
	}

	public void setWrkdefaultsign(String wrkDefaultsign) {
		this.setField(3, wrkDefaultsign);
	}

	public String getWrkdefaultsign() {
		return this.getField(3);
	}

	public void setPdcode(String pdCode) {
		this.setField(4, pdCode);
	}

	public String getPdcode() {
		return this.getField(4);
	}

	public void setSscode(String ssCode) {
		this.setField(5, ssCode);
	}

	public String getSscode() {
		return this.getField(5);
	}

	public void setPkcode(String pkCode) {
		this.setField(6, pkCode);
	}

	public String getPkcode() {
		return this.getField(6);
	}

}
