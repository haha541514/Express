package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PaymenttypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PaymenttypeColumns() {
		
	}
	
	public PaymenttypeColumns(String ptPtcode, 
            String ptPtname, String ptPtename){
		m_astrColumns = new String[3];
		setPtptcode(ptPtcode);
		setPtptname(ptPtname);
		setPtptename(ptPtename);
	}

	public void setPtptcode(String ptPtcode) {
		this.setField(0, ptPtcode);
	}

	public String getPtptcode() {
		return this.getField(0);
	}

	public void setPtptname(String ptPtname) {
		this.setField(1, ptPtname);
	}

	public String getPtptname() {
		return this.getField(1);
	}

	public void setPtptename(String ptPtename) {
		this.setField(2, ptPtename);
	}

	public String getPtptename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
