package kyle.fetcher.track.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ChanneltrackmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChanneltrackmappingColumns() {
		m_astrColumns = new String[14];
	}
	
	public ChanneltrackmappingColumns(Long ctmCtmid, 
            String ctmCtmsourcetrackdesc, Date ctmCtmcreatedate, 
            Date ctmCtmmodifydate, String chnChncode, 
            String chnChnsname, Long copOpid, 
            String copOpname, Long mopOpid, 
            String mopOpname, String wbtsWbtscode, 
            String wbtsWbtsname, String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[14];
		setCtmctmid(ctmCtmid);
		setCtmctmsourcetrackdesc(ctmCtmsourcetrackdesc);
		setCtmctmcreatedate(ctmCtmcreatedate);
		setCtmctmmodifydate(ctmCtmmodifydate);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setWbtswbtscode(wbtsWbtscode);
		setWbtswbtsname(wbtsWbtsname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setCtmctmid(Long ctmCtmid) {
		this.setField(0, ctmCtmid);
	}

	public String getCtmctmid() {
		return this.getField(0);
	}

	public void setCtmctmsourcetrackdesc(String ctmCtmsourcetrackdesc) {
		this.setField(1, ctmCtmsourcetrackdesc);
	}

	public String getCtmctmsourcetrackdesc() {
		return this.getField(1);
	}

	public void setCtmctmcreatedate(Date ctmCtmcreatedate) {
		this.setField(2, ctmCtmcreatedate);
	}

	public String getCtmctmcreatedate() {
		return this.getField(2);
	}

	public void setCtmctmmodifydate(Date ctmCtmmodifydate) {
		this.setField(3, ctmCtmmodifydate);
	}

	public String getCtmctmmodifydate() {
		return this.getField(3);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(4, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(4);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(5, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(5);
	}

	public void setCopopid(Long copOpid) {
		this.setField(6, copOpid);
	}

	public String getCopopid() {
		return this.getField(6);
	}

	public void setCopopname(String copOpname) {
		this.setField(7, copOpname);
	}

	public String getCopopname() {
		return this.getField(7);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(8, mopOpid);
	}

	public String getMopopid() {
		return this.getField(8);
	}

	public void setMopopname(String mopOpname) {
		this.setField(9, mopOpname);
	}

	public String getMopopname() {
		return this.getField(9);
	}

	public void setWbtswbtscode(String wbtsWbtscode) {
		this.setField(10, wbtsWbtscode);
	}

	public String getWbtswbtscode() {
		return this.getField(10);
	}

	public void setWbtswbtsname(String wbtsWbtsname) {
		this.setField(11, wbtsWbtsname);
	}

	public String getWbtswbtsname() {
		return this.getField(11);
	}

	public void setSssscode(String ssSscode) {
		this.setField(12, ssSscode);
	}

	public String getSssscode() {
		return this.getField(12);
	}

	public void setSsssname(String ssSsname) {
		this.setField(13, ssSsname);
	}

	public String getSsssname() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
