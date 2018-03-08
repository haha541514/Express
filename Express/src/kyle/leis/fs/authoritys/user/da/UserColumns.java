package kyle.leis.fs.authoritys.user.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class UserColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UserColumns() {
		m_astrColumns = new String[47];
	}
	
	public UserColumns(Long opOpid, 
            String opOpcode, String opOpname, 
            String opOpsex, String Word, 
            String opOpename, String opOpsname, 
            String opOpidnumber, String opOpemail, 
            long opOpidcreator, Date opOpcreatedate, 
            long opOpidmodifier, Date opOpmodifydate, 
            String opOpaddress, String opOptelephone, 
            String opOpmobile, Date opOpconfirmdate, 
            Date opOpdimissiondate, String opOpmsnname, 
            String opOpqqnumber, String opOpfaxnumber, 
            String psPscode, String psPsname, 
            String psPsename, String eeEecode, 
            String eeEename, String eeEeename, 
            String eeEesname, String eeEeesname, 
            String fcFccode, String fcFcname, 
            String fcFcename, String coCocode, 
            String coConame, String coCoename, 
            String coCosname, String coCosename, 
            String dpDpcode, String dpDpname, 
            String dpDpename, String osOscode, 
            String osOsname, String opOpissuecontactpersonsign,
            String Ststcode, String Ststname, 
            String Ctctcode, String Ctctname){
		m_astrColumns = new String[47];
		setStstcode(Ststcode);
		setStstname(Ststname);
		setCtctcode(Ctctcode);
		setCtctname(Ctctname);
		setOpopid(opOpid);
		setOpopcode(opOpcode);
		setOpopname(opOpname);
		setOpopsex(opOpsex);
		setWord(Word);
		setOpopename(opOpename);
		setOpopsname(opOpsname);
		setOpopidnumber(opOpidnumber);
		setOpopemail(opOpemail);
		setOpopidcreator(opOpidcreator);
		setOpopcreatedate(opOpcreatedate);
		setOpopidmodifier(opOpidmodifier);
		setOpopmodifydate(opOpmodifydate);
		setOpopaddress(opOpaddress);
		setOpoptelephone(opOptelephone);
		setOpopmobile(opOpmobile);
		setOpopconfirmdate(opOpconfirmdate);
		setOpopdimissiondate(opOpdimissiondate);
		setOpopmsnname(opOpmsnname);
		setOpopqqnumber(opOpqqnumber);
		setOpopfaxnumber(opOpfaxnumber);
		setPspscode(psPscode);
		setPspsname(psPsname);
		setPspsename(psPsename);
		setEeeecode(eeEecode);
		setEeeename(eeEename);
		setEeeeename(eeEeename);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setFcfccode(fcFccode);
		setFcfcname(fcFcname);
		setFcfcename(fcFcename);
		setCococode(coCocode);
		setCoconame(coConame);
		setCocoename(coCoename);
		setCocosname(coCosname);
		setCocosename(coCosename);
		setDpdpcode(dpDpcode);
		setDpdpname(dpDpname);
		setDpdpename(dpDpename);
		setOsoscode(osOscode);
		setOsosname(osOsname);
		setOpopissuecontactpersonsign(opOpissuecontactpersonsign);
	}
	public void setStstcode(String Ststcode) {
		this.setField(43, Ststcode);
	}

	public String getStstcode() {
		return this.getField(43);
	}
	public void setStstname(String Ststname) {
		this.setField(44, Ststname);
	}

	public String getStstname() {
		return this.getField(44);
	}
	public void setCtctcode(String Ctctcode) {
		this.setField(45, Ctctcode);
	}

	public String getCtctcode() {
		return this.getField(45);
	}
	public void setCtctname(String Ctctname) {
		this.setField(46, Ctctname);
	}

	public String getCtctname() {
		return this.getField(46);
	}

	public void setOpopid(Long opOpid) {
		this.setField(0, opOpid);
	}

	public String getOpopid() {
		return this.getField(0);
	}

	public void setOpopcode(String opOpcode) {
		this.setField(1, opOpcode);
	}

	public String getOpopcode() {
		return this.getField(1);
	}

	public void setOpopname(String opOpname) {
		this.setField(2, opOpname);
	}

	public String getOpopname() {
		return this.getField(2);
	}

	public void setOpopsex(String opOpsex) {
		this.setField(3, opOpsex);
	}

	public String getOpopsex() {
		return this.getField(3);
	}

	public void setWord(String Word) {
		this.setField(4, Word);
	}

	public String getWord() {
		return this.getField(4);
	}

	public void setOpopename(String opOpename) {
		this.setField(5, opOpename);
	}

	public String getOpopename() {
		return this.getField(5);
	}

	public void setOpopsname(String opOpsname) {
		this.setField(6, opOpsname);
	}

	public String getOpopsname() {
		return this.getField(6);
	}

	public void setOpopidnumber(String opOpidnumber) {
		this.setField(7, opOpidnumber);
	}

	public String getOpopidnumber() {
		return this.getField(7);
	}

	public void setOpopemail(String opOpemail) {
		this.setField(8, opOpemail);
	}

	public String getOpopemail() {
		return this.getField(8);
	}

	public void setOpopidcreator(long opOpidcreator) {
		this.setField(9, opOpidcreator);
	}

	public String getOpopidcreator() {
		return this.getField(9);
	}

	public void setOpopcreatedate(Date opOpcreatedate) {
		this.setField(10, opOpcreatedate);
	}

	public String getOpopcreatedate() {
		return this.getField(10);
	}

	public void setOpopidmodifier(long opOpidmodifier) {
		this.setField(11, opOpidmodifier);
	}

	public String getOpopidmodifier() {
		return this.getField(11);
	}

	public void setOpopmodifydate(Date opOpmodifydate) {
		this.setField(12, opOpmodifydate);
	}

	public String getOpopmodifydate() {
		return this.getField(12);
	}

	public void setOpopaddress(String opOpaddress) {
		this.setField(13, opOpaddress);
	}

	public String getOpopaddress() {
		return this.getField(13);
	}

	public void setOpoptelephone(String opOptelephone) {
		this.setField(14, opOptelephone);
	}

	public String getOpoptelephone() {
		return this.getField(14);
	}

	public void setOpopmobile(String opOpmobile) {
		this.setField(15, opOpmobile);
	}

	public String getOpopmobile() {
		return this.getField(15);
	}

	public void setOpopconfirmdate(Date opOpconfirmdate) {
		this.setField(16, opOpconfirmdate);
	}

	public String getOpopconfirmdate() {
		return this.getField(16);
	}

	public void setOpopdimissiondate(Date opOpdimissiondate) {
		this.setField(17, opOpdimissiondate);
	}

	public String getOpopdimissiondate() {
		return this.getField(17);
	}

	public void setOpopmsnname(String opOpmsnname) {
		this.setField(18, opOpmsnname);
	}

	public String getOpopmsnname() {
		return this.getField(18);
	}

	public void setOpopqqnumber(String opOpqqnumber) {
		this.setField(19, opOpqqnumber);
	}

	public String getOpopqqnumber() {
		return this.getField(19);
	}

	public void setOpopfaxnumber(String opOpfaxnumber) {
		this.setField(20, opOpfaxnumber);
	}

	public String getOpopfaxnumber() {
		return this.getField(20);
	}

	public void setPspscode(String psPscode) {
		this.setField(21, psPscode);
	}

	public String getPspscode() {
		return this.getField(21);
	}

	public void setPspsname(String psPsname) {
		this.setField(22, psPsname);
	}

	public String getPspsname() {
		return this.getField(22);
	}

	public void setPspsename(String psPsename) {
		this.setField(23, psPsename);
	}

	public String getPspsename() {
		return this.getField(23);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(24, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(24);
	}

	public void setEeeename(String eeEename) {
		this.setField(25, eeEename);
	}

	public String getEeeename() {
		return this.getField(25);
	}

	public void setEeeeename(String eeEeename) {
		this.setField(26, eeEeename);
	}

	public String getEeeeename() {
		return this.getField(26);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(27, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(27);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(28, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(28);
	}

	public void setFcfccode(String fcFccode) {
		this.setField(29, fcFccode);
	}

	public String getFcfccode() {
		return this.getField(29);
	}

	public void setFcfcname(String fcFcname) {
		this.setField(30, fcFcname);
	}

	public String getFcfcname() {
		return this.getField(30);
	}

	public void setFcfcename(String fcFcename) {
		this.setField(31, fcFcename);
	}

	public String getFcfcename() {
		return this.getField(31);
	}

	public void setCococode(String coCocode) {
		this.setField(32, coCocode);
	}

	public String getCococode() {
		return this.getField(32);
	}

	public void setCoconame(String coConame) {
		this.setField(33, coConame);
	}

	public String getCoconame() {
		return this.getField(33);
	}

	public void setCocoename(String coCoename) {
		this.setField(34, coCoename);
	}

	public String getCocoename() {
		return this.getField(34);
	}

	public void setCocosname(String coCosname) {
		this.setField(35, coCosname);
	}

	public String getCocosname() {
		return this.getField(35);
	}

	public void setCocosename(String coCosename) {
		this.setField(36, coCosename);
	}

	public String getCocosename() {
		return this.getField(36);
	}

	public void setDpdpcode(String dpDpcode) {
		this.setField(37, dpDpcode);
	}

	public String getDpdpcode() {
		return this.getField(37);
	}

	public void setDpdpname(String dpDpname) {
		this.setField(38, dpDpname);
	}

	public String getDpdpname() {
		return this.getField(38);
	}

	public void setDpdpename(String dpDpename) {
		this.setField(39, dpDpename);
	}

	public String getDpdpename() {
		return this.getField(39);
	}

	public void setOsoscode(String osOscode) {
		this.setField(40, osOscode);
	}

	public String getOsoscode() {
		return this.getField(40);
	}

	public void setOsosname(String osOsname) {
		this.setField(41, osOsname);
	}

	public String getOsosname() {
		return this.getField(41);
	}

	public void setOpopissuecontactpersonsign(String opOpissuecontactpersonsign) {
		this.setField(42, opOpissuecontactpersonsign);
	}

	public String getOpopissuecontactpersonsign() {
		return this.getField(42);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
