package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TrasporttoolColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TrasporttoolColumns() {
		m_astrColumns = new String[7];
	}
	
	public TrasporttoolColumns(String ttTtcode, 
            String ttTtlabelcode, String ttTtdeparturetime, 
            String ttTtarrivaltime, String ttTtdrivername, 
            String ttchnChnsname, String ttcoCosname){
		m_astrColumns = new String[7];
		setTtttcode(ttTtcode);
		setTtttlabelcode(ttTtlabelcode);
		setTtttdeparturetime(ttTtdeparturetime);
		setTtttarrivaltime(ttTtarrivaltime);
		setTtttdrivername(ttTtdrivername);
		setTtchnchnsname(ttchnChnsname);
		setTtcocosname(ttcoCosname);
	}

	public void setTtttcode(String ttTtcode) {
		this.setField(0, ttTtcode);
	}

	public String getTtttcode() {
		return this.getField(0);
	}

	public void setTtttlabelcode(String ttTtlabelcode) {
		this.setField(1, ttTtlabelcode);
	}

	public String getTtttlabelcode() {
		return this.getField(1);
	}

	public void setTtttdeparturetime(String ttTtdeparturetime) {
		this.setField(2, ttTtdeparturetime);
	}

	public String getTtttdeparturetime() {
		return this.getField(2);
	}

	public void setTtttarrivaltime(String ttTtarrivaltime) {
		this.setField(3, ttTtarrivaltime);
	}

	public String getTtttarrivaltime() {
		return this.getField(3);
	}

	public void setTtttdrivername(String ttTtdrivername) {
		this.setField(4, ttTtdrivername);
	}

	public String getTtttdrivername() {
		return this.getField(4);
	}

	public void setTtchnchnsname(String ttchnChnsname) {
		this.setField(5, ttchnChnsname);
	}

	public String getTtchnchnsname() {
		return this.getField(5);
	}

	public void setTtcocosname(String ttcoCosname) {
		this.setField(6, ttcoCosname);
	}

	public String getTtcocosname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
