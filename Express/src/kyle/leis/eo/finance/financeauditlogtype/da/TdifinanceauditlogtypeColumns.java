package kyle.leis.eo.finance.financeauditlogtype.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TdifinanceauditlogtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TdifinanceauditlogtypeColumns() {
		m_astrColumns = new String[2];
	}
	
	public TdifinanceauditlogtypeColumns(String falFaltcode, 
            String falFaltcontent){
		m_astrColumns = new String[2];
		setFalfaltcode(falFaltcode);
		setFalfaltcontent(falFaltcontent);
	}

	public void setFalfaltcode(String falFaltcode) {
		this.setField(0, falFaltcode);
	}

	public String getFalfaltcode() {
		return this.getField(0);
	}

	public void setFalfaltcontent(String falFaltcontent) {
		this.setField(1, falFaltcontent);
	}

	public String getFalfaltcontent() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
