package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ServerweightkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerweightkindColumns() {
		m_astrColumns = new String[3];
	}
	
	public ServerweightkindColumns(String swkSwkcode, 
            String swkSwkname, String swkSwkename){
		m_astrColumns = new String[3];
		setSwkswkcode(swkSwkcode);
		setSwkswkname(swkSwkname);
		setSwkswkename(swkSwkename);
	}

	public void setSwkswkcode(String swkSwkcode) {
		this.setField(0, swkSwkcode);
	}

	public String getSwkswkcode() {
		return this.getField(0);
	}

	public void setSwkswkname(String swkSwkname) {
		this.setField(1, swkSwkname);
	}

	public String getSwkswkname() {
		return this.getField(1);
	}

	public void setSwkswkename(String swkSwkename) {
		this.setField(2, swkSwkename);
	}

	public String getSwkswkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
