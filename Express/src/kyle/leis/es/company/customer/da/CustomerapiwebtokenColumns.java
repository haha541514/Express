package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerapiwebtokenColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerapiwebtokenColumns() {
		m_astrColumns = new String[8];
	}
	
	public CustomerapiwebtokenColumns(Integer cawtCawtid, 
            String coCocode, String cawtCawtusername, 
            String cawtCawttoken, String capwtCapwtcode, 
            String capwtCapwtname, String capwtCapwtename, String cawtCawtpassword){
		m_astrColumns = new String[8];
		setCawtcawtid(cawtCawtid);
		setCococode(coCocode);
		setCawtcawtusername(cawtCawtusername);
		setCawtcawttoken(cawtCawttoken);
		setCapwtcapwtcode(capwtCapwtcode);
		setCapwtcapwtname(capwtCapwtname);
		setCapwtcapwtename(capwtCapwtename);
		setCawtcawtpassword(cawtCawtpassword);
	}

	public void setCawtcawtid(Integer cawtCawtid) {
		this.setField(0, cawtCawtid);
	}

	public String getCawtcawtid() {
		return this.getField(0);
	}

	public void setCococode(String coCocode) {
		this.setField(1, coCocode);
	}

	public String getCococode() {
		return this.getField(1);
	}

	public void setCawtcawtusername(String cawtCawtusername) {
		this.setField(2, cawtCawtusername);
	}

	public String getCawtcawtusername() {
		return this.getField(2);
	}

	public void setCawtcawttoken(String cawtCawttoken) {
		this.setField(3, cawtCawttoken);
	}

	public String getCawtcawttoken() {
		return this.getField(3);
	}

	public void setCapwtcapwtcode(String capwtCapwtcode) {
		this.setField(4, capwtCapwtcode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(4);
	}

	public void setCapwtcapwtname(String capwtCapwtname) {
		this.setField(5, capwtCapwtname);
	}

	public String getCapwtcapwtname() {
		return this.getField(5);
	}

	public void setCapwtcapwtename(String capwtCapwtename) {
		this.setField(6, capwtCapwtename);
	}

	public String getCapwtcapwtename() {
		return this.getField(6);
	}
	
	public void setCawtcawtpassword(String cawtCawtpassword) {
		this.setField(7, cawtCawtpassword);
	}

	public String getCawtcawtpassword() {
		return this.getField(7);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
