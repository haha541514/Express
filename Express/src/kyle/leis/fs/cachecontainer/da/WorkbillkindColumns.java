package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WorkbillkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WorkbillkindColumns() {
		m_astrColumns = new String[3];
	}
	
	public WorkbillkindColumns(String wbkWbkcode, 
            String wbkWbkname, String wbkWbkename){
		m_astrColumns = new String[3];
		setWbkwbkcode(wbkWbkcode);
		setWbkwbkname(wbkWbkname);
		setWbkwbkename(wbkWbkename);
	}

	public void setWbkwbkcode(String wbkWbkcode) {
		this.setField(0, wbkWbkcode);
	}

	public String getWbkwbkcode() {
		return this.getField(0);
	}

	public void setWbkwbkname(String wbkWbkname) {
		this.setField(1, wbkWbkname);
	}

	public String getWbkwbkname() {
		return this.getField(1);
	}

	public void setWbkwbkename(String wbkWbkename) {
		this.setField(2, wbkWbkename);
	}

	public String getWbkwbkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
