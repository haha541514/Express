package kyle.leis.fs.authoritys.user.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class UrseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UrseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public UrseqColumns(String Urseq){
		m_astrColumns = new String[1];
		setUrseq(Urseq);
	}

	public void setUrseq(String Urseq) {
		this.setField(0, Urseq);
	}

	public String getUrseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
