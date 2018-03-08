package kyle.fetcher.track.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TrackstatusColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TrackstatusColumns() {
		m_astrColumns = new String[4];
	}
	
	public TrackstatusColumns(String wbbtWbts_code, 
            String wbbtWpa_code, String wbbtWbbt_signforuser, 
            String wbbtWbbt_signfordate){
		m_astrColumns = new String[4];
		setWbbtwbts_code(wbbtWbts_code);
		setWbbtwpa_code(wbbtWpa_code);
		setWbbtwbbt_signforuser(wbbtWbbt_signforuser);
		setWbbtwbbt_signfordate(wbbtWbbt_signfordate);
	}

	public void setWbbtwbts_code(String wbbtWbts_code) {
		this.setField(0, wbbtWbts_code);
	}

	public String getWbbtwbts_code() {
		return this.getField(0);
	}

	public void setWbbtwpa_code(String wbbtWpa_code) {
		this.setField(1, wbbtWpa_code);
	}

	public String getWbbtwpa_code() {
		return this.getField(1);
	}

	public void setWbbtwbbt_signforuser(String wbbtWbbt_signforuser) {
		this.setField(2, wbbtWbbt_signforuser);
	}

	public String getWbbtwbbt_signforuser() {
		return this.getField(2);
	}

	public void setWbbtwbbt_signfordate(String wbbtWbbt_signfordate) {
		this.setField(3, wbbtWbbt_signfordate);
	}

	public String getWbbtwbbt_signfordate() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
