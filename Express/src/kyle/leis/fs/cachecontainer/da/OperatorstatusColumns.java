package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OperatorstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OperatorstatusColumns() {
		m_astrColumns = new String[2];
	}
	
	public OperatorstatusColumns(String osOscode, 
            String osOsname){
		m_astrColumns = new String[2];
		setOsoscode(osOscode);
		setOsosname(osOsname);
	}

	public void setOsoscode(String osOscode) {
		this.setField(0, osOscode);
	}

	public String getOsoscode() {
		return this.getField(0);
	}

	public void setOsosname(String osOsname) {
		this.setField(1, osOsname);
	}

	public String getOsosname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
