package kyle.leis.es.price.pricegroup.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class CustomerpricegroupvalueColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public CustomerpricegroupvalueColumns() {
		
	}
	
	public CustomerpricegroupvalueColumns(String cpgvcomp_idPgcode, 
            Long cpgvcomp_idEpcode, String cpgvcomp_idFkcode, 
            String pgPgcode, String pgPgname, 
            String fkFkcode, String fkFkname){
		m_astrColumns = new String[7];
		setCpgvcomp_idpgcode(cpgvcomp_idPgcode);
		setCpgvcomp_idepcode(cpgvcomp_idEpcode);
		setCpgvcomp_idfkcode(cpgvcomp_idFkcode);
		setPgpgcode(pgPgcode);
		setPgpgname(pgPgname);
		setFkfkcode(fkFkcode);
		setFkfkname(fkFkname);
	}

	public void setCpgvcomp_idpgcode(String cpgvcomp_idPgcode) {
		this.setField(0, cpgvcomp_idPgcode);
	}

	public String getCpgvcomp_idpgcode() {
		return this.getField(0);
	}

	public void setCpgvcomp_idepcode(Long cpgvcomp_idEpcode) {
		this.setField(1, cpgvcomp_idEpcode);
	}

	public String getCpgvcomp_idepcode() {
		return this.getField(1);
	}

	public void setCpgvcomp_idfkcode(String cpgvcomp_idFkcode) {
		this.setField(2, cpgvcomp_idFkcode);
	}

	public String getCpgvcomp_idfkcode() {
		return this.getField(2);
	}

	public void setPgpgcode(String pgPgcode) {
		this.setField(3, pgPgcode);
	}

	public String getPgpgcode() {
		return this.getField(3);
	}

	public void setPgpgname(String pgPgname) {
		this.setField(4, pgPgname);
	}

	public String getPgpgname() {
		return this.getField(4);
	}

	public void setFkfkcode(String fkFkcode) {
		this.setField(5, fkFkcode);
	}

	public String getFkfkcode() {
		return this.getField(5);
	}

	public void setFkfkname(String fkFkname) {
		this.setField(6, fkFkname);
	}

	public String getFkfkname() {
		return this.getField(6);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
