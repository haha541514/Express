package kyle.leis.fs.authoritys.user.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CocodeseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CocodeseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public CocodeseqColumns(String Cocodeseq){
		m_astrColumns = new String[1];
		setCocodeseq(Cocodeseq);
	}

	public void setCocodeseq(String Cocodeseq) {
		this.setField(0, Cocodeseq);
	}

	public String getCocodeseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
