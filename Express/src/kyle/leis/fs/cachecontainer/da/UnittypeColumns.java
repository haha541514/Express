package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class UnittypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UnittypeColumns() {
		
	}
	
	public UnittypeColumns(String utUtcode, 
            String utUtname, String utUtename){
		m_astrColumns = new String[3];
		setUtutcode(utUtcode);
		setUtutname(utUtname);
		setUtutename(utUtename);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(0, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(0);
	}

	public void setUtutname(String utUtname) {
		this.setField(1, utUtname);
	}

	public String getUtutname() {
		return this.getField(1);
	}

	public void setUtutename(String utUtename) {
		this.setField(2, utUtename);
	}

	public String getUtutename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
