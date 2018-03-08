package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class TntaddressColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public TntaddressColumns() {
		m_astrColumns = new String[5];
	}
	
	public TntaddressColumns(String Tntca_companyname, 
            String Tntca_address1, String Tntca_address2, 
            String Tntca_postcode, String Tntca_cityname){
		m_astrColumns = new String[5];
		setTntca_companyname(Tntca_companyname);
		setTntca_address1(Tntca_address1);
		setTntca_address2(Tntca_address2);
		setTntca_postcode(Tntca_postcode);
		setTntca_cityname(Tntca_cityname);
	}

	public void setTntca_companyname(String Tntca_companyname) {
		this.setField(0, Tntca_companyname);
	}

	public String getTntca_companyname() {
		return this.getField(0);
	}

	public void setTntca_address1(String Tntca_address1) {
		this.setField(1, Tntca_address1);
	}

	public String getTntca_address1() {
		return this.getField(1);
	}

	public void setTntca_address2(String Tntca_address2) {
		this.setField(2, Tntca_address2);
	}

	public String getTntca_address2() {
		return this.getField(2);
	}

	public void setTntca_postcode(String Tntca_postcode) {
		this.setField(3, Tntca_postcode);
	}

	public String getTntca_postcode() {
		return this.getField(3);
	}

	public void setTntca_cityname(String Tntca_cityname) {
		this.setField(4, Tntca_cityname);
	}

	public String getTntca_cityname() {
		return this.getField(4);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
