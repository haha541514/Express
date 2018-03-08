package kyle.leis.eo.finance.writeoff.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class WriteoffColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public WriteoffColumns() {
		m_astrColumns = new String[11];
	}
	
	public WriteoffColumns(Long woWoid, 
            BigDecimal woWototal, Date woWocreatedate, 
            String woWolabelcode, String woWoremark, 
            String ckCkcode, String ckCkname, 
            Long opOpid, String opOpname, 
            String ssSscode, String ssSsname){
		m_astrColumns = new String[11];
		setWowoid(woWoid);
		setWowototal(woWototal);
		setWowocreatedate(woWocreatedate);
		setWowolabelcode(woWolabelcode);
		setWoworemark(woWoremark);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setOpopid(opOpid);
		setOpopname(opOpname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setWowoid(Long woWoid) {
		this.setField(0, woWoid);
	}

	public String getWowoid() {
		return this.getField(0);
	}

	public void setWowototal(BigDecimal woWototal) {
		this.setField(1, woWototal);
	}

	public String getWowototal() {
		return this.getField(1);
	}

	public void setWowocreatedate(Date woWocreatedate) {
		this.setField(2, woWocreatedate);
	}

	public String getWowocreatedate() {
		return this.getField(2);
	}

	public void setWowolabelcode(String woWolabelcode) {
		this.setField(3, woWolabelcode);
	}

	public String getWowolabelcode() {
		return this.getField(3);
	}

	public void setWoworemark(String woWoremark) {
		this.setField(4, woWoremark);
	}

	public String getWoworemark() {
		return this.getField(4);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(5, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(5);
	}

	public void setCkckname(String ckCkname) {
		this.setField(6, ckCkname);
	}

	public String getCkckname() {
		return this.getField(6);
	}

	public void setOpopid(Long opOpid) {
		this.setField(7, opOpid);
	}

	public String getOpopid() {
		return this.getField(7);
	}

	public void setOpopname(String opOpname) {
		this.setField(8, opOpname);
	}

	public String getOpopname() {
		return this.getField(8);
	}

	public void setSssscode(String ssSscode) {
		this.setField(9, ssSscode);
	}

	public String getSssscode() {
		return this.getField(9);
	}

	public void setSsssname(String ssSsname) {
		this.setField(10, ssSsname);
	}

	public String getSsssname() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
