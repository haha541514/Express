package kyle.leis.es.businessrule.channeltrackmapping.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ChanneltrackmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ChanneltrackmappingColumns() {
		m_astrColumns = new String[16];
	}
	
	public ChanneltrackmappingColumns(Long ctmCtmid, 
            String ctmCtmsourcetrackdesc, Date ctmCtmcreatedate, 
            Date ctmCtmmodifydate, String chnChncode, 
            String chnChnsname, String chnChnsename, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String wtsWbtscode, String wtsWbtsname, 
            String wtsWbtsename, String ssSscode, 
            String ssSsname){
		m_astrColumns = new String[16];
		setCtmctmid(ctmCtmid);
		setCtmctmsourcetrackdesc(ctmCtmsourcetrackdesc);
		setCtmctmcreatedate(ctmCtmcreatedate);
		setCtmctmmodifydate(ctmCtmmodifydate);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setWtswbtscode(wtsWbtscode);
		setWtswbtsname(wtsWbtsname);
		setWtswbtsename(wtsWbtsename);
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

	public void setChnchnsename(String chnChnsename) {
		this.setField(6, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(6);
	}

	public void setCopopid(Long copOpid) {
		this.setField(7, copOpid);
	}

	public String getCopopid() {
		return this.getField(7);
	}

	public void setCopopname(String copOpname) {
		this.setField(8, copOpname);
	}

	public String getCopopname() {
		return this.getField(8);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(9, mopOpid);
	}

	public String getMopopid() {
		return this.getField(9);
	}

	public void setMopopname(String mopOpname) {
		this.setField(10, mopOpname);
	}

	public String getMopopname() {
		return this.getField(10);
	}

	public void setWtswbtscode(String wtsWbtscode) {
		this.setField(11, wtsWbtscode);
	}

	public String getWtswbtscode() {
		return this.getField(11);
	}

	public void setWtswbtsname(String wtsWbtsname) {
		this.setField(12, wtsWbtsname);
	}

	public String getWtswbtsname() {
		return this.getField(12);
	}

	public void setWtswbtsename(String wtsWbtsename) {
		this.setField(13, wtsWbtsename);
	}

	public String getWtswbtsename() {
		return this.getField(13);
	}

	public void setSssscode(String ssSscode) {
		this.setField(14, ssSscode);
	}

	public String getSssscode() {
		return this.getField(14);
	}

	public void setSsssname(String ssSsname) {
		this.setField(15, ssSsname);
	}

	public String getSsssname() {
		return this.getField(15);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
