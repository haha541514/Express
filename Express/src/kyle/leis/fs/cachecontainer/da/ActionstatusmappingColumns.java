package kyle.leis.fs.cachecontainer.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ActionstatusmappingColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ActionstatusmappingColumns() {
		m_astrColumns = new String[6];
	}
	
	public ActionstatusmappingColumns(String asmcode, 
            String asmbusinesskind, String asmoriginstatus, 
            String asmfinalstatus, String akAkcode, 
            String akAkname){
		m_astrColumns = new String[6];
		setAsmcode(asmcode);
		setAsmbusinesskind(asmbusinesskind);
		setAsmoriginstatus(asmoriginstatus);
		setAsmfinalstatus(asmfinalstatus);
		setAkakcode(akAkcode);
		setAkakname(akAkname);
	}

	public void setAsmcode(String asmcode) {
		this.setField(0, asmcode);
	}

	public String getAsmcode() {
		return this.getField(0);
	}

	public void setAsmbusinesskind(String asmbusinesskind) {
		this.setField(1, asmbusinesskind);
	}

	public String getAsmbusinesskind() {
		return this.getField(1);
	}

	public void setAsmoriginstatus(String asmoriginstatus) {
		this.setField(2, asmoriginstatus);
	}

	public String getAsmoriginstatus() {
		return this.getField(2);
	}

	public void setAsmfinalstatus(String asmfinalstatus) {
		this.setField(3, asmfinalstatus);
	}

	public String getAsmfinalstatus() {
		return this.getField(3);
	}

	public void setAkakcode(String akAkcode) {
		this.setField(4, akAkcode);
	}

	public String getAkakcode() {
		return this.getField(4);
	}

	public void setAkakname(String akAkname) {
		this.setField(5, akAkname);
	}

	public String getAkakname() {
		return this.getField(5);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
