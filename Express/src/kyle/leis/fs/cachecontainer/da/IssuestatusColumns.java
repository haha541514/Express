package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IssuestatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssuestatusColumns() {
		m_astrColumns = new String[2];
	}
	
	public IssuestatusColumns(String isusIsuscode, 
            String isusIsusname){
		m_astrColumns = new String[2];
		setIsusisuscode(isusIsuscode);
		setIsusisusname(isusIsusname);
	}

	public void setIsusisuscode(String isusIsuscode) {
		this.setField(0, isusIsuscode);
	}

	public String getIsusisuscode() {
		return this.getField(0);
	}

	public void setIsusisusname(String isusIsusname) {
		this.setField(1, isusIsusname);
	}

	public String getIsusisusname() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
