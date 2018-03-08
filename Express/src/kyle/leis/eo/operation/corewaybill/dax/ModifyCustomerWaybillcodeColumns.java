package kyle.leis.eo.operation.corewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ModifyCustomerWaybillcodeColumns extends GeneralColumns implements Serializable {
	
	private static final long serialVersionUID = -8265284628323765505L;

	public ModifyCustomerWaybillcodeColumns() {
		m_astrColumns = new String[2];
	}
	
	public void setOldCustomerwaybillcode(String strOldCustomerwaybillcode) {
		this.setField(0, strOldCustomerwaybillcode);
	}

	public String getOldCustomerwaybillcode() {
		return this.getField(0);
	}

	public void setCustomerwaybillcode(String strCustomerwaybillcode) {
		this.setField(1, strCustomerwaybillcode);
	}

	public String getCustomerwaybillcode() {
		return this.getField(1);
	}	
}
