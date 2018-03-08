package kyle.leis.es.bulletin.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BulletinColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BulletinColumns() {
		m_astrColumns = new String[17];
	}
	
	public BulletinColumns(Long blBlid, 
            String blBlheading, String blBlcontent, 
            String blBlcontentindex, String blBllink, 
            Date blBlvaliddate, String blBlsignname, 
            Date blBlcreatedate, Date blBlmodifydate, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String bkBkcode, String bkBkname, 
            String bllBlcode, String bllBlname){
		m_astrColumns = new String[17];
		setBlblid(blBlid);
		setBlblheading(blBlheading);
		setBlblcontent(blBlcontent);
		setBlblcontentindex(blBlcontentindex);
		setBlbllink(blBllink);
		setBlblvaliddate(blBlvaliddate);
		setBlblsignname(blBlsignname);
		setBlblcreatedate(blBlcreatedate);
		setBlblmodifydate(blBlmodifydate);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setBkbkcode(bkBkcode);
		setBkbkname(bkBkname);
		setBllblcode(bllBlcode);
		setBllblname(bllBlname);
		//setBlblwechatsign(blBlwechatsign);
	}

	public void setBlblid(Long blBlid) {
		this.setField(0, blBlid);
	}

	public String getBlblid() {
		return this.getField(0);
	}

	public void setBlblheading(String blBlheading) {
		this.setField(1, blBlheading);
	}

	public String getBlblheading() {
		return this.getField(1);
	}

	public void setBlblcontent(String blBlcontent) {
		this.setField(2, blBlcontent);
	}

	public String getBlblcontent() {
		return this.getField(2);
	}

	public void setBlblcontentindex(String blBlcontentindex) {
		this.setField(3, blBlcontentindex);
	}

	public String getBlblcontentindex() {
		return this.getField(3);
	}

	public void setBlbllink(String blBllink) {
		this.setField(4, blBllink);
	}

	public String getBlbllink() {
		return this.getField(4);
	}

	public void setBlblvaliddate(Date blBlvaliddate) {
		this.setField(5, blBlvaliddate);
	}

	public String getBlblvaliddate() {
		return this.getField(5);
	}

	public void setBlblsignname(String blBlsignname) {
		this.setField(6, blBlsignname);
	}

	public String getBlblsignname() {
		return this.getField(6);
	}

	public void setBlblcreatedate(Date blBlcreatedate) {
		this.setField(7, blBlcreatedate);
	}

	public String getBlblcreatedate() {
		return this.getField(7);
	}

	public void setBlblmodifydate(Date blBlmodifydate) {
		this.setField(8, blBlmodifydate);
	}

	public String getBlblmodifydate() {
		return this.getField(8);
	}

	public void setCopopid(Long copOpid) {
		this.setField(9, copOpid);
	}

	public String getCopopid() {
		return this.getField(9);
	}

	public void setCopopname(String copOpname) {
		this.setField(10, copOpname);
	}

	public String getCopopname() {
		return this.getField(10);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(11, mopOpid);
	}

	public String getMopopid() {
		return this.getField(11);
	}

	public void setMopopname(String mopOpname) {
		this.setField(12, mopOpname);
	}

	public String getMopopname() {
		return this.getField(12);
	}

	public void setBkbkcode(String bkBkcode) {
		this.setField(13, bkBkcode);
	}

	public String getBkbkcode() {
		return this.getField(13);
	}

	public void setBkbkname(String bkBkname) {
		this.setField(14, bkBkname);
	}

	public String getBkbkname() {
		return this.getField(14);
	}

	public void setBllblcode(String bllBlcode) {
		this.setField(15, bllBlcode);
	}

	public String getBllblcode() {
		return this.getField(15);
	}

	public void setBllblname(String bllBlname) {
		this.setField(16, bllBlname);
	}

	public String getBllblname() {
		return this.getField(16);
	}
	
	public void setBlblwechatsign(String blBlwechatsign){
		this.setField(17, blBlwechatsign);
	}
	
	public String getBlblwechatsign(){
		return this.getField(17);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
