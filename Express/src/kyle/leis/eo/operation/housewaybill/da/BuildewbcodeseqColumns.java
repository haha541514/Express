package kyle.leis.eo.operation.housewaybill.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class BuildewbcodeseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BuildewbcodeseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public BuildewbcodeseqColumns(String Buildewbseq){
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
