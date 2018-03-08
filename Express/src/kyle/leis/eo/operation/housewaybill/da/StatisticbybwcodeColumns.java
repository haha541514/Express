package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class StatisticbybwcodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public StatisticbybwcodeColumns() {
		m_astrColumns = new String[1];
	}
	
	public StatisticbybwcodeColumns(String Pieces){
		m_astrColumns = new String[1];
		setPieces(Pieces);
	}

	public void setPieces(String Pieces) {
		this.setField(0, Pieces);
	}

	public String getPieces() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
