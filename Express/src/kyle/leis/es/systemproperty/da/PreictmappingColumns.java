package kyle.leis.es.systemproperty.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PreictmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PreictmappingColumns() {
		m_astrColumns = new String[2];
	}
	
	public PreictmappingColumns(String Pm_originvalue, 
            String Pm_standardvalue){
		m_astrColumns = new String[2];
		setPm_originvalue(Pm_originvalue);
		setPm_standardvalue(Pm_standardvalue);
	}

	public void setPm_originvalue(String Pm_originvalue) {
		this.setField(0, Pm_originvalue);
	}

	public String getPm_originvalue() {
		return this.getField(0);
	}

	public void setPm_standardvalue(String Pm_standardvalue) {
		this.setField(1, Pm_standardvalue);
	}

	public String getPm_standardvalue() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
