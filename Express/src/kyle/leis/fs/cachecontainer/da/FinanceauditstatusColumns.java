package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FinanceauditstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FinanceauditstatusColumns() {
		m_astrColumns = new String[3];
	}
	
	public FinanceauditstatusColumns(String Fascode, 
            String Fasname, String Fasename){
		m_astrColumns = new String[3];
		setFascode(Fascode);
		setFasname(Fasname);
		setFasename(Fasename);
	}

	public void setFascode(String Fascode) {
		this.setField(0, Fascode);
	}

	public String getFascode() {
		return this.getField(0);
	}

	public void setFasname(String Fasname) {
		this.setField(1, Fasname);
	}

	public String getFasname() {
		return this.getField(1);
	}

	public void setFasename(String Fasename) {
		this.setField(2, Fasename);
	}

	public String getFasename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
