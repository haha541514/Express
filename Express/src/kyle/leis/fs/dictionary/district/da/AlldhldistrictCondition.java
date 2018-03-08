package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class AlldhldistrictCondition extends GeneralCondition {
	public AlldhldistrictCondition() {
		m_astrConditions = new String[6];
	}	
	
	public void setDdnationcode(String ddnationcode) {
		this.setField(0, ddnationcode);
	}

	public String getDdnationcode() {
		return this.getField(0);
	}

	public void setDdcityname(String ddcityname) {
		this.setField(1, ddcityname);
	}

	public String getDdcityname() {
		return this.getField(1);
	}

	public void setDdhubcode(String ddhubcode) {
		this.setField(2, ddhubcode);
	}

	public String getDdhubcode() {
		return this.getField(2);
	}

	public void setStartpostcode(String Startpostcode) {
		this.setField(3, Startpostcode);
	}

	public String getStartpostcode() {
		return this.getField(3);
	}

	public void setEndpostcode(String Endpostcode) {
		this.setField(4, Endpostcode);
	}

	public String getEndpostcode() {
		return this.getField(4);
	}

}
