package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodestatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodestatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public WaybillcodestatusColumns(String wbcsCscode, 
            String wbcsCsname, String wbcsCsename){
		m_astrColumns = new String[3];
		setWbcscscode(wbcsCscode);
		setWbcscsname(wbcsCsname);
		setWbcscsename(wbcsCsename);
	}

	public void setWbcscscode(String wbcsCscode) {
		this.setField(0, wbcsCscode);
	}

	public String getWbcscscode() {
		return this.getField(0);
	}

	public void setWbcscsname(String wbcsCsname) {
		this.setField(1, wbcsCsname);
	}

	public String getWbcscsname() {
		return this.getField(1);
	}

	public void setWbcscsename(String wbcsCsename) {
		this.setField(2, wbcsCsename);
	}

	public String getWbcscsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
