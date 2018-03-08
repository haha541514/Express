package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class FinanceauditlogtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FinanceauditlogtypeColumns() {
		m_astrColumns = new String[2];
	}
	
	public FinanceauditlogtypeColumns(String faltFaltcode, 
            String faltFaltcontent){
		m_astrColumns = new String[2];
		setFaltfaltcode(faltFaltcode);
		setFaltfaltcontent(faltFaltcontent);
	}

	public void setFaltfaltcode(String faltFaltcode) {
		this.setField(0, faltFaltcode);
	}

	public String getFaltfaltcode() {
		return this.getField(0);
	}

	public void setFaltfaltcontent(String faltFaltcontent) {
		this.setField(1, faltFaltcontent);
	}

	public String getFaltfaltcontent() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
