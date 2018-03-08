package kyle.leis.fs.dictionary.enterpriseelement.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class EnterpriseelementColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public EnterpriseelementColumns() {
		m_astrColumns = new String[29];
	}
	
	public EnterpriseelementColumns(String eeEecode, 
            int eeEelevel, String eeEestructurecode, 
            String eeEename, String eeEeename, 
            String eeEesname, String eeEeesname, 
            String eeEeaddress, String eeEeeaddress, 
            String eeEepostcode, String eeEeemail, 
            String eeEetelephone, String eeEefax, 
            long eeEeopidcreator, Date eeEecreatedate, 
            long eeEeopidmodifier, Date eeEemodifydate, 
            String dtDtcode, String dtDtname, 
            String dtDtename, String dtDtstatecode, 
            String dtDtstatename, String dtDtgrade, 
            String rgRgcode, String rgRgname, 
            String rgRgename, String eekEekcode, 
            String eekEekname, String eekEekename){
		m_astrColumns = new String[29];
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
	}

	public void setEeeecode(String eeEecode) {
		this.setField(0, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(0);
	}

	public void setEeeelevel(int eeEelevel) {
		this.setField(1, eeEelevel);
	}

	public String getEeeelevel() {
		return this.getField(1);
	}

	public void setEeeestructurecode(String eeEestructurecode) {
		this.setField(2, eeEestructurecode);
	}

	public String getEeeestructurecode() {
		return this.getField(2);
	}

	public void setEeeename(String eeEename) {
		this.setField(3, eeEename);
	}

	public String getEeeename() {
		return this.getField(3);
	}

	public void setEeeeename(String eeEeename) {
		this.setField(4, eeEeename);
	}

	public String getEeeeename() {
		return this.getField(4);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(5, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(5);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(6, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(6);
	}

	public void setEeeeaddress(String eeEeaddress) {
		this.setField(7, eeEeaddress);
	}

	public String getEeeeaddress() {
		return this.getField(7);
	}

	public void setEeeeeaddress(String eeEeeaddress) {
		this.setField(8, eeEeeaddress);
	}

	public String getEeeeeaddress() {
		return this.getField(8);
	}

	public void setEeeepostcode(String eeEepostcode) {
		this.setField(9, eeEepostcode);
	}

	public String getEeeepostcode() {
		return this.getField(9);
	}

	public void setEeeeemail(String eeEeemail) {
		this.setField(10, eeEeemail);
	}

	public String getEeeeemail() {
		return this.getField(10);
	}

	public void setEeeetelephone(String eeEetelephone) {
		this.setField(11, eeEetelephone);
	}

	public String getEeeetelephone() {
		return this.getField(11);
	}

	public void setEeeefax(String eeEefax) {
		this.setField(12, eeEefax);
	}

	public String getEeeefax() {
		return this.getField(12);
	}

	public void setEeeeopidcreator(long eeEeopidcreator) {
		this.setField(13, eeEeopidcreator);
	}

	public String getEeeeopidcreator() {
		return this.getField(13);
	}

	public void setEeeecreatedate(Date eeEecreatedate) {
		this.setField(14, eeEecreatedate);
	}

	public String getEeeecreatedate() {
		return this.getField(14);
	}

	public void setEeeeopidmodifier(long eeEeopidmodifier) {
		this.setField(15, eeEeopidmodifier);
	}

	public String getEeeeopidmodifier() {
		return this.getField(15);
	}

	public void setEeeemodifydate(Date eeEemodifydate) {
		this.setField(16, eeEemodifydate);
	}

	public String getEeeemodifydate() {
		return this.getField(16);
	}

	public void setDtdtcode(String dtDtcode) {
		this.setField(17, dtDtcode);
	}

	public String getDtdtcode() {
		return this.getField(17);
	}

	public void setDtdtname(String dtDtname) {
		this.setField(18, dtDtname);
	}

	public String getDtdtname() {
		return this.getField(18);
	}

	public void setDtdtename(String dtDtename) {
		this.setField(19, dtDtename);
	}

	public String getDtdtename() {
		return this.getField(19);
	}

	public void setDtdtstatecode(String dtDtstatecode) {
		this.setField(20, dtDtstatecode);
	}

	public String getDtdtstatecode() {
		return this.getField(20);
	}

	public void setDtdtstatename(String dtDtstatename) {
		this.setField(21, dtDtstatename);
	}

	public String getDtdtstatename() {
		return this.getField(21);
	}

	public void setDtdtgrade(String dtDtgrade) {
		this.setField(22, dtDtgrade);
	}

	public String getDtdtgrade() {
		return this.getField(22);
	}

	public void setRgrgcode(String rgRgcode) {
		this.setField(23, rgRgcode);
	}

	public String getRgrgcode() {
		return this.getField(23);
	}

	public void setRgrgname(String rgRgname) {
		this.setField(24, rgRgname);
	}

	public String getRgrgname() {
		return this.getField(24);
	}

	public void setRgrgename(String rgRgename) {
		this.setField(25, rgRgename);
	}

	public String getRgrgename() {
		return this.getField(25);
	}

	public void setEekeekcode(String eekEekcode) {
		this.setField(26, eekEekcode);
	}

	public String getEekeekcode() {
		return this.getField(26);
	}

	public void setEekeekname(String eekEekname) {
		this.setField(27, eekEekname);
	}

	public String getEekeekname() {
		return this.getField(27);
	}

	public void setEekeekename(String eekEekename) {
		this.setField(28, eekEekename);
	}

	public String getEekeekename() {
		return this.getField(28);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
