package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IssuegradeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssuegradeColumns() {
		m_astrColumns = new String[2];
	}
	
	public IssuegradeColumns(String isugIsugcode, 
            String isugIsugname){
		m_astrColumns = new String[2];
		setIsugisugcode(isugIsugcode);
		setIsugisugname(isugIsugname);
	}

	public void setIsugisugcode(String isugIsugcode) {
		this.setField(0, isugIsugcode);
	}

	public String getIsugisugcode() {
		return this.getField(0);
	}

	public void setIsugisugname(String isugIsugname) {
		this.setField(1, isugIsugname);
	}

	public String getIsugisugname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
