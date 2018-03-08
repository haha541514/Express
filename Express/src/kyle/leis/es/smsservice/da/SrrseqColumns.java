package kyle.leis.es.smsservice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SrrseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SrrseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public SrrseqColumns(String Srrseq){
		m_astrColumns = new String[1];
		setSrrseq(Srrseq);
	}

	public void setSrrseq(String Srrseq) {
		this.setField(0, Srrseq);
	}

	public String getSrrseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
