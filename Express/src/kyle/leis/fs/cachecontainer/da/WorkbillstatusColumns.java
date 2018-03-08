package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WorkbillstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WorkbillstatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public WorkbillstatusColumns(String wbsWbscode, 
            String wbsWbsname, String wbsWbsename){
		m_astrColumns = new String[3];
		setWbswbscode(wbsWbscode);
		setWbswbsname(wbsWbsname);
		setWbswbsename(wbsWbsename);
	}

	public void setWbswbscode(String wbsWbscode) {
		this.setField(0, wbsWbscode);
	}

	public String getWbswbscode() {
		return this.getField(0);
	}

	public void setWbswbsname(String wbsWbsname) {
		this.setField(1, wbsWbsname);
	}

	public String getWbswbsname() {
		return this.getField(1);
	}

	public void setWbswbsename(String wbsWbsename) {
		this.setField(2, wbsWbsename);
	}

	public String getWbswbsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
