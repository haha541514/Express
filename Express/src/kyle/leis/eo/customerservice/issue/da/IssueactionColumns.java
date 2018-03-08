package kyle.leis.eo.customerservice.issue.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class IssueactionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IssueactionColumns() {
		m_astrColumns = new String[8];
	}
	
	public IssueactionColumns(Integer isacomp_idIsaid, 
            Long isacomp_idIsuid, Date isaIsacreatedate, 
            String isaIsacontent, Long copOpid, 
            String copOpname, String akAkcode, 
            String akAkname){
		m_astrColumns = new String[8];
		setIsacomp_idisaid(isacomp_idIsaid);
		setIsacomp_idisuid(isacomp_idIsuid);
		setIsaisacreatedate(isaIsacreatedate);
		setIsaisacontent(isaIsacontent);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setAkakcode(akAkcode);
		setAkakname(akAkname);
	}

	public void setIsacomp_idisaid(Integer isacomp_idIsaid) {
		this.setField(0, isacomp_idIsaid);
	}

	public String getIsacomp_idisaid() {
		return this.getField(0);
	}

	public void setIsacomp_idisuid(Long isacomp_idIsuid) {
		this.setField(1, isacomp_idIsuid);
	}

	public String getIsacomp_idisuid() {
		return this.getField(1);
	}

	public void setIsaisacreatedate(Date isaIsacreatedate) {
		this.setField(2, isaIsacreatedate);
	}

	public String getIsaisacreatedate() {
		return this.getField(2);
	}

	public void setIsaisacontent(String isaIsacontent) {
		this.setField(3, isaIsacontent);
	}

	public String getIsaisacontent() {
		return this.getField(3);
	}

	public void setCopopid(Long copOpid) {
		this.setField(4, copOpid);
	}

	public String getCopopid() {
		return this.getField(4);
	}

	public void setCopopname(String copOpname) {
		this.setField(5, copOpname);
	}

	public String getCopopname() {
		return this.getField(5);
	}

	public void setAkakcode(String akAkcode) {
		this.setField(6, akAkcode);
	}

	public String getAkakcode() {
		return this.getField(6);
	}

	public void setAkakname(String akAkname) {
		this.setField(7, akAkname);
	}

	public String getAkakname() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
