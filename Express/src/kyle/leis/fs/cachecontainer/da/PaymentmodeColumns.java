package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PaymentmodeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PaymentmodeColumns() {
		
	}
	
	public PaymentmodeColumns(String pmPmcode, 
            String pmPmname, String pmPmename, 
            String pmPmsename, String pmPmvisiblesign, 
            String ssSscode){
		m_astrColumns = new String[6];
		setPmpmcode(pmPmcode);
		setPmpmname(pmPmname);
		setPmpmename(pmPmename);
		setPmpmsename(pmPmsename);
		setPmpmvisiblesign(pmPmvisiblesign);
		setSssscode(ssSscode);
	}

	public void setPmpmcode(String pmPmcode) {
		this.setField(0, pmPmcode);
	}

	public String getPmpmcode() {
		return this.getField(0);
	}

	public void setPmpmname(String pmPmname) {
		this.setField(1, pmPmname);
	}

	public String getPmpmname() {
		return this.getField(1);
	}

	public void setPmpmename(String pmPmename) {
		this.setField(2, pmPmename);
	}

	public String getPmpmename() {
		return this.getField(2);
	}

	public void setPmpmsename(String pmPmsename) {
		this.setField(3, pmPmsename);
	}

	public String getPmpmsename() {
		return this.getField(3);
	}

	public void setPmpmvisiblesign(String pmPmvisiblesign) {
		this.setField(4, pmPmvisiblesign);
	}

	public String getPmpmvisiblesign() {
		return this.getField(4);
	}

	public void setSssscode(String ssSscode) {
		this.setField(5, ssSscode);
	}

	public String getSssscode() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
