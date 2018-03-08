package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CashrecordstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CashrecordstatusColumns() {
		
	}
	
	public CashrecordstatusColumns(String crsCrscode, 
            String crsCrsname, String crsCrsename){
		m_astrColumns = new String[3];
		setCrscrscode(crsCrscode);
		setCrscrsname(crsCrsname);
		setCrscrsename(crsCrsename);
	}

	public void setCrscrscode(String crsCrscode) {
		this.setField(0, crsCrscode);
	}

	public String getCrscrscode() {
		return this.getField(0);
	}

	public void setCrscrsname(String crsCrsname) {
		this.setField(1, crsCrsname);
	}

	public String getCrscrsname() {
		return this.getField(1);
	}

	public void setCrscrsename(String crsCrsename) {
		this.setField(2, crsCrsename);
	}

	public String getCrscrsename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
