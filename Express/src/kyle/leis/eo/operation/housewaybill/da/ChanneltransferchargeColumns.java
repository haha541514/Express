package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChanneltransferchargeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChanneltransferchargeColumns() {
		m_astrColumns = new String[1];
	}
	
	public ChanneltransferchargeColumns( String Sumservercharge){
		m_astrColumns = new String[1];
		setSumservercharge(Sumservercharge);
	}

	public void setSumservercharge(String Sumservercharge) {
		this.setField(0, Sumservercharge);
	}

	public String getSumservercharge() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
