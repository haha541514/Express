package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptoperationtacheColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptoperationtacheColumns() {
		m_astrColumns = new String[3];
	}
	
	public OptoperationtacheColumns(Long optotcomp_idBrid, 
            String otOtcode, String otOtname){
		m_astrColumns = new String[3];
		setOptotcomp_idbrid(optotcomp_idBrid);
		setOtotcode(otOtcode);
		setOtotname(otOtname);
	}

	public void setOptotcomp_idbrid(Long optotcomp_idBrid) {
		this.setField(0, optotcomp_idBrid);
	}

	public String getOptotcomp_idbrid() {
		return this.getField(0);
	}

	public void setOtotcode(String otOtcode) {
		this.setField(1, otOtcode);
	}

	public String getOtotcode() {
		return this.getField(1);
	}

	public void setOtotname(String otOtname) {
		this.setField(2, otOtname);
	}

	public String getOtotname() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
