package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CurrencykindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CurrencykindColumns() {
		
	}
	
	public CurrencykindColumns(String ckCkcode, 
            String ckCkname, String ckCkename){
		m_astrColumns = new String[3];
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setCkckename(ckCkename);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(0, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(0);
	}

	public void setCkckname(String ckCkname) {
		this.setField(1, ckCkname);
	}

	public String getCkckname() {
		return this.getField(1);
	}

	public void setCkckename(String ckCkename) {
		this.setField(2, ckCkename);
	}

	public String getCkckename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
