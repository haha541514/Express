package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CashrecordkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CashrecordkindColumns() {
		
	}
	
	public CashrecordkindColumns(String crkCrkcode, 
            String crkCrkname, String crkCrkename){
		m_astrColumns = new String[3];
		setCrkcrkcode(crkCrkcode);
		setCrkcrkname(crkCrkname);
		setCrkcrkename(crkCrkename);
	}

	public void setCrkcrkcode(String crkCrkcode) {
		this.setField(0, crkCrkcode);
	}

	public String getCrkcrkcode() {
		return this.getField(0);
	}

	public void setCrkcrkname(String crkCrkname) {
		this.setField(1, crkCrkname);
	}

	public String getCrkcrkname() {
		return this.getField(1);
	}

	public void setCrkcrkename(String crkCrkename) {
		this.setField(2, crkCrkename);
	}

	public String getCrkcrkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
