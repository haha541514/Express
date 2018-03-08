package kyle.leis.es.price.freightprice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SurchargevaluebaseColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SurchargevaluebaseColumns() {
		
	}
	
	public SurchargevaluebaseColumns(String svbcomp_idFkcode, 
            Long svbcomp_idEpcode, Integer svbcomp_idSvid, 
            String fkFkname){
		m_astrColumns = new String[4];
		setSvbcomp_idfkcode(svbcomp_idFkcode);
		setSvbcomp_idepcode(svbcomp_idEpcode);
		setSvbcomp_idsvid(svbcomp_idSvid);
		setFkfkname(fkFkname);
	}

	public void setSvbcomp_idfkcode(String svbcomp_idFkcode) {
		this.setField(0, svbcomp_idFkcode);
	}

	public String getSvbcomp_idfkcode() {
		return this.getField(0);
	}

	public void setSvbcomp_idepcode(Long svbcomp_idEpcode) {
		this.setField(1, svbcomp_idEpcode);
	}

	public String getSvbcomp_idepcode() {
		return this.getField(1);
	}

	public void setSvbcomp_idsvid(Integer svbcomp_idSvid) {
		this.setField(2, svbcomp_idSvid);
	}

	public String getSvbcomp_idsvid() {
		return this.getField(2);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(3, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
