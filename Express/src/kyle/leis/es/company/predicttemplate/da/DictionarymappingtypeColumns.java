package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class DictionarymappingtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public DictionarymappingtypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public DictionarymappingtypeColumns(String dmkDmk_code, 
            String dmkDmk_name, String dmkDmk_ename){
		m_astrColumns = new String[3];
		setDmkdmk_code(dmkDmk_code);
		setDmkdmk_name(dmkDmk_name);
		setDmkdmk_ename(dmkDmk_ename);
	}

	public void setDmkdmk_code(String dmkDmk_code) {
		this.setField(0, dmkDmk_code);
	}

	public String getDmkdmk_code() {
		return this.getField(0);
	}

	public void setDmkdmk_name(String dmkDmk_name) {
		this.setField(1, dmkDmk_name);
	}

	public String getDmkdmk_name() {
		return this.getField(1);
	}

	public void setDmkdmk_ename(String dmkDmk_ename) {
		this.setField(2, dmkDmk_ename);
	}

	public String getDmkdmk_ename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
