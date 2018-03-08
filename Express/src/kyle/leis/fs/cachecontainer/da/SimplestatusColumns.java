package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplestatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplestatusColumns() {
		
	}
	
	public SimplestatusColumns(String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[2];
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setSssscode(String ssSscode) {
		this.setField(0, ssSscode);
	}

	public String getSssscode() {
		return this.getField(0);
	}

	public void setSsssname(String ssSsname) {
		this.setField(1, ssSsname);
	}

	public String getSsssname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
