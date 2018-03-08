package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class PredicttemplateColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PredicttemplateColumns() {
		m_astrColumns = new String[11];
	}
	
	public PredicttemplateColumns(Long potPotid, 
            String potPotname, String potPotremark, 
            Date potPotcreatedate, Date potPotmodifydate, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String coCocode, String coCosname){
		m_astrColumns = new String[11];
		setPotpotid(potPotid);
		setPotpotname(potPotname);
		setPotpotremark(potPotremark);
		setPotpotcreatedate(potPotcreatedate);
		setPotpotmodifydate(potPotmodifydate);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setCococode(coCocode);
		setCocosname(coCosname);
	}

	public void setPotpotid(Long potPotid) {
		this.setField(0, potPotid);
	}

	public String getPotpotid() {
		return this.getField(0);
	}

	public void setPotpotname(String potPotname) {
		this.setField(1, potPotname);
	}

	public String getPotpotname() {
		return this.getField(1);
	}

	public void setPotpotremark(String potPotremark) {
		this.setField(2, potPotremark);
	}

	public String getPotpotremark() {
		return this.getField(2);
	}

	public void setPotpotcreatedate(Date potPotcreatedate) {
		this.setField(3, potPotcreatedate);
	}

	public String getPotpotcreatedate() {
		return this.getField(3);
	}

	public void setPotpotmodifydate(Date potPotmodifydate) {
		this.setField(4, potPotmodifydate);
	}

	public String getPotpotmodifydate() {
		return this.getField(4);
	}

	public void setCopopid(Long copOpid) {
		this.setField(5, copOpid);
	}

	public String getCopopid() {
		return this.getField(5);
	}

	public void setCopopname(String copOpname) {
		this.setField(6, copOpname);
	}

	public String getCopopname() {
		return this.getField(6);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(7, mopOpid);
	}

	public String getMopopid() {
		return this.getField(7);
	}

	public void setMopopname(String mopOpname) {
		this.setField(8, mopOpname);
	}

	public String getMopopname() {
		return this.getField(8);
	}

	public void setCococode(String coCocode) {
		this.setField(9, coCocode);
	}

	public String getCococode() {
		return this.getField(9);
	}

	public void setCocosname(String coCosname) {
		this.setField(10, coCosname);
	}

	public String getCocosname() {
		return this.getField(10);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
