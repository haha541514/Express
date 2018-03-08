package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptdeparturedistrictColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptdeparturedistrictColumns() {
		m_astrColumns = new String[3];
	}
	
	public OptdeparturedistrictColumns(Long optddtcomp_idBrid, 
            String ddtDtcode, String ddtDtname){
		m_astrColumns = new String[3];
		setOptddtcomp_idbrid(optddtcomp_idBrid);
		setDdtdtcode(ddtDtcode);
		setDdtdtname(ddtDtname);
	}

	public void setOptddtcomp_idbrid(Long optddtcomp_idBrid) {
		this.setField(0, optddtcomp_idBrid);
	}

	public String getOptddtcomp_idbrid() {
		return this.getField(0);
	}

	public void setDdtdtcode(String ddtDtcode) {
		this.setField(1, ddtDtcode);
	}

	public String getDdtdtcode() {
		return this.getField(1);
	}

	public void setDdtdtname(String ddtDtname) {
		this.setField(2, ddtDtname);
	}

	public String getDdtdtname() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
