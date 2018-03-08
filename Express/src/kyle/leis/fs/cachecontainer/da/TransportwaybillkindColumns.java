package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransportwaybillkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportwaybillkindColumns() {
		m_astrColumns = new String[3];
	}
	
	public TransportwaybillkindColumns(String twbkTwbkcode, 
            String twbkTwbkname, String twbkTwbkename){
		m_astrColumns = new String[3];
		setTwbktwbkcode(twbkTwbkcode);
		setTwbktwbkname(twbkTwbkname);
		setTwbktwbkename(twbkTwbkename);
	}

	public void setTwbktwbkcode(String twbkTwbkcode) {
		this.setField(0, twbkTwbkcode);
	}

	public String getTwbktwbkcode() {
		return this.getField(0);
	}

	public void setTwbktwbkname(String twbkTwbkname) {
		this.setField(1, twbkTwbkname);
	}

	public String getTwbktwbkname() {
		return this.getField(1);
	}

	public void setTwbktwbkename(String twbkTwbkename) {
		this.setField(2, twbkTwbkename);
	}

	public String getTwbktwbkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
