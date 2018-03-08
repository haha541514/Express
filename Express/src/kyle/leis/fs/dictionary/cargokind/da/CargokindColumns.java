package kyle.leis.fs.dictionary.cargokind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CargokindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CargokindColumns() {
		m_astrColumns = new String[6];
	}
	
	public CargokindColumns(String ckCgkcode, 
            String ckCgkname, String ckCgkename, 
            String ckCgkbatterysign, String siSscode, 
            String siSsname){
		m_astrColumns = new String[6];
		setCkcgkcode(ckCgkcode);
		setCkcgkname(ckCgkname);
		setCkcgkename(ckCgkename);
		setCkcgkbatterysign(ckCgkbatterysign);
		setSisscode(siSscode);
		setSissname(siSsname);
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

	public void setSisscode(String siSscode) {
		this.setField(4, siSscode);
	}

	public String getSisscode() {
		return this.getField(4);
	}

	public void setSissname(String siSsname) {
		this.setField(5, siSsname);
	}

	public String getSissname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
