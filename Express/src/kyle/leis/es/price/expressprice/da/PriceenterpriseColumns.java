package kyle.leis.es.price.expressprice.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PriceenterpriseColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PriceenterpriseColumns() {
		m_astrColumns = new String[4];
	}
	
	public PriceenterpriseColumns(Long pecomp_idEpcode, 
            String pecomp_idEecode, String eeEesname, 
            String eeEeesname){
		m_astrColumns = new String[4];
		setPecomp_idepcode(pecomp_idEpcode);
		setPecomp_ideecode(pecomp_idEecode);
		setEeeesname(eeEesname);
		setEeeeesname(eeEeesname);
	}

	public void setPecomp_idepcode(Long pecomp_idEpcode) {
		this.setField(0, pecomp_idEpcode);
	}

	public String getPecomp_idepcode() {
		return this.getField(0);
	}

	public void setPecomp_ideecode(String pecomp_idEecode) {
		this.setField(1, pecomp_idEecode);
	}

	public String getPecomp_ideeecode() {
		return this.getField(1);
	}

	public void setEeeesname(String eeEesname) {
		this.setField(2, eeEesname);
	}

	public String getEeeesname() {
		return this.getField(2);
	}

	public void setEeeeesname(String eeEeesname) {
		this.setField(3, eeEeesname);
	}

	public String getEeeeesname() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
