package kyle.leis.es.price.incidentalprice.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class IncidentalpriceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public IncidentalpriceColumns() {
		m_astrColumns = new String[33];
	}
	
	public IncidentalpriceColumns(Long epEpcode, 
            Date epEpcreatedate, Date epEpmodifydate, 
            Date epEpstartdate, Date epEpenddate, 
            String epEpremark, String epEpwithdrawsign, 
            String psPscode, String psPsname, 
            String epkEpkcode, String epkEpkname, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String eeEecode, String eeEesname, 
            String eeEeesname, String coCocode, 
            String coCosname, String coColabelcode, 
            String pgPgcode, String pgPgname, 
            String pkPkcode, String pkPkname, 
            String pkPksename, String chnChncode, 
            String chnChnsname, String chnChnsename, 
            String dtDtcode, String dtDthubcode, 
            String pdPdcode, String pdPdname){
		m_astrColumns = new String[33];
		setEpepcode(epEpcode);
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
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setCococode(coCocode);
		setCocosname(coCosname);
		setCocolabelcode(coColabelcode);
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksename(pkPksename);
		setChnchncode(chnChncode);
		setChnchnsname(chnChnsname);
		setChnchnsename(chnChnsename);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
	}

	public void setEpepcode(Long epEpcode) {
		this.setField(0, epEpcode);
	}

	public String getEpepcode() {
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

	public void setEeeesname(String eeEesname) {
		this.setField(16, eeEesname);
	}

	public String getEeeesname() {
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

	public void setCocolabelcode(String coColabelcode) {
		this.setField(20, coColabelcode);
	}

	public String getCocolabelcode() {
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

	public void setPkpkcode(String pkPkcode) {
		this.setField(23, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(23);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(24, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(24);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(25, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(25);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(26, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(26);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(27, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(27);
	}

	public void setChnchnsename(String chnChnsename) {
		this.setField(28, chnChnsename);
	}

	public String getChnchnsename() {
		return this.getField(28);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(29, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(29);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(30, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(30);
	}

	public void setPdpdcode(String pdPdcode) {
		this.setField(31, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(31);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(32, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(32);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
