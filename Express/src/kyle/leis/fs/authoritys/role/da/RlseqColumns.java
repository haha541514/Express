package kyle.leis.fs.authoritys.role.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class RlseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public RlseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public RlseqColumns(String Rlseq){
		m_astrColumns = new String[1];
		setRlseq(Rlseq);
	}

	public void setRlseq(String Rlseq) {
		this.setField(0, Rlseq);
	}

	public String getRlseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
