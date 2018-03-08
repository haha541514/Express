package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ServerstructuregroupColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerstructuregroupColumns() {
		m_astrColumns = new String[3];
	}
	
	public ServerstructuregroupColumns(String ssgSsgcode, 
            String ssgSsgname, String ssgSsgename){
		m_astrColumns = new String[3];
		setSsgssgcode(ssgSsgcode);
		setSsgssgname(ssgSsgname);
		setSsgssgename(ssgSsgename);
	}

	public void setSsgssgcode(String ssgSsgcode) {
		this.setField(0, ssgSsgcode);
	}

	public String getSsgssgcode() {
		return this.getField(0);
	}

	public void setSsgssgname(String ssgSsgname) {
		this.setField(1, ssgSsgname);
	}

	public String getSsgssgname() {
		return this.getField(1);
	}

	public void setSsgssgename(String ssgSsgename) {
		this.setField(2, ssgSsgename);
	}

	public String getSsgssgename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
