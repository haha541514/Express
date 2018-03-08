package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodekindColumns() {
		m_astrColumns = new String[3];
	}
	
	public WaybillcodekindColumns(String wbckBckcode, 
            String wbckBckname, String wbckBckename){
		m_astrColumns = new String[3];
		setWbckbckcode(wbckBckcode);
		setWbckbckname(wbckBckname);
		setWbckbckename(wbckBckename);
	}

	public void setWbckbckcode(String wbckBckcode) {
		this.setField(0, wbckBckcode);
	}

	public String getWbckbckcode() {
		return this.getField(0);
	}

	public void setWbckbckname(String wbckBckname) {
		this.setField(1, wbckBckname);
	}

	public String getWbckbckname() {
		return this.getField(1);
	}

	public void setWbckbckename(String wbckBckename) {
		this.setField(2, wbckBckename);
	}

	public String getWbckbckename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
