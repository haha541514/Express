package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PredicttemplatevalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredicttemplatevalueColumns() {
		m_astrColumns = new String[13];
	}
	
	public PredicttemplatevalueColumns(Integer potvcomp_idPotvid, 
            Long potvcomp_idPotid, String potvPotvcolumnname, 
            String dmkDmkcode, String dmkDmkname, 
            String cmtCmtcode, String cmtCmtname, 
            String tcTcid, Integer tcTccolumnindex, 
            String tcTccolumnname, String tcTccolumnename, 
            String tcTccolumngroup, String tcTccolumntype){
		m_astrColumns = new String[13];
		setPotvcomp_idpotvid(potvcomp_idPotvid);
		setPotvcomp_idpotid(potvcomp_idPotid);
		setPotvpotvcolumnname(potvPotvcolumnname);
		setDmkdmkcode(dmkDmkcode);
		setDmkdmkname(dmkDmkname);
		setCmtcmtcode(cmtCmtcode);
		setCmtcmtname(cmtCmtname);
		setTctcid(tcTcid);
		setTctccolumnindex(tcTccolumnindex);
		setTctccolumnname(tcTccolumnname);
		setTctccolumnename(tcTccolumnename);
		setTctccolumngroup(tcTccolumngroup);
		setTctccolumntype(tcTccolumntype);
	}

	public void setPotvcomp_idpotvid(Integer potvcomp_idPotvid) {
		this.setField(0, potvcomp_idPotvid);
	}

	public String getPotvcomp_idpotvid() {
		return this.getField(0);
	}

	public void setPotvcomp_idpotid(Long potvcomp_idPotid) {
		this.setField(1, potvcomp_idPotid);
	}

	public String getPotvcomp_idpotid() {
		return this.getField(1);
	}

	public void setPotvpotvcolumnname(String potvPotvcolumnname) {
		this.setField(2, potvPotvcolumnname);
	}

	public String getPotvpotvcolumnname() {
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

	public void setCmtcmtcode(String cmtCmtcode) {
		this.setField(5, cmtCmtcode);
	}

	public String getCmtcmtcode() {
		return this.getField(5);
	}

	public void setCmtcmtname(String cmtCmtname) {
		this.setField(6, cmtCmtname);
	}

	public String getCmtcmtname() {
		return this.getField(6);
	}

	public void setTctcid(String tcTcid) {
		this.setField(7, tcTcid);
	}

	public String getTctcid() {
		return this.getField(7);
	}

	public void setTctccolumnindex(Integer tcTccolumnindex) {
		this.setField(8, tcTccolumnindex);
	}

	public String getTctccolumnindex() {
		return this.getField(8);
	}

	public void setTctccolumnname(String tcTccolumnname) {
		this.setField(9, tcTccolumnname);
	}

	public String getTctccolumnname() {
		return this.getField(9);
	}

	public void setTctccolumnename(String tcTccolumnename) {
		this.setField(10, tcTccolumnename);
	}

	public String getTctccolumnename() {
		return this.getField(10);
	}

	public void setTctccolumngroup(String tcTccolumngroup) {
		this.setField(11, tcTccolumngroup);
	}

	public String getTctccolumngroup() {
		return this.getField(11);
	}

	public void setTctccolumntype(String tcTccolumntype) {
		this.setField(12, tcTccolumntype);
	}

	public String getTctccolumntype() {
		return this.getField(12);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
