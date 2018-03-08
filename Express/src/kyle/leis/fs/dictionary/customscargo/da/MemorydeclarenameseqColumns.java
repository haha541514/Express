package kyle.leis.fs.dictionary.customscargo.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class MemorydeclarenameseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public MemorydeclarenameseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public MemorydeclarenameseqColumns(String Memorydeclarenameseq){
		m_astrColumns = new String[1];
		setMemorydeclarenameseq(Memorydeclarenameseq);
	}

	public void setMemorydeclarenameseq(String Memorydeclarenameseq) {
		this.setField(0, Memorydeclarenameseq);
	}

	public String getMemorydeclarenameseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
