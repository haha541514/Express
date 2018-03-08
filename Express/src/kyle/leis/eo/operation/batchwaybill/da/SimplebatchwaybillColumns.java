package kyle.leis.eo.operation.batchwaybill.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SimplebatchwaybillColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SimplebatchwaybillColumns() {
		m_astrColumns = new String[14];
	}
	
	public SimplebatchwaybillColumns(Long bwBwcode, 
            Date bwAdddate, String bwBwlabelcode, 
            String bwBwbatchnumber, String bwsBwscode, 
            String chnChncode, String chnChnsname, 
            String chnChnsename, String eeEecode, 
            String eeEesname, String eeEeesname, 
            String coCocode, String coCosname, 
            String coCosename){
		m_astrColumns = new String[14];
		setBwbwcode(bwBwcode);
		setBwadddate(bwAdddate);
		setBwbwlabelcode(bwBwlabelcode);
		setBwbwbatchnumber(bwBwbatchnumber);
		setBwsbwscode(bwsBwscode);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocosename(coCosename);
	}

	public void setBwbwcode(Long bwBwcode) {
		this.setField(0, bwBwcode);
	}

	public String getBwbwcode() {
		return this.getField(0);
	}

	public void setBwadddate(Date bwAdddate) {
		this.setField(1, bwAdddate);
	}

	public String getBwadddate() {
		return this.getField(1);
	}

	public void setBwbwlabelcode(String bwBwlabelcode) {
		this.setField(2, bwBwlabelcode);
	}

	public String getBwbwlabelcode() {
		return this.getField(2);
	}

	public void setBwbwbatchnumber(String bwBwbatchnumber) {
		this.setField(3, bwBwbatchnumber);
	}

	public String getBwbwbatchnumber() {
		return this.getField(3);
	}

	public void setBwsbwscode(String bwsBwscode) {
		this.setField(4, bwsBwscode);
	}

	public String getBwsbwscode() {
		return this.getField(4);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(5, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(5);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(6, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(6);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(7, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(7);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(8, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(8);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(9, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(9);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(10, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(10);
	}

	public void setCococode(String coCocode) {
		this.setField(11, coCocode);
	}

	public String getCococode() {
		return this.getField(11);
	}

	public void setCocosname(String coCosname) {
		this.setField(12, coCosname);
	}

	public String getCocosname() {
		return this.getField(12);
	}

	public void setCocosename(String coCosename) {
		this.setField(13, coCosename);
	}

	public String getCocosename() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
