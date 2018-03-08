package kyle.leis.fs.dictionary.enterpriseelement.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class BranchColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public BranchColumns() {
		m_astrColumns = new String[38];
	}
	
	public BranchColumns(String brEecode, 
            Long brBropidmanager, Long brBropidcustomerservice, 
            Long brBropiddunner, Long brBropidsaler, 
            String eeEecode, int eeEelevel, 
            String eeEestructurecode, String eeEename, 
            String eeEeename, String eeEesname, 
            String eeEeesname, String eeEeaddress, 
            String eeEeeaddress, String eeEepostcode, 
            String eeEeemail, String eeEetelephone, 
            String eeEefax, long eeEeopidcreator, 
            Date eeEecreatedate, long eeEeopidmodifier, 
            Date eeEemodifydate, String dtDtcode, 
            String dtDtname, String dtDtename, 
            String dtDtstatecode, String dtDtstatename, 
            String dtDtgrade, String rgRgcode, 
            String rgRgname, String rgRgename, 
            String eekEekcode, String eekEekname, 
            String eekEekename, String stStcode, 
            String stStname, String ctCtcode, 
            String ctCtname){
		m_astrColumns = new String[38];
		setBreecode(brEecode);
		setBrbropidmanager(brBropidmanager);
		setBrbropidcustomerservice(brBropidcustomerservice);
		setBrbropiddunner(brBropiddunner);
		setBrbropidsaler(brBropidsaler);
		setEeeecode(eeEecode);
		setEeeelevel(eeEelevel);
		setEeeestructurecode(eeEestructurecode);
		setEeeename(eeEename);
		setEeeeename(eeEeename);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
		setEeeeaddress(eeEeaddress);
		setEeeeeaddress(eeEeeaddress);
		setEeeepostcode(eeEepostcode);
		setEeeeemail(eeEeemail);
		setEeeetelephone(eeEetelephone);
		setEeeefax(eeEefax);
		setEeeeopidcreator(eeEeopidcreator);
		setEeeecreatedate(eeEecreatedate);
		setEeeeopidmodifier(eeEeopidmodifier);
		setEeeemodifydate(eeEemodifydate);
		setDtdtcode(dtDtcode);
		setDtdtname(dtDtname);
		setDtdtename(dtDtename);
		setDtdtstatecode(dtDtstatecode);
		setDtdtstatename(dtDtstatename);
		setDtdtgrade(dtDtgrade);
		setRgrgcode(rgRgcode);
		setRgrgname(rgRgname);
		setRgrgename(rgRgename);
		setEekeekcode(eekEekcode);
		setEekeekname(eekEekname);
		setEekeekename(eekEekename);
		setStstcode(stStcode);
		setStstname(stStname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
	}

	public void setBreecode(String brEecode) {
		this.setField(0, brEecode);
	}

	public String getBreecode() {
		return this.getField(0);
	}

	public void setBrbropidmanager(Long brBropidmanager) {
		this.setField(1, brBropidmanager);
	}

	public String getBrbropidmanager() {
		return this.getField(1);
	}

	public void setBrbropidcustomerservice(Long brBropidcustomerservice) {
		this.setField(2, brBropidcustomerservice);
	}

	public String getBrbropidcustomerservice() {
		return this.getField(2);
	}

	public void setBrbropiddunner(Long brBropiddunner) {
		this.setField(3, brBropiddunner);
	}

	public String getBrbropiddunner() {
		return this.getField(3);
	}

	public void setBrbropidsaler(Long brBropidsaler) {
		this.setField(4, brBropidsaler);
	}

	public String getBrbropidsaler() {
		return this.getField(4);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(5, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(5);
	}

	public void setEeeelevel(int eeEelevel) {
		this.setField(6, eeEelevel);
	}

	public String getEeeelevel() {
		return this.getField(6);
	}

	public void setEeeestructurecode(String eeEestructurecode) {
		this.setField(7, eeEestructurecode);
	}

	public String getEeeestructurecode() {
		return this.getField(7);
	}

	public void setEeeename(String eeEename) {
		this.setField(8, eeEename);
	}

	public String getEeeename() {
		return this.getField(8);
	}

	public void setEeeeename(String eeEeename) {
		this.setField(9, eeEeename);
	}

	public String getEeeeename() {
		return this.getField(9);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(10, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(10);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(11, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(11);
	}

	public void setEeeeaddress(String eeEeaddress) {
		this.setField(12, eeEeaddress);
	}

	public String getEeeeaddress() {
		return this.getField(12);
	}

	public void setEeeeeaddress(String eeEeeaddress) {
		this.setField(13, eeEeeaddress);
	}

	public String getEeeeeaddress() {
		return this.getField(13);
	}

	public void setEeeepostcode(String eeEepostcode) {
		this.setField(14, eeEepostcode);
	}

	public String getEeeepostcode() {
		return this.getField(14);
	}

	public void setEeeeemail(String eeEeemail) {
		this.setField(15, eeEeemail);
	}

	public String getEeeeemail() {
		return this.getField(15);
	}

	public void setEeeetelephone(String eeEetelephone) {
		this.setField(16, eeEetelephone);
	}

	public String getEeeetelephone() {
		return this.getField(16);
	}

	public void setEeeefax(String eeEefax) {
		this.setField(17, eeEefax);
	}

	public String getEeeefax() {
		return this.getField(17);
	}

	public void setEeeeopidcreator(long eeEeopidcreator) {
		this.setField(18, eeEeopidcreator);
	}

	public String getEeeeopidcreator() {
		return this.getField(18);
	}

	public void setEeeecreatedate(Date eeEecreatedate) {
		this.setField(19, eeEecreatedate);
	}

	public String getEeeecreatedate() {
		return this.getField(19);
	}

	public void setEeeeopidmodifier(long eeEeopidmodifier) {
		this.setField(20, eeEeopidmodifier);
	}

	public String getEeeeopidmodifier() {
		return this.getField(20);
	}

	public void setEeeemodifydate(Date eeEemodifydate) {
		this.setField(21, eeEemodifydate);
	}

	public String getEeeemodifydate() {
		return this.getField(21);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(22, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(22);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(23, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(23);
	}

	public void setDtdtename(String dtDtename) {
		this.setField(24, dtDtename);
	}

	public String getDtdtename() {
		return this.getField(24);
	}

	public void setDtdtstatecode(String dtDtstatecode) {
		this.setField(25, dtDtstatecode);
	}

	public String getDtdtstatecode() {
		return this.getField(25);
	}

	public void setDtdtstatename(String dtDtstatename) {
		this.setField(26, dtDtstatename);
	}

	public String getDtdtstatename() {
		return this.getField(26);
	}

	public void setDtdtgrade(String dtDtgrade) {
		this.setField(27, dtDtgrade);
	}

	public String getDtdtgrade() {
		return this.getField(27);
	}

	public void setRgrgcode(String rgRgcode) {
		this.setField(28, rgRgcode);
	}

	public String getRgrgcode() {
		return this.getField(28);
	}

	public void setRgrgname(String rgRgname) {
		this.setField(29, rgRgname);
	}

	public String getRgrgname() {
		return this.getField(29);
	}

	public void setRgrgename(String rgRgename) {
		this.setField(30, rgRgename);
	}

	public String getRgrgename() {
		return this.getField(30);
	}

	public void setEekeekcode(String eekEekcode) {
		this.setField(31, eekEekcode);
	}

	public String getEekeekcode() {
		return this.getField(31);
	}

	public void setEekeekname(String eekEekname) {
		this.setField(32, eekEekname);
	}

	public String getEekeekname() {
		return this.getField(32);
	}

	public void setEekeekename(String eekEekename) {
		this.setField(33, eekEekename);
	}

	public String getEekeekename() {
		return this.getField(33);
	}

	public void setStstcode(String stStcode) {
		this.setField(34, stStcode);
	}

	public String getStstcode() {
		return this.getField(34);
	}

	public void setStstname(String stStname) {
		this.setField(35, stStname);
	}

	public String getStstname() {
		return this.getField(35);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(36, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(36);
	}

	public void setCtctname(String ctCtname) {
		this.setField(37, ctCtname);
	}

	public String getCtctname() {
		return this.getField(37);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
