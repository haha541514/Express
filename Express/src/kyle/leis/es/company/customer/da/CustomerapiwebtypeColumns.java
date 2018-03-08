package kyle.leis.es.company.customer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerapiwebtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerapiwebtypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public CustomerapiwebtypeColumns(String capwtCapwtcode, 
            String capwtCapwtname, String capwtCapwtename){
		m_astrColumns = new String[3];
		setCapwtcapwtcode(capwtCapwtcode);
		setCapwtcapwtname(capwtCapwtname);
		setCapwtcapwtename(capwtCapwtename);
	}

	public void setCapwtcapwtcode(String capwtCapwtcode) {
		this.setField(0, capwtCapwtcode);
	}

	public String getCapwtcapwtcode() {
		return this.getField(0);
	}

	public void setCapwtcapwtname(String capwtCapwtname) {
		this.setField(1, capwtCapwtname);
	}

	public String getCapwtcapwtname() {
		return this.getField(1);
	}

	public void setCapwtcapwtename(String capwtCapwtename) {
		this.setField(2, capwtCapwtename);
	}

	public String getCapwtcapwtename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
