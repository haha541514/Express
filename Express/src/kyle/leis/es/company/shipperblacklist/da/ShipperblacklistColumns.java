package kyle.leis.es.company.shipperblacklist.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ShipperblacklistColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ShipperblacklistColumns() {
		m_astrColumns = new String[15];
	}
	
	public ShipperblacklistColumns(Long sblSblcode, 
            String sblSblcompanyname, Date sblSblcreatedate, 
            Date sblSblmodifydate, Long opcOpid, 
            String opcOpcode, String opcOpname, 
            String opcOpename, Long opmOpid, 
            String opmOpcode, String opmOpname, 
            String opmOpename, String ssgSsgcode, 
            String ssgSsgname, String ssgSsgename){
		m_astrColumns = new String[15];
		setSblsblcode(sblSblcode);
		setSblsblcompanyname(sblSblcompanyname);
		setSblsblcreatedate(sblSblcreatedate);
		setSblsblmodifydate(sblSblmodifydate);
		setOpcopid(opcOpid);
		setOpcopcode(opcOpcode);
		setOpcopname(opcOpname);
		setOpcopename(opcOpename);
		setOpmopid(opmOpid);
		setOpmopcode(opmOpcode);
		setOpmopname(opmOpname);
		setOpmopename(opmOpename);
		setSsgssgcode(ssgSsgcode);
		setSsgssgname(ssgSsgname);
		setSsgssgename(ssgSsgename);
	}

	public void setSblsblcode(Long sblSblcode) {
		this.setField(0, sblSblcode);
	}

	public String getSblsblcode() {
		return this.getField(0);
	}

	public void setSblsblcompanyname(String sblSblcompanyname) {
		this.setField(1, sblSblcompanyname);
	}

	public String getSblsblcompanyname() {
		return this.getField(1);
	}

	public void setSblsblcreatedate(Date sblSblcreatedate) {
		this.setField(2, sblSblcreatedate);
	}

	public String getSblsblcreatedate() {
		return this.getField(2);
	}

	public void setSblsblmodifydate(Date sblSblmodifydate) {
		this.setField(3, sblSblmodifydate);
	}

	public String getSblsblmodifydate() {
		return this.getField(3);
	}

	public void setOpcopid(Long opcOpid) {
		this.setField(4, opcOpid);
	}

	public String getOpcopid() {
		return this.getField(4);
	}

	public void setOpcopcode(String opcOpcode) {
		this.setField(5, opcOpcode);
	}

	public String getOpcopcode() {
		return this.getField(5);
	}

	public void setOpcopname(String opcOpname) {
		this.setField(6, opcOpname);
	}

	public String getOpcopname() {
		return this.getField(6);
	}

	public void setOpcopename(String opcOpename) {
		this.setField(7, opcOpename);
	}

	public String getOpcopename() {
		return this.getField(7);
	}

	public void setOpmopid(Long opmOpid) {
		this.setField(8, opmOpid);
	}

	public String getOpmopid() {
		return this.getField(8);
	}

	public void setOpmopcode(String opmOpcode) {
		this.setField(9, opmOpcode);
	}

	public String getOpmopcode() {
		return this.getField(9);
	}

	public void setOpmopname(String opmOpname) {
		this.setField(10, opmOpname);
	}

	public String getOpmopname() {
		return this.getField(10);
	}

	public void setOpmopename(String opmOpename) {
		this.setField(11, opmOpename);
	}

	public String getOpmopename() {
		return this.getField(11);
	}

	public void setSsgssgcode(String ssgSsgcode) {
		this.setField(12, ssgSsgcode);
	}

	public String getSsgssgcode() {
		return this.getField(12);
	}

	public void setSsgssgname(String ssgSsgname) {
		this.setField(13, ssgSsgname);
	}

	public String getSsgssgname() {
		return this.getField(13);
	}

	public void setSsgssgename(String ssgSsgename) {
		this.setField(14, ssgSsgename);
	}

	public String getSsgssgename() {
		return this.getField(14);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
