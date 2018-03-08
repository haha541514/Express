package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MaxsamelevelcustomerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MaxsamelevelcustomerColumns() {
		m_astrColumns = new String[1];
	}
	
	public MaxsamelevelcustomerColumns(String Maxstructruecode){
		m_astrColumns = new String[1];
		setMaxstructruecode(Maxstructruecode);
	}

	public void setMaxstructruecode(String Maxstructruecode) {
		this.setField(0, Maxstructruecode);
	}

	public String getMaxstructruecode() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
