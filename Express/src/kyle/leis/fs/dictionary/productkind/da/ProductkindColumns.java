package kyle.leis.fs.dictionary.productkind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ProductkindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ProductkindColumns() {
		m_astrColumns = new String[25];
	}
	
	public ProductkindColumns(String pkPkcode, 
            String pkPkname, String pkPkename, 
            String pkPksname, String pkPksename, 
            String pkPkdescription, String pkPksigninrestrictsign, 
            String pkPksaletrialsign, String ssSscode, 
            String ssSsname, String pkShowserverewbcode,
            String pkBillingbybatchwaysign,String pksigninneedpostcode,
            String pkPcwrestrictformula,String pkPdsrestrictformula,
            String pkArrearallowsignout, String pkPaybillingbybatchwaysign,
            String pkSiprintselflabelcodesign,String strPkIntroductionlink,
            String strChncode, String pkStructurecode,
            String strSsgcode, String eeStructurecode,
            String eeCode, String eeSname){
		m_astrColumns = new String[25];
		setPkpkcode(pkPkcode);
		setPkpkname(pkPkname);
		setPkpkename(pkPkename);
		setPkpksname(pkPksname);
		setPkpksename(pkPksename);
		setPkpkdescription(pkPkdescription);
		setPkpksigninrestrictsign(pkPksigninrestrictsign);
		setPkpksaletrialsign(pkPksaletrialsign);
		setSssscode(ssSscode);
		setSsssname(ssSsname);
		setPkshowserverewbcode(pkShowserverewbcode);
		setPkbillingbybatchwaysign(pkBillingbybatchwaysign);
		setPksigninneedpostcode(pksigninneedpostcode);
		setPkpcwrestrictformula(pkPcwrestrictformula);
		setPkpdsrestrictformula(pkPdsrestrictformula);
		setPkarrearallowsignout(pkArrearallowsignout);
		setPaybillingbybatchwaysign(pkPaybillingbybatchwaysign);
		setPksiprintselflabelcodesign(pkSiprintselflabelcodesign);
		setPkIntroductionlink(strPkIntroductionlink);
		setChncode(strChncode);
		setPkstructurecode(pkStructurecode);
		setSsgcode(strSsgcode);
		setEestructurecode(eeStructurecode);
		setEecode(eeCode);
		setEesname(eeSname);
	}

	public void setPkpkcode(String pkPkcode) {
		this.setField(0, pkPkcode);
	}

	public String getPkpkcode() {
		return this.getField(0);
	}

	public void setPkpkname(String pkPkname) {
		this.setField(1, pkPkname);
	}

	public String getPkpkname() {
		return this.getField(1);
	}

	public void setPkpkename(String pkPkename) {
		this.setField(2, pkPkename);
	}

	public String getPkpkename() {
		return this.getField(2);
	}

	public void setPkpksname(String pkPksname) {
		this.setField(3, pkPksname);
	}

	public String getPkpksname() {
		return this.getField(3);
	}

	public void setPkpksename(String pkPksename) {
		this.setField(4, pkPksename);
	}

	public String getPkpksename() {
		return this.getField(4);
	}

	public void setPkpkdescription(String pkPkdescription) {
		this.setField(5, pkPkdescription);
	}

	public String getPkpkdescription() {
		return this.getField(5);
	}

	public void setPkpksigninrestrictsign(String pkPksigninrestrictsign) {
		this.setField(6, pkPksigninrestrictsign);
	}

	public String getPkpksigninrestrictsign() {
		return this.getField(6);
	}

	public void setPkpksaletrialsign(String pkPksaletrialsign) {
		this.setField(7, pkPksaletrialsign);
	}

	public String getPkpksaletrialsign() {
		return this.getField(7);
	}

	public void setSssscode(String ssSscode) {
		this.setField(8, ssSscode);
	}

	public String getSssscode() {
		return this.getField(8);
	}

	public void setSsssname(String ssSsname) {
		this.setField(9, ssSsname);
	}

	public String getSsssname() {
		return this.getField(9);
	}

	public void setPkshowserverewbcode(String pkPkshowserverewbcode) {
		this.setField(10, pkPkshowserverewbcode);
	}

	public String getPkshowserverewbcode() {
		return this.getField(10);
	}	
	
	public void setPkbillingbybatchwaysign(String pkBillingbybatchwaysign) {
		this.setField(11, pkBillingbybatchwaysign);
	}

	public String getPkbillingbybatchwaysign() {
		return this.getField(11);
	}		
	public void setPksigninneedpostcode(String pksigninneedpostcode) {
		this.setField(12, pksigninneedpostcode);
	}

	public String getPksigninneedpostcode() {
		return this.getField(12);
	}
	public void setPkpcwrestrictformula(String pkPcwrestrictformula){
		this.setField(13, pkPcwrestrictformula);
	}
	public String getPkpcwrestrictformula(){
		return this.getField(13);
	}
	public void setPkpdsrestrictformula(String pkPdsrestrictformula){
		this.setField(14, pkPdsrestrictformula);
	}
	public String getPkpdsrestrictformula(){
		return this.getField(14);
	}
	
	public void setPkarrearallowsignout(String pkArrearallowsignout){
		this.setField(15, pkArrearallowsignout);
	}
	public String getPkarrearallowsignout(){
		return this.getField(15);
	}
	
	public void setPaybillingbybatchwaysign(String pkPaybillingbybatchwaysign){
		this.setField(16, pkPaybillingbybatchwaysign);
	}
	public String getPaybillingbybatchwaysign(){
		return this.getField(16);
	}	
	
	public void setPksiprintselflabelcodesign(String strPksiprintselflabelcodesign) {
		this.setField(17, strPksiprintselflabelcodesign);
	}

	public String getPksiprintselflabelcodesign() {
		return this.getField(17);
	}	
	
	public void setPkIntroductionlink(String strPkIntroductionlink) {
		this.setField(18, strPkIntroductionlink);
	}

	public String getPkIntroductionlink() {
		return this.getField(18);
	}		
	
	public void setChncode(String strChncode) {
		this.setField(19, strChncode);
	}

	public String getChncode() {
		return this.getField(19);
	}	
	
	public void setPkstructurecode(String strPkstructurecode) {
		this.setField(20, strPkstructurecode);
	}

	public String getPkstructurecode() {
		return this.getField(20);
	}		
	
	public void setSsgcode(String strSsgcode) {
		this.setField(21, strSsgcode);
	}

	public String getSsgcode() {
		return this.getField(21);
	}		
	
	public void setEestructurecode(String strEestructurecode) {
		this.setField(22, strEestructurecode);
	}

	public String getEestructurecode() {
		return this.getField(22);
	}	
	
	public void setEecode(String strEecode) {
		this.setField(23, strEecode);
	}

	public String getEecode() {
		return this.getField(23);
	}	
	
	public void setEesname(String strEesname) {
		this.setField(24, strEesname);
	}

	public String getEesname() {
		return this.getField(24);
	}		
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
