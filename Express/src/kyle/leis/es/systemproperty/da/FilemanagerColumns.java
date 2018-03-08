package kyle.leis.es.systemproperty.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FilemanagerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FilemanagerColumns() {
		m_astrColumns = new String[3];
	}
	
	public FilemanagerColumns(String flFl_name, 
            String flFl_path, String flFl_createdate){
		m_astrColumns = new String[3];
		setFlfl_name(flFl_name);
		setFlfl_path(flFl_path);
		setFlfl_createdate(flFl_createdate);
	}

	public void setFlfl_name(String flFl_name) {
		this.setField(0, flFl_name);
	}

	public String getFlfl_name() {
		return this.getField(0);
	}

	public void setFlfl_path(String flFl_path) {
		this.setField(1, flFl_path);
	}

	public String getFlfl_path() {
		return this.getField(1);
	}

	public void setFlfl_createdate(String flFl_createdate) {
		this.setField(2, flFl_createdate);
	}

	public String getFlfl_createdate() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
