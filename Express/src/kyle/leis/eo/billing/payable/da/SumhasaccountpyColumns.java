package kyle.leis.eo.billing.payable.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumhasaccountpyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumhasaccountpyColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumhasaccountpyColumns(String Sumpytotal){
		m_astrColumns = new String[1];
		setSumpytotal(Sumpytotal);
	}

	public void setSumpytotal(String Sumpytotal) {
		this.setField(0, Sumpytotal);
	}

	public String getSumpytotal() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
