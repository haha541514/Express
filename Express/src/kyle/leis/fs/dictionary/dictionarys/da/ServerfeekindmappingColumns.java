package kyle.leis.fs.dictionary.dictionarys.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ServerfeekindmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ServerfeekindmappingColumns() {
		m_astrColumns = new String[4];
	}
	
	public ServerfeekindmappingColumns(String sfmSfkmcode, 
            String sfmSfkmserverbillkind, String sfmSfkmorigindesc, 
            String fkFkcode){
		m_astrColumns = new String[4];
		setSfmsfkmcode(sfmSfkmcode);
		setSfmsfkmserverbillkind(sfmSfkmserverbillkind);
		setSfmsfkmorigindesc(sfmSfkmorigindesc);
		setFkfkcode(fkFkcode);
	}

	public void setSfmsfkmcode(String sfmSfkmcode) {
		this.setField(0, sfmSfkmcode);
	}

	public String getSfmsfkmcode() {
		return this.getField(0);
	}

	public void setSfmsfkmserverbillkind(String sfmSfkmserverbillkind) {
		this.setField(1, sfmSfkmserverbillkind);
	}

	public String getSfmsfkmserverbillkind() {
		return this.getField(1);
	}

	public void setSfmsfkmorigindesc(String sfmSfkmorigindesc) {
		this.setField(2, sfmSfkmorigindesc);
	}

	public String getSfmsfkmorigindesc() {
		return this.getField(2);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(3, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(3);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
