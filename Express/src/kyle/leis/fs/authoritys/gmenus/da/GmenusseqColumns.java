package kyle.leis.fs.authoritys.gmenus.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class GmenusseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public GmenusseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public GmenusseqColumns(String Gmenusseq){
		m_astrColumns = new String[1];
		setGmenusseq(Gmenusseq);
	}

	public void setGmenusseq(String Gmenusseq) {
		this.setField(0, Gmenusseq);
	}

	public String getGmenusseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
