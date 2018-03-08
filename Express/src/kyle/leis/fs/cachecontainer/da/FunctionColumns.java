package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FunctionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FunctionColumns() {
		m_astrColumns = new String[3];
	}
	
	public FunctionColumns(String fcFccode, 
            String fcFcname, String fcFcename){
		m_astrColumns = new String[3];
		setFcfccode(fcFccode);
		setFcfcname(fcFcname);
		setFcfcename(fcFcename);
	}

	public void setFcfccode(String fcFccode) {
		this.setField(0, fcFccode);
	}

	public String getFcfccode() {
		return this.getField(0);
	}

	public void setFcfcname(String fcFcname) {
		this.setField(1, fcFcname);
	}

	public String getFcfcname() {
		return this.getField(1);
	}

	public void setFcfcename(String fcFcename) {
		this.setField(2, fcFcename);
	}

	public String getFcfcename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
