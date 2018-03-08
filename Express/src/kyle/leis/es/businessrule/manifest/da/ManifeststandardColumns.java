package kyle.leis.es.businessrule.manifest.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ManifeststandardColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ManifeststandardColumns() {
		m_astrColumns = new String[4];
	}
	
	public ManifeststandardColumns(Long mscMsccode, 
            String mscMsccolumnname, String mscMsccolumnename, 
            String mscMscsqlcolumnname){
		m_astrColumns = new String[4];
		setMscmsccode(mscMsccode);
		setMscmsccolumnname(mscMsccolumnname);
		setMscmsccolumnename(mscMsccolumnename);
		setMscmscsqlcolumnname(mscMscsqlcolumnname);
	}

	public void setMscmsccode(Long mscMsccode) {
		this.setField(0, mscMsccode);
	}

	public String getMscmsccode() {
		return this.getField(0);
	}

	public void setMscmsccolumnname(String mscMsccolumnname) {
		this.setField(1, mscMsccolumnname);
	}

	public String getMscmsccolumnname() {
		return this.getField(1);
	}

	public void setMscmsccolumnename(String mscMsccolumnename) {
		this.setField(2, mscMsccolumnename);
	}

	public String getMscmsccolumnename() {
		return this.getField(2);
	}

	public void setMscmscsqlcolumnname(String mscMscsqlcolumnname) {
		this.setField(3, mscMscsqlcolumnname);
	}

	public String getMscmscsqlcolumnname() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
