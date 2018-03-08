package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DepartmentColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DepartmentColumns() {
		m_astrColumns = new String[3];
	}
	
	public DepartmentColumns(String dpDpcode, 
            String dpDpname, String dpDpename){
		m_astrColumns = new String[3];
		setDpdpcode(dpDpcode);
		setDpdpname(dpDpname);
		setDpdpename(dpDpename);
	}

	public void setDpdpcode(String dpDpcode) {
		this.setField(0, dpDpcode);
	}

	public String getDpdpcode() {
		return this.getField(0);
	}

	public void setDpdpname(String dpDpname) {
		this.setField(1, dpDpname);
	}

	public String getDpdpname() {
		return this.getField(1);
	}

	public void setDpdpename(String dpDpename) {
		this.setField(2, dpDpename);
	}

	public String getDpdpename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
