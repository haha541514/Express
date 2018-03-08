package kyle.leis.es.businessrule.operationprompt.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class OptcorporationColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public OptcorporationColumns() {
		m_astrColumns = new String[6];
	}
	
	public OptcorporationColumns(Long optcocomp_idBrid, 
            String optcoOptccssign, String coCocode, 
            String coConame, String coCosname, 
            String coColabelcode){
		m_astrColumns = new String[6];
		setOptcocomp_idbrid(optcocomp_idBrid);
		setOptcooptccssign(optcoOptccssign);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setCocolabelcode(coColabelcode);
	}

	public void setOptcocomp_idbrid(Long optcocomp_idBrid) {
		this.setField(0, optcocomp_idBrid);
	}

	public String getOptcocomp_idbrid() {
		return this.getField(0);
	}

	public void setOptcooptccssign(String optcoOptccssign) {
		this.setField(1, optcoOptccssign);
	}

	public String getOptcooptccssign() {
		return this.getField(1);
	}

	public void setCococode(String coCocode) {
		this.setField(2, coCocode);
	}

	public String getCococode() {
		return this.getField(2);
	}

	public void setCoconame(String coConame) {
		this.setField(3, coConame);
	}

	public String getCoconame() {
		return this.getField(3);
	}

	public void setCocosname(String coCosname) {
		this.setField(4, coCosname);
	}

	public String getCocosname() {
		return this.getField(4);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(5, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
