package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class VolumeweightColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public VolumeweightColumns() {
		m_astrColumns = new String[2];
	}
	
	public VolumeweightColumns(String cwCw_code, 
            String Volumeweight){
		m_astrColumns = new String[2];
		setCwcw_code(cwCw_code);
		setVolumeweight(Volumeweight);
	}

	public void setCwcw_code(String cwCw_code) {
		this.setField(0, cwCw_code);
	}

	public String getCwcw_code() {
		return this.getField(0);
	}

	public void setVolumeweight(String Volumeweight) {
		this.setField(1, Volumeweight);
	}

	public String getVolumeweight() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
