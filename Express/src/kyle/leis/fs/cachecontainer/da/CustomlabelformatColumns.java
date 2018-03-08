package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomlabelformatColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomlabelformatColumns() {
		m_astrColumns = new String[3];
	}
	
	public CustomlabelformatColumns(String clfClfcode, 
            String clfClfname, String clfClfename){
		m_astrColumns = new String[3];
		setClfclfcode(clfClfcode);
		setClfclfname(clfClfname);
		setClfclfename(clfClfename);
	}

	public void setClfclfcode(String clfClfcode) {
		this.setField(0, clfClfcode);
	}

	public String getClfclfcode() {
		return this.getField(0);
	}

	public void setClfclfname(String clfClfname) {
		this.setField(1, clfClfname);
	}

	public String getClfclfname() {
		return this.getField(1);
	}

	public void setClfclfename(String clfClfename) {
		this.setField(2, clfClfename);
	}

	public String getClfclfename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
