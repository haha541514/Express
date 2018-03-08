package kyle.leis.es.businessrule.manifest.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MefseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MefseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public MefseqColumns(String Smefseq){
		m_astrColumns = new String[1];
		setSmefseq(Smefseq);
	}

	public void setSmefseq(String Smefseq) {
		this.setField(0, Smefseq);
	}

	public String getSmefseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
