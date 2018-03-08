package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimpleissuetypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimpleissuetypeColumns() {
		m_astrColumns = new String[4];
	}
	
	public SimpleissuetypeColumns(String isutIsutcode, 
            String isutIsutname, String isutIsutename, 
            String isutIsutgroup){
		m_astrColumns = new String[4];
		setIsutisutcode(isutIsutcode);
		setIsutisutname(isutIsutname);
		setIsutisutename(isutIsutename);
		setIsutisutgroup(isutIsutgroup);
	}

	public void setIsutisutcode(String isutIsutcode) {
		this.setField(0, isutIsutcode);
	}

	public String getIsutisutcode() {
		return this.getField(0);
	}

	public void setIsutisutname(String isutIsutname) {
		this.setField(1, isutIsutname);
	}

	public String getIsutisutname() {
		return this.getField(1);
	}

	public void setIsutisutename(String isutIsutename) {
		this.setField(2, isutIsutename);
	}

	public String getIsutisutename() {
		return this.getField(2);
	}

	public void setIsutisutgroup(String isutIsutgroup) {
		this.setField(3, isutIsutgroup);
	}

	public String getIsutisutgroup() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
