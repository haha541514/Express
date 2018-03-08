package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class ProductkindCondition extends GeneralCondition {
	public ProductkindCondition() {
		m_astrConditions = new String[2];
	}	
	
	public void setPksaletrialsign(String Pksaletrialsign) {
		this.setField(0, Pksaletrialsign);
	}

	public String getPksaletrialsign() {
		return this.getField(0);
	}

}
