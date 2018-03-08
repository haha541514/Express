package kyle.leis.fs.dictionary.enterpriseelement.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class EecityColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public EecityColumns() {
		m_astrColumns = new String[4];
	}
	
	public EecityColumns(String eectcomp_idEecode, 
            Integer eectcomp_idEecid, String stStcode, 
            String ctCtcode){
		m_astrColumns = new String[4];
		setEectcomp_ideecode(eectcomp_idEecode);
		setEectcomp_ideecid(eectcomp_idEecid);
		setStstcode(stStcode);
		setCtctcode(ctCtcode);
	}

	public void setEectcomp_ideecode(String eectcomp_idEecode) {
		this.setField(0, eectcomp_idEecode);
	}

	public String getEectcomp_ideecode() {
		return this.getField(0);
	}

	public void setEectcomp_ideecid(Integer eectcomp_idEecid) {
		this.setField(1, eectcomp_idEecid);
	}

	public String getEectcomp_ideecid() {
		return this.getField(1);
	}

	public void setStstcode(String stStcode) {
		this.setField(2, stStcode);
	}

	public String getStstcode() {
		return this.getField(2);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(3, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
