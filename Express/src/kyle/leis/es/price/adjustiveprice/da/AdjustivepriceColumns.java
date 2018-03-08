package kyle.leis.es.price.adjustiveprice.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class AdjustivepriceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public AdjustivepriceColumns() {
		m_astrColumns = new String[20];
	}
	
	public AdjustivepriceColumns(Long apEpcode, 
            Date epEpcreatedate, Date epEpmodifydate, 
            Date epEpstartdate, Date epEpenddate, 
            String epEpremark, String epEpwithdrawsign, 
            String eeEecode, String eeEename, 
            String eeEeesname, String psPscode, 
            String psPsname, String epkEpkcode, 
            String epkEpkname, Long copOpid, 
            String copOpname, Long mopOpid, 
            String mopOpname, String chnChncode, 
            String chnChnname){
		m_astrColumns = new String[20];
		setApepcode(apEpcode);
		setEpepcreatedate(epEpcreatedate);
		setEpepmodifydate(epEpmodifydate);
		setEpepstartdate(epEpstartdate);
		setEpependdate(epEpenddate);
		setEpepremark(epEpremark);
		setEpepwithdrawsign(epEpwithdrawsign);
		setEeeecode(eeEecode);
		setEeeename(eeEename);
		setEeeeesname(eeEeesname);
		setPspscode(psPscode);
		setPspsname(psPsname);
		setEpkepkcode(epkEpkcode);
		setEpkepkname(epkEpkname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
	}

	public void setApepcode(Long apEpcode) {
		this.setField(0, apEpcode);
	}

	public String getApepcode() {
		return this.getField(0);
	}

	public void setEpepcreatedate(Date epEpcreatedate) {
		this.setField(1, epEpcreatedate);
	}

	public String getEpepcreatedate() {
		return this.getField(1);
	}

	public void setEpepmodifydate(Date epEpmodifydate) {
		this.setField(2, epEpmodifydate);
	}

	public String getEpepmodifydate() {
		return this.getField(2);
	}

	public void setEpepstartdate(Date epEpstartdate) {
		this.setField(3, epEpstartdate);
	}

	public String getEpepstartdate() {
		return this.getField(3);
	}

	public void setEpependdate(Date epEpenddate) {
		this.setField(4, epEpenddate);
	}

	public String getEpependdate() {
		return this.getField(4);
	}

	public void setEpepremark(String epEpremark) {
		this.setField(5, epEpremark);
	}

	public String getEpepremark() {
		return this.getField(5);
	}

	public void setEpepwithdrawsign(String epEpwithdrawsign) {
		this.setField(6, epEpwithdrawsign);
	}

	public String getEpepwithdrawsign() {
		return this.getField(6);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(7, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(7);
	}

	public void setEeeename(String eeEename) {
		this.setField(8, eeEename);
	}

	public String getEeeename() {
		return this.getField(8);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(9, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(9);
	}

	public void setPspscode(String psPscode) {
		this.setField(10, psPscode);
	}

	public String getPspscode() {
		return this.getField(10);
	}

	public void setPspsname(String psPsname) {
		this.setField(11, psPsname);
	}

	public String getPspsname() {
		return this.getField(11);
	}

	public void setEpkepkcode(String epkEpkcode) {
		this.setField(12, epkEpkcode);
	}

	public String getEpkepkcode() {
		return this.getField(12);
	}

	public void setEpkepkname(String epkEpkname) {
		this.setField(13, epkEpkname);
	}

	public String getEpkepkname() {
		return this.getField(13);
	}

	public void setCopopid(Long copOpid) {
		this.setField(14, copOpid);
	}

	public String getCopopid() {
		return this.getField(14);
	}

	public void setCopopname(String copOpname) {
		this.setField(15, copOpname);
	}

	public String getCopopname() {
		return this.getField(15);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(16, mopOpid);
	}

	public String getMopopid() {
		return this.getField(16);
	}

	public void setMopopname(String mopOpname) {
		this.setField(17, mopOpname);
	}

	public String getMopopname() {
		return this.getField(17);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(18, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(18);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(19, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(19);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
