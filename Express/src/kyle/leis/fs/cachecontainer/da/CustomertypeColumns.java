package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomertypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomertypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public CustomertypeColumns(String ctCtcode, 
            String ctCtname, String ctCtename){
		m_astrColumns = new String[3];
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setCtctename(ctCtename);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(0, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(0);
	}

	public void setCtctname(String ctCtname) {
		this.setField(1, ctCtname);
	}

	public String getCtctname() {
		return this.getField(1);
	}

	public void setCtctename(String ctCtename) {
		this.setField(2, ctCtename);
	}

	public String getCtctename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
