package kyle.leis.eo.finance.purchaseorder.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PurchasefeeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PurchasefeeColumns() {
		m_astrColumns = new String[3];
	}
	
	public PurchasefeeColumns(String pofPo_id, 
            String pofFk_code, String pofPof_commissionrate){
		m_astrColumns = new String[3];
		setPofpo_id(pofPo_id);
		setPoffk_code(pofFk_code);
		setPofpof_commissionrate(pofPof_commissionrate);
	}

	public void setPofpo_id(String pofPo_id) {
		this.setField(0, pofPo_id);
	}

	public String getPofpo_id() {
		return this.getField(0);
	}

	public void setPoffk_code(String pofFk_code) {
		this.setField(1, pofFk_code);
	}

	public String getPoffk_code() {
		return this.getField(1);
	}

	public void setPofpof_commissionrate(String pofPof_commissionrate) {
		this.setField(2, pofPof_commissionrate);
	}

	public String getPofpof_commissionrate() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
