package kyle.leis.fs.authority.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class UserroleColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public UserroleColumns() {
		
	}
	
	public UserroleColumns(String strRlcode, String strRlname, String strRlename, String strRladministratorsign, Integer iRlopidcreator, Date dRlcreatedate, Integer iRlopidmodifier, Date dRlmodifydate, String strRlstructurecode, String strRlremark, String strUr_usercode, String strIskname, String strIskename, String strIskcode){
		m_astrColumns = new String[14];
		setRlcode(strRlcode);
		setRlname(strRlname);
		setRlename(strRlename);
		setRladministratorsign(strRladministratorsign);
		setRlopidcreator(iRlopidcreator);
		setRlcreatedate(dRlcreatedate);
		setRlopidmodifier(iRlopidmodifier);
		setRlmodifydate(dRlmodifydate);
		setRlstructurecode(strRlstructurecode);
		setRlremark(strRlremark);
		setUr_usercode(strUr_usercode);
		setIskname(strIskname);
		setIskename(strIskename);
		setIskcode(strIskcode);
	}

	public void setRlcode(String strRlcode) {
		this.setField(0, strRlcode);
	}

	public String getRlcode() {
		return this.getField(0);
	}


	public void setRlname(String strRlname) {
		this.setField(1, strRlname);
	}

	public String getRlname() {
		return this.getField(1);
	}


	public void setRlename(String strRlename) {
		this.setField(2, strRlename);
	}

	public String getRlename() {
		return this.getField(2);
	}


	public void setRladministratorsign(String strRladministratorsign) {
		this.setField(3, strRladministratorsign);
	}

	public String getRladministratorsign() {
		return this.getField(3);
	}


	public void setRlopidcreator(Integer strRlopidcreator) {
		this.setField(4, strRlopidcreator);
	}

	public String getRlopidcreator() {
		return this.getField(4);
	}


	public void setRlcreatedate(Date strRlcreatedate) {
		this.setField(5, strRlcreatedate);
	}

	public String getRlcreatedate() {
		return this.getField(5);
	}


	public void setRlopidmodifier(Integer strRlopidmodifier) {
		this.setField(6, strRlopidmodifier);
	}

	public String getRlopidmodifier() {
		return this.getField(6);
	}


	public void setRlmodifydate(Date strRlmodifydate) {
		this.setField(7, strRlmodifydate);
	}

	public String getRlmodifydate() {
		return this.getField(7);
	}


	public void setRlstructurecode(String strRlstructurecode) {
		this.setField(8, strRlstructurecode);
	}

	public String getRlstructurecode() {
		return this.getField(8);
	}


	public void setRlremark(String strRlremark) {
		this.setField(9, strRlremark);
	}

	public String getRlremark() {
		return this.getField(9);
	}


	public void setUr_usercode(String strUr_usercode) {
		this.setField(10, strUr_usercode);
	}

	public String getUr_usercode() {
		return this.getField(10);
	}


	public void setIskname(String strIskname) {
		this.setField(11, strIskname);
	}

	public String getIskname() {
		return this.getField(11);
	}


	public void setIskename(String strIskename) {
		this.setField(12, strIskename);
	}

	public String getIskename() {
		return this.getField(12);
	}


	public void setIskcode(String strIskcode) {
		this.setField(13, strIskcode);
	}

	public String getIskcode() {
		return this.getField(13);
	}


	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
