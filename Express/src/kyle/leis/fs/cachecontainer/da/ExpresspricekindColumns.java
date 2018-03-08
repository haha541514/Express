package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ExpresspricekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ExpresspricekindColumns() {
		m_astrColumns = new String[3];
	}
	
	public ExpresspricekindColumns(String epkEpkcode, 
            String epkEpkname, String epkEpkename){
		m_astrColumns = new String[3];
		setEpkepkcode(epkEpkcode);
		setEpkepkname(epkEpkname);
		setEpkepkename(epkEpkename);
	}

	public void setEpkepkcode(String epkEpkcode) {
		this.setField(0, epkEpkcode);
	}

	public String getEpkepkcode() {
		return this.getField(0);
	}

	public void setEpkepkname(String epkEpkname) {
		this.setField(1, epkEpkname);
	}

	public String getEpkepkname() {
		return this.getField(1);
	}

	public void setEpkepkename(String epkEpkename) {
		this.setField(2, epkEpkename);
	}

	public String getEpkepkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
