package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ListserverwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ListserverwaybillColumns() {
		m_astrColumns = new String[1];
	}
	
	public ListserverwaybillColumns(String swbSwb_code){
		m_astrColumns = new String[1];
		setSwbswb_code(swbSwb_code);
	}

	public void setSwbswb_code(String swbSwb_code) {
		this.setField(0, swbSwb_code);
	}

	public String getSwbswb_code() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
