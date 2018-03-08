package kyle.leis.fs.dictionary.district.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class StatisticdhlremotedistrictCondition extends GeneralCondition {
	public StatisticdhlremotedistrictCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setDrd_nationcode(String drd_nationcode) {
		this.setField(0, drd_nationcode);
	}

	public String getDrd_nationcode() {
		return this.getField(0);
	}

}
