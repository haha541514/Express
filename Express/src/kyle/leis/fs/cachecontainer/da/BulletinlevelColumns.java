package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BulletinlevelColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BulletinlevelColumns() {
		m_astrColumns = new String[3];
	}
	
	public BulletinlevelColumns(String blBlcode, 
            String blBlname, String blBlename){
		m_astrColumns = new String[3];
		setBlblcode(blBlcode);
		setBlblname(blBlname);
		setBlblename(blBlename);
	}

	public void setBlblcode(String blBlcode) {
		this.setField(0, blBlcode);
	}

	public String getBlblcode() {
		return this.getField(0);
	}

	public void setBlblname(String blBlname) {
		this.setField(1, blBlname);
	}

	public String getBlblname() {
		return this.getField(1);
	}

	public void setBlblename(String blBlename) {
		this.setField(2, blBlename);
	}

	public String getBlblename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
