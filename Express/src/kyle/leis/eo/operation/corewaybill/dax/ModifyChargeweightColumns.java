package kyle.leis.eo.operation.corewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ModifyChargeweightColumns extends GeneralColumns implements Serializable {
	private static final long serialVersionUID = 0L;

	public ModifyChargeweightColumns() {
		m_astrColumns = new String[2];
	}
	
	public void setCustomerwaybillcode(String strCustomerwaybillcode) {
		this.setField(0, strCustomerwaybillcode);
	}

	public String getCustomerwaybillcode() {
		return this.getField(0);
	}

	public void setChargeweight(String strChargeweight) {
		this.setField(1, strChargeweight);
	}

	public String getChargeweight() {
		return this.getField(1);
	}	
}
