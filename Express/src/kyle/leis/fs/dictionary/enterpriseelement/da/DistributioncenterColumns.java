package kyle.leis.fs.dictionary.enterpriseelement.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class DistributioncenterColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DistributioncenterColumns() {
		m_astrColumns = new String[39];
	}
	
	public DistributioncenterColumns(String dcEecode, 
            String dceeEecode, int dceeEelevel, 
            String dceeEestructurecode, String dceeEename, 
            String dceeEeename, String dceeEesname, 
            String dceeEeesname, String dceeEeaddress, 
            String dceeEeeaddress, String dceeEepostcode, 
            String dceeEeemail, String dceeEetelephone, 
            String dceeEefax, long dceeEeopidcreator, 
            Date dceeEecreatedate, long dceeEeopidmodifier, 
            Date dceeEemodifydate, String dceedtDtcode, 
            String dceedtDtname, String dceedtDtename, 
            String dceergRgcode, String dceergRgname, 
            String dceergRgename, String dceeeekEekcode, 
            String dceeeekEekname, String dceeeekEekename, 
            String dcdtbDtcode, String dcdtbDtname, 
            String dcdtbDtename, String dcbrEecode, 
            Long dcbrBropidmanager, Long dcbrBropidcustomerservice, 
            Long dcbrBropiddunner, Long dcbrBropidsaler, 
            String stStcode, String stStname, 
            String ctCtcode, String ctCtname){
		m_astrColumns = new String[39];
		setDceecode(dcEecode);
		setDceeeecode(dceeEecode);
		setDceeeelevel(dceeEelevel);
		setDceeeestructurecode(dceeEestructurecode);
		setDceeeename(dceeEename);
		setDceeeeename(dceeEeename);
		setDceeeesname(dceeEesname);
		setDceeeeesname(dceeEeesname);
		setDceeeeaddress(dceeEeaddress);
		setDceeeeeaddress(dceeEeeaddress);
		setDceeeepostcode(dceeEepostcode);
		setDceeeeemail(dceeEeemail);
		setDceeeetelephone(dceeEetelephone);
		setDceeeefax(dceeEefax);
		setDceeeeopidcreator(dceeEeopidcreator);
		setDceeeecreatedate(dceeEecreatedate);
		setDceeeeopidmodifier(dceeEeopidmodifier);
		setDceeeemodifydate(dceeEemodifydate);
		setDceedtdtcode(dceedtDtcode);
		setDceedtdtname(dceedtDtname);
		setDceedtdtename(dceedtDtename);
		setDceergrgcode(dceergRgcode);
		setDceergrgname(dceergRgname);
		setDceergrgename(dceergRgename);
		setDceeeekeekcode(dceeeekEekcode);
		setDceeeekeekname(dceeeekEekname);
		setDceeeekeekename(dceeeekEekename);
		setDcdtbdtcode(dcdtbDtcode);
		setDcdtbdtname(dcdtbDtname);
		setDcdtbdtename(dcdtbDtename);
		setDcbreecode(dcbrEecode);
		setDcbrbropidmanager(dcbrBropidmanager);
		setDcbrbropidcustomerservice(dcbrBropidcustomerservice);
		setDcbrbropiddunner(dcbrBropiddunner);
		setDcbrbropidsaler(dcbrBropidsaler);
		setStstcode(stStcode);
		setStstname(stStname);
		setCtctcode(ctCtcode);
		setCtctname(ctCtname);
	}

	public void setDceecode(String dcEecode) {
		this.setField(0, dcEecode);
	}

	public String getDceecode() {
		return this.getField(0);
	}

	public void setDceeeecode(String dceeEecode) {
		this.setField(1, dceeEecode);
	}

	public String getDceeeecode() {
		return this.getField(1);
	}

	public void setDceeeelevel(int dceeEelevel) {
		this.setField(2, dceeEelevel);
	}

	public String getDceeeelevel() {
		return this.getField(2);
	}

	public void setDceeeestructurecode(String dceeEestructurecode) {
		this.setField(3, dceeEestructurecode);
	}

	public String getDceeeestructurecode() {
		return this.getField(3);
	}

	public void setDceeeename(String dceeEename) {
		this.setField(4, dceeEename);
	}

	public String getDceeeename() {
		return this.getField(4);
	}

	public void setDceeeeename(String dceeEeename) {
		this.setField(5, dceeEeename);
	}

	public String getDceeeeename() {
		return this.getField(5);
	}

	public void setDceeeesname(String dceeEesname) {
		this.setField(6, dceeEesname);
	}

	public String getDceeeesname() {
		return this.getField(6);
	}

	public void setDceeeeesname(String dceeEeesname) {
		this.setField(7, dceeEeesname);
	}

	public String getDceeeeesname() {
		return this.getField(7);
	}

	public void setDceeeeaddress(String dceeEeaddress) {
		this.setField(8, dceeEeaddress);
	}

	public String getDceeeeaddress() {
		return this.getField(8);
	}

	public void setDceeeeeaddress(String dceeEeeaddress) {
		this.setField(9, dceeEeeaddress);
	}

	public String getDceeeeeaddress() {
		return this.getField(9);
	}

	public void setDceeeepostcode(String dceeEepostcode) {
		this.setField(10, dceeEepostcode);
	}

	public String getDceeeepostcode() {
		return this.getField(10);
	}

	public void setDceeeeemail(String dceeEeemail) {
		this.setField(11, dceeEeemail);
	}

	public String getDceeeeemail() {
		return this.getField(11);
	}

	public void setDceeeetelephone(String dceeEetelephone) {
		this.setField(12, dceeEetelephone);
	}

	public String getDceeeetelephone() {
		return this.getField(12);
	}

	public void setDceeeefax(String dceeEefax) {
		this.setField(13, dceeEefax);
	}

	public String getDceeeefax() {
		return this.getField(13);
	}

	public void setDceeeeopidcreator(long dceeEeopidcreator) {
		this.setField(14, dceeEeopidcreator);
	}

	public String getDceeeeopidcreator() {
		return this.getField(14);
	}

	public void setDceeeecreatedate(Date dceeEecreatedate) {
		this.setField(15, dceeEecreatedate);
	}

	public String getDceeeecreatedate() {
		return this.getField(15);
	}

	public void setDceeeeopidmodifier(long dceeEeopidmodifier) {
		this.setField(16, dceeEeopidmodifier);
	}

	public String getDceeeeopidmodifier() {
		return this.getField(16);
	}

	public void setDceeeemodifydate(Date dceeEemodifydate) {
		this.setField(17, dceeEemodifydate);
	}

	public String getDceeeemodifydate() {
		return this.getField(17);
	}

	public void setDceedtdtcode(String dceedtDtcode) {
		this.setField(18, dceedtDtcode);
	}

	public String getDceedtdtcode() {
		return this.getField(18);
	}

	public void setDceedtdtname(String dceedtDtname) {
		this.setField(19, dceedtDtname);
	}

	public String getDceedtdtname() {
		return this.getField(19);
	}

	public void setDceedtdtename(String dceedtDtename) {
		this.setField(20, dceedtDtename);
	}

	public String getDceedtdtename() {
		return this.getField(20);
	}

	public void setDceergrgcode(String dceergRgcode) {
		this.setField(21, dceergRgcode);
	}

	public String getDceergrgcode() {
		return this.getField(21);
	}

	public void setDceergrgname(String dceergRgname) {
		this.setField(22, dceergRgname);
	}

	public String getDceergrgname() {
		return this.getField(22);
	}

	public void setDceergrgename(String dceergRgename) {
		this.setField(23, dceergRgename);
	}

	public String getDceergrgename() {
		return this.getField(23);
	}

	public void setDceeeekeekcode(String dceeeekEekcode) {
		this.setField(24, dceeeekEekcode);
	}

	public String getDceeeekeekcode() {
		return this.getField(24);
	}

	public void setDceeeekeekname(String dceeeekEekname) {
		this.setField(25, dceeeekEekname);
	}

	public String getDceeeekeekname() {
		return this.getField(25);
	}

	public void setDceeeekeekename(String dceeeekEekename) {
		this.setField(26, dceeeekEekename);
	}

	public String getDceeeekeekename() {
		return this.getField(26);
	}

	public void setDcdtbdtcode(String dcdtbDtcode) {
		this.setField(27, dcdtbDtcode);
	}

	public String getDcdtbdtcode() {
		return this.getField(27);
	}

	public void setDcdtbdtname(String dcdtbDtname) {
		this.setField(28, dcdtbDtname);
	}

	public String getDcdtbdtname() {
		return this.getField(28);
	}

	public void setDcdtbdtename(String dcdtbDtename) {
		this.setField(29, dcdtbDtename);
	}

	public String getDcdtbdtename() {
		return this.getField(29);
	}

	public void setDcbreecode(String dcbrEecode) {
		this.setField(30, dcbrEecode);
	}

	public String getDcbreecode() {
		return this.getField(30);
	}

	public void setDcbrbropidmanager(Long dcbrBropidmanager) {
		this.setField(31, dcbrBropidmanager);
	}

	public String getDcbrbropidmanager() {
		return this.getField(31);
	}

	public void setDcbrbropidcustomerservice(Long dcbrBropidcustomerservice) {
		this.setField(32, dcbrBropidcustomerservice);
	}

	public String getDcbrbropidcustomerservice() {
		return this.getField(32);
	}

	public void setDcbrbropiddunner(Long dcbrBropiddunner) {
		this.setField(33, dcbrBropiddunner);
	}

	public String getDcbrbropiddunner() {
		return this.getField(33);
	}

	public void setDcbrbropidsaler(Long dcbrBropidsaler) {
		this.setField(34, dcbrBropidsaler);
	}

	public String getDcbrbropidsaler() {
		return this.getField(34);
	}

	public void setStstcode(String stStcode) {
		this.setField(35, stStcode);
	}

	public String getStstcode() {
		return this.getField(35);
	}

	public void setStstname(String stStname) {
		this.setField(36, stStname);
	}

	public String getStstname() {
		return this.getField(36);
	}

	public void setCtctcode(String ctCtcode) {
		this.setField(37, ctCtcode);
	}

	public String getCtctcode() {
		return this.getField(37);
	}

	public void setCtctname(String ctCtname) {
		this.setField(38, ctCtname);
	}

	public String getCtctname() {
		return this.getField(38);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
