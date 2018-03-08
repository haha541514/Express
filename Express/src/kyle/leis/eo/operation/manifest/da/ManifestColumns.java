package kyle.leis.eo.operation.manifest.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ManifestColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifestColumns() {
		m_astrColumns = new String[12];
	}
	
	public ManifestColumns(Long mfMfcode, 
            Date mfMfcreatedate, Date mfMfmodifydate, 
            String mfMfremark, Long mopOpid, 
            String mopOpname, Long copOpid, 
            String copOpname, String ssSscode, 
            String ssSsname, String strEecode,
            String strEesname){
		m_astrColumns = new String[12];
		setMfmfcode(mfMfcode);
		setMfmfcreatedate(mfMfcreatedate);
		setMfmfmodifydate(mfMfmodifydate);
		setMfmfremark(mfMfremark);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		this.setEecode(strEecode);
		this.setEesname(strEesname);
	}

	public void setMfmfcode(Long mfMfcode) {
		this.setField(0, mfMfcode);
	}

	public String getMfmfcode() {
		return this.getField(0);
	}

	public void setMfmfcreatedate(Date mfMfcreatedate) {
		this.setField(1, mfMfcreatedate);
	}

	public String getMfmfcreatedate() {
		return this.getField(1);
	}

	public void setMfmfmodifydate(Date mfMfmodifydate) {
		this.setField(2, mfMfmodifydate);
	}

	public String getMfmfmodifydate() {
		return this.getField(2);
	}

	public void setMfmfremark(String mfMfremark) {
		this.setField(3, mfMfremark);
	}

	public String getMfmfremark() {
		return this.getField(3);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(4, mopOpid);
	}

	public String getMopopid() {
		return this.getField(4);
	}

	public void setMopopname(String mopOpname) {
		this.setField(5, mopOpname);
	}

	public String getMopopname() {
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

	public void setSssscode(String ssSscode) {
		this.setField(8, ssSscode);
	}

	public String getSssscode() {
		return this.getField(8);
	}

	public void setSsssname(String ssSsname) {
		this.setField(9, ssSsname);
	}

	public String getSsssname() {
		return this.getField(9);
	}

	public void setEecode(String eeCode) {
		this.setField(10, eeCode);
	}

	public String getEecode() {
		return this.getField(10);
	}
	
	public void setEesname(String eeSname) {
		this.setField(11, eeSname);
	}

	public String getEesname() {
		return this.getField(11);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
