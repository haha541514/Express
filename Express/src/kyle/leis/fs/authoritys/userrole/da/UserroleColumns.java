package kyle.leis.fs.authoritys.userrole.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class UserroleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UserroleColumns() {
		m_astrColumns = new String[14];
	}
	
	public UserroleColumns(String ulcomp_idUrusercode, 
            String rlRlcode, String rlRlname, 
            String rlRlename, String rlRladministratorsign, 
            Integer rlRlopidcreator, Date rlRlcreatedate, 
            Integer rlRlopidmodifier, Date rlRlmodifydate, 
            String rlRlstructurecode, String rlRlremark, 
            String iskIskname, String iskIskename, 
            String iskIskcode){
		m_astrColumns = new String[14];
		setUlcomp_idurusercode(ulcomp_idUrusercode);
		setRlrlcode(rlRlcode);
		setRlrlname(rlRlname);
		setRlrlename(rlRlename);
		setRlrladministratorsign(rlRladministratorsign);
		setRlrlopidcreator(rlRlopidcreator);
		setRlrlcreatedate(rlRlcreatedate);
		setRlrlopidmodifier(rlRlopidmodifier);
		setRlrlmodifydate(rlRlmodifydate);
		setRlrlstructurecode(rlRlstructurecode);
		setRlrlremark(rlRlremark);
		setIskiskname(iskIskname);
		setIskiskename(iskIskename);
		setIskiskcode(iskIskcode);
	}

	public void setUlcomp_idurusercode(String ulcomp_idUrusercode) {
		this.setField(0, ulcomp_idUrusercode);
	}

	public String getUlcomp_idurusercode() {
		return this.getField(0);
	}

	public void setRlrlcode(String rlRlcode) {
		this.setField(1, rlRlcode);
	}

	public String getRlrlcode() {
		return this.getField(1);
	}

	public void setRlrlname(String rlRlname) {
		this.setField(2, rlRlname);
	}

	public String getRlrlname() {
		return this.getField(2);
	}

	public void setRlrlename(String rlRlename) {
		this.setField(3, rlRlename);
	}

	public String getRlrlename() {
		return this.getField(3);
	}

	public void setRlrladministratorsign(String rlRladministratorsign) {
		this.setField(4, rlRladministratorsign);
	}

	public String getRlrladministratorsign() {
		return this.getField(4);
	}

	public void setRlrlopidcreator(Integer rlRlopidcreator) {
		this.setField(5, rlRlopidcreator);
	}

	public String getRlrlopidcreator() {
		return this.getField(5);
	}

	public void setRlrlcreatedate(Date rlRlcreatedate) {
		this.setField(6, rlRlcreatedate);
	}

	public String getRlrlcreatedate() {
		return this.getField(6);
	}

	public void setRlrlopidmodifier(Integer rlRlopidmodifier) {
		this.setField(7, rlRlopidmodifier);
	}

	public String getRlrlopidmodifier() {
		return this.getField(7);
	}

	public void setRlrlmodifydate(Date rlRlmodifydate) {
		this.setField(8, rlRlmodifydate);
	}

	public String getRlrlmodifydate() {
		return this.getField(8);
	}

	public void setRlrlstructurecode(String rlRlstructurecode) {
		this.setField(9, rlRlstructurecode);
	}

	public String getRlrlstructurecode() {
		return this.getField(9);
	}

	public void setRlrlremark(String rlRlremark) {
		this.setField(10, rlRlremark);
	}

	public String getRlrlremark() {
		return this.getField(10);
	}

	public void setIskiskname(String iskIskname) {
		this.setField(11, iskIskname);
	}

	public String getIskiskname() {
		return this.getField(11);
	}

	public void setIskiskename(String iskIskename) {
		this.setField(12, iskIskename);
	}

	public String getIskiskename() {
		return this.getField(12);
	}

	public void setIskiskcode(String iskIskcode) {
		this.setField(13, iskIskcode);
	}

	public String getIskiskcode() {
		return this.getField(13);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
