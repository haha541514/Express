package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class RegionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public RegionColumns() {
		m_astrColumns = new String[4];
	}
	
	public RegionColumns(String tdrRgcode, 
            String tdrRgname, String tdrRgename, 
            String tdrcCtcode){
		m_astrColumns = new String[4];
		setTdrrgcode(tdrRgcode);
		setTdrrgname(tdrRgname);
		setTdrrgename(tdrRgename);
		setTdrcctcode(tdrcCtcode);
	}

	public void setTdrrgcode(String tdrRgcode) {
		this.setField(0, tdrRgcode);
	}

	public String getTdrrgcode() {
		return this.getField(0);
	}

	public void setTdrrgname(String tdrRgname) {
		this.setField(1, tdrRgname);
	}

	public String getTdrrgname() {
		return this.getField(1);
	}

	public void setTdrrgename(String tdrRgename) {
		this.setField(2, tdrRgename);
	}

	public String getTdrrgename() {
		return this.getField(2);
	}

	public void setTdrcctcode(String tdrcCtcode) {
		this.setField(3, tdrcCtcode);
	}

	public String getTdrcctcode() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
