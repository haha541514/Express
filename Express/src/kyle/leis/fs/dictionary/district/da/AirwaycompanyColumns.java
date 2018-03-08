package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class AirwaycompanyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AirwaycompanyColumns() {
		m_astrColumns = new String[5];
	}
	
	public AirwaycompanyColumns(String acAc_id, 
            String acAc_hubcode, String acAc_threehubcode, 
            String acAc_ename, String acAc_cname){
		m_astrColumns = new String[5];
		setAcac_id(acAc_id);
		setAcac_hubcode(acAc_hubcode);
		setAcac_threehubcode(acAc_threehubcode);
		setAcac_ename(acAc_ename);
		setAcac_cname(acAc_cname);
	}

	public void setAcac_id(String acAc_id) {
		this.setField(0, acAc_id);
	}

	public String getAcac_id() {
		return this.getField(0);
	}

	public void setAcac_hubcode(String acAc_hubcode) {
		this.setField(1, acAc_hubcode);
	}

	public String getAcac_hubcode() {
		return this.getField(1);
	}

	public void setAcac_threehubcode(String acAc_threehubcode) {
		this.setField(2, acAc_threehubcode);
	}

	public String getAcac_threehubcode() {
		return this.getField(2);
	}

	public void setAcac_ename(String acAc_ename) {
		this.setField(3, acAc_ename);
	}

	public String getAcac_ename() {
		return this.getField(3);
	}

	public void setAcac_cname(String acAc_cname) {
		this.setField(4, acAc_cname);
	}

	public String getAcac_cname() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
