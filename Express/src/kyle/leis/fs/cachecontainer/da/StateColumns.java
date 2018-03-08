package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class StateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public StateColumns() {
		m_astrColumns = new String[2];
	}
	
	public StateColumns(String stStcode, 
            String stStname){
		m_astrColumns = new String[2];
		setStstcode(stStcode);
		setStstname(stStname);
	}

	public void setStstcode(String stStcode) {
		this.setField(0, stStcode);
	}

	public String getStstcode() {
		return this.getField(0);
	}

	public void setStstname(String stStname) {
		this.setField(1, stStname);
	}

	public String getStstname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
