package kyle.leis.es.price.pricegroup.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CustomergroupandvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomergroupandvalueColumns() {
		
	}
	
	public CustomergroupandvalueColumns(Long cpgEpcode, 
            Date epEpcreatedate, Date epEpmodifydate, 
            Date epEpstartdate, Date epEpenddate, 
            String epEpremark, String epEpwithdrawsign, 
            String psPscode, String psPsname, 
            String epkEpkcode, String epkEpkname, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String eeEecode, String eeeeEesname, 
            String eeEeesname, String coCocode, 
            String coCosname, String coCosename, 
            String coColabelcode, String cstCstcode, 
            String cstCstname, String pgkPgkcode, 
            String pgkPgkname, String pkPkcode, 
            String pkPksname, String pkPksename, 
            String pgPgcode, String pgPgname, 
            String fkFkcode, String fkFkname){
		m_astrColumns = new String[33];
		setCpgepcode(cpgEpcode);
		setEpepcreatedate(epEpcreatedate);
		setEpepmodifydate(epEpmodifydate);
		setEpepstartdate(epEpstartdate);
		setEpependdate(epEpenddate);
		setEpepremark(epEpremark);
		setEpepwithdrawsign(epEpwithdrawsign);
		setPspscode(psPscode);
		setPspsname(psPsname);
		setEpkepkcode(epkEpkcode);
		setEpkepkname(epkEpkname);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setEeeecode(eeEecode);
		setEeeeeesname(eeeeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCocolabelcode(coColabelcode);
		setCstcstcode(cstCstcode);
		setCstcstname(cstCstname);
		setPgkpgkcode(pgkPgkcode);
		setPgkpgkname(pgkPgkname);
		setPkpkcode(pkPkcode);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
	}

	public void setCpgepcode(Long cpgEpcode) {
		this.setField(0, cpgEpcode);
	}

	public String getCpgepcode() {
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

	public void setPspscode(String psPscode) {
		this.setField(7, psPscode);
	}

	public String getPspscode() {
		return this.getField(7);
	}

	public void setPspsname(String psPsname) {
		this.setField(8, psPsname);
	}

	public String getPspsname() {
		return this.getField(8);
	}

	public void setEpkepkcode(String epkEpkcode) {
		this.setField(9, epkEpkcode);
	}

	public String getEpkepkcode() {
		return this.getField(9);
	}

	public void setEpkepkname(String epkEpkname) {
		this.setField(10, epkEpkname);
	}

	public String getEpkepkname() {
		return this.getField(10);
	}

	public void setCopopid(Long copOpid) {
		this.setField(11, copOpid);
	}

	public String getCopopid() {
		return this.getField(11);
	}

	public void setCopopname(String copOpname) {
		this.setField(12, copOpname);
	}

	public String getCopopname() {
		return this.getField(12);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(13, mopOpid);
	}

	public String getMopopid() {
		return this.getField(13);
	}

	public void setMopopname(String mopOpname) {
		this.setField(14, mopOpname);
	}

	public String getMopopname() {
		return this.getField(14);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(15, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(15);
	}

	public void setEeeeeesname(String eeeeEesname) {
		this.setField(16, eeeeEesname);
	}

	public String getEeeeeesname() {
		return this.getField(16);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(17, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(17);
	}

	public void setCococode(String coCocode) {
		this.setField(18, coCocode);
	}

	public String getCococode() {
		return this.getField(18);
	}

	public void setCocosname(String coCosname) {
		this.setField(19, coCosname);
	}

	public String getCocosname() {
		return this.getField(19);
	}

	public void setCocosename(String coCosename) {
		this.setField(20, coCosename);
	}

	public String getCocosename() {
		return this.getField(20);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(21, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(21);
	}

	public void setCstcstcode(String cstCstcode) {
		this.setField(22, cstCstcode);
	}

	public String getCstcstcode() {
		return this.getField(22);
	}

	public void setCstcstname(String cstCstname) {
		this.setField(23, cstCstname);
	}

	public String getCstcstname() {
		return this.getField(23);
	}

	public void setPgkpgkcode(String pgkPgkcode) {
		this.setField(24, pgkPgkcode);
	}

	public String getPgkpgkcode() {
		return this.getField(24);
	}

	public void setPgkpgkname(String pgkPgkname) {
		this.setField(25, pgkPgkname);
	}

	public String getPgkpgkname() {
		return this.getField(25);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(26, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(26);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(27, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(27);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(28, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(28);
	}

	public void setPgpgcode(String pgPgcode) {
		this.setField(29, pgPgcode);
	}

	public String getPgpgcode() {
		return this.getField(29);
	}

	public void setPgpgname(String pgPgname) {
		this.setField(30, pgPgname);
	}

	public String getPgpgname() {
		return this.getField(30);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(31, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(31);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(32, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(32);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
