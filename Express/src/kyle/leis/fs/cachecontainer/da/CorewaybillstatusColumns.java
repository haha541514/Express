package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorewaybillstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorewaybillstatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public CorewaybillstatusColumns(String cwsCwscode, 
            String cwsCwsname, String cwsCwsename){
		m_astrColumns = new String[3];
		setCwscwscode(cwsCwscode);
		setCwscwsname(cwsCwsname);
		setCwscwsename(cwsCwsename);
	}

	public void setCwscwscode(String cwsCwscode) {
		this.setField(0, cwsCwscode);
	}

	public String getCwscwscode() {
		return this.getField(0);
	}

	public void setCwscwsname(String cwsCwsname) {
		this.setField(1, cwsCwsname);
	}

	public String getCwscwsname() {
		return this.getField(1);
	}

	public void setCwscwsename(String cwsCwsename) {
		this.setField(2, cwsCwsename);
	}

	public String getCwscwsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
