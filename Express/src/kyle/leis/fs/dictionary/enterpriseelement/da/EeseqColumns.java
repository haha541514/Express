package kyle.leis.fs.dictionary.enterpriseelement.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class EeseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public EeseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public EeseqColumns(String Eeseq){
		m_astrColumns = new String[1];
		setEeseq(Eeseq);
	}

	public void setEeseq(String Eeseq) {
		this.setField(0, Eeseq);
	}

	public String getEeseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
