package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransportwaybillstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportwaybillstatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public TransportwaybillstatusColumns(String twbsTwbscode, 
            String twbsTwbsname, String twbsTwbsename){
		m_astrColumns = new String[3];
		setTwbstwbscode(twbsTwbscode);
		setTwbstwbsname(twbsTwbsname);
		setTwbstwbsename(twbsTwbsename);
	}

	public void setTwbstwbscode(String twbsTwbscode) {
		this.setField(0, twbsTwbscode);
	}

	public String getTwbstwbscode() {
		return this.getField(0);
	}

	public void setTwbstwbsname(String twbsTwbsname) {
		this.setField(1, twbsTwbsname);
	}

	public String getTwbstwbsname() {
		return this.getField(1);
	}

	public void setTwbstwbsename(String twbsTwbsename) {
		this.setField(2, twbsTwbsename);
	}

	public String getTwbstwbsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
