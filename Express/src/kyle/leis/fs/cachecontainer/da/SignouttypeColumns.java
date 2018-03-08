package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SignouttypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SignouttypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public SignouttypeColumns(String sotSotcode, 
            String sotSotname, String sotSotename){
		m_astrColumns = new String[3];
		setSotsotcode(sotSotcode);
		setSotsotname(sotSotname);
		setSotsotename(sotSotename);
	}

	public void setSotsotcode(String sotSotcode) {
		this.setField(0, sotSotcode);
	}

	public String getSotsotcode() {
		return this.getField(0);
	}

	public void setSotsotname(String sotSotname) {
		this.setField(1, sotSotname);
	}

	public String getSotsotname() {
		return this.getField(1);
	}

	public void setSotsotename(String sotSotename) {
		this.setField(2, sotSotename);
	}

	public String getSotsotename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
