package kyle.leis.es.bulletin.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BulletinseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BulletinseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public BulletinseqColumns(String Buildewbseq){
		m_astrColumns = new String[1];
		setBuildewbseq(Buildewbseq);
	}

	public void setBuildewbseq(String Buildewbseq) {
		this.setField(0, Buildewbseq);
	}

	public String getBuildewbseq() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
