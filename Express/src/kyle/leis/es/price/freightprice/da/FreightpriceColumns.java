package kyle.leis.es.price.freightprice.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class FreightpriceColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public FreightpriceColumns() {
		m_astrColumns = new String[46];
	}
	
	public FreightpriceColumns(Long fpEpcode, 
            BigDecimal fpFpcommissionrate, Date epEpcreatedate, 
            Date epEpmodifydate, Date epEpstartdate, 
            Date epEpenddate, String epEpremark, 
            String epEpwithdrawsign, String eeEecode, 
            String eeEename, String eeEeesname, 
            String psPscode, String psPsname, 
            String epkEpkcode, String epkEpkname, 
            Long copOpid, String copOpname, 
            Long mopOpid, String mopOpname, 
            String pkPkcode, String pkPkname, 
            String pkPksname, String dtDtcode, 
            String dtDthubcode, String dtDtname, 
            String ckCkcode, String ckCkname, 
            String pdPdcode, String pdPdname, 
            String pmPmcode, String pmPmname, 
            String utUtcode, String utUtname, 
            String ctCtcode, String ctCtname, 
            Long znZncode, String znZnname, 
            String znZnkeywords, String coCocode, 
            String coConame, String coCosname, 
            String pgPgcode, String pgPgname, 
            String chnChncode, String chnChnname, 
            String chnChnsname){
		m_astrColumns = new String[46];
		setFpepcode(fpEpcode);
		setFpfpcommissionrate(fpFpcommissionrate);
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
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksname(pkPksname);
		setDtdtcode(dtDtcode);
		setDtdthubcode(dtDthubcode);
		setDtdtname(dtDtname);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setPdpdcode(pdPdcode);
		setPdpdname(pdPdname);
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setUtutcode(utUtcode);
		setUtutname(utUtname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setZnzncode(znZncode);
		setZnznname(znZnname);
		setZnznkeywords(znZnkeywords);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocosname(coCosname);
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
		setChnchncode(chnChncode);
		setChnchnname(chnChnname);
		setChnchnsname(chnChnsname);
	}

	public void setFpepcode(Long fpEpcode) {
		this.setField(0, fpEpcode);
	}

	public String getFpepcode() {
		return this.getField(0);
	}

	public void setFpfpcommissionrate(BigDecimal fpFpcommissionrate) {
		this.setField(1, fpFpcommissionrate);
	}

	public String getFpfpcommissionrate() {
		return this.getField(1);
	}

	public void setEpepcreatedate(Date epEpcreatedate) {
		this.setField(2, epEpcreatedate);
	}

	public String getEpepcreatedate() {
		return this.getField(2);
	}

	public void setEpepmodifydate(Date epEpmodifydate) {
		this.setField(3, epEpmodifydate);
	}

	public String getEpepmodifydate() {
		return this.getField(3);
	}

	public void setEpepstartdate(Date epEpstartdate) {
		this.setField(4, epEpstartdate);
	}

	public String getEpepstartdate() {
		return this.getField(4);
	}

	public void setEpependdate(Date epEpenddate) {
		this.setField(5, epEpenddate);
	}

	public String getEpependdate() {
		return this.getField(5);
	}

	public void setEpepremark(String epEpremark) {
		this.setField(6, epEpremark);
	}

	public String getEpepremark() {
		return this.getField(6);
	}

	public void setEpepwithdrawsign(String epEpwithdrawsign) {
		this.setField(7, epEpwithdrawsign);
	}

	public String getEpepwithdrawsign() {
		return this.getField(7);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(8, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(8);
	}

	public void setEeeename(String eeEename) {
		this.setField(9, eeEename);
	}

	public String getEeeename() {
		return this.getField(9);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(10, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(10);
	}

	public void setPspscode(String psPscode) {
		this.setField(11, psPscode);
	}

	public String getPspscode() {
		return this.getField(11);
	}

	public void setPspsname(String psPsname) {
		this.setField(12, psPsname);
	}

	public String getPspsname() {
		return this.getField(12);
	}

	public void setEpkepkcode(String epkEpkcode) {
		this.setField(13, epkEpkcode);
	}

	public String getEpkepkcode() {
		return this.getField(13);
	}

	public void setEpkepkname(String epkEpkname) {
		this.setField(14, epkEpkname);
	}

	public String getEpkepkname() {
		return this.getField(14);
	}

	public void setCopopid(Long copOpid) {
		this.setField(15, copOpid);
	}

	public String getCopopid() {
		return this.getField(15);
	}

	public void setCopopname(String copOpname) {
		this.setField(16, copOpname);
	}

	public String getCopopname() {
		return this.getField(16);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(17, mopOpid);
	}

	public String getMopopid() {
		return this.getField(17);
	}

	public void setMopopname(String mopOpname) {
		this.setField(18, mopOpname);
	}

	public String getMopopname() {
		return this.getField(18);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(19, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(19);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(20, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(20);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(21, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(21);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(22, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(22);
	}

	public void setDtdthubcode(String dtDthubcode) {
		this.setField(23, dtDthubcode);
	}

	public String getDtdthubcode() {
		return this.getField(23);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(24, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(24);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(25, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(25);
	}

	public void setCkckname(String ckCkname) {
		this.setField(26, ckCkname);
	}

	public String getCkckname() {
		return this.getField(26);
	}

	public void setPdpdcode(String pdPdcode) {
		this.setField(27, pdPdcode);
	}

	public String getPdpdcode() {
		return this.getField(27);
	}

	public void setPdpdname(String pdPdname) {
		this.setField(28, pdPdname);
	}

	public String getPdpdname() {
		return this.getField(28);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(29, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(29);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(30, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(30);
	}

	public void setUtutcode(String utUtcode) {
		this.setField(31, utUtcode);
	}

	public String getUtutcode() {
		return this.getField(31);
	}

	public void setUtutname(String utUtname) {
		this.setField(32, utUtname);
	}

	public String getUtutname() {
		return this.getField(32);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(33, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(33);
	}

	public void setCtctname(String ctCtname) {
		this.setField(34, ctCtname);
	}

	public String getCtctname() {
		return this.getField(34);
	}

	public void setZnzncode(Long znZncode) {
		this.setField(35, znZncode);
	}

	public String getZnzncode() {
		return this.getField(35);
	}

	public void setZnznname(String znZnname) {
		this.setField(36, znZnname);
	}

	public String getZnznname() {
		return this.getField(36);
	}

	public void setZnznkeywords(String znZnkeywords) {
		this.setField(37, znZnkeywords);
	}

	public String getZnznkeywords() {
		return this.getField(37);
	}

	public void setCococode(String coCocode) {
		this.setField(38, coCocode);
	}

	public String getCococode() {
		return this.getField(38);
	}

	public void setCoconame(String coConame) {
		this.setField(39, coConame);
	}

	public String getCoconame() {
		return this.getField(39);
	}

	public void setCocosname(String coCosname) {
		this.setField(40, coCosname);
	}

	public String getCocosname() {
		return this.getField(40);
	}

	public void setPgpgcode(String pgPgcode) {
		this.setField(41, pgPgcode);
	}

	public String getPgpgcode() {
		return this.getField(41);
	}

	public void setPgpgname(String pgPgname) {
		this.setField(42, pgPgname);
	}

	public String getPgpgname() {
		return this.getField(42);
	}

	public void setChnchncode(String chnChncode) {
		this.setField(43, chnChncode);
	}

	public String getChnchncode() {
		return this.getField(43);
	}

	public void setChnchnname(String chnChnname) {
		this.setField(44, chnChnname);
	}

	public String getChnchnname() {
		return this.getField(44);
	}

	public void setChnchnsname(String chnChnsname) {
		this.setField(45, chnChnsname);
	}

	public String getChnchnsname() {
		return this.getField(45);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
