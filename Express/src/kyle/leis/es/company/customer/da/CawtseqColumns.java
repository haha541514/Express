package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CawtseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CawtseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public CawtseqColumns(String Cawtseq){
		m_astrColumns = new String[1];
		setCawtseq(Cawtseq);
	}

	public void setCawtseq(String Cawtseq) {
		this.setField(0, Cawtseq);
	}

	public String getCawtseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
