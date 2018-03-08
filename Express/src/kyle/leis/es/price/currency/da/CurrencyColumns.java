package kyle.leis.es.price.currency.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CurrencyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CurrencyColumns() {
		m_astrColumns = new String[23];
	}
	
	public CurrencyColumns(Long cuEpcode, 
            Date epEpcreatedate, Date epEpmodifydate, 
            Date epEpstartdate, Date epEpenddate, 
            String epEpremark, String epEpwithdrawsign, 
            String epkEpkcode, String epkEpkname, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String eeEecode, String eeEesname, 
            String psPscode, String psPsname, 
            String pkPkcode, String pkPksname, 
            String coCocode, String coCosname, 
            String pgPgcode, String pgPgname){
		m_astrColumns = new String[23];
		setCuepcode(cuEpcode);
		setEpepcreatedate(epEpcreatedate);
		setEpepmodifydate(epEpmodifydate);
		setEpepstartdate(epEpstartdate);
		setEpependdate(epEpenddate);
		setEpepremark(epEpremark);
		setEpepwithdrawsign(epEpwithdrawsign);
		setEpkepkcode(epkEpkcode);
		setEpkepkname(epkEpkname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setPspscode(psPscode);
		setPspsname(psPsname);
		setPkpkcode(pkPkcode);
		setPkpksname(pkPksname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
	}

	public void setCuepcode(Long cuEpcode) {
		this.setField(0, cuEpcode);
	}

	public String getCuepcode() {
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

	public void setEpkepkcode(String epkEpkcode) {
		this.setField(7, epkEpkcode);
	}

	public String getEpkepkcode() {
		return this.getField(7);
	}

	public void setEpkepkname(String epkEpkname) {
		this.setField(8, epkEpkname);
	}

	public String getEpkepkname() {
		return this.getField(8);
	}

	public void setCopopid(Long copOpid) {
		this.setField(9, copOpid);
	}

	public String getCopopid() {
		return this.getField(9);
	}

	public void setCopopname(String copOpname) {
		this.setField(10, copOpname);
	}

	public String getCopopname() {
		return this.getField(10);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(11, mopOpid);
	}

	public String getMopopid() {
		return this.getField(11);
	}

	public void setMopopname(String mopOpname) {
		this.setField(12, mopOpname);
	}

	public String getMopopname() {
		return this.getField(12);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(13, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(13);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(14, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(14);
	}

	public void setPspscode(String psPscode) {
		this.setField(15, psPscode);
	}

	public String getPspscode() {
		return this.getField(15);
	}

	public void setPspsname(String psPsname) {
		this.setField(16, psPsname);
	}

	public String getPspsname() {
		return this.getField(16);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(17, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(17);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(18, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(18);
	}

	public void setCococode(String coCocode) {
		this.setField(19, coCocode);
	}

	public String getCococode() {
		return this.getField(19);
	}

	public void setCocosname(String coCosname) {
		this.setField(20, coCosname);
	}

	public String getCocosname() {
		return this.getField(20);
	}

	public void setPgpgcode(String pgPgcode) {
		this.setField(21, pgPgcode);
	}

	public String getPgpgcode() {
		return this.getField(21);
	}

	public void setPgpgname(String pgPgname) {
		this.setField(22, pgPgname);
	}

	public String getPgpgname() {
		return this.getField(22);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
