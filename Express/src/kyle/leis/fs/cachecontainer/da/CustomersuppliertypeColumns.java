package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomersuppliertypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomersuppliertypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public CustomersuppliertypeColumns(String cstCstcode, 
            String cstCstname, String cstCstename){
		m_astrColumns = new String[3];
		setCstcstcode(cstCstcode);
		setCstcstname(cstCstname);
		setCstcstename(cstCstename);
	}

	public void setCstcstcode(String cstCstcode) {
		this.setField(0, cstCstcode);
	}

	public String getCstcstcode() {
		return this.getField(0);
	}

	public void setCstcstname(String cstCstname) {
		this.setField(1, cstCstname);
	}

	public String getCstcstname() {
		return this.getField(1);
	}

	public void setCstcstename(String cstCstename) {
		this.setField(2, cstCstename);
	}

	public String getCstcstename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
