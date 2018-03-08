package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CargotypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CargotypeColumns() {
		
	}
	
	public CargotypeColumns(String ctCtcode, 
            String ctCtname, String ctCtename, 
            String ctCtsename, String ctCtvisiblesign){
		m_astrColumns = new String[5];
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setCtctename(ctCtename);
		setCtctsename(ctCtsename);
		setCtctvisiblesign(ctCtvisiblesign);
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

	public void setCtctsename(String ctCtsename) {
		this.setField(3, ctCtsename);
	}

	public String getCtctsename() {
		return this.getField(3);
	}

	public void setCtctvisiblesign(String ctCtvisiblesign) {
		this.setField(4, ctCtvisiblesign);
	}

	public String getCtctvisiblesign() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
