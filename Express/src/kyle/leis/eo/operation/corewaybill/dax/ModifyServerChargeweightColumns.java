package kyle.leis.eo.operation.corewaybill.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;

public class ModifyServerChargeweightColumns extends GeneralColumns implements Serializable {
	private static final long serialVersionUID = 0L;

	public ModifyServerChargeweightColumns() {
		m_astrColumns = new String[2];
	}
	
	public void setServerwaybillcode(String strServerwaybillcode) {
		this.setField(0, strServerwaybillcode);
	}

	public String getServerwaybillcode() {
		return this.getField(0);
	}

	public void setServerchargeweight(String strServerchargeweight) {
		this.setField(1, strServerchargeweight);
	}

	public String getServerchargeweight() {
		return this.getField(1);
	}	
}
