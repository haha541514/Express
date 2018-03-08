package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PricegroupColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PricegroupColumns() {
		
	}
	
	public PricegroupColumns(String pgPgcode, 
            String pgPgname, String pgPgename){
		m_astrColumns = new String[3];
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
		setPgpgename(pgPgename);
	}

	public void setPgpgcode(String pgPgcode) {
		this.setField(0, pgPgcode);
	}

	public String getPgpgcode() {
		return this.getField(0);
	}

	public void setPgpgname(String pgPgname) {
		this.setField(1, pgPgname);
	}

	public String getPgpgname() {
		return this.getField(1);
	}

	public void setPgpgename(String pgPgename) {
		this.setField(2, pgPgename);
	}

	public String getPgpgename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
