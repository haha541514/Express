package kyle.leis.es.company.supplier.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class SupplierColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SupplierColumns() {
		m_astrColumns = new String[31];
	}
	
	public SupplierColumns(String spCocode, 
            String spSpaccount, String spSpmanifestseriesnumber, 
            Date coCocreatedate, Date coComodifydate, 
            Date coCoauditdate, String coOpcodeconfirm, 
            Date coCoconfirmdate, String coConame, 
            String coCoename, String coCosname, 
            String coCosename, String coColabelcode, 
            String coCoaddress, String coCopostcode, 
            String coCoremark, String coCowebsite, 
            String cstCstcode, String cstCstname, 
            String aopOpname, Long aopOpid, 
            String mopOpname, Long mopOpid, 
            String copOpname, Long copOpid, 
            String eeEecode, String eeEesname, 
            String cosCscode, String cosCsname, 
            String coCocarryoversign, Date coCocarryoverdate){
		m_astrColumns = new String[31];
		setSpcocode(spCocode);
		setSpspaccount(spSpaccount);
		setSpspmanifestseriesnumber(spSpmanifestseriesnumber);
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
		setCococarryoversign(coCocarryoversign);
		setCococarryoverdate(coCocarryoverdate);
	}

	public void setSpcocode(String spCocode) {
		this.setField(0, spCocode);
	}

	public String getSpcocode() {
		return this.getField(0);
	}

	public void setSpspaccount(String spSpaccount) {
		this.setField(1, spSpaccount);
	}

	public String getSpspaccount() {
		return this.getField(1);
	}

	public void setSpspmanifestseriesnumber(String spSpmanifestseriesnumber) {
		this.setField(2, spSpmanifestseriesnumber);
	}

	public String getSpspmanifestseriesnumber() {
		return this.getField(2);
	}

	public void setCococreatedate(Date coCocreatedate) {
		this.setField(3, coCocreatedate);
	}

	public String getCococreatedate() {
		return this.getField(3);
	}

	public void setCocomodifydate(Date coComodifydate) {
		this.setField(4, coComodifydate);
	}

	public String getCocomodifydate() {
		return this.getField(4);
	}

	public void setCocoauditdate(Date coCoauditdate) {
		this.setField(5, coCoauditdate);
	}

	public String getCocoauditdate() {
		return this.getField(5);
	}

	public void setCoopcodeconfirm(String coOpcodeconfirm) {
		this.setField(6, coOpcodeconfirm);
	}

	public String getCoopcodeconfirm() {
		return this.getField(6);
	}

	public void setCococonfirmdate(Date coCoconfirmdate) {
		this.setField(7, coCoconfirmdate);
	}

	public String getCococonfirmdate() {
		return this.getField(7);
	}

	public void setCoconame(String coConame) {
		this.setField(8, coConame);
	}

	public String getCoconame() {
		return this.getField(8);
	}

	public void setCocoename(String coCoename) {
		this.setField(9, coCoename);
	}

	public String getCocoename() {
		return this.getField(9);
	}

	public void setCocosname(String coCosname) {
		this.setField(10, coCosname);
	}

	public String getCocosname() {
		return this.getField(10);
	}

	public void setCocosename(String coCosename) {
		this.setField(11, coCosename);
	}

	public String getCocosename() {
		return this.getField(11);
	}

	public void setCocolabelcode(String coColabelcode) {
		this.setField(12, coColabelcode);
	}

	public String getCocolabelcode() {
		return this.getField(12);
	}

	public void setCocoaddress(String coCoaddress) {
		this.setField(13, coCoaddress);
	}

	public String getCocoaddress() {
		return this.getField(13);
	}

	public void setCocopostcode(String coCopostcode) {
		this.setField(14, coCopostcode);
	}

	public String getCocopostcode() {
		return this.getField(14);
	}

	public void setCocoremark(String coCoremark) {
		this.setField(15, coCoremark);
	}

	public String getCocoremark() {
		return this.getField(15);
	}

	public void setCocowebsite(String coCowebsite) {
		this.setField(16, coCowebsite);
	}

	public String getCocowebsite() {
		return this.getField(16);
	}

	public void setCstcstcode(String cstCstcode) {
		this.setField(17, cstCstcode);
	}

	public String getCstcstcode() {
		return this.getField(17);
	}

	public void setCstcstname(String cstCstname) {
		this.setField(18, cstCstname);
	}

	public String getCstcstname() {
		return this.getField(18);
	}

	public void setAopopname(String aopOpname) {
		this.setField(19, aopOpname);
	}

	public String getAopopname() {
		return this.getField(19);
	}

	public void setAopopid(Long aopOpid) {
		this.setField(20, aopOpid);
	}

	public String getAopopid() {
		return this.getField(20);
	}

	public void setMopopname(String mopOpname) {
		this.setField(21, mopOpname);
	}

	public String getMopopname() {
		return this.getField(21);
	}

	public void setMopopid(Long mopOpid) {
		this.setField(22, mopOpid);
	}

	public String getMopopid() {
		return this.getField(22);
	}

	public void setCopopname(String copOpname) {
		this.setField(23, copOpname);
	}

	public String getCopopname() {
		return this.getField(23);
	}

	public void setCopopid(Long copOpid) {
		this.setField(24, copOpid);
	}

	public String getCopopid() {
		return this.getField(24);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(25, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(25);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(26, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(26);
	}

	public void setCoscscode(String cosCscode) {
		this.setField(27, cosCscode);
	}

	public String getCoscscode() {
		return this.getField(27);
	}

	public void setCoscsname(String cosCsname) {
		this.setField(28, cosCsname);
	}

	public String getCoscsname() {
		return this.getField(28);
	}

	public void setCococarryoversign(String coCocarryoversign) {
		this.setField(29, coCocarryoversign);
	}

	public String getCococarryoversign() {
		return this.getField(29);
	}

	public void setCococarryoverdate(Date coCocarryoverdate) {
		this.setField(30, coCocarryoverdate);
	}

	public String getCococarryoverdate() {
		return this.getField(30);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
