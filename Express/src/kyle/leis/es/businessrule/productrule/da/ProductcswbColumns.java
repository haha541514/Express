package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ProductcswbColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ProductcswbColumns() {
		m_astrColumns = new String[4];
	}
	
	public ProductcswbColumns(Long prBrid, 
            String prPrallcussignoutbyoriginwbsign, Long cswbcomp_idBrid, 
            String cswbcomp_idCocode){
		m_astrColumns = new String[4];
		setPrbrid(prBrid);
		setPrprallcussignoutbyoriginwbsign(prPrallcussignoutbyoriginwbsign);
		setCswbcomp_idbrid(cswbcomp_idBrid);
		setCswbcomp_idcocode(cswbcomp_idCocode);
	}

	public void setPrbrid(Long prBrid) {
		this.setField(0, prBrid);
	}

	public String getPrbrid() {
		return this.getField(0);
	}

	public void setPrprallcussignoutbyoriginwbsign(String prPrallcussignoutbyoriginwbsign) {
		this.setField(1, prPrallcussignoutbyoriginwbsign);
	}

	public String getPrprallcussignoutbyoriginwbsign() {
		return this.getField(1);
	}

	public void setCswbcomp_idbrid(Long cswbcomp_idBrid) {
		this.setField(2, cswbcomp_idBrid);
	}

	public String getCswbcomp_idbrid() {
		return this.getField(2);
	}

	public void setCswbcomp_idcocode(String cswbcomp_idCocode) {
		this.setField(3, cswbcomp_idCocode);
	}

	public String getCswbcomp_idcocode() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
