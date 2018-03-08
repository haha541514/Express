package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptexpressspecialtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptexpressspecialtypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public OptexpressspecialtypeColumns(Long optestcomp_idBrid, 
            String estEstcode, String estEstname){
		m_astrColumns = new String[3];
		setOptestcomp_idbrid(optestcomp_idBrid);
		setEstestcode(estEstcode);
		setEstestname(estEstname);
	}

	public void setOptestcomp_idbrid(Long optestcomp_idBrid) {
		this.setField(0, optestcomp_idBrid);
	}

	public String getOptestcomp_idbrid() {
		return this.getField(0);
	}

	public void setEstestcode(String estEstcode) {
		this.setField(1, estEstcode);
	}

	public String getEstestcode() {
		return this.getField(1);
	}

	public void setEstestname(String estEstname) {
		this.setField(2, estEstname);
	}

	public String getEstestname() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
