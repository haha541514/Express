package kyle.leis.fs.cachecontainer.da;

import kyle.common.dbaccess.query.GeneralCondition;

public class SupplierCondition extends GeneralCondition {
	public SupplierCondition() {
		m_astrConditions = new String[1];
	}	
	
	public void setCocode(String strCocode) {
		this.setField(0, strCocode);
	}

	public String getCocode() {
		return this.getField(0);
	}


}
