package kyle.leis.eo.finance.purchaseorder.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PurchasepayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PurchasepayableColumns() {
		m_astrColumns = new String[5];
	}
	
	public PurchasepayableColumns(String pyPy_id, 
            String pyCw_code, String pyBk_code, 
            String pyFk_code, String pyPy_commissionrate){
		m_astrColumns = new String[5];
		setPypy_id(pyPy_id);
		setPycw_code(pyCw_code);
		setPybk_code(pyBk_code);
		setPyfk_code(pyFk_code);
		setPypy_commissionrate(pyPy_commissionrate);
	}

	public void setPypy_id(String pyPy_id) {
		this.setField(0, pyPy_id);
	}

	public String getPypy_id() {
		return this.getField(0);
	}

	public void setPycw_code(String pyCw_code) {
		this.setField(1, pyCw_code);
	}

	public String getPycw_code() {
		return this.getField(1);
	}

	public void setPybk_code(String pyBk_code) {
		this.setField(2, pyBk_code);
	}

	public String getPybk_code() {
		return this.getField(2);
	}

	public void setPyfk_code(String pyFk_code) {
		this.setField(3, pyFk_code);
	}

	public String getPyfk_code() {
		return this.getField(3);
	}

	public void setPypy_commissionrate(String pyPy_commissionrate) {
		this.setField(4, pyPy_commissionrate);
	}

	public String getPypy_commissionrate() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
