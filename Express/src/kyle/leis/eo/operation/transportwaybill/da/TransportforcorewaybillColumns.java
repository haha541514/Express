package kyle.leis.eo.operation.transportwaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TransportforcorewaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TransportforcorewaybillColumns() {
		m_astrColumns = new String[6];
	}
	
	public TransportforcorewaybillColumns(String twbvTwbvbaglabelcode, 
            Long bwBwcode, String twbTwblabelcode, 
            String ttTtlabelcode, String ttTtdrivername,
            Long strTwbId){
		m_astrColumns = new String[6];
		setTwbvtwbvbaglabelcode(twbvTwbvbaglabelcode);
		setBwbwcode(bwBwcode);
		setTwbtwblabelcode(twbTwblabelcode);
		setTtttlabelcode(ttTtlabelcode);
		setTtttdrivername(ttTtdrivername);
		setTwbId(String.valueOf(strTwbId));
	}

	public void setTwbvtwbvbaglabelcode(String twbvTwbvbaglabelcode) {
		this.setField(0, twbvTwbvbaglabelcode);
	}

	public String getTwbvtwbvbaglabelcode() {
		return this.getField(0);
	}

	public void setBwbwcode(Long bwBwcode) {
		this.setField(1, bwBwcode);
	}

	public String getBwbwcode() {
		return this.getField(1);
	}

	public void setTwbtwblabelcode(String twbTwblabelcode) {
		this.setField(2, twbTwblabelcode);
	}

	public String getTwbtwblabelcode() {
		return this.getField(2);
	}

	public void setTtttlabelcode(String ttTtlabelcode) {
		this.setField(3, ttTtlabelcode);
	}

	public String getTtttlabelcode() {
		return this.getField(3);
	}

	public void setTtttdrivername(String ttTtdrivername) {
		this.setField(4, ttTtdrivername);
	}

	public String getTtttdrivername() {
		return this.getField(4);
	}
	
	public void setTwbId(String strTwbId) {
		this.setField(5, strTwbId);
	}

	public String getTwbId() {
		return this.getField(5);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
