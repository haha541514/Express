package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OperationtacheColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OperationtacheColumns() {
		m_astrColumns = new String[3];
	}
	
	public OperationtacheColumns(String otOtcode, 
            String otOtname, String otOtename){
		m_astrColumns = new String[3];
		setOtotcode(otOtcode);
		setOtotname(otOtname);
		setOtotename(otOtename);
	}

	public void setOtotcode(String otOtcode) {
		this.setField(0, otOtcode);
	}

	public String getOtotcode() {
		return this.getField(0);
	}

	public void setOtotname(String otOtname) {
		this.setField(1, otOtname);
	}

	public String getOtotname() {
		return this.getField(1);
	}

	public void setOtotename(String otOtename) {
		this.setField(2, otOtename);
	}

	public String getOtotename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
