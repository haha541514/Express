package kyle.leis.fs.dictionary.customscargo.da;

import java.io.Serializable;
import java.util.Date;

import kyle.common.dbaccess.query.GeneralColumns;

public class CustomscargoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomscargoColumns() {
		m_astrColumns = new String[7];
	}
	
	public CustomscargoColumns(String ccCccode, 
            String ccCcename, String ccCcname, 
            String ccCchscode, Date ccCcmodifydate, 
            long ccCcopidmodifier, String ccCcunittype){
		m_astrColumns = new String[7];
		setCccccode(ccCccode);
		setCcccename(ccCcename);
		setCcccname(ccCcname);
		setCccchscode(ccCchscode);
		setCcccmodifydate(ccCcmodifydate);
		setCcccopidmodifier(ccCcopidmodifier);
		setCcccunittype(ccCcunittype);
	}

	public void setCccccode(String ccCccode) {
		this.setField(0, ccCccode);
	}

	public String getCccccode() {
		return this.getField(0);
	}

	public void setCcccename(String ccCcename) {
		this.setField(1, ccCcename);
	}

	public String getCcccename() {
		return this.getField(1);
	}

	public void setCcccname(String ccCcname) {
		this.setField(2, ccCcname);
	}

	public String getCcccname() {
		return this.getField(2);
	}

	public void setCccchscode(String ccCchscode) {
		this.setField(3, ccCchscode);
	}

	public String getCccchscode() {
		return this.getField(3);
	}

	public void setCcccmodifydate(Date ccCcmodifydate) {
		this.setField(4, ccCcmodifydate);
	}

	public String getCcccmodifydate() {
		return this.getField(4);
	}

	public void setCcccopidmodifier(long ccCcopidmodifier) {
		this.setField(5, ccCcopidmodifier);
	}

	public String getCcccopidmodifier() {
		return this.getField(5);
	}

	public void setCcccunittype(String ccCcunittype) {
		this.setField(6, ccCcunittype);
	}

	public String getCcccunittype() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
