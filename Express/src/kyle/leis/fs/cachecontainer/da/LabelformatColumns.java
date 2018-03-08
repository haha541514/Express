package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class LabelformatColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public LabelformatColumns() {
		m_astrColumns = new String[3];
	}
	
	public LabelformatColumns(String lfLfcode, 
            String lfLfname, String lfLfename){
		m_astrColumns = new String[3];
		setLflfcode(lfLfcode);
		setLflfname(lfLfname);
		setLflfename(lfLfename);
	}

	public void setLflfcode(String lfLfcode) {
		this.setField(0, lfLfcode);
	}

	public String getLflfcode() {
		return this.getField(0);
	}

	public void setLflfname(String lfLfname) {
		this.setField(1, lfLfname);
	}

	public String getLflfname() {
		return this.getField(1);
	}

	public void setLflfename(String lfLfename) {
		this.setField(2, lfLfename);
	}

	public String getLflfename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
