package kyle.leis.es.price.zone.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ZonedistrictpostcodeCondition extends GeneralCondition {
	public ZonedistrictpostcodeCondition() {
		m_astrConditions = new String[5];
	}	
	
	public void setZnvid(String znvId) {
		this.setField(0, znvId);
	}

	public String getZnvid() {
		return this.getField(0);
	}

	public void setDtcode(String dtCode) {
		this.setField(1, dtCode);
	}

	public String getDtcode() {
		return this.getField(1);
	}

	public void setZncode(String znCode) {
		this.setField(2, znCode);
	}

	public String getZncode() {
		return this.getField(2);
	}

	public void setValidpostcode2(String ValidPostcode2) {
		this.setField(3, ValidPostcode2);
	}

	public String getValidpostcode2() {
		return this.getField(3);
	}

	public void setValidpostcode1(String ValidPostcode1) {
		this.setField(4, ValidPostcode1);
	}

	public String getValidpostcode1() {
		return this.getField(4);
	}

}
