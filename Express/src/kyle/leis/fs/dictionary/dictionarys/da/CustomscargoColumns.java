package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomscargoColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomscargoColumns() {
		m_astrColumns = new String[4];
	}
	
	public CustomscargoColumns(String ccCc_ename, 
            String ccCc_name, String ccCc_hscode,
            String ccCc_unittype){
		m_astrColumns = new String[4];
		setCccc_ename(ccCc_ename);
		setCccc_name(ccCc_name);
		setCccc_hscode(ccCc_hscode);
		setCccc_unittype(ccCc_unittype);
	}

	public void setCccc_ename(String ccCc_ename) {
		this.setField(0, ccCc_ename);
	}

	public String getCccc_ename() {
		return this.getField(0);
	}

	public void setCccc_name(String ccCc_name) {
		this.setField(1, ccCc_name);
	}

	public String getCccc_name() {
		return this.getField(1);
	}

	public void setCccc_hscode(String ccCc_hscode) {
		this.setField(2, ccCc_hscode);
	}

	public String getCccc_hscode() {
		return this.getField(2);
	}

	public void setCccc_unittype(String ccCc_unittype) {
		this.setField(3, ccCc_unittype);
	}

	public String getCccc_unittype() {
		return this.getField(3);
	}	
	
	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
