package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeestatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeestatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public FeestatusColumns(String fsFscode, 
            String fsFsname, String fsFsename){
		m_astrColumns = new String[3];
		setFsfscode(fsFscode);
		setFsfsname(fsFsname);
		setFsfsename(fsFsename);
	}

	public void setFsfscode(String fsFscode) {
		this.setField(0, fsFscode);
	}

	public String getFsfscode() {
		return this.getField(0);
	}

	public void setFsfsname(String fsFsname) {
		this.setField(1, fsFsname);
	}

	public String getFsfsname() {
		return this.getField(1);
	}

	public void setFsfsename(String fsFsename) {
		this.setField(2, fsFsename);
	}

	public String getFsfsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
