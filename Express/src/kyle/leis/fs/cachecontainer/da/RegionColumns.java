package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class RegionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public RegionColumns() {
		m_astrColumns = new String[3];
	}
	
	public RegionColumns(String rgRgcode, 
            String rgRgname, String rgRgename){
		m_astrColumns = new String[3];
		setRgrgcode(rgRgcode);
		setRgrgname(rgRgname);
		setRgrgename(rgRgename);
	}

	public void setRgrgcode(String rgRgcode) {
		this.setField(0, rgRgcode);
	}

	public String getRgrgcode() {
		return this.getField(0);
	}

	public void setRgrgname(String rgRgname) {
		this.setField(1, rgRgname);
	}

	public String getRgrgname() {
		return this.getField(1);
	}

	public void setRgrgename(String rgRgename) {
		this.setField(2, rgRgename);
	}

	public String getRgrgename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
