package kyle.leis.eo.billing.receivable.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ReceivableforsmsColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ReceivableforsmsColumns() {
		m_astrColumns = new String[1];
	}
	
	public ReceivableforsmsColumns(String Rvrv_actualtotal){
		m_astrColumns = new String[1];
		setRvrv_actualtotal(Rvrv_actualtotal);
	}

	public void setRvrv_actualtotal(String Rvrv_actualtotal) {
		this.setField(0, Rvrv_actualtotal);
	}

	public String getRvrv_actualtotal() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
