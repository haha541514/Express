package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CusoperationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CusoperationColumns() {
		m_astrColumns = new String[2];
	}
	
	public CusoperationColumns(String cmCocode, 
            String otOtcode){
		m_astrColumns = new String[2];
		setCmcocode(cmCocode);
		setOtotcode(otOtcode);
	}

	public void setCmcocode(String cmCocode) {
		this.setField(0, cmCocode);
	}

	public String getCmcocode() {
		return this.getField(0);
	}

	public void setOtotcode(String otOtcode) {
		this.setField(1, otOtcode);
	}

	public String getOtotcode() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
