package kyle.leis.eo.customerservice.issue.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MaxissueactionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MaxissueactionColumns() {
		m_astrColumns = new String[1];
	}
	
	public MaxissueactionColumns(String Maxisaid){
		m_astrColumns = new String[1];
		setMaxisaid(Maxisaid);
	}

	public void setMaxisaid(String Maxisaid) {
		this.setField(0, Maxisaid);
	}

	public String getMaxisaid() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
