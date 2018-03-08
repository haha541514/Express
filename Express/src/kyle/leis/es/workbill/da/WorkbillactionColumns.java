package kyle.leis.es.workbill.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WorkbillactionColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WorkbillactionColumns() {
		m_astrColumns = new String[8];
	}
	
	public WorkbillactionColumns(Integer wbaWbaid, 
            Date wbaWbacreatedate, String wbaWbacontent, 
            String opOpname, String akAkname, 
            Long opOpid, String akAkcode, 
            Long wbWbid){
		m_astrColumns = new String[8];
		setWbawbaid(wbaWbaid);
		setWbawbacreatedate(wbaWbacreatedate);
		setWbawbacontent(wbaWbacontent);
		setOpopname(opOpname);
		setAkakname(akAkname);
		setOpopid(opOpid);
		setAkakcode(akAkcode);
		setWbwbid(wbWbid);
	}

	public void setWbawbaid(Integer wbaWbaid) {
		this.setField(0, wbaWbaid);
	}

	public String getWbawbaid() {
		return this.getField(0);
	}

	public void setWbawbacreatedate(Date wbaWbacreatedate) {
		this.setField(1, wbaWbacreatedate);
	}

	public String getWbawbacreatedate() {
		return this.getField(1);
	}

	public void setWbawbacontent(String wbaWbacontent) {
		this.setField(2, wbaWbacontent);
	}

	public String getWbawbacontent() {
		return this.getField(2);
	}

	public void setOpopname(String opOpname) {
		this.setField(3, opOpname);
	}

	public String getOpopname() {
		return this.getField(3);
	}

	public void setAkakname(String akAkname) {
		this.setField(4, akAkname);
	}

	public String getAkakname() {
		return this.getField(4);
	}

	public void setOpopid(Long opOpid) {
		this.setField(5, opOpid);
	}

	public String getOpopid() {
		return this.getField(5);
	}

	public void setAkakcode(String akAkcode) {
		this.setField(6, akAkcode);
	}

	public String getAkakcode() {
		return this.getField(6);
	}

	public void setWbwbid(Long wbWbid) {
		this.setField(7, wbWbid);
	}

	public String getWbwbid() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
