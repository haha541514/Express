package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CargokindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CargokindColumns() {
		m_astrColumns = new String[5];
	}
	
	public CargokindColumns(String ckCgkcode, 
            String ckCgkname, String ckCgkename, 
            String ckCgkbatterysign, String ssSscode){
		m_astrColumns = new String[5];
		setCkcgkcode(ckCgkcode);
		setCkcgkname(ckCgkname);
		setCkcgkename(ckCgkename);
		setCkcgkbatterysign(ckCgkbatterysign);
		setSssscode(ssSscode);
	}

	public void setCkcgkcode(String ckCgkcode) {
		this.setField(0, ckCgkcode);
	}

	public String getCkcgkcode() {
		return this.getField(0);
	}

	public void setCkcgkname(String ckCgkname) {
		this.setField(1, ckCgkname);
	}

	public String getCkcgkname() {
		return this.getField(1);
	}

	public void setCkcgkename(String ckCgkename) {
		this.setField(2, ckCgkename);
	}

	public String getCkcgkename() {
		return this.getField(2);
	}

	public void setCkcgkbatterysign(String ckCgkbatterysign) {
		this.setField(3, ckCgkbatterysign);
	}

	public String getCkcgkbatterysign() {
		return this.getField(3);
	}

	public void setSssscode(String ssSscode) {
		this.setField(4, ssSscode);
	}

	public String getSssscode() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
