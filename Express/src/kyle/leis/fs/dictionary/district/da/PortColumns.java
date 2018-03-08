package kyle.leis.fs.dictionary.district.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class PortColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public PortColumns() {
		m_astrColumns = new String[3];
	}
	
	public PortColumns(String pPt_code,
			String pPt_ename, 
            String pPt_cname){
		m_astrColumns = new String[3];
		setPpt_ename(pPt_code);
		setPpt_ename(pPt_ename);
		setPpt_cname(pPt_cname);
	}

	public void setPpt_code(String pPt_code) {
		this.setField(0, pPt_code);
	}

	public String getPpt_code() {
		return this.getField(0);
	}

	public void setPpt_ename(String pPt_ename) {
		this.setField(1, pPt_ename);
	}

	public String getPpt_ename() {
		return this.getField(1);
	}
	
	public void setPpt_cname(String pPt_cname) {
		this.setField(2, pPt_cname);
	}

	public String getPpt_cname() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
