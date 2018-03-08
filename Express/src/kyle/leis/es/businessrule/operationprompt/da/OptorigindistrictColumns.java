package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptorigindistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptorigindistrictColumns() {
		m_astrColumns = new String[3];
	}
	
	public OptorigindistrictColumns(Long optodtcomp_idBrid, 
            String odtDtcode, String odtDtname){
		m_astrColumns = new String[3];
		setOptodtcomp_idbrid(optodtcomp_idBrid);
		setOdtdtcode(odtDtcode);
		setOdtdtname(odtDtname);
	}

	public void setOptodtcomp_idbrid(Long optodtcomp_idBrid) {
		this.setField(0, optodtcomp_idBrid);
	}

	public String getOptodtcomp_idbrid() {
		return this.getField(0);
	}

	public void setOdtdtcode(String odtDtcode) {
		this.setField(1, odtDtcode);
	}

	public String getOdtdtcode() {
		return this.getField(1);
	}

	public void setOdtdtname(String odtDtname) {
		this.setField(2, odtDtname);
	}

	public String getOdtdtname() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
