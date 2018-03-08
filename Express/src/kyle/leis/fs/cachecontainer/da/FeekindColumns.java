package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FeekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FeekindColumns() {
		m_astrColumns = new String[4];
	}
	
	public FeekindColumns(String fkFkcode, 
            String fkFkname, String fkFkename,
            String fkAccountingonlysign){
		m_astrColumns = new String[4];
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
		setFkfkename(fkFkename);
		setFkAccountingonlysign(fkAccountingonlysign);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(0, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(0);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(1, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(1);
	}

	public void setFkfkename(String fkFkename) {
		this.setField(2, fkFkename);
	}

	public String getFkfkename() {
		return this.getField(2);
	}

	public void setFkAccountingonlysign(String strFkAccountingonlysign) {
		this.setField(3, strFkAccountingonlysign);
	}

	public String getFkAccountingonlysign() {
		return this.getField(3);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
