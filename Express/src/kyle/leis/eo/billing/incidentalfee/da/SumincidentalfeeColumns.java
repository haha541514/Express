package kyle.leis.eo.billing.incidentalfee.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumincidentalfeeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumincidentalfeeColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumincidentalfeeColumns(String Incidentalfeetotal){
		m_astrColumns = new String[1];
		setIncidentalfeetotal(Incidentalfeetotal);
	}

	public void setIncidentalfeetotal(String Incidentalfeetotal) {
		this.setField(0, Incidentalfeetotal);
	}

	public String getIncidentalfeetotal() {
		return this.getField(0);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
