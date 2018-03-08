package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CityColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CityColumns() {
		m_astrColumns = new String[5];
	}
	
	public CityColumns(String ctCtcode, 
            String ctCtname, String ctCtstartpostcode, 
            String ctCtendpostcode, String stStcode){
		m_astrColumns = new String[5];
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setCtctstartpostcode(ctCtstartpostcode);
		setCtctendpostcode(ctCtendpostcode);
		setStstcode(stStcode);
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

	public void setCtctstartpostcode(String ctCtstartpostcode) {
		this.setField(2, ctCtstartpostcode);
	}

	public String getCtctstartpostcode() {
		return this.getField(2);
	}

	public void setCtctendpostcode(String ctCtendpostcode) {
		this.setField(3, ctCtendpostcode);
	}

	public String getCtctendpostcode() {
		return this.getField(3);
	}

	public void setStstcode(String stStcode) {
		this.setField(4, stStcode);
	}

	public String getStstcode() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
