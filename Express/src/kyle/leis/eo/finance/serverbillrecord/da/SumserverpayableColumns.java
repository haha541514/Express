package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumserverpayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumserverpayableColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumserverpayableColumns(String Totalcharge){
		m_astrColumns = new String[1];
		setTotalcharge(Totalcharge);
	}

	public void setTotalcharge(String Totalcharge) {
		this.setField(0, Totalcharge);
	}

	public String getTotalcharge() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
