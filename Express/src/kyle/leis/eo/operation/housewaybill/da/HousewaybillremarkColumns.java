package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class HousewaybillremarkColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public HousewaybillremarkColumns() {
		m_astrColumns = new String[1];
	}
	
	public HousewaybillremarkColumns(String hwHw_remark){
		m_astrColumns = new String[1];
		setHwhw_remark(hwHw_remark);
	}

	public void setHwhw_remark(String hwHw_remark) {
		this.setField(0, hwHw_remark);
	}

	public String getHwhw_remark() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
