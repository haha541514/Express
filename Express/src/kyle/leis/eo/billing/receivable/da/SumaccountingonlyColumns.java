package kyle.leis.eo.billing.receivable.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumaccountingonlyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumaccountingonlyColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumaccountingonlyColumns(String Sumrvtotal){
		m_astrColumns = new String[1];
		setSumrvtotal(Sumrvtotal);
	}

	public void setSumrvtotal(String Sumrvtotal) {
		this.setField(0, Sumrvtotal);
	}

	public String getSumrvtotal() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
