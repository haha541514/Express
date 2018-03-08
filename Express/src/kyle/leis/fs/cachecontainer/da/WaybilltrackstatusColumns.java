package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybilltrackstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybilltrackstatusColumns() {
		m_astrColumns = new String[6];
	}
	
	public WaybilltrackstatusColumns(String wbtsWbtscode, 
            String wbtsWbtsname, String wbtsWbtsename, 
            String wbtsWbtsabnormalsign, String wbtpWbtpcode, 
            String wbtpWbtpname){
		m_astrColumns = new String[6];
		setWbtswbtscode(wbtsWbtscode);
		setWbtswbtsname(wbtsWbtsname);
		setWbtswbtsename(wbtsWbtsename);
		setWbtswbtsabnormalsign(wbtsWbtsabnormalsign);
		setWbtpwbtpcode(wbtpWbtpcode);
		setWbtpwbtpname(wbtpWbtpname);
	}

	public void setWbtswbtscode(String wbtsWbtscode) {
		this.setField(0, wbtsWbtscode);
	}

	public String getWbtswbtscode() {
		return this.getField(0);
	}

	public void setWbtswbtsname(String wbtsWbtsname) {
		this.setField(1, wbtsWbtsname);
	}

	public String getWbtswbtsname() {
		return this.getField(1);
	}

	public void setWbtswbtsename(String wbtsWbtsename) {
		this.setField(2, wbtsWbtsename);
	}

	public String getWbtswbtsename() {
		return this.getField(2);
	}

	public void setWbtswbtsabnormalsign(String wbtsWbtsabnormalsign) {
		this.setField(3, wbtsWbtsabnormalsign);
	}

	public String getWbtswbtsabnormalsign() {
		return this.getField(3);
	}

	public void setWbtpwbtpcode(String wbtpWbtpcode) {
		this.setField(4, wbtpWbtpcode);
	}

	public String getWbtpwbtpcode() {
		return this.getField(4);
	}

	public void setWbtpwbtpname(String wbtpWbtpname) {
		this.setField(5, wbtpWbtpname);
	}

	public String getWbtpwbtpname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
