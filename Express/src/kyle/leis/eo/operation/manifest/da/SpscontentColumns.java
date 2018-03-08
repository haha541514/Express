package kyle.leis.eo.operation.manifest.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SpscontentColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;
	
	public SpscontentColumns(){
		m_astrColumns = new String[1];
	}
	
	public SpscontentColumns(String strSpsContent){
		m_astrColumns = new String[1];
		setSpsContent(strSpsContent);
	}

	public void setSpsContent(String strSpsContent) {
		this.setField(0, strSpsContent);
	}

	public String getSpsContent() {
		return this.getField(0);
	}


	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
