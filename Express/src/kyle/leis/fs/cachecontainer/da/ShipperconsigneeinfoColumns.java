package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ShipperconsigneeinfoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ShipperconsigneeinfoColumns() {
		m_astrColumns = new String[13];
	}
	
	public ShipperconsigneeinfoColumns(String scSccode, 
            String scSclabelcode, String scScname, 
            String scSccompanyname, String scScaddress1, 
            String scScaddress2, String scScaddress3, 
            String scSccitycode, String scSctelephone, 
            String scScfax, String scScshipperconsigneetype,
            String scPostcode,String chnChncode){
		m_astrColumns = new String[13];
		setScsccode(scSccode);
		setScsclabelcode(scSclabelcode);
		setScscname(scScname);
		setScsccompanyname(scSccompanyname);
		setScscaddress1(scScaddress1);
		setScscaddress2(scScaddress2);
		setScscaddress3(scScaddress3);
		setScsccitycode(scSccitycode);
		setScsctelephone(scSctelephone);
		setScscfax(scScfax);
		setScscshipperconsigneetype(scScshipperconsigneetype);
		setScpostcode(scPostcode);
		setChnchncode(chnChncode);
	}

	public void setScsccode(String scSccode) {
		this.setField(0, scSccode);
	}

	public String getScsccode() {
		return this.getField(0);
	}

	public void setScsclabelcode(String scSclabelcode) {
		this.setField(1, scSclabelcode);
	}

	public String getScsclabelcode() {
		return this.getField(1);
	}

	public void setScscname(String scScname) {
		this.setField(2, scScname);
	}

	public String getScscname() {
		return this.getField(2);
	}

	public void setScsccompanyname(String scSccompanyname) {
		this.setField(3, scSccompanyname);
	}

	public String getScsccompanyname() {
		return this.getField(3);
	}

	public void setScscaddress1(String scScaddress1) {
		this.setField(4, scScaddress1);
	}

	public String getScscaddress1() {
		return this.getField(4);
	}

	public void setScscaddress2(String scScaddress2) {
		this.setField(5, scScaddress2);
	}

	public String getScscaddress2() {
		return this.getField(5);
	}

	public void setScscaddress3(String scScaddress3) {
		this.setField(6, scScaddress3);
	}

	public String getScscaddress3() {
		return this.getField(6);
	}

	public void setScsccitycode(String scSccitycode) {
		this.setField(7, scSccitycode);
	}

	public String getScsccitycode() {
		return this.getField(7);
	}

	public void setScsctelephone(String scSctelephone) {
		this.setField(8, scSctelephone);
	}

	public String getScsctelephone() {
		return this.getField(8);
	}

	public void setScscfax(String scScfax) {
		this.setField(9, scScfax);
	}

	public String getScscfax() {
		return this.getField(9);
	}

	public void setScscshipperconsigneetype(String scScshipperconsigneetype) {
		this.setField(10, scScshipperconsigneetype);
	}

	public String getScscshipperconsigneetype() {
		return this.getField(10);
	}

	public void setScpostcode(String strScpostcode) {
		this.setField(11, strScpostcode);
	}

	public String getScpostcode() {
		return this.getField(11);
	}	
	
	
	public void setChnchncode(String chnChncode) {
		this.setField(12, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(12);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
