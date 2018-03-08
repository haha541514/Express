package kyle.leis.es.company.companys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorporationseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorporationseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public CorporationseqColumns(String Corporationseq){
		m_astrColumns = new String[1];
		setCorporationseq(Corporationseq);
	}

	public void setCorporationseq(String Corporationseq) {
		this.setField(0, Corporationseq);
	}

	public String getCorporationseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
