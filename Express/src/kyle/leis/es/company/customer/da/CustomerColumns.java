package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerColumns() {
		m_astrColumns = new String[55];
	}
	
	public CustomerColumns(String cmCocode, 
            String cmCminvoicesign, String cmCmodanoticesign, 
            String cmCmodaholdsign, Date coCocreatedate, 
            Date coComodifydate, Date coCoauditdate, 
            String coOpcodeconfirm, Date coCoconfirmdate, 
            String coConame, String coCoename, 
            String coCosname, String coCosename, 
            String coColabelcode, String coCoaddress, 
            String coCopostcode, String coCoremark, 
            String coCowebsite, String cstCstcode, 
            String cstCstname, String ctCtcode, 
            String ctCtname, Long csopOpid, 
            String csopOpname, Long ssopOpid, 
            String ssopOpname, Long dunopOpid, 
            String dunopOpname, BigDecimal cmCmcreditlimit, 
            String aopOpname, Long aopOpid, 
            String mopOpname, Long mopOpid, 
            String copOpname, Long copOpid, 
            String eeEecode, String eeEesname, 
            String cosCscode, String cosCsname, 
            String cmCmpayablebankaccount, String coCocarryoversign, 
            Date coCocarryoverdate, String cmCmwebtrackneedlogin, 
            BigDecimal cmCmmaxreceivabletotal, String cmCmmaxrttype, 
            String cmCmallowprintlabelsign, BigDecimal cmCmtemporarycreditlimit, 
            Date cmCmtclstartdate, Date cmCmtclenddate, 
            String cmCmprintchildlabelsign, String cmCmstructruecode, 
            String cmparentCocode, BigDecimal cmCmholdhwrate, 
            String cmCmwebinputchangeswbsign, String cmCmarrearprintlabelsign){
		m_astrColumns = new String[55];
		setCmcocode(cmCocode);
		setCmcminvoicesign(cmCminvoicesign);
		setCmcmodanoticesign(cmCmodanoticesign);
		setCmcmodaholdsign(cmCmodaholdsign);
		setCococreatedate(coCocreatedate);
		setCocomodifydate(coComodifydate);
		setCocoauditdate(coCoauditdate);
		setCoopcodeconfirm(coOpcodeconfirm);
		setCococonfirmdate(coCoconfirmdate);
		setCoconame(coConame);
		setCocoename(coCoename);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setCocolabelcode(coColabelcode);
		setCocoaddress(coCoaddress);
		setCocopostcode(coCopostcode);
		setCocoremark(coCoremark);
		setCocowebsite(coCowebsite);
		setCstcstcode(cstCstcode);
		setCstcstname(cstCstname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
		setCsopopid(csopOpid);
		setCsopopname(csopOpname);
		setSsopopid(ssopOpid);
		setSsopopname(ssopOpname);
		setDunopopid(dunopOpid);
		setDunopopname(dunopOpname);
		setCmcmcreditlimit(cmCmcreditlimit);
		setAopopname(aopOpname);
		setAopopid(aopOpid);
		setMopopname(mopOpname);
		setMopopid(mopOpid);
		setCopopname(copOpname);
		setCopopid(copOpid);
		setEeeecode(eeEecode);
		setEeeesname(eeEesname);
		setCoscscode(cosCscode);
		setCoscsname(cosCsname);
		setCmcmpayablebankaccount(cmCmpayablebankaccount);
		setCococarryoversign(coCocarryoversign);
		setCococarryoverdate(coCocarryoverdate);
		setCmcmwebtrackneedlogin(cmCmwebtrackneedlogin);
		setCmcmmaxreceivabletotal(cmCmmaxreceivabletotal);
		setCmcmmaxrttype(cmCmmaxrttype);
		setCmcmallowprintlabelsign(cmCmallowprintlabelsign);
		setCmcmtemporarycreditlimit(cmCmtemporarycreditlimit);
		setCmcmtclstartdate(cmCmtclstartdate);
		setCmcmtclenddate(cmCmtclenddate);
		setCmcmprintchildlabelsign(cmCmprintchildlabelsign);
		setCmcmstructruecode(cmCmstructruecode);
		setCmparentcocode(cmparentCocode);
		setCmcmholdhwrate(cmCmholdhwrate);
		setCmcmwebinputchangeswbsign(cmCmwebinputchangeswbsign);
		setCmcmarrearprintlabelsign(cmCmarrearprintlabelsign);
	}

	public void setCmcocode(String cmCocode) {
		this.setField(0, cmCocode);
	}

	public String getCmcocode() {
		return this.getField(0);
	}

	public void setCmcminvoicesign(String cmCminvoicesign) {
		this.setField(1, cmCminvoicesign);
	}

	public String getCmcminvoicesign() {
		return this.getField(1);
	}

	public void setCmcmodanoticesign(String cmCmodanoticesign) {
		this.setField(2, cmCmodanoticesign);
	}

	public String getCmcmodanoticesign() {
		return this.getField(2);
	}

	public void setCmcmodaholdsign(String cmCmodaholdsign) {
		this.setField(3, cmCmodaholdsign);
	}

	public String getCmcmodaholdsign() {
		return this.getField(3);
	}

	public void setCococreatedate(Date coCocreatedate) {
		this.setField(4, coCocreatedate);
	}

	public String getCococreatedate() {
		return this.getField(4);
	}

	public void setCocomodifydate(Date coComodifydate) {
		this.setField(5, coComodifydate);
	}

	public String getCocomodifydate() {
		return this.getField(5);
	}

	public void setCocoauditdate(Date coCoauditdate) {
		this.setField(6, coCoauditdate);
	}

	public String getCocoauditdate() {
		return this.getField(6);
	}

	public void setCoopcodeconfirm(String coOpcodeconfirm) {
		this.setField(7, coOpcodeconfirm);
	}

	public String getCoopcodeconfirm() {
		return this.getField(7);
	}

	public void setCococonfirmdate(Date coCoconfirmdate) {
		this.setField(8, coCoconfirmdate);
	}

	public String getCococonfirmdate() {
		return this.getField(8);
	}

	public void setCoconame(String coConame) {
		this.setField(9, coConame);
	}

	public String getCoconame() {
		return this.getField(9);
	}

	public void setCocoename(String coCoename) {
		this.setField(10, coCoename);
	}

	public String getCocoename() {
		return this.getField(10);
	}

	public void setCocosname(String coCosname) {
		this.setField(11, coCosname);
	}

	public String getCocosname() {
		return this.getField(11);
	}

	public void setCocosename(String coCosename) {
		this.setField(12, coCosename);
	}

	public String getCocosename() {
		return this.getField(12);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(13, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(13);
	}

	public void setCocoaddress(String coCoaddress) {
		this.setField(14, coCoaddress);
	}

	public String getCocoaddress() {
		return this.getField(14);
	}

	public void setCocopostcode(String coCopostcode) {
		this.setField(15, coCopostcode);
	}

	public String getCocopostcode() {
		return this.getField(15);
	}

	public void setCocoremark(String coCoremark) {
		this.setField(16, coCoremark);
	}

	public String getCocoremark() {
		return this.getField(16);
	}

	public void setCocowebsite(String coCowebsite) {
		this.setField(17, coCowebsite);
	}

	public String getCocowebsite() {
		return this.getField(17);
	}

	public void setCstcstcode(String cstCstcode) {
		this.setField(18, cstCstcode);
	}

	public String getCstcstcode() {
		return this.getField(18);
	}

	public void setCstcstname(String cstCstname) {
		this.setField(19, cstCstname);
	}

	public String getCstcstname() {
		return this.getField(19);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(20, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(20);
	}

	public void setCtctname(String ctCtname) {
		this.setField(21, ctCtname);
	}

	public String getCtctname() {
		return this.getField(21);
	}

	public void setCsopopid(Long csopOpid) {
		this.setField(22, csopOpid);
	}

	public String getCsopopid() {
		return this.getField(22);
	}

	public void setCsopopname(String csopOpname) {
		this.setField(23, csopOpname);
	}

	public String getCsopopname() {
		return this.getField(23);
	}

	public void setSsopopid(Long ssopOpid) {
		this.setField(24, ssopOpid);
	}

	public String getSsopopid() {
		return this.getField(24);
	}

	public void setSsopopname(String ssopOpname) {
		this.setField(25, ssopOpname);
	}

	public String getSsopopname() {
		return this.getField(25);
	}

	public void setDunopopid(Long dunopOpid) {
		this.setField(26, dunopOpid);
	}

	public String getDunopopid() {
		return this.getField(26);
	}

	public void setDunopopname(String dunopOpname) {
		this.setField(27, dunopOpname);
	}

	public String getDunopopname() {
		return this.getField(27);
	}

	public void setCmcmcreditlimit(BigDecimal cmCmcreditlimit) {
		this.setField(28, cmCmcreditlimit);
	}

	public String getCmcmcreditlimit() {
		return this.getField(28);
	}

	public void setAopopname(String aopOpname) {
		this.setField(29, aopOpname);
	}

	public String getAopopname() {
		return this.getField(29);
	}

	public void setAopopid(Long aopOpid) {
		this.setField(30, aopOpid);
	}

	public String getAopopid() {
		return this.getField(30);
	}

	public void setMopopname(String mopOpname) {
		this.setField(31, mopOpname);
	}

	public String getMopopname() {
		return this.getField(31);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(32, mopOpid);
	}

	public String getMopopid() {
		return this.getField(32);
	}

	public void setCopopname(String copOpname) {
		this.setField(33, copOpname);
	}

	public String getCopopname() {
		return this.getField(33);
	}

	public void setCopopid(Long copOpid) {
		this.setField(34, copOpid);
	}

	public String getCopopid() {
		return this.getField(34);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(35, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(35);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(36, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(36);
	}

	public void setCoscscode(String cosCscode) {
		this.setField(37, cosCscode);
	}

	public String getCoscscode() {
		return this.getField(37);
	}

	public void setCoscsname(String cosCsname) {
		this.setField(38, cosCsname);
	}

	public String getCoscsname() {
		return this.getField(38);
	}

	public void setCmcmpayablebankaccount(String cmCmpayablebankaccount) {
		this.setField(39, cmCmpayablebankaccount);
	}

	public String getCmcmpayablebankaccount() {
		return this.getField(39);
	}

	public void setCococarryoversign(String coCocarryoversign) {
		this.setField(40, coCocarryoversign);
	}

	public String getCococarryoversign() {
		return this.getField(40);
	}

	public void setCococarryoverdate(Date coCocarryoverdate) {
		this.setField(41, coCocarryoverdate);
	}

	public String getCococarryoverdate() {
		return this.getField(41);
	}

	public void setCmcmwebtrackneedlogin(String cmCmwebtrackneedlogin) {
		this.setField(42, cmCmwebtrackneedlogin);
	}

	public String getCmcmwebtrackneedlogin() {
		return this.getField(42);
	}

	public void setCmcmmaxreceivabletotal(BigDecimal cmCmmaxreceivabletotal) {
		this.setField(43, cmCmmaxreceivabletotal);
	}

	public String getCmcmmaxreceivabletotal() {
		return this.getField(43);
	}

	public void setCmcmmaxrttype(String cmCmmaxrttype) {
		this.setField(44, cmCmmaxrttype);
	}

	public String getCmcmmaxrttype() {
		return this.getField(44);
	}

	public void setCmcmallowprintlabelsign(String cmCmallowprintlabelsign) {
		this.setField(45, cmCmallowprintlabelsign);
	}

	public String getCmcmallowprintlabelsign() {
		return this.getField(45);
	}

	public void setCmcmtemporarycreditlimit(BigDecimal cmCmtemporarycreditlimit) {
		this.setField(46, cmCmtemporarycreditlimit);
	}

	public String getCmcmtemporarycreditlimit() {
		return this.getField(46);
	}

	public void setCmcmtclstartdate(Date cmCmtclstartdate) {
		this.setField(47, cmCmtclstartdate);
	}

	public String getCmcmtclstartdate() {
		return this.getField(47);
	}

	public void setCmcmtclenddate(Date cmCmtclenddate) {
		this.setField(48, cmCmtclenddate);
	}

	public String getCmcmtclenddate() {
		return this.getField(48);
	}

	public void setCmcmprintchildlabelsign(String cmCmprintchildlabelsign) {
		this.setField(49, cmCmprintchildlabelsign);
	}

	public String getCmcmprintchildlabelsign() {
		return this.getField(49);
	}

	public void setCmcmstructruecode(String cmCmstructruecode) {
		this.setField(50, cmCmstructruecode);
	}

	public String getCmcmstructruecode() {
		return this.getField(50);
	}

	public void setCmparentcocode(String cmparentCocode) {
		this.setField(51, cmparentCocode);
	}

	public String getCmparentcocode() {
		return this.getField(51);
	}

	public void setCmcmholdhwrate(BigDecimal cmCmholdhwrate) {
		this.setField(52, cmCmholdhwrate);
	}

	public String getCmcmholdhwrate() {
		return this.getField(52);
	}

	public void setCmcmwebinputchangeswbsign(String cmCmwebinputchangeswbsign) {
		this.setField(53, cmCmwebinputchangeswbsign);
	}

	public String getCmcmwebinputchangeswbsign() {
		return this.getField(53);
	}

	public void setCmcmarrearprintlabelsign(String cmCmarrearprintlabelsign) {
		this.setField(54, cmCmarrearprintlabelsign);
	}

	public String getCmcmarrearprintlabelsign() {
		return this.getField(54);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
