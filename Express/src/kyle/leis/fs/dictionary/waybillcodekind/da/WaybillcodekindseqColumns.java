package kyle.leis.fs.dictionary.waybillcodekind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class WaybillcodekindseqColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WaybillcodekindseqColumns() {
		m_astrColumns = new String[1];
	}
	
	public WaybillcodekindseqColumns(String bckcode){
		m_astrColumns = new String[1];
		setBckcode(bckcode);
	}

	public void setBckcode(String bckcode) {
		this.setField(0,bckcode);
	}

	public String getBckcode() {
		return this.getField(0);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
