package kyle.leis.eo.operation.specialtype.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SpecialtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SpecialtypeColumns() {
		m_astrColumns = new String[7];
	}
	
	public SpecialtypeColumns(Long wbstcomp_idCwcode, 
            String wbstcomp_idEstcode, Date wbstWbstcreatedate, 
            String wbstWbstremark, String estEstname, 
            Long copOpid, String copOpname){
		m_astrColumns = new String[7];
		setWbstcomp_idcwcode(wbstcomp_idCwcode);
		setWbstcomp_idestcode(wbstcomp_idEstcode);
		setWbstwbstcreatedate(wbstWbstcreatedate);
		setWbstwbstremark(wbstWbstremark);
		setEstestname(estEstname);
		setCopopid(copOpid);
		setCopopname(copOpname);
	}

	public void setWbstcomp_idcwcode(Long wbstcomp_idCwcode) {
		this.setField(0, wbstcomp_idCwcode);
	}

	public String getWbstcomp_idcwcode() {
		return this.getField(0);
	}

	public void setWbstcomp_idestcode(String wbstcomp_idEstcode) {
		this.setField(1, wbstcomp_idEstcode);
	}

	public String getWbstcomp_idestcode() {
		return this.getField(1);
	}

	public void setWbstwbstcreatedate(Date wbstWbstcreatedate) {
		this.setField(2, wbstWbstcreatedate);
	}

	public String getWbstwbstcreatedate() {
		return this.getField(2);
	}

	public void setWbstwbstremark(String wbstWbstremark) {
		this.setField(3, wbstWbstremark);
	}

	public String getWbstwbstremark() {
		return this.getField(3);
	}

	public void setEstestname(String estEstname) {
		this.setField(4, estEstname);
	}

	public String getEstestname() {
		return this.getField(4);
	}

	public void setCopopid(Long copOpid) {
		this.setField(5, copOpid);
	}

	public String getCopopid() {
		return this.getField(5);
	}

	public void setCopopname(String copOpname) {
		this.setField(6, copOpname);
	}

	public String getCopopname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
