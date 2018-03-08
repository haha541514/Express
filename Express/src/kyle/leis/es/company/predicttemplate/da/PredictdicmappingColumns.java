package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredictdicmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredictdicmappingColumns() {
		m_astrColumns = new String[7];
	}
	
	public PredictdicmappingColumns(Long podmPodmid, 
            String podmPodmoriginvalue, String podmPodmstandardvalue, 
            String dmkDmkcode, String dmkDmkname, 
            Long potPotid, String potPotname){
		m_astrColumns = new String[7];
		setPodmpodmid(podmPodmid);
		setPodmpodmoriginvalue(podmPodmoriginvalue);
		setPodmpodmstandardvalue(podmPodmstandardvalue);
		setDmkdmkcode(dmkDmkcode);
		setDmkdmkname(dmkDmkname);
		setPotpotid(potPotid);
		setPotpotname(potPotname);
	}

	public void setPodmpodmid(Long podmPodmid) {
		this.setField(0, podmPodmid);
	}

	public String getPodmpodmid() {
		return this.getField(0);
	}

	public void setPodmpodmoriginvalue(String podmPodmoriginvalue) {
		this.setField(1, podmPodmoriginvalue);
	}

	public String getPodmpodmoriginvalue() {
		return this.getField(1);
	}

	public void setPodmpodmstandardvalue(String podmPodmstandardvalue) {
		this.setField(2, podmPodmstandardvalue);
	}

	public String getPodmpodmstandardvalue() {
		return this.getField(2);
	}

	public void setDmkdmkcode(String dmkDmkcode) {
		this.setField(3, dmkDmkcode);
	}

	public String getDmkdmkcode() {
		return this.getField(3);
	}

	public void setDmkdmkname(String dmkDmkname) {
		this.setField(4, dmkDmkname);
	}

	public String getDmkdmkname() {
		return this.getField(4);
	}

	public void setPotpotid(Long potPotid) {
		this.setField(5, potPotid);
	}

	public String getPotpotid() {
		return this.getField(5);
	}

	public void setPotpotname(String potPotname) {
		this.setField(6, potPotname);
	}

	public String getPotpotname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
