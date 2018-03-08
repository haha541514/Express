package kyle.leis.es.price.zone.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ZoneColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ZoneColumns() {
		m_astrColumns = new String[18];
	}
	
	public ZoneColumns(Long znZncode, 
            String znZnname, String znZnename, 
            Date znZncreatedate, Date znZnmodifydate, 
            String znZnremark, String znZnkeywords, 
            String pkPkcode, String pkPksname, 
            String pkPksename, String ssSscode, 
            String ssSsname, Long copOpid, 
            String copOpname, Long mopOpid, 
            String mopOpname, String zfZfcode, 
            String zfZfname){
		m_astrColumns = new String[18];
		setZnzncode(znZncode);
		setZnznname(znZnname);
		setZnznename(znZnename);
		setZnzncreatedate(znZncreatedate);
		setZnznmodifydate(znZnmodifydate);
		setZnznremark(znZnremark);
		setZnznkeywords(znZnkeywords);
		setPkpkcode(pkPkcode);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setZfzfcode(zfZfcode);
		setZfzfname(zfZfname);
	}

	public void setZnzncode(Long znZncode) {
		this.setField(0, znZncode);
	}

	public String getZnzncode() {
		return this.getField(0);
	}

	public void setZnznname(String znZnname) {
		this.setField(1, znZnname);
	}

	public String getZnznname() {
		return this.getField(1);
	}

	public void setZnznename(String znZnename) {
		this.setField(2, znZnename);
	}

	public String getZnznename() {
		return this.getField(2);
	}

	public void setZnzncreatedate(Date znZncreatedate) {
		this.setField(3, znZncreatedate);
	}

	public String getZnzncreatedate() {
		return this.getField(3);
	}

	public void setZnznmodifydate(Date znZnmodifydate) {
		this.setField(4, znZnmodifydate);
	}

	public String getZnznmodifydate() {
		return this.getField(4);
	}

	public void setZnznremark(String znZnremark) {
		this.setField(5, znZnremark);
	}

	public String getZnznremark() {
		return this.getField(5);
	}

	public void setZnznkeywords(String znZnkeywords) {
		this.setField(6, znZnkeywords);
	}

	public String getZnznkeywords() {
		return this.getField(6);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(7, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(7);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(8, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(8);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(9, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(9);
	}

	public void setSssscode(String ssSscode) {
		this.setField(10, ssSscode);
	}

	public String getSssscode() {
		return this.getField(10);
	}

	public void setSsssname(String ssSsname) {
		this.setField(11, ssSsname);
	}

	public String getSsssname() {
		return this.getField(11);
	}

	public void setCopopid(Long copOpid) {
		this.setField(12, copOpid);
	}

	public String getCopopid() {
		return this.getField(12);
	}

	public void setCopopname(String copOpname) {
		this.setField(13, copOpname);
	}

	public String getCopopname() {
		return this.getField(13);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(14, mopOpid);
	}

	public String getMopopid() {
		return this.getField(14);
	}

	public void setMopopname(String mopOpname) {
		this.setField(15, mopOpname);
	}

	public String getMopopname() {
		return this.getField(15);
	}

	public void setZfzfcode(String zfZfcode) {
		this.setField(16, zfZfcode);
	}

	public String getZfzfcode() {
		return this.getField(16);
	}

	public void setZfzfname(String zfZfname) {
		this.setField(17, zfZfname);
	}

	public String getZfzfname() {
		return this.getField(17);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
