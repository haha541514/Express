package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FreightvaluetypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FreightvaluetypeColumns() {
		
	}
	
	public FreightvaluetypeColumns(String fvtFvtcode, 
            String fvtFvtname, String fvtFvtename){
		m_astrColumns = new String[3];
		setFvtfvtcode(fvtFvtcode);
		setFvtfvtname(fvtFvtname);
		setFvtfvtename(fvtFvtename);
	}

	public void setFvtfvtcode(String fvtFvtcode) {
		this.setField(0, fvtFvtcode);
	}

	public String getFvtfvtcode() {
		return this.getField(0);
	}

	public void setFvtfvtname(String fvtFvtname) {
		this.setField(1, fvtFvtname);
	}

	public String getFvtfvtname() {
		return this.getField(1);
	}

	public void setFvtfvtename(String fvtFvtename) {
		this.setField(2, fvtFvtename);
	}

	public String getFvtfvtename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
