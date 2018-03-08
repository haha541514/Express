package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ZoneformatColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZoneformatColumns() {
		m_astrColumns = new String[3];
	}
	
	public ZoneformatColumns(String zfZfcode, 
            String zfZfname, String zfZfename){
		m_astrColumns = new String[3];
		setZfzfcode(zfZfcode);
		setZfzfname(zfZfname);
		setZfzfename(zfZfename);
	}

	public void setZfzfcode(String zfZfcode) {
		this.setField(0, zfZfcode);
	}

	public String getZfzfcode() {
		return this.getField(0);
	}

	public void setZfzfname(String zfZfname) {
		this.setField(1, zfZfname);
	}

	public String getZfzfname() {
		return this.getField(1);
	}

	public void setZfzfename(String zfZfename) {
		this.setField(2, zfZfename);
	}

	public String getZfzfename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
