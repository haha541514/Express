package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SmsidseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SmsidseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public SmsidseqColumns(String Smsidseq){
		m_astrColumns = new String[1];
		setSmsidseq(Smsidseq);
	}

	public void setSmsidseq(String Smsidseq) {
		this.setField(0, Smsidseq);
	}

	public String getSmsidseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
