package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IssueholdstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssueholdstatusColumns() {
		m_astrColumns = new String[2];
	}
	
	public IssueholdstatusColumns(String ihsIhscode, 
            String ihsIhsname){
		m_astrColumns = new String[2];
		setIhsihscode(ihsIhscode);
		setIhsihsname(ihsIhsname);
	}

	public void setIhsihscode(String ihsIhscode) {
		this.setField(0, ihsIhscode);
	}

	public String getIhsihscode() {
		return this.getField(0);
	}

	public void setIhsihsname(String ihsIhsname) {
		this.setField(1, ihsIhsname);
	}

	public String getIhsihsname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
