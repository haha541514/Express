package kyle.leis.eo.finance.serverbillrecord.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CountserverpayableColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CountserverpayableColumns() {
		m_astrColumns = new String[1];
	}
	
	public CountserverpayableColumns(String count){
		m_astrColumns = new String[1];
		setCount(count);
	}

	public void setCount(String  count) {
		this.setField(0,count);
	}

	public String getCount() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
