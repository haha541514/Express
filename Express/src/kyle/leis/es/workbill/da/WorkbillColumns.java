package kyle.leis.es.workbill.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WorkbillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WorkbillColumns() {
		m_astrColumns = new String[25];
	}
	
	public WorkbillColumns(Long wbWbid, 
            Date wbWbcreatedate, Date wbWbmodifydate, 
            Date wbWbplanstartdate, Date wbWbplanenddate, 
            Date wbWbactualstartdate, Date wbWbactualenddate, 
            String wbWbcontent, String wbWbheading, 
            String wbkWbkname, String prOpname, 
            String exOpname, String moOpname, 
            String suOpname, String crOpname, 
            String wblWblname, String wbsWbsname, 
            Long prOpid, Long exOpid, 
            Long moOpid, Long suOpid, 
            Long crOpid, String wbkWbkcode, 
            String wblWblcode, String wbsWbscode){
		m_astrColumns = new String[25];
		setWbwbid(wbWbid);
		setWbwbcreatedate(wbWbcreatedate);
		setWbwbmodifydate(wbWbmodifydate);
		setWbwbplanstartdate(wbWbplanstartdate);
		setWbwbplanenddate(wbWbplanenddate);
		setWbwbactualstartdate(wbWbactualstartdate);
		setWbwbactualenddate(wbWbactualenddate);
		setWbwbcontent(wbWbcontent);
		setWbwbheading(wbWbheading);
		setWbkwbkname(wbkWbkname);
		setPropname(prOpname);
		setExopname(exOpname);
		setMoopname(moOpname);
		setSuopname(suOpname);
		setCropname(crOpname);
		setWblwblname(wblWblname);
		setWbswbsname(wbsWbsname);
		setPropid(prOpid);
		setExopid(exOpid);
		setMoopid(moOpid);
		setSuopid(suOpid);
		setCropid(crOpid);
		setWbkwbkcode(wbkWbkcode);
		setWblwblcode(wblWblcode);
		setWbswbscode(wbsWbscode);
	}

	public void setWbwbid(Long wbWbid) {
		this.setField(0, wbWbid);
	}

	public String getWbwbid() {
		return this.getField(0);
	}

	public void setWbwbcreatedate(Date wbWbcreatedate) {
		this.setField(1, wbWbcreatedate);
	}

	public String getWbwbcreatedate() {
		return this.getField(1);
	}

	public void setWbwbmodifydate(Date wbWbmodifydate) {
		this.setField(2, wbWbmodifydate);
	}

	public String getWbwbmodifydate() {
		return this.getField(2);
	}

	public void setWbwbplanstartdate(Date wbWbplanstartdate) {
		this.setField(3, wbWbplanstartdate);
	}

	public String getWbwbplanstartdate() {
		return this.getField(3);
	}

	public void setWbwbplanenddate(Date wbWbplanenddate) {
		this.setField(4, wbWbplanenddate);
	}

	public String getWbwbplanenddate() {
		return this.getField(4);
	}

	public void setWbwbactualstartdate(Date wbWbactualstartdate) {
		this.setField(5, wbWbactualstartdate);
	}

	public String getWbwbactualstartdate() {
		return this.getField(5);
	}

	public void setWbwbactualenddate(Date wbWbactualenddate) {
		this.setField(6, wbWbactualenddate);
	}

	public String getWbwbactualenddate() {
		return this.getField(6);
	}

	public void setWbwbcontent(String wbWbcontent) {
		this.setField(7, wbWbcontent);
	}

	public String getWbwbcontent() {
		return this.getField(7);
	}

	public void setWbwbheading(String wbWbheading) {
		this.setField(8, wbWbheading);
	}

	public String getWbwbheading() {
		return this.getField(8);
	}

	public void setWbkwbkname(String wbkWbkname) {
		this.setField(9, wbkWbkname);
	}

	public String getWbkwbkname() {
		return this.getField(9);
	}

	public void setPropname(String prOpname) {
		this.setField(10, prOpname);
	}

	public String getPropname() {
		return this.getField(10);
	}

	public void setExopname(String exOpname) {
		this.setField(11, exOpname);
	}

	public String getExopname() {
		return this.getField(11);
	}

	public void setMoopname(String moOpname) {
		this.setField(12, moOpname);
	}

	public String getMoopname() {
		return this.getField(12);
	}

	public void setSuopname(String suOpname) {
		this.setField(13, suOpname);
	}

	public String getSuopname() {
		return this.getField(13);
	}

	public void setCropname(String crOpname) {
		this.setField(14, crOpname);
	}

	public String getCropname() {
		return this.getField(14);
	}

	public void setWblwblname(String wblWblname) {
		this.setField(15, wblWblname);
	}

	public String getWblwblname() {
		return this.getField(15);
	}

	public void setWbswbsname(String wbsWbsname) {
		this.setField(16, wbsWbsname);
	}

	public String getWbswbsname() {
		return this.getField(16);
	}

	public void setPropid(Long prOpid) {
		this.setField(17, prOpid);
	}

	public String getPropid() {
		return this.getField(17);
	}

	public void setExopid(Long exOpid) {
		this.setField(18, exOpid);
	}

	public String getExopid() {
		return this.getField(18);
	}

	public void setMoopid(Long moOpid) {
		this.setField(19, moOpid);
	}

	public String getMoopid() {
		return this.getField(19);
	}

	public void setSuopid(Long suOpid) {
		this.setField(20, suOpid);
	}

	public String getSuopid() {
		return this.getField(20);
	}

	public void setCropid(Long crOpid) {
		this.setField(21, crOpid);
	}

	public String getCropid() {
		return this.getField(21);
	}

	public void setWbkwbkcode(String wbkWbkcode) {
		this.setField(22, wbkWbkcode);
	}

	public String getWbkwbkcode() {
		return this.getField(22);
	}

	public void setWblwblcode(String wblWblcode) {
		this.setField(23, wblWblcode);
	}

	public String getWblwblcode() {
		return this.getField(23);
	}

	public void setWbswbscode(String wbsWbscode) {
		this.setField(24, wbsWbscode);
	}

	public String getWbswbscode() {
		return this.getField(24);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
