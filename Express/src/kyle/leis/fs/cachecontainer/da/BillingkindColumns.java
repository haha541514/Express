package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BillingkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BillingkindColumns() {
		
	}
	
	public BillingkindColumns(String bkBkcode, 
            String bkBkname, String bkBkename){
		m_astrColumns = new String[3];
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setBkbkename(bkBkename);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(0, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(0);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(1, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(1);
	}

	public void setBkbkename(String bkBkename) {
		this.setField(2, bkBkename);
	}

	public String getBkbkename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
