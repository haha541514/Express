package kyle.leis.fs.authoritys.gmenus.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class GmitemColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public GmitemColumns() {
		m_astrColumns = new String[2];
	}
	
	public GmitemColumns(String gmiGmcode, 
            String gmiGmicontent){
		m_astrColumns = new String[2];
		setGmigmcode(gmiGmcode);
		setGmigmicontent(gmiGmicontent);
	}

	public void setGmigmcode(String gmiGmcode) {
		this.setField(0, gmiGmcode);
	}

	public String getGmigmcode() {
		return this.getField(0);
	}

	public void setGmigmicontent(String gmiGmicontent) {
		this.setField(1, gmiGmicontent);
	}

	public String getGmigmicontent() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
