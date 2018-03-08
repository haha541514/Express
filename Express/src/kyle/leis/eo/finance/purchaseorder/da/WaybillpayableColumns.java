package kyle.leis.eo.finance.purchaseorder.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillpayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillpayableColumns() {
		m_astrColumns = new String[3];
	}
	
	public WaybillpayableColumns(String powbPo_id, 
            String powbCw_code, String pyPy_id){
		m_astrColumns = new String[3];
		setPowbpo_id(powbPo_id);
		setPowbcw_code(powbCw_code);
		setPypy_id(pyPy_id);
	}

	public void setPowbpo_id(String powbPo_id) {
		this.setField(0, powbPo_id);
	}

	public String getPowbpo_id() {
		return this.getField(0);
	}

	public void setPowbcw_code(String powbCw_code) {
		this.setField(1, powbCw_code);
	}

	public String getPowbcw_code() {
		return this.getField(1);
	}

	public void setPypy_id(String pyPy_id) {
		this.setField(2, pyPy_id);
	}

	public String getPypy_id() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
