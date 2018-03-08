package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BusinessrulekindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BusinessrulekindColumns() {
		
	}
	
	public BusinessrulekindColumns(String brkBrkcode, 
            String brkBrkname, String brkBrkename){
		m_astrColumns = new String[3];
		setBrkbrkcode(brkBrkcode);
		setBrkbrkname(brkBrkname);
		setBrkbrkename(brkBrkename);
	}

	public void setBrkbrkcode(String brkBrkcode) {
		this.setField(0, brkBrkcode);
	}

	public String getBrkbrkcode() {
		return this.getField(0);
	}

	public void setBrkbrkname(String brkBrkname) {
		this.setField(1, brkBrkname);
	}

	public String getBrkbrkname() {
		return this.getField(1);
	}

	public void setBrkbrkename(String brkBrkename) {
		this.setField(2, brkBrkename);
	}

	public String getBrkbrkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
