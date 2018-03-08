package kyle.leis.eo.operation.corewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ModifyHawbWaybillcodeColumns  extends GeneralColumns implements Serializable {
	
	private static final long serialVersionUID = -8265284628323765505L;

	public ModifyHawbWaybillcodeColumns() {
		m_astrColumns = new String[2];
	}
	
	public void setOldHawbwaybillcode(String strOldHawbwaybillcode) {
		this.setField(0, strOldHawbwaybillcode);
	}

	public String getOldHawbwaybillcode() {
		return this.getField(0);
	}

	public void setHawbwaybillcode(String strHawbwaybillcode) {
		this.setField(1, strHawbwaybillcode);
	}

	public String getHawbwaybillcode() {
		return this.getField(1);
	}

}
