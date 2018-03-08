package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ChanneltransferweightColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChanneltransferweightColumns() {
		m_astrColumns = new String[1];
	}
	
	public ChanneltransferweightColumns(String Sumserverchargeweight){
		m_astrColumns = new String[1];
		setSumserverchargeweight(Sumserverchargeweight);
	}

	public void setSumserverchargeweight(String Sumserverchargeweight) {
		this.setField(0, Sumserverchargeweight);
	}

	public String getSumserverchargeweight() {
		return this.getField(0);
	}
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
