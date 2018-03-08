package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SumserverwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SumserverwaybillColumns() {
		m_astrColumns = new String[1];
	}
	
	public SumserverwaybillColumns(String Totalcharge){
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
