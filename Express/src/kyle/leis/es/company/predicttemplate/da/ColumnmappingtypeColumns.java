package kyle.leis.es.company.predicttemplate.da;

import java.io.Serializable;
import kyle.common.dbaccess.query.GeneralColumns;

public class ColumnmappingtypeColumns extends GeneralColumns implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8265284628323765505L;

	public ColumnmappingtypeColumns() {
		m_astrColumns = new String[3];
	}
	
	public ColumnmappingtypeColumns(String cmtCmt_code, 
            String cmtCmt_name, String cmtCmt_ename){
		m_astrColumns = new String[3];
		setCmtcmt_code(cmtCmt_code);
		setCmtcmt_name(cmtCmt_name);
		setCmtcmt_ename(cmtCmt_ename);
	}

	public void setCmtcmt_code(String cmtCmt_code) {
		this.setField(0, cmtCmt_code);
	}

	public String getCmtcmt_code() {
		return this.getField(0);
	}

	public void setCmtcmt_name(String cmtCmt_name) {
		this.setField(1, cmtCmt_name);
	}

	public String getCmtcmt_name() {
		return this.getField(1);
	}

	public void setCmtcmt_ename(String cmtCmt_ename) {
		this.setField(2, cmtCmt_ename);
	}

	public String getCmtcmt_ename() {
		return this.getField(2);
	}

	
    public String toString() {
        return "Code Generate By Kyle";
    }	
	
}
