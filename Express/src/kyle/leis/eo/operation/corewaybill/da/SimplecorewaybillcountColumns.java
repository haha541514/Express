package kyle.leis.eo.operation.corewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SimplecorewaybillcountColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplecorewaybillcountColumns() {
		m_astrColumns = new String[1];
	}
	
	public SimplecorewaybillcountColumns(String Libcount){
		m_astrColumns = new String[1];
		setLibcount(Libcount);
	}

	public void setLibcount(String Libcount) {
		this.setField(0, Libcount);
	}

	public String getLibcount() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
