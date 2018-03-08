package kyle.leis.es.businessrule.manifest.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestexportformatColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifestexportformatColumns() {
		m_astrColumns = new String[13];
	}
	
	public ManifestexportformatColumns(Long mefMefcode, 
            String mefMefname, String mefMefename, 
            String mefMeftemplatepath, int mefMefbeginrow, 
            int mefMefbegincolumn, Date mefMefcreatedate, 
            Date mefMefmodifydate, String mefMefexportfilesuffix, 
            Long opcOpid, String opcOpname, 
            Long opmOpid, String opmOpname){
		m_astrColumns = new String[13];
		setMefmefcode(mefMefcode);
		setMefmefname(mefMefname);
		setMefmefename(mefMefename);
		setMefmeftemplatepath(mefMeftemplatepath);
		setMefmefbeginrow(mefMefbeginrow);
		setMefmefbegincolumn(mefMefbegincolumn);
		setMefmefcreatedate(mefMefcreatedate);
		setMefmefmodifydate(mefMefmodifydate);
		setMefmefexportfilesuffix(mefMefexportfilesuffix);
		setOpcopid(opcOpid);
		setOpcopname(opcOpname);
		setOpmopid(opmOpid);
		setOpmopname(opmOpname);
	}

	public void setMefmefcode(Long mefMefcode) {
		this.setField(0, mefMefcode);
	}

	public String getMefmefcode() {
		return this.getField(0);
	}

	public void setMefmefname(String mefMefname) {
		this.setField(1, mefMefname);
	}

	public String getMefmefname() {
		return this.getField(1);
	}

	public void setMefmefename(String mefMefename) {
		this.setField(2, mefMefename);
	}

	public String getMefmefename() {
		return this.getField(2);
	}

	public void setMefmeftemplatepath(String mefMeftemplatepath) {
		this.setField(3, mefMeftemplatepath);
	}

	public String getMefmeftemplatepath() {
		return this.getField(3);
	}

	public void setMefmefbeginrow(int mefMefbeginrow) {
		this.setField(4, mefMefbeginrow);
	}

	public String getMefmefbeginrow() {
		return this.getField(4);
	}

	public void setMefmefbegincolumn(int mefMefbegincolumn) {
		this.setField(5, mefMefbegincolumn);
	}

	public String getMefmefbegincolumn() {
		return this.getField(5);
	}

	public void setMefmefcreatedate(Date mefMefcreatedate) {
		this.setField(6, mefMefcreatedate);
	}

	public String getMefmefcreatedate() {
		return this.getField(6);
	}

	public void setMefmefmodifydate(Date mefMefmodifydate) {
		this.setField(7, mefMefmodifydate);
	}

	public String getMefmefmodifydate() {
		return this.getField(7);
	}

	public void setMefmefexportfilesuffix(String mefMefexportfilesuffix) {
		this.setField(8, mefMefexportfilesuffix);
	}

	public String getMefmefexportfilesuffix() {
		return this.getField(8);
	}

	public void setOpcopid(Long opcOpid) {
		this.setField(9, opcOpid);
	}

	public String getOpcopid() {
		return this.getField(9);
	}

	public void setOpcopname(String opcOpname) {
		this.setField(10, opcOpname);
	}

	public String getOpcopname() {
		return this.getField(10);
	}

	public void setOpmopid(Long opmOpid) {
		this.setField(11, opmOpid);
	}

	public String getOpmopid() {
		return this.getField(11);
	}

	public void setOpmopname(String opmOpname) {
		this.setField(12, opmOpname);
	}

	public String getOpmopname() {
		return this.getField(12);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
