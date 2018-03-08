package kyle.leis.es.businessrule.productrule.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class ProductruleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ProductruleColumns() {
		m_astrColumns = new String[27];
	}
	
	public ProductruleColumns(Long prBrid, 
            String prPrinsurancesign, BigDecimal prPrdoxtransfergw, 
            String prPrsimpleinputsign, String prPrcollectsign, 
            String prPrallcussignoutbyoriginwbsign, String brBrname, 
            String brBrename, Date brBrstartdate, 
            Date brBrenddate, Long copOpid, 
            String copOpname, Date brBrcreatedate, 
            Long mopOpid, String mopOpname, 
            Date brBrmodifydate, String brBrremark, 
            String pkPkcode, String pkPkname, 
            String pkPksname, String pkPksename, 
            String ckCkcode, String ckCkname, 
            String brkBrkcode, String brkBrkname, 
            String ssSscode, String ssSsname){
		m_astrColumns = new String[27];
		setPrbrid(prBrid);
		setPrprinsurancesign(prPrinsurancesign);
		setPrprdoxtransfergw(prPrdoxtransfergw);
		setPrprsimpleinputsign(prPrsimpleinputsign);
		setPrprcollectsign(prPrcollectsign);
		setPrprallcussignoutbyoriginwbsign(prPrallcussignoutbyoriginwbsign);
		setBrbrname(brBrname);
		setBrbrename(brBrename);
		setBrbrstartdate(brBrstartdate);
		setBrbrenddate(brBrenddate);
		setCopopid(copOpid);
		setCopopname(copOpname);
		setBrbrcreatedate(brBrcreatedate);
		setMopopid(mopOpid);
		setMopopname(mopOpname);
		setBrbrmodifydate(brBrmodifydate);
		setBrbrremark(brBrremark);
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setCkckcode(ckCkcode);
		setCkckname(ckCkname);
		setBrkbrkcode(brkBrkcode);
		setBrkbrkname(brkBrkname);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
	}

	public void setPrbrid(Long prBrid) {
		this.setField(0, prBrid);
	}

	public String getPrbrid() {
		return this.getField(0);
	}

	public void setPrprinsurancesign(String prPrinsurancesign) {
		this.setField(1, prPrinsurancesign);
	}

	public String getPrprinsurancesign() {
		return this.getField(1);
	}

	public void setPrprdoxtransfergw(BigDecimal prPrdoxtransfergw) {
		this.setField(2, prPrdoxtransfergw);
	}

	public String getPrprdoxtransfergw() {
		return this.getField(2);
	}

	public void setPrprsimpleinputsign(String prPrsimpleinputsign) {
		this.setField(3, prPrsimpleinputsign);
	}

	public String getPrprsimpleinputsign() {
		return this.getField(3);
	}

	public void setPrprcollectsign(String prPrcollectsign) {
		this.setField(4, prPrcollectsign);
	}

	public String getPrprcollectsign() {
		return this.getField(4);
	}

	public void setPrprallcussignoutbyoriginwbsign(String prPrallcussignoutbyoriginwbsign) {
		this.setField(5, prPrallcussignoutbyoriginwbsign);
	}

	public String getPrprallcussignoutbyoriginwbsign() {
		return this.getField(5);
	}

	public void setBrbrname(String brBrname) {
		this.setField(6, brBrname);
	}

	public String getBrbrname() {
		return this.getField(6);
	}

	public void setBrbrename(String brBrename) {
		this.setField(7, brBrename);
	}

	public String getBrbrename() {
		return this.getField(7);
	}

	public void setBrbrstartdate(Date brBrstartdate) {
		this.setField(8, brBrstartdate);
	}

	public String getBrbrstartdate() {
		return this.getField(8);
	}

	public void setBrbrenddate(Date brBrenddate) {
		this.setField(9, brBrenddate);
	}

	public String getBrbrenddate() {
		return this.getField(9);
	}

	public void setCopopid(Long copOpid) {
		this.setField(10, copOpid);
	}

	public String getCopopid() {
		return this.getField(10);
	}

	public void setCopopname(String copOpname) {
		this.setField(11, copOpname);
	}

	public String getCopopname() {
		return this.getField(11);
	}

	public void setBrbrcreatedate(Date brBrcreatedate) {
		this.setField(12, brBrcreatedate);
	}

	public String getBrbrcreatedate() {
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

	public void setBrbrmodifydate(Date brBrmodifydate) {
		this.setField(15, brBrmodifydate);
	}

	public String getBrbrmodifydate() {
		return this.getField(15);
	}

	public void setBrbrremark(String brBrremark) {
		this.setField(16, brBrremark);
	}

	public String getBrbrremark() {
		return this.getField(16);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(17, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(17);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(18, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(18);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(19, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(19);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(20, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(20);
	}

	public void setCkckcode(String ckCkcode) {
		this.setField(21, ckCkcode);
	}

	public String getCkckcode() {
		return this.getField(21);
	}

	public void setCkckname(String ckCkname) {
		this.setField(22, ckCkname);
	}

	public String getCkckname() {
		return this.getField(22);
	}

	public void setBrkbrkcode(String brkBrkcode) {
		this.setField(23, brkBrkcode);
	}

	public String getBrkbrkcode() {
		return this.getField(23);
	}

	public void setBrkbrkname(String brkBrkname) {
		this.setField(24, brkBrkname);
	}

	public String getBrkbrkname() {
		return this.getField(24);
	}

	public void setSssscode(String ssSscode) {
		this.setField(25, ssSscode);
	}

	public String getSssscode() {
		return this.getField(25);
	}

	public void setSsssname(String ssSsname) {
		this.setField(26, ssSsname);
	}

	public String getSsssname() {
		return this.getField(26);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
