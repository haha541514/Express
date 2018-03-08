package kyle.leis.es.price.incidentalprice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class IncidentalvaluebaseColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IncidentalvaluebaseColumns() {
		
	}
	
	public IncidentalvaluebaseColumns(Long ivbcomp_idEpcode, 
            Integer ivbcomp_idIpvid, String ivbcomp_idFkcode, 
            String fkFkcode, String fkFkname){
		m_astrColumns = new String[5];
		setIvbcomp_idepcode(ivbcomp_idEpcode);
		setIvbcomp_idipvid(ivbcomp_idIpvid);
		setIvbcomp_idfkcode(ivbcomp_idFkcode);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
	}

	public void setIvbcomp_idepcode(Long ivbcomp_idEpcode) {
		this.setField(0, ivbcomp_idEpcode);
	}

	public String getIvbcomp_idepcode() {
		return this.getField(0);
	}

	public void setIvbcomp_idipvid(Integer ivbcomp_idIpvid) {
		this.setField(1, ivbcomp_idIpvid);
	}

	public String getIvbcomp_idipvid() {
		return this.getField(1);
	}

	public void setIvbcomp_idfkcode(String ivbcomp_idFkcode) {
		this.setField(2, ivbcomp_idFkcode);
	}

	public String getIvbcomp_idfkcode() {
		return this.getField(2);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(3, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(3);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(4, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
