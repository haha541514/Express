package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MessagenoticekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MessagenoticekindColumns() {
		m_astrColumns = new String[3];
	}
	
	public MessagenoticekindColumns(String mnkMnkcode, 
            String mnkMnkname, String mnkMnkename){
		m_astrColumns = new String[3];
		setMnkmnkcode(mnkMnkcode);
		setMnkmnkname(mnkMnkname);
		setMnkmnkename(mnkMnkename);
	}

	public void setMnkmnkcode(String mnkMnkcode) {
		this.setField(0, mnkMnkcode);
	}

	public String getMnkmnkcode() {
		return this.getField(0);
	}

	public void setMnkmnkname(String mnkMnkname) {
		this.setField(1, mnkMnkname);
	}

	public String getMnkmnkname() {
		return this.getField(1);
	}

	public void setMnkmnkename(String mnkMnkename) {
		this.setField(2, mnkMnkename);
	}

	public String getMnkmnkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
