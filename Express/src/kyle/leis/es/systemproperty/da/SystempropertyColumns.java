package kyle.leis.es.systemproperty.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class SystempropertyColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public SystempropertyColumns() {
		m_astrColumns = new String[6];
	}
	
	public SystempropertyColumns(String spcomp_idSpcode, 
            String spSpname, String spSpvalue, 
            String eeEecode, String eeEename, 
            String eeEeename){
		m_astrColumns = new String[6];
		setSpcomp_idspcode(spcomp_idSpcode);
		setSpspname(spSpname);
		setSpspvalue(spSpvalue);
		setEeeecode(eeEecode);
		setEeeename(eeEename);
		setEeeeename(eeEeename);
	}

	public void setSpcomp_idspcode(String spcomp_idSpcode) {
		this.setField(0, spcomp_idSpcode);
	}

	public String getSpcomp_idspcode() {
		return this.getField(0);
	}

	public void setSpspname(String spSpname) {
		this.setField(1, spSpname);
	}

	public String getSpspname() {
		return this.getField(1);
	}

	public void setSpspvalue(String spSpvalue) {
		this.setField(2, spSpvalue);
	}

	public String getSpspvalue() {
		return this.getField(2);
	}

	public void setEeeecode(String eeEecode) {
		this.setField(3, eeEecode);
	}

	public String getEeeecode() {
		return this.getField(3);
	}

	public void setEeeename(String eeEename) {
		this.setField(4, eeEename);
	}

	public String getEeeename() {
		return this.getField(4);
	}

	public void setEeeeename(String eeEeename) {
		this.setField(5, eeEeename);
	}

	public String getEeeeename() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
