package kyle.leis.fs.cachecontainer.dax;

import java.io.Serializable;

import kyle.common.dbaccess.query.GeneralColumns;
import kyle.common.util.jlang.StringUtility;

public class ProductkindColumnsEX  extends GeneralColumns implements Serializable, Comparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ProductkindColumnsEX() {
		m_astrColumns = new String[13];
	}
	
	public ProductkindColumnsEX(String strPkcode, String strPkname, 
			String strPkename, String strPksname, 
			String strPksename, String strPkdescription, 
			String strSscode, String strPksigninrestrictsign,
			String strPkShowserverewbcode, String strPkBillingbybatchwaysign,
			String strPkSigninneedpostcode, String strPksiprintselflabelcodesign,
			String strPkwebstructurecode){
		m_astrColumns = new String[13];
		setPkcode(strPkcode);
		setPkname(strPkname);
		setPkename(strPkename);
		setPksname(strPksname);
		setPksename(strPksename);
		setPkdescription(strPkdescription);
		setSscode(strSscode);
		setPksigninrestrictsign(strPksigninrestrictsign);
		setPkShowserverewbcode(strPkShowserverewbcode);
		setPkbillingbybatchwaysign(strPkBillingbybatchwaysign);
		setPkSigninneedpostcode(strPkSigninneedpostcode);
		setPksiprintselflabelcodesign(strPksiprintselflabelcodesign);
		setPkwebstructurecode(strPkwebstructurecode);
	}

	public void setPkcode(String strPkcode) {
		this.setField(0, strPkcode);
	}

	public String getPkcode() {
		return this.getField(0);
	}


	public void setPkname(String strPkname) {
		this.setField(1, strPkname);
	}

	public String getPkname() {
		return this.getField(1);
	}


	public void setPkename(String strPkename) {
		this.setField(2, strPkename);
	}

	public String getPkename() {
		return this.getField(2);
	}


	public void setPksname(String strPksname) {
		this.setField(3, strPksname);
	}

	public String getPksname() {
		return this.getField(3);
	}


	public void setPksename(String strPksename) {
		this.setField(4, strPksename);
	}

	public String getPksename() {
		return this.getField(4);
	}


	public void setPkdescription(String strPkdescription) {
		this.setField(5, strPkdescription);
	}

	public String getPkdescription() {
		return this.getField(5);
	}


	public void setSscode(String strSscode) {
		this.setField(6, strSscode);
	}

	public String getSscode() {
		return this.getField(6);
	}

	public void setPksigninrestrictsign(String strPksigninrestrictsign) {
		this.setField(7, strPksigninrestrictsign);
	}

	public String getPksigninrestrictsign() {
		return this.getField(7);
	}
	
	public void setPkShowserverewbcode(String strPkShowserverewbcode) {
		this.setField(8, strPkShowserverewbcode);
	}

	public String getPkShowserverewbcode() {
		return this.getField(8);
	}	
	
	public void setPkbillingbybatchwaysign(String strPkBillingbybatchwaysign) {
		this.setField(9, strPkBillingbybatchwaysign);
	}

	public String getPkbillingbybatchwaysign() {
		return this.getField(9);
	}	
	
	public void setPkSigninneedpostcode(String strPkSigninneedpostcode) {
		this.setField(10, strPkSigninneedpostcode);
	}

	public String getPkSigninneedpostcode() {
		return this.getField(10);
	}		
	
	public void setPksiprintselflabelcodesign(String strPksiprintselflabelcodesign) {
		this.setField(11, strPksiprintselflabelcodesign);
	}

	public String getPksiprintselflabelcodesign() {
		return this.getField(11);
	}	
	
	public void setPkwebstructurecode(String strPkwebstructurecode) {
		this.setField(12, strPkwebstructurecode);
	}

	public String getPkwebstructurecode() {
		return this.getField(12);
	}	
	
    public String toString() {
        return "Code Generate By Kyle";
    }

	public int compareTo(Object o) {
		if (StringUtility.isNull(getPkwebstructurecode()) &&
				!StringUtility.isNull(((ProductkindColumnsEX)o).getPkwebstructurecode()))
			return -1;
		if (!StringUtility.isNull(getPkwebstructurecode()) &&
				StringUtility.isNull(((ProductkindColumnsEX)o).getPkwebstructurecode()))
			return 1;
		if (!StringUtility.isNull(getPkwebstructurecode()) &&
				!StringUtility.isNull(((ProductkindColumnsEX)o).getPkwebstructurecode()))
			return getPkwebstructurecode().compareTo(((ProductkindColumnsEX)o).getPkwebstructurecode());
		return 0;
	}	

}
