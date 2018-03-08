package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WorkbilllevelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WorkbilllevelColumns() {
		m_astrColumns = new String[3];
	}
	
	public WorkbilllevelColumns(String wblWblcode, 
            String wblWblname, String wblWblename){
		m_astrColumns = new String[3];
		setWblwblcode(wblWblcode);
		setWblwblname(wblWblname);
		setWblwblename(wblWblename);
	}

	public void setWblwblcode(String wblWblcode) {
		this.setField(0, wblWblcode);
	}

	public String getWblwblcode() {
		return this.getField(0);
	}

	public void setWblwblname(String wblWblname) {
		this.setField(1, wblWblname);
	}

	public String getWblwblname() {
		return this.getField(1);
	}

	public void setWblwblename(String wblWblename) {
		this.setField(2, wblWblename);
	}

	public String getWblwblename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
