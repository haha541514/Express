package kyle.leis.eo.operation.corewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ModifyServerWaybillcodeColumns extends GeneralColumns implements Serializable {
	
	private static final long serialVersionUID = -8265284628323765505L;

	public ModifyServerWaybillcodeColumns() {
		m_astrColumns = new String[2];
	}
	
	public void setOldServerwaybillcode(String strOldServerwaybillcode) {
		this.setField(0, strOldServerwaybillcode);
	}

	public String getOldServerwaybillcode() {
		return this.getField(0);
	}

	public void setServerwaybillcode(String strServerwaybillcode) {
		this.setField(1, strServerwaybillcode);
	}

	public String getServerwaybillcode() {
		return this.getField(1);
	}	
}
