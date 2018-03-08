package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CorporationproductruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CorporationproductruleColumns() {
		m_astrColumns = new String[8];
	}
	
	public CorporationproductruleColumns(Long cswbcomp_idBrid, 
            String cswbcomp_idCocode, String coCocode, 
            String coColabelcode, String coConame, 
            String coCoename, String coCosname, 
            String coCosename){
		m_astrColumns = new String[8];
		setCswbcomp_idbrid(cswbcomp_idBrid);
		setCswbcomp_idcocode(cswbcomp_idCocode);
		setCococode(coCocode);
		setCocolabelcode(coColabelcode);
		setCoconame(coConame);
		setCocoename(coCoename);
		setCocosname(coCosname);
		setCocosename(coCosename);
	}

	public void setCswbcomp_idbrid(Long cswbcomp_idBrid) {
		this.setField(0, cswbcomp_idBrid);
	}

	public String getCswbcomp_idbrid() {
		return this.getField(0);
	}

	public void setCswbcomp_idcocode(String cswbcomp_idCocode) {
		this.setField(1, cswbcomp_idCocode);
	}

	public String getCswbcomp_idcocode() {
		return this.getField(1);
	}

	public void setCococode(String coCocode) {
		this.setField(2, coCocode);
	}

	public String getCococode() {
		return this.getField(2);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(3, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(3);
	}

	public void setCoconame(String coConame) {
		this.setField(4, coConame);
	}

	public String getCoconame() {
		return this.getField(4);
	}

	public void setCocoename(String coCoename) {
		this.setField(5, coCoename);
	}

	public String getCocoename() {
		return this.getField(5);
	}

	public void setCocosname(String coCosname) {
		this.setField(6, coCosname);
	}

	public String getCocosname() {
		return this.getField(6);
	}

	public void setCocosename(String coCosename) {
		this.setField(7, coCosename);
	}

	public String getCocosename() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
