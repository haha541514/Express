package kyle.leis.fs.dictionary.productkind.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PkcargokindColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PkcargokindColumns() {
		m_astrColumns = new String[2];
	}
	
	public PkcargokindColumns(String pkckcomp_idPkcode, 
            String pkckcomp_idCgkcode){
		m_astrColumns = new String[2];
		setPkckcomp_idpkcode(pkckcomp_idPkcode);
		setPkckcomp_idcgkcode(pkckcomp_idCgkcode);
	}

	public void setPkckcomp_idpkcode(String pkckcomp_idPkcode) {
		this.setField(0, pkckcomp_idPkcode);
	}

	public String getPkckcomp_idpkcode() {
		return this.getField(0);
	}

	public void setPkckcomp_idcgkcode(String pkckcomp_idCgkcode) {
		this.setField(1, pkckcomp_idCgkcode);
	}

	public String getPkckcomp_idcgkcode() {
		return this.getField(1);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
